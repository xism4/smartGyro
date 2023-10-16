package cz.msebera.android.http.impl.conn;

import c.a.a.a.e.b.b;
import c.a.a.a.e.q;
import c.a.a.a.m.a;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.RouteTracker;
import cz.msebera.android.http.pool.PoolEntry;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Deprecated
class HttpPoolEntry
  extends a<b, q>
{
  public HttpClientAndroidLog log;
  private final RouteTracker tracker;
  
  public HttpPoolEntry(HttpClientAndroidLog paramHttpClientAndroidLog, String paramString, HttpRoute paramHttpRoute, OperatedClientConnection paramOperatedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    super(paramString, paramHttpRoute, paramOperatedClientConnection, paramLong, paramTimeUnit);
    log = paramHttpClientAndroidLog;
    tracker = new RouteTracker(paramHttpRoute);
  }
  
  public void close()
  {
    OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)getConnection();
    try
    {
      localOperatedClientConnection.close();
      return;
    }
    catch (IOException localIOException)
    {
      log.debug("I/O error closing connection", localIOException);
    }
  }
  
  HttpRoute getEffectiveRoute()
  {
    return tracker.toRoute();
  }
  
  HttpRoute getPlannedRoute()
  {
    return (HttpRoute)getRoute();
  }
  
  RouteTracker getTracker()
  {
    return tracker;
  }
  
  public boolean isClosed()
  {
    return !((OperatedClientConnection)getConnection()).isOpen();
  }
  
  public boolean isExpired(long paramLong)
  {
    boolean bool = super.isExpired(paramLong);
    if ((bool) && (log.isDebugEnabled()))
    {
      HttpClientAndroidLog localHttpClientAndroidLog = log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Connection ");
      localStringBuilder.append(this);
      localStringBuilder.append(" expired @ ");
      localStringBuilder.append(new Date(getExpiry()));
      localHttpClientAndroidLog.debug(localStringBuilder.toString());
    }
    return bool;
  }
}
