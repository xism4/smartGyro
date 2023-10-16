package cz.msebera.android.http;

import java.io.Closeable;

public abstract interface HttpConnection
  extends Closeable
{
  public abstract void close();
  
  public abstract boolean isOpen();
  
  public abstract boolean isStale();
  
  public abstract void setSocketTimeout(int paramInt);
  
  public abstract void shutdown();
}
