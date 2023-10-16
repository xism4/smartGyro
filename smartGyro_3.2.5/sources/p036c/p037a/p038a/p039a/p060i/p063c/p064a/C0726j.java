package p036c.p037a.p038a.p039a.p060i.p063c.p064a;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.c.a.j */
public class C0726j {

    /* renamed from: a */
    private final Condition f2176a;

    /* renamed from: b */
    private final C0723g f2177b;

    /* renamed from: c */
    private Thread f2178c;

    /* renamed from: d */
    private boolean f2179d;

    public C0726j(Condition condition, C0723g gVar) {
        C0870a.m3042a(condition, "Condition");
        this.f2176a = condition;
        this.f2177b = gVar;
    }

    /* renamed from: a */
    public void mo2984a() {
        this.f2179d = true;
        this.f2176a.signalAll();
    }

    /* renamed from: a */
    public boolean mo2985a(Date date) {
        boolean z;
        if (this.f2178c != null) {
            throw new IllegalStateException("A thread is already waiting on this object.\ncaller: " + Thread.currentThread() + "\nwaiter: " + this.f2178c);
        } else if (!this.f2179d) {
            this.f2178c = Thread.currentThread();
            if (date != null) {
                try {
                    z = this.f2176a.awaitUntil(date);
                } catch (Throwable th) {
                    this.f2178c = null;
                    throw th;
                }
            } else {
                this.f2176a.await();
                z = true;
            }
            if (!this.f2179d) {
                this.f2178c = null;
                return z;
            }
            throw new InterruptedException("Operation interrupted");
        } else {
            throw new InterruptedException("Operation interrupted");
        }
    }

    /* renamed from: b */
    public void mo2986b() {
        if (this.f2178c != null) {
            this.f2176a.signalAll();
            return;
        }
        throw new IllegalStateException("Nobody waiting on this object.");
    }
}
