package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.execchain.HttpContext;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public abstract class AbstractClientConnAdapter
  implements ManagedClientConnection, HttpContext
{
  private final ClientConnectionManager connManager;
  private volatile long duration;
  private volatile boolean markedReusable;
  private volatile boolean released;
  private volatile OperatedClientConnection wrappedConnection;
  
  protected AbstractClientConnAdapter(ClientConnectionManager paramClientConnectionManager, OperatedClientConnection paramOperatedClientConnection)
  {
    connManager = paramClientConnectionManager;
    wrappedConnection = paramOperatedClientConnection;
    markedReusable = false;
    released = false;
    duration = Long.MAX_VALUE;
  }
  
  /* Error */
  public void abortConnection()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 31	cz/msebera/android/http/impl/conn/AbstractClientConnAdapter:released	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 31	cz/msebera/android/http/impl/conn/AbstractClientConnAdapter:released	Z
    //   19: aload_0
    //   20: invokevirtual 44	cz/msebera/android/http/impl/conn/AbstractClientConnAdapter:unmarkReusable	()V
    //   23: aload_0
    //   24: invokeinterface 49 1 0
    //   29: aload_0
    //   30: getfield 25	cz/msebera/android/http/impl/conn/AbstractClientConnAdapter:connManager	Lcz/msebera/android/http/conn/ClientConnectionManager;
    //   33: aload_0
    //   34: aload_0
    //   35: getfield 35	cz/msebera/android/http/impl/conn/AbstractClientConnAdapter:duration	J
    //   38: getstatic 55	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   41: invokeinterface 61 5 0
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: astore_2
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_2
    //   53: athrow
    //   54: astore_2
    //   55: goto -26 -> 29
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	AbstractClientConnAdapter
    //   6	2	1	bool	boolean
    //   49	4	2	localThrowable	Throwable
    //   54	1	2	localIOException	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   2	7	49	java/lang/Throwable
    //   14	23	49	java/lang/Throwable
    //   23	29	49	java/lang/Throwable
    //   29	46	49	java/lang/Throwable
    //   23	29	54	java/io/IOException
  }
  
  protected final void assertValid(OperatedClientConnection paramOperatedClientConnection)
  {
    if ((!isReleased()) && (paramOperatedClientConnection != null)) {
      return;
    }
    throw new ConnectionShutdownException();
  }
  
  protected void detach()
  {
    try
    {
      wrappedConnection = null;
      duration = Long.MAX_VALUE;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void flush()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    localOperatedClientConnection.flush();
  }
  
  public Object getAttribute(String paramString)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    if ((localOperatedClientConnection instanceof HttpContext)) {
      return ((HttpContext)localOperatedClientConnection).getAttribute(paramString);
    }
    return null;
  }
  
  protected ClientConnectionManager getManager()
  {
    return connManager;
  }
  
  public InetAddress getRemoteAddress()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getRemoteAddress();
  }
  
  public int getRemotePort()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getRemotePort();
  }
  
  public SSLSession getSSLSession()
  {
    Object localObject = getWrappedConnection();
    assertValid((OperatedClientConnection)localObject);
    if (!isOpen()) {
      return null;
    }
    localObject = ((OperatedClientConnection)localObject).getSocket();
    if ((localObject instanceof SSLSocket)) {
      return ((SSLSocket)localObject).getSession();
    }
    return null;
  }
  
  protected OperatedClientConnection getWrappedConnection()
  {
    return wrappedConnection;
  }
  
  public boolean isMarkedReusable()
  {
    return markedReusable;
  }
  
  public boolean isOpen()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    if (localOperatedClientConnection == null) {
      return false;
    }
    return localOperatedClientConnection.isOpen();
  }
  
  protected boolean isReleased()
  {
    return released;
  }
  
  public boolean isResponseAvailable(int paramInt)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.isResponseAvailable(paramInt);
  }
  
  public boolean isStale()
  {
    if (isReleased()) {
      return true;
    }
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    if (localOperatedClientConnection == null) {
      return true;
    }
    return localOperatedClientConnection.isStale();
  }
  
  public void markReusable()
  {
    markedReusable = true;
  }
  
  public void receiveResponseEntity(HttpResponse paramHttpResponse)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.receiveResponseEntity(paramHttpResponse);
  }
  
  public HttpResponse receiveResponseHeader()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    return localOperatedClientConnection.receiveResponseHeader();
  }
  
  public void releaseConnection()
  {
    try
    {
      boolean bool = released;
      if (bool) {
        return;
      }
      released = true;
      connManager.releaseConnection(this, duration, TimeUnit.MILLISECONDS);
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void sendRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.sendRequestEntity(paramHttpEntityEnclosingRequest);
  }
  
  public void sendRequestHeader(HttpRequest paramHttpRequest)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.sendRequestHeader(paramHttpRequest);
  }
  
  public void setAttribute(String paramString, Object paramObject)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    if ((localOperatedClientConnection instanceof HttpContext)) {
      ((HttpContext)localOperatedClientConnection).setAttribute(paramString, paramObject);
    }
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
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    localOperatedClientConnection.setSocketTimeout(paramInt);
  }
  
  public void unmarkReusable()
  {
    markedReusable = false;
  }
}
