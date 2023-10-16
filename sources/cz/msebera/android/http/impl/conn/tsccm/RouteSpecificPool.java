package cz.msebera.android.http.impl.conn.tsccm;

import c.a.a.a.i.c.a.b;
import c.a.a.a.i.c.a.g;
import c.a.a.a.i.c.a.j;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.tsccm.ConnPerRoute;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import java.util.LinkedList;
import java.util.Queue;

@Deprecated
public class RouteSpecificPool {
    protected final ConnPerRoute connPerRoute;
    protected final LinkedList<b> freeEntries;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(g.class);
    protected final int maxEntries;
    protected int numEntries;
    protected final HttpRoute route;
    protected final Queue<j> waitingThreads;

    public RouteSpecificPool(HttpRoute httpRoute, ConnPerRoute connPerRoute2) {
        this.route = httpRoute;
        this.connPerRoute = connPerRoute2;
        this.maxEntries = connPerRoute2.getMaxForRoute(httpRoute);
        this.freeEntries = new LinkedList();
        this.waitingThreads = new LinkedList();
        this.numEntries = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cz.msebera.android.http.impl.conn.tsccm.BasicPoolEntry allocEntry(java.lang.Object r14) {
        /*
            r13 = this;
            java.util.LinkedList<c.a.a.a.i.c.a.b> r0 = r13.freeEntries
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0034
            java.util.LinkedList<c.a.a.a.i.c.a.b> r0 = r13.freeEntries
            int r2 = r0.size()
            java.util.ListIterator r3 = r0.listIterator(r2)
        L_0x0012:
            boolean r1 = r3.hasPrevious()
            if (r1 == 0) goto L_0x0034
            java.lang.Object r4 = r3.previous()
            r6 = r4
            cz.msebera.android.http.impl.conn.tsccm.BasicPoolEntry r6 = (cz.msebera.android.http.impl.conn.tsccm.BasicPoolEntry) r6
            r5 = r6
            java.lang.Object r4 = r5.getState()
            if (r4 == 0) goto L_0x0030
            java.lang.Object r4 = r5.getState()
            boolean r1 = cz.msebera.android.http.mime.LangUtils.equals((java.lang.Object) r14, (java.lang.Object) r4)
            if (r1 == 0) goto L_0x0012
        L_0x0030:
            r3.remove()
            return r5
        L_0x0034:
            int r2 = r13.getCapacity()
            if (r2 != 0) goto L_0x0060
            java.util.LinkedList<c.a.a.a.i.c.a.b> r0 = r13.freeEntries
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0060
            java.util.LinkedList<c.a.a.a.i.c.a.b> r0 = r13.freeEntries
            java.lang.Object r14 = r0.remove()
            r7 = r14
            cz.msebera.android.http.impl.conn.tsccm.BasicPoolEntry r7 = (cz.msebera.android.http.impl.conn.tsccm.BasicPoolEntry) r7
            r5 = r7
            r5.shutdownEntry()
            cz.msebera.android.http.conn.OperatedClientConnection r8 = r5.getConnection()
            r8.close()     // Catch:{ IOException -> 0x0057 }
            return r5
        L_0x0057:
            r9 = move-exception
            cz.msebera.android.http.cache.HttpClientAndroidLog r10 = r13.log
            java.lang.String r11 = "I/O error closing connection"
            r10.debug(r11, r9)
            return r5
        L_0x0060:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.conn.tsccm.RouteSpecificPool.allocEntry(java.lang.Object):cz.msebera.android.http.impl.conn.tsccm.BasicPoolEntry");
    }

    public void createdEntry(BasicPoolEntry basicPoolEntry) {
        Args.check(this.route.equals(basicPoolEntry.getPlannedRoute()), "Entry not planned for this pool");
        this.numEntries++;
    }

    public boolean deleteEntry(BasicPoolEntry basicPoolEntry) {
        boolean $z0 = this.freeEntries.remove(basicPoolEntry);
        if ($z0) {
            this.numEntries--;
        }
        return $z0;
    }

    public void dropEntry() {
        Asserts.check(this.numEntries > 0, "There is no entry that could be dropped");
        this.numEntries--;
    }

    public void freeEntry(BasicPoolEntry basicPoolEntry) {
        int $i0 = this.numEntries;
        if ($i0 < 1) {
            throw new IllegalStateException("No entry created for this pool. " + this.route);
        } else if ($i0 > this.freeEntries.size()) {
            this.freeEntries.add(basicPoolEntry);
        } else {
            throw new IllegalStateException("No entry allocated from this pool. " + this.route);
        }
    }

    public int getCapacity() {
        return this.connPerRoute.getMaxForRoute(this.route) - this.numEntries;
    }

    public final int getMaxEntries() {
        return this.maxEntries;
    }

    public final HttpRoute getRoute() {
        return this.route;
    }

    public boolean hasThread() {
        return !this.waitingThreads.isEmpty();
    }

    public boolean isUnused() {
        return this.numEntries < 1 && this.waitingThreads.isEmpty();
    }

    public WaitingThread nextThread() {
        return this.waitingThreads.peek();
    }

    public void queueThread(WaitingThread waitingThread) {
        Args.notNull(waitingThread, "Waiting thread");
        this.waitingThreads.add(waitingThread);
    }

    public void removeThread(WaitingThread waitingThread) {
        if (waitingThread != null) {
            this.waitingThreads.remove(waitingThread);
        }
    }
}
