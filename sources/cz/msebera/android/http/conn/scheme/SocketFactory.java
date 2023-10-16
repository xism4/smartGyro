package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.net.Socket;

@Deprecated
public interface SocketFactory {
    Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams);

    Socket createSocket();

    boolean isSecure(Socket socket);
}
