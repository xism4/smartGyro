package p036c.p037a.p038a.p039a.p060i.p067f;

import java.util.ArrayList;
import java.util.List;
import p036c.p037a.p038a.p039a.C0486B;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0880q;
import p036c.p037a.p038a.p039a.C0888y;
import p036c.p037a.p038a.p039a.p049d.C0574b;
import p036c.p037a.p038a.p039a.p068j.C0807c;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p069k.C0823k;
import p036c.p037a.p038a.p039a.p069k.C0834v;
import p036c.p037a.p038a.p039a.p070l.C0843f;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.f.a */
public abstract class C0789a<T extends C0880q> implements C0807c<T> {

    /* renamed from: a */
    private final C0810f f2280a;

    /* renamed from: b */
    private final C0574b f2281b;

    /* renamed from: c */
    private final List<C0873d> f2282c;

    /* renamed from: d */
    protected final C0834v f2283d;

    /* renamed from: e */
    private int f2284e;

    /* renamed from: f */
    private T f2285f;

    @Deprecated
    public C0789a(C0810f fVar, C0834v vVar, C0844g gVar) {
        C0870a.m3042a(fVar, "Session input buffer");
        C0870a.m3042a(gVar, "HTTP parameters");
        this.f2280a = fVar;
        this.f2281b = C0843f.m2987a(gVar);
        this.f2283d = vVar == null ? C0823k.f2379b : vVar;
        this.f2282c = new ArrayList();
        this.f2284e = 0;
    }

    /* renamed from: a */
    public static C0576e[] m2855a(C0810f fVar, int i, int i2, C0834v vVar) {
        ArrayList arrayList = new ArrayList();
        if (vVar == null) {
            vVar = C0823k.f2379b;
        }
        return m2856a(fVar, i, i2, vVar, arrayList);
    }

    /* renamed from: a */
    public static C0576e[] m2856a(C0810f fVar, int i, int i2, C0834v vVar, List<C0873d> list) {
        int i3;
        C0870a.m3042a(fVar, "Session input buffer");
        C0870a.m3042a(vVar, "Line parser");
        C0870a.m3042a(list, "Header line list");
        C0873d dVar = null;
        C0873d dVar2 = null;
        while (true) {
            if (dVar == null) {
                dVar = new C0873d(64);
            } else {
                dVar.clear();
            }
            i3 = 0;
            if (fVar.mo3012a(dVar) == -1 || dVar.length() < 1) {
                C0576e[] eVarArr = new C0576e[list.size()];
            } else {
                if ((dVar.charAt(0) == ' ' || dVar.charAt(0) == 9) && dVar2 != null) {
                    while (i3 < dVar.length() && ((r3 = dVar.charAt(i3)) == ' ' || r3 == 9)) {
                        i3++;
                    }
                    if (i2 <= 0 || ((dVar2.length() + 1) + dVar.length()) - i3 <= i2) {
                        dVar2.append(' ');
                        dVar2.mo3297a(dVar, i3, dVar.length() - i3);
                    } else {
                        throw new C0888y("Maximum line length limit exceeded");
                    }
                } else {
                    list.add(dVar);
                    dVar2 = dVar;
                    dVar = null;
                }
                if (i > 0 && list.size() >= i) {
                    throw new C0888y("Maximum header count exceeded");
                }
            }
        }
        C0576e[] eVarArr2 = new C0576e[list.size()];
        while (i3 < list.size()) {
            try {
                eVarArr2[i3] = vVar.mo3174a(list.get(i3));
                i3++;
            } catch (C0486B e) {
                throw new C0487C(e.getMessage());
            }
        }
        return eVarArr2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract T mo3004a(C0810f fVar);

    public T parse() {
        int i = this.f2284e;
        if (i == 0) {
            try {
                this.f2285f = mo3004a(this.f2280a);
                this.f2284e = 1;
            } catch (C0486B e) {
                throw new C0487C(e.getMessage(), e);
            }
        } else if (i != 1) {
            throw new IllegalStateException("Inconsistent parser state");
        }
        this.f2285f.mo3117a(m2856a(this.f2280a, this.f2281b.mo2605b(), this.f2281b.mo2606c(), this.f2283d, this.f2282c));
        T t = this.f2285f;
        this.f2285f = null;
        this.f2282c.clear();
        this.f2284e = 0;
        return t;
    }
}
