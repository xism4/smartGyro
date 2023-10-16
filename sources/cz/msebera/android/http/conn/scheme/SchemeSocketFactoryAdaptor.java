package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
class SchemeSocketFactoryAdaptor implements SchemeSocketFactory {
    private final SocketFactory factory;

    SchemeSocketFactoryAdaptor(SocketFactory socketFactory) {
        this.factory = socketFactory;
    }

    public Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        int $i1;
        InetAddress $r7;
        String $r6 = inetSocketAddress.getHostName();
        int $i0 = inetSocketAddress.getPort();
        if (inetSocketAddress2 != null) {
            $r7 = inetSocketAddress2.getAddress();
            $i1 = inetSocketAddress2.getPort();
        } else {
            $r7 = null;
            $i1 = 0;
        }
        return this.factory.connectSocket(socket, $r6, $i0, $r7, $i1, httpParams);
    }

    public Socket createSocket(HttpParams httpParams) {
        return this.factory.createSocket();
    }

    public boolean equals(Object $r1) {
        SocketFactory $r2;
        if ($r1 == null) {
            return false;
        }
        if (this == $r1) {
            return true;
        }
        if ($r1 instanceof SchemeSocketFactoryAdaptor) {
            $r2 = this.factory;
            $r1 = ((SchemeSocketFactoryAdaptor) $r1).factory;
        } else {
            $r2 = this.factory;
        }
        return $r2.equals($r1);
    }

    public int hashCode() {
        return this.factory.hashCode();
    }

    public boolean isSecure(Socket socket) {
        return this.factory.isSecure(socket);
    }
}
