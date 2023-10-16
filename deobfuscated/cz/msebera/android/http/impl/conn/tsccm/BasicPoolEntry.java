package cz.msebera.android.http.impl.conn.tsccm;

import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.impl.conn.AbstractPoolEntry;
import cz.msebera.android.http.mime.Args;
import java.util.concurrent.TimeUnit;

@Deprecated
public class BasicPoolEntry
  extends AbstractPoolEntry
{
  private final long created;
  private long expiry;
  private long updated;
  private final long validUntil;
  
  public BasicPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute, long paramLong, TimeUnit paramTimeUnit)
  {
    super(paramClientConnectionOperator, paramHttpRoute);
    Args.notNull(paramHttpRoute, "HTTP route");
    created = System.currentTimeMillis();
    if (paramLong > 0L) {
      paramLong = created + paramTimeUnit.toMillis(paramLong);
    } else {
      paramLong = Long.MAX_VALUE;
    }
    validUntil = paramLong;
    expiry = validUntil;
  }
  
  protected final OperatedClientConnection getConnection()
  {
    return connection;
  }
  
  protected final HttpRoute getPlannedRoute()
  {
    return route;
  }
  
  public boolean isExpired(long paramLong)
  {
    return paramLong >= expiry;
  }
  
  protected void shutdownEntry()
  {
    super.shutdownEntry();
  }
  
  public void updateExpiry(long paramLong, TimeUnit paramTimeUnit)
  {
    updated = System.currentTimeMillis();
    if (paramLong > 0L) {
      paramLong = updated + paramTimeUnit.toMillis(paramLong);
    } else {
      paramLong = Long.MAX_VALUE;
    }
    expiry = Math.min(validUntil, paramLong);
  }
}
