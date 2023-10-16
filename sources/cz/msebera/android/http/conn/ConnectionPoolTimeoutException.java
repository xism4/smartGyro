package cz.msebera.android.http.conn;

public class ConnectionPoolTimeoutException extends ConnectTimeoutException {
    public ConnectionPoolTimeoutException(String str) {
        super(str);
    }
}
