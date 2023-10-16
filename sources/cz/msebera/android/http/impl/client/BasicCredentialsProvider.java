package cz.msebera.android.http.impl.client;

import c.a.a.a.a.h;
import c.a.a.a.a.n;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.mime.Args;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicCredentialsProvider implements CredentialsProvider {
    private final ConcurrentHashMap<h, n> credMap = new ConcurrentHashMap();

    private static Credentials matchCredentials(Map map, AuthScope authScope) {
        Credentials $r3 = (Credentials) map.get(authScope);
        if ($r3 != null) {
            return $r3;
        }
        int $i0 = -1;
        AuthScope $r4 = null;
        for (AuthScope $r7 : map.keySet()) {
            int $i1 = authScope.match($r7);
            if ($i1 > $i0) {
                $r4 = $r7;
                $i0 = $i1;
            }
        }
        return $r4 != null ? (Credentials) map.get($r4) : $r3;
    }

    public Credentials getCredentials(AuthScope authScope) {
        Args.notNull(authScope, "Authentication scope");
        return matchCredentials(this.credMap, authScope);
    }

    public String toString() {
        return this.credMap.toString();
    }
}
