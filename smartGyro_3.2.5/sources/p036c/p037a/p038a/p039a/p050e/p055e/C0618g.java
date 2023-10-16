package p036c.p037a.p038a.p039a.p050e.p055e;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

@Deprecated
/* renamed from: c.a.a.a.e.e.g */
public class C0618g {
    /* renamed from: a */
    public static SSLContext m2363a() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
            return instance;
        } catch (NoSuchAlgorithmException e) {
            throw new C0619h(e.getMessage(), e);
        } catch (KeyManagementException e2) {
            throw new C0619h(e2.getMessage(), e2);
        }
    }

    /* renamed from: b */
    public static C0616f m2364b() {
        return new C0616f();
    }
}
