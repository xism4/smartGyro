package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieOrigin;

public abstract class AbstractCookieAttributeHandler implements CookieAttributeHandler {
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        return true;
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
    }
}
