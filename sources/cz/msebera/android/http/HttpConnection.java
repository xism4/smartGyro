package cz.msebera.android.http;

import java.io.Closeable;

public interface HttpConnection extends Closeable {
    void close();

    boolean isOpen();

    boolean isStale();

    void setSocketTimeout(int i);

    void shutdown();
}
