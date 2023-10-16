package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import p036c.p037a.p038a.p039a.C0572d;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0643d;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p069k.C0815c;
import p036c.p037a.p038a.p039a.p069k.C0818f;
import p036c.p037a.p038a.p039a.p069k.C0829q;
import p036c.p037a.p038a.p039a.p069k.C0835w;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.d.n */
public class C0769n extends C0773q {

    /* renamed from: b */
    private static final String[] f2252b = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"};

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0769n(java.lang.String[] r4, p036c.p037a.p038a.p039a.p060i.p065d.C0770o.C0771a r5) {
        /*
            r3 = this;
            r0 = 7
            c.a.a.a.f.b[] r0 = new p036c.p037a.p038a.p039a.p057f.C0641b[r0]
            c.a.a.a.i.d.p r1 = new c.a.a.a.i.d.p
            r1.<init>()
            r2 = 0
            r0[r2] = r1
            c.a.a.a.i.d.f r1 = new c.a.a.a.i.d.f
            r1.<init>()
            r2 = 1
            r0[r2] = r1
            c.a.a.a.i.d.o$a r1 = p036c.p037a.p038a.p039a.p060i.p065d.C0770o.C0771a.SECURITYLEVEL_IE_MEDIUM
            if (r5 != r1) goto L_0x001d
            c.a.a.a.i.d.m r5 = new c.a.a.a.i.d.m
            r5.<init>()
            goto L_0x0022
        L_0x001d:
            c.a.a.a.i.d.i r5 = new c.a.a.a.i.d.i
            r5.<init>()
        L_0x0022:
            r1 = 2
            r0[r1] = r5
            r5 = 3
            c.a.a.a.i.d.h r1 = new c.a.a.a.i.d.h
            r1.<init>()
            r0[r5] = r1
            r5 = 4
            c.a.a.a.i.d.j r1 = new c.a.a.a.i.d.j
            r1.<init>()
            r0[r5] = r1
            r5 = 5
            c.a.a.a.i.d.e r1 = new c.a.a.a.i.d.e
            r1.<init>()
            r0[r5] = r1
            r5 = 6
            c.a.a.a.i.d.g r1 = new c.a.a.a.i.d.g
            if (r4 == 0) goto L_0x0049
            java.lang.Object r4 = r4.clone()
            java.lang.String[] r4 = (java.lang.String[]) r4
            goto L_0x004b
        L_0x0049:
            java.lang.String[] r4 = f2252b
        L_0x004b:
            r1.<init>(r4)
            r0[r5] = r1
            r3.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p065d.C0769n.<init>(java.lang.String[], c.a.a.a.i.d.o$a):void");
    }

    /* renamed from: b */
    private static boolean m2804b(String str) {
        return str != null && str.startsWith("\"") && str.endsWith("\"");
    }

    /* renamed from: a */
    public List<C0642c> mo2763a(C0576e eVar, C0645f fVar) {
        C0835w wVar;
        C0873d dVar;
        C0870a.m3042a(eVar, "Header");
        C0870a.m3042a(fVar, "Cookie origin");
        if (eVar.getName().equalsIgnoreCase("Set-Cookie")) {
            C0639f[] elements = eVar.getElements();
            boolean z = false;
            boolean z2 = false;
            for (C0639f fVar2 : elements) {
                if (fVar2.getParameterByName("version") != null) {
                    z2 = true;
                }
                if (fVar2.getParameterByName("expires") != null) {
                    z = true;
                }
            }
            if (!z && z2) {
                return mo3036a(elements, fVar);
            }
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
            C0639f a = vVar.mo3046a(dVar, wVar);
            String name = a.getName();
            String value2 = a.getValue();
            if (name == null || name.isEmpty()) {
                throw new C0653n("Cookie name may not be empty");
            }
            C0759d dVar3 = new C0759d(name, value2);
            dVar3.setPath(C0773q.m2811b(fVar));
            dVar3.setDomain(C0773q.m2810a(fVar));
            C0889z[] parameters = a.getParameters();
            for (int length = parameters.length - 1; length >= 0; length--) {
                C0889z zVar = parameters[length];
                String lowerCase = zVar.getName().toLowerCase(Locale.ROOT);
                dVar3.mo3042a(lowerCase, zVar.getValue());
                C0643d a2 = mo3039a(lowerCase);
                if (a2 != null) {
                    a2.mo2752a((C0655p) dVar3, zVar.getValue());
                }
            }
            if (z) {
                dVar3.setVersion(0);
            }
            return Collections.singletonList(dVar3);
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
            String name = cVar.getName();
            String value = cVar.getValue();
            if (cVar.getVersion() <= 0 || m2804b(value)) {
                dVar.mo3298a(name);
                dVar.mo3298a("=");
                if (value != null) {
                    dVar.mo3298a(value);
                }
            } else {
                C0818f.f2360b.mo3144a(dVar, (C0639f) new C0815c(name, value), false);
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
        return "compatibility";
    }
}
