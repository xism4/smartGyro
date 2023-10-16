package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.cookie.SetCookie2;
import cz.msebera.android.http.mime.Args;
import java.util.StringTokenizer;

public class RFC2965PortAttributeHandler implements Object {
    private static int[] parsePortAttribute(String str) {
        StringTokenizer $r1 = new StringTokenizer(str, ",");
        int[] $r2 = new int[$r1.countTokens()];
        int $i0 = 0;
        while ($r1.hasMoreTokens()) {
            try {
                $r2[$i0] = Integer.parseInt($r1.nextToken().trim());
                if ($r2[$i0] >= 0) {
                    $i0++;
                } else {
                    throw new MalformedCookieException("Invalid Port attribute.");
                }
            } catch (NumberFormatException $r4) {
                throw new MalformedCookieException("Invalid Port attribute: " + $r4.getMessage());
            }
        }
        return $r2;
    }

    private static boolean portMatch(int i, int[] iArr) {
        for (int $i2 : iArr) {
            if (i == $i2) {
                return true;
            }
        }
        return false;
    }

    public String getAttributeName() {
        return "port";
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        int $i0 = cookieOrigin.getPort();
        if (!(cookie instanceof ClientCookie) || !((ClientCookie) cookie).containsAttribute("port")) {
            return true;
        }
        return cookie.getPorts() != null && portMatch($i0, cookie.getPorts());
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        if (setCookie instanceof SetCookie2) {
            SetCookie2 $r3 = (SetCookie2) setCookie;
            if (str != null && !str.trim().isEmpty()) {
                $r3.setPorts(parsePortAttribute(str));
            }
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        int $i0 = cookieOrigin.getPort();
        if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute("port") && !portMatch($i0, cookie.getPorts())) {
            throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
        }
    }
}
