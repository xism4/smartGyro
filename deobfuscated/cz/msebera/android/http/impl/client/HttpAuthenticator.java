package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.execchain.HttpContext;

@Deprecated
public class HttpAuthenticator
  extends cz.msebera.android.http.impl.auth.HttpAuthenticator
{
  public HttpAuthenticator(HttpClientAndroidLog paramHttpClientAndroidLog)
  {
    super(paramHttpClientAndroidLog);
  }
  
  public boolean authenticate(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    return handleAuthChallenge(paramHttpHost, paramHttpResponse, paramAuthenticationStrategy, paramAuthState, paramHttpContext);
  }
}
