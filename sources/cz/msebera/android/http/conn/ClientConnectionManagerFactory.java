package cz.msebera.android.http.conn;

import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public interface ClientConnectionManagerFactory {
    ClientConnectionManager newInstance(HttpParams httpParams, SchemeRegistry schemeRegistry);
}
