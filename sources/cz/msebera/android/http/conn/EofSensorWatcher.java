package cz.msebera.android.http.conn;

import java.io.InputStream;

public interface EofSensorWatcher {
    boolean eofDetected(InputStream inputStream);

    boolean streamAbort(InputStream inputStream);

    boolean streamClosed(InputStream inputStream);
}
