package cz.msebera.android.http.impl.client;

import c.a.a.a.i.b.n;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.CircularRedirectException;
import cz.msebera.android.http.client.RedirectStrategy;
import cz.msebera.android.http.client.auth.HttpDelete;
import cz.msebera.android.http.client.auth.HttpHead;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.client.auth.RequestBuilder;
import cz.msebera.android.http.client.cache.HttpClientContext;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.client.ssl.URIBuilder;
import cz.msebera.android.http.client.ssl.URIUtils;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpCoreContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.mime.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

public class DefaultRedirectStrategy
  implements RedirectStrategy
{
  public static final DefaultRedirectStrategy INSTANCE = new DefaultRedirectStrategy();
  private static final String[] REDIRECT_METHODS = { "GET", "HEAD" };
  public HttpClientAndroidLog log = new HttpClientAndroidLog(n.class);
  
  public DefaultRedirectStrategy() {}
  
  protected URI createLocationURI(String paramString)
  {
    try
    {
      Object localObject1 = new URIBuilder(new URI(paramString).normalize());
      localObject2 = ((URIBuilder)localObject1).getHost();
      if (localObject2 != null)
      {
        Locale localLocale = Locale.ROOT;
        ((URIBuilder)localObject1).setHost(((String)localObject2).toLowerCase(localLocale));
      }
      boolean bool = TextUtils.isEmpty(((URIBuilder)localObject1).getPath());
      if (bool) {
        ((URIBuilder)localObject1).setPath("/");
      }
      localObject1 = ((URIBuilder)localObject1).build();
      return localObject1;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Invalid redirect URI: ");
      ((StringBuilder)localObject2).append(paramString);
      throw new ProtocolException(((StringBuilder)localObject2).toString(), localURISyntaxException);
    }
  }
  
  public URI getLocationURI(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpResponse, "HTTP response");
    Args.notNull(paramHttpContext, "HTTP context");
    HttpClientContext localHttpClientContext = HttpClientContext.adapt(paramHttpContext);
    Object localObject1 = paramHttpResponse.getFirstHeader("location");
    if (localObject1 != null)
    {
      paramHttpResponse = ((Header)localObject1).getValue();
      if (log.isDebugEnabled())
      {
        localObject1 = log;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Redirect requested to location '");
        ((StringBuilder)localObject2).append(paramHttpResponse);
        ((StringBuilder)localObject2).append("'");
        ((HttpClientAndroidLog)localObject1).debug(((StringBuilder)localObject2).toString());
      }
      Object localObject2 = localHttpClientContext.getRequestConfig();
      localObject1 = createLocationURI(paramHttpResponse);
      paramHttpResponse = (HttpResponse)localObject1;
      try
      {
        boolean bool = ((URI)localObject1).isAbsolute();
        if (!bool)
        {
          bool = ((RequestConfig)localObject2).isCircularRedirectsAllowed();
          if (bool)
          {
            paramHttpResponse = localHttpClientContext.getTargetHost();
            Asserts.notNull(paramHttpResponse, "Target host");
            paramHttpResponse = URIUtils.resolve(URIUtils.rewriteURI(new URI(paramHttpRequest.getRequestLine().getUri()), paramHttpResponse, false), (URI)localObject1);
          }
          else
          {
            paramHttpRequest = new StringBuilder();
            paramHttpRequest.append("Relative redirect location '");
            paramHttpRequest.append(localObject1);
            paramHttpRequest.append("' not allowed");
            paramHttpRequest = new ProtocolException(paramHttpRequest.toString());
            throw paramHttpRequest;
          }
        }
        localObject1 = (RedirectLocations)localHttpClientContext.getAttribute("http.protocol.redirect-locations");
        paramHttpRequest = (HttpRequest)localObject1;
        if (localObject1 == null)
        {
          paramHttpRequest = new RedirectLocations();
          paramHttpContext.setAttribute("http.protocol.redirect-locations", paramHttpRequest);
        }
        if ((!((RequestConfig)localObject2).getConnectionRequestTimeout()) && (paramHttpRequest.contains(paramHttpResponse)))
        {
          paramHttpRequest = new StringBuilder();
          paramHttpRequest.append("Circular redirect to '");
          paramHttpRequest.append(paramHttpResponse);
          paramHttpRequest.append("'");
          throw new CircularRedirectException(paramHttpRequest.toString());
        }
        paramHttpRequest.add(paramHttpResponse);
        return paramHttpResponse;
      }
      catch (URISyntaxException paramHttpRequest)
      {
        throw new ProtocolException(paramHttpRequest.getMessage(), paramHttpRequest);
      }
    }
    paramHttpRequest = new StringBuilder();
    paramHttpRequest.append("Received redirect response ");
    paramHttpRequest.append(paramHttpResponse.getStatusLine());
    paramHttpRequest.append(" but no location header");
    throw new ProtocolException(paramHttpRequest.toString());
  }
  
  public HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    paramHttpContext = getLocationURI(paramHttpRequest, paramHttpResponse, paramHttpContext);
    String str = paramHttpRequest.getRequestLine().getMethod();
    if (str.equalsIgnoreCase("HEAD")) {
      return new HttpDelete(paramHttpContext);
    }
    if (str.equalsIgnoreCase("GET")) {
      return new HttpHead(paramHttpContext);
    }
    if (paramHttpResponse.getStatusLine().getStatusCode() == 307)
    {
      paramHttpRequest = RequestBuilder.copy(paramHttpRequest);
      paramHttpRequest.setUri(paramHttpContext);
      return paramHttpRequest.build();
    }
    return new HttpHead(paramHttpContext);
  }
  
  protected boolean isRedirectable(String paramString)
  {
    String[] arrayOfString = REDIRECT_METHODS;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfString[i].equalsIgnoreCase(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpResponse, "HTTP response");
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    paramHttpRequest = paramHttpRequest.getRequestLine().getMethod();
    paramHttpResponse = paramHttpResponse.getFirstHeader("location");
    if (i != 307) {
      switch (i)
      {
      default: 
        return false;
      case 303: 
        return true;
      case 302: 
        return (isRedirectable(paramHttpRequest)) && (paramHttpResponse != null);
      }
    }
    return isRedirectable(paramHttpRequest);
  }
}
