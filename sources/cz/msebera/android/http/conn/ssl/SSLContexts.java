package cz.msebera.android.http.conn.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

@Deprecated
public class SSLContexts {
    public static SSLContext createDefault() {
        try {
            SSLContext $r0 = SSLContext.getInstance("TLS");
            $r0.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
            return $r0;
        } catch (NoSuchAlgorithmException $r4) {
            throw new SSLInitializationException($r4.getMessage(), $r4);
        } catch (KeyManagementException $r1) {
            throw new SSLInitializationException($r1.getMessage(), $r1);
        }
    }

    public static SSLContextBuilder custom() {
        return new SSLContextBuilder();
    }
}
