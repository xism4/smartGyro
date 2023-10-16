package cz.msebera.android.http.cookie;

import c.a.a.a.f.c;
import java.io.Serializable;
import java.util.Comparator;

public class CookiePathComparator implements Serializable, Comparator<c> {
    public static final CookiePathComparator INSTANCE = new CookiePathComparator();

    private String normalizePath(Cookie cookie) {
        String $r2 = cookie.getPath();
        String $r3 = $r2;
        if ($r2 == null) {
            $r3 = "/";
        }
        if ($r3.endsWith("/")) {
            return $r3;
        }
        return $r3 + '/';
    }

    public int compare(Cookie cookie, Cookie cookie2) {
        String $r3 = normalizePath(cookie);
        String $r4 = normalizePath(cookie2);
        if ($r3.equals($r4)) {
            return 0;
        }
        if ($r3.startsWith($r4)) {
            return -1;
        }
        return $r4.startsWith($r3) ? 1 : 0;
    }
}
