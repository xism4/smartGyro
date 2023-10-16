package cz.msebera.android.http.impl.conn.tsccm;

@Deprecated
public class WaitingThreadAborter
{
  private boolean aborted;
  private WaitingThread waitingThread;
  
  public WaitingThreadAborter() {}
  
  public void abort()
  {
    aborted = true;
    WaitingThread localWaitingThread = waitingThread;
    if (localWaitingThread != null) {
      localWaitingThread.interrupt();
    }
  }
  
  public void setWaitingThread(WaitingThread paramWaitingThread)
  {
    waitingThread = paramWaitingThread;
    if (aborted) {
      paramWaitingThread.interrupt();
    }
  }
}
