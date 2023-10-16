package p026b.p033c.p034a.p035a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.net.URI;
import p036c.p037a.p038a.p039a.C0491G;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p041b.C0563l;

/* renamed from: b.c.a.a.f */
public abstract class C0468f implements C0482s {

    /* renamed from: a */
    private String f1712a;

    /* renamed from: b */
    private Handler f1713b;

    /* renamed from: c */
    private boolean f1714c;

    /* renamed from: d */
    private boolean f1715d;

    /* renamed from: e */
    private URI f1716e;

    /* renamed from: f */
    private C0576e[] f1717f;

    /* renamed from: g */
    private Looper f1718g;

    /* renamed from: h */
    private WeakReference<Object> f1719h;

    /* renamed from: b.c.a.a.f$a */
    private static class C0469a extends Handler {

        /* renamed from: a */
        private final C0468f f1720a;

        C0469a(C0468f fVar, Looper looper) {
            super(looper);
            this.f1720a = fVar;
        }

        public void handleMessage(Message message) {
            this.f1720a.mo2381a(message);
        }
    }

    public C0468f() {
        this((Looper) null);
    }

    public C0468f(Looper looper) {
        this.f1712a = "UTF-8";
        this.f1716e = null;
        this.f1717f = null;
        this.f1718g = null;
        this.f1719h = new WeakReference<>((Object) null);
        this.f1718g = looper == null ? Looper.myLooper() : looper;
        mo2396b(false);
        mo2386a(false);
    }

