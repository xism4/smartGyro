package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.CookieSpecFactory;
import cz.msebera.android.http.cookie.CookieSpecProvider;
import cz.msebera.android.http.execchain.HttpContext;

@Deprecated
public class NetscapeDraftSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final CookieSpec cookieSpec;

    public NetscapeDraftSpecFactory() {
        this((String[]) null, false);
    }

    public NetscapeDraftSpecFactory(String[] strArr, boolean z) {
        this.cookieSpec = new OCFile(strArr, z);
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
            if (r12 == 0) goto L_0x002a
            r0 = 0
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
            java.lang.String r2 = "http.protocol.single-cookie-header"
            r9 = 0
            boolean r8 = r12.getBooleanParameter(r2, r9)
            cz.msebera.android.http.impl.cookie.OCFile r10 = new cz.msebera.android.http.impl.cookie.OCFile
            r10.<init>(r0, r8)
            return r10
        L_0x002a:
            cz.msebera.android.http.impl.cookie.OCFile r10 = new cz.msebera.android.http.impl.cookie.OCFile
            r10.<init>()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.cookie.NetscapeDraftSpecFactory.newInstance(cz.msebera.android.http.util.HttpParams):cz.msebera.android.http.cookie.CookieSpec");
    }
}
