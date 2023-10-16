package cz.msebera.android.http.impl.conn.tsccm;

import c.a.a.a.i.c.a.b;
import c.a.a.a.i.c.a.g;
import c.a.a.a.i.c.a.j;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.tsccm.ConnPerRoute;
import cz.msebera.android.http.impl.conn.AbstractPoolEntry;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.mime.LangUtils;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

@Deprecated
public class RouteSpecificPool
{
  protected final ConnPerRoute connPerRoute;
  protected final LinkedList<b> freeEntries;
  public HttpClientAndroidLog log = new HttpClientAndroidLog(g.class);
  protected final int maxEntries;
  protected int numEntries;
  protected final HttpRoute route;
  protected final Queue<j> waitingThreads;
  
  public RouteSpecificPool(HttpRoute paramHttpRoute, ConnPerRoute paramConnPerRoute)
  {
    route = paramHttpRoute;
    connPerRoute = paramConnPerRoute;
    maxEntries = paramConnPerRoute.getMaxForRoute(paramHttpRoute);
    freeEntries = new LinkedList();
    waitingThreads = new LinkedList();
    numEntries = 0;
  }
  
  public BasicPoolEntry allocEntry(Object paramObject)
  {
    Object localObject;
    if (!freeEntries.isEmpty())
    {
      localObject = freeEntries;
      localObject = ((LinkedList)localObject).listIterator(((LinkedList)localObject).size());
      while (((ListIterator)localObject).hasPrevious())
      {
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)((ListIterator)localObject).previous();
        if ((localBasicPoolEntry.getState() == null) || (LangUtils.equals(paramObject, localBasicPoolEntry.getState())))
        {
          ((ListIterator)localObject).remove();
          return localBasicPoolEntry;
        }
      }
    }
    if ((getCapacity() == 0) && (!freeEntries.isEmpty()))
    {
      paramObject = (BasicPoolEntry)freeEntries.remove();
      paramObject.shutdownEntry();
      localObject = paramObject.getConnection();
      try
      {
        ((HttpConnection)localObject).close();
        return paramObject;
      }
      catch (IOException localIOException)
      {
        log.debug("I/O error closing connection", localIOException);
        return paramObject;
      }
    }
    return null;
  }
  
  public void createdEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    Args.check(route.equals(paramBasicPoolEntry.getPlannedRoute()), "Entry not planned for this pool");
    numEntries += 1;
  }
  
  public boolean deleteEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    boolean bool = freeEntries.remove(paramBasicPoolEntry);
    if (bool) {
      numEntries -= 1;
    }
    return bool;
  }
  
  public void dropEntry()
  {
    boolean bool;
    if (numEntries > 0) {
      bool = true;
    } else {
      bool = false;
    }
    Asserts.check(bool, "There is no entry that could be dropped");
    numEntries -= 1;
  }
  
  public void freeEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    int i = numEntries;
    if (i >= 1)
    {
      if (i > freeEntries.size())
      {
        freeEntries.add(paramBasicPoolEntry);
        return;
      }
      paramBasicPoolEntry = new StringBuilder();
      paramBasicPoolEntry.append("No entry allocated from this pool. ");
      paramBasicPoolEntry.append(route);
      throw new IllegalStateException(paramBasicPoolEntry.toString());
    }
    paramBasicPoolEntry = new StringBuilder();
    paramBasicPoolEntry.append("No entry created for this pool. ");
    paramBasicPoolEntry.append(route);
    throw new IllegalStateException(paramBasicPoolEntry.toString());
  }
  
  public int getCapacity()
  {
    return connPerRoute.getMaxForRoute(route) - numEntries;
  }
  
  public final int getMaxEntries()
  {
    return maxEntries;
  }
  
  public final HttpRoute getRoute()
  {
    return route;
  }
  
  public boolean hasThread()
  {
    return waitingThreads.isEmpty() ^ true;
  }
  
  public boolean isUnused()
  {
    return (numEntries < 1) && (waitingThreads.isEmpty());
  }
  
  public WaitingThread nextThread()
  {
    return (WaitingThread)waitingThreads.peek();
  }
  
  public void queueThread(WaitingThread paramWaitingThread)
  {
    Args.notNull(paramWaitingThread, "Waiting thread");
    waitingThreads.add(paramWaitingThread);
  }
  
  public void removeThread(WaitingThread paramWaitingThread)
  {
    if (paramWaitingThread == null) {
      return;
    }
    waitingThreads.remove(paramWaitingThread);
  }
}
