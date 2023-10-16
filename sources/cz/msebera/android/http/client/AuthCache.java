package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.auth.AuthScheme;

public interface AuthCache {
    AuthScheme get(HttpHost httpHost);

    void put(HttpHost httpHost, AuthScheme authScheme);

    void remove(HttpHost httpHost);
}
