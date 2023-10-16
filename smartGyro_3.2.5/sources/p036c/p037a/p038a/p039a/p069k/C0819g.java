package p036c.p037a.p038a.p039a.p069k;

import java.util.ArrayList;
import java.util.BitSet;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.g */
public class C0819g implements C0832t {
    @Deprecated

    /* renamed from: a */
    public static final C0819g f2361a = new C0819g();

    /* renamed from: b */
    public static final C0819g f2362b = new C0819g();

    /* renamed from: c */
    private static final BitSet f2363c = C0836x.m2968a(61, 59, 44);

    /* renamed from: d */
    private static final BitSet f2364d = C0836x.m2968a(59, 44);

    /* renamed from: e */
    private final C0836x f2365e = C0836x.f2405a;

    /* renamed from: a */
    public static C0639f[] m2910a(String str, C0832t tVar) {
        C0870a.m3042a(str, "Value");
        C0873d dVar = new C0873d(str.length());
        dVar.mo3298a(str);
        C0835w wVar = new C0835w(0, str.length());
        if (tVar == null) {
            tVar = f2362b;
        }
        return tVar.mo3152a(dVar, wVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0639f mo3150a(String str, String str2, C0889z[] zVarArr) {
        return new C0815c(str, str2, zVarArr);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0889z mo3151a(String str, String str2) {
        return new C0825m(str, str2);
    }

    /* renamed from: a */
    public C0639f[] mo3152a(C0873d dVar, C0835w wVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0870a.m3042a(wVar, "Parser cursor");
        ArrayList arrayList = new ArrayList();
        while (!wVar.mo3219a()) {
            C0639f b = mo3153b(dVar, wVar);
            if (b.getName().length() != 0 || b.getValue() != null) {
                arrayList.add(b);
            }
        }
        return (C0639f[]) arrayList.toArray(new C0639f[arrayList.size()]);
    }

    /* renamed from: b */
    public C0639f mo3153b(C0873d dVar, C0835w wVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0870a.m3042a(wVar, "Parser cursor");
        C0889z c = mo3154c(dVar, wVar);
        return mo3150a(c.getName(), c.getValue(), (wVar.mo3219a() || dVar.charAt(wVar.mo3220b() + -1) == ',') ? null : mo3155d(dVar, wVar));
    }

    /* renamed from: c */
    public C0889z mo3154c(C0873d dVar, C0835w wVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0870a.m3042a(wVar, "Parser cursor");
        String a = this.f2365e.mo3223a(dVar, wVar, f2363c);
        if (wVar.mo3219a()) {
            return new C0825m(a, (String) null);
        }
        char charAt = dVar.charAt(wVar.mo3220b());
        wVar.mo3218a(wVar.mo3220b() + 1);
        if (charAt != '=') {
            return mo3151a(a, (String) null);
        }
        String b = this.f2365e.mo3227b(dVar, wVar, f2364d);
        if (!wVar.mo3219a()) {
            wVar.mo3218a(wVar.mo3220b() + 1);
        }
        return mo3151a(a, b);
    }

    /* renamed from: d */
    public C0889z[] mo3155d(C0873d dVar, C0835w wVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0870a.m3042a(wVar, "Parser cursor");
        this.f2365e.mo3224a(dVar, wVar);
        ArrayList arrayList = new ArrayList();
        while (!wVar.mo3219a()) {
            arrayList.add(mo3154c(dVar, wVar));
            if (dVar.charAt(wVar.mo3220b() - 1) == ',') {
                break;
            }
        }
        return (C0889z[]) arrayList.toArray(new C0889z[arrayList.size()]);
    }
}
