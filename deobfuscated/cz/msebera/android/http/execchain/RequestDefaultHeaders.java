package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.Args;
import java.net.InetAddress;

public class RequestDefaultHeaders
  implements HttpRequestInterceptor
{
  public RequestDefaultHeaders() {}
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    HttpCoreContext localHttpCoreContext = HttpCoreContext.adapt(paramHttpContext);
    ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
    if ((paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) && (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))) {
      return;
    }
    if (!paramHttpRequest.containsHeader("Host"))
    {
      Object localObject1 = localHttpCoreContext.getTargetHost();
      paramHttpContext = (HttpContext)localObject1;
      Object localObject2 = paramHttpContext;
      if (localObject1 == null)
      {
        localObject2 = localHttpCoreContext.getConnection();
        localObject1 = paramHttpContext;
        if ((localObject2 instanceof HttpInetConnection))
        {
          localObject1 = (HttpInetConnection)localObject2;
          localObject2 = ((HttpInetConnection)localObject1).getRemoteAddress();
          int i = ((HttpInetConnection)localObject1).getRemotePort();
          localObject1 = paramHttpContext;
          if (localObject2 != null) {
            localObject1 = new HttpHost(((InetAddress)localObject2).getHostName(), i);
          }
        }
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          if (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
            return;
          }
          throw new ProtocolException("Target host missing");
        }
      }
      paramHttpRequest.addHeader("Host", ((HttpHost)localObject2).toHostString());
    }
  }
}
