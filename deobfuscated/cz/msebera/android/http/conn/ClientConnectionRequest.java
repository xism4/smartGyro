package cz.msebera.android.http.conn;

import java.util.concurrent.TimeUnit;

@Deprecated
public abstract interface ClientConnectionRequest
{
  public abstract void abortRequest();
  
  public abstract ManagedClientConnection getConnection(long paramLong, TimeUnit paramTimeUnit);
}
