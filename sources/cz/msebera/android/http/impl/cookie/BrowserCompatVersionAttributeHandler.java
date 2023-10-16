package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;

public class BrowserCompatVersionAttributeHandler extends AbstractCookieAttributeHandler implements Object {
    public String getAttributeName() {
        return "version";
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        if (str != null) {
            int $i0 = 0;
            try {
                $i0 = Integer.parseInt(str);
            } catch (NumberFormatException e) {
            }
            setCookie.setVersion($i0);
            return;
        }
        throw new MalformedCookieException("Missing value for version attribute");
    }
}
