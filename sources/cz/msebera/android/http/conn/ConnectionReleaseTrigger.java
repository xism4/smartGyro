package cz.msebera.android.http.conn;

public interface ConnectionReleaseTrigger {
    void abortConnection();

    void releaseConnection();
}
