package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.conn.ConnectTimeoutException;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Deprecated
public class PlainSocketFactory implements SocketFactory, SchemeSocketFactory {
    private final HostNameResolver nameResolver = null;

    public static PlainSocketFactory getSocketFactory() {
        return new PlainSocketFactory();
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int $i1, HttpParams httpParams) {
        InetSocketAddress $r2;
        if (inetAddress != null || $i1 > 0) {
            if ($i1 <= 0) {
                $i1 = 0;
            }
            $r2 = new InetSocketAddress(inetAddress, $i1);
        } else {
            $r2 = null;
        }
        HostNameResolver $r6 = this.nameResolver;
        return connectSocket(socket, new InetSocketAddress($r6 != null ? $r6.resolve(str) : InetAddress.getByName(str), i), $r2, httpParams);
    }

    public Socket connectSocket(Socket $r2, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        Args.notNull(inetSocketAddress, "Remote address");
        Args.notNull(httpParams, "HTTP parameters");
        if ($r2 == null) {
            $r2 = createSocket();
        }
        if (inetSocketAddress2 != null) {
            $r2.setReuseAddress(HttpConnectionParams.getSoReuseaddr(httpParams));
            $r2.bind(inetSocketAddress2);
        }
        int $i0 = HttpConnectionParams.getConnectionTimeout(httpParams);
        try {
            $r2.setSoTimeout(HttpConnectionParams.getSoTimeout(httpParams));
            $r2.connect(inetSocketAddress, $i0);
            return $r2;
        } catch (SocketTimeoutException e) {
            throw new ConnectTimeoutException("Connect to " + inetSocketAddress + " timed out");
        }
    }

    public Socket createSocket() {
        return new Socket();
    }

    public Socket createSocket(HttpParams httpParams) {
        return new Socket();
    }

    public final boolean isSecure(Socket socket) {
        return false;
    }
}
