package cz.msebera.android.http.conn.routing;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import java.net.InetAddress;

public class BasicRouteDirector
  implements HttpRouteDirector
{
  public BasicRouteDirector() {}
  
  protected int directStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    if (paramRouteInfo2.getHopCount() > 1) {
      return -1;
    }
    if (!paramRouteInfo1.getTargetHost().equals(paramRouteInfo2.getTargetHost())) {
      return -1;
    }
    if (paramRouteInfo1.isSecure() != paramRouteInfo2.isSecure()) {
      return -1;
    }
    if ((paramRouteInfo1.getLocalAddress() != null) && (!paramRouteInfo1.getLocalAddress().equals(paramRouteInfo2.getLocalAddress()))) {
      return -1;
    }
    return 0;
  }
  
  protected int firstStep(RouteInfo paramRouteInfo)
  {
    if (paramRouteInfo.getHopCount() > 1) {
      return 2;
    }
    return 1;
  }
  
  public int nextStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    Args.notNull(paramRouteInfo1, "Planned route");
    if ((paramRouteInfo2 != null) && (paramRouteInfo2.getHopCount() >= 1))
    {
      if (paramRouteInfo1.getHopCount() > 1) {
        return proxiedStep(paramRouteInfo1, paramRouteInfo2);
      }
      return directStep(paramRouteInfo1, paramRouteInfo2);
    }
    return firstStep(paramRouteInfo1);
  }
  
  protected int proxiedStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    if (paramRouteInfo2.getHopCount() <= 1) {
      return -1;
    }
    if (!paramRouteInfo1.getTargetHost().equals(paramRouteInfo2.getTargetHost())) {
      return -1;
    }
    int j = paramRouteInfo1.getHopCount();
    int k = paramRouteInfo2.getHopCount();
    if (j < k) {
      return -1;
    }
    int i = 0;
    while (i < k - 1)
    {
      if (!paramRouteInfo1.getHopTarget(i).equals(paramRouteInfo2.getHopTarget(i))) {
        return -1;
      }
      i += 1;
    }
    if (j > k) {
      return 4;
    }
    if ((!paramRouteInfo2.isTunnelled()) || (paramRouteInfo1.isTunnelled()))
    {
      if ((paramRouteInfo2.isLayered()) && (!paramRouteInfo1.isLayered())) {
        return -1;
      }
      if ((paramRouteInfo1.isTunnelled()) && (!paramRouteInfo2.isTunnelled())) {
        return 3;
      }
      if ((paramRouteInfo1.isLayered()) && (!paramRouteInfo2.isLayered())) {
        return 5;
      }
      if (paramRouteInfo1.isSecure() != paramRouteInfo2.isSecure()) {
        return -1;
      }
      return 0;
    }
    return -1;
  }
}
