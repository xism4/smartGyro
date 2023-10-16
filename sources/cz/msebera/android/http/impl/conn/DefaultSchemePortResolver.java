package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.SchemePortResolver;
import cz.msebera.android.http.conn.UnsupportedSchemeException;
import cz.msebera.android.http.mime.Args;

public class DefaultSchemePortResolver implements SchemePortResolver {
    public static final DefaultSchemePortResolver INSTANCE = new DefaultSchemePortResolver();

    public int resolve(HttpHost httpHost) {
        Args.notNull(httpHost, "HTTP host");
        int $i0 = httpHost.getPort();
        if ($i0 > 0) {
            return $i0;
        }
        String $r3 = httpHost.getSchemeName();
        if ($r3.equalsIgnoreCase("http")) {
            return 80;
        }
        if ($r3.equalsIgnoreCase("https")) {
            return 443;
        }
        throw new UnsupportedSchemeException($r3 + " protocol is not supported");
    }
}
