package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.message.BasicHeaderElement;
import cz.msebera.android.http.message.BasicHeaderValueFormatter;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BrowserCompatSpec extends CookieSpecBase {
    private static final String[] DATE_PATTERNS = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"};

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: cz.msebera.android.http.impl.cookie.BasicPathHandler} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: cz.msebera.android.http.impl.cookie.BrowserCompatSpec$1} */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BrowserCompatSpec(java.lang.String[] r17, cz.msebera.android.http.impl.cookie.MathArrays$OrderDirection r18) {
        /*
            r16 = this;
            r2 = 7
            cz.msebera.android.http.cookie.Object[] r1 = new cz.msebera.android.http.cookie.Object[r2]
            cz.msebera.android.http.impl.cookie.BrowserCompatVersionAttributeHandler r3 = new cz.msebera.android.http.impl.cookie.BrowserCompatVersionAttributeHandler
            r3.<init>()
            r2 = 0
            r1[r2] = r3
            cz.msebera.android.http.impl.cookie.BasicDomainHandler r4 = new cz.msebera.android.http.impl.cookie.BasicDomainHandler
            r4.<init>()
            r2 = 1
            r1[r2] = r4
            cz.msebera.android.http.impl.cookie.MathArrays$OrderDirection r5 = cz.msebera.android.http.impl.cookie.MathArrays$OrderDirection.SECURITYLEVEL_IE_MEDIUM
            r0 = r18
            if (r0 != r5) goto L_0x0020
            cz.msebera.android.http.impl.cookie.BrowserCompatSpec$1 r6 = new cz.msebera.android.http.impl.cookie.BrowserCompatSpec$1
            r7 = r6
            r6.<init>()
            goto L_0x0026
        L_0x0020:
            cz.msebera.android.http.impl.cookie.BasicPathHandler r8 = new cz.msebera.android.http.impl.cookie.BasicPathHandler
            r7 = r8
            r8.<init>()
        L_0x0026:
            r2 = 2
            r1[r2] = r7
            cz.msebera.android.http.impl.cookie.BasicCommentHandler r9 = new cz.msebera.android.http.impl.cookie.BasicCommentHandler
            r9.<init>()
            r2 = 3
            r1[r2] = r9
            cz.msebera.android.http.impl.cookie.BasicSecureHandler r10 = new cz.msebera.android.http.impl.cookie.BasicSecureHandler
            r10.<init>()
            r2 = 4
            r1[r2] = r10
            cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler r11 = new cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler
            r11.<init>()
            r2 = 5
            r1[r2] = r11
            cz.msebera.android.http.impl.cookie.BasicExpiresHandler r12 = new cz.msebera.android.http.impl.cookie.BasicExpiresHandler
            r13 = r12
            if (r17 == 0) goto L_0x0053
            r0 = r17
            java.lang.Object r14 = r0.clone()
            r15 = r14
            java.lang.String[] r15 = (java.lang.String[]) r15
            r17 = r15
            goto L_0x0055
        L_0x0053:
            java.lang.String[] r17 = DATE_PATTERNS
        L_0x0055:
            r0 = r17
            r12.<init>(r0)
            r2 = 6
            r1[r2] = r13
            r0 = r16
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.cookie.BrowserCompatSpec.<init>(java.lang.String[], cz.msebera.android.http.impl.cookie.MathArrays$OrderDirection):void");
    }

    private static boolean isQuoteEnclosed(String str) {
        return str != null && str.startsWith("\"") && str.endsWith("\"");
    }

    public List formatCookies(List list) {
        Args.notEmpty((Collection) list, "List of cookies");
        CharArrayBuffer $r3 = new CharArrayBuffer(list.size() * 20);
        $r3.append("Cookie");
        $r3.append(": ");
        for (int $i0 = 0; $i0 < list.size(); $i0++) {
            Cookie $r5 = (Cookie) list.get($i0);
            if ($i0 > 0) {
                $r3.append("; ");
            }
            String $r6 = $r5.getName();
            String $r7 = $r5.getValue();
            if ($r5.getVersion() <= 0 || isQuoteEnclosed($r7)) {
                $r3.append($r6);
                $r3.append("=");
                if ($r7 != null) {
                    $r3.append($r7);
                }
            } else {
                BasicHeaderValueFormatter.INSTANCE.formatHeaderElement($r3, new BasicHeaderElement($r6, $r7), false);
            }
        }
        ArrayList $r9 = new ArrayList(1);
        $r9.add(new BufferedHeader($r3));
        return $r9;
    }

    public int getVersion() {
        return 0;
    }

    public Header getVersionHeader() {
        return null;
    }

    public List parse(Header header, CookieOrigin cookieOrigin) {
        cz.msebera.android.http.message.CharArrayBuffer $r12;
        CharArrayBuffer $r11;
        Args.notNull(header, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase("Set-Cookie")) {
            HeaderElement[] $r4 = header.getElements();
            boolean $z0 = false;
            boolean $z1 = false;
            for (HeaderElement $r5 : $r4) {
                if ($r5.getParameterByName("version") != null) {
                    $z1 = true;
                }
                if ($r5.getParameterByName("expires") != null) {
                    $z0 = true;
                }
            }
            if (!$z0 && $z1) {
                return parse($r4, cookieOrigin);
            }
            NetscapeDraftHeaderParser $r8 = NetscapeDraftHeaderParser.DEFAULT;
            if (header instanceof FormattedHeader) {
                FormattedHeader $r9 = (FormattedHeader) header;
                CharArrayBuffer $r10 = $r9.getBuffer();
                $r11 = $r10;
                $r12 = new cz.msebera.android.http.message.CharArrayBuffer($r9.getValuePos(), $r10.length());
            } else {
                String $r3 = header.getValue();
                if ($r3 != null) {
                    $r11 = new CharArrayBuffer($r3.length());
                    $r11.append($r3);
                    $r12 = new cz.msebera.android.http.message.CharArrayBuffer(0, $r11.length());
                } else {
                    throw new MalformedCookieException("Header value is null");
                }
            }
            HeaderElement $r52 = $r8.parseHeader($r11, $r12);
            String $r32 = $r52.getName();
            String $r13 = $r52.getValue();
            if ($r32 == null || $r32.isEmpty()) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            BasicClientCookie basicClientCookie = new BasicClientCookie($r32, $r13);
            basicClientCookie.setPath(CookieSpecBase.getDefaultPath(cookieOrigin));
            basicClientCookie.setDomain(CookieSpecBase.getDefaultDomain(cookieOrigin));
            NameValuePair[] $r15 = $r52.getParameters();
            for (int $i0 = $r15.length - 1; $i0 >= 0; $i0--) {
                NameValuePair $r6 = $r15[$i0];
                String $r33 = $r6.getName().toLowerCase(Locale.ROOT);
                basicClientCookie.setAttribute($r33, $r6.getValue());
                CookieAttributeHandler $r17 = findAttribHandler($r33);
                if ($r17 != null) {
                    $r17.parse(basicClientCookie, $r6.getValue());
                }
            }
            if ($z0) {
                basicClientCookie.setVersion(0);
            }
            return Collections.singletonList(basicClientCookie);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public String toString() {
        return "compatibility";
    }
}
