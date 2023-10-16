package cz.msebera.android.http.impl;

import cz.msebera.android.http.HttpConnectionMetrics;
import cz.msebera.android.http.io.HttpTransportMetrics;

public class HttpConnectionMetricsImpl implements HttpConnectionMetrics {
    private final HttpTransportMetrics inTransportMetric;
    private final HttpTransportMetrics outTransportMetric;
    private long requestCount = 0;
    private long responseCount = 0;

    public HttpConnectionMetricsImpl(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        this.inTransportMetric = httpTransportMetrics;
        this.outTransportMetric = httpTransportMetrics2;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    public void incrementResponseCount() {
        this.responseCount++;
    }
}
