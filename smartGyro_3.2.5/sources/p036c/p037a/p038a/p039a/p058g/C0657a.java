package p036c.p037a.p038a.p039a.p058g;

import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.p069k.C0814b;

/* renamed from: c.a.a.a.g.a */
public abstract class C0657a implements C0837l {

    /* renamed from: a */
    protected C0576e f1952a;

    /* renamed from: b */
    protected C0576e f1953b;

    /* renamed from: c */
    protected boolean f1954c;

    protected C0657a() {
    }

    /* renamed from: a */
    public void mo2784a(C0576e eVar) {
        this.f1953b = eVar;
    }

    /* renamed from: a */
    public void mo2785a(String str) {
        mo2787b(str != null ? new C0814b("Content-Type", str) : null);
    }

    /* renamed from: a */
    public void mo2786a(boolean z) {
        this.f1954c = z;
    }

    /* renamed from: b */
    public void mo2787b(C0576e eVar) {
        this.f1952a = eVar;
    }

    @Deprecated
    public void consumeContent() {
    }

    public C0576e getContentEncoding() {
        return this.f1953b;
    }

    public C0576e getContentType() {
        return this.f1952a;
    }

    public boolean isChunked() {
        return this.f1954c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (this.f1952a != null) {
            sb.append("Content-Type: ");
            sb.append(this.f1952a.getValue());
            sb.append(',');
        }
        if (this.f1953b != null) {
            sb.append("Content-Encoding: ");
            sb.append(this.f1953b.getValue());
            sb.append(',');
        }
        long contentLength = getContentLength();
        if (contentLength >= 0) {
            sb.append("Content-Length: ");
            sb.append(contentLength);
            sb.append(',');
        }
        sb.append("Chunked: ");
        sb.append(this.f1954c);
        sb.append(']');
        return sb.toString();
    }
}
