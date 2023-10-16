package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.client.UserTokenHandler;
import cz.msebera.android.http.client.cache.HttpClientContext;
import cz.msebera.android.http.conn.ManagedHttpClientConnection;
import cz.msebera.android.http.execchain.HttpContext;
import java.security.Principal;
import javax.net.ssl.SSLSession;

public class DefaultUserTokenHandler implements UserTokenHandler {
    public static final DefaultUserTokenHandler INSTANCE = new DefaultUserTokenHandler();

    private static Principal getAuthPrincipal(AuthState authState) {
        Credentials $r2;
        AuthScheme $r1 = authState.getAuthScheme();
        if ($r1 == null || !$r1.isComplete() || !$r1.isConnectionBased() || ($r2 = authState.getCredentials()) == null) {
            return null;
        }
        return $r2.getUserPrincipal();
    }

    public Object getUserToken(HttpContext httpContext) {
        Principal $r5;
        SSLSession $r8;
        HttpClientContext $r2 = HttpClientContext.adapt(httpContext);
        AuthState $r3 = $r2.getTargetAuthState();
        if ($r3 != null) {
            Principal $r4 = getAuthPrincipal($r3);
            $r5 = $r4;
            if ($r4 == null) {
                $r5 = getAuthPrincipal($r2.getProxyAuthState());
            }
        } else {
            $r5 = null;
        }
        if ($r5 == null) {
            HttpConnection $r6 = $r2.getConnection();
            if ($r6.isOpen() && ($r6 instanceof ManagedHttpClientConnection) && ($r8 = ((ManagedHttpClientConnection) $r6).getSSLSession()) != null) {
                return $r8.getLocalPrincipal();
            }
        }
        return $r5;
    }
}
