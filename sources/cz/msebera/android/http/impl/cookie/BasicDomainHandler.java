package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.conn.socket.InetAddressUtils;
import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import java.util.Locale;

public class BasicDomainHandler implements Object {
    static boolean domainMatch(String $r0, String str) {
        if (InetAddressUtils.isIPv4Address(str) || InetAddressUtils.isIPv6Address(str)) {
            return false;
        }
        if ($r0.startsWith(".")) {
            $r0 = $r0.substring(1);
        }
        if (!str.endsWith($r0)) {
            return false;
        }
        int $i0 = str.length() - $r0.length();
        if ($i0 == 0) {
            return true;
        }
        return $i0 > 1 && str.charAt($i0 - 1) == '.';
    }

    public String getAttributeName() {
        return "domain";
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        String $r3 = cookieOrigin.getHost();
        String $r4 = cookie.getDomain();
        String $r5 = $r4;
        if ($r4 == null) {
            return false;
        }
        if ($r4.startsWith(".")) {
            $r5 = $r4.substring(1);
        }
        String $r42 = $r5.toLowerCase(Locale.ROOT);
        if ($r3.equals($r42)) {
            return true;
        }
        if (!(cookie instanceof ClientCookie) || !((ClientCookie) cookie).containsAttribute("domain")) {
            return false;
        }
        return domainMatch($r42, $r3);
    }

    public void parse(SetCookie setCookie, String $r2) {
        Args.notNull(setCookie, "Cookie");
        if (TextUtils.isBlank($r2)) {
            throw new MalformedCookieException("Blank or null value for domain attribute");
        } else if (!$r2.endsWith(".")) {
            if ($r2.startsWith(".")) {
                $r2 = $r2.substring(1);
            }
            setCookie.setDomain($r2.toLowerCase(Locale.ROOT));
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        String $r4 = cookieOrigin.getHost();
        String $r5 = cookie.getDomain();
        if ($r5 == null) {
            throw new CookieRestrictionViolationException("Cookie 'domain' may not be null");
        } else if (!$r4.equals($r5) && !domainMatch($r5, $r4)) {
            throw new CookieRestrictionViolationException("Illegal 'domain' attribute \"" + $r5 + "\". Domain of origin: \"" + $r4 + "\"");
        }
    }
}
