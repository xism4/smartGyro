package p036c.p037a.p038a.p039a.p069k;

import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0490F;
import p036c.p037a.p038a.p039a.C0491G;
import p036c.p037a.p038a.p039a.C0572d;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.j */
public class C0822j implements C0833u {
    @Deprecated

    /* renamed from: a */
    public static final C0822j f2376a = new C0822j();

    /* renamed from: b */
    public static final C0822j f2377b = new C0822j();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo3162a(C0488D d) {
        return d.mo2444c().length() + 4;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0873d mo3163a(C0873d dVar) {
        if (dVar == null) {
            return new C0873d(64);
        }
        dVar.clear();
        return dVar;
    }

    /* renamed from: a */
    public C0873d mo3164a(C0873d dVar, C0488D d) {
        C0870a.m3042a(d, "Protocol version");
        int a = mo3162a(d);
        if (dVar == null) {
            dVar = new C0873d(a);
        } else {
            dVar.mo3295a(a);
        }
        dVar.mo3298a(d.mo2444c());
        dVar.append('/');
        dVar.mo3298a(Integer.toString(d.mo2439a()));
        dVar.append('.');
        dVar.mo3298a(Integer.toString(d.mo2442b()));
        return dVar;
    }

    /* renamed from: a */
    public C0873d mo3165a(C0873d dVar, C0490F f) {
        C0870a.m3042a(f, "Request line");
        C0873d a = mo3163a(dVar);
        mo3169b(a, f);
        return a;
    }

    /* renamed from: a */
    public C0873d mo3166a(C0873d dVar, C0576e eVar) {
        C0870a.m3042a(eVar, "Header");
        if (eVar instanceof C0572d) {
            return ((C0572d) eVar).getBuffer();
        }
        C0873d a = mo3163a(dVar);
        mo3170b(a, eVar);
        return a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3167a(C0873d dVar, C0491G g) {
        int a = mo3162a(g.getProtocolVersion()) + 1 + 3 + 1;
        String reasonPhrase = g.getReasonPhrase();
        if (reasonPhrase != null) {
            a += reasonPhrase.length();
        }
        dVar.mo3295a(a);
        mo3164a(dVar, g.getProtocolVersion());
        dVar.append(' ');
        dVar.mo3298a(Integer.toString(g.getStatusCode()));
        dVar.append(' ');
        if (reasonPhrase != null) {
            dVar.mo3298a(reasonPhrase);
        }
    }

    /* renamed from: b */
    public C0873d mo3168b(C0873d dVar, C0491G g) {
        C0870a.m3042a(g, "Status line");
        C0873d a = mo3163a(dVar);
        mo3167a(a, g);
        return a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo3169b(C0873d dVar, C0490F f) {
        String method = f.getMethod();
        String uri = f.getUri();
        dVar.mo3295a(method.length() + 1 + uri.length() + 1 + mo3162a(f.getProtocolVersion()));
        dVar.mo3298a(method);
        dVar.append(' ');
        dVar.mo3298a(uri);
        dVar.append(' ');
        mo3164a(dVar, f.getProtocolVersion());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo3170b(C0873d dVar, C0576e eVar) {
        String name = eVar.getName();
        String value = eVar.getValue();
        int length = name.length() + 2;
        if (value != null) {
            length += value.length();
        }
        dVar.mo3295a(length);
        dVar.mo3298a(name);
        dVar.mo3298a(": ");
        if (value != null) {
            dVar.mo3298a(value);
        }
    }
}
