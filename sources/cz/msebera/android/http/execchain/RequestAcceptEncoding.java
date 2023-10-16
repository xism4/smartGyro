package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpRequestInterceptor;

public class RequestAcceptEncoding implements HttpRequestInterceptor {
    private final String originServer;

    public RequestAcceptEncoding() {
        this((String) null);
    }

    public RequestAcceptEncoding(String str) {
        this.originServer = str;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void process(cz.msebera.android.http.HttpRequest r7, cz.msebera.android.http.execchain.HttpContext r8) {
        /*
            r6 = this;
            java.lang.String r0 = "HTTP request"
            cz.msebera.android.http.mime.Args.notNull(r7, r0)
            java.lang.String r0 = "User-Agent"
            boolean r1 = r7.containsHeader(r0)
            if (r1 != 0) goto L_0x0029
            r2 = 0
            cz.msebera.android.http.util.HttpParams r3 = r7.getParams()
            if (r3 == 0) goto L_0x001e
            java.lang.String r0 = "http.useragent"
            java.lang.Object r4 = r3.getParameter(r0)
            r5 = r4
            java.lang.String r5 = (java.lang.String) r5
            r2 = r5
        L_0x001e:
            if (r2 != 0) goto L_0x0022
            java.lang.String r2 = r6.originServer
        L_0x0022:
            if (r2 == 0) goto L_0x0029
            java.lang.String r0 = "User-Agent"
            r7.addHeader(r0, r2)
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.execchain.RequestAcceptEncoding.process(cz.msebera.android.http.HttpRequest, cz.msebera.android.http.execchain.HttpContext):void");
    }
}
