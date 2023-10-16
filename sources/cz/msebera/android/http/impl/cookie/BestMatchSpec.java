package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie2;
import cz.msebera.android.http.message.CharArrayBuffer;
import cz.msebera.android.http.mime.Args;
import java.util.Iterator;
import java.util.List;

public class BestMatchSpec implements CookieSpec {
    private final RFC2109Spec obsoleteStrict;
    private final NetscapeDraftSpec rfc2109;
    private final RFC2965Spec strict;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: java.lang.String[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BestMatchSpec(java.lang.String[] r24, boolean r25) {
        /*
            r23 = this;
            r0 = r23
            r0.<init>()
            cz.msebera.android.http.impl.cookie.RFC2965Spec r2 = new cz.msebera.android.http.impl.cookie.RFC2965Spec
            r4 = 9
            cz.msebera.android.http.cookie.Object[] r3 = new cz.msebera.android.http.cookie.Object[r4]
            cz.msebera.android.http.impl.cookie.RFC2109DomainHandler r5 = new cz.msebera.android.http.impl.cookie.RFC2109DomainHandler
            r5.<init>()
            r4 = 0
            r3[r4] = r5
            cz.msebera.android.http.impl.cookie.BasicPathHandler r6 = new cz.msebera.android.http.impl.cookie.BasicPathHandler
            r6.<init>()
            r4 = 1
            r3[r4] = r6
            cz.msebera.android.http.impl.cookie.RFC2965DomainAttributeHandler r7 = new cz.msebera.android.http.impl.cookie.RFC2965DomainAttributeHandler
            r7.<init>()
            r4 = 2
            r3[r4] = r7
            cz.msebera.android.http.impl.cookie.RFC2965PortAttributeHandler r8 = new cz.msebera.android.http.impl.cookie.RFC2965PortAttributeHandler
            r8.<init>()
            r4 = 3
            r3[r4] = r8
            cz.msebera.android.http.impl.cookie.BasicCommentHandler r9 = new cz.msebera.android.http.impl.cookie.BasicCommentHandler
            r9.<init>()
            r4 = 4
            r3[r4] = r9
            cz.msebera.android.http.impl.cookie.BasicSecureHandler r10 = new cz.msebera.android.http.impl.cookie.BasicSecureHandler
            r10.<init>()
            r4 = 5
            r3[r4] = r10
            cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler r11 = new cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler
            r11.<init>()
            r4 = 6
            r3[r4] = r11
            cz.msebera.android.http.impl.cookie.RFC2965CommentUrlAttributeHandler r12 = new cz.msebera.android.http.impl.cookie.RFC2965CommentUrlAttributeHandler
            r12.<init>()
            r4 = 7
            r3[r4] = r12
            cz.msebera.android.http.impl.cookie.RFC2965DiscardAttributeHandler r13 = new cz.msebera.android.http.impl.cookie.RFC2965DiscardAttributeHandler
            r13.<init>()
            r4 = 8
            r3[r4] = r13
            r0 = r25
            r2.<init>((boolean) r0, (cz.msebera.android.http.cookie.Object[]) r3)
            r0 = r23
            r0.strict = r2
            cz.msebera.android.http.impl.cookie.RFC2109Spec r14 = new cz.msebera.android.http.impl.cookie.RFC2109Spec
            r4 = 6
            cz.msebera.android.http.cookie.Object[] r3 = new cz.msebera.android.http.cookie.Object[r4]
            cz.msebera.android.http.impl.cookie.RFC2109VersionHandler r15 = new cz.msebera.android.http.impl.cookie.RFC2109VersionHandler
            r15.<init>()
            r4 = 0
            r3[r4] = r15
            cz.msebera.android.http.impl.cookie.BasicPathHandler r6 = new cz.msebera.android.http.impl.cookie.BasicPathHandler
            r6.<init>()
            r4 = 1
            r3[r4] = r6
            cz.msebera.android.http.impl.cookie.RFC2965VersionAttributeHandler r16 = new cz.msebera.android.http.impl.cookie.RFC2965VersionAttributeHandler
            r0 = r16
            r0.<init>()
            r4 = 2
            r3[r4] = r16
            cz.msebera.android.http.impl.cookie.BasicCommentHandler r9 = new cz.msebera.android.http.impl.cookie.BasicCommentHandler
            r9.<init>()
            r4 = 3
            r3[r4] = r9
            cz.msebera.android.http.impl.cookie.BasicSecureHandler r10 = new cz.msebera.android.http.impl.cookie.BasicSecureHandler
            r10.<init>()
            r4 = 4
            r3[r4] = r10
            cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler r11 = new cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler
            r11.<init>()
            r4 = 5
            r3[r4] = r11
            r0 = r25
            r14.<init>((boolean) r0, (cz.msebera.android.http.cookie.Object[]) r3)
            r0 = r23
            r0.obsoleteStrict = r14
            cz.msebera.android.http.impl.cookie.NetscapeDraftSpec r17 = new cz.msebera.android.http.impl.cookie.NetscapeDraftSpec
            r4 = 5
            cz.msebera.android.http.cookie.Object[] r3 = new cz.msebera.android.http.cookie.Object[r4]
            cz.msebera.android.http.impl.cookie.BasicDomainHandler r18 = new cz.msebera.android.http.impl.cookie.BasicDomainHandler
            r0 = r18
            r0.<init>()
            r4 = 0
            r3[r4] = r18
            cz.msebera.android.http.impl.cookie.BasicPathHandler r6 = new cz.msebera.android.http.impl.cookie.BasicPathHandler
            r6.<init>()
            r4 = 1
            r3[r4] = r6
            cz.msebera.android.http.impl.cookie.BasicSecureHandler r10 = new cz.msebera.android.http.impl.cookie.BasicSecureHandler
            r10.<init>()
            r4 = 2
            r3[r4] = r10
            cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler r11 = new cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler
            r11.<init>()
            r4 = 3
            r3[r4] = r11
            cz.msebera.android.http.impl.cookie.BasicExpiresHandler r19 = new cz.msebera.android.http.impl.cookie.BasicExpiresHandler
            if (r24 == 0) goto L_0x00d5
            r0 = r24
            java.lang.Object r20 = r0.clone()
            r21 = r20
            java.lang.String[] r21 = (java.lang.String[]) r21
            r24 = r21
            goto L_0x00df
        L_0x00d5:
            r4 = 1
            java.lang.String[] r0 = new java.lang.String[r4]
            r24 = r0
            r4 = 0
            java.lang.String r22 = "EEE, dd-MMM-yy HH:mm:ss z"
            r24[r4] = r22
        L_0x00df:
            r0 = r19
            r1 = r24
            r0.<init>(r1)
            r4 = 4
            r3[r4] = r19
            r0 = r17
            r0.<init>((cz.msebera.android.http.cookie.Object[]) r3)
            r0 = r17
            r1 = r23
            r1.rfc2109 = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.cookie.BestMatchSpec.<init>(java.lang.String[], boolean):void");
    }

    public List formatCookies(List $r1) {
        Args.notNull($r1, "List of cookies");
        Iterator $r2 = $r1.iterator();
        int $i0 = Integer.MAX_VALUE;
        boolean $z0 = true;
        while ($r2.hasNext()) {
            Cookie $r4 = (Cookie) $r2.next();
            if (!($r4 instanceof SetCookie2)) {
                $z0 = false;
            }
            if ($r4.getVersion() < $i0) {
                $i0 = $r4.getVersion();
            }
        }
        return $i0 > 0 ? $z0 ? this.strict.formatCookies($r1) : this.obsoleteStrict.formatCookies($r1) : this.rfc2109.formatCookies($r1);
    }

    public int getVersion() {
        return this.strict.getVersion();
    }

    public Header getVersionHeader() {
        return null;
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        return cookie.getVersion() > 0 ? cookie instanceof SetCookie2 ? this.strict.match(cookie, cookieOrigin) : this.obsoleteStrict.match(cookie, cookieOrigin) : this.rfc2109.match(cookie, cookieOrigin);
    }

    public List parse(Header header, CookieOrigin cookieOrigin) {
        CharArrayBuffer $r15;
        cz.msebera.android.http.mime.CharArrayBuffer $r14;
        Args.notNull(header, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        HeaderElement[] $r3 = header.getElements();
        boolean $z0 = false;
        boolean $z1 = false;
        for (HeaderElement $r4 : $r3) {
            if ($r4.getParameterByName("version") != null) {
                $z1 = true;
            }
            if ($r4.getParameterByName("expires") != null) {
                $z0 = true;
            }
        }
        if (!$z0 && $z1) {
            return "Set-Cookie2".equals(header.getName()) ? this.strict.parse($r3, cookieOrigin) : this.obsoleteStrict.parse($r3, cookieOrigin);
        }
        NetscapeDraftHeaderParser $r11 = NetscapeDraftHeaderParser.DEFAULT;
        if (header instanceof FormattedHeader) {
            FormattedHeader $r12 = (FormattedHeader) header;
            cz.msebera.android.http.mime.CharArrayBuffer $r13 = $r12.getBuffer();
            $r14 = $r13;
            $r15 = new CharArrayBuffer($r12.getValuePos(), $r13.length());
        } else {
            String $r6 = header.getValue();
            if ($r6 != null) {
                $r14 = new cz.msebera.android.http.mime.CharArrayBuffer($r6.length());
                $r14.append($r6);
                $r15 = new CharArrayBuffer(0, $r14.length());
            } else {
                throw new MalformedCookieException("Header value is null");
            }
        }
        HeaderElement[] $r32 = {$r11.parseHeader($r14, $r15)};
        NetscapeDraftSpec $r16 = this.rfc2109;
        NetscapeDraftSpec netscapeDraftSpec = $r16;
        return $r16.parse($r32, cookieOrigin);
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        if (cookie.getVersion() <= 0) {
            this.rfc2109.validate(cookie, cookieOrigin);
        } else if (cookie instanceof SetCookie2) {
            this.strict.validate(cookie, cookieOrigin);
        } else {
            this.obsoleteStrict.validate(cookie, cookieOrigin);
        }
    }
}
