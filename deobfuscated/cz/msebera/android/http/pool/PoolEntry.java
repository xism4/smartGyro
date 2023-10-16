package cz.msebera.android.http.pool;

import cz.msebera.android.http.mime.Args;
import java.util.concurrent.TimeUnit;

public abstract class PoolEntry<T, C>
{
  private final C conn;
  private final long created;
  private long expiry;
  private final String id;
  private final T route;
  private volatile Object state;
  private long updated;
  private final long validUnit;
  
  public PoolEntry(String paramString, Object paramObject1, Object paramObject2, long paramLong, TimeUnit paramTimeUnit)
  {
    Args.notNull(paramObject1, "Route");
    Args.notNull(paramObject2, "Connection");
    Args.notNull(paramTimeUnit, "Time unit");
    id = paramString;
    route = paramObject1;
    conn = paramObject2;
    created = System.currentTimeMillis();
    if (paramLong > 0L) {
      paramLong = created + paramTimeUnit.toMillis(paramLong);
    } else {
      paramLong = Long.MAX_VALUE;
    }
    validUnit = paramLong;
    expiry = validUnit;
  }
  
  public Object getConnection()
  {
    return conn;
  }
  
  public long getExpiry()
  {
    try
    {
      long l = expiry;
      return l;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public Object getRoute()
  {
    return route;
  }
  
  public boolean isExpired(long paramLong)
  {
    try
    {
      long l = expiry;
      boolean bool;
      if (paramLong >= l) {
        bool = true;
      } else {
        bool = false;
      }
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setState(Object paramObject)
  {
    state = paramObject;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[id:");
    localStringBuilder.append(id);
    localStringBuilder.append("][route:");
    localStringBuilder.append(route);
    localStringBuilder.append("][state:");
    localStringBuilder.append(state);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void updateExpiry(long paramLong, TimeUnit paramTimeUnit)
  {
    for (;;)
    {
      try
      {
        Args.notNull(paramTimeUnit, "Time unit");
        updated = System.currentTimeMillis();
        if (paramLong > 0L)
        {
          paramLong = updated + paramTimeUnit.toMillis(paramLong);
          expiry = Math.min(paramLong, validUnit);
          return;
        }
      }
      catch (Throwable paramTimeUnit)
      {
        throw paramTimeUnit;
      }
      paramLong = Long.MAX_VALUE;
    }
  }
}
