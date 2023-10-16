package cz.msebera.android.http.client;

import cz.msebera.android.http.conn.routing.HttpRoute;

public interface BackoffManager {
    void backOff(HttpRoute httpRoute);

    void probe(HttpRoute httpRoute);
}
