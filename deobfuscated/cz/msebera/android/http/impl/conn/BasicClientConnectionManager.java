package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.e;
import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.RouteTracker;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Deprecated
public class BasicClientConnectionManager
  implements ClientConnectionManager
{
  private static final AtomicLong COUNTER = new AtomicLong();
  private ManagedClientConnectionImpl conn;
  private final ClientConnectionOperator connOperator;
  public HttpClientAndroidLog log = new HttpClientAndroidLog(e.class);
  private HttpPoolEntry poolEntry;
  private final SchemeRegistry schemeRegistry;
  private volatile boolean shutdown;
  
  public BasicClientConnectionManager(SchemeRegistry paramSchemeRegistry)
  {
    Args.notNull(paramSchemeRegistry, "Scheme registry");
    schemeRegistry = paramSchemeRegistry;
    connOperator = createConnectionOperator(paramSchemeRegistry);
  }
  
  private void assertNotShutdown()
  {
    Asserts.check(shutdown ^ true, "Connection manager has been shut down");
  }
  
  private void shutdownConnection(HttpClientConnection paramHttpClientConnection)
  {
    try
    {
      paramHttpClientConnection.shutdown();
      return;
    }
    catch (IOException paramHttpClientConnection)
    {
      if (log.isDebugEnabled()) {
        log.debug("I/O exception shutting down connection", paramHttpClientConnection);
      }
    }
  }
  
  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
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
  
  ManagedClientConnection getConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    Args.notNull(paramHttpRoute, "Route");
    for (;;)
    {
      try
      {
        assertNotShutdown();
        Object localObject;
        if (log.isDebugEnabled())
        {
          paramObject = log;
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Get connection for route ");
          ((StringBuilder)localObject).append(paramHttpRoute);
          paramObject.debug(((StringBuilder)localObject).toString());
        }
        if (conn == null)
        {
          bool = true;
          Asserts.check(bool, "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
          if ((poolEntry != null) && (!poolEntry.getPlannedRoute().equals(paramHttpRoute)))
          {
            poolEntry.close();
            poolEntry = null;
          }
          if (poolEntry == null)
          {
            paramObject = Long.toString(COUNTER.getAndIncrement());
            localObject = connOperator.createConnection();
            poolEntry = new HttpPoolEntry(log, paramObject, paramHttpRoute, (OperatedClientConnection)localObject, 0L, TimeUnit.MILLISECONDS);
          }
          long l = System.currentTimeMillis();
          if (poolEntry.isExpired(l))
          {
            poolEntry.close();
            poolEntry.getTracker().reset();
          }
          conn = new ManagedClientConnectionImpl(this, connOperator, poolEntry);
          paramHttpRoute = conn;
          return paramHttpRoute;
        }
      }
      catch (Throwable paramHttpRoute)
      {
        throw paramHttpRoute;
      }
      boolean bool = false;
    }
  }
  
  public SchemeRegistry getSchemeRegistry()
  {
    return schemeRegistry;
  }
  
  /* Error */
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 186
    //   4: ldc -61
    //   6: invokestatic 196	cz/msebera/android/http/mime/Args:check	(ZLjava/lang/String;)V
    //   9: aload_1
    //   10: checkcast 186	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl
    //   13: astore 6
    //   15: aload 6
    //   17: monitorenter
    //   18: aload_0
    //   19: getfield 42	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:log	Lcz/msebera/android/http/cache/HttpClientAndroidLog;
    //   22: invokevirtual 81	cz/msebera/android/http/cache/HttpClientAndroidLog:isDebugEnabled	()Z
    //   25: ifeq +43 -> 68
    //   28: aload_0
    //   29: getfield 42	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:log	Lcz/msebera/android/http/cache/HttpClientAndroidLog;
    //   32: astore 7
    //   34: new 105	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   41: astore 8
    //   43: aload 8
    //   45: ldc -58
    //   47: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: aload 8
    //   53: aload_1
    //   54: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: aload 7
    //   60: aload 8
    //   62: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokevirtual 121	cz/msebera/android/http/cache/HttpClientAndroidLog:debug	(Ljava/lang/Object;)V
    //   68: aload 6
    //   70: invokevirtual 202	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:getPoolEntry	()Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   73: ifnonnull +7 -> 80
    //   76: aload 6
    //   78: monitorexit
    //   79: return
    //   80: aload 6
    //   82: invokevirtual 206	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:getManager	()Lcz/msebera/android/http/conn/ClientConnectionManager;
    //   85: aload_0
    //   86: if_acmpne +266 -> 352
    //   89: iconst_1
    //   90: istore 5
    //   92: goto +3 -> 95
    //   95: iload 5
    //   97: ldc -48
    //   99: invokestatic 69	cz/msebera/android/http/mime/Asserts:check	(ZLjava/lang/String;)V
    //   102: aload_0
    //   103: monitorenter
    //   104: aload_0
    //   105: getfield 61	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:shutdown	Z
    //   108: ifeq +15 -> 123
    //   111: aload_0
    //   112: aload 6
    //   114: invokespecial 210	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:shutdownConnection	(Lcz/msebera/android/http/HttpClientConnection;)V
    //   117: aload_0
    //   118: monitorexit
    //   119: aload 6
    //   121: monitorexit
    //   122: return
    //   123: aload 6
    //   125: invokevirtual 213	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:isOpen	()Z
    //   128: ifeq +17 -> 145
    //   131: aload 6
    //   133: invokevirtual 216	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   136: ifne +9 -> 145
    //   139: aload_0
    //   140: aload 6
    //   142: invokespecial 210	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:shutdownConnection	(Lcz/msebera/android/http/HttpClientConnection;)V
    //   145: aload 6
    //   147: invokevirtual 216	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   150: ifeq +130 -> 280
    //   153: aload_0
    //   154: getfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   157: astore 7
    //   159: aload 4
    //   161: ifnull +9 -> 170
    //   164: aload 4
    //   166: astore_1
    //   167: goto +7 -> 174
    //   170: getstatic 163	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   173: astore_1
    //   174: aload 7
    //   176: lload_2
    //   177: aload_1
    //   178: invokevirtual 222	cz/msebera/android/http/pool/PoolEntry:updateExpiry	(JLjava/util/concurrent/TimeUnit;)V
    //   181: aload_0
    //   182: getfield 42	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:log	Lcz/msebera/android/http/cache/HttpClientAndroidLog;
    //   185: invokevirtual 81	cz/msebera/android/http/cache/HttpClientAndroidLog:isDebugEnabled	()Z
    //   188: ifeq +92 -> 280
    //   191: lload_2
    //   192: lconst_0
    //   193: lcmp
    //   194: ifle +164 -> 358
    //   197: new 105	java/lang/StringBuilder
    //   200: dup
    //   201: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   204: astore_1
    //   205: aload_1
    //   206: ldc -32
    //   208: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: pop
    //   212: aload_1
    //   213: lload_2
    //   214: invokevirtual 227	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   217: pop
    //   218: aload_1
    //   219: ldc -27
    //   221: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: pop
    //   225: aload_1
    //   226: aload 4
    //   228: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: aload_1
    //   233: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   236: astore_1
    //   237: goto +3 -> 240
    //   240: aload_0
    //   241: getfield 42	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:log	Lcz/msebera/android/http/cache/HttpClientAndroidLog;
    //   244: astore 4
    //   246: new 105	java/lang/StringBuilder
    //   249: dup
    //   250: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   253: astore 7
    //   255: aload 7
    //   257: ldc -25
    //   259: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: pop
    //   263: aload 7
    //   265: aload_1
    //   266: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: pop
    //   270: aload 4
    //   272: aload 7
    //   274: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   277: invokevirtual 121	cz/msebera/android/http/cache/HttpClientAndroidLog:debug	(Ljava/lang/Object;)V
    //   280: aload 6
    //   282: invokevirtual 234	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:detach	()Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   285: pop
    //   286: aload_0
    //   287: aconst_null
    //   288: putfield 123	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:conn	Lcz/msebera/android/http/impl/conn/ManagedClientConnectionImpl;
    //   291: aload_0
    //   292: getfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   295: invokevirtual 237	cz/msebera/android/http/impl/conn/HttpPoolEntry:isClosed	()Z
    //   298: ifeq +8 -> 306
    //   301: aload_0
    //   302: aconst_null
    //   303: putfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   306: aload_0
    //   307: monitorexit
    //   308: aload 6
    //   310: monitorexit
    //   311: return
    //   312: astore_1
    //   313: aload 6
    //   315: invokevirtual 234	cz/msebera/android/http/impl/conn/ManagedClientConnectionImpl:detach	()Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   318: pop
    //   319: aload_0
    //   320: aconst_null
    //   321: putfield 123	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:conn	Lcz/msebera/android/http/impl/conn/ManagedClientConnectionImpl;
    //   324: aload_0
    //   325: getfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   328: invokevirtual 237	cz/msebera/android/http/impl/conn/HttpPoolEntry:isClosed	()Z
    //   331: ifeq +8 -> 339
    //   334: aload_0
    //   335: aconst_null
    //   336: putfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   339: aload_1
    //   340: athrow
    //   341: astore_1
    //   342: aload_0
    //   343: monitorexit
    //   344: aload_1
    //   345: athrow
    //   346: astore_1
    //   347: aload 6
    //   349: monitorexit
    //   350: aload_1
    //   351: athrow
    //   352: iconst_0
    //   353: istore 5
    //   355: goto -260 -> 95
    //   358: ldc -17
    //   360: astore_1
    //   361: goto -121 -> 240
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	364	0	this	BasicClientConnectionManager
    //   0	364	1	paramManagedClientConnection	ManagedClientConnection
    //   0	364	2	paramLong	long
    //   0	364	4	paramTimeUnit	TimeUnit
    //   90	264	5	bool	boolean
    //   13	335	6	localManagedClientConnectionImpl	ManagedClientConnectionImpl
    //   32	241	7	localObject	Object
    //   41	20	8	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   123	145	312	java/lang/Throwable
    //   145	159	312	java/lang/Throwable
    //   170	174	312	java/lang/Throwable
    //   174	191	312	java/lang/Throwable
    //   197	237	312	java/lang/Throwable
    //   240	280	312	java/lang/Throwable
    //   104	119	341	java/lang/Throwable
    //   280	306	341	java/lang/Throwable
    //   306	308	341	java/lang/Throwable
    //   313	339	341	java/lang/Throwable
    //   339	341	341	java/lang/Throwable
    //   342	344	341	java/lang/Throwable
    //   18	68	346	java/lang/Throwable
    //   68	79	346	java/lang/Throwable
    //   80	89	346	java/lang/Throwable
    //   95	104	346	java/lang/Throwable
    //   119	122	346	java/lang/Throwable
    //   308	311	346	java/lang/Throwable
    //   344	346	346	java/lang/Throwable
    //   347	350	346	java/lang/Throwable
  }
  
  public final ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    return new PoolingClientConnectionManager.1(this, paramHttpRoute, paramObject);
  }
  
  /* Error */
  public void shutdown()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield 61	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:shutdown	Z
    //   7: aload_0
    //   8: getfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   11: ifnull +10 -> 21
    //   14: aload_0
    //   15: getfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   18: invokevirtual 142	cz/msebera/android/http/impl/conn/HttpPoolEntry:close	()V
    //   21: aload_0
    //   22: aconst_null
    //   23: putfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   26: aload_0
    //   27: aconst_null
    //   28: putfield 123	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:conn	Lcz/msebera/android/http/impl/conn/ManagedClientConnectionImpl;
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield 127	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:poolEntry	Lcz/msebera/android/http/impl/conn/HttpPoolEntry;
    //   40: aload_0
    //   41: aconst_null
    //   42: putfield 123	cz/msebera/android/http/impl/conn/BasicClientConnectionManager:conn	Lcz/msebera/android/http/impl/conn/ManagedClientConnectionImpl;
    //   45: aload_1
    //   46: athrow
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	BasicClientConnectionManager
    //   34	12	1	localThrowable1	Throwable
    //   47	4	1	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   7	21	34	java/lang/Throwable
    //   2	7	47	java/lang/Throwable
    //   21	33	47	java/lang/Throwable
    //   35	47	47	java/lang/Throwable
    //   48	50	47	java/lang/Throwable
  }
}
