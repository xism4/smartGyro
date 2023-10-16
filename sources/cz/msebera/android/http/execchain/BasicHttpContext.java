package cz.msebera.android.http.execchain;

import cz.msebera.android.http.mime.Args;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicHttpContext implements HttpContext {
    private final Map<String, Object> map;
    private final HttpContext parentContext;

    public BasicHttpContext() {
        this((HttpContext) null);
    }

    public BasicHttpContext(HttpContext httpContext) {
        this.map = new ConcurrentHashMap();
        this.parentContext = httpContext;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r3 = r4.parentContext;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getAttribute(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "Id"
            cz.msebera.android.http.mime.Args.notNull(r5, r0)
            java.util.Map<java.lang.String, java.lang.Object> r1 = r4.map
            java.lang.Object r2 = r1.get(r5)
            if (r2 != 0) goto L_0x0015
            cz.msebera.android.http.execchain.HttpContext r3 = r4.parentContext
            if (r3 == 0) goto L_0x0015
            java.lang.Object r2 = r3.getAttribute(r5)
        L_0x0015:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.execchain.BasicHttpContext.getAttribute(java.lang.String):java.lang.Object");
    }

    public void setAttribute(String str, Object obj) {
        Args.notNull(str, "Id");
        if (obj != null) {
            this.map.put(str, obj);
        } else {
            this.map.remove(str);
        }
    }

    public String toString() {
        return this.map.toString();
    }
}
