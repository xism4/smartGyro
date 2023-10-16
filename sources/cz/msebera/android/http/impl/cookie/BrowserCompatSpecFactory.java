package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.CookieSpecFactory;
import cz.msebera.android.http.cookie.CookieSpecProvider;
import cz.msebera.android.http.execchain.HttpContext;

@Deprecated
public class BrowserCompatSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final CookieSpec cookieSpec;
    private final MathArrays$OrderDirection securityLevel;

    public BrowserCompatSpecFactory() {
        this((String[]) null, MathArrays$OrderDirection.SECURITYLEVEL_DEFAULT);
    }

    public BrowserCompatSpecFactory(String[] strArr, MathArrays$OrderDirection mathArrays$OrderDirection) {
        this.securityLevel = mathArrays$OrderDirection;
        this.cookieSpec = new BrowserCompatSpec(strArr, mathArrays$OrderDirection);
    }

    public CookieSpec create(HttpContext httpContext) {
        return this.cookieSpec;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cz.msebera.android.http.cookie.CookieSpec newInstance(cz.msebera.android.http.util.HttpParams r12) {
        /*
            r11 = this;
            r0 = 0
            if (r12 == 0) goto L_0x0025
            java.lang.String r2 = "http.protocol.cookie-datepatterns"
            java.lang.Object r1 = r12.getParameter(r2)
            r4 = r1
            java.util.Collection r4 = (java.util.Collection) r4
            r3 = r4
            if (r3 == 0) goto L_0x001d
            int r5 = r3.size()
            java.lang.String[] r0 = new java.lang.String[r5]
            java.lang.Object[] r6 = r3.toArray(r0)
            r7 = r6
            java.lang.String[] r7 = (java.lang.String[]) r7
            r0 = r7
        L_0x001d:
            cz.msebera.android.http.impl.cookie.BrowserCompatSpec r8 = new cz.msebera.android.http.impl.cookie.BrowserCompatSpec
            cz.msebera.android.http.impl.cookie.MathArrays$OrderDirection r9 = r11.securityLevel
            r8.<init>(r0, r9)
            return r8
        L_0x0025:
            cz.msebera.android.http.impl.cookie.BrowserCompatSpec r8 = new cz.msebera.android.http.impl.cookie.BrowserCompatSpec
            cz.msebera.android.http.impl.cookie.MathArrays$OrderDirection r9 = r11.securityLevel
            r10 = 0
            r8.<init>(r10, r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.cookie.BrowserCompatSpecFactory.newInstance(cz.msebera.android.http.util.HttpParams):cz.msebera.android.http.cookie.CookieSpec");
    }
}
