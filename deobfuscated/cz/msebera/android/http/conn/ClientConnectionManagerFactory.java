package cz.msebera.android.http.conn;

import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public abstract interface ClientConnectionManagerFactory
{
  public abstract ClientConnectionManager newInstance(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry);
}
