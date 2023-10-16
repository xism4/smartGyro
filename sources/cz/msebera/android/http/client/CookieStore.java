package cz.msebera.android.http.client;

import cz.msebera.android.http.cookie.Cookie;
import java.util.Date;
import java.util.List;

public interface CookieStore {
    void addCookie(Cookie cookie);

    boolean clearExpired(Date date);

    List getCookies();
}
