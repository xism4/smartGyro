package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0643d;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.q */
public abstract class C0773q extends C0757b {
    public C0773q() {
    }

    protected C0773q(C0641b... bVarArr) {
        super(bVarArr);
    }

    /* renamed from: a */
    protected static String m2810a(C0645f fVar) {
        return fVar.mo2756a();
    }

    /* renamed from: b */
    protected static String m2811b(C0645f fVar) {
        String b = fVar.mo2757b();
        int lastIndexOf = b.lastIndexOf(47);
        if (lastIndexOf < 0) {
            return b;
        }
        if (lastIndexOf == 0) {
            lastIndexOf = 1;
        }
        return b.substring(0, lastIndexOf);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public List<C0642c> mo3036a(C0639f[] fVarArr, C0645f fVar) {
        ArrayList arrayList = new ArrayList(fVarArr.length);
        for (C0639f fVar2 : fVarArr) {
            String name = fVar2.getName();
            String value = fVar2.getValue();
            if (name == null || name.isEmpty()) {
                throw new C0653n("Cookie name may not be empty");
            }
            C0759d dVar = new C0759d(name, value);
            dVar.setPath(m2811b(fVar));
            dVar.setDomain(m2810a(fVar));
            C0889z[] parameters = fVar2.getParameters();
            for (int length = parameters.length - 1; length >= 0; length--) {
                C0889z zVar = parameters[length];
                String lowerCase = zVar.getName().toLowerCase(Locale.ROOT);
                dVar.mo3042a(lowerCase, zVar.getValue());
                C0643d a = mo3039a(lowerCase);
                if (a != null) {
                    a.mo2752a((C0655p) dVar, zVar.getValue());
                }
            }
            arrayList.add(dVar);
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo2764a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        for (C0643d a : mo3040a()) {
            a.mo2751a(cVar, fVar);
        }
    }

    /* renamed from: b */
    public boolean mo2765b(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        for (C0643d b : mo3040a()) {
            if (!b.mo2753b(cVar, fVar)) {
                return false;
            }
        }
        return true;
    }
}
