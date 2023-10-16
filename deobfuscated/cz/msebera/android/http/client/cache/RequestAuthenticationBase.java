package cz.msebera.android.http.client.cache;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.auth.AuthOption;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.auth.AuthenticationException;
import cz.msebera.android.http.auth.ContextAwareAuthScheme;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Asserts;
import java.util.Queue;

@Deprecated
abstract class RequestAuthenticationBase
  implements HttpRequestInterceptor
{
  final HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
  
  public RequestAuthenticationBase() {}
  
  private Header authenticate(AuthScheme paramAuthScheme, Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Asserts.notNull(paramAuthScheme, "Auth scheme");
    if ((paramAuthScheme instanceof ContextAwareAuthScheme)) {
      return ((ContextAwareAuthScheme)paramAuthScheme).authenticate(paramCredentials, paramHttpRequest, paramHttpContext);
    }
    return paramAuthScheme.authenticate(paramCredentials, paramHttpRequest);
  }
  
  private void ensureAuthScheme(AuthScheme paramAuthScheme)
  {
    Asserts.notNull(paramAuthScheme, "Auth scheme");
  }
  
  void process(AuthState paramAuthState, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    Object localObject = paramAuthState.getCredentials();
    int i = 1.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[paramAuthState.getState().ordinal()];
    if (i != 1)
    {
      if (i != 2)
      {
        if (i == 3)
        {
          Queue localQueue = paramAuthState.getAuthOptions();
          if (localQueue != null)
          {
            while (!localQueue.isEmpty())
            {
              localObject = (AuthOption)localQueue.remove();
              localAuthScheme = ((AuthOption)localObject).getAuthScheme();
              localObject = ((AuthOption)localObject).getCredentials();
              paramAuthState.update(localAuthScheme, (Credentials)localObject);
              HttpClientAndroidLog localHttpClientAndroidLog;
              StringBuilder localStringBuilder;
              if (log.isDebugEnabled())
              {
                localHttpClientAndroidLog = log;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("Generating response to an authentication challenge using ");
                localStringBuilder.append(localAuthScheme.getSchemeName());
                localStringBuilder.append(" scheme");
                localHttpClientAndroidLog.debug(localStringBuilder.toString());
              }
              try
              {
                paramHttpRequest.addHeader(authenticate(localAuthScheme, (Credentials)localObject, paramHttpRequest, paramHttpContext));
                return;
              }
              catch (AuthenticationException localAuthenticationException) {}
              if (log.isWarnEnabled())
              {
                localHttpClientAndroidLog = log;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append(localAuthScheme);
                localStringBuilder.append(" authentication error: ");
                localStringBuilder.append(localAuthenticationException.getMessage());
                localHttpClientAndroidLog.warn(localStringBuilder.toString());
              }
            }
            return;
          }
          ensureAuthScheme(localAuthScheme);
        }
      }
      else
      {
        ensureAuthScheme(localAuthScheme);
        if (localAuthScheme.isConnectionBased()) {
          return;
        }
      }
      if (localAuthScheme != null) {
        try
        {
          paramHttpRequest.addHeader(authenticate(localAuthScheme, localAuthenticationException, paramHttpRequest, paramHttpContext));
          return;
        }
        catch (AuthenticationException paramAuthState)
        {
          if (log.isErrorEnabled())
          {
            paramHttpRequest = log;
            paramHttpContext = new StringBuilder();
            paramHttpContext.append(localAuthScheme);
            paramHttpContext.append(" authentication error: ");
            paramHttpContext.append(paramAuthState.getMessage());
            paramHttpRequest.error(paramHttpContext.toString());
          }
        }
      }
    }
  }
}
