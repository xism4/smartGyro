package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpHost;

public interface SchemePortResolver {
    int resolve(HttpHost httpHost);
}
