package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;

public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler implements Object {
    public String getAttributeName() {
        return "comment";
    }

    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, "Cookie");
        setCookie.setComment(str);
    }
}
