package cz.msebera.android.http.impl.conn.tsccm;

import c.a.a.a.i.c.a.i;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.tsccm.ConnPerRouteBean;
import cz.msebera.android.http.impl.conn.AbstractClientConnAdapter;
import cz.msebera.android.http.impl.conn.AbstractPooledConnAdapter;
import cz.msebera.android.http.impl.conn.DefaultClientConnectionOperator;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Deprecated
public class ThreadSafeClientConnManager
  implements ClientConnectionManager
{
  protected final ClientConnectionOperator connOperator;
  protected final ConnPerRouteBean connPerRoute;
  protected final AbstractConnPool connectionPool;
  public HttpClientAndroidLog log;
  protected final ConnPoolByRoute pool;
  protected final SchemeRegistry schemeRegistry;
  
  public ThreadSafeClientConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    Args.notNull(paramSchemeRegistry, "Scheme registry");
    log = new HttpClientAndroidLog(i.class);
    schemeRegistry = paramSchemeRegistry;
    connPerRoute = new ConnPerRouteBean();
    connOperator = createConnectionOperator(paramSchemeRegistry);
    pool = ((ConnPoolByRoute)createConnectionPool(paramHttpParams));
    connectionPool = pool;
  }
  
  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
  }
  
  protected AbstractConnPool createConnectionPool(HttpParams paramHttpParams)
  {
    return new ConnPoolByRoute(connOperator, paramHttpParams);
  }
  
  protected void finalize()
  {
    try
    {
      shutdown();
      super.finalize();
      return;
    }
    catch (Throwable localThrowable)
    {
      super.finalize();
      throw localThrowable;
    }
  }
  
  public SchemeRegistry getSchemeRegistry()
  {
    return schemeRegistry;
  }
  
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    Args.check(paramManagedClientConnection instanceof BasicPooledConnAdapter, "Connection class mismatch, connection not obtained from this manager");
    localBasicPooledConnAdapter = (BasicPooledConnAdapter)paramManagedClientConnection;
    if (localBasicPooledConnAdapter.getPoolEntry() != null)
    {
      if (localBasicPooledConnAdapter.getManager() == this) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      Asserts.check(bool1, "Connection not obtained from this manager");
    }
    try
    {
      localBasicPoolEntry = (BasicPoolEntry)localBasicPooledConnAdapter.getPoolEntry();
      if (localBasicPoolEntry == null) {
        return;
      }
      try
      {
        bool1 = localBasicPooledConnAdapter.isOpen();
        if (bool1)
        {
          bool1 = localBasicPooledConnAdapter.isMarkedReusable();
          if (!bool1) {
            localBasicPooledConnAdapter.shutdown();
          }
        }
        bool2 = localBasicPooledConnAdapter.isMarkedReusable();
        bool1 = bool2;
        if (log.isDebugEnabled())
        {
          if (bool2) {
            paramManagedClientConnection = log;
          }
          for (str = "Released connection is reusable.";; str = "Released connection is not reusable.")
          {
            paramManagedClientConnection.debug(str);
            break;
            paramManagedClientConnection = log;
          }
        }
        localBasicPooledConnAdapter.detach();
        paramManagedClientConnection = pool;
      }
      catch (Throwable localThrowable) {}catch (IOException paramManagedClientConnection)
      {
        for (;;)
        {
          if (log.isDebugEnabled()) {
            log.debug("Exception shutting down released connection.", paramManagedClientConnection);
          }
          boolean bool2 = localBasicPooledConnAdapter.isMarkedReusable();
          bool1 = bool2;
          if (log.isDebugEnabled())
          {
            if (bool2) {
              paramManagedClientConnection = log;
            }
            for (str = "Released connection is reusable.";; str = "Released connection is not reusable.")
            {
              paramManagedClientConnection.debug(str);
              break;
              paramManagedClientConnection = log;
            }
          }
          localBasicPooledConnAdapter.detach();
          paramManagedClientConnection = pool;
        }
      }
      paramManagedClientConnection.freeEntry(localBasicPoolEntry, bool1, paramLong, paramTimeUnit);
      return;
    }
    catch (Throwable paramManagedClientConnection)
    {
      BasicPoolEntry localBasicPoolEntry;
      String str;
      throw paramManagedClientConnection;
    }
    boolean bool1 = localBasicPooledConnAdapter.isMarkedReusable();
    if (log.isDebugEnabled())
    {
      if (bool1) {
        paramManagedClientConnection = log;
      }
      for (str = "Released connection is reusable.";; str = "Released connection is not reusable.")
      {
        paramManagedClientConnection.debug(str);
        break;
        paramManagedClientConnection = log;
      }
    }
    localBasicPooledConnAdapter.detach();
    pool.freeEntry(localBasicPoolEntry, bool1, paramLong, paramTimeUnit);
    throw localThrowable;
  }
  
  public ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    return new ThreadSafeClientConnManager.1(this, pool.requestPoolEntry(paramHttpRoute, paramObject), paramHttpRoute);
  }
  
  public void shutdown()
  {
    log.debug("Shutting down");
    pool.shutdown();
  }
}
