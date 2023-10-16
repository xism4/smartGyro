package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.Locale;

public class RFC2965VersionAttributeHandler implements Object {
    public String getAttributeName() {
        return "domain";
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        String $r3 = cookieOrigin.getHost();
        String $r4 = cookie.getDomain();
        if ($r4 == null) {
            return false;
        }
        if (!$r3.equals($r4)) {
            return $r4.startsWith(".") && $r3.endsWith($r4);
        }
        return true;
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (!str.trim().isEmpty()) {
            setCookie.setDomain(str);
        } else {
            throw new MalformedCookieException("Blank value for domain attribute");
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        String $r3 = cookieOrigin.getHost();
        String $r4 = cookie.getDomain();
        if ($r4 == null) {
            throw new CookieRestrictionViolationException("Cookie domain may not be null");
        } else if ($r4.equals($r3)) {
        } else {
            if ($r4.indexOf(46) == -1) {
                throw new CookieRestrictionViolationException("Domain attribute \"" + $r4 + "\" does not match the host \"" + $r3 + "\"");
            } else if ($r4.startsWith(".")) {
                int $i0 = $r4.indexOf(46, 1);
                if ($i0 < 0 || $i0 == $r4.length() - 1) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + $r4 + "\" violates RFC 2109: domain must contain an embedded dot");
                }
                String $r32 = $r3.toLowerCase(Locale.ROOT);
                if (!$r32.endsWith($r4)) {
                    throw new CookieRestrictionViolationException("Illegal domain attribute \"" + $r4 + "\". Domain of origin: \"" + $r32 + "\"");
                } else if ($r32.substring(0, $r32.length() - $r4.length()).indexOf(46) != -1) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + $r4 + "\" violates RFC 2109: host minus domain may not contain any dots");
                }
            } else {
                throw new CookieRestrictionViolationException("Domain attribute \"" + $r4 + "\" violates RFC 2109: domain must start with a dot");
            }
        }
    }
}
