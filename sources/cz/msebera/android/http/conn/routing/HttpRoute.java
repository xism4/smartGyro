package cz.msebera.android.http.conn.routing;

import c.a.a.a.o;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class HttpRoute implements RouteInfo, Cloneable {
    private final RouteInfo.LayerType layered;
    private final InetAddress localAddress;
    private final List<o> proxyChain;
    private final boolean secure;
    private final HttpHost targetHost;
    private final RouteInfo.TunnelType tunnelled;

    public HttpRoute(HttpHost httpHost) {
        this(httpHost, (InetAddress) null, Collections.emptyList(), false, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost httpHost2, boolean z) {
        this(httpHost, inetAddress, Collections.singletonList(httpHost2), z, z ? RouteInfo.TunnelType.TUNNELLED : RouteInfo.TunnelType.PLAIN, z ? RouteInfo.LayerType.LAYERED : RouteInfo.LayerType.PLAIN);
        Args.notNull(httpHost2, "Proxy host");
    }

    private HttpRoute(HttpHost httpHost, InetAddress inetAddress, List list, boolean z, RouteInfo.TunnelType $r4, RouteInfo.LayerType $r5) {
        Args.notNull(httpHost, "Target host");
        this.targetHost = normalize(httpHost);
        this.localAddress = inetAddress;
        this.proxyChain = (list == null || list.isEmpty()) ? null : new ArrayList(list);
        if ($r4 == RouteInfo.TunnelType.TUNNELLED) {
            Args.check(this.proxyChain != null, "Proxy required if tunnelled");
        }
        this.secure = z;
        this.tunnelled = $r4 == null ? RouteInfo.TunnelType.PLAIN : $r4;
        this.layered = $r5 == null ? RouteInfo.LayerType.PLAIN : $r5;
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, boolean z) {
        this(httpHost, inetAddress, Collections.emptyList(), z, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost[] httpHostArr, boolean z, RouteInfo.TunnelType tunnelType, RouteInfo.LayerType layerType) {
        this(httpHost, inetAddress, httpHostArr != null ? Arrays.asList(httpHostArr) : null, z, tunnelType, layerType);
    }

    private static int getDefaultPort(String str) {
        if ("http".equalsIgnoreCase(str)) {
            return 80;
        }
        return "https".equalsIgnoreCase(str) ? 443 : -1;
    }

    private static HttpHost normalize(HttpHost $r0) {
        if ($r0.getPort() >= 0) {
            return $r0;
        }
        InetAddress $r1 = $r0.getAddress();
        String $r2 = $r0.getSchemeName();
        return $r1 != null ? new HttpHost($r1, getDefaultPort($r2), $r2) : new HttpHost($r0.getHostName(), getDefaultPort($r2), $r2);
    }

    public Object clone() {
        return super.clone();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpRoute)) {
            return false;
        }
        HttpRoute $r2 = obj;
        if (this.secure != $r2.secure || this.tunnelled != $r2.tunnelled || this.layered != $r2.layered || !LangUtils.equals((Object) this.targetHost, (Object) $r2.targetHost) || !LangUtils.equals((Object) this.localAddress, (Object) $r2.localAddress)) {
            return false;
        }
        List $r11 = this.proxyChain;
        List<o> list = $r2.proxyChain;
        List<o> list2 = list;
        return LangUtils.equals((Object) $r11, (Object) list);
    }

    public final int getHopCount() {
        List $r1 = this.proxyChain;
        if ($r1 != null) {
            return 1 + $r1.size();
        }
        return 1;
    }

    public final HttpHost getHopTarget(int i) {
        Args.notNegative(i, "Hop index");
        int $i1 = getHopCount();
        Args.check(i < $i1, "Hop index exceeds tracked route length");
        return i < $i1 - 1 ? this.proxyChain.get(i) : this.targetHost;
    }

    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public final HttpHost getProxyHost() {
        List $r1 = this.proxyChain;
        if ($r1 == null || $r1.isEmpty()) {
            return null;
        }
        return this.proxyChain.get(0);
    }

    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    public final int hashCode() {
        int $i0 = LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.targetHost), (Object) this.localAddress);
        List<HttpHost> $r3 = this.proxyChain;
        if ($r3 != null) {
            for (HttpHost $r1 : $r3) {
                $i0 = LangUtils.hashCode($i0, (Object) $r1);
            }
        }
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode($i0, this.secure), (Object) this.tunnelled), (Object) this.layered);
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

    public final String toString() {
        StringBuilder $r1 = new StringBuilder((getHopCount() * 30) + 50);
        InetAddress $r2 = this.localAddress;
        if ($r2 != null) {
            $r1.append($r2);
            $r1.append("->");
        }
        $r1.append('{');
        if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED) {
            $r1.append('t');
        }
        if (this.layered == RouteInfo.LayerType.LAYERED) {
            $r1.append('l');
        }
        if (this.secure) {
            $r1.append('s');
        }
        $r1.append("}->");
        List<HttpHost> $r7 = this.proxyChain;
        if ($r7 != null) {
            for (HttpHost $r10 : $r7) {
                $r1.append($r10);
                $r1.append("->");
            }
        }
        $r1.append(this.targetHost);
        return $r1.toString();
    }
}
