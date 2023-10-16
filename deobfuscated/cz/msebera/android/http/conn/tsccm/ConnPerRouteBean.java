package cz.msebera.android.http.conn.tsccm;

import c.a.a.a.e.b.b;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.mime.Args;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class ConnPerRouteBean
  implements ConnPerRoute
{
  private volatile int defaultMax;
  private final ConcurrentHashMap<b, Integer> maxPerHostMap = new ConcurrentHashMap();
  
  public ConnPerRouteBean()
  {
    this(2);
  }
  
  public ConnPerRouteBean(int paramInt)
  {
    setDefaultMaxPerRoute(paramInt);
  }
  
  public int getMaxForRoute(HttpRoute paramHttpRoute)
  {
    Args.notNull(paramHttpRoute, "HTTP route");
    paramHttpRoute = (Integer)maxPerHostMap.get(paramHttpRoute);
    if (paramHttpRoute != null) {
      return paramHttpRoute.intValue();
    }
    return defaultMax;
  }
  
  public void setDefaultMaxPerRoute(int paramInt)
  {
    Args.positive(paramInt, "Default max per route");
    defaultMax = paramInt;
  }
  
  public String toString()
  {
    return maxPerHostMap.toString();
  }
}
