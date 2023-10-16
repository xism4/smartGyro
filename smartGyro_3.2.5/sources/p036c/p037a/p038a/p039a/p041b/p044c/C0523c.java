package p036c.p037a.p038a.p039a.p041b.p044c;

import java.io.IOException;
import p036c.p037a.p038a.p039a.p048c.C0571a;
import p036c.p037a.p038a.p039a.p050e.C0628i;

/* renamed from: c.a.a.a.b.c.c */
class C0523c implements C0571a {

    /* renamed from: a */
    final /* synthetic */ C0628i f1813a;

    /* renamed from: b */
    final /* synthetic */ C0524d f1814b;

    C0523c(C0524d dVar, C0628i iVar) {
        this.f1814b = dVar;
        this.f1813a = iVar;
    }

    public boolean cancel() {
        try {
            this.f1813a.abortConnection();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
