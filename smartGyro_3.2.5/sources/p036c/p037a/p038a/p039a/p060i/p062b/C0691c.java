package p036c.p037a.p038a.p039a.p060i.p062b;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import p036c.p037a.p038a.p039a.C0572d;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p040a.C0495a;
import p036c.p037a.p038a.p039a.p040a.C0497c;
import p036c.p037a.p038a.p039a.p040a.C0499e;
import p036c.p037a.p038a.p039a.p040a.C0502h;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p040a.C0510p;
import p036c.p037a.p038a.p039a.p041b.C0515a;
import p036c.p037a.p038a.p039a.p041b.C0520c;
import p036c.p037a.p038a.p039a.p041b.C0560i;
import p036c.p037a.p038a.p039a.p041b.p042a.C0516a;
import p036c.p037a.p038a.p039a.p041b.p046e.C0540a;
import p036c.p037a.p038a.p039a.p049d.C0573a;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0854d;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.b.c */
abstract class C0691c implements C0520c {

    /* renamed from: a */
    private static final List<String> f2062a = Collections.unmodifiableList(Arrays.asList(new String[]{"Negotiate", "Kerberos", "NTLM", "Digest", "Basic"}));

    /* renamed from: b */
    public C0668b f2063b = new C0668b(getClass());

    /* renamed from: c */
    private final int f2064c;

    /* renamed from: d */
    private final String f2065d;

    C0691c(int i, String str) {
        this.f2064c = i;
        this.f2065d = str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract Collection<String> mo2887a(C0516a aVar);

    /* renamed from: a */
    public Map<String, C0576e> mo2535a(C0867o oVar, C0883t tVar, C0855e eVar) {
        C0873d dVar;
        int i;
        C0870a.m3042a(tVar, "HTTP response");
        C0576e[] headers = tVar.getHeaders(this.f2065d);
        HashMap hashMap = new HashMap(headers.length);
        for (C0576e eVar2 : headers) {
            if (eVar2 instanceof C0572d) {
                C0572d dVar2 = (C0572d) eVar2;
                dVar = dVar2.getBuffer();
                i = dVar2.getValuePos();
            } else {
                String value = eVar2.getValue();
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
            hashMap.put(dVar.mo3294a(i, i2).toLowerCase(Locale.ROOT), eVar2);
        }
        return hashMap;
    }

    /* renamed from: a */
    public Queue<C0495a> mo2536a(Map<String, C0576e> map, C0867o oVar, C0883t tVar, C0855e eVar) {
        C0668b bVar;
        String str;
        C0870a.m3042a(map, "Map of auth challenges");
        C0870a.m3042a(oVar, "Host");
        C0870a.m3042a(tVar, "HTTP response");
        C0870a.m3042a(eVar, "HTTP context");
        C0540a a = C0540a.m2199a(eVar);
        LinkedList linkedList = new LinkedList();
        C0573a<C0499e> f = a.mo2566f();
        if (f == null) {
            bVar = this.f2063b;
            str = "Auth scheme registry not set in the context";
        } else {
            C0560i k = a.mo2571k();
            if (k == null) {
                bVar = this.f2063b;
                str = "Credentials provider not set in the context";
            } else {
                Collection<String> a2 = mo2887a(a.mo2574n());
                if (a2 == null) {
                    a2 = f2062a;
                }
                if (this.f2063b.mo2805a()) {
                    C0668b bVar2 = this.f2063b;
                    bVar2.mo2803a("Authentication schemes in the order of preference: " + a2);
                }
                for (String str2 : a2) {
                    C0576e eVar2 = map.get(str2.toLowerCase(Locale.ROOT));
                    if (eVar2 != null) {
                        C0499e lookup = f.lookup(str2);
                        if (lookup != null) {
                            C0497c a3 = lookup.mo2469a(eVar);
                            a3.mo2463a(eVar2);
                            C0508n a4 = k.mo2598a(new C0502h(oVar.mo3271b(), oVar.mo3272c(), a3.getRealm(), a3.getSchemeName()));
                            if (a4 != null) {
                                linkedList.add(new C0495a(a3, a4));
                            }
                        } else if (this.f2063b.mo2812d()) {
                            C0668b bVar3 = this.f2063b;
                            bVar3.mo2811d("Authentication scheme " + str2 + " not supported");
                        }
                    } else if (this.f2063b.mo2805a()) {
                        C0668b bVar4 = this.f2063b;
                        bVar4.mo2803a("Challenge for " + str2 + " authentication scheme not available");
                    }
                }
                return linkedList;
            }
        }
        bVar.mo2803a(str);
        return linkedList;
    }

    /* renamed from: a */
    public void mo2537a(C0867o oVar, C0497c cVar, C0855e eVar) {
        C0870a.m3042a(oVar, "Host");
        C0870a.m3042a(eVar, "HTTP context");
        C0515a e = C0540a.m2199a(eVar).mo2565e();
        if (e != null) {
            if (this.f2063b.mo2805a()) {
                C0668b bVar = this.f2063b;
                bVar.mo2803a("Clearing cached auth scheme for " + oVar);
            }
            e.mo2506a(oVar);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo2888a(C0497c cVar) {
        if (cVar == null || !cVar.isComplete()) {
            return false;
        }
        String schemeName = cVar.getSchemeName();
        return schemeName.equalsIgnoreCase("Basic") || schemeName.equalsIgnoreCase("Digest");
    }

    /* renamed from: b */
    public void mo2538b(C0867o oVar, C0497c cVar, C0855e eVar) {
        C0870a.m3042a(oVar, "Host");
        C0870a.m3042a(cVar, "Auth scheme");
        C0870a.m3042a(eVar, "HTTP context");
        C0540a a = C0540a.m2199a(eVar);
        if (mo2888a(cVar)) {
            C0515a e = a.mo2565e();
            if (e == null) {
                e = new C0692d();
                a.mo2564a(e);
            }
            if (this.f2063b.mo2805a()) {
                C0668b bVar = this.f2063b;
                bVar.mo2803a("Caching '" + cVar.getSchemeName() + "' auth scheme for " + oVar);
            }
            e.mo2507a(oVar, cVar);
        }
    }

    /* renamed from: b */
    public boolean mo2539b(C0867o oVar, C0883t tVar, C0855e eVar) {
        C0870a.m3042a(tVar, "HTTP response");
        return tVar.getStatusLine().getStatusCode() == this.f2064c;
    }
}
