package cz.msebera.android.http.impl.conn.tsccm;

import cz.msebera.android.http.mime.Args;
import java.util.Date;
import java.util.concurrent.locks.Condition;

@Deprecated
public class WaitingThread {
    private boolean aborted;
    private final Condition cond;
    private final RouteSpecificPool pool;
    private Thread waiter;

    public WaitingThread(Condition condition, RouteSpecificPool routeSpecificPool) {
        Args.notNull(condition, "Condition");
        this.cond = condition;
        this.pool = routeSpecificPool;
    }

    public boolean await(Date date) {
        boolean $z0;
        if (this.waiter != null) {
            throw new IllegalStateException("A thread is already waiting on this object.\ncaller: " + Thread.currentThread() + "\nwaiter: " + this.waiter);
        } else if (!this.aborted) {
            this.waiter = Thread.currentThread();
            if (date != null) {
                try {
                    $z0 = this.cond.awaitUntil(date);
                } catch (Throwable $r5) {
                    this.waiter = null;
                    throw $r5;
                }
            } else {
                this.cond.await();
                $z0 = true;
            }
            if (!this.aborted) {
                this.waiter = null;
                return $z0;
            }
            throw new InterruptedException("Operation interrupted");
        } else {
            throw new InterruptedException("Operation interrupted");
        }
    }

    public void interrupt() {
        this.aborted = true;
        this.cond.signalAll();
    }

    public void wakeup() {
        if (this.waiter != null) {
            this.cond.signalAll();
            return;
        }
        throw new IllegalStateException("Nobody waiting on this object.");
    }
}
