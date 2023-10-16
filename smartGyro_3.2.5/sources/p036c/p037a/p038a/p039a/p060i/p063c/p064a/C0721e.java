package p036c.p037a.p038a.p039a.p060i.p063c.p064a;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import p036c.p037a.p038a.p039a.p050e.C0607d;
import p036c.p037a.p038a.p039a.p050e.C0627h;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p050e.p051a.C0580c;
import p036c.p037a.p038a.p039a.p050e.p051a.C0581d;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.i.c.a.e */
public class C0721e extends C0717a {

    /* renamed from: e */
    public C0668b f2147e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public final Lock f2148f;

    /* renamed from: g */
    protected final C0607d f2149g;

    /* renamed from: h */
    protected final C0581d f2150h;

    /* renamed from: i */
    protected final Set<C0718b> f2151i;

    /* renamed from: j */
    protected final Queue<C0718b> f2152j;

    /* renamed from: k */
    protected final Queue<C0726j> f2153k;

    /* renamed from: l */
    protected final Map<C0587b, C0723g> f2154l;

    /* renamed from: m */
    private final long f2155m;

    /* renamed from: n */
    private final TimeUnit f2156n;

    /* renamed from: o */
    protected volatile boolean f2157o;

    /* renamed from: p */
    protected volatile int f2158p;

    /* renamed from: q */
    protected volatile int f2159q;

    public C0721e(C0607d dVar, C0581d dVar2, int i) {
        this(dVar, dVar2, i, -1, TimeUnit.MILLISECONDS);
    }

    public C0721e(C0607d dVar, C0581d dVar2, int i, long j, TimeUnit timeUnit) {
        this.f2147e = new C0668b(C0721e.class);
        C0870a.m3042a(dVar, "Connection operator");
        C0870a.m3042a(dVar2, "Connections per route");
        this.f2148f = this.f2136b;
        this.f2151i = this.f2137c;
        this.f2149g = dVar;
        this.f2150h = dVar2;
        this.f2158p = i;
        this.f2152j = mo2960a();
        this.f2153k = mo2965c();
        this.f2154l = mo2964b();
        this.f2155m = j;
        this.f2156n = timeUnit;
    }

    @Deprecated
    public C0721e(C0607d dVar, C0844g gVar) {
        this(dVar, C0580c.m2290a(gVar), C0580c.m2294b(gVar));
    }

