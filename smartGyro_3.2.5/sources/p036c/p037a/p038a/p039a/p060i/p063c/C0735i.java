package p036c.p037a.p038a.p039a.p060i.p063c;

import p036c.p037a.p038a.p039a.C0485A;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0884u;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p060i.p067f.C0789a;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p069k.C0834v;
import p036c.p037a.p038a.p039a.p069k.C0835w;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.c.i */
public class C0735i extends C0789a<C0883t> {

    /* renamed from: g */
    public C0668b f2209g = new C0668b(C0735i.class);

    /* renamed from: h */
    private final C0884u f2210h;

    /* renamed from: i */
    private final C0873d f2211i;

    @Deprecated
    public C0735i(C0810f fVar, C0834v vVar, C0884u uVar, C0844g gVar) {
        super(fVar, vVar, gVar);
        C0870a.m3042a(uVar, "Response factory");
        this.f2210h = uVar;
        this.f2211i = new C0873d(128);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0883t m2704a(C0810f fVar) {
        int i = 0;
        while (true) {
            this.f2211i.clear();
            int a = fVar.mo3012a(this.f2211i);
            if (a == -1 && i == 0) {
                throw new C0485A("The target server failed to respond");
            }
            C0835w wVar = new C0835w(0, this.f2211i.length());
            if (this.f2283d.mo3175b(this.f2211i, wVar)) {
                return this.f2210h.mo2931a(this.f2283d.mo3173a(this.f2211i, wVar), (C0855e) null);
            } else if (a != -1 && !mo3005a(this.f2211i, i)) {
                if (this.f2209g.mo2805a()) {
                    C0668b bVar = this.f2209g;
                    bVar.mo2803a("Garbage in response: " + this.f2211i.toString());
                }
                i++;
            }
        }
        throw new C0487C("The server failed to respond with a valid HTTP response");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo3005a(C0873d dVar, int i) {
        return false;
    }
}
