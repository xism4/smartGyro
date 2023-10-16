package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.Date;

public class BasicCommentHandler extends AbstractCookieAttributeHandler implements Object {
    public String getAttributeName() {
        return "max-age";
    }

    public void parse(SetCookie setCookie, String $r3) {
        Args.notNull(setCookie, "Cookie");
        if ($r3 != null) {
            try {
                int $i0 = Integer.parseInt($r3);
                if ($i0 >= 0) {
                    setCookie.setExpiryDate(new Date(System.currentTimeMillis() + (((long) $i0) * 1000)));
                    return;
                }
                throw new MalformedCookieException("Negative 'max-age' attribute: " + $r3);
            } catch (NumberFormatException e) {
                throw new MalformedCookieException("Invalid 'max-age' attribute: " + $r3);
            }
        } else {
            throw new MalformedCookieException("Missing value for 'max-age' attribute");
        }
    }
}
