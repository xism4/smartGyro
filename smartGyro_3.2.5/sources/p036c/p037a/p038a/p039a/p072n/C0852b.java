package p036c.p037a.p038a.p039a.p072n;

import java.util.ArrayList;
import java.util.List;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0885v;

@Deprecated
/* renamed from: c.a.a.a.n.b */
public final class C0852b implements C0857g, C0859i, C0860j, Cloneable {

    /* renamed from: a */
    protected final List<C0882s> f2417a = new ArrayList();

    /* renamed from: b */
    protected final List<C0885v> f2418b = new ArrayList();

    /* renamed from: a */
    public int mo3246a() {
        return this.f2417a.size();
    }

    /* renamed from: a */
    public C0882s mo3247a(int i) {
        if (i < 0 || i >= this.f2417a.size()) {
            return null;
        }
        return this.f2417a.get(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3248a(C0852b bVar) {
        bVar.f2417a.clear();
        bVar.f2417a.addAll(this.f2417a);
        bVar.f2418b.clear();
        bVar.f2418b.addAll(this.f2418b);
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        for (C0882s a : this.f2417a) {
            a.mo2352a(rVar, eVar);
        }
    }

    /* renamed from: a */
    public final void mo3249a(C0882s sVar) {
        mo3254b(sVar);
    }

    /* renamed from: a */
    public final void mo3250a(C0882s sVar, int i) {
        mo3255b(sVar, i);
    }

    /* renamed from: a */
    public void mo2353a(C0883t tVar, C0855e eVar) {
        for (C0885v a : this.f2418b) {
            a.mo2353a(tVar, eVar);
        }
    }

    /* renamed from: a */
    public final void mo3251a(C0885v vVar) {
        mo3256b(vVar);
    }

    /* renamed from: b */
    public int mo3252b() {
        return this.f2418b.size();
    }

    /* renamed from: b */
    public C0885v mo3253b(int i) {
        if (i < 0 || i >= this.f2418b.size()) {
            return null;
        }
        return this.f2418b.get(i);
    }

    /* renamed from: b */
    public void mo3254b(C0882s sVar) {
        if (sVar != null) {
            this.f2417a.add(sVar);
        }
    }

    /* renamed from: b */
    public void mo3255b(C0882s sVar, int i) {
        if (sVar != null) {
            this.f2417a.add(i, sVar);
        }
    }

    /* renamed from: b */
    public void mo3256b(C0885v vVar) {
        if (vVar != null) {
            this.f2418b.add(vVar);
        }
    }

    public Object clone() {
        C0852b bVar = (C0852b) super.clone();
        mo3248a(bVar);
        return bVar;
    }
}
