package cz.msebera.android.http.conn.ssl;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

@Deprecated
public class SSLContextBuilder {
    private Set<KeyManager> keymanagers = new LinkedHashSet();
    private String protocol;
    private SecureRandom secureRandom;
    private Set<TrustManager> trustmanagers = new LinkedHashSet();

    class TrustManagerDelegate implements X509TrustManager {
        private final X509TrustManager trustManager;
        private final TrustStrategy trustStrategy;

        TrustManagerDelegate(X509TrustManager x509TrustManager, TrustStrategy trustStrategy2) {
            this.trustManager = x509TrustManager;
            this.trustStrategy = trustStrategy2;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.trustManager.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            if (!this.trustStrategy.isTrusted(x509CertificateArr, str)) {
                this.trustManager.checkServerTrusted(x509CertificateArr, str);
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.trustManager.getAcceptedIssuers();
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r7v1, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.net.ssl.SSLContext build() {
        /*
            r11 = this;
            java.lang.String r0 = r11.protocol
            if (r0 == 0) goto L_0x0005
            goto L_0x0007
        L_0x0005:
            java.lang.String r0 = "TLS"
        L_0x0007:
            javax.net.ssl.SSLContext r1 = javax.net.ssl.SSLContext.getInstance(r0)
            java.util.Set<javax.net.ssl.KeyManager> r2 = r11.keymanagers
            boolean r3 = r2.isEmpty()
            r4 = 0
            if (r3 != 0) goto L_0x0025
            java.util.Set<javax.net.ssl.KeyManager> r2 = r11.keymanagers
            int r5 = r2.size()
            javax.net.ssl.KeyManager[] r6 = new javax.net.ssl.KeyManager[r5]
            java.lang.Object[] r7 = r2.toArray(r6)
            r8 = r7
            javax.net.ssl.KeyManager[] r8 = (javax.net.ssl.KeyManager[]) r8
            r6 = r8
            goto L_0x0026
        L_0x0025:
            r6 = 0
        L_0x0026:
            java.util.Set<javax.net.ssl.TrustManager> r2 = r11.trustmanagers
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x003e
            java.util.Set<javax.net.ssl.TrustManager> r2 = r11.trustmanagers
            int r5 = r2.size()
            javax.net.ssl.TrustManager[] r4 = new javax.net.ssl.TrustManager[r5]
            java.lang.Object[] r7 = r2.toArray(r4)
            r9 = r7
            javax.net.ssl.TrustManager[] r9 = (javax.net.ssl.TrustManager[]) r9
            r4 = r9
        L_0x003e:
            java.security.SecureRandom r10 = r11.secureRandom
            r1.init(r6, r4, r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.conn.ssl.SSLContextBuilder.build():javax.net.ssl.SSLContext");
    }

    public SSLContextBuilder loadTrustMaterial(KeyStore keyStore) {
        loadTrustMaterial(keyStore, (TrustStrategy) null);
        return this;
    }

    public SSLContextBuilder loadTrustMaterial(KeyStore keyStore, TrustStrategy trustStrategy) {
        TrustManagerFactory $r4 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        $r4.init(keyStore);
        Object[] $r5 = $r4.getTrustManagers();
        if ($r5 != null) {
            if (trustStrategy != null) {
                for (int $i1 = 0; $i1 < $r5.length; $i1++) {
                    Object $r6 = $r5[$i1];
                    if ($r6 instanceof X509TrustManager) {
                        $r5[$i1] = new TrustManagerDelegate((X509TrustManager) $r6, trustStrategy);
                    }
                }
            }
            for (Object $r62 : $r5) {
                this.trustmanagers.add($r62);
            }
        }
        return this;
    }
}
