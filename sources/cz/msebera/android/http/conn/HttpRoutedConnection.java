package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;

@Deprecated
public interface HttpRoutedConnection extends HttpInetConnection {
    HttpRoute getRoute();
}
