package cz.msebera.android.http.client.cache;

import c.a.a.a.b.e.c;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.auth.AuthProtocolState;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpCoreContext;
import cz.msebera.android.http.mime.Args;

public class RequestAuthCache
  implements HttpRequestInterceptor
{
  public HttpClientAndroidLog log = new HttpClientAndroidLog(c.class);
  
  public RequestAuthCache() {}
  
  private void doPreemptiveAuth(HttpHost paramHttpHost, AuthScheme paramAuthScheme, AuthState paramAuthState, CredentialsProvider paramCredentialsProvider)
  {
    String str = paramAuthScheme.getSchemeName();
    if (log.isDebugEnabled())
    {
      HttpClientAndroidLog localHttpClientAndroidLog = log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Re-using cached '");
      localStringBuilder.append(str);
      localStringBuilder.append("' auth scheme for ");
      localStringBuilder.append(paramHttpHost);
      localHttpClientAndroidLog.debug(localStringBuilder.toString());
    }
    paramCredentialsProvider = paramCredentialsProvider.getCredentials(new AuthScope(paramHttpHost, AuthScope.ANY_REALM, str));
    if (paramCredentialsProvider != null)
    {
      if ("BASIC".equalsIgnoreCase(paramAuthScheme.getSchemeName())) {
        paramHttpHost = AuthProtocolState.CHALLENGED;
      } else {
        paramHttpHost = AuthProtocolState.SUCCESS;
      }
      paramAuthState.setState(paramHttpHost);
      paramAuthState.update(paramAuthScheme, paramCredentialsProvider);
      return;
    }
    log.debug("No credentials for preemptive authentication");
  }
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpContext, "HTTP context");
    HttpClientContext localHttpClientContext = HttpClientContext.adapt(paramHttpContext);
    Object localObject = localHttpClientContext.getAuthCache();
    if (localObject == null)
    {
      paramHttpRequest = log;
      paramHttpContext = "Auth cache not set in the context";
    }
    CredentialsProvider localCredentialsProvider;
    RouteInfo localRouteInfo;
    for (;;)
    {
      paramHttpRequest.debug(paramHttpContext);
      return;
      localCredentialsProvider = localHttpClientContext.getCredentialsProvider();
      if (localCredentialsProvider == null)
      {
        paramHttpRequest = log;
        paramHttpContext = "Credentials provider not set in the context";
      }
      else
      {
        localRouteInfo = localHttpClientContext.getHttpRoute();
        if (localRouteInfo == null)
        {
          paramHttpRequest = log;
          paramHttpContext = "Route info not set in the context";
        }
        else
        {
          paramHttpContext = localHttpClientContext.getTargetHost();
          paramHttpRequest = paramHttpContext;
          if (paramHttpContext != null) {
            break;
          }
          paramHttpRequest = log;
          paramHttpContext = "Target host not set in the context";
        }
      }
    }
    if (paramHttpContext.getPort() < 0) {
      paramHttpRequest = new HttpHost(paramHttpContext.getHostName(), localRouteInfo.getTargetHost().getPort(), paramHttpContext.getSchemeName());
    }
    paramHttpContext = localHttpClientContext.getTargetAuthState();
    if ((paramHttpContext != null) && (paramHttpContext.getState() == AuthProtocolState.UNCHALLENGED))
    {
      AuthScheme localAuthScheme = ((AuthCache)localObject).get(paramHttpRequest);
      if (localAuthScheme != null) {
        doPreemptiveAuth(paramHttpRequest, localAuthScheme, paramHttpContext, localCredentialsProvider);
      }
    }
    paramHttpRequest = localRouteInfo.getProxyHost();
    paramHttpContext = localHttpClientContext.getProxyAuthState();
    if ((paramHttpRequest != null) && (paramHttpContext != null) && (paramHttpContext.getState() == AuthProtocolState.UNCHALLENGED))
    {
      localObject = ((AuthCache)localObject).get(paramHttpRequest);
      if (localObject != null) {
        doPreemptiveAuth(paramHttpRequest, (AuthScheme)localObject, paramHttpContext, localCredentialsProvider);
      }
    }
  }
}
