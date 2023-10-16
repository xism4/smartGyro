package cz.msebera.android.http.client.cache;

import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;

@Deprecated
public class RequestTargetAuthentication
  extends RequestAuthenticationBase
{
  public RequestTargetAuthentication() {}
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpContext, "HTTP context");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
      return;
    }
    if (paramHttpRequest.containsHeader("Authorization")) {
      return;
    }
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    if (localAuthState == null)
    {
      log.debug("Target auth state not set in the context");
      return;
    }
    if (log.isDebugEnabled())
    {
      HttpClientAndroidLog localHttpClientAndroidLog = log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Target auth state: ");
      localStringBuilder.append(localAuthState.getState());
      localHttpClientAndroidLog.debug(localStringBuilder.toString());
    }
    process(localAuthState, paramHttpRequest, paramHttpContext);
  }
}
