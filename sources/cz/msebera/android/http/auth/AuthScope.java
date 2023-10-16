package cz.msebera.android.http.auth;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.util.Locale;

public class AuthScope {
    public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
    public static final String ANY_HOST = null;
    public static final String ANY_REALM = null;
    public static final String ANY_SCHEME = null;
    private final String host;
    private final HttpHost origin;
    private final int port;
    private final String realm;
    private final String scheme;

    public AuthScope(HttpHost httpHost, String $r4, String str) {
        Args.notNull(httpHost, "Host");
        this.host = httpHost.getHostName().toLowerCase(Locale.ROOT);
        this.port = httpHost.getPort() < 0 ? -1 : httpHost.getPort();
        this.realm = $r4 == null ? ANY_REALM : $r4;
        this.scheme = str == null ? ANY_SCHEME : str.toUpperCase(Locale.ROOT);
        this.origin = httpHost;
    }

    public AuthScope(String str, int i) {
        this(str, i, ANY_REALM, ANY_SCHEME);
    }

    public AuthScope(String $r3, int $i0, String $r4, String str) {
        this.host = $r3 == null ? ANY_HOST : $r3.toLowerCase(Locale.ROOT);
        this.port = $i0 < 0 ? -1 : $i0;
        this.realm = $r4 == null ? ANY_REALM : $r4;
        this.scheme = str == null ? ANY_SCHEME : str.toUpperCase(Locale.ROOT);
        this.origin = null;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthScope)) {
            return super.equals(obj);
        }
        AuthScope $r2 = (AuthScope) obj;
        return LangUtils.equals((Object) this.host, (Object) $r2.host) && this.port == $r2.port && LangUtils.equals((Object) this.realm, (Object) $r2.realm) && LangUtils.equals((Object) this.scheme, (Object) $r2.scheme);
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.host), this.port), (Object) this.realm), (Object) this.scheme);
    }

    public int match(AuthScope authScope) {
        int $i0;
        if (LangUtils.equals((Object) this.scheme, (Object) authScope.scheme)) {
            $i0 = 1;
        } else {
            String $r3 = this.scheme;
            String $r2 = ANY_SCHEME;
            if ($r3 != $r2 && authScope.scheme != $r2) {
                return -1;
            }
            $i0 = 0;
        }
        if (LangUtils.equals((Object) this.realm, (Object) authScope.realm)) {
            $i0 += 2;
        } else {
            String $r32 = this.realm;
            String $r22 = ANY_REALM;
            if (!($r32 == $r22 || authScope.realm == $r22)) {
                return -1;
            }
        }
        int $i1 = this.port;
        int $i2 = authScope.port;
        if ($i1 == $i2) {
            $i0 += 4;
        } else if (!($i1 == -1 || $i2 == -1)) {
            return -1;
        }
        if (LangUtils.equals((Object) this.host, (Object) authScope.host)) {
            return $i0 + 8;
        }
        String $r33 = this.host;
        String $r23 = ANY_HOST;
        if ($r33 == $r23 || authScope.host == $r23) {
            return $i0;
        }
        return -1;
    }

    public String toString() {
        StringBuilder $r1 = new StringBuilder();
        String $r2 = this.scheme;
        if ($r2 != null) {
            $r1.append($r2.toUpperCase(Locale.ROOT));
            $r1.append(' ');
        }
        if (this.realm != null) {
            $r1.append('\'');
            $r1.append(this.realm);
            $r1.append('\'');
        } else {
            $r1.append("<any realm>");
        }
        if (this.host != null) {
            $r1.append('@');
            $r1.append(this.host);
            if (this.port >= 0) {
                $r1.append(':');
                $r1.append(this.port);
            }
        }
        return $r1.toString();
    }
}
