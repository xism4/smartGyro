package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p057f.C0640a;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0643d;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p069k.C0829q;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.d.G */
public class C0753G extends C0782z {
    public C0753G() {
        this((String[]) null, false);
    }

    C0753G(boolean z, C0641b... bVarArr) {
        super(z, bVarArr);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0753G(java.lang.String[] r4, boolean r5) {
        /*
            r3 = this;
            r0 = 10
            c.a.a.a.f.b[] r0 = new p036c.p037a.p038a.p039a.p057f.C0641b[r0]
            c.a.a.a.i.d.I r1 = new c.a.a.a.i.d.I
            r1.<init>()
            r2 = 0
            r0[r2] = r1
            c.a.a.a.i.d.i r1 = new c.a.a.a.i.d.i
            r1.<init>()
            r2 = 1
            r0[r2] = r1
            c.a.a.a.i.d.E r1 = new c.a.a.a.i.d.E
            r1.<init>()
            r2 = 2
            r0[r2] = r1
            c.a.a.a.i.d.F r1 = new c.a.a.a.i.d.F
            r1.<init>()
            r2 = 3
            r0[r2] = r1
            c.a.a.a.i.d.h r1 = new c.a.a.a.i.d.h
            r1.<init>()
            r2 = 4
            r0[r2] = r1
            c.a.a.a.i.d.j r1 = new c.a.a.a.i.d.j
            r1.<init>()
            r2 = 5
            r0[r2] = r1
            c.a.a.a.i.d.e r1 = new c.a.a.a.i.d.e
            r1.<init>()
            r2 = 6
            r0[r2] = r1
            c.a.a.a.i.d.g r1 = new c.a.a.a.i.d.g
            if (r4 == 0) goto L_0x0047
            java.lang.Object r4 = r4.clone()
            java.lang.String[] r4 = (java.lang.String[]) r4
            goto L_0x0049
        L_0x0047:
            java.lang.String[] r4 = p036c.p037a.p038a.p039a.p060i.p065d.C0782z.f2266b
        L_0x0049:
            r1.<init>(r4)
            r4 = 7
            r0[r4] = r1
            r4 = 8
            c.a.a.a.i.d.C r1 = new c.a.a.a.i.d.C
            r1.<init>()
            r0[r4] = r1
            r4 = 9
            c.a.a.a.i.d.D r1 = new c.a.a.a.i.d.D
            r1.<init>()
            r0[r4] = r1
            r3.<init>((boolean) r5, (p036c.p037a.p038a.p039a.p057f.C0641b[]) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p065d.C0753G.<init>(java.lang.String[], boolean):void");
    }

    /* renamed from: b */
    private List<C0642c> m2764b(C0639f[] fVarArr, C0645f fVar) {
        ArrayList arrayList = new ArrayList(fVarArr.length);
        for (C0639f fVar2 : fVarArr) {
            String name = fVar2.getName();
            String value = fVar2.getValue();
            if (name == null || name.isEmpty()) {
                throw new C0653n("Cookie name may not be empty");
            }
            C0758c cVar = new C0758c(name, value);
            cVar.setPath(C0773q.m2811b(fVar));
            cVar.setDomain(C0773q.m2810a(fVar));
            cVar.setPorts(new int[]{fVar.mo2758c()});
            C0889z[] parameters = fVar2.getParameters();
            HashMap hashMap = new HashMap(parameters.length);
            for (int length = parameters.length - 1; length >= 0; length--) {
                C0889z zVar = parameters[length];
                hashMap.put(zVar.getName().toLowerCase(Locale.ROOT), zVar);
            }
            for (Map.Entry value2 : hashMap.entrySet()) {
                C0889z zVar2 = (C0889z) value2.getValue();
                String lowerCase = zVar2.getName().toLowerCase(Locale.ROOT);
                cVar.mo3042a(lowerCase, zVar2.getValue());
                C0643d a = mo3039a(lowerCase);
                if (a != null) {
                    a.mo2752a((C0655p) cVar, zVar2.getValue());
                }
            }
            arrayList.add(cVar);
        }
        return arrayList;
    }

    /* renamed from: c */
    private static C0645f m2765c(C0645f fVar) {
        String a = fVar.mo2756a();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= a.length()) {
                z = true;
                break;
            }
            char charAt = a.charAt(i);
            if (charAt == '.' || charAt == ':') {
                break;
            }
            i++;
        }
        if (!z) {
            return fVar;
        }
        return new C0645f(a + ".local", fVar.mo2758c(), fVar.mo2757b(), fVar.mo2759d());
    }

    /* renamed from: a */
    public List<C0642c> mo2763a(C0576e eVar, C0645f fVar) {
        C0870a.m3042a(eVar, "Header");
        C0870a.m3042a(fVar, "Cookie origin");
        if (eVar.getName().equalsIgnoreCase("Set-Cookie2")) {
            return m2764b(eVar.getElements(), m2765c(fVar));
        }
        throw new C0653n("Unrecognized cookie header '" + eVar.toString() + "'");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public List<C0642c> mo3036a(C0639f[] fVarArr, C0645f fVar) {
        return m2764b(fVarArr, m2765c(fVar));
    }

    /* renamed from: a */
    public void mo2764a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        super.mo2764a(cVar, m2765c(fVar));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3037a(C0873d dVar, C0642c cVar, int i) {
        String attribute;
        int[] ports;
        super.mo3037a(dVar, cVar, i);
        if ((cVar instanceof C0640a) && (attribute = ((C0640a) cVar).getAttribute("port")) != null) {
            dVar.mo3298a("; $Port");
            dVar.mo3298a("=\"");
            if (!attribute.trim().isEmpty() && (ports = cVar.getPorts()) != null) {
                int length = ports.length;
                for (int i2 = 0; i2 < length; i2++) {
                    if (i2 > 0) {
                        dVar.mo3298a(",");
                    }
                    dVar.mo3298a(Integer.toString(ports[i2]));
                }
            }
            dVar.mo3298a("\"");
        }
    }

    /* renamed from: b */
    public boolean mo2765b(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        return super.mo2765b(cVar, m2765c(fVar));
    }

    public int getVersion() {
        return 1;
    }

    public C0576e getVersionHeader() {
        C0873d dVar = new C0873d(40);
        dVar.mo3298a("Cookie2");
        dVar.mo3298a(": ");
        dVar.mo3298a("$Version=");
        dVar.mo3298a(Integer.toString(getVersion()));
        return new C0829q(dVar);
    }

    public String toString() {
        return "rfc2965";
    }
}
