package p000a.p001a.p017d.p023d;

import android.view.View;
import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0093B;

/* renamed from: a.a.d.d.h */
class C0174h extends C0093B {

    /* renamed from: a */
    private boolean f436a = false;

    /* renamed from: b */
    private int f437b = 0;

    /* renamed from: c */
    final /* synthetic */ C0175i f438c;

    C0174h(C0175i iVar) {
        this.f438c = iVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo706a() {
        this.f437b = 0;
        this.f436a = false;
        this.f438c.mo713b();
    }

    /* renamed from: b */
    public void mo390b(View view) {
        int i = this.f437b + 1;
        this.f437b = i;
        if (i == this.f438c.f439a.size()) {
            C0092A a = this.f438c.f442d;
            if (a != null) {
                a.mo390b((View) null);
            }
            mo706a();
        }
    }

    /* renamed from: c */
    public void mo391c(View view) {
        if (!this.f436a) {
            this.f436a = true;
            C0092A a = this.f438c.f442d;
            if (a != null) {
                a.mo391c((View) null);
            }
        }
    }
}
