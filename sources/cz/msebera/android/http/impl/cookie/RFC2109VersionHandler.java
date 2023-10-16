package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;

public class RFC2109VersionHandler extends AbstractCookieAttributeHandler implements Object {
    public String getAttributeName() {
        return "version";
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for version attribute");
        } else if (!str.trim().isEmpty()) {
            try {
                setCookie.setVersion(Integer.parseInt(str));
            } catch (NumberFormatException $r4) {
                throw new MalformedCookieException("Invalid version: " + $r4.getMessage());
            }
        } else {
            throw new MalformedCookieException("Blank value for version attribute");
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        if (cookie.getVersion() < 0) {
            throw new CookieRestrictionViolationException("Cookie version may not be negative");
        }
    }
}
