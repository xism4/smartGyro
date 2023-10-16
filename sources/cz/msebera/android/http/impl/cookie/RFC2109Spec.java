package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookiePathComparator;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RFC2109Spec extends CookieSpecBase {
    static final String[] DATE_PATTERNS = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};
    private final boolean oneHeader;

    public RFC2109Spec() {
        this((String[]) null, false);
    }

    protected RFC2109Spec(boolean z, Object... objectArr) {
        super(objectArr);
        this.oneHeader = z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: java.lang.String[]} */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RFC2109Spec(java.lang.String[] r12, boolean r13) {
        /*
            r11 = this;
            r1 = 7
            cz.msebera.android.http.cookie.Object[] r0 = new cz.msebera.android.http.cookie.Object[r1]
            cz.msebera.android.http.impl.cookie.RFC2109VersionHandler r2 = new cz.msebera.android.http.impl.cookie.RFC2109VersionHandler
            r2.<init>()
            r1 = 0
            r0[r1] = r2
            cz.msebera.android.http.impl.cookie.BasicPathHandler r3 = new cz.msebera.android.http.impl.cookie.BasicPathHandler
            r3.<init>()
            r1 = 1
            r0[r1] = r3
            cz.msebera.android.http.impl.cookie.RFC2965VersionAttributeHandler r4 = new cz.msebera.android.http.impl.cookie.RFC2965VersionAttributeHandler
            r4.<init>()
            r1 = 2
            r0[r1] = r4
            cz.msebera.android.http.impl.cookie.BasicCommentHandler r5 = new cz.msebera.android.http.impl.cookie.BasicCommentHandler
            r5.<init>()
            r1 = 3
            r0[r1] = r5
            cz.msebera.android.http.impl.cookie.BasicSecureHandler r6 = new cz.msebera.android.http.impl.cookie.BasicSecureHandler
            r6.<init>()
            r1 = 4
            r0[r1] = r6
            cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler r7 = new cz.msebera.android.http.impl.cookie.BasicMaxAgeHandler
            r7.<init>()
            r1 = 5
            r0[r1] = r7
            cz.msebera.android.http.impl.cookie.BasicExpiresHandler r8 = new cz.msebera.android.http.impl.cookie.BasicExpiresHandler
            if (r12 == 0) goto L_0x0040
            java.lang.Object r9 = r12.clone()
            r10 = r9
            java.lang.String[] r10 = (java.lang.String[]) r10
            r12 = r10
            goto L_0x0042
        L_0x0040:
            java.lang.String[] r12 = DATE_PATTERNS
        L_0x0042:
            r8.<init>(r12)
            r1 = 6
            r0[r1] = r8
            r11.<init>(r0)
            r11.oneHeader = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.cookie.RFC2109Spec.<init>(java.lang.String[], boolean):void");
    }

    private List doFormatManyHeaders(List list) {
        ArrayList $r1 = new ArrayList(list.size());
        Iterator $r4 = list.iterator();
        while ($r4.hasNext()) {
            Cookie $r6 = (Cookie) $r4.next();
            int $i0 = $r6.getVersion();
            CharArrayBuffer $r2 = new CharArrayBuffer(40);
            $r2.append("Cookie: ");
            $r2.append("$Version=");
            $r2.append(Integer.toString($i0));
            $r2.append("; ");
            formatCookieAsVer($r2, $r6, $i0);
            $r1.add(new BufferedHeader($r2));
        }
        return $r1;
    }

    private List doFormatOneHeader(List list) {
        Iterator $r2 = list.iterator();
        int $i0 = Integer.MAX_VALUE;
        while ($r2.hasNext()) {
            Cookie $r4 = (Cookie) $r2.next();
            if ($r4.getVersion() < $i0) {
                $i0 = $r4.getVersion();
            }
        }
        CharArrayBuffer $r5 = new CharArrayBuffer(list.size() * 40);
        $r5.append("Cookie");
        $r5.append(": ");
        $r5.append("$Version=");
        $r5.append(Integer.toString($i0));
        Iterator $r22 = list.iterator();
        while ($r22.hasNext()) {
            $r5.append("; ");
            formatCookieAsVer($r5, (Cookie) $r22.next(), $i0);
        }
        ArrayList $r7 = new ArrayList(1);
        $r7.add(new BufferedHeader($r5));
        return $r7;
    }

    /* access modifiers changed from: protected */
    public void formatCookieAsVer(CharArrayBuffer charArrayBuffer, Cookie cookie, int i) {
        formatParamAsVer(charArrayBuffer, cookie.getName(), cookie.getValue(), i);
        if (cookie.getPath() != null && (cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute("path")) {
            charArrayBuffer.append("; ");
            formatParamAsVer(charArrayBuffer, "$Path", cookie.getPath(), i);
        }
        if (cookie.getDomain() != null && (cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute("domain")) {
            charArrayBuffer.append("; ");
            formatParamAsVer(charArrayBuffer, "$Domain", cookie.getDomain(), i);
        }
    }

    public List formatCookies(List $r1) {
        Args.notEmpty((Collection) $r1, "List of cookies");
        if ($r1.size() > 1) {
            ArrayList $r2 = r5;
            ArrayList r5 = new ArrayList($r1);
            Collections.sort($r2, CookiePathComparator.INSTANCE);
            $r1 = $r2;
        }
        return this.oneHeader ? doFormatOneHeader($r1) : doFormatManyHeaders($r1);
    }

    /* access modifiers changed from: protected */
    public void formatParamAsVer(CharArrayBuffer charArrayBuffer, String str, String str2, int i) {
        charArrayBuffer.append(str);
        charArrayBuffer.append("=");
        if (str2 == null) {
            return;
        }
        if (i > 0) {
            charArrayBuffer.append('\"');
            charArrayBuffer.append(str2);
            charArrayBuffer.append('\"');
            return;
        }
        charArrayBuffer.append(str2);
    }

    public int getVersion() {
        return 1;
    }

    public Header getVersionHeader() {
        return null;
    }

    public List parse(Header header, CookieOrigin cookieOrigin) {
        Args.notNull(header, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase("Set-Cookie")) {
            return parse(header.getElements(), cookieOrigin);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public String toString() {
        return "rfc2109";
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        String $r3 = cookie.getName();
        if ($r3.indexOf(32) != -1) {
            throw new CookieRestrictionViolationException("Cookie name may not contain blanks");
        } else if (!$r3.startsWith("$")) {
            super.validate(cookie, cookieOrigin);
        } else {
            throw new CookieRestrictionViolationException("Cookie name may not start with $");
        }
    }
}
