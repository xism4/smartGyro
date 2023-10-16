package cz.msebera.android.http.client.cache;

import c.a.a.a.b.e.f;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;

public class RequestAddCookies
  implements HttpRequestInterceptor
{
  public HttpClientAndroidLog log = new HttpClientAndroidLog(f.class);
  
  public RequestAddCookies() {}
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
    {
      paramHttpRequest.setHeader("Proxy-Connection", "Keep-Alive");
      return;
    }
    paramHttpContext = HttpClientContext.adapt(paramHttpContext).getHttpRoute();
    if (paramHttpContext == null)
    {
      log.debug("Connection route not set in the context");
      return;
    }
    if (((paramHttpContext.getHopCount() == 1) || (paramHttpContext.isTunnelled())) && (!paramHttpRequest.containsHeader("Connection"))) {
      paramHttpRequest.addHeader("Connection", "Keep-Alive");
    }
    if ((paramHttpContext.getHopCount() == 2) && (!paramHttpContext.isTunnelled()) && (!paramHttpRequest.containsHeader("Proxy-Connection"))) {
      paramHttpRequest.addHeader("Proxy-Connection", "Keep-Alive");
    }
  }
}
