package cz.msebera.android.http.client.protocol;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public class HttpClientParams
{
  public static long getConnectionManagerTimeout(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    Long localLong = (Long)paramHttpParams.getParameter("http.conn-manager.timeout");
    if (localLong != null) {
      return localLong.longValue();
    }
    return HttpConnectionParams.getConnectionTimeout(paramHttpParams);
  }
  
  public static boolean isAuthenticating(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getBooleanParameter("http.protocol.handle-authentication", true);
  }
  
  public static boolean isRedirecting(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getBooleanParameter("http.protocol.handle-redirects", true);
  }
}
