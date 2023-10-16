package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.Locale;

public class RFC2965DomainAttributeHandler implements Object {
    public boolean domainMatch(String str, String str2) {
        if (!str.equals(str2)) {
            return str2.startsWith(".") && str.endsWith(str2);
        }
        return true;
    }

    public String getAttributeName() {
        return "domain";
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        String $r3 = cookieOrigin.getHost().toLowerCase(Locale.ROOT);
        String $r5 = cookie.getDomain();
        return domainMatch($r3, $r5) && $r3.substring(0, $r3.length() - $r5.length()).indexOf(46) == -1;
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (!str.trim().isEmpty()) {
            String $r3 = str.toLowerCase(Locale.ROOT);
            String $r5 = $r3;
            if (!str.startsWith(".")) {
                $r5 = '.' + $r3;
            }
            setCookie.setDomain($r5);
        } else {
            throw new MalformedCookieException("Blank value for domain attribute");
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        String $r3 = cookieOrigin.getHost().toLowerCase(Locale.ROOT);
        if (cookie.getDomain() != null) {
            String $r5 = cookie.getDomain().toLowerCase(Locale.ROOT);
            if (!(cookie instanceof ClientCookie) || !((ClientCookie) cookie).containsAttribute("domain")) {
                if (!cookie.getDomain().equals($r3)) {
                    throw new CookieRestrictionViolationException("Illegal domain attribute: \"" + cookie.getDomain() + "\"." + "Domain of origin: \"" + $r3 + "\"");
                }
            } else if ($r5.startsWith(".")) {
                int $i0 = $r5.indexOf(46, 1);
                if (($i0 < 0 || $i0 == $r5.length() - 1) && !$r5.equals(".local")) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2965: the value contains no embedded dots " + "and the value is not .local");
                } else if (!domainMatch($r3, $r5)) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2965: effective host name does not " + "domain-match domain attribute.");
                } else if ($r3.substring(0, $r3.length() - $r5.length()).indexOf(46) != -1) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2965: " + "effective host minus domain may not contain any dots");
                }
            } else {
                throw new CookieRestrictionViolationException("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2109: domain must start with a dot");
            }
        } else {
            throw new CookieRestrictionViolationException("Invalid cookie state: domain not specified");
        }
    }
}
