package cz.msebera.android.http.conn;

import java.io.InputStream;

public abstract interface EofSensorWatcher
{
  public abstract boolean eofDetected(InputStream paramInputStream);
  
  public abstract boolean streamAbort(InputStream paramInputStream);
  
  public abstract boolean streamClosed(InputStream paramInputStream);
}
