package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.List;
import p036c.p037a.p038a.p039a.C0572d;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0648i;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0654o;
import p036c.p037a.p038a.p039a.p069k.C0835w;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.d.r */
public class C0774r implements C0648i {

    /* renamed from: a */
    private final C0753G f2258a;

    /* renamed from: b */
    private final C0782z f2259b;

    /* renamed from: c */
    private final C0779w f2260c;

    public C0774r(String[] strArr, boolean z) {
        this.f2258a = new C0753G(z, new C0755I(), new C0764i(), new C0751E(), new C0752F(), new C0763h(), new C0765j(), new C0760e(), new C0749C(), new C0750D());
        this.f2259b = new C0782z(z, new C0748B(), new C0764i(), new C0781y(), new C0763h(), new C0765j(), new C0760e());
        C0641b[] bVarArr = new C0641b[5];
        bVarArr[0] = new C0761f();
        bVarArr[1] = new C0764i();
        bVarArr[2] = new C0765j();
        bVarArr[3] = new C0760e();
        bVarArr[4] = new C0762g(strArr != null ? (String[]) strArr.clone() : new String[]{"EEE, dd-MMM-yy HH:mm:ss z"});
        this.f2260c = new C0779w(bVarArr);
    }

    /* renamed from: a */
    public List<C0642c> mo2763a(C0576e eVar, C0645f fVar) {
        C0835w wVar;
        C0873d dVar;
        C0870a.m3042a(eVar, "Header");
        C0870a.m3042a(fVar, "Cookie origin");
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
            return "Set-Cookie2".equals(eVar.getName()) ? this.f2258a.mo3036a(elements, fVar) : this.f2259b.mo3036a(elements, fVar);
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
        return this.f2260c.mo3036a(new C0639f[]{vVar.mo3046a(dVar, wVar)}, fVar);
    }

    /* renamed from: a */
    public void mo2764a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        if (cVar.getVersion() <= 0) {
            this.f2260c.mo2764a(cVar, fVar);
        } else if (cVar instanceof C0654o) {
            this.f2258a.mo2764a(cVar, fVar);
        } else {
            this.f2259b.mo2764a(cVar, fVar);
        }
    }

    /* renamed from: b */
    public boolean mo2765b(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        return cVar.getVersion() > 0 ? cVar instanceof C0654o ? this.f2258a.mo2765b(cVar, fVar) : this.f2259b.mo2765b(cVar, fVar) : this.f2260c.mo2765b(cVar, fVar);
    }

    public List<C0576e> formatCookies(List<C0642c> list) {
        C0870a.m3042a(list, "List of cookies");
        int i = Integer.MAX_VALUE;
        boolean z = true;
        for (C0642c next : list) {
            if (!(next instanceof C0654o)) {
                z = false;
            }
            if (next.getVersion() < i) {
                i = next.getVersion();
            }
        }
        return i > 0 ? z ? this.f2258a.formatCookies(list) : this.f2259b.formatCookies(list) : this.f2260c.formatCookies(list);
    }

    public int getVersion() {
        return this.f2258a.getVersion();
    }

    public C0576e getVersionHeader() {
        return null;
    }
}
