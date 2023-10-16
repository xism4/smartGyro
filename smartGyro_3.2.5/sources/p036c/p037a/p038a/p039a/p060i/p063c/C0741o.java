package p036c.p037a.p038a.p039a.p060i.p063c;

import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.p068j.C0809e;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p074p.C0873d;

@Deprecated
/* renamed from: c.a.a.a.i.c.o */
public class C0741o implements C0811g {

    /* renamed from: a */
    private final C0811g f2222a;

    /* renamed from: b */
    private final C0745s f2223b;

    /* renamed from: c */
    private final String f2224c;

    public C0741o(C0811g gVar, C0745s sVar, String str) {
        this.f2222a = gVar;
        this.f2223b = sVar;
        this.f2224c = str == null ? C0570c.f1866b.name() : str;
    }

    /* renamed from: a */
    public void mo3018a(C0873d dVar) {
        this.f2222a.mo3018a(dVar);
        if (this.f2223b.mo3031a()) {
            String str = new String(dVar.mo3301a(), 0, dVar.length());
            this.f2223b.mo3033b((str + "\r\n").getBytes(this.f2224c));
        }
    }

    public void flush() {
        this.f2222a.flush();
    }

    public C0809e getMetrics() {
        return this.f2222a.getMetrics();
    }

    public void write(int i) {
        this.f2222a.write(i);
        if (this.f2223b.mo3031a()) {
            this.f2223b.mo3032b(i);
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        this.f2222a.write(bArr, i, i2);
        if (this.f2223b.mo3031a()) {
            this.f2223b.mo3034b(bArr, i, i2);
        }
    }

    public void writeLine(String str) {
        this.f2222a.writeLine(str);
        if (this.f2223b.mo3031a()) {
            this.f2223b.mo3033b((str + "\r\n").getBytes(this.f2224c));
        }
    }
}
