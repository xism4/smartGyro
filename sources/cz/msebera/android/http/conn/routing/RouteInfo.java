package cz.msebera.android.http.conn.routing;

import cz.msebera.android.http.HttpHost;
import java.net.InetAddress;

public interface RouteInfo {

    public enum LayerType {
        PLAIN,
        LAYERED
    }

    public enum TunnelType {
        PLAIN,
        TUNNELLED
    }

    int getHopCount();

    HttpHost getHopTarget(int i);

    InetAddress getLocalAddress();

    HttpHost getProxyHost();

    HttpHost getTargetHost();

    boolean isLayered();

    boolean isSecure();

    boolean isTunnelled();
}
