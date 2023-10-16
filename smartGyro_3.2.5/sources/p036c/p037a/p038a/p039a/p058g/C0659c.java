package p036c.p037a.p038a.p039a.p058g;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0876g;

/* renamed from: c.a.a.a.g.c */
public class C0659c extends C0662f {

    /* renamed from: b */
    private final byte[] f1957b;

    public C0659c(C0837l lVar) {
        super(lVar);
        if (!lVar.isRepeatable() || lVar.getContentLength() < 0) {
            this.f1957b = C0876g.m3081b(lVar);
        } else {
            this.f1957b = null;
        }
    }

    public InputStream getContent() {
        byte[] bArr = this.f1957b;
        return bArr != null ? new ByteArrayInputStream(bArr) : super.getContent();
    }

    public long getContentLength() {
        byte[] bArr = this.f1957b;
        return bArr != null ? (long) bArr.length : super.getContentLength();
    }

    public boolean isChunked() {
        return this.f1957b == null && super.isChunked();
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return this.f1957b == null && super.isStreaming();
    }

    public void writeTo(OutputStream outputStream) {
        C0870a.m3042a(outputStream, "Output stream");
        byte[] bArr = this.f1957b;
        if (bArr != null) {
            outputStream.write(bArr);
        } else {
            super.writeTo(outputStream);
        }
    }
}
