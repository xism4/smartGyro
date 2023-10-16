package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.client.ssl.DateUtils;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.Date;

public class BasicExpiresHandler extends AbstractCookieAttributeHandler implements Object {
    private final String[] datepatterns;

    public BasicExpiresHandler(String[] strArr) {
        Args.notNull(strArr, "Array of date patterns");
        this.datepatterns = strArr;
    }

    public String getAttributeName() {
        return "expires";
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        if (str != null) {
            Date $r4 = DateUtils.parseDate(str, this.datepatterns);
            if ($r4 != null) {
                setCookie.setExpiryDate($r4);
                return;
            }
            throw new MalformedCookieException("Invalid 'expires' attribute: " + str);
        }
        throw new MalformedCookieException("Missing value for 'expires' attribute");
    }
}
