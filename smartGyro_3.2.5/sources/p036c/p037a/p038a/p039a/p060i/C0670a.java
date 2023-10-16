package p036c.p037a.p038a.p039a.p060i;

import java.io.IOException;
import java.net.SocketTimeoutException;
import p036c.p037a.p038a.p039a.C0669i;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0884u;
import p036c.p037a.p038a.p039a.p060i.p066e.C0784a;
import p036c.p037a.p038a.p039a.p060i.p066e.C0785b;
import p036c.p037a.p038a.p039a.p060i.p066e.C0786c;
import p036c.p037a.p038a.p039a.p060i.p066e.C0787d;
import p036c.p037a.p038a.p039a.p060i.p067f.C0798j;
import p036c.p037a.p038a.p039a.p068j.C0806b;
import p036c.p037a.p038a.p039a.p068j.C0807c;
import p036c.p037a.p038a.p039a.p068j.C0808d;
import p036c.p037a.p038a.p039a.p068j.C0809e;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p069k.C0833u;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.a */
public abstract class C0670a implements C0669i {

    /* renamed from: a */
    private final C0785b f1994a = mo2825c();

    /* renamed from: b */
    private final C0784a f1995b = mo2824b();

    /* renamed from: c */
    private C0810f f1996c = null;

    /* renamed from: d */
    private C0811g f1997d = null;

    /* renamed from: e */
    private C0806b f1998e = null;

    /* renamed from: f */
    private C0807c<C0883t> f1999f = null;

    /* renamed from: g */
    private C0808d<C0881r> f2000g = null;

    /* renamed from: h */
    private C0783e f2001h = null;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0783e mo2819a(C0809e eVar, C0809e eVar2) {
        return new C0783e(eVar, eVar2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C0807c<C0883t> mo2820a(C0810f fVar, C0884u uVar, C0844g gVar);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0808d<C0881r> mo2821a(C0811g gVar, C0844g gVar2) {
        return new C0798j(gVar, (C0833u) null, gVar2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo2822a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2823a(C0810f fVar, C0811g gVar, C0844g gVar2) {
        C0870a.m3042a(fVar, "Input session buffer");
        this.f1996c = fVar;
        C0870a.m3042a(gVar, "Output session buffer");
        this.f1997d = gVar;
        if (fVar instanceof C0806b) {
            this.f1998e = (C0806b) fVar;
        }
        this.f1999f = mo2820a(fVar, mo2826d(), gVar2);
        this.f2000g = mo2821a(gVar, gVar2);
        this.f2001h = mo2819a(fVar.getMetrics(), gVar.getMetrics());
    }

    /* renamed from: a */
    public void mo2813a(C0848m mVar) {
        C0870a.m3042a(mVar, "HTTP request");
        mo2822a();
        if (mVar.getEntity() != null) {
            this.f1994a.mo3054a(this.f1997d, mVar, mVar.getEntity());
        }
    }

    /* renamed from: a */
    public void mo2814a(C0881r rVar) {
        C0870a.m3042a(rVar, "HTTP request");
        mo2822a();
        this.f2000g.mo3059a(rVar);
        this.f2001h.mo3049a();
    }

    /* renamed from: a */
    public void mo2815a(C0883t tVar) {
        C0870a.m3042a(tVar, "HTTP response");
        mo2822a();
        tVar.mo3158a(this.f1995b.mo3051a(this.f1996c, tVar));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0784a mo2824b() {
        return new C0784a(new C0786c());
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C0785b mo2825c() {
        return new C0785b(new C0787d());
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public C0884u mo2826d() {
        return C0715c.f2128a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo2827e() {
        this.f1997d.flush();
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public boolean mo2828f() {
        C0806b bVar = this.f1998e;
        return bVar != null && bVar.mo3013a();
    }

    public void flush() {
        mo2822a();
        mo2827e();
    }

    public boolean isResponseAvailable(int i) {
        mo2822a();
        try {
            return this.f1996c.isDataAvailable(i);
        } catch (SocketTimeoutException unused) {
            return false;
        }
    }

    public boolean isStale() {
        if (!isOpen() || mo2828f()) {
            return true;
        }
        try {
            this.f1996c.isDataAvailable(1);
            return mo2828f();
        } catch (SocketTimeoutException unused) {
            return false;
        } catch (IOException unused2) {
            return true;
        }
    }

    public C0883t receiveResponseHeader() {
        mo2822a();
        C0883t parse = this.f1999f.parse();
        if (parse.getStatusLine().getStatusCode() >= 200) {
            this.f2001h.mo3050b();
        }
        return parse;
    }
}
