package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
class SchemeLayeredSocketFactoryAdaptor2 implements SchemeLayeredSocketFactory {
    private final LayeredSchemeSocketFactory factory;

    SchemeLayeredSocketFactoryAdaptor2(LayeredSchemeSocketFactory layeredSchemeSocketFactory) {
        this.factory = layeredSchemeSocketFactory;
    }

    public Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        return this.factory.connectSocket(socket, inetSocketAddress, inetSocketAddress2, httpParams);
    }

    public Socket createLayeredSocket(Socket socket, String str, int i, HttpParams httpParams) {
        return this.factory.createLayeredSocket(socket, str, i, true);
    }

    public Socket createSocket(HttpParams httpParams) {
        return this.factory.createSocket(httpParams);
    }

    public boolean isSecure(Socket socket) {
        return this.factory.isSecure(socket);
    }
}
