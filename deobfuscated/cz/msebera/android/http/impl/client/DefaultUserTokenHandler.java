package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.client.UserTokenHandler;
import cz.msebera.android.http.client.cache.HttpClientContext;
import cz.msebera.android.http.conn.ManagedHttpClientConnection;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpCoreContext;
import java.security.Principal;
import javax.net.ssl.SSLSession;

public class DefaultUserTokenHandler
  implements UserTokenHandler
{
  public static final DefaultUserTokenHandler INSTANCE = new DefaultUserTokenHandler();
  
  public DefaultUserTokenHandler() {}
  
  private static Principal getAuthPrincipal(AuthState paramAuthState)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    if ((localAuthScheme != null) && (localAuthScheme.isComplete()) && (localAuthScheme.isConnectionBased()))
    {
      paramAuthState = paramAuthState.getCredentials();
      if (paramAuthState != null) {
        return paramAuthState.getUserPrincipal();
      }
    }
    return null;
  }
  
  public Object getUserToken(HttpContext paramHttpContext)
  {
    HttpClientContext localHttpClientContext = HttpClientContext.adapt(paramHttpContext);
    paramHttpContext = localHttpClientContext.getTargetAuthState();
    Object localObject;
    if (paramHttpContext != null)
    {
      localObject = getAuthPrincipal(paramHttpContext);
      paramHttpContext = (HttpContext)localObject;
      if (localObject == null) {
        paramHttpContext = getAuthPrincipal(localHttpClientContext.getProxyAuthState());
      }
    }
    else
    {
      paramHttpContext = null;
    }
    if (paramHttpContext == null)
    {
      localObject = localHttpClientContext.getConnection();
      if ((((HttpConnection)localObject).isOpen()) && ((localObject instanceof ManagedHttpClientConnection)))
      {
        localObject = ((ManagedHttpClientConnection)localObject).getSSLSession();
        if (localObject != null) {
          return ((SSLSession)localObject).getLocalPrincipal();
        }
      }
    }
    return paramHttpContext;
  }
}
