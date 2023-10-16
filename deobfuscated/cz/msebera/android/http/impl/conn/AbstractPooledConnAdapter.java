package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.RouteTracker;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public abstract class AbstractPooledConnAdapter
  extends AbstractClientConnAdapter
{
  protected volatile AbstractPoolEntry poolEntry;
  
  protected AbstractPooledConnAdapter(ClientConnectionManager paramClientConnectionManager, AbstractPoolEntry paramAbstractPoolEntry)
  {
    super(paramClientConnectionManager, connection);
    poolEntry = paramAbstractPoolEntry;
  }
  
  protected void assertValid(AbstractPoolEntry paramAbstractPoolEntry)
  {
    if ((!isReleased()) && (paramAbstractPoolEntry != null)) {
      return;
    }
    throw new ConnectionShutdownException();
  }
  
  public void close()
  {
    Object localObject = getPoolEntry();
    if (localObject != null) {
      ((AbstractPoolEntry)localObject).shutdownEntry();
    }
    localObject = getWrappedConnection();
    if (localObject != null) {
      ((HttpConnection)localObject).close();
    }
  }
  
  protected void detach()
  {
    try
    {
      poolEntry = null;
      super.detach();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected AbstractPoolEntry getPoolEntry()
  {
    return poolEntry;
  }
  
  public HttpRoute getRoute()
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    if (tracker == null) {
      return null;
    }
    return tracker.toRoute();
  }
  
  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.layerProtocol(paramHttpContext, paramHttpParams);
  }
  
  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.open(paramHttpRoute, paramHttpContext, paramHttpParams);
  }
  
  public void setState(Object paramObject)
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.setState(paramObject);
  }
  
  public void shutdown()
  {
    Object localObject = getPoolEntry();
    if (localObject != null) {
      ((AbstractPoolEntry)localObject).shutdownEntry();
    }
    localObject = getWrappedConnection();
    if (localObject != null) {
      ((HttpConnection)localObject).shutdown();
    }
  }
  
  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.tunnelTarget(paramBoolean, paramHttpParams);
  }
}
