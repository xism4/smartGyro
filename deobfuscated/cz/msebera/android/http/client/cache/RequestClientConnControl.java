package cz.msebera.android.http.client.cache;

import c.a.a.a.b.e.b;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.CookieSpecProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpCoreContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import cz.msebera.android.http.protocol.Lookup;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RequestClientConnControl
  implements HttpRequestInterceptor
{
  public HttpClientAndroidLog log = new HttpClientAndroidLog(b.class);
  
  public RequestClientConnControl() {}
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpContext, "HTTP context");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
      return;
    }
    Object localObject4 = HttpClientContext.adapt(paramHttpContext);
    CookieStore localCookieStore = ((HttpClientContext)localObject4).getCookieStore();
    if (localCookieStore == null)
    {
      log.debug("Cookie store not specified in HTTP context");
      return;
    }
    Object localObject5 = ((HttpClientContext)localObject4).getCookieSpecRegistry();
    if (localObject5 == null)
    {
      log.debug("CookieSpec registry not specified in HTTP context");
      return;
    }
    Object localObject7 = ((HttpCoreContext)localObject4).getTargetHost();
    if (localObject7 == null)
    {
      log.debug("Target host not set in the context");
      return;
    }
    Object localObject6 = ((HttpClientContext)localObject4).getHttpRoute();
    if (localObject6 == null)
    {
      log.debug("Connection route not set in the context");
      return;
    }
    Object localObject1 = ((HttpClientContext)localObject4).getRequestConfig().getMaxRedirects();
    Object localObject3 = localObject1;
    if (localObject1 == null) {
      localObject3 = "default";
    }
    if (log.isDebugEnabled())
    {
      localObject1 = log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("CookieSpec selected: ");
      ((StringBuilder)localObject2).append((String)localObject3);
      ((HttpClientAndroidLog)localObject1).debug(((StringBuilder)localObject2).toString());
    }
    boolean bool = paramHttpRequest instanceof HttpUriRequest;
    Object localObject2 = null;
    if (bool) {
      localObject1 = ((HttpUriRequest)paramHttpRequest).getURI();
    }
    try
    {
      localObject1 = new URI(paramHttpRequest.getRequestLine().getUri());
    }
    catch (URISyntaxException localURISyntaxException)
    {
      int j;
      int i;
      for (;;) {}
    }
    localObject1 = null;
    if (localObject1 != null) {
      localObject2 = ((URI)localObject1).getPath();
    }
    localObject1 = ((HttpHost)localObject7).getHostName();
    j = ((HttpHost)localObject7).getPort();
    i = j;
    if (j < 0) {
      i = ((RouteInfo)localObject6).getTargetHost().getPort();
    }
    j = 0;
    if (i < 0) {
      i = 0;
    }
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject2 = "/";
    }
    localObject1 = new CookieOrigin((String)localObject1, i, (String)localObject2, ((RouteInfo)localObject6).isSecure());
    localObject2 = (CookieSpecProvider)((Lookup)localObject5).lookup((String)localObject3);
    if (localObject2 == null)
    {
      if (log.isDebugEnabled())
      {
        paramHttpRequest = log;
        paramHttpContext = new StringBuilder();
        paramHttpContext.append("Unsupported cookie policy: ");
        paramHttpContext.append((String)localObject3);
        paramHttpRequest.debug(paramHttpContext.toString());
      }
    }
    else
    {
      localObject2 = ((CookieSpecProvider)localObject2).create((HttpContext)localObject4);
      localObject5 = localCookieStore.getCookies();
      localObject3 = new ArrayList();
      localObject4 = new Date();
      localObject5 = ((List)localObject5).iterator();
      i = j;
      while (((Iterator)localObject5).hasNext())
      {
        localObject6 = (Cookie)((Iterator)localObject5).next();
        StringBuilder localStringBuilder;
        if (!((Cookie)localObject6).isExpired((Date)localObject4))
        {
          if (((CookieSpec)localObject2).match((Cookie)localObject6, (CookieOrigin)localObject1))
          {
            if (log.isDebugEnabled())
            {
              localObject7 = log;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("Cookie ");
              localStringBuilder.append(localObject6);
              localStringBuilder.append(" match ");
              localStringBuilder.append(localObject1);
              ((HttpClientAndroidLog)localObject7).debug(localStringBuilder.toString());
            }
            ((List)localObject3).add(localObject6);
          }
        }
        else
        {
          if (log.isDebugEnabled())
          {
            localObject7 = log;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Cookie ");
            localStringBuilder.append(localObject6);
            localStringBuilder.append(" expired");
            ((HttpClientAndroidLog)localObject7).debug(localStringBuilder.toString());
          }
          i = 1;
        }
      }
      if (i != 0) {
        localCookieStore.clearExpired((Date)localObject4);
      }
      if (!((List)localObject3).isEmpty())
      {
        localObject3 = ((CookieSpec)localObject2).formatCookies((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext()) {
          paramHttpRequest.addHeader((Header)((Iterator)localObject3).next());
        }
      }
      if (((CookieSpec)localObject2).getVersion() > 0)
      {
        localObject3 = ((CookieSpec)localObject2).getVersionHeader();
        if (localObject3 != null) {
          paramHttpRequest.addHeader((Header)localObject3);
        }
      }
      paramHttpContext.setAttribute("http.cookie-spec", localObject2);
      paramHttpContext.setAttribute("http.cookie-origin", localObject1);
      return;
    }
  }
}
