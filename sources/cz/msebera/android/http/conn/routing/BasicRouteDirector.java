package cz.msebera.android.http.conn.routing;

import cz.msebera.android.http.mime.Args;

public class BasicRouteDirector implements HttpRouteDirector {
    /* access modifiers changed from: protected */
    public int directStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        if (routeInfo2.getHopCount() <= 1 && routeInfo.getTargetHost().equals(routeInfo2.getTargetHost()) && routeInfo.isSecure() == routeInfo2.isSecure()) {
            return (routeInfo.getLocalAddress() == null || routeInfo.getLocalAddress().equals(routeInfo2.getLocalAddress())) ? 0 : -1;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int firstStep(RouteInfo routeInfo) {
        return routeInfo.getHopCount() > 1 ? 2 : 1;
    }

    public int nextStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        Args.notNull(routeInfo, "Planned route");
        return (routeInfo2 == null || routeInfo2.getHopCount() < 1) ? firstStep(routeInfo) : routeInfo.getHopCount() > 1 ? proxiedStep(routeInfo, routeInfo2) : directStep(routeInfo, routeInfo2);
    }

    /* access modifiers changed from: protected */
    public int proxiedStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        int $i0;
        int $i1;
        if (routeInfo2.getHopCount() <= 1 || !routeInfo.getTargetHost().equals(routeInfo2.getTargetHost()) || ($i0 = routeInfo.getHopCount()) < ($i1 = routeInfo2.getHopCount())) {
            return -1;
        }
        for (int $i2 = 0; $i2 < $i1 - 1; $i2++) {
            if (!routeInfo.getHopTarget($i2).equals(routeInfo2.getHopTarget($i2))) {
                return -1;
            }
        }
        if ($i0 > $i1) {
            return 4;
        }
        if (routeInfo2.isTunnelled() && !routeInfo.isTunnelled()) {
            return -1;
        }
        if (routeInfo2.isLayered() && !routeInfo.isLayered()) {
            return -1;
        }
        if (routeInfo.isTunnelled() && !routeInfo2.isTunnelled()) {
            return 3;
        }
        if (!routeInfo.isLayered() || routeInfo2.isLayered()) {
            return routeInfo.isSecure() != routeInfo2.isSecure() ? -1 : 0;
        }
        return 5;
    }
}
