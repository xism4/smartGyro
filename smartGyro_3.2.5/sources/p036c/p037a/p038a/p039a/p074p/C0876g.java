package p036c.p037a.p038a.p039a.p074p;

import java.io.InputStream;
import p036c.p037a.p038a.p039a.C0837l;

/* renamed from: c.a.a.a.p.g */
public final class C0876g {
    /* renamed from: a */
    public static void m3080a(C0837l lVar) {
        InputStream content;
        if (lVar != null && lVar.isStreaming() && (content = lVar.getContent()) != null) {
            content.close();
        }
    }

    /* renamed from: b */
    public static byte[] m3081b(C0837l lVar) {
        C0870a.m3042a(lVar, "Entity");
        InputStream content = lVar.getContent();
        if (content == null) {
            return null;
        }
        try {
            C0870a.m3044a(lVar.getContentLength() <= 2147483647L, "HTTP entity too large to be buffered in memory");
            int contentLength = (int) lVar.getContentLength();
            if (contentLength < 0) {
                contentLength = 4096;
            }
            C0872c cVar = new C0872c(contentLength);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    return cVar.mo3292g();
                }
                cVar.mo3283a(bArr, 0, read);
            }
        } finally {
            content.close();
        }
    }
}
