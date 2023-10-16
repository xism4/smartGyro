package cz.msebera.android.http.conn.tsccm;

import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public final class ConnManagerParams
  implements ConnManagerPNames
{
  private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE = new ConnPerRoute()
  {
    public int getMaxForRoute(HttpRoute paramAnonymousHttpRoute)
    {
      return 2;
    }
  };
  
  public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    ConnPerRoute localConnPerRoute = (ConnPerRoute)paramHttpParams.getParameter("http.conn-manager.max-per-route");
    paramHttpParams = localConnPerRoute;
    if (localConnPerRoute == null) {
      paramHttpParams = DEFAULT_CONN_PER_ROUTE;
    }
    return paramHttpParams;
  }
  
  public static int getMaxTotalConnections(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getIntParameter("http.conn-manager.max-total", 20);
  }
  
  public static void setMaxConnectionsPerRoute(HttpParams paramHttpParams, ConnPerRoute paramConnPerRoute)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setParameter("http.conn-manager.max-per-route", paramConnPerRoute);
  }
  
  public static void setMaxTotalConnections(HttpParams paramHttpParams, int paramInt)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setIntParameter("http.conn-manager.max-total", paramInt);
  }
  
  public static void setTimeout(HttpParams paramHttpParams, long paramLong)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setLongParameter("http.conn-manager.timeout", paramLong);
  }
}
