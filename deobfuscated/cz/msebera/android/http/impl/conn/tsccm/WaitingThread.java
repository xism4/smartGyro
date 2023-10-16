package cz.msebera.android.http.impl.conn.tsccm;

import cz.msebera.android.http.mime.Args;
import java.util.Date;
import java.util.concurrent.locks.Condition;

@Deprecated
public class WaitingThread
{
  private boolean aborted;
  private final Condition cond;
  private final RouteSpecificPool pool;
  private Thread waiter;
  
  public WaitingThread(Condition paramCondition, RouteSpecificPool paramRouteSpecificPool)
  {
    Args.notNull(paramCondition, "Condition");
    cond = paramCondition;
    pool = paramRouteSpecificPool;
  }
  
  public boolean await(Date paramDate)
  {
    if (waiter == null)
    {
      if (!aborted)
      {
        waiter = Thread.currentThread();
        if (paramDate != null) {}
        try
        {
          boolean bool1 = cond.awaitUntil(paramDate);
          break label50;
          cond.await();
          bool1 = true;
          label50:
          boolean bool2 = aborted;
          if (!bool2)
          {
            waiter = null;
            return bool1;
          }
          throw new InterruptedException("Operation interrupted");
        }
        catch (Throwable paramDate)
        {
          waiter = null;
          throw paramDate;
        }
      }
      throw new InterruptedException("Operation interrupted");
    }
    paramDate = new StringBuilder();
    paramDate.append("A thread is already waiting on this object.\ncaller: ");
    paramDate.append(Thread.currentThread());
    paramDate.append("\nwaiter: ");
    paramDate.append(waiter);
    throw new IllegalStateException(paramDate.toString());
  }
  
  public void interrupt()
  {
    aborted = true;
    cond.signalAll();
  }
  
  public void wakeup()
  {
    if (waiter != null)
    {
      cond.signalAll();
      return;
    }
    throw new IllegalStateException("Nobody waiting on this object.");
  }
}
