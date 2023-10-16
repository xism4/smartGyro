package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthProtocolState;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.auth.MalformedChallengeException;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.execchain.HttpContext;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

public class HttpAuthenticator
{
  public HttpClientAndroidLog log;
  
  public HttpAuthenticator(HttpClientAndroidLog paramHttpClientAndroidLog)
  {
    if (paramHttpClientAndroidLog == null) {
      paramHttpClientAndroidLog = new HttpClientAndroidLog(getClass());
    }
    log = paramHttpClientAndroidLog;
  }
  
  public boolean handleAuthChallenge(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    Object localObject1 = log;
    try
    {
      boolean bool = ((HttpClientAndroidLog)localObject1).isDebugEnabled();
      if (bool)
      {
        localObject1 = log;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramHttpHost.toHostString());
        ((StringBuilder)localObject2).append(" requested authentication");
        ((HttpClientAndroidLog)localObject1).debug(((StringBuilder)localObject2).toString());
      }
      Object localObject2 = paramAuthenticationStrategy.getChallenges(paramHttpHost, paramHttpResponse, paramHttpContext);
      bool = ((Map)localObject2).isEmpty();
      if (bool)
      {
        paramHttpHost = log;
        paramHttpHost.debug("Response contains no authentication challenges");
        return false;
      }
      localObject1 = paramAuthState.getAuthScheme();
      Object localObject3 = 1.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState;
      int i = paramAuthState.getState().ordinal();
      i = localObject3[i];
      if ((i != 1) && (i != 2)) {
        if (i != 3)
        {
          if (i != 4)
          {
            if (i == 5) {
              break label215;
            }
            break label350;
          }
          return false;
        }
      }
      label215:
      do
      {
        paramAuthState.reset();
        break;
        if (localObject1 == null)
        {
          paramHttpResponse = log;
          paramHttpResponse.debug("Auth scheme is null");
          paramAuthenticationStrategy.authFailed(paramHttpHost, null, paramHttpContext);
          paramAuthState.reset();
          paramHttpHost = AuthProtocolState.FAILURE;
          paramAuthState.setState(paramHttpHost);
          return false;
        }
        if (localObject1 == null) {
          break;
        }
        localObject3 = ((AuthScheme)localObject1).getSchemeName();
        Locale localLocale = Locale.ROOT;
        localObject3 = ((Map)localObject2).get(((String)localObject3).toLowerCase(localLocale));
        localObject3 = (Header)localObject3;
      } while (localObject3 == null);
      paramHttpResponse = log;
      paramHttpResponse.debug("Authorization challenge processed");
      ((AuthScheme)localObject1).processChallenge((Header)localObject3);
      bool = ((AuthScheme)localObject1).isComplete();
      if (bool)
      {
        paramHttpResponse = log;
        paramHttpResponse.debug("Authentication failed");
        paramAuthenticationStrategy.authFailed(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
        paramAuthState.reset();
        paramHttpHost = AuthProtocolState.FAILURE;
        paramAuthState.setState(paramHttpHost);
        return false;
      }
      paramHttpHost = AuthProtocolState.HANDSHAKE;
      paramAuthState.setState(paramHttpHost);
      return true;
      label350:
      paramHttpHost = paramAuthenticationStrategy.select((Map)localObject2, paramHttpHost, paramHttpResponse, paramHttpContext);
      if (paramHttpHost != null)
      {
        bool = paramHttpHost.isEmpty();
        if (!bool)
        {
          paramHttpResponse = log;
          bool = paramHttpResponse.isDebugEnabled();
          if (bool)
          {
            paramHttpResponse = log;
            paramAuthenticationStrategy = new StringBuilder();
            paramAuthenticationStrategy.append("Selected authentication options: ");
            paramAuthenticationStrategy.append(paramHttpHost);
            paramHttpResponse.debug(paramAuthenticationStrategy.toString());
          }
          paramHttpResponse = AuthProtocolState.CHALLENGED;
          paramAuthState.setState(paramHttpResponse);
          paramAuthState.update(paramHttpHost);
          return true;
        }
      }
      else
      {
        return false;
      }
    }
    catch (MalformedChallengeException paramHttpHost)
    {
      if (log.isWarnEnabled())
      {
        paramHttpResponse = log;
        paramAuthenticationStrategy = new StringBuilder();
        paramAuthenticationStrategy.append("Malformed challenge: ");
        paramAuthenticationStrategy.append(paramHttpHost.getMessage());
        paramHttpResponse.warn(paramAuthenticationStrategy.toString());
      }
      paramAuthState.reset();
    }
    return false;
  }
  
  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    if (paramAuthenticationStrategy.isAuthenticationRequested(paramHttpHost, paramHttpResponse, paramHttpContext))
    {
      log.debug("Authentication required");
      if (paramAuthState.getState() == AuthProtocolState.SUCCESS)
      {
        paramAuthenticationStrategy.authFailed(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
        return true;
      }
    }
    else
    {
      int i = 1.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[paramAuthState.getState().ordinal()];
      if ((i != 1) && (i != 2))
      {
        if (i != 3) {
          paramAuthState.setState(AuthProtocolState.UNCHALLENGED);
        }
      }
      else
      {
        log.debug("Authentication succeeded");
        paramAuthState.setState(AuthProtocolState.SUCCESS);
        paramAuthenticationStrategy.authSucceeded(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
      }
      return false;
    }
    return true;
  }
}
