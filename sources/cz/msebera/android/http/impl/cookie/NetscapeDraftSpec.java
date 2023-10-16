package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NetscapeDraftSpec extends CookieSpecBase {
    public NetscapeDraftSpec() {
        this((String[]) null);
    }

    NetscapeDraftSpec(Object... objectArr) {
        super(objectArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: java.lang.String[]} */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NetscapeDraftSpec(java.lang.String[] r11) {
        /*
            r10 = this;
            r1 = 5
            cz.msebera.android.http.cookie.Object[] r0 = new cz.msebera.android.http.cookie.Object[r1]
            cz.msebera.android.http.impl.cookie.BasicPathHandler r2 = new cz.msebera.android.http.impl.cookie.BasicPathHandler
            r2.<init>()
            r1 = 0
            r0[r1] = r2
            cz.msebera.android.http.impl.cookie.NetscapeDomainHandler r3 = new cz.msebera.android.http.impl.cookie.NetscapeDomainHandler
            r3.<init>()
            r1 = 1
            r0[r1] = r3
            cz.msebera.android.http.impl.cookie.BasicSecureHandler r4 = new cz.msebera.android.http.impl.cookie.BasicSecureHandler
            r4.<init>()
            r1 = 2
            r0[r1] = r4
            cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler r5 = new cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler
            r5.<init>()
            r1 = 3
            r0[r1] = r5
            cz.msebera.android.http.impl.cookie.BasicExpiresHandler r6 = new cz.msebera.android.http.impl.cookie.BasicExpiresHandler
            if (r11 == 0) goto L_0x0030
            java.lang.Object r7 = r11.clone()
            r8 = r7
            java.lang.String[] r8 = (java.lang.String[]) r8
            r11 = r8
            goto L_0x0038
        L_0x0030:
            r1 = 1
            java.lang.String[] r11 = new java.lang.String[r1]
            r1 = 0
            java.lang.String r9 = "EEE, dd-MMM-yy HH:mm:ss z"
            r11[r1] = r9
        L_0x0038:
            r6.<init>(r11)
            r1 = 4
            r0[r1] = r6
            r10.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.cookie.NetscapeDraftSpec.<init>(java.lang.String[]):void");
    }

    public List formatCookies(List list) {
        Args.notEmpty((Collection) list, "List of cookies");
        CharArrayBuffer $r2 = new CharArrayBuffer(list.size() * 20);
        $r2.append("Cookie");
        $r2.append(": ");
        for (int $i0 = 0; $i0 < list.size(); $i0++) {
            Cookie $r4 = (Cookie) list.get($i0);
            if ($i0 > 0) {
                $r2.append("; ");
            }
            $r2.append($r4.getName());
            String $r5 = $r4.getValue();
            if ($r5 != null) {
                $r2.append("=");
                $r2.append($r5);
            }
        }
        ArrayList $r6 = new ArrayList(1);
        $r6.add(new BufferedHeader($r2));
        return $r6;
    }

    public int getVersion() {
        return 0;
    }

    public Header getVersionHeader() {
        return null;
    }

    public List parse(Header header, CookieOrigin cookieOrigin) {
        cz.msebera.android.http.message.CharArrayBuffer $r8;
        CharArrayBuffer $r7;
        Args.notNull(header, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase("Set-Cookie")) {
            NetscapeDraftHeaderParser $r4 = NetscapeDraftHeaderParser.DEFAULT;
            if (header instanceof FormattedHeader) {
                FormattedHeader $r5 = (FormattedHeader) header;
                CharArrayBuffer $r6 = $r5.getBuffer();
                $r7 = $r6;
                $r8 = new cz.msebera.android.http.message.CharArrayBuffer($r5.getValuePos(), $r6.length());
            } else {
                String $r3 = header.getValue();
                if ($r3 != null) {
                    $r7 = new CharArrayBuffer($r3.length());
                    $r7.append($r3);
                    $r8 = new cz.msebera.android.http.message.CharArrayBuffer(0, $r7.length());
                } else {
                    throw new MalformedCookieException("Header value is null");
                }
            }
            return parse(new HeaderElement[]{$r4.parseHeader($r7, $r8)}, cookieOrigin);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public String toString() {
        return "netscape";
    }
}
