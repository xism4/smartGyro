package cz.msebera.android.http.conn.tsccm;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;

@Deprecated
public class ConnRouteParams
  implements ConnRoutePNames
{
  public static final HttpHost NO_HOST = new HttpHost("127.0.0.255", 0, "no-host");
  public static final HttpRoute NO_ROUTE = new HttpRoute(NO_HOST);
  
  public static HttpHost getDefaultProxy(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "Parameters");
    paramHttpParams = (HttpHost)paramHttpParams.getParameter("http.route.default-proxy");
    if ((paramHttpParams != null) && (NO_HOST.equals(paramHttpParams))) {
      return null;
    }
    return paramHttpParams;
  }
  
  public static HttpRoute getForcedRoute(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "Parameters");
    paramHttpParams = (HttpRoute)paramHttpParams.getParameter("http.route.forced-route");
    if ((paramHttpParams != null) && (NO_ROUTE.equals(paramHttpParams))) {
      return null;
    }
    return paramHttpParams;
  }
  
  public static InetAddress getLocalAddress(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "Parameters");
    return (InetAddress)paramHttpParams.getParameter("http.route.local-address");
  }
}
