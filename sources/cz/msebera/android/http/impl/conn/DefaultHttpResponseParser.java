package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.i;
import c.a.a.a.i.f.a;
import c.a.a.a.t;
import cz.msebera.android.http.HttpResponseFactory;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.message.LineParser;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.util.HttpParams;

public class DefaultHttpResponseParser extends a<t> {
    private final CharArrayBuffer lineBuf;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(i.class);
    private final HttpResponseFactory responseFactory;

    public DefaultHttpResponseParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        Args.notNull(httpResponseFactory, "Response factory");
        this.responseFactory = httpResponseFactory;
        this.lineBuf = new CharArrayBuffer(128);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: cz.msebera.android.http.impl.io.AbstractMessageParser} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: cz.msebera.android.http.impl.io.AbstractMessageParser} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cz.msebera.android.http.HttpResponse parseHead(cz.msebera.android.http.io.SessionInputBuffer r21) {
        /*
            r20 = this;
            r2 = 0
        L_0x0001:
            r0 = r20
            cz.msebera.android.http.mime.CharArrayBuffer r3 = r0.lineBuf
            r3.clear()
            r0 = r20
            cz.msebera.android.http.mime.CharArrayBuffer r3 = r0.lineBuf
            r0 = r21
            int r4 = r0.readLine(r3)
            r5 = -1
            if (r4 != r5) goto L_0x0020
            if (r2 == 0) goto L_0x0018
            goto L_0x0020
        L_0x0018:
            cz.msebera.android.http.NoHttpResponseException r6 = new cz.msebera.android.http.NoHttpResponseException
            java.lang.String r7 = "The target server failed to respond"
            r6.<init>(r7)
            throw r6
        L_0x0020:
            cz.msebera.android.http.message.CharArrayBuffer r8 = new cz.msebera.android.http.message.CharArrayBuffer
            r0 = r20
            cz.msebera.android.http.mime.CharArrayBuffer r3 = r0.lineBuf
            int r9 = r3.length()
            r5 = 0
            r8.<init>(r5, r9)
            r0 = r20
            cz.msebera.android.http.message.LineParser r10 = r0.lineParser
            r0 = r20
            cz.msebera.android.http.mime.CharArrayBuffer r3 = r0.lineBuf
            boolean r11 = r10.hasProtocolVersion(r3, r8)
            if (r11 == 0) goto L_0x0052
            r0 = r20
            cz.msebera.android.http.message.LineParser r10 = r0.lineParser
            r0 = r20
            cz.msebera.android.http.mime.CharArrayBuffer r3 = r0.lineBuf
            cz.msebera.android.http.StatusLine r12 = r10.parseStatusLine(r3, r8)
            r0 = r20
            cz.msebera.android.http.HttpResponseFactory r13 = r0.responseFactory
            r15 = 0
            cz.msebera.android.http.HttpResponse r14 = r13.newHttpResponse(r12, r15)
            return r14
        L_0x0052:
            r5 = -1
            if (r4 == r5) goto L_0x00a2
            r0 = r20
            cz.msebera.android.http.mime.CharArrayBuffer r3 = r0.lineBuf
            r0 = r20
            boolean r11 = r0.reject(r3, r2)
            if (r11 != 0) goto L_0x00a2
            r0 = r20
            cz.msebera.android.http.cache.HttpClientAndroidLog r0 = r0.log
            r16 = r0
            boolean r11 = r0.isDebugEnabled()
            if (r11 == 0) goto L_0x009d
            r0 = r20
            cz.msebera.android.http.cache.HttpClientAndroidLog r0 = r0.log
            r16 = r0
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r0 = r17
            r0.<init>()
            java.lang.String r7 = "Garbage in response: "
            r0 = r17
            r0.append(r7)
            r0 = r20
            cz.msebera.android.http.mime.CharArrayBuffer r3 = r0.lineBuf
            java.lang.String r18 = r3.toString()
            r0 = r17
            r1 = r18
            r0.append(r1)
            r0 = r17
            java.lang.String r18 = r0.toString()
            r0 = r16
            r1 = r18
            r0.debug(r1)
        L_0x009d:
            int r2 = r2 + 1
            goto L_0x0001
        L_0x00a2:
            cz.msebera.android.http.ProtocolException r19 = new cz.msebera.android.http.ProtocolException
            java.lang.String r7 = "The server failed to respond with a valid HTTP response"
            r0 = r19
            r0.<init>(r7)
            goto L_0x00ac
        L_0x00ac:
            throw r19
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.conn.DefaultHttpResponseParser.parseHead(cz.msebera.android.http.io.SessionInputBuffer):cz.msebera.android.http.HttpResponse");
    }

    /* access modifiers changed from: protected */
    public boolean reject(CharArrayBuffer charArrayBuffer, int i) {
        return false;
    }
}
