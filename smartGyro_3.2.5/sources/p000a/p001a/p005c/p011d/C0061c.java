package p000a.p001a.p005c.p011d;

import android.os.Handler;
import p000a.p001a.p005c.p006a.p007a.C0038h;
import p000a.p001a.p005c.p011d.C0064f;
import p000a.p001a.p005c.p011d.C0072k;

/* renamed from: a.a.c.d.c */
class C0061c implements C0072k.C0073a<C0064f.C0067c> {

    /* renamed from: a */
    final /* synthetic */ C0038h.C0039a f150a;

    /* renamed from: b */
    final /* synthetic */ Handler f151b;

    C0061c(C0038h.C0039a aVar, Handler handler) {
        this.f150a = aVar;
        this.f151b = handler;
    }

    /* renamed from: a */
    public void mo226a(C0064f.C0067c cVar) {
        int i;
        C0038h.C0039a aVar;
        if (cVar == null) {
            aVar = this.f150a;
            i = 1;
        } else {
            i = cVar.f166b;
            if (i == 0) {
                this.f150a.mo173a(cVar.f165a, this.f151b);
                return;
            }
            aVar = this.f150a;
        }
        aVar.mo171a(i, this.f151b);
    }
}
