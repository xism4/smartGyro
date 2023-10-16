package cz.msebera.android.http.conn.scheme;

import c.a.a.a.e.c.e;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class SchemeRegistry {
    private final ConcurrentHashMap<String, e> registeredSchemes = new ConcurrentHashMap();

    public final Scheme get(String str) {
        Args.notNull(str, "Scheme name");
        return this.registeredSchemes.get(str);
    }

    public final Scheme getScheme(HttpHost httpHost) {
        Args.notNull(httpHost, "Host");
        return getScheme(httpHost.getSchemeName());
    }

    public final Scheme getScheme(String str) {
        Scheme $r3 = get(str);
        if ($r3 != null) {
            return $r3;
        }
        throw new IllegalStateException("Scheme '" + str + "' not registered.");
    }

    public final Scheme register(Scheme scheme) {
        Args.notNull(scheme, "Scheme");
        return this.registeredSchemes.put(scheme.getName(), scheme);
    }
}
