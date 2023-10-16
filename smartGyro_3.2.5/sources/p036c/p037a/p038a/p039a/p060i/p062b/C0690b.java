package p036c.p037a.p038a.p039a.p060i.p062b;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p040a.C0495a;
import p036c.p037a.p038a.p039a.p040a.C0497c;
import p036c.p037a.p038a.p039a.p040a.C0502h;
import p036c.p037a.p038a.p039a.p040a.C0504j;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p041b.C0515a;
import p036c.p037a.p038a.p039a.p041b.C0518b;
import p036c.p037a.p038a.p039a.p041b.C0520c;
import p036c.p037a.p038a.p039a.p041b.C0560i;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.b.b */
class C0690b implements C0520c {

    /* renamed from: a */
    public C0668b f2060a;

    /* renamed from: b */
    private final C0518b f2061b;

    /* renamed from: a */
    private boolean m2532a(C0497c cVar) {
        if (cVar == null || !cVar.isComplete()) {
            return false;
        }
        String schemeName = cVar.getSchemeName();
        return schemeName.equalsIgnoreCase("Basic") || schemeName.equalsIgnoreCase("Digest");
    }

    /* renamed from: a */
    public C0518b mo2886a() {
        return this.f2061b;
    }

    /* renamed from: a */
    public Map<String, C0576e> mo2535a(C0867o oVar, C0883t tVar, C0855e eVar) {
        return this.f2061b.mo2533a(tVar, eVar);
    }

    /* renamed from: a */
    public Queue<C0495a> mo2536a(Map<String, C0576e> map, C0867o oVar, C0883t tVar, C0855e eVar) {
        C0870a.m3042a(map, "Map of auth challenges");
        C0870a.m3042a(oVar, "Host");
        C0870a.m3042a(tVar, "HTTP response");
        C0870a.m3042a(eVar, "HTTP context");
        LinkedList linkedList = new LinkedList();
        C0560i iVar = (C0560i) eVar.getAttribute("http.auth.credentials-provider");
        if (iVar == null) {
            this.f2060a.mo2803a("Credentials provider not set in the context");
            return linkedList;
        }
        try {
            C0497c a = this.f2061b.mo2532a(map, tVar, eVar);
            a.mo2463a(map.get(a.getSchemeName().toLowerCase(Locale.ROOT)));
            C0508n a2 = iVar.mo2598a(new C0502h(oVar.mo3271b(), oVar.mo3272c(), a.getRealm(), a.getSchemeName()));
            if (a2 != null) {
                linkedList.add(new C0495a(a, a2));
            }
            return linkedList;
        } catch (C0504j e) {
            if (this.f2060a.mo2812d()) {
                this.f2060a.mo2807b(e.getMessage(), e);
            }
            return linkedList;
        }
    }

    /* renamed from: a */
    public void mo2537a(C0867o oVar, C0497c cVar, C0855e eVar) {
        C0515a aVar = (C0515a) eVar.getAttribute("http.auth.auth-cache");
        if (aVar != null) {
            if (this.f2060a.mo2805a()) {
                C0668b bVar = this.f2060a;
                bVar.mo2803a("Removing from cache '" + cVar.getSchemeName() + "' auth scheme for " + oVar);
            }
            aVar.mo2506a(oVar);
        }
    }

    /* renamed from: b */
    public void mo2538b(C0867o oVar, C0497c cVar, C0855e eVar) {
        C0515a aVar = (C0515a) eVar.getAttribute("http.auth.auth-cache");
        if (m2532a(cVar)) {
            if (aVar == null) {
                aVar = new C0692d();
                eVar.setAttribute("http.auth.auth-cache", aVar);
            }
            if (this.f2060a.mo2805a()) {
                C0668b bVar = this.f2060a;
                bVar.mo2803a("Caching '" + cVar.getSchemeName() + "' auth scheme for " + oVar);
            }
            aVar.mo2507a(oVar, cVar);
        }
    }

    /* renamed from: b */
    public boolean mo2539b(C0867o oVar, C0883t tVar, C0855e eVar) {
        return this.f2061b.mo2534b(tVar, eVar);
    }
}
