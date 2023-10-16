package com.org.android.manager;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Connection
{
  private final Object LOCK = new Object();
  private final String g;
  private Handler handler;
  private final int q;
  private final int r;
  private Handler.Callback this$0 = new SnackbarManager.1(this);
  private HandlerThread thread;
  private int type;
  
  public Connection(String paramString, int paramInt1, int paramInt2)
  {
    g = paramString;
    q = paramInt1;
    r = paramInt2;
    type = 0;
  }
  
  private void enqueue(Runnable paramRunnable)
  {
    Object localObject = LOCK;
    try
    {
      if (thread == null)
      {
        thread = new HandlerThread(g, q);
        thread.start();
        handler = new Handler(thread.getLooper(), this$0);
        type += 1;
      }
      handler.removeMessages(0);
      handler.sendMessage(handler.obtainMessage(1, paramRunnable));
      return;
    }
    catch (Throwable paramRunnable)
    {
      throw paramRunnable;
    }
  }
  
  void close()
  {
    Object localObject = LOCK;
    try
    {
      if (handler.hasMessages(1)) {
        return;
      }
      thread.quit();
      thread = null;
      handler = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  void close(Runnable paramRunnable)
  {
    paramRunnable.run();
    paramRunnable = LOCK;
    try
    {
      handler.removeMessages(0);
      handler.sendMessageDelayed(handler.obtainMessage(0), r);
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void close(Callable paramCallable, c paramC)
  {
    enqueue(new Callables.3(this, paramCallable, new Handler(), paramC));
  }
  
  public Object get(Callable paramCallable, int paramInt)
  {
    localReentrantLock = new ReentrantLock();
    Condition localCondition = localReentrantLock.newCondition();
    AtomicReference localAtomicReference = new AtomicReference();
    AtomicBoolean localAtomicBoolean = new AtomicBoolean(true);
    enqueue(new PeerGroup.10(this, localAtomicReference, paramCallable, localReentrantLock, localAtomicBoolean, localCondition));
    localReentrantLock.lock();
    label109:
    do
    {
      try
      {
        bool = localAtomicBoolean.get();
        if (!bool)
        {
          paramCallable = localAtomicReference.get();
          localReentrantLock.unlock();
          return paramCallable;
        }
        l1 = TimeUnit.MILLISECONDS.toNanos(paramInt);
      }
      catch (Throwable paramCallable)
      {
        boolean bool;
        long l1;
        long l2;
        localReentrantLock.unlock();
        throw paramCallable;
      }
      try
      {
        l2 = localCondition.awaitNanos(l1);
        l1 = l2;
      }
      catch (InterruptedException paramCallable)
      {
        break label109;
      }
      bool = localAtomicBoolean.get();
      if (!bool)
      {
        paramCallable = localAtomicReference.get();
        localReentrantLock.unlock();
        return paramCallable;
      }
    } while (l1 > 0L);
    throw new InterruptedException("timeout");
  }
}
