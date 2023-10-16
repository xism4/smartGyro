package lombok.eclipse.handlers.http;

import cz.msebera.android.http.conn.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class MySSLSocketFactory extends SSLSocketFactory {
    final SSLContext sslContext = SSLContext.getInstance("TLS");

    public MySSLSocketFactory(KeyStore keyStore) {
        super(keyStore);
        MemorizingTrustManager r5 = new MemorizingTrustManager(this);
        this.sslContext.init((KeyManager[]) null, new TrustManager[]{r5}, (SecureRandom) null);
    }

    public static SSLSocketFactory getFixedSocketFactory() {
        try {
            MySSLSocketFactory $r0 = new MySSLSocketFactory(getKeystore());
            $r0.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return $r0;
        } catch (Throwable $r3) {
            $r3.printStackTrace();
            return SSLSocketFactory.getSocketFactory();
        }
    }

    public static KeyStore getKeystore() {
        KeyStore $r0 = null;
        try {
            $r0 = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                $r0.load((InputStream) null, (char[]) null);
                return $r0;
            } catch (Throwable $r2) {
                $r3 = $r2;
                $r3.printStackTrace();
                return $r0;
            }
        } catch (Throwable th) {
            $r3 = th;
            $r3.printStackTrace();
            return $r0;
        }
    }

    public Socket createSocket() {
        return this.sslContext.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.sslContext.getSocketFactory().createSocket(socket, str, i, z);
    }
}
