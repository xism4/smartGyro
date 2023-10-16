package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Deprecated
public class HttpInetSocketAddress extends InetSocketAddress {
    private final HttpHost httphost;

    public HttpInetSocketAddress(HttpHost httpHost, InetAddress inetAddress, int i) {
        super(inetAddress, i);
        Args.notNull(httpHost, "HTTP host");
        this.httphost = httpHost;
    }

    public HttpHost getHttpHost() {
        return this.httphost;
    }

    public String toString() {
        return this.httphost.getHostName() + ":" + getPort();
    }
}
