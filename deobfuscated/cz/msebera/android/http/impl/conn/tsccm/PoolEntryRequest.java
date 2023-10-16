package cz.msebera.android.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;

@Deprecated
public abstract interface PoolEntryRequest
{
  public abstract void abortRequest();
  
  public abstract BasicPoolEntry getPoolEntry(long paramLong, TimeUnit paramTimeUnit);
}
