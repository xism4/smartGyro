package p036c.p037a.p038a.p039a.p060i.p061a;

import java.util.Locale;
import p036c.p037a.p038a.p039a.C0572d;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p040a.C0506l;
import p036c.p037a.p038a.p039a.p040a.C0507m;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p040a.C0510p;
import p036c.p037a.p038a.p039a.p072n.C0854d;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.a.a */
public abstract class C0671a implements C0507m {

    /* renamed from: a */
    private C0506l f2002a;

    /* renamed from: a */
    public C0576e mo2492a(C0508n nVar, C0881r rVar, C0855e eVar) {
        return mo2462a(nVar, rVar);
    }

    /* renamed from: a */
    public void mo2463a(C0576e eVar) {
        C0506l lVar;
        int i;
        C0873d dVar;
        C0870a.m3042a(eVar, "Header");
        String name = eVar.getName();
        if (name.equalsIgnoreCase("WWW-Authenticate")) {
            lVar = C0506l.TARGET;
        } else if (name.equalsIgnoreCase("Proxy-Authenticate")) {
            lVar = C0506l.PROXY;
        } else {
            throw new C0510p("Unexpected header name: " + name);
        }
        this.f2002a = lVar;
        if (eVar instanceof C0572d) {
            C0572d dVar2 = (C0572d) eVar;
            dVar = dVar2.getBuffer();
            i = dVar2.getValuePos();
        } else {
            String value = eVar.getValue();
            if (value != null) {
                dVar = new C0873d(value.length());
                dVar.mo3298a(value);
                i = 0;
            } else {
                throw new C0510p("Header value is null");
            }
        }
        while (i < dVar.length() && C0854d.m3012a(dVar.charAt(i))) {
            i++;
        }
        int i2 = i;
        while (i2 < dVar.length() && !C0854d.m3012a(dVar.charAt(i2))) {
            i2++;
        }
        String a = dVar.mo3294a(i, i2);
        if (a.equalsIgnoreCase(getSchemeName())) {
            mo2830a(dVar, i2, dVar.length());
            return;
        }
        throw new C0510p("Invalid scheme identifier: " + a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo2830a(C0873d dVar, int i, int i2);

    /* renamed from: a */
    public boolean mo2831a() {
        C0506l lVar = this.f2002a;
        return lVar != null && lVar == C0506l.PROXY;
    }

    public String toString() {
        String schemeName = getSchemeName();
        return schemeName != null ? schemeName.toUpperCase(Locale.ROOT) : super.toString();
    }
}
