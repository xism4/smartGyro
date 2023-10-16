package p000a.p001a.p005c.p011d;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: a.a.c.d.j */
class C0071j implements Runnable {

    /* renamed from: a */
    final /* synthetic */ AtomicReference f174a;

    /* renamed from: b */
    final /* synthetic */ Callable f175b;

    /* renamed from: c */
    final /* synthetic */ ReentrantLock f176c;

    /* renamed from: d */
    final /* synthetic */ AtomicBoolean f177d;

    /* renamed from: e */
    final /* synthetic */ Condition f178e;

    /* renamed from: f */
    final /* synthetic */ C0072k f179f;

    C0071j(C0072k kVar, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
        this.f179f = kVar;
        this.f174a = atomicReference;
        this.f175b = callable;
        this.f176c = reentrantLock;
        this.f177d = atomicBoolean;
        this.f178e = condition;
    }

    public void run() {
        try {
            this.f174a.set(this.f175b.call());
        } catch (Exception unused) {
        }
        this.f176c.lock();
        try {
            this.f177d.set(false);
            this.f178e.signal();
        } finally {
            this.f176c.unlock();
        }
    }
}
