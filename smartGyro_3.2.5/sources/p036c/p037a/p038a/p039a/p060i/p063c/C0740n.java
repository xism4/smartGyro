package p036c.p037a.p038a.p039a.p060i.p063c;

import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.p068j.C0806b;
import p036c.p037a.p038a.p039a.p068j.C0809e;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p074p.C0873d;

@Deprecated
/* renamed from: c.a.a.a.i.c.n */
public class C0740n implements C0810f, C0806b {

    /* renamed from: a */
    private final C0810f f2218a;

    /* renamed from: b */
    private final C0806b f2219b;

    /* renamed from: c */
    private final C0745s f2220c;

    /* renamed from: d */
    private final String f2221d;

    public C0740n(C0810f fVar, C0745s sVar, String str) {
        this.f2218a = fVar;
        this.f2219b = fVar instanceof C0806b ? (C0806b) fVar : null;
        this.f2220c = sVar;
        this.f2221d = str == null ? C0570c.f1866b.name() : str;
    }

    /* renamed from: a */
    public int mo3012a(C0873d dVar) {
        int a = this.f2218a.mo3012a(dVar);
        if (this.f2220c.mo3031a() && a >= 0) {
            String str = new String(dVar.mo3301a(), dVar.length() - a, a);
            this.f2220c.mo3029a((str + "\r\n").getBytes(this.f2221d));
        }
        return a;
    }

    /* renamed from: a */
    public boolean mo3013a() {
        C0806b bVar = this.f2219b;
        if (bVar != null) {
            return bVar.mo3013a();
        }
        return false;
    }

    public C0809e getMetrics() {
        return this.f2218a.getMetrics();
    }

    public boolean isDataAvailable(int i) {
        return this.f2218a.isDataAvailable(i);
    }

    public int read() {
        int read = this.f2218a.read();
        if (this.f2220c.mo3031a() && read != -1) {
            this.f2220c.mo3028a(read);
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) {
        int read = this.f2218a.read(bArr, i, i2);
        if (this.f2220c.mo3031a() && read > 0) {
            this.f2220c.mo3030a(bArr, i, read);
        }
        return read;
    }
}
