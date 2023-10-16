package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.client.CircularRedirectException;
import cz.msebera.android.http.client.ssl.URIUtils;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.impl.client.DefaultRedirectHandler;
import cz.msebera.android.http.impl.client.RedirectLocations;
import cz.msebera.android.http.util.HttpParams;
import java.net.URI;
import java.net.URISyntaxException;

class MyRedirectHandler
  extends DefaultRedirectHandler
{
  private final boolean enableRedirects;
  
  public MyRedirectHandler(boolean paramBoolean)
  {
    enableRedirects = paramBoolean;
  }
  
  public URI getLocationURI(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse != null)
    {
      Object localObject1 = paramHttpResponse.getFirstHeader("location");
      if (localObject1 != null)
      {
        Object localObject2 = ((Header)localObject1).getValue().replaceAll(" ", "%20");
        try
        {
          localObject1 = new URI((String)localObject2);
          localObject2 = paramHttpResponse.getParams();
          paramHttpResponse = (HttpResponse)localObject1;
          if (!((URI)localObject1).isAbsolute()) {
            if (!((HttpParams)localObject2).isParameterTrue("http.protocol.reject-relative-redirect"))
            {
              paramHttpResponse = (HttpHost)paramHttpContext.getAttribute("http.target_host");
              if (paramHttpResponse != null)
              {
                HttpRequest localHttpRequest = (HttpRequest)paramHttpContext.getAttribute("http.request");
                try
                {
                  paramHttpResponse = URIUtils.resolve(URIUtils.rewriteURI(new URI(localHttpRequest.getRequestLine().getUri()), paramHttpResponse, true), (URI)localObject1);
                }
                catch (URISyntaxException paramHttpResponse)
                {
                  throw new ProtocolException(paramHttpResponse.getMessage(), paramHttpResponse);
                }
              }
              else
              {
                throw new IllegalStateException("Target host not available in the HTTP context");
              }
            }
            else
            {
              paramHttpResponse = new StringBuilder();
              paramHttpResponse.append("Relative redirect location '");
              paramHttpResponse.append(localObject1);
              paramHttpResponse.append("' not allowed");
              throw new ProtocolException(paramHttpResponse.toString());
            }
          }
          if (!((HttpParams)localObject2).isParameterFalse("http.protocol.allow-circular-redirects")) {
            return paramHttpResponse;
          }
          localObject2 = (RedirectLocations)paramHttpContext.getAttribute("http.protocol.redirect-locations");
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            localObject1 = new RedirectLocations();
            paramHttpContext.setAttribute("http.protocol.redirect-locations", localObject1);
          }
          if (paramHttpResponse.getFragment() != null) {
            try
            {
              paramHttpContext = URIUtils.rewriteURI(paramHttpResponse, new HttpHost(paramHttpResponse.getHost(), paramHttpResponse.getPort(), paramHttpResponse.getScheme()), true);
            }
            catch (URISyntaxException paramHttpResponse)
            {
              throw new ProtocolException(paramHttpResponse.getMessage(), paramHttpResponse);
            }
          } else {
            paramHttpContext = paramHttpResponse;
          }
          if (!((RedirectLocations)localObject1).contains(paramHttpContext))
          {
            ((RedirectLocations)localObject1).add(paramHttpContext);
            return paramHttpResponse;
          }
          paramHttpResponse = new StringBuilder();
          paramHttpResponse.append("Circular redirect to '");
          paramHttpResponse.append(paramHttpContext);
          paramHttpResponse.append("'");
          throw new CircularRedirectException(paramHttpResponse.toString());
        }
        catch (URISyntaxException paramHttpResponse)
        {
          paramHttpContext = new StringBuilder();
          paramHttpContext.append("Invalid redirect URI: ");
          paramHttpContext.append((String)localObject2);
          throw new ProtocolException(paramHttpContext.toString(), paramHttpResponse);
        }
      }
      paramHttpContext = new StringBuilder();
      paramHttpContext.append("Received redirect response ");
      paramHttpContext.append(paramHttpResponse.getStatusLine());
      paramHttpContext.append(" but no location header");
      throw new ProtocolException(paramHttpContext.toString());
    }
    throw new IllegalArgumentException("HTTP response may not be null");
    return paramHttpResponse;
  }
  
  public boolean isRedirectRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (!enableRedirects) {
      return false;
    }
    if (paramHttpResponse != null)
    {
      int i = paramHttpResponse.getStatusLine().getStatusCode();
      if (i != 307) {
        switch (i)
        {
        default: 
          return false;
        }
      }
      return true;
    }
    throw new IllegalArgumentException("HTTP response may not be null");
  }
}
