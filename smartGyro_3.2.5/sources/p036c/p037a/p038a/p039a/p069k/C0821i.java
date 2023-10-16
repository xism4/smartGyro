package p036c.p037a.p038a.p039a.p069k;

import java.util.Locale;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0489E;
import p036c.p037a.p038a.p039a.C0491G;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.k.i */
public class C0821i extends C0813a implements C0883t {

    /* renamed from: c */
    private C0491G f2369c;

    /* renamed from: d */
    private C0488D f2370d;

    /* renamed from: e */
    private int f2371e;

    /* renamed from: f */
    private String f2372f;

    /* renamed from: g */
    private C0837l f2373g;

    /* renamed from: h */
    private final C0489E f2374h;

    /* renamed from: i */
    private Locale f2375i;

    public C0821i(C0491G g, C0489E e, Locale locale) {
        C0870a.m3042a(g, "Status line");
        this.f2369c = g;
        this.f2370d = g.getProtocolVersion();
        this.f2371e = g.getStatusCode();
        this.f2372f = g.getReasonPhrase();
        this.f2374h = e;
        this.f2375i = locale;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo3157a(int i) {
        C0489E e = this.f2374h;
        if (e == null) {
            return null;
        }
        Locale locale = this.f2375i;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return e.getReason(i, locale);
    }

    /* renamed from: a */
    public void mo3158a(C0837l lVar) {
        this.f2373g = lVar;
    }

    public C0837l getEntity() {
        return this.f2373g;
    }

    public C0488D getProtocolVersion() {
        return this.f2370d;
    }

    public C0491G getStatusLine() {
        if (this.f2369c == null) {
            C0488D d = this.f2370d;
            if (d == null) {
                d = C0886w.f2446f;
            }
            int i = this.f2371e;
            String str = this.f2372f;
            if (str == null) {
                str = mo3157a(i);
            }
            this.f2369c = new C0827o(d, i, str);
        }
        return this.f2369c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatusLine());
        sb.append(' ');
        sb.append(this.f2344a);
        if (this.f2373g != null) {
            sb.append(' ');
            sb.append(this.f2373g);
        }
        return sb.toString();
    }
}
