package cz.msebera.android.http.impl.entity;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.entity.ContentLengthStrategy;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.Args;

@Deprecated
public class EntityDeserializer {
    private final ContentLengthStrategy lenStrategy;

    public EntityDeserializer(ContentLengthStrategy $r1) {
        Args.notNull($r1, "Content length strategy");
        this.lenStrategy = $r1;
    }

    public HttpEntity deserialize(SessionInputBuffer sessionInputBuffer, HttpMessage httpMessage) {
        Args.notNull(sessionInputBuffer, "Session input buffer");
        Args.notNull(httpMessage, "HTTP message");
        return doDeserialize(sessionInputBuffer, httpMessage);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cz.msebera.android.http.entity.BasicHttpEntity doDeserialize(cz.msebera.android.http.io.SessionInputBuffer r20, cz.msebera.android.http.HttpMessage r21) {
        /*
            r19 = this;
            cz.msebera.android.http.entity.BasicHttpEntity r2 = new cz.msebera.android.http.entity.BasicHttpEntity
            r3 = r2
            r2.<init>()
            r0 = r19
            cz.msebera.android.http.entity.ContentLengthStrategy r4 = r0.lenStrategy
            r0 = r21
            long r5 = r4.determineLength(r0)
            r8 = -2
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x002f
            r10 = 1
            r3.setChunked(r10)
            r8 = -1
            r3.setContentLength(r8)
            cz.msebera.android.http.impl.io.ChunkedInputStream r11 = new cz.msebera.android.http.impl.io.ChunkedInputStream
            r12 = r11
            r0 = r20
            r11.<init>(r0)
        L_0x0027:
            r14 = r12
            java.io.InputStream r14 = (java.io.InputStream) r14
            r13 = r14
            r3.setContent(r13)
            goto L_0x005d
        L_0x002f:
            r8 = -1
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x0048
            r10 = 0
            r3.setChunked(r10)
            r8 = -1
            r3.setContentLength(r8)
            cz.msebera.android.http.impl.io.IdentityInputStream r15 = new cz.msebera.android.http.impl.io.IdentityInputStream
            r12 = r15
            r0 = r20
            r15.<init>(r0)
            goto L_0x0027
        L_0x0048:
            r10 = 0
            r3.setChunked(r10)
            r3.setContentLength(r5)
            cz.msebera.android.http.impl.io.ContentLengthInputStream r16 = new cz.msebera.android.http.impl.io.ContentLengthInputStream
            r0 = r16
            r1 = r20
            r0.<init>(r1, r5)
            r0 = r16
            r3.setContent(r0)
        L_0x005d:
            java.lang.String r18 = "Content-Type"
            r0 = r21
            r1 = r18
            cz.msebera.android.http.Header r17 = r0.getFirstHeader(r1)
            if (r17 == 0) goto L_0x006e
            r0 = r17
            r3.setContentType((cz.msebera.android.http.Header) r0)
        L_0x006e:
            java.lang.String r18 = "Content-Encoding"
            r0 = r21
            r1 = r18
            cz.msebera.android.http.Header r17 = r0.getFirstHeader(r1)
            if (r17 == 0) goto L_0x007f
            r0 = r17
            r3.setContentEncoding(r0)
        L_0x007f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.entity.EntityDeserializer.doDeserialize(cz.msebera.android.http.io.SessionInputBuffer, cz.msebera.android.http.HttpMessage):cz.msebera.android.http.entity.BasicHttpEntity");
    }
}
