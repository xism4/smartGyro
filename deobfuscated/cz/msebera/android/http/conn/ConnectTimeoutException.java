package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpHost;
import java.io.InterruptedIOException;

public class ConnectTimeoutException
  extends InterruptedIOException
{
  private final HttpHost host = null;
  
  public ConnectTimeoutException(String paramString)
  {
    super(paramString);
  }
}
