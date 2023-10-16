package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.conn.scheme.PlainSocketFactory;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.ssl.SSLSocketFactory;

@Deprecated
public final class SchemeRegistryFactory
{
  public static SchemeRegistry createDefault()
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
    localSchemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
    return localSchemeRegistry;
  }
}
