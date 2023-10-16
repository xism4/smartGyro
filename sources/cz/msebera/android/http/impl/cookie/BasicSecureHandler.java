package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;

public class BasicSecureHandler extends AbstractCookieAttributeHandler implements Object {
    public String getAttributeName() {
        return "secure";
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        return !cookie.isSecure() || cookieOrigin.isSecure();
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        setCookie.setSecure(true);
    }
}
