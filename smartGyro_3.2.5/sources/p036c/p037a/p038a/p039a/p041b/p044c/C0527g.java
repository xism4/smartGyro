package p036c.p037a.p038a.p039a.p041b.p044c;

import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.p041b.p047f.C0551a;

/* renamed from: c.a.a.a.b.c.g */
public abstract class C0527g extends C0531k implements C0848m {

    /* renamed from: h */
    private C0837l f1817h;

    /* renamed from: a */
    public void mo2548a(C0837l lVar) {
        this.f1817h = lVar;
    }

    public Object clone() {
        C0527g gVar = (C0527g) super.clone();
        C0837l lVar = this.f1817h;
        if (lVar != null) {
            gVar.f1817h = (C0837l) C0551a.m2226a(lVar);
        }
        return gVar;
    }

    public boolean expectContinue() {
        C0576e firstHeader = getFirstHeader("Expect");
        return firstHeader != null && "100-continue".equalsIgnoreCase(firstHeader.getValue());
    }

    public C0837l getEntity() {
        return this.f1817h;
    }
}
