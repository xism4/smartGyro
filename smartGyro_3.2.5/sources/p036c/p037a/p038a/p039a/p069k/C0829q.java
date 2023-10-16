package p036c.p037a.p038a.p039a.p069k;

import java.io.Serializable;
import p036c.p037a.p038a.p039a.C0486B;
import p036c.p037a.p038a.p039a.C0572d;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.q */
public class C0829q implements C0572d, Cloneable, Serializable {

    /* renamed from: a */
    private final String f2397a;

    /* renamed from: b */
    private final C0873d f2398b;

    /* renamed from: c */
    private final int f2399c;

    public C0829q(C0873d dVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        int b = dVar.mo3303b(58);
        if (b != -1) {
            String b2 = dVar.mo3304b(0, b);
            if (b2.length() != 0) {
                this.f2398b = dVar;
                this.f2397a = b2;
                this.f2399c = b + 1;
                return;
            }
            throw new C0486B("Invalid header: " + dVar.toString());
        }
        throw new C0486B("Invalid header: " + dVar.toString());
    }

    public Object clone() {
        return super.clone();
    }

    public C0873d getBuffer() {
        return this.f2398b;
    }

    public C0639f[] getElements() {
        C0835w wVar = new C0835w(0, this.f2398b.length());
        wVar.mo3218a(this.f2399c);
        return C0819g.f2362b.mo3152a(this.f2398b, wVar);
    }

    public String getName() {
        return this.f2397a;
    }

    public String getValue() {
        C0873d dVar = this.f2398b;
        return dVar.mo3304b(this.f2399c, dVar.length());
    }

    public int getValuePos() {
        return this.f2399c;
    }

    public String toString() {
        return this.f2398b.toString();
    }
}
