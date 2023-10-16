package p036c.p037a.p038a.p039a.p060i.p063c.p064a;

import java.util.LinkedList;
import java.util.Queue;
import p036c.p037a.p038a.p039a.p050e.p051a.C0581d;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.i.c.a.g */
public class C0723g {

    /* renamed from: a */
    public C0668b f2160a = new C0668b(C0723g.class);

    /* renamed from: b */
    protected final C0587b f2161b;

    /* renamed from: c */
    protected final int f2162c;

    /* renamed from: d */
    protected final C0581d f2163d;

    /* renamed from: e */
    protected final LinkedList<C0718b> f2164e;

    /* renamed from: f */
    protected final Queue<C0726j> f2165f;

    /* renamed from: g */
    protected int f2166g;

    public C0723g(C0587b bVar, C0581d dVar) {
        this.f2161b = bVar;
        this.f2163d = dVar;
        this.f2162c = dVar.mo2622a(bVar);
        this.f2164e = new LinkedList<>();
        this.f2165f = new LinkedList();
        this.f2166g = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0018  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0718b mo2968a(java.lang.Object r4) {
        /*
            r3 = this;
            java.util.LinkedList<c.a.a.a.i.c.a.b> r0 = r3.f2164e
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0032
            java.util.LinkedList<c.a.a.a.i.c.a.b> r0 = r3.f2164e
            int r1 = r0.size()
            java.util.ListIterator r0 = r0.listIterator(r1)
        L_0x0012:
            boolean r1 = r0.hasPrevious()
            if (r1 == 0) goto L_0x0032
            java.lang.Object r1 = r0.previous()
            c.a.a.a.i.c.a.b r1 = (p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0718b) r1
            java.lang.Object r2 = r1.mo2989a()
            if (r2 == 0) goto L_0x002e
            java.lang.Object r2 = r1.mo2989a()
            boolean r2 = p036c.p037a.p038a.p039a.p074p.C0877h.m3085a((java.lang.Object) r4, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x0012
        L_0x002e:
            r0.remove()
            return r1
        L_0x0032:
            int r4 = r3.mo2972b()
            if (r4 != 0) goto L_0x005c
            java.util.LinkedList<c.a.a.a.i.c.a.b> r4 = r3.f2164e
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x005c
            java.util.LinkedList<c.a.a.a.i.c.a.b> r4 = r3.f2164e
            java.lang.Object r4 = r4.remove()
            c.a.a.a.i.c.a.b r4 = (p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0718b) r4
            r4.mo2947b()
            c.a.a.a.e.q r0 = r4.mo2948c()
            r0.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x005b
        L_0x0053:
            r0 = move-exception
            c.a.a.a.h.b r1 = r3.f2160a
            java.lang.String r2 = "I/O error closing connection"
            r1.mo2804a(r2, r0)
        L_0x005b:
            return r4
        L_0x005c:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0723g.mo2968a(java.lang.Object):c.a.a.a.i.c.a.b");
    }

    /* renamed from: a */
    public void mo2969a() {
        C0871b.m3050a(this.f2166g > 0, "There is no entry that could be dropped");
        this.f2166g--;
    }

    /* renamed from: a */
    public void mo2970a(C0718b bVar) {
        C0870a.m3044a(this.f2161b.equals(bVar.mo2949d()), "Entry not planned for this pool");
        this.f2166g++;
    }

    /* renamed from: a */
    public void mo2971a(C0726j jVar) {
        C0870a.m3042a(jVar, "Waiting thread");
        this.f2165f.add(jVar);
    }

    /* renamed from: b */
    public int mo2972b() {
        return this.f2163d.mo2622a(this.f2161b) - this.f2166g;
    }

    /* renamed from: b */
    public void mo2973b(C0726j jVar) {
        if (jVar != null) {
            this.f2165f.remove(jVar);
        }
    }

    /* renamed from: b */
    public boolean mo2974b(C0718b bVar) {
        boolean remove = this.f2164e.remove(bVar);
        if (remove) {
            this.f2166g--;
        }
        return remove;
    }

    /* renamed from: c */
    public final int mo2975c() {
        return this.f2162c;
    }

    /* renamed from: c */
    public void mo2976c(C0718b bVar) {
        int i = this.f2166g;
        if (i < 1) {
            throw new IllegalStateException("No entry created for this pool. " + this.f2161b);
        } else if (i > this.f2164e.size()) {
            this.f2164e.add(bVar);
        } else {
            throw new IllegalStateException("No entry allocated from this pool. " + this.f2161b);
        }
    }

    /* renamed from: d */
    public final C0587b mo2977d() {
        return this.f2161b;
    }

    /* renamed from: e */
    public boolean mo2978e() {
        return !this.f2165f.isEmpty();
    }

    /* renamed from: f */
    public boolean mo2979f() {
        return this.f2166g < 1 && this.f2165f.isEmpty();
    }

    /* renamed from: g */
    public C0726j mo2980g() {
        return this.f2165f.peek();
    }
}
