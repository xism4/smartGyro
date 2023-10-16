package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RFC2965Spec extends RFC2109Spec {
    public RFC2965Spec() {
        this((String[]) null, false);
    }

    RFC2965Spec(boolean z, Object... objectArr) {
        super(z, objectArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: java.lang.String[]} */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RFC2965Spec(java.lang.String[] r16, boolean r17) {
        /*
            r15 = this;
            r2 = 10
            cz.msebera.android.http.cookie.Object[] r1 = new cz.msebera.android.http.cookie.Object[r2]
            cz.msebera.android.http.impl.cookie.RFC2109DomainHandler r3 = new cz.msebera.android.http.impl.cookie.RFC2109DomainHandler
            r3.<init>()
            r2 = 0
            r1[r2] = r3
            cz.msebera.android.http.impl.cookie.BasicPathHandler r4 = new cz.msebera.android.http.impl.cookie.BasicPathHandler
            r4.<init>()
            r2 = 1
            r1[r2] = r4
            cz.msebera.android.http.impl.cookie.RFC2965DomainAttributeHandler r5 = new cz.msebera.android.http.impl.cookie.RFC2965DomainAttributeHandler
            r5.<init>()
            r2 = 2
            r1[r2] = r5
            cz.msebera.android.http.impl.cookie.RFC2965PortAttributeHandler r6 = new cz.msebera.android.http.impl.cookie.RFC2965PortAttributeHandler
            r6.<init>()
            r2 = 3
            r1[r2] = r6
            cz.msebera.android.http.impl.cookie.BasicCommentHandler r7 = new cz.msebera.android.http.impl.cookie.BasicCommentHandler
            r7.<init>()
            r2 = 4
            r1[r2] = r7
            cz.msebera.android.http.impl.cookie.BasicSecureHandler r8 = new cz.msebera.android.http.impl.cookie.BasicSecureHandler
            r8.<init>()
            r2 = 5
            r1[r2] = r8
            cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler r9 = new cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler
            r9.<init>()
            r2 = 6
            r1[r2] = r9
            cz.msebera.android.http.impl.cookie.BasicExpiresHandler r10 = new cz.msebera.android.http.impl.cookie.BasicExpiresHandler
            if (r16 == 0) goto L_0x004c
            r0 = r16
            java.lang.Object r11 = r0.clone()
            r12 = r11
            java.lang.String[] r12 = (java.lang.String[]) r12
            r16 = r12
            goto L_0x004e
        L_0x004c:
            java.lang.String[] r16 = cz.msebera.android.http.impl.cookie.RFC2109Spec.DATE_PATTERNS
        L_0x004e:
            r0 = r16
            r10.<init>(r0)
            r2 = 7
            r1[r2] = r10
            cz.msebera.android.http.impl.cookie.RFC2965CommentUrlAttributeHandler r13 = new cz.msebera.android.http.impl.cookie.RFC2965CommentUrlAttributeHandler
            r13.<init>()
            r2 = 8
            r1[r2] = r13
            cz.msebera.android.http.impl.cookie.RFC2965DiscardAttributeHandler r14 = new cz.msebera.android.http.impl.cookie.RFC2965DiscardAttributeHandler
            r14.<init>()
            r2 = 9
            r1[r2] = r14
            r0 = r17
            r15.<init>((boolean) r0, (cz.msebera.android.http.cookie.Object[]) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.cookie.RFC2965Spec.<init>(java.lang.String[], boolean):void");
    }

    private static CookieOrigin adjustEffectiveHost(CookieOrigin cookieOrigin) {
        String $r1 = cookieOrigin.getHost();
        boolean $z0 = false;
        int $i0 = 0;
        while (true) {
            if ($i0 >= $r1.length()) {
                $z0 = true;
                break;
            }
            char $c2 = $r1.charAt($i0);
            if ($c2 == '.' || $c2 == ':') {
                break;
            }
            $i0++;
        }
        if (!$z0) {
            return cookieOrigin;
        }
        return new CookieOrigin($r1 + ".local", cookieOrigin.getPort(), cookieOrigin.getPath(), cookieOrigin.isSecure());
    }

    private List createCookies(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        ArrayList $r1 = new ArrayList(headerElementArr.length);
        for (HeaderElement $r5 : headerElementArr) {
            String $r6 = $r5.getName();
            String $r7 = $r5.getValue();
            if ($r6 == null || $r6.isEmpty()) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            BasicClientCookie2 $r8 = new BasicClientCookie2($r6, $r7);
            $r8.setPath(CookieSpecBase.getDefaultPath(cookieOrigin));
            $r8.setDomain(CookieSpecBase.getDefaultDomain(cookieOrigin));
            $r8.setPorts(new int[]{cookieOrigin.getPort()});
            NameValuePair[] $r10 = $r5.getParameters();
            HashMap $r11 = new HashMap($r10.length);
            for (int $i2 = $r10.length - 1; $i2 >= 0; $i2--) {
                NameValuePair $r12 = $r10[$i2];
                $r11.put($r12.getName().toLowerCase(Locale.ROOT), $r12);
            }
            for (Map.Entry $r16 : $r11.entrySet()) {
                NameValuePair $r122 = (NameValuePair) $r16.getValue();
                String $r62 = $r122.getName().toLowerCase(Locale.ROOT);
                $r8.setAttribute($r62, $r122.getValue());
                CookieAttributeHandler $r17 = findAttribHandler($r62);
                if ($r17 != null) {
                    $r17.parse($r8, $r122.getValue());
                }
            }
            $r1.add($r8);
        }
        return $r1;
    }

    /* access modifiers changed from: protected */
    public void formatCookieAsVer(CharArrayBuffer charArrayBuffer, Cookie cookie, int i) {
        String $r4;
        int[] $r5;
        super.formatCookieAsVer(charArrayBuffer, cookie, i);
        if ((cookie instanceof ClientCookie) && ($r4 = ((ClientCookie) cookie).getAttribute("port")) != null) {
            charArrayBuffer.append("; $Port");
            charArrayBuffer.append("=\"");
            if (!$r4.trim().isEmpty() && ($r5 = cookie.getPorts()) != null) {
                int $i0 = $r5.length;
                for (int $i1 = 0; $i1 < $i0; $i1++) {
                    if ($i1 > 0) {
                        charArrayBuffer.append(",");
                    }
                    charArrayBuffer.append(Integer.toString($r5[$i1]));
                }
            }
            charArrayBuffer.append("\"");
        }
    }

    public int getVersion() {
        return 1;
    }

    public Header getVersionHeader() {
        CharArrayBuffer $r1 = new CharArrayBuffer(40);
        $r1.append("Cookie2");
        $r1.append(": ");
        $r1.append("$Version=");
        $r1.append(Integer.toString(getVersion()));
        return new BufferedHeader($r1);
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        return super.match(cookie, adjustEffectiveHost(cookieOrigin));
    }

    public List parse(Header header, CookieOrigin cookieOrigin) {
        Args.notNull(header, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase("Set-Cookie2")) {
            return createCookies(header.getElements(), adjustEffectiveHost(cookieOrigin));
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    /* access modifiers changed from: protected */
    public List parse(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        return createCookies(headerElementArr, adjustEffectiveHost(cookieOrigin));
    }

    public String toString() {
        return "rfc2965";
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        super.validate(cookie, adjustEffectiveHost(cookieOrigin));
    }
}
