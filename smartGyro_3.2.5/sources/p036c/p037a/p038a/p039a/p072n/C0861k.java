package p036c.p037a.p038a.p039a.p072n;

import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0885v;

/* renamed from: c.a.a.a.n.k */
public final class C0861k implements C0857g {

    /* renamed from: a */
    private final C0882s[] f2425a;

    /* renamed from: b */
    private final C0885v[] f2426b;

    public C0861k(C0882s[] sVarArr, C0885v[] vVarArr) {
        if (sVarArr != null) {
            int length = sVarArr.length;
            this.f2425a = new C0882s[length];
            System.arraycopy(sVarArr, 0, this.f2425a, 0, length);
        } else {
            this.f2425a = new C0882s[0];
        }
        if (vVarArr != null) {
            int length2 = vVarArr.length;
            this.f2426b = new C0885v[length2];
            System.arraycopy(vVarArr, 0, this.f2426b, 0, length2);
            return;
        }
        this.f2426b = new C0885v[0];
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        for (C0882s a : this.f2425a) {
            a.mo2352a(rVar, eVar);
        }
    }

    /* renamed from: a */
    public void mo2353a(C0883t tVar, C0855e eVar) {
        for (C0885v a : this.f2426b) {
            a.mo2353a(tVar, eVar);
        }
    }
}
