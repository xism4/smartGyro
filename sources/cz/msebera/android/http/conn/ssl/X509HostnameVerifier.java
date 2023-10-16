package cz.msebera.android.http.conn.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;

@Deprecated
public interface X509HostnameVerifier extends HostnameVerifier {
    void verify(String str, SSLSocket sSLSocket);

    void verify(String str, String[] strArr, String[] strArr2);
}
