package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.HttpTransportMetrics;

public class HttpTransportMetricsImpl implements HttpTransportMetrics {
    private long bytesTransferred = 0;

    public void incrementBytesTransferred(long j) {
        this.bytesTransferred += j;
    }
}
