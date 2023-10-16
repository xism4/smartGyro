package cz.msebera.android.http.io;

import cz.msebera.android.http.mime.CharArrayBuffer;

public interface SessionInputBuffer {
    HttpTransportMetrics getMetrics();

    boolean isDataAvailable(int i);

    int read();

    int read(byte[] bArr, int i, int i2);

    int readLine(CharArrayBuffer charArrayBuffer);
}
