package cz.msebera.android.http.conn.routing;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.mime.LangUtils;
import java.net.InetAddress;

public final class RouteTracker implements RouteInfo, Cloneable {
    private boolean connected;
    private RouteInfo.LayerType layered;
    private final InetAddress localAddress;
    private HttpHost[] proxyChain;
    private boolean secure;
    private final HttpHost targetHost;
    private RouteInfo.TunnelType tunnelled;

    public RouteTracker(HttpHost httpHost, InetAddress inetAddress) {
        Args.notNull(httpHost, "Target host");
        this.targetHost = httpHost;
        this.localAddress = inetAddress;
        this.tunnelled = RouteInfo.TunnelType.PLAIN;
        this.layered = RouteInfo.LayerType.PLAIN;
    }

    public RouteTracker(HttpRoute httpRoute) {
        this(httpRoute.getTargetHost(), httpRoute.getLocalAddress());
    }

    public Object clone() {
        return super.clone();
    }

    public final void connectProxy(HttpHost httpHost, boolean z) {
        Args.notNull(httpHost, "Proxy host");
        Asserts.check(!this.connected, "Already connected");
        this.connected = true;
        this.proxyChain = new HttpHost[]{httpHost};
        this.secure = z;
    }

    public final void connectProxy(boolean z) {
        Asserts.check(!this.connected, "Already connected");
        this.connected = true;
        this.secure = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RouteTracker)) {
            return false;
        }
        RouteTracker $r2 = obj;
        if (this.connected != $r2.connected || this.secure != $r2.secure || this.tunnelled != $r2.tunnelled || this.layered != $r2.layered || !LangUtils.equals((Object) this.targetHost, (Object) $r2.targetHost) || !LangUtils.equals((Object) this.localAddress, (Object) $r2.localAddress)) {
            return false;
        }
        HttpHost[] $r11 = this.proxyChain;
        HttpHost[] $r12 = $r2.proxyChain;
        HttpHost[] httpHostArr = $r12;
        return LangUtils.equals((Object[]) $r11, (Object[]) $r12);
    }

    public final int getHopCount() {
        if (!this.connected) {
            return 0;
        }
        HttpHost[] $r1 = this.proxyChain;
        if ($r1 == null) {
            return 1;
        }
        return 1 + $r1.length;
    }

    public final HttpHost getHopTarget(int i) {
        Args.notNegative(i, "Hop index");
        int $i0 = getHopCount();
        Args.check(i < $i0, "Hop index exceeds tracked route length");
        return i < $i0 - 1 ? this.proxyChain[i] : this.targetHost;
    }

    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public final HttpHost getProxyHost() {
        HttpHost[] $r1 = this.proxyChain;
        if ($r1 == null) {
            return null;
        }
        return $r1[0];
    }

    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    public final int hashCode() {
        int $i1 = LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.targetHost), (Object) this.localAddress);
        HttpHost[] $r3 = this.proxyChain;
        if ($r3 != null) {
            for (HttpHost $r1 : $r3) {
                $i1 = LangUtils.hashCode($i1, (Object) $r1);
            }
        }
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode($i1, this.connected), this.secure), (Object) this.tunnelled), (Object) this.layered);
    }

    public final boolean isConnected() {
        return this.connected;
    }

    public final boolean isLayered() {
        return this.layered == RouteInfo.LayerType.LAYERED;
    }

    public final boolean isSecure() {
        return this.secure;
    }

    public final boolean isTunnelled() {
        return this.tunnelled == RouteInfo.TunnelType.TUNNELLED;
    }

    public final void layerProtocol(boolean z) {
        Asserts.check(this.connected, "No layered protocol unless connected");
        this.layered = RouteInfo.LayerType.LAYERED;
        this.secure = z;
    }

    public void reset() {
        this.connected = false;
        this.proxyChain = null;
        this.tunnelled = RouteInfo.TunnelType.PLAIN;
        this.layered = RouteInfo.LayerType.PLAIN;
        this.secure = false;
    }

    public final HttpRoute toRoute() {
        if (!this.connected) {
            return null;
        }
        return new HttpRoute(this.targetHost, this.localAddress, this.proxyChain, this.secure, this.tunnelled, this.layered);
    }

    public final String toString() {
        StringBuilder $r2 = new StringBuilder((getHopCount() * 30) + 50);
        $r2.append("RouteTracker[");
        InetAddress $r3 = this.localAddress;
        if ($r3 != null) {
            $r2.append($r3);
            $r2.append("->");
        }
        $r2.append('{');
        if (this.connected) {
            $r2.append('c');
        }
        if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED) {
            $r2.append('t');
        }
        if (this.layered == RouteInfo.LayerType.LAYERED) {
            $r2.append('l');
        }
        if (this.secure) {
            $r2.append('s');
        }
        $r2.append("}->");
        HttpHost[] $r8 = this.proxyChain;
        if ($r8 != null) {
            for (HttpHost $r1 : $r8) {
                $r2.append($r1);
                $r2.append("->");
            }
        }
        $r2.append(this.targetHost);
        $r2.append(']');
        return $r2.toString();
    }

    public final void tunnelTarget(boolean z) {
        Asserts.check(this.connected, "No tunnel unless connected");
        Asserts.notNull(this.proxyChain, "No tunnel without proxy");
        this.tunnelled = RouteInfo.TunnelType.TUNNELLED;
        this.secure = z;
    }
}
