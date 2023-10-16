package p000a.p001a.p005c.p011d;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Callable;

/* renamed from: a.a.c.d.k */
public class C0072k {

    /* renamed from: a */
    private final Object f180a = new Object();

    /* renamed from: b */
    private HandlerThread f181b;

    /* renamed from: c */
    private Handler f182c;

    /* renamed from: d */
    private int f183d;

    /* renamed from: e */
    private Handler.Callback f184e = new C0068g(this);

    /* renamed from: f */
    private final int f185f;

    /* renamed from: g */
    private final int f186g;

    /* renamed from: h */
    private final String f187h;

    /* renamed from: a.a.c.d.k$a */
    public interface C0073a<T> {
        /* renamed from: a */
        void mo226a(T t);
    }

    public C0072k(String str, int i, int i2) {
        this.f187h = str;
        this.f186g = i;
        this.f185f = i2;
        this.f183d = 0;
    }

    /* renamed from: b */
    private void m244b(Runnable runnable) {
        synchronized (this.f180a) {
            if (this.f181b == null) {
                this.f181b = new HandlerThread(this.f187h, this.f186g);
                this.f181b.start();
                this.f182c = new Handler(this.f181b.getLooper(), this.f184e);
                this.f183d++;
            }
            this.f182c.removeMessages(0);
            this.f182c.sendMessage(this.f182c.obtainMessage(1, runnable));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:9|10|11|12|(4:25|14|15|16)(1:17)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003f */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0045 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T mo241a(java.util.concurrent.Callable<T> r13, int r14) {
        /*
            r12 = this;
            java.util.concurrent.locks.ReentrantLock r7 = new java.util.concurrent.locks.ReentrantLock
            r7.<init>()
            java.util.concurrent.locks.Condition r8 = r7.newCondition()
            java.util.concurrent.atomic.AtomicReference r9 = new java.util.concurrent.atomic.AtomicReference
            r9.<init>()
            java.util.concurrent.atomic.AtomicBoolean r10 = new java.util.concurrent.atomic.AtomicBoolean
            r0 = 1
            r10.<init>(r0)
            a.a.c.d.j r11 = new a.a.c.d.j
            r0 = r11
            r1 = r12
            r2 = r9
            r3 = r13
            r4 = r7
            r5 = r10
            r6 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r12.m244b(r11)
            r7.lock()
            boolean r13 = r10.get()     // Catch:{ all -> 0x005c }
            if (r13 != 0) goto L_0x0034
            java.lang.Object r13 = r9.get()     // Catch:{ all -> 0x005c }
            r7.unlock()
            return r13
        L_0x0034:
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x005c }
            long r0 = (long) r14     // Catch:{ all -> 0x005c }
            long r13 = r13.toNanos(r0)     // Catch:{ all -> 0x005c }
        L_0x003b:
            long r13 = r8.awaitNanos(r13)     // Catch:{ InterruptedException -> 0x003f }
        L_0x003f:
            boolean r0 = r10.get()     // Catch:{ all -> 0x005c }
            if (r0 != 0) goto L_0x004d
            java.lang.Object r13 = r9.get()     // Catch:{ all -> 0x005c }
            r7.unlock()
            return r13
        L_0x004d:
            r0 = 0
            int r2 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0054
            goto L_0x003b
        L_0x0054:
            java.lang.InterruptedException r13 = new java.lang.InterruptedException     // Catch:{ all -> 0x005c }
            java.lang.String r14 = "timeout"
            r13.<init>(r14)     // Catch:{ all -> 0x005c }
            throw r13     // Catch:{ all -> 0x005c }
        L_0x005c:
            r13 = move-exception
            r7.unlock()
            goto L_0x0062
        L_0x0061:
            throw r13
        L_0x0062:
            goto L_0x0061
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p011d.C0072k.mo241a(java.util.concurrent.Callable, int):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo242a() {
        synchronized (this.f180a) {
            if (!this.f182c.hasMessages(1)) {
                this.f181b.quit();
                this.f181b = null;
                this.f182c = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo243a(Runnable runnable) {
        runnable.run();
        synchronized (this.f180a) {
            this.f182c.removeMessages(0);
            this.f182c.sendMessageDelayed(this.f182c.obtainMessage(0), (long) this.f185f);
        }
    }

    /* renamed from: a */
    public <T> void mo244a(Callable<T> callable, C0073a<T> aVar) {
        m244b(new C0070i(this, callable, new Handler(), aVar));
    }
}
