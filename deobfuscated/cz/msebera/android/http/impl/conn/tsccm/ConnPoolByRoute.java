package cz.msebera.android.http.impl.conn.tsccm;

import c.a.a.a.i.c.a.e;
import c.a.a.a.i.c.a.g;
import c.a.a.a.i.c.a.j;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.tsccm.ConnManagerParams;
import cz.msebera.android.http.conn.tsccm.ConnPerRoute;
import cz.msebera.android.http.impl.conn.AbstractPoolEntry;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Deprecated
public class ConnPoolByRoute
  extends AbstractConnPool
{
  protected final ConnPerRoute connPerRoute;
  private final long connTTL;
  private final TimeUnit connTTLTimeUnit;
  protected final Queue<c.a.a.a.i.c.a.b> freeConnections;
  protected final Set<c.a.a.a.i.c.a.b> leasedConnections;
  public HttpClientAndroidLog log = new HttpClientAndroidLog(e.class);
  protected volatile int maxTotalConnections;
  protected volatile int numConnections;
  protected final ClientConnectionOperator operator;
  private final Lock poolLock;
  protected final Map<c.a.a.a.e.b.b, g> routeToPool;
  protected volatile boolean shutdown;
  protected final Queue<j> waitingThreads;
  
  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, ConnPerRoute paramConnPerRoute, int paramInt)
  {
    this(paramClientConnectionOperator, paramConnPerRoute, paramInt, -1L, TimeUnit.MILLISECONDS);
  }
  
  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, ConnPerRoute paramConnPerRoute, int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    Args.notNull(paramClientConnectionOperator, "Connection operator");
    Args.notNull(paramConnPerRoute, "Connections per route");
    poolLock = poolLock;
    leasedConnections = leasedConnections;
    operator = paramClientConnectionOperator;
    connPerRoute = paramConnPerRoute;
    maxTotalConnections = paramInt;
    freeConnections = createFreeConnQueue();
    waitingThreads = createWaitingThreadQueue();
    routeToPool = createRouteToPoolMap();
    connTTL = paramLong;
    connTTLTimeUnit = paramTimeUnit;
  }
  
  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, HttpParams paramHttpParams)
  {
    this(paramClientConnectionOperator, ConnManagerParams.getMaxConnectionsPerRoute(paramHttpParams), ConnManagerParams.getMaxTotalConnections(paramHttpParams));
  }
  
  private void closeConnection(BasicPoolEntry paramBasicPoolEntry)
  {
    paramBasicPoolEntry = paramBasicPoolEntry.getConnection();
    if (paramBasicPoolEntry != null) {
      try
      {
        paramBasicPoolEntry.close();
        return;
      }
      catch (IOException paramBasicPoolEntry)
      {
        log.debug("I/O error closing connection", paramBasicPoolEntry);
      }
    }
  }
  
  protected BasicPoolEntry createEntry(RouteSpecificPool paramRouteSpecificPool, ClientConnectionOperator paramClientConnectionOperator)
  {
    if (log.isDebugEnabled())
    {
      HttpClientAndroidLog localHttpClientAndroidLog = log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Creating new connection [");
      localStringBuilder.append(paramRouteSpecificPool.getRoute());
      localStringBuilder.append("]");
      localHttpClientAndroidLog.debug(localStringBuilder.toString());
    }
    paramClientConnectionOperator = new BasicPoolEntry(paramClientConnectionOperator, paramRouteSpecificPool.getRoute(), connTTL, connTTLTimeUnit);
    poolLock.lock();
    try
    {
      paramRouteSpecificPool.createdEntry(paramClientConnectionOperator);
      int i = numConnections;
      numConnections = (i + 1);
      leasedConnections.add(paramClientConnectionOperator);
      poolLock.unlock();
      return paramClientConnectionOperator;
    }
    catch (Throwable paramRouteSpecificPool)
    {
      poolLock.unlock();
      throw paramRouteSpecificPool;
    }
  }
  
  protected Queue createFreeConnQueue()
  {
    return new LinkedList();
  }
  
  protected Map createRouteToPoolMap()
  {
    return new HashMap();
  }
  
  protected Queue createWaitingThreadQueue()
  {
    return new LinkedList();
  }
  
  protected void deleteEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    HttpRoute localHttpRoute = paramBasicPoolEntry.getPlannedRoute();
    Object localObject;
    if (log.isDebugEnabled())
    {
      localObject = log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Deleting connection [");
      localStringBuilder.append(localHttpRoute);
      localStringBuilder.append("][");
      localStringBuilder.append(paramBasicPoolEntry.getState());
      localStringBuilder.append("]");
      ((HttpClientAndroidLog)localObject).debug(localStringBuilder.toString());
    }
    poolLock.lock();
    try
    {
      closeConnection(paramBasicPoolEntry);
      localObject = getRoutePool(localHttpRoute, true);
      ((RouteSpecificPool)localObject).deleteEntry(paramBasicPoolEntry);
      int i = numConnections;
      numConnections = (i - 1);
      boolean bool = ((RouteSpecificPool)localObject).isUnused();
      if (bool) {
        routeToPool.remove(localHttpRoute);
      }
      poolLock.unlock();
      return;
    }
    catch (Throwable paramBasicPoolEntry)
    {
      poolLock.unlock();
      throw paramBasicPoolEntry;
    }
  }
  
  protected void deleteLeastUsedEntry()
  {
    poolLock.lock();
    try
    {
      BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)freeConnections.remove();
      if (localBasicPoolEntry != null)
      {
        deleteEntry(localBasicPoolEntry);
      }
      else
      {
        boolean bool = log.isDebugEnabled();
        if (bool) {
          log.debug("No free connection to delete");
        }
      }
      poolLock.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      poolLock.unlock();
      throw localThrowable;
    }
  }
  
  public void freeEntry(BasicPoolEntry paramBasicPoolEntry, boolean paramBoolean, long paramLong, TimeUnit paramTimeUnit)
  {
    HttpRoute localHttpRoute = paramBasicPoolEntry.getPlannedRoute();
    Object localObject1;
    Object localObject2;
    if (log.isDebugEnabled())
    {
      localObject1 = log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Releasing connection [");
      ((StringBuilder)localObject2).append(localHttpRoute);
      ((StringBuilder)localObject2).append("][");
      ((StringBuilder)localObject2).append(paramBasicPoolEntry.getState());
      ((StringBuilder)localObject2).append("]");
      ((HttpClientAndroidLog)localObject1).debug(((StringBuilder)localObject2).toString());
    }
    poolLock.lock();
    try
    {
      boolean bool = shutdown;
      if (bool)
      {
        closeConnection(paramBasicPoolEntry);
        poolLock.unlock();
        return;
      }
      leasedConnections.remove(paramBasicPoolEntry);
      localObject2 = getRoutePool(localHttpRoute, true);
      if (paramBoolean)
      {
        i = ((RouteSpecificPool)localObject2).getCapacity();
        if (i >= 0)
        {
          paramBoolean = log.isDebugEnabled();
          if (paramBoolean)
          {
            if (paramLong > 0L)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("for ");
              ((StringBuilder)localObject1).append(paramLong);
              ((StringBuilder)localObject1).append(" ");
              ((StringBuilder)localObject1).append(paramTimeUnit);
              localObject1 = ((StringBuilder)localObject1).toString();
            }
            else
            {
              localObject1 = "indefinitely";
            }
            HttpClientAndroidLog localHttpClientAndroidLog = log;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Pooling connection [");
            localStringBuilder.append(localHttpRoute);
            localStringBuilder.append("][");
            localStringBuilder.append(paramBasicPoolEntry.getState());
            localStringBuilder.append("]; keep alive ");
            localStringBuilder.append((String)localObject1);
            localHttpClientAndroidLog.debug(localStringBuilder.toString());
          }
          ((RouteSpecificPool)localObject2).freeEntry(paramBasicPoolEntry);
          paramBasicPoolEntry.updateExpiry(paramLong, paramTimeUnit);
          freeConnections.add(paramBasicPoolEntry);
          break label356;
        }
      }
      closeConnection(paramBasicPoolEntry);
      ((RouteSpecificPool)localObject2).dropEntry();
      int i = numConnections;
      numConnections = (i - 1);
      label356:
      notifyWaitingThread((RouteSpecificPool)localObject2);
      poolLock.unlock();
      return;
    }
    catch (Throwable paramBasicPoolEntry)
    {
      poolLock.unlock();
      throw paramBasicPoolEntry;
    }
  }
  
  protected BasicPoolEntry getEntryBlocking(HttpRoute paramHttpRoute, Object paramObject, long paramLong, TimeUnit paramTimeUnit, WaitingThreadAborter paramWaitingThreadAborter)
  {
    Object localObject1 = null;
    Date localDate;
    if (paramLong > 0L) {
      localDate = new Date(System.currentTimeMillis() + paramTimeUnit.toMillis(paramLong));
    } else {
      localDate = null;
    }
    poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
      Object localObject2 = null;
      paramTimeUnit = (TimeUnit)localObject1;
      for (;;)
      {
        localObject1 = paramTimeUnit;
        if (paramTimeUnit == null)
        {
          boolean bool = shutdown;
          int i = 0;
          if (!bool) {
            bool = true;
          } else {
            bool = false;
          }
          Asserts.check(bool, "Connection pool shut down");
          bool = log.isDebugEnabled();
          if (bool)
          {
            paramTimeUnit = log;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("[");
            ((StringBuilder)localObject1).append(paramHttpRoute);
            ((StringBuilder)localObject1).append("] total kept alive: ");
            ((StringBuilder)localObject1).append(freeConnections.size());
            ((StringBuilder)localObject1).append(", total issued: ");
            ((StringBuilder)localObject1).append(leasedConnections.size());
            ((StringBuilder)localObject1).append(", total allocated: ");
            ((StringBuilder)localObject1).append(numConnections);
            ((StringBuilder)localObject1).append(" out of ");
            ((StringBuilder)localObject1).append(maxTotalConnections);
            paramTimeUnit.debug(((StringBuilder)localObject1).toString());
          }
          paramTimeUnit = getFreeEntry(localRouteSpecificPool, paramObject);
          localObject1 = paramTimeUnit;
          if (paramTimeUnit == null)
          {
            int j = localRouteSpecificPool.getCapacity();
            if (j > 0) {
              i = 1;
            }
            bool = log.isDebugEnabled();
            if (bool)
            {
              paramTimeUnit = log;
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append("Available capacity: ");
              ((StringBuilder)localObject3).append(localRouteSpecificPool.getCapacity());
              ((StringBuilder)localObject3).append(" out of ");
              ((StringBuilder)localObject3).append(localRouteSpecificPool.getMaxEntries());
              ((StringBuilder)localObject3).append(" [");
              ((StringBuilder)localObject3).append(paramHttpRoute);
              ((StringBuilder)localObject3).append("][");
              ((StringBuilder)localObject3).append(paramObject);
              ((StringBuilder)localObject3).append("]");
              paramTimeUnit.debug(((StringBuilder)localObject3).toString());
            }
            if (i != 0)
            {
              j = numConnections;
              int k = maxTotalConnections;
              if (j >= k) {}
            }
            for (paramTimeUnit = operator;; paramTimeUnit = operator)
            {
              paramTimeUnit = createEntry(localRouteSpecificPool, paramTimeUnit);
              break;
              if (i == 0) {
                break label478;
              }
              bool = freeConnections.isEmpty();
              if (bool) {
                break label478;
              }
              deleteLeastUsedEntry();
              localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
            }
            label478:
            bool = log.isDebugEnabled();
            if (bool)
            {
              paramTimeUnit = log;
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append("Need to wait for connection [");
              ((StringBuilder)localObject3).append(paramHttpRoute);
              ((StringBuilder)localObject3).append("][");
              ((StringBuilder)localObject3).append(paramObject);
              ((StringBuilder)localObject3).append("]");
              paramTimeUnit.debug(((StringBuilder)localObject3).toString());
            }
            Object localObject3 = localObject2;
            if (localObject2 == null)
            {
              paramTimeUnit = newWaitingThread(poolLock.newCondition(), localRouteSpecificPool);
              localObject3 = paramTimeUnit;
              paramWaitingThreadAborter.setWaitingThread(paramTimeUnit);
            }
            try
            {
              localRouteSpecificPool.queueThread((WaitingThread)localObject3);
              waitingThreads.add(localObject3);
              bool = ((WaitingThread)localObject3).await(localDate);
              localRouteSpecificPool.removeThread((WaitingThread)localObject3);
              waitingThreads.remove(localObject3);
              paramTimeUnit = (TimeUnit)localObject1;
              localObject2 = localObject3;
              if (!bool)
              {
                paramTimeUnit = (TimeUnit)localObject1;
                localObject2 = localObject3;
                if (localDate != null)
                {
                  paramLong = localDate.getTime();
                  long l = System.currentTimeMillis();
                  if (paramLong > l)
                  {
                    paramTimeUnit = (TimeUnit)localObject1;
                    localObject2 = localObject3;
                  }
                  else
                  {
                    throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
                  }
                }
              }
            }
            catch (Throwable paramHttpRoute)
            {
              localRouteSpecificPool.removeThread((WaitingThread)localObject3);
              waitingThreads.remove(localObject3);
              throw paramHttpRoute;
            }
          }
        }
      }
      poolLock.unlock();
      return localObject1;
    }
    catch (Throwable paramHttpRoute)
    {
      poolLock.unlock();
      throw paramHttpRoute;
    }
  }
  
  protected BasicPoolEntry getFreeEntry(RouteSpecificPool paramRouteSpecificPool, Object paramObject)
  {
    poolLock.lock();
    int i = 0;
    Object localObject1 = null;
    while (i == 0) {
      try
      {
        Object localObject2 = paramRouteSpecificPool.allocEntry(paramObject);
        localObject1 = localObject2;
        boolean bool;
        Object localObject3;
        if (localObject2 != null)
        {
          bool = log.isDebugEnabled();
          StringBuilder localStringBuilder;
          if (bool)
          {
            localObject3 = log;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Getting free connection [");
            localStringBuilder.append(paramRouteSpecificPool.getRoute());
            localStringBuilder.append("][");
            localStringBuilder.append(paramObject);
            localStringBuilder.append("]");
            ((HttpClientAndroidLog)localObject3).debug(localStringBuilder.toString());
          }
          freeConnections.remove(localObject2);
          bool = ((BasicPoolEntry)localObject2).isExpired(System.currentTimeMillis());
          if (bool)
          {
            bool = log.isDebugEnabled();
            if (bool)
            {
              localObject3 = log;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("Closing expired free connection [");
              localStringBuilder.append(paramRouteSpecificPool.getRoute());
              localStringBuilder.append("][");
              localStringBuilder.append(paramObject);
              localStringBuilder.append("]");
              ((HttpClientAndroidLog)localObject3).debug(localStringBuilder.toString());
            }
            closeConnection((BasicPoolEntry)localObject2);
            paramRouteSpecificPool.dropEntry();
            int j = numConnections;
            numConnections = (j - 1);
            continue;
          }
          leasedConnections.add(localObject2);
        }
        else
        {
          bool = log.isDebugEnabled();
          if (bool)
          {
            localObject2 = log;
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("No free connections [");
            ((StringBuilder)localObject3).append(paramRouteSpecificPool.getRoute());
            ((StringBuilder)localObject3).append("][");
            ((StringBuilder)localObject3).append(paramObject);
            ((StringBuilder)localObject3).append("]");
            ((HttpClientAndroidLog)localObject2).debug(((StringBuilder)localObject3).toString());
          }
        }
        i = 1;
      }
      catch (Throwable paramRouteSpecificPool)
      {
        poolLock.unlock();
        throw paramRouteSpecificPool;
      }
    }
    poolLock.unlock();
    return localObject1;
  }
  
  protected RouteSpecificPool getRoutePool(HttpRoute paramHttpRoute, boolean paramBoolean)
  {
    poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool2 = (RouteSpecificPool)routeToPool.get(paramHttpRoute);
      RouteSpecificPool localRouteSpecificPool1 = localRouteSpecificPool2;
      if (localRouteSpecificPool2 == null)
      {
        localRouteSpecificPool1 = localRouteSpecificPool2;
        if (paramBoolean)
        {
          localRouteSpecificPool2 = newRouteSpecificPool(paramHttpRoute);
          localRouteSpecificPool1 = localRouteSpecificPool2;
          routeToPool.put(paramHttpRoute, localRouteSpecificPool2);
        }
      }
      poolLock.unlock();
      return localRouteSpecificPool1;
    }
    catch (Throwable paramHttpRoute)
    {
      poolLock.unlock();
      throw paramHttpRoute;
    }
  }
  
  protected RouteSpecificPool newRouteSpecificPool(HttpRoute paramHttpRoute)
  {
    return new RouteSpecificPool(paramHttpRoute, connPerRoute);
  }
  
  protected WaitingThread newWaitingThread(Condition paramCondition, RouteSpecificPool paramRouteSpecificPool)
  {
    return new WaitingThread(paramCondition, paramRouteSpecificPool);
  }
  
  protected void notifyWaitingThread(RouteSpecificPool paramRouteSpecificPool)
  {
    poolLock.lock();
    if (paramRouteSpecificPool != null) {}
    try
    {
      boolean bool = paramRouteSpecificPool.hasThread();
      if (bool)
      {
        bool = log.isDebugEnabled();
        if (bool)
        {
          HttpClientAndroidLog localHttpClientAndroidLog = log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Notifying thread waiting on pool [");
          localStringBuilder.append(paramRouteSpecificPool.getRoute());
          localStringBuilder.append("]");
          localHttpClientAndroidLog.debug(localStringBuilder.toString());
        }
        paramRouteSpecificPool = paramRouteSpecificPool.nextThread();
      }
      else
      {
        bool = waitingThreads.isEmpty();
        if (!bool)
        {
          bool = log.isDebugEnabled();
          if (bool) {
            log.debug("Notifying thread waiting on any pool");
          }
          paramRouteSpecificPool = (WaitingThread)waitingThreads.remove();
        }
        else
        {
          bool = log.isDebugEnabled();
          if (bool) {
            log.debug("Notifying no-one, there are no waiting threads");
          }
          paramRouteSpecificPool = null;
        }
      }
      if (paramRouteSpecificPool != null) {
        paramRouteSpecificPool.wakeup();
      }
      poolLock.unlock();
      return;
    }
    catch (Throwable paramRouteSpecificPool)
    {
      poolLock.unlock();
      throw paramRouteSpecificPool;
    }
  }
  
  public PoolEntryRequest requestPoolEntry(HttpRoute paramHttpRoute, Object paramObject)
  {
    return new ConnPoolByRoute.1(this, new WaitingThreadAborter(), paramHttpRoute, paramObject);
  }
  
  public void shutdown()
  {
    poolLock.lock();
    try
    {
      boolean bool = shutdown;
      if (bool)
      {
        poolLock.unlock();
        return;
      }
      shutdown = true;
      Iterator localIterator = leasedConnections.iterator();
      Object localObject;
      for (;;)
      {
        bool = localIterator.hasNext();
        if (!bool) {
          break;
        }
        localObject = (BasicPoolEntry)localIterator.next();
        localIterator.remove();
        closeConnection((BasicPoolEntry)localObject);
      }
      localIterator = freeConnections.iterator();
      for (;;)
      {
        bool = localIterator.hasNext();
        if (!bool) {
          break;
        }
        localObject = (BasicPoolEntry)localIterator.next();
        localIterator.remove();
        bool = log.isDebugEnabled();
        if (bool)
        {
          HttpClientAndroidLog localHttpClientAndroidLog = log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Closing connection [");
          localStringBuilder.append(((BasicPoolEntry)localObject).getPlannedRoute());
          localStringBuilder.append("][");
          localStringBuilder.append(((AbstractPoolEntry)localObject).getState());
          localStringBuilder.append("]");
          localHttpClientAndroidLog.debug(localStringBuilder.toString());
        }
        closeConnection((BasicPoolEntry)localObject);
      }
      localIterator = waitingThreads.iterator();
      for (;;)
      {
        bool = localIterator.hasNext();
        if (!bool) {
          break;
        }
        localObject = (WaitingThread)localIterator.next();
        localIterator.remove();
        ((WaitingThread)localObject).wakeup();
      }
      routeToPool.clear();
      poolLock.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      poolLock.unlock();
      throw localThrowable;
    }
  }
}
