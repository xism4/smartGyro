package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.RouteTracker;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.pool.PoolEntry;
import cz.msebera.android.http.util.HttpParams;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
class ManagedClientConnectionImpl
  implements ManagedClientConnection
{
  private volatile long duration;
  private final ClientConnectionManager manager;
  private final ClientConnectionOperator operator;
  private volatile HttpPoolEntry poolEntry;
  private volatile boolean reusable;
  
  ManagedClientConnectionImpl(ClientConnectionManager paramClientConnectionManager, ClientConnectionOperator paramClientConnectionOperator, HttpPoolEntry paramHttpPoolEntry)
  {
    Args.notNull(paramClientConnectionManager, "Connection manager");
    Args.notNull(paramClientConnectionOperator, "Connection operator");
    Args.notNull(paramHttpPoolEntry, "HTTP pool entry");
    manager = paramClientConnectionManager;
    operator = paramClientConnectionOperator;
    poolEntry = paramHttpPoolEntry;
    reusable = false;
    duration = Long.MAX_VALUE;
  }
  
  private OperatedClientConnection ensureConnection()
  {
    HttpPoolEntry localHttpPoolEntry = poolEntry;
    if (localHttpPoolEntry != null) {
      return (OperatedClientConnection)localHttpPoolEntry.getConnection();
    }
    throw new ConnectionShutdownException();
  }
  
  private HttpPoolEntry ensurePoolEntry()
  {
    HttpPoolEntry localHttpPoolEntry = poolEntry;
    if (localHttpPoolEntry != null) {
      return localHttpPoolEntry;
    }
    throw new ConnectionShutdownException();
  }
  
  private OperatedClientConnection getConnection()
  {
    HttpPoolEntry localHttpPoolEntry = poolEntry;
    if (localHttpPoolEntry == null) {
      return null;
    }
    return (OperatedClientConnection)localHttpPoolEntry.getConnection();
  }
  
  /* Error */
  public void abortConnection()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   6: ifnonnull +6 -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_0
    //   14: putfield 42	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:reusable	Z
    //   17: aload_0
    //   18: getfield 40	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   21: invokevirtual 55	cz/msebera/android/http/pool/PoolEntry:getConnection	()Ljava/lang/Object;
    //   24: checkcast 57	cz/msebera/android/http/conn/OperatedClientConnection
    //   27: astore_1
    //   28: aload_1
    //   29: invokeinterface 72 1 0
    //   34: aload_0
    //   35: getfield 36	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:manager	Lcz/msebera/android/http/conn/ClientConnectionManager;
    //   38: aload_0
    //   39: aload_0
    //   40: getfield 46	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:duration	J
    //   43: getstatic 78	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   46: invokeinterface 84 5 0
    //   51: aload_0
    //   52: aconst_null
    //   53: putfield 40	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   56: aload_0
    //   57: monitorexit
    //   58: return
    //   59: astore_1
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_1
    //   63: athrow
    //   64: astore_1
    //   65: goto -31 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	ManagedClientConnectionImpl
    //   27	2	1	localOperatedClientConnection	OperatedClientConnection
    //   59	4	1	localThrowable	Throwable
    //   64	1	1	localIOException	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   2	11	59	java/lang/Throwable
    //   12	28	59	java/lang/Throwable
    //   28	34	59	java/lang/Throwable
    //   34	58	59	java/lang/Throwable
    //   60	62	59	java/lang/Throwable
    //   28	34	64	java/io/IOException
  }
  
  public void close()
  {
    HttpPoolEntry localHttpPoolEntry = poolEntry;
    if (localHttpPoolEntry != null)
    {
      OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)localHttpPoolEntry.getConnection();
      localHttpPoolEntry.getTracker().reset();
      localOperatedClientConnection.close();
    }
  }
  
  HttpPoolEntry detach()
  {
    HttpPoolEntry localHttpPoolEntry = poolEntry;
    poolEntry = null;
    return localHttpPoolEntry;
  }
  
  public void flush()
  {
    ensureConnection().flush();
  }
  
  public ClientConnectionManager getManager()
  {
    return manager;
  }
  
  HttpPoolEntry getPoolEntry()
  {
    return poolEntry;
  }
  
  public InetAddress getRemoteAddress()
  {
    return ensureConnection().getRemoteAddress();
  }
  
  public int getRemotePort()
  {
    return ensureConnection().getRemotePort();
  }
  
  public HttpRoute getRoute()
  {
    return ensurePoolEntry().getEffectiveRoute();
  }
  
  public SSLSession getSSLSession()
  {
    Socket localSocket = ensureConnection().getSocket();
    if ((localSocket instanceof SSLSocket)) {
      return ((SSLSocket)localSocket).getSession();
    }
    return null;
  }
  
  public boolean isMarkedReusable()
  {
    return reusable;
  }
  
  public boolean isOpen()
  {
    OperatedClientConnection localOperatedClientConnection = getConnection();
    if (localOperatedClientConnection != null) {
      return localOperatedClientConnection.isOpen();
    }
    return false;
  }
  
  public boolean isResponseAvailable(int paramInt)
  {
    return ensureConnection().isResponseAvailable(paramInt);
  }
  
  public boolean isStale()
  {
    OperatedClientConnection localOperatedClientConnection = getConnection();
    if (localOperatedClientConnection != null) {
      return localOperatedClientConnection.isStale();
    }
    return true;
  }
  
  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    for (;;)
    {
      try
      {
        if (poolEntry != null)
        {
          Object localObject = poolEntry.getTracker();
          Asserts.notNull(localObject, "Route tracker");
          Asserts.check(((RouteTracker)localObject).isConnected(), "Connection not open");
          Asserts.check(((RouteTracker)localObject).isTunnelled(), "Protocol layering without a tunnel not supported");
          if (!((RouteTracker)localObject).isLayered())
          {
            bool = true;
            Asserts.check(bool, "Multiple protocol layering not supported");
            localObject = ((RouteTracker)localObject).getTargetHost();
            OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)poolEntry.getConnection();
            operator.updateSecureConnection(localOperatedClientConnection, (HttpHost)localObject, paramHttpContext, paramHttpParams);
            try
            {
              if (poolEntry != null)
              {
                poolEntry.getTracker().layerProtocol(localOperatedClientConnection.isSecure());
                return;
              }
              throw new InterruptedIOException();
            }
            catch (Throwable paramHttpContext)
            {
              throw paramHttpContext;
            }
          }
        }
        else
        {
          throw new ConnectionShutdownException();
        }
      }
      catch (Throwable paramHttpContext)
      {
        throw paramHttpContext;
      }
      boolean bool = false;
    }
  }
  
  public void markReusable()
  {
    reusable = true;
  }
  
  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpRoute, "Route");
    Args.notNull(paramHttpParams, "HTTP parameters");
    for (;;)
    {
      try
      {
        if (poolEntry != null)
        {
          Object localObject = poolEntry.getTracker();
          Asserts.notNull(localObject, "Route tracker");
          if (!((RouteTracker)localObject).isConnected())
          {
            bool = true;
            Asserts.check(bool, "Connection already open");
            OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)poolEntry.getConnection();
            HttpHost localHttpHost = paramHttpRoute.getProxyHost();
            ClientConnectionOperator localClientConnectionOperator = operator;
            if (localHttpHost != null) {
              localObject = localHttpHost;
            } else {
              localObject = paramHttpRoute.getTargetHost();
            }
            localClientConnectionOperator.openConnection(localOperatedClientConnection, (HttpHost)localObject, paramHttpRoute.getLocalAddress(), paramHttpContext, paramHttpParams);
            try
            {
              if (poolEntry != null)
              {
                paramHttpRoute = poolEntry.getTracker();
                if (localHttpHost == null) {
                  paramHttpRoute.connectProxy(localOperatedClientConnection.isSecure());
                } else {
                  paramHttpRoute.connectProxy(localHttpHost, localOperatedClientConnection.isSecure());
                }
                return;
              }
              throw new InterruptedIOException();
            }
            catch (Throwable paramHttpRoute)
            {
              throw paramHttpRoute;
            }
          }
        }
        else
        {
          throw new ConnectionShutdownException();
        }
      }
      catch (Throwable paramHttpRoute)
      {
        throw paramHttpRoute;
      }
      boolean bool = false;
    }
  }
  
  public void receiveResponseEntity(HttpResponse paramHttpResponse)
  {
    ensureConnection().receiveResponseEntity(paramHttpResponse);
  }
  
  public HttpResponse receiveResponseHeader()
  {
    return ensureConnection().receiveResponseHeader();
  }
  
  public void releaseConnection()
  {
    try
    {
      if (poolEntry == null) {
        return;
      }
      manager.releaseConnection(this, duration, TimeUnit.MILLISECONDS);
      poolEntry = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void sendRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    ensureConnection().sendRequestEntity(paramHttpEntityEnclosingRequest);
  }
  
  public void sendRequestHeader(HttpRequest paramHttpRequest)
  {
    ensureConnection().sendRequestHeader(paramHttpRequest);
  }
  
  public void setIdleDuration(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong > 0L) {
      paramLong = paramTimeUnit.toMillis(paramLong);
    } else {
      paramLong = -1L;
    }
    duration = paramLong;
  }
  
  public void setSocketTimeout(int paramInt)
  {
    ensureConnection().setSocketTimeout(paramInt);
  }
  
  public void setState(Object paramObject)
  {
    ensurePoolEntry().setState(paramObject);
  }
  
  public void shutdown()
  {
    HttpPoolEntry localHttpPoolEntry = poolEntry;
    if (localHttpPoolEntry != null)
    {
      OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)localHttpPoolEntry.getConnection();
      localHttpPoolEntry.getTracker().reset();
      localOperatedClientConnection.shutdown();
    }
  }
  
  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    for (;;)
    {
      try
      {
        if (poolEntry != null)
        {
          Object localObject = poolEntry.getTracker();
          Asserts.notNull(localObject, "Route tracker");
          Asserts.check(((RouteTracker)localObject).isConnected(), "Connection not open");
          if (!((RouteTracker)localObject).isTunnelled())
          {
            bool = true;
            Asserts.check(bool, "Connection is already tunnelled");
            localObject = ((RouteTracker)localObject).getTargetHost();
            OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)poolEntry.getConnection();
            localOperatedClientConnection.update(null, (HttpHost)localObject, paramBoolean, paramHttpParams);
            try
            {
              if (poolEntry != null)
              {
                poolEntry.getTracker().tunnelTarget(paramBoolean);
                return;
              }
              throw new InterruptedIOException();
            }
            catch (Throwable paramHttpParams)
            {
              throw paramHttpParams;
            }
          }
        }
        else
        {
          throw new ConnectionShutdownException();
        }
      }
      catch (Throwable paramHttpParams)
      {
        throw paramHttpParams;
      }
      boolean bool = false;
    }
  }
  
  public void unmarkReusable()
  {
    reusable = false;
  }
}
