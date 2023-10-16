package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.HttpTransportMetrics;

public class HttpTransportMetricsImpl
  implements HttpTransportMetrics
{
  private long bytesTransferred = 0L;
  
  public HttpTransportMetricsImpl() {}
  
  public void incrementBytesTransferred(long paramLong)
  {
    bytesTransferred += paramLong;
  }
}
