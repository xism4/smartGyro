package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.util.HttpParams;
import java.net.Socket;

@Deprecated
public interface OperatedClientConnection extends HttpClientConnection, HttpInetConnection {
    Socket getSocket();

    boolean isSecure();

    void openCompleted(boolean z, HttpParams httpParams);

    void opening(Socket socket, HttpHost httpHost);

    void update(Socket socket, HttpHost httpHost, boolean z, HttpParams httpParams);
}
