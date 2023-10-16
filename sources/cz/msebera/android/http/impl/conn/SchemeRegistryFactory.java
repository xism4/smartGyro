package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.conn.scheme.PlainSocketFactory;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.scheme.SchemeSocketFactory;
import cz.msebera.android.http.conn.ssl.SSLSocketFactory;

@Deprecated
public final class SchemeRegistryFactory {
    public static SchemeRegistry createDefault() {
        SchemeRegistry $r0 = new SchemeRegistry();
        $r0.register(new Scheme("http", 80, (SchemeSocketFactory) PlainSocketFactory.getSocketFactory()));
        $r0.register(new Scheme("https", 443, (SchemeSocketFactory) SSLSocketFactory.getSocketFactory()));
        return $r0;
    }
}
