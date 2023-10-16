package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import java.util.Locale;
import java.util.StringTokenizer;

public class NetscapeDomainHandler extends BasicDomainHandler {
    private static boolean isSpecialDomain(String str) {
        String $r0 = str.toUpperCase(Locale.ROOT);
        return $r0.endsWith(".COM") || $r0.endsWith(".EDU") || $r0.endsWith(".NET") || $r0.endsWith(".GOV") || $r0.endsWith(".MIL") || $r0.endsWith(".ORG") || $r0.endsWith(".INT");
    }

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
        return $r3.endsWith($r4);
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        if (!TextUtils.isBlank(str)) {
            setCookie.setDomain(str);
            return;
        }
        throw new MalformedCookieException("Blank or null value for domain attribute");
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        String $r3 = cookieOrigin.getHost();
        String $r4 = cookie.getDomain();
        if (!$r3.equals($r4) && !BasicDomainHandler.domainMatch($r4, $r3)) {
            throw new CookieRestrictionViolationException("Illegal domain attribute \"" + $r4 + "\". Domain of origin: \"" + $r3 + "\"");
        } else if ($r3.contains(".")) {
            int $i0 = new StringTokenizer($r4, ".").countTokens();
            if (isSpecialDomain($r4)) {
                if ($i0 < 2) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + $r4 + "\" violates the Netscape cookie specification for " + "special domains");
                }
            } else if ($i0 < 3) {
                throw new CookieRestrictionViolationException("Domain attribute \"" + $r4 + "\" violates the Netscape cookie specification");
            }
        }
    }
}
