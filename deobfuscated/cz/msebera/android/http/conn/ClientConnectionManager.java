package cz.msebera.android.http.conn;

import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import java.util.concurrent.TimeUnit;

@Deprecated
public abstract interface ClientConnectionManager
{
  public abstract SchemeRegistry getSchemeRegistry();
  
  public abstract void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit);
  
  public abstract ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject);
  
  public abstract void shutdown();
}
