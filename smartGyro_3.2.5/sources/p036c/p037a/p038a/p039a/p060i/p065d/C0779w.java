package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.ArrayList;
import java.util.List;
import p036c.p037a.p038a.p039a.C0572d;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p069k.C0829q;
import p036c.p037a.p038a.p039a.p069k.C0835w;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.d.w */
public class C0779w extends C0773q {
    public C0779w() {
        this((String[]) null);
    }

    C0779w(C0641b... bVarArr) {
        super(bVarArr);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0779w(java.lang.String[] r4) {
        /*
            r3 = this;
            r0 = 5
            c.a.a.a.f.b[] r0 = new p036c.p037a.p038a.p039a.p057f.C0641b[r0]
            c.a.a.a.i.d.i r1 = new c.a.a.a.i.d.i
            r1.<init>()
            r2 = 0
            r0[r2] = r1
            c.a.a.a.i.d.u r1 = new c.a.a.a.i.d.u
            r1.<init>()
            r2 = 1
            r0[r2] = r1
            c.a.a.a.i.d.j r1 = new c.a.a.a.i.d.j
            r1.<init>()
            r2 = 2
            r0[r2] = r1
            c.a.a.a.i.d.e r1 = new c.a.a.a.i.d.e
            r1.<init>()
            r2 = 3
            r0[r2] = r1
            c.a.a.a.i.d.g r1 = new c.a.a.a.i.d.g
            if (r4 == 0) goto L_0x002e
            java.lang.Object r4 = r4.clone()
            java.lang.String[] r4 = (java.lang.String[]) r4
            goto L_0x0034
        L_0x002e:
            java.lang.String r4 = "EEE, dd-MMM-yy HH:mm:ss z"
            java.lang.String[] r4 = new java.lang.String[]{r4}
        L_0x0034:
            r1.<init>(r4)
            r4 = 4
            r0[r4] = r1
            r3.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p065d.C0779w.<init>(java.lang.String[]):void");
    }

    /* renamed from: a */
    public List<C0642c> mo2763a(C0576e eVar, C0645f fVar) {
        C0835w wVar;
        C0873d dVar;
        C0870a.m3042a(eVar, "Header");
        C0870a.m3042a(fVar, "Cookie origin");
        if (eVar.getName().equalsIgnoreCase("Set-Cookie")) {
            C0778v vVar = C0778v.f2261a;
            if (eVar instanceof C0572d) {
                C0572d dVar2 = (C0572d) eVar;
                dVar = dVar2.getBuffer();
                wVar = new C0835w(dVar2.getValuePos(), dVar.length());
            } else {
                String value = eVar.getValue();
                if (value != null) {
                    dVar = new C0873d(value.length());
                    dVar.mo3298a(value);
                    wVar = new C0835w(0, dVar.length());
                } else {
                    throw new C0653n("Header value is null");
                }
            }
            return mo3036a(new C0639f[]{vVar.mo3046a(dVar, wVar)}, fVar);
        }
        throw new C0653n("Unrecognized cookie header '" + eVar.toString() + "'");
    }

    public List<C0576e> formatCookies(List<C0642c> list) {
        C0870a.m3043a(list, "List of cookies");
        C0873d dVar = new C0873d(list.size() * 20);
        dVar.mo3298a("Cookie");
        dVar.mo3298a(": ");
        for (int i = 0; i < list.size(); i++) {
            C0642c cVar = list.get(i);
            if (i > 0) {
                dVar.mo3298a("; ");
            }
            dVar.mo3298a(cVar.getName());
            String value = cVar.getValue();
            if (value != null) {
                dVar.mo3298a("=");
                dVar.mo3298a(value);
            }
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new C0829q(dVar));
        return arrayList;
    }

    public int getVersion() {
        return 0;
    }

    public C0576e getVersionHeader() {
        return null;
    }

    public String toString() {
        return "netscape";
    }
}
