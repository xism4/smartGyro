package com.org.android.manager;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Connection {
    private final Object LOCK = new Object();
    private final String g;
    private Handler handler;
    private final int q;
    private final int r;
    private Handler.Callback this$0 = new SnackbarManager$1(this);
    private HandlerThread thread;
    private int type;

    public Connection(String str, int i, int i2) {
        this.g = str;
        this.q = i;
        this.r = i2;
        this.type = 0;
    }

    private void enqueue(Runnable runnable) {
        synchronized (this.LOCK) {
            if (this.thread == null) {
                this.thread = new HandlerThread(this.g, this.q);
                this.thread.start();
                this.handler = new Handler(this.thread.getLooper(), this.this$0);
                this.type++;
            }
            this.handler.removeMessages(0);
            this.handler.sendMessage(this.handler.obtainMessage(1, runnable));
        }
    }

    /* access modifiers changed from: package-private */
    public void close() {
        synchronized (this.LOCK) {
            if (!this.handler.hasMessages(1)) {
                this.thread.quit();
                this.thread = null;
                this.handler = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void close(Runnable runnable) {
        runnable.run();
        synchronized (this.LOCK) {
            this.handler.removeMessages(0);
            this.handler.sendMessageDelayed(this.handler.obtainMessage(0), (long) this.r);
        }
    }

    public void close(Callable callable, c cVar) {
        enqueue(new Callables$3(this, callable, new Handler(), cVar));
    }

    public Object get(Callable callable, int i) {
        ReentrantLock $r1 = new ReentrantLock();
        Condition $r7 = $r1.newCondition();
        AtomicReference $r2 = new AtomicReference();
        AtomicBoolean $r3 = new AtomicBoolean(true);
        enqueue(new PeerGroup$10(this, $r2, callable, $r1, $r3, $r7));
        $r1.lock();
        try {
            if (!$r3.get()) {
                return $r2.get();
            }
            long $l1 = (long) i;
            long j = $l1;
            long $l12 = TimeUnit.MILLISECONDS.toNanos($l1);
            do {
                try {
                    $l12 = $r7.awaitNanos($l12);
                } catch (InterruptedException e) {
                }
                if (!$r3.get()) {
                    Object $r8 = $r2.get();
                    $r1.unlock();
                    return $r8;
                }
            } while ($l12 > 0);
            throw new InterruptedException("timeout");
        } finally {
            $r1.unlock();
        }
    }
}
