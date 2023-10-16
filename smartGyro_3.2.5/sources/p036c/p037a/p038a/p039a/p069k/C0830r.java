package p036c.p037a.p038a.p039a.p069k;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0664h;

/* renamed from: c.a.a.a.k.r */
public class C0830r implements Cloneable, Serializable {

    /* renamed from: a */
    private final C0576e[] f2400a = new C0576e[0];

    /* renamed from: b */
    private final List<C0576e> f2401b = new ArrayList(16);

    /* renamed from: a */
    public void mo3205a() {
        this.f2401b.clear();
    }

    /* renamed from: a */
    public void mo3206a(C0576e eVar) {
        if (eVar != null) {
            this.f2401b.add(eVar);
        }
    }

    /* renamed from: a */
    public void mo3207a(C0576e[] eVarArr) {
        mo3205a();
        if (eVarArr != null) {
            Collections.addAll(this.f2401b, eVarArr);
        }
    }

    /* renamed from: a */
    public boolean mo3208a(String str) {
        for (int i = 0; i < this.f2401b.size(); i++) {
            if (this.f2401b.get(i).getName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public C0576e mo3209b(String str) {
        for (int i = 0; i < this.f2401b.size(); i++) {
            C0576e eVar = this.f2401b.get(i);
            if (eVar.getName().equalsIgnoreCase(str)) {
                return eVar;
            }
        }
        return null;
    }

    /* renamed from: b */
    public void mo3210b(C0576e eVar) {
        if (eVar != null) {
            this.f2401b.remove(eVar);
        }
    }

    /* renamed from: b */
    public C0576e[] mo3211b() {
        List<C0576e> list = this.f2401b;
        return (C0576e[]) list.toArray(new C0576e[list.size()]);
    }

    /* renamed from: c */
    public C0664h mo3212c() {
        return new C0824l(this.f2401b, (String) null);
    }

    /* renamed from: c */
    public void mo3213c(C0576e eVar) {
        if (eVar != null) {
            for (int i = 0; i < this.f2401b.size(); i++) {
                if (this.f2401b.get(i).getName().equalsIgnoreCase(eVar.getName())) {
                    this.f2401b.set(i, eVar);
                    return;
                }
            }
            this.f2401b.add(eVar);
        }
    }

    /* renamed from: c */
    public C0576e[] mo3214c(String str) {
        ArrayList arrayList = null;
        for (int i = 0; i < this.f2401b.size(); i++) {
            C0576e eVar = this.f2401b.get(i);
            if (eVar.getName().equalsIgnoreCase(str)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(eVar);
            }
        }
        return arrayList != null ? (C0576e[]) arrayList.toArray(new C0576e[arrayList.size()]) : this.f2400a;
    }

    public Object clone() {
        return super.clone();
    }

    /* renamed from: d */
    public C0664h mo3216d(String str) {
        return new C0824l(this.f2401b, str);
    }

    public String toString() {
        return this.f2401b.toString();
    }
}
