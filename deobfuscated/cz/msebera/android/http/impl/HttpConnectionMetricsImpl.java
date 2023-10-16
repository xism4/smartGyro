package cz.msebera.android.http.impl;

import cz.msebera.android.http.HttpConnectionMetrics;
import cz.msebera.android.http.io.HttpTransportMetrics;

public class HttpConnectionMetricsImpl
  implements HttpConnectionMetrics
{
  private final HttpTransportMetrics inTransportMetric;
  private final HttpTransportMetrics outTransportMetric;
  private long requestCount = 0L;
  private long responseCount = 0L;
  
  public HttpConnectionMetricsImpl(HttpTransportMetrics paramHttpTransportMetrics1, HttpTransportMetrics paramHttpTransportMetrics2)
  {
    inTransportMetric = paramHttpTransportMetrics1;
    outTransportMetric = paramHttpTransportMetrics2;
  }
  
  public void incrementRequestCount()
  {
    requestCount += 1L;
  }
  
  public void incrementResponseCount()
  {
    responseCount += 1L;
  }
}