    /* renamed from: b */
    private void m2635b(C0718b bVar) {
        C0636q c = bVar.mo2948c();
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                this.f2147e.mo2804a("I/O error closing connection", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0718b mo2953a(C0587b bVar, Object obj, long j, TimeUnit timeUnit, C0727k kVar) {
        C0723g a;
        C0726j jVar;
        C0607d dVar;
        C0718b bVar2 = null;
        Date date = j > 0 ? new Date(System.currentTimeMillis() + timeUnit.toMillis(j)) : null;
        this.f2148f.lock();
        try {
            a = mo2958a(bVar, true);
            jVar = null;
            while (true) {
                if (bVar2 != null) {
                    break;
                }
                boolean z = false;
                C0871b.m3050a(!this.f2157o, "Connection pool shut down");
                if (this.f2147e.mo2805a()) {
                    this.f2147e.mo2803a("[" + bVar + "] total kept alive: " + this.f2152j.size() + ", total issued: " + this.f2151i.size() + ", total allocated: " + this.f2159q + " out of " + this.f2158p);
                }
                bVar2 = mo2955a(a, obj);
                if (bVar2 != null) {
                    break;
                }
                if (a.mo2972b() > 0) {
                    z = true;
                }
                if (this.f2147e.mo2805a()) {
                    this.f2147e.mo2803a("Available capacity: " + a.mo2972b() + " out of " + a.mo2975c() + " [" + bVar + "][" + obj + "]");
                }
                if (z && this.f2159q < this.f2158p) {
                    dVar = this.f2149g;
                } else if (!z || this.f2152j.isEmpty()) {
                    if (this.f2147e.mo2805a()) {
                        this.f2147e.mo2803a("Need to wait for connection [" + bVar + "][" + obj + "]");
                    }
                    if (jVar == null) {
                        jVar = mo2959a(this.f2148f.newCondition(), a);
                        kVar.mo2988a(jVar);
                    }
                    a.mo2971a(jVar);
                    this.f2153k.add(jVar);
                    boolean a2 = jVar.mo2985a(date);
                    a.mo2973b(jVar);
                    this.f2153k.remove(jVar);
                    if (!a2 && date != null) {
                        if (date.getTime() <= System.currentTimeMillis()) {
                            throw new C0627h("Timeout waiting for connection from pool");
                        }
                    }
                } else {
                    mo2966d();
                    a = mo2958a(bVar, true);
                    dVar = this.f2149g;
                }
                bVar2 = mo2954a(a, dVar);
            }
            this.f2148f.unlock();
            return bVar2;
        } catch (Throwable th) {
            this.f2148f.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0718b mo2954a(C0723g gVar, C0607d dVar) {
        if (this.f2147e.mo2805a()) {
            this.f2147e.mo2803a("Creating new connection [" + gVar.mo2977d() + "]");
        }
        C0718b bVar = new C0718b(dVar, gVar.mo2977d(), this.f2155m, this.f2156n);
        this.f2148f.lock();
        try {
            gVar.mo2970a(bVar);
            this.f2159q++;
            this.f2151i.add(bVar);
            return bVar;
        } finally {
            this.f2148f.unlock();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0718b mo2955a(C0723g gVar, Object obj) {
        this.f2148f.lock();
        boolean z = false;
        C0718b bVar = null;
        while (!z) {
            try {
                bVar = gVar.mo2968a(obj);
                if (bVar != null) {
                    if (this.f2147e.mo2805a()) {
                        this.f2147e.mo2803a("Getting free connection [" + gVar.mo2977d() + "][" + obj + "]");
                    }
                    this.f2152j.remove(bVar);
                    if (bVar.mo2946a(System.currentTimeMillis())) {
                        if (this.f2147e.mo2805a()) {
                            this.f2147e.mo2803a("Closing expired free connection [" + gVar.mo2977d() + "][" + obj + "]");
                        }
                        m2635b(bVar);
                        gVar.mo2969a();
                        this.f2159q--;
                    } else {
                        this.f2151i.add(bVar);
                    }
                } else if (this.f2147e.mo2805a()) {
                    this.f2147e.mo2803a("No free connections [" + gVar.mo2977d() + "][" + obj + "]");
                }
                z = true;
            } catch (Throwable th) {
                this.f2148f.unlock();
                throw th;
            }
        }
        this.f2148f.unlock();
        return bVar;
    }

    /* renamed from: a */
    public C0722f mo2956a(C0587b bVar, Object obj) {
        return new C0720d(this, new C0727k(), bVar, obj);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0723g mo2957a(C0587b bVar) {
        return new C0723g(bVar, this.f2150h);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0723g mo2958a(C0587b bVar, boolean z) {
        this.f2148f.lock();
        try {
            C0723g gVar = this.f2154l.get(bVar);
            if (gVar == null && z) {
                gVar = mo2957a(bVar);
                this.f2154l.put(bVar, gVar);
            }
            return gVar;
        } finally {
            this.f2148f.unlock();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0726j mo2959a(Condition condition, C0723g gVar) {
        return new C0726j(condition, gVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Queue<C0718b> mo2960a() {
        return new LinkedList();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2961a(C0718b bVar) {
        C0587b d = bVar.mo2949d();
        if (this.f2147e.mo2805a()) {
            this.f2147e.mo2803a("Deleting connection [" + d + "][" + bVar.mo2989a() + "]");
        }
        this.f2148f.lock();
        try {
            m2635b(bVar);
            C0723g a = mo2958a(d, true);
            a.mo2974b(bVar);
            this.f2159q--;
            if (a.mo2979f()) {
                this.f2154l.remove(d);
            }
        } finally {
            this.f2148f.unlock();
        }
    }

    /* renamed from: a */
    public void mo2962a(C0718b bVar, boolean z, long j, TimeUnit timeUnit) {
        String str;
        C0587b d = bVar.mo2949d();
        if (this.f2147e.mo2805a()) {
            this.f2147e.mo2803a("Releasing connection [" + d + "][" + bVar.mo2989a() + "]");
        }
        this.f2148f.lock();
        try {
            if (this.f2157o) {
                m2635b(bVar);
                return;
            }
            this.f2151i.remove(bVar);
            C0723g a = mo2958a(d, true);
            if (!z || a.mo2972b() < 0) {
                m2635b(bVar);
                a.mo2969a();
                this.f2159q--;
            } else {
                if (this.f2147e.mo2805a()) {
                    if (j > 0) {
                        str = "for " + j + " " + timeUnit;
                    } else {
                        str = "indefinitely";
                    }
                    this.f2147e.mo2803a("Pooling connection [" + d + "][" + bVar.mo2989a() + "]; keep alive " + str);
                }
                a.mo2976c(bVar);
                bVar.mo2945a(j, timeUnit);
                this.f2152j.add(bVar);
            }
            mo2963a(a);
            this.f2148f.unlock();
        } finally {
            this.f2148f.unlock();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006b A[Catch:{ all -> 0x0074 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo2963a(p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0723g r4) {
        /*
            r3 = this;
            java.util.concurrent.locks.Lock r0 = r3.f2148f
            r0.lock()
            if (r4 == 0) goto L_0x0039
            boolean r0 = r4.mo2978e()     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x0039
            c.a.a.a.h.b r0 = r3.f2147e     // Catch:{ all -> 0x0074 }
            boolean r0 = r0.mo2805a()     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x0034
            c.a.a.a.h.b r0 = r3.f2147e     // Catch:{ all -> 0x0074 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r1.<init>()     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = "Notifying thread waiting on pool ["
            r1.append(r2)     // Catch:{ all -> 0x0074 }
            c.a.a.a.e.b.b r2 = r4.mo2977d()     // Catch:{ all -> 0x0074 }
            r1.append(r2)     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = "]"
            r1.append(r2)     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0074 }
            r0.mo2803a(r1)     // Catch:{ all -> 0x0074 }
        L_0x0034:
            c.a.a.a.i.c.a.j r4 = r4.mo2980g()     // Catch:{ all -> 0x0074 }
            goto L_0x0069
        L_0x0039:
            java.util.Queue<c.a.a.a.i.c.a.j> r4 = r3.f2153k     // Catch:{ all -> 0x0074 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x0074 }
            if (r4 != 0) goto L_0x0059
            c.a.a.a.h.b r4 = r3.f2147e     // Catch:{ all -> 0x0074 }
            boolean r4 = r4.mo2805a()     // Catch:{ all -> 0x0074 }
            if (r4 == 0) goto L_0x0050
            c.a.a.a.h.b r4 = r3.f2147e     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = "Notifying thread waiting on any pool"
            r4.mo2803a(r0)     // Catch:{ all -> 0x0074 }
        L_0x0050:
            java.util.Queue<c.a.a.a.i.c.a.j> r4 = r3.f2153k     // Catch:{ all -> 0x0074 }
            java.lang.Object r4 = r4.remove()     // Catch:{ all -> 0x0074 }
            c.a.a.a.i.c.a.j r4 = (p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0726j) r4     // Catch:{ all -> 0x0074 }
            goto L_0x0069
        L_0x0059:
            c.a.a.a.h.b r4 = r3.f2147e     // Catch:{ all -> 0x0074 }
            boolean r4 = r4.mo2805a()     // Catch:{ all -> 0x0074 }
            if (r4 == 0) goto L_0x0068
            c.a.a.a.h.b r4 = r3.f2147e     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = "Notifying no-one, there are no waiting threads"
            r4.mo2803a(r0)     // Catch:{ all -> 0x0074 }
        L_0x0068:
            r4 = 0
        L_0x0069:
            if (r4 == 0) goto L_0x006e
            r4.mo2986b()     // Catch:{ all -> 0x0074 }
        L_0x006e:
            java.util.concurrent.locks.Lock r4 = r3.f2148f
            r4.unlock()
            return
        L_0x0074:
            r4 = move-exception
            java.util.concurrent.locks.Lock r0 = r3.f2148f
            r0.unlock()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0721e.mo2963a(c.a.a.a.i.c.a.g):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Map<C0587b, C0723g> mo2964b() {
        return new HashMap();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public Queue<C0726j> mo2965c() {
        return new LinkedList();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo2966d() {
        this.f2148f.lock();
        try {
            C0718b remove = this.f2152j.remove();
            if (remove != null) {
                mo2961a(remove);
            } else if (this.f2147e.mo2805a()) {
                this.f2147e.mo2803a("No free connection to delete");
            }
        } finally {
            this.f2148f.unlock();
        }
    }

    /* renamed from: e */
    public void mo2967e() {
        this.f2148f.lock();
        try {
            if (!this.f2157o) {
                this.f2157o = true;
                Iterator<C0718b> it = this.f2151i.iterator();
                while (it.hasNext()) {
                    it.remove();
                    m2635b(it.next());
                }
                Iterator it2 = this.f2152j.iterator();
                while (it2.hasNext()) {
                    C0718b bVar = (C0718b) it2.next();
                    it2.remove();
                    if (this.f2147e.mo2805a()) {
                        C0668b bVar2 = this.f2147e;
                        bVar2.mo2803a("Closing connection [" + bVar.mo2949d() + "][" + bVar.mo2989a() + "]");
                    }
                    m2635b(bVar);
                }
                Iterator it3 = this.f2153k.iterator();
                while (it3.hasNext()) {
                    it3.remove();
                    ((C0726j) it3.next()).mo2986b();
                }
                this.f2154l.clear();
                this.f2148f.unlock();
            }
        } finally {
            this.f2148f.unlock();
        }
    }
}
