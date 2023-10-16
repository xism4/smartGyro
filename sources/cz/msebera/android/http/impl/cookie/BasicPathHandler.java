package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;

public class BasicPathHandler implements Object {
    static boolean pathMatch(String str, String $r1) {
        if ($r1 == null) {
            $r1 = "/";
        }
        if ($r1.length() > 1 && $r1.endsWith("/")) {
            $r1 = $r1.substring(0, $r1.length() - 1);
        }
        if (str.startsWith($r1)) {
            return $r1.equals("/") || str.length() == $r1.length() || str.charAt($r1.length()) == '/';
        }
        return false;
    }

    public String getAttributeName() {
        return "path";
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        return pathMatch(cookieOrigin.getPath(), cookie.getPath());
    }

    public void parse(SetCookie setCookie, String $r2) {
        Args.notNull(setCookie, "Cookie");
        if (TextUtils.isBlank($r2)) {
            $r2 = "/";
        }
        setCookie.setPath($r2);
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        if (!match(cookie, cookieOrigin)) {
            throw new CookieRestrictionViolationException("Illegal 'path' attribute \"" + cookie.getPath() + "\". Path of origin: \"" + cookieOrigin.getPath() + "\"");
        }
    }
}