    public C0468f(boolean z) {
        this.f1712a = "UTF-8";
        this.f1716e = null;
        this.f1717f = null;
        this.f1718g = null;
        this.f1719h = new WeakReference<>((Object) null);
        mo2386a(z);
        if (!mo2388a()) {
            this.f1718g = Looper.myLooper();
            mo2396b(false);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Message mo2376a(int i, Object obj) {
        return Message.obtain(this.f1713b, i, obj);
    }

    /* renamed from: a */
    public final void mo2377a(int i) {
        mo2394b(mo2376a(5, (Object) new Object[]{Integer.valueOf(i)}));
    }

    /* renamed from: a */
    public abstract void mo2378a(int i, C0576e[] eVarArr, byte[] bArr);

    /* renamed from: a */
    public final void mo2379a(int i, C0576e[] eVarArr, byte[] bArr, Throwable th) {
        mo2394b(mo2376a(1, (Object) new Object[]{Integer.valueOf(i), eVarArr, bArr, th}));
    }

    /* renamed from: a */
    public void mo2380a(long j, long j2) {
        double d;
        C0474k kVar = C0465d.f1690a;
        Object[] objArr = new Object[3];
        objArr[0] = Long.valueOf(j);
        objArr[1] = Long.valueOf(j2);
        if (j2 > 0) {
            double d2 = (double) j;
            Double.isNaN(d2);
            double d3 = (double) j2;
            Double.isNaN(d3);
            d = ((d2 * 1.0d) / d3) * 100.0d;
        } else {
            d = -1.0d;
        }
        objArr[2] = Double.valueOf(d);
        kVar.mo2421c("AsyncHttpRH", String.format("Progress %d from %d (%2.0f%%)", objArr));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2381a(Message message) {
        C0474k kVar;
        String str;
        try {
            switch (message.what) {
                case 0:
                    Object[] objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length < 3) {
                        kVar = C0465d.f1690a;
                        str = "SUCCESS_MESSAGE didn't got enough params";
                        break;
                    } else {
                        mo2378a(((Integer) objArr[0]).intValue(), (C0576e[]) objArr[1], (byte[]) objArr[2]);
                        return;
                    }
                case 1:
                    Object[] objArr2 = (Object[]) message.obj;
                    if (objArr2 == null || objArr2.length < 4) {
                        kVar = C0465d.f1690a;
                        str = "FAILURE_MESSAGE didn't got enough params";
                        break;
                    } else {
                        mo2392b(((Integer) objArr2[0]).intValue(), (C0576e[]) objArr2[1], (byte[]) objArr2[2], (Throwable) objArr2[3]);
                        return;
                    }
                case 2:
                    mo2404i();
                    return;
                case 3:
                    mo2403h();
                    return;
                case 4:
                    Object[] objArr3 = (Object[]) message.obj;
                    if (objArr3 == null || objArr3.length < 2) {
                        kVar = C0465d.f1690a;
                        str = "PROGRESS_MESSAGE didn't got enough params";
                        break;
                    } else {
                        mo2380a(((Long) objArr3[0]).longValue(), ((Long) objArr3[1]).longValue());
                        return;
                    }
                    break;
                case 5:
                    Object[] objArr4 = (Object[]) message.obj;
                    if (objArr4 == null || objArr4.length != 1) {
                        kVar = C0465d.f1690a;
                        str = "RETRY_MESSAGE didn't get enough params";
                        break;
                    } else {
                        mo2390b(((Integer) objArr4[0]).intValue());
                        return;
                    }
                    break;
                case 6:
                    mo2402g();
                    return;
                default:
                    return;
            }
            kVar.mo2419b("AsyncHttpRH", str);
        } catch (Throwable th) {
            mo2384a(th);
            throw null;
        }
    }

    /* renamed from: a */
    public void mo2382a(C0482s sVar, C0883t tVar) {
    }

    /* renamed from: a */
    public void mo2383a(C0883t tVar) {
        if (!Thread.currentThread().isInterrupted()) {
            C0491G statusLine = tVar.getStatusLine();
            byte[] a = mo2389a(tVar.getEntity());
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            if (statusLine.getStatusCode() >= 300) {
                mo2379a(statusLine.getStatusCode(), tVar.getAllHeaders(), a, new C0563l(statusLine.getStatusCode(), statusLine.getReasonPhrase()));
            } else {
                mo2391b(statusLine.getStatusCode(), tVar.getAllHeaders(), a);
            }
        }
    }

    /* renamed from: a */
    public void mo2384a(Throwable th) {
        C0465d.f1690a.mo2420b("AsyncHttpRH", "User-space exception detected!", th);
        throw new RuntimeException(th);
    }

    /* renamed from: a */
    public void mo2385a(URI uri) {
        this.f1716e = uri;
    }

    /* renamed from: a */
    public void mo2386a(boolean z) {
        if (z) {
            this.f1718g = null;
            this.f1713b = null;
        }
        this.f1715d = z;
    }

    /* renamed from: a */
    public void mo2387a(C0576e[] eVarArr) {
        this.f1717f = eVarArr;
    }

    /* renamed from: a */
    public boolean mo2388a() {
        return this.f1715d;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        p026b.p033c.p034a.p035a.C0465d.m2002a(r0);
        p026b.p033c.p034a.p035a.C0465d.m2001a(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return r7.mo3292g();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] mo2389a(p036c.p037a.p038a.p039a.C0837l r13) {
        /*
            r12 = this;
            if (r13 == 0) goto L_0x0070
            java.io.InputStream r0 = r13.getContent()
            if (r0 == 0) goto L_0x0070
            long r1 = r13.getContentLength()
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0068
            r3 = 4096(0x1000, float:5.74E-42)
            r4 = 0
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x001e
            r6 = 4096(0x1000, float:5.74E-42)
            goto L_0x001f
        L_0x001e:
            int r6 = (int) r1
        L_0x001f:
            c.a.a.a.p.c r7 = new c.a.a.a.p.c     // Catch:{ OutOfMemoryError -> 0x005d }
            r7.<init>(r6)     // Catch:{ OutOfMemoryError -> 0x005d }
            byte[] r3 = new byte[r3]     // Catch:{ all -> 0x0055 }
            r8 = r4
        L_0x0027:
            int r6 = r0.read(r3)     // Catch:{ all -> 0x0055 }
            r10 = -1
            if (r6 == r10) goto L_0x004a
            java.lang.Thread r10 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0055 }
            boolean r10 = r10.isInterrupted()     // Catch:{ all -> 0x0055 }
            if (r10 != 0) goto L_0x004a
            long r10 = (long) r6     // Catch:{ all -> 0x0055 }
            long r8 = r8 + r10
            r10 = 0
            r7.mo3283a((byte[]) r3, (int) r10, (int) r6)     // Catch:{ all -> 0x0055 }
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x0045
            r10 = 1
            goto L_0x0046
        L_0x0045:
            r10 = r1
        L_0x0046:
            r12.mo2393b((long) r8, (long) r10)     // Catch:{ all -> 0x0055 }
            goto L_0x0027
        L_0x004a:
            p026b.p033c.p034a.p035a.C0465d.m2002a((java.io.InputStream) r0)     // Catch:{ OutOfMemoryError -> 0x005d }
            p026b.p033c.p034a.p035a.C0465d.m2001a((p036c.p037a.p038a.p039a.C0837l) r13)     // Catch:{ OutOfMemoryError -> 0x005d }
            byte[] r13 = r7.mo3292g()     // Catch:{ OutOfMemoryError -> 0x005d }
            goto L_0x0071
        L_0x0055:
            r1 = move-exception
            p026b.p033c.p034a.p035a.C0465d.m2002a((java.io.InputStream) r0)     // Catch:{ OutOfMemoryError -> 0x005d }
            p026b.p033c.p034a.p035a.C0465d.m2001a((p036c.p037a.p038a.p039a.C0837l) r13)     // Catch:{ OutOfMemoryError -> 0x005d }
            throw r1     // Catch:{ OutOfMemoryError -> 0x005d }
        L_0x005d:
            java.lang.System.gc()
            java.io.IOException r13 = new java.io.IOException
            java.lang.String r0 = "File too large to fit into available memory"
            r13.<init>(r0)
            throw r13
        L_0x0068:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "HTTP entity too large to be buffered in memory"
            r13.<init>(r0)
            throw r13
        L_0x0070:
            r13 = 0
        L_0x0071:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: p026b.p033c.p034a.p035a.C0468f.mo2389a(c.a.a.a.l):byte[]");
    }

    /* renamed from: b */
    public void mo2390b(int i) {
        C0465d.f1690a.mo2422d("AsyncHttpRH", String.format("Request retry no. %d", new Object[]{Integer.valueOf(i)}));
    }

    /* renamed from: b */
    public final void mo2391b(int i, C0576e[] eVarArr, byte[] bArr) {
        mo2394b(mo2376a(0, (Object) new Object[]{Integer.valueOf(i), eVarArr, bArr}));
    }

    /* renamed from: b */
    public abstract void mo2392b(int i, C0576e[] eVarArr, byte[] bArr, Throwable th);

    /* renamed from: b */
    public final void mo2393b(long j, long j2) {
        mo2394b(mo2376a(4, (Object) new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo2394b(Message message) {
        if (mo2397b() || this.f1713b == null) {
            mo2381a(message);
        } else if (!Thread.currentThread().isInterrupted()) {
            C0484u.m2111a(this.f1713b != null, "handler should not be null!");
            this.f1713b.sendMessage(message);
        }
    }

    /* renamed from: b */
    public void mo2395b(C0482s sVar, C0883t tVar) {
    }

    /* renamed from: b */
    public void mo2396b(boolean z) {
        C0469a aVar;
        if (!z && this.f1718g == null) {
            z = true;
            C0465d.f1690a.mo2415a("AsyncHttpRH", "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
        }
        if (z || this.f1713b != null) {
            if (z && this.f1713b != null) {
                aVar = null;
            }
            this.f1714c = z;
        }
        aVar = new C0469a(this, this.f1718g);
        this.f1713b = aVar;
        this.f1714c = z;
    }

    /* renamed from: b */
    public boolean mo2397b() {
        return this.f1714c;
    }

    /* renamed from: c */
    public final void mo2398c() {
        mo2394b(mo2376a(2, (Object) null));
    }

    /* renamed from: d */
    public final void mo2399d() {
        mo2394b(mo2376a(3, (Object) null));
    }

    /* renamed from: e */
    public final void mo2400e() {
        mo2394b(mo2376a(6, (Object) null));
    }

    /* renamed from: f */
    public URI mo2401f() {
        return this.f1716e;
    }

    /* renamed from: g */
    public void mo2402g() {
        C0465d.f1690a.mo2422d("AsyncHttpRH", "Request got cancelled");
    }

    /* renamed from: h */
    public void mo2403h() {
    }

    /* renamed from: i */
    public void mo2404i() {
    }
}
