package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
public interface SchemeSocketFactory {
    Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams);

    Socket createSocket(HttpParams httpParams);

    boolean isSecure(Socket socket);
}
