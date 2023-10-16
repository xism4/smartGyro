package cz.msebera.android.http.cookie;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import java.util.Locale;

public final class CookieOrigin {
    private final String host;
    private final String path;
    private final int port;
    private final boolean secure;

    public CookieOrigin(String str, int i, String str2, boolean z) {
        Args.notBlank(str, "Host");
        Args.notNegative(i, "Port");
        Args.notNull(str2, "Path");
        this.host = str.toLowerCase(Locale.ROOT);
        this.port = i;
        if (!TextUtils.isBlank(str2)) {
            this.path = str2;
        } else {
            this.path = "/";
        }
        this.secure = z;
    }

    public String getHost() {
        return this.host;
    }

    public String getPath() {
        return this.path;
    }

    public int getPort() {
        return this.port;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public String toString() {
        StringBuilder $r1 = new StringBuilder();
        $r1.append('[');
        if (this.secure) {
            $r1.append("(secure)");
        }
        $r1.append(this.host);
        $r1.append(':');
        $r1.append(Integer.toString(this.port));
        $r1.append(this.path);
        $r1.append(']');
        return $r1.toString();
    }
}
