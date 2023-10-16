package cz.msebera.android.http.io;

import cz.msebera.android.http.mime.CharArrayBuffer;

public interface SessionOutputBuffer {
    void flush();

    HttpTransportMetrics getMetrics();

    void write(int i);

    void write(byte[] bArr, int i, int i2);

    void writeLine(CharArrayBuffer charArrayBuffer);

    void writeLine(String str);
}
