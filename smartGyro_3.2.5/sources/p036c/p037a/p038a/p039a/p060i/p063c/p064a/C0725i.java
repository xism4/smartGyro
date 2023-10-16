package p036c.p037a.p038a.p039a.p060i.p063c.p064a;

import p036c.p037a.p038a.p039a.p050e.C0585b;
import p036c.p037a.p038a.p039a.p050e.C0607d;
import p036c.p037a.p038a.p039a.p050e.C0610e;
import p036c.p037a.p038a.p039a.p050e.p051a.C0582e;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p050e.p053c.C0603i;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p060i.p063c.C0734h;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.c.a.i */
public class C0725i implements C0585b {

    /* renamed from: a */
    public C0668b f2170a = new C0668b(C0725i.class);

    /* renamed from: b */
    protected final C0603i f2171b;

    /* renamed from: c */
    protected final C0717a f2172c;

    /* renamed from: d */
    protected final C0721e f2173d;

    /* renamed from: e */
    protected final C0607d f2174e;

    /* renamed from: f */
    protected final C0582e f2175f;

    @Deprecated
    public C0725i(C0844g gVar, C0603i iVar) {
        C0870a.m3042a(iVar, "Scheme registry");
        this.f2171b = iVar;
        this.f2175f = new C0582e();
        this.f2174e = mo2981a(iVar);
        this.f2173d = (C0721e) mo2982a(gVar);
        this.f2172c = this.f2173d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0607d mo2981a(C0603i iVar) {
        return new C0734h(iVar);
    }

    /* renamed from: a */
    public C0610e mo2625a(C0587b bVar, Object obj) {
        return new C0724h(this, this.f2173d.mo2956a(bVar, obj), bVar);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    /* renamed from: a */
    public C0717a mo2982a(C0844g gVar) {
        return new C0721e(this.f2174e, gVar);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:34:0x0070=Splitter:B:34:0x0070, B:19:0x0038=Splitter:B:19:0x0038} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo2626a(p036c.p037a.p038a.p039a.p050e.C0634o r8, long r9, java.util.concurrent.TimeUnit r11) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0719c
            java.lang.String r1 = "Connection class mismatch, connection not obtained from this manager"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3044a((boolean) r0, (java.lang.String) r1)
            c.a.a.a.i.c.a.c r8 = (p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0719c) r8
            c.a.a.a.i.c.b r0 = r8.mo2950f()
            if (r0 == 0) goto L_0x001d
            c.a.a.a.e.b r0 = r8.mo2935b()
            if (r0 != r7) goto L_0x0017
            r0 = 1
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            java.lang.String r1 = "Connection not obtained from this manager"
            p036c.p037a.p038a.p039a.p074p.C0871b.m3050a((boolean) r0, (java.lang.String) r1)
        L_0x001d:
            monitor-enter(r8)
            c.a.a.a.i.c.b r0 = r8.mo2950f()     // Catch:{ all -> 0x00b9 }
            r2 = r0
            c.a.a.a.i.c.a.b r2 = (p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0718b) r2     // Catch:{ all -> 0x00b9 }
            if (r2 != 0) goto L_0x0029
            monitor-exit(r8)     // Catch:{ all -> 0x00b9 }
            return
        L_0x0029:
            boolean r0 = r8.isOpen()     // Catch:{ IOException -> 0x0060 }
            if (r0 == 0) goto L_0x0038
            boolean r0 = r8.mo2937d()     // Catch:{ IOException -> 0x0060 }
            if (r0 != 0) goto L_0x0038
            r8.shutdown()     // Catch:{ IOException -> 0x0060 }
        L_0x0038:
            boolean r3 = r8.mo2937d()     // Catch:{ all -> 0x00b9 }
            c.a.a.a.h.b r0 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            boolean r0 = r0.mo2805a()     // Catch:{ all -> 0x00b9 }
            if (r0 == 0) goto L_0x0053
            if (r3 == 0) goto L_0x004e
            c.a.a.a.h.b r0 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = "Released connection is reusable."
        L_0x004a:
            r0.mo2803a(r1)     // Catch:{ all -> 0x00b9 }
            goto L_0x0053
        L_0x004e:
            c.a.a.a.h.b r0 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = "Released connection is not reusable."
            goto L_0x004a
        L_0x0053:
            r8.mo2933a()     // Catch:{ all -> 0x00b9 }
            c.a.a.a.i.c.a.e r1 = r7.f2173d     // Catch:{ all -> 0x00b9 }
        L_0x0058:
            r4 = r9
            r6 = r11
            r1.mo2962a(r2, r3, r4, r6)     // Catch:{ all -> 0x00b9 }
            goto L_0x0091
        L_0x005e:
            r0 = move-exception
            goto L_0x0093
        L_0x0060:
            r0 = move-exception
            c.a.a.a.h.b r1 = r7.f2170a     // Catch:{ all -> 0x005e }
            boolean r1 = r1.mo2805a()     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0070
            c.a.a.a.h.b r1 = r7.f2170a     // Catch:{ all -> 0x005e }
            java.lang.String r3 = "Exception shutting down released connection."
            r1.mo2804a(r3, r0)     // Catch:{ all -> 0x005e }
        L_0x0070:
            boolean r3 = r8.mo2937d()     // Catch:{ all -> 0x00b9 }
            c.a.a.a.h.b r0 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            boolean r0 = r0.mo2805a()     // Catch:{ all -> 0x00b9 }
            if (r0 == 0) goto L_0x008b
            if (r3 == 0) goto L_0x0086
            c.a.a.a.h.b r0 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = "Released connection is reusable."
        L_0x0082:
            r0.mo2803a(r1)     // Catch:{ all -> 0x00b9 }
            goto L_0x008b
        L_0x0086:
            c.a.a.a.h.b r0 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = "Released connection is not reusable."
            goto L_0x0082
        L_0x008b:
            r8.mo2933a()     // Catch:{ all -> 0x00b9 }
            c.a.a.a.i.c.a.e r1 = r7.f2173d     // Catch:{ all -> 0x00b9 }
            goto L_0x0058
        L_0x0091:
            monitor-exit(r8)     // Catch:{ all -> 0x00b9 }
            return
        L_0x0093:
            boolean r3 = r8.mo2937d()     // Catch:{ all -> 0x00b9 }
            c.a.a.a.h.b r1 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            boolean r1 = r1.mo2805a()     // Catch:{ all -> 0x00b9 }
            if (r1 == 0) goto L_0x00ae
            if (r3 == 0) goto L_0x00a9
            c.a.a.a.h.b r1 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            java.lang.String r4 = "Released connection is reusable."
        L_0x00a5:
            r1.mo2803a(r4)     // Catch:{ all -> 0x00b9 }
            goto L_0x00ae
        L_0x00a9:
            c.a.a.a.h.b r1 = r7.f2170a     // Catch:{ all -> 0x00b9 }
            java.lang.String r4 = "Released connection is not reusable."
            goto L_0x00a5
        L_0x00ae:
            r8.mo2933a()     // Catch:{ all -> 0x00b9 }
            c.a.a.a.i.c.a.e r1 = r7.f2173d     // Catch:{ all -> 0x00b9 }
            r4 = r9
            r6 = r11
            r1.mo2962a(r2, r3, r4, r6)     // Catch:{ all -> 0x00b9 }
            throw r0     // Catch:{ all -> 0x00b9 }
        L_0x00b9:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00b9 }
            goto L_0x00bd
        L_0x00bc:
            throw r9
        L_0x00bd:
            goto L_0x00bc
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0725i.mo2626a(c.a.a.a.e.o, long, java.util.concurrent.TimeUnit):void");
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public C0603i getSchemeRegistry() {
        return this.f2171b;
    }

    public void shutdown() {
        this.f2170a.mo2803a("Shutting down");
        this.f2173d.mo2967e();
    }
}
