package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpInetConnection;
import javax.net.ssl.SSLSession;

public interface ManagedHttpClientConnection extends HttpClientConnection, HttpInetConnection {
    SSLSession getSSLSession();
}
