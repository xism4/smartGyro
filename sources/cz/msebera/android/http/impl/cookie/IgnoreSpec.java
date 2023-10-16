package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.cookie.CookieOrigin;
import java.util.Collections;
import java.util.List;

public class IgnoreSpec extends CookieSpecBase {
    public List formatCookies(List list) {
        return Collections.emptyList();
    }

    public int getVersion() {
        return 0;
    }

    public Header getVersionHeader() {
        return null;
    }

    public List parse(Header header, CookieOrigin cookieOrigin) {
        return Collections.emptyList();
    }
}
