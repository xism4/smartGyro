package cz.msebera.android.http.impl.conn.tsccm;

@Deprecated
public class WaitingThreadAborter {
    private boolean aborted;
    private WaitingThread waitingThread;

    public void abort() {
        this.aborted = true;
        WaitingThread $r1 = this.waitingThread;
        if ($r1 != null) {
            $r1.interrupt();
        }
    }

    public void setWaitingThread(WaitingThread waitingThread2) {
        this.waitingThread = waitingThread2;
        if (this.aborted) {
            waitingThread2.interrupt();
        }
    }
}
