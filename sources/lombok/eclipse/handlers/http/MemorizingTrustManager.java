package lombok.eclipse.handlers.http;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class MemorizingTrustManager implements X509TrustManager {
    final /* synthetic */ MySSLSocketFactory this$0;

    MemorizingTrustManager(MySSLSocketFactory mySSLSocketFactory) {
        this.this$0 = mySSLSocketFactory;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
