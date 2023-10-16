package p036c.p037a.p038a.p039a.p050e.p055e;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

@Deprecated
/* renamed from: c.a.a.a.e.e.f */
public class C0616f {

    /* renamed from: a */
    private String f1920a;

    /* renamed from: b */
    private Set<KeyManager> f1921b = new LinkedHashSet();

    /* renamed from: c */
    private Set<TrustManager> f1922c = new LinkedHashSet();

    /* renamed from: d */
    private SecureRandom f1923d;

    /* renamed from: c.a.a.a.e.e.f$a */
    static class C0617a implements X509TrustManager {

        /* renamed from: a */
        private final X509TrustManager f1924a;

        /* renamed from: b */
        private final C0622k f1925b;

        C0617a(X509TrustManager x509TrustManager, C0622k kVar) {
            this.f1924a = x509TrustManager;
            this.f1925b = kVar;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.f1924a.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            if (!this.f1925b.mo3280a(x509CertificateArr, str)) {
                this.f1924a.checkServerTrusted(x509CertificateArr, str);
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.f1924a.getAcceptedIssuers();
        }
    }

    /* renamed from: a */
    public C0616f mo2692a(KeyStore keyStore) {
        mo2693a(keyStore, (C0622k) null);
        return this;
    }

    /* renamed from: a */
    public C0616f mo2693a(KeyStore keyStore, C0622k kVar) {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers != null) {
            if (kVar != null) {
                for (int i = 0; i < trustManagers.length; i++) {
                    TrustManager trustManager = trustManagers[i];
                    if (trustManager instanceof X509TrustManager) {
                        trustManagers[i] = new C0617a((X509TrustManager) trustManager, kVar);
                    }
                }
            }
            for (TrustManager add : trustManagers) {
                this.f1922c.add(add);
            }
        }
        return this;
    }

    /* renamed from: a */
    public SSLContext mo2694a() {
        KeyManager[] keyManagerArr;
        String str = this.f1920a;
        if (str == null) {
            str = "TLS";
        }
        SSLContext instance = SSLContext.getInstance(str);
        TrustManager[] trustManagerArr = null;
        if (!this.f1921b.isEmpty()) {
            Set<KeyManager> set = this.f1921b;
            keyManagerArr = (KeyManager[]) set.toArray(new KeyManager[set.size()]);
        } else {
            keyManagerArr = null;
        }
        if (!this.f1922c.isEmpty()) {
            Set<TrustManager> set2 = this.f1922c;
            trustManagerArr = (TrustManager[]) set2.toArray(new TrustManager[set2.size()]);
        }
        instance.init(keyManagerArr, trustManagerArr, this.f1923d);
        return instance;
    }
}
