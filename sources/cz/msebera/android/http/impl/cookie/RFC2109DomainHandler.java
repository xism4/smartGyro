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

public class RFC2109DomainHandler implements Object {
    public String getAttributeName() {
        return "version";
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        return true;
    }

    public void parse(SetCookie setCookie, String str) {
        int $i0;
        Args.notNull(setCookie, "Cookie");
        if (str != null) {
            try {
                $i0 = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                $i0 = -1;
            }
            if ($i0 >= 0) {
                setCookie.setVersion($i0);
                return;
            }
            throw new MalformedCookieException("Invalid cookie version.");
        }
        throw new MalformedCookieException("Missing value for version attribute");
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        if ((cookie instanceof SetCookie2) && (cookie instanceof ClientCookie) && !((ClientCookie) cookie).containsAttribute("version")) {
            throw new CookieRestrictionViolationException("Violates RFC 2965. Version attribute is required.");
        }
    }
}
