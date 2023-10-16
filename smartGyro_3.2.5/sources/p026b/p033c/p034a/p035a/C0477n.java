package p026b.p033c.p034a.p035a;

import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import p036c.p037a.p038a.p039a.p050e.p055e.C0620i;

/* renamed from: b.c.a.a.n */
public class C0477n extends C0620i {

    /* renamed from: i */
    final SSLContext f1730i = SSLContext.getInstance("TLS");

    public C0477n(KeyStore keyStore) {
        super(keyStore);
        C0476m mVar = new C0476m(this);
        this.f1730i.init((KeyManager[]) null, new TrustManager[]{mVar}, (SecureRandom) null);
    }

    /* renamed from: b */
    public static C0620i m2085b() {
        try {
            C0477n nVar = new C0477n(m2086c());
            nVar.mo2701a(C0620i.f1926a);
            return nVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return C0620i.m2365a();
        }
    }

    /* renamed from: c */
    public static KeyStore m2086c() {
        KeyStore keyStore = null;
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                instance.load((InputStream) null, (char[]) null);
                return instance;
            } catch (Throwable th) {
                KeyStore keyStore2 = instance;
                th = th;
                keyStore = keyStore2;
                th.printStackTrace();
                return keyStore;
            }
        } catch (Throwable th2) {
            th = th2;
            th.printStackTrace();
            return keyStore;
        }
    }

    public Socket createSocket() {
        return this.f1730i.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.f1730i.getSocketFactory().createSocket(socket, str, i, z);
    }
}
