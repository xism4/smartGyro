package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.mime.Args;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class CookieSpecBase extends AbstractCookieSpec {
    public CookieSpecBase() {
    }

    protected CookieSpecBase(Object... objectArr) {
        super(objectArr);
    }

    protected static String getDefaultDomain(CookieOrigin cookieOrigin) {
        return cookieOrigin.getHost();
    }

    protected static String getDefaultPath(CookieOrigin cookieOrigin) {
        String $r1 = cookieOrigin.getPath();
        int $i0 = $r1.lastIndexOf(47);
        int $i1 = $i0;
        if ($i0 < 0) {
            return $r1;
        }
        if ($i0 == 0) {
            $i1 = 1;
        }
        return $r1.substring(0, $i1);
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        for (CookieAttributeHandler $r6 : getAttribHandlers()) {
            if (!$r6.match(cookie, cookieOrigin)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public List parse(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        ArrayList $r1 = new ArrayList(headerElementArr.length);
        for (HeaderElement $r4 : headerElementArr) {
            String $r5 = $r4.getName();
            String $r6 = $r4.getValue();
            if ($r5 == null || $r5.isEmpty()) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            BasicClientCookie $r7 = new BasicClientCookie($r5, $r6);
            $r7.setPath(getDefaultPath(cookieOrigin));
            $r7.setDomain(getDefaultDomain(cookieOrigin));
            NameValuePair[] $r8 = $r4.getParameters();
            for (int $i2 = $r8.length - 1; $i2 >= 0; $i2--) {
                NameValuePair $r9 = $r8[$i2];
                String $r52 = $r9.getName().toLowerCase(Locale.ROOT);
                $r7.setAttribute($r52, $r9.getValue());
                CookieAttributeHandler $r11 = findAttribHandler($r52);
                if ($r11 != null) {
                    $r11.parse($r7, $r9.getValue());
                }
            }
            $r1.add($r7);
        }
        return $r1;
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        for (CookieAttributeHandler $r6 : getAttribHandlers()) {
            $r6.validate(cookie, cookieOrigin);
        }
    }
}
