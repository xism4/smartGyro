package p036c.p037a.p038a.p039a.p060i.p067f;

import p036c.p037a.p038a.p039a.C0664h;
import p036c.p037a.p038a.p039a.C0880q;
import p036c.p037a.p038a.p039a.p068j.C0808d;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p069k.C0822j;
import p036c.p037a.p038a.p039a.p069k.C0833u;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.f.b */
public abstract class C0790b<T extends C0880q> implements C0808d<T> {

    /* renamed from: a */
    protected final C0811g f2286a;

    /* renamed from: b */
    protected final C0873d f2287b = new C0873d(128);

    /* renamed from: c */
    protected final C0833u f2288c;

    @Deprecated
    public C0790b(C0811g gVar, C0833u uVar, C0844g gVar2) {
        C0870a.m3042a(gVar, "Session input buffer");
        this.f2286a = gVar;
        this.f2288c = uVar == null ? C0822j.f2377b : uVar;
    }

    /* renamed from: a */
    public void mo3059a(T t) {
        C0870a.m3042a(t, "HTTP message");
        mo3060b(t);
        C0664h headerIterator = t.headerIterator();
        while (headerIterator.hasNext()) {
            this.f2286a.mo3018a(this.f2288c.mo3166a(this.f2287b, headerIterator.nextHeader()));
        }
        this.f2287b.clear();
        this.f2286a.mo3018a(this.f2287b);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo3060b(T t);
}
