package android.support.p025v7.app;

import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p005c.p014g.C0134z;

/* renamed from: android.support.v7.app.t */
class C0259t implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0261v f764a;

    C0259t(C0261v vVar) {
        this.f764a = vVar;
    }

    public void run() {
        C0261v vVar = this.f764a;
        vVar.f801q.showAtLocation(vVar.f800p, 55, 0, 0);
        this.f764a.mo1058g();
        if (this.f764a.mo1069o()) {
            this.f764a.f800p.setAlpha(0.0f);
            C0261v vVar2 = this.f764a;
            C0134z a = C0127u.m436a(vVar2.f800p);
            a.mo503a(1.0f);
            vVar2.f803s = a;
            this.f764a.f803s.mo505a((C0092A) new C0258s(this));
            return;
        }
        this.f764a.f800p.setAlpha(1.0f);
        this.f764a.f800p.setVisibility(0);
    }
}
