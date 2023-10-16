package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.util.concurrent.TimeUnit;

@Deprecated
public abstract interface ManagedClientConnection
  extends HttpClientConnection, HttpRoutedConnection, ManagedHttpClientConnection, ConnectionReleaseTrigger
{
  public abstract HttpRoute getRoute();
  
  public abstract void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams);
  
  public abstract void markReusable();
  
  public abstract void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams);
  
  public abstract void setIdleDuration(long paramLong, TimeUnit paramTimeUnit);
  
  public abstract void setState(Object paramObject);
  
  public abstract void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams);
  
  public abstract void unmarkReusable();
}
