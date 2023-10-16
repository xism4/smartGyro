package p036c.p037a.p038a.p039a.p060i.p063c;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p050e.p052b.C0593f;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p071m.C0849a;

@Deprecated
/* renamed from: c.a.a.a.i.c.l */
class C0738l extends C0849a<C0587b, C0636q> {

    /* renamed from: i */
    public C0668b f2214i;

    /* renamed from: j */
    private final C0593f f2215j;

    public C0738l(C0668b bVar, String str, C0587b bVar2, C0636q qVar, long j, TimeUnit timeUnit) {
        super(str, bVar2, qVar, j, timeUnit);
        this.f2214i = bVar;
        this.f2215j = new C0593f(bVar2);
    }

    /* renamed from: a */
    public boolean mo3006a(long j) {
        boolean a = super.mo3006a(j);
        if (a && this.f2214i.mo2805a()) {
            C0668b bVar = this.f2214i;
            bVar.mo2803a("Connection " + this + " expired @ " + new Date(mo3242b()));
        }
        return a;
    }

    /* renamed from: d */
    public void mo3007d() {
        try {
            ((C0636q) mo3239a()).close();
        } catch (IOException e) {
            this.f2214i.mo2804a("I/O error closing connection", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public C0587b mo3008e() {
        return this.f2215j.mo2651c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public C0587b mo3009f() {
        return (C0587b) mo3243c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public C0593f mo3010g() {
        return this.f2215j;
    }

    /* renamed from: h */
    public boolean mo3011h() {
        return !((C0636q) mo3239a()).isOpen();
    }
}
