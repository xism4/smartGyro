package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.ArrayList;
import java.util.BitSet;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p069k.C0815c;
import p036c.p037a.p038a.p039a.p069k.C0825m;
import p036c.p037a.p038a.p039a.p069k.C0835w;
import p036c.p037a.p038a.p039a.p069k.C0836x;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.d.v */
public class C0778v {

    /* renamed from: a */
    public static final C0778v f2261a = new C0778v();

    /* renamed from: b */
    private static final BitSet f2262b = C0836x.m2968a(61, 59);

    /* renamed from: c */
    private static final BitSet f2263c = C0836x.m2968a(59);

    /* renamed from: d */
    private final C0836x f2264d = C0836x.f2405a;

    /* renamed from: b */
    private C0889z m2826b(C0873d dVar, C0835w wVar) {
        String a = this.f2264d.mo3223a(dVar, wVar, f2262b);
        if (wVar.mo3219a()) {
            return new C0825m(a, (String) null);
        }
        char charAt = dVar.charAt(wVar.mo3220b());
        wVar.mo3218a(wVar.mo3220b() + 1);
        if (charAt != '=') {
            return new C0825m(a, (String) null);
        }
        String a2 = this.f2264d.mo3223a(dVar, wVar, f2263c);
        if (!wVar.mo3219a()) {
            wVar.mo3218a(wVar.mo3220b() + 1);
        }
        return new C0825m(a, a2);
    }

    /* renamed from: a */
    public C0639f mo3046a(C0873d dVar, C0835w wVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0870a.m3042a(wVar, "Parser cursor");
        C0889z b = m2826b(dVar, wVar);
        ArrayList arrayList = new ArrayList();
        while (!wVar.mo3219a()) {
            arrayList.add(m2826b(dVar, wVar));
        }
        return new C0815c(b.getName(), b.getValue(), (C0889z[]) arrayList.toArray(new C0889z[arrayList.size()]));
    }
}
