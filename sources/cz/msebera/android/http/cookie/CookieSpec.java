package cz.msebera.android.http.cookie;

import cz.msebera.android.http.Header;
import java.util.List;

public interface CookieSpec {
    List formatCookies(List list);

    int getVersion();

    Header getVersionHeader();

    boolean match(Cookie cookie, CookieOrigin cookieOrigin);

    List parse(Header header, CookieOrigin cookieOrigin);

    void validate(Cookie cookie, CookieOrigin cookieOrigin);
}
