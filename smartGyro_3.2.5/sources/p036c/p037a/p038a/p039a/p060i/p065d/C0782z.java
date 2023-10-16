package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.p057f.C0640a;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0646g;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p069k.C0829q;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.d.z */
public class C0782z extends C0773q {

    /* renamed from: b */
    static final String[] f2266b = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};

    /* renamed from: c */
    private final boolean f2267c;

    public C0782z() {
        this((String[]) null, false);
    }

    protected C0782z(boolean z, C0641b... bVarArr) {
        super(bVarArr);
        this.f2267c = z;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0782z(java.lang.String[] r4, boolean r5) {
        /*
            r3 = this;
            r0 = 7
            c.a.a.a.f.b[] r0 = new p036c.p037a.p038a.p039a.p057f.C0641b[r0]
            c.a.a.a.i.d.B r1 = new c.a.a.a.i.d.B
            r1.<init>()
            r2 = 0
            r0[r2] = r1
            c.a.a.a.i.d.i r1 = new c.a.a.a.i.d.i
            r1.<init>()
            r2 = 1
            r0[r2] = r1
            c.a.a.a.i.d.y r1 = new c.a.a.a.i.d.y
            r1.<init>()
            r2 = 2
            r0[r2] = r1
            c.a.a.a.i.d.h r1 = new c.a.a.a.i.d.h
            r1.<init>()
            r2 = 3
            r0[r2] = r1
            c.a.a.a.i.d.j r1 = new c.a.a.a.i.d.j
            r1.<init>()
            r2 = 4
            r0[r2] = r1
            c.a.a.a.i.d.e r1 = new c.a.a.a.i.d.e
            r1.<init>()
            r2 = 5
            r0[r2] = r1
            c.a.a.a.i.d.g r1 = new c.a.a.a.i.d.g
            if (r4 == 0) goto L_0x003e
            java.lang.Object r4 = r4.clone()
            java.lang.String[] r4 = (java.lang.String[]) r4
            goto L_0x0040
        L_0x003e:
            java.lang.String[] r4 = f2266b
        L_0x0040:
            r1.<init>(r4)
            r4 = 6
            r0[r4] = r1
            r3.<init>(r0)
            r3.f2267c = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p065d.C0782z.<init>(java.lang.String[], boolean):void");
    }

    /* renamed from: a */
    private List<C0576e> m2835a(List<C0642c> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (C0642c next : list) {
            int version = next.getVersion();
            C0873d dVar = new C0873d(40);
            dVar.mo3298a("Cookie: ");
            dVar.mo3298a("$Version=");
            dVar.mo3298a(Integer.toString(version));
            dVar.mo3298a("; ");
            mo3037a(dVar, next, version);
            arrayList.add(new C0829q(dVar));
        }
        return arrayList;
    }

    /* renamed from: b */
    private List<C0576e> m2836b(List<C0642c> list) {
        int i = Integer.MAX_VALUE;
        for (C0642c next : list) {
            if (next.getVersion() < i) {
                i = next.getVersion();
            }
        }
        C0873d dVar = new C0873d(list.size() * 40);
        dVar.mo3298a("Cookie");
        dVar.mo3298a(": ");
        dVar.mo3298a("$Version=");
        dVar.mo3298a(Integer.toString(i));
        for (C0642c a : list) {
            dVar.mo3298a("; ");
            mo3037a(dVar, a, i);
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new C0829q(dVar));
        return arrayList;
    }

    /* renamed from: a */
    public List<C0642c> mo2763a(C0576e eVar, C0645f fVar) {
        C0870a.m3042a(eVar, "Header");
        C0870a.m3042a(fVar, "Cookie origin");
        if (eVar.getName().equalsIgnoreCase("Set-Cookie")) {
            return mo3036a(eVar.getElements(), fVar);
        }
        throw new C0653n("Unrecognized cookie header '" + eVar.toString() + "'");
    }

    /* renamed from: a */
    public void mo2764a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        String name = cVar.getName();
        if (name.indexOf(32) != -1) {
            throw new C0647h("Cookie name may not contain blanks");
        } else if (!name.startsWith("$")) {
            super.mo2764a(cVar, fVar);
        } else {
            throw new C0647h("Cookie name may not start with $");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3037a(C0873d dVar, C0642c cVar, int i) {
        mo3048a(dVar, cVar.getName(), cVar.getValue(), i);
        if (cVar.getPath() != null && (cVar instanceof C0640a) && ((C0640a) cVar).containsAttribute("path")) {
            dVar.mo3298a("; ");
            mo3048a(dVar, "$Path", cVar.getPath(), i);
        }
        if (cVar.getDomain() != null && (cVar instanceof C0640a) && ((C0640a) cVar).containsAttribute("domain")) {
            dVar.mo3298a("; ");
            mo3048a(dVar, "$Domain", cVar.getDomain(), i);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3048a(C0873d dVar, String str, String str2, int i) {
        dVar.mo3298a(str);
        dVar.mo3298a("=");
        if (str2 == null) {
            return;
        }
        if (i > 0) {
            dVar.append('\"');
            dVar.mo3298a(str2);
            dVar.append('\"');
            return;
        }
        dVar.mo3298a(str2);
    }

    public List<C0576e> formatCookies(List<C0642c> list) {
        C0870a.m3043a(list, "List of cookies");
        if (list.size() > 1) {
            ArrayList arrayList = new ArrayList(list);
            Collections.sort(arrayList, C0646g.f1948a);
            list = arrayList;
        }
        return this.f2267c ? m2836b(list) : m2835a(list);
    }

    public int getVersion() {
        return 1;
    }

    public C0576e getVersionHeader() {
        return null;
    }

    public String toString() {
        return "rfc2109";
    }
}
