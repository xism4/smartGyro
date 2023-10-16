package cz.msebera.android.http.util;

import cz.msebera.android.http.mime.Args;

@Deprecated
public final class HttpConnectionParams
  implements CoreConnectionPNames
{
  public static int getConnectionTimeout(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getIntParameter("http.connection.timeout", 0);
  }
  
  public static int getLinger(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getIntParameter("http.socket.linger", -1);
  }
  
  public static boolean getSoReuseaddr(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getBooleanParameter("http.socket.reuseaddr", false);
  }
  
  public static int getSoTimeout(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getIntParameter("http.socket.timeout", 0);
  }
  
  public static boolean getTcpNoDelay(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getBooleanParameter("http.tcp.nodelay", true);
  }
  
  public static boolean isStaleCheckingEnabled(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    return paramHttpParams.getBooleanParameter("http.connection.stalecheck", true);
  }
  
  public static void setConnectionTimeout(HttpParams paramHttpParams, int paramInt)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setIntParameter("http.connection.timeout", paramInt);
  }
  
  public static void setSoTimeout(HttpParams paramHttpParams, int paramInt)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setIntParameter("http.socket.timeout", paramInt);
  }
  
  public static void setSocketBufferSize(HttpParams paramHttpParams, int paramInt)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setIntParameter("http.socket.buffer-size", paramInt);
  }
  
  public static void setTcpNoDelay(HttpParams paramHttpParams, boolean paramBoolean)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setBooleanParameter("http.tcp.nodelay", paramBoolean);
  }
}
