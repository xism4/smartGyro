package cz.msebera.android.http.client.cache;

import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.HttpRoutedConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;

@Deprecated
public class RequestProxyAuthentication
  extends RequestAuthenticationBase
{
  public RequestProxyAuthentication() {}
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpContext, "HTTP context");
    if (paramHttpRequest.containsHeader("Proxy-Authorization")) {
      return;
    }
    Object localObject = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
    if (localObject == null) {
      paramHttpRequest = log;
    }
    for (paramHttpContext = "HTTP connection not set in the context";; paramHttpContext = "Proxy auth state not set in the context")
    {
      paramHttpRequest.debug(paramHttpContext);
      return;
      if (((HttpRoutedConnection)localObject).getRoute().isTunnelled()) {
        return;
      }
      localObject = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
      if (localObject != null) {
        break;
      }
      paramHttpRequest = log;
    }
    if (log.isDebugEnabled())
    {
      HttpClientAndroidLog localHttpClientAndroidLog = log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Proxy auth state: ");
      localStringBuilder.append(((AuthState)localObject).getState());
      localHttpClientAndroidLog.debug(localStringBuilder.toString());
    }
    process((AuthState)localObject, paramHttpRequest, paramHttpContext);
  }
}
