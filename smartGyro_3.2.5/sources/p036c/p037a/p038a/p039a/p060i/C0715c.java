package p036c.p037a.p038a.p039a.p060i;

import java.util.Locale;
import p036c.p037a.p038a.p039a.C0489E;
import p036c.p037a.p038a.p039a.C0491G;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0884u;
import p036c.p037a.p038a.p039a.p069k.C0821i;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.c */
public class C0715c implements C0884u {

    /* renamed from: a */
    public static final C0715c f2128a = new C0715c();

    /* renamed from: b */
    protected final C0489E f2129b;

    public C0715c() {
        this(C0746d.f2233a);
    }

    public C0715c(C0489E e) {
        C0870a.m3042a(e, "Reason phrase catalog");
        this.f2129b = e;
    }

    /* renamed from: a */
    public C0883t mo2931a(C0491G g, C0855e eVar) {
        C0870a.m3042a(g, "Status line");
        return new C0821i(g, this.f2129b, mo2932a(eVar));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Locale mo2932a(C0855e eVar) {
        return Locale.getDefault();
    }
}
