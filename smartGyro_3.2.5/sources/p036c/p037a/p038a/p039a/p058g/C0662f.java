package p036c.p037a.p038a.p039a.p058g;

import java.io.InputStream;
import java.io.OutputStream;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.g.f */
public class C0662f implements C0837l {

    /* renamed from: a */
    protected C0837l f1975a;

    public C0662f(C0837l lVar) {
        C0870a.m3042a(lVar, "Wrapped entity");
        this.f1975a = lVar;
    }

    @Deprecated
    public void consumeContent() {
        this.f1975a.consumeContent();
    }

    public InputStream getContent() {
        return this.f1975a.getContent();
    }

    public C0576e getContentEncoding() {
        return this.f1975a.getContentEncoding();
    }

    public long getContentLength() {
        return this.f1975a.getContentLength();
    }

    public C0576e getContentType() {
        return this.f1975a.getContentType();
    }

    public boolean isChunked() {
        return this.f1975a.isChunked();
    }

    public boolean isRepeatable() {
        return this.f1975a.isRepeatable();
    }

    public boolean isStreaming() {
        return this.f1975a.isStreaming();
    }

    public void writeTo(OutputStream outputStream) {
        this.f1975a.writeTo(outputStream);
    }
}
