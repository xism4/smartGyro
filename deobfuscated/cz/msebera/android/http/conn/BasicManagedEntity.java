package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.entity.HttpEntityWrapper;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.EntityUtils;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

@Deprecated
public class BasicManagedEntity
  extends HttpEntityWrapper
  implements ConnectionReleaseTrigger, EofSensorWatcher
{
  protected final boolean attemptReuse;
  protected ManagedClientConnection managedConn;
  
  public BasicManagedEntity(HttpEntity paramHttpEntity, ManagedClientConnection paramManagedClientConnection, boolean paramBoolean)
  {
    super(paramHttpEntity);
    Args.notNull(paramManagedClientConnection, "Connection");
    managedConn = paramManagedClientConnection;
    attemptReuse = paramBoolean;
  }
  
  private void ensureConsumed()
  {
    ManagedClientConnection localManagedClientConnection = managedConn;
    if (localManagedClientConnection == null) {
      return;
    }
    try
    {
      boolean bool = attemptReuse;
      if (bool)
      {
        EntityUtils.consume(wrappedEntity);
        managedConn.markReusable();
      }
      else
      {
        localManagedClientConnection.unmarkReusable();
      }
      releaseManagedConnection();
      return;
    }
    catch (Throwable localThrowable)
    {
      releaseManagedConnection();
      throw localThrowable;
    }
  }
  
  public void abortConnection()
  {
    ManagedClientConnection localManagedClientConnection = managedConn;
    if (localManagedClientConnection != null) {
      try
      {
        localManagedClientConnection.abortConnection();
        managedConn = null;
        return;
      }
      catch (Throwable localThrowable)
      {
        managedConn = null;
        throw localThrowable;
      }
    }
  }
  
  public void consumeContent()
  {
    ensureConsumed();
  }
  
  public boolean eofDetected(InputStream paramInputStream)
  {
    try
    {
      ManagedClientConnection localManagedClientConnection = managedConn;
      if (localManagedClientConnection != null)
      {
        boolean bool = attemptReuse;
        if (bool)
        {
          paramInputStream.close();
          managedConn.markReusable();
        }
        else
        {
          managedConn.unmarkReusable();
        }
      }
      releaseManagedConnection();
      return false;
    }
    catch (Throwable paramInputStream)
    {
      releaseManagedConnection();
      throw paramInputStream;
    }
  }
  
  public InputStream getContent()
  {
    return new EofSensorInputStream(wrappedEntity.getContent(), this);
  }
  
  public boolean isRepeatable()
  {
    return false;
  }
  
  protected void releaseManagedConnection()
  {
    ManagedClientConnection localManagedClientConnection = managedConn;
    if (localManagedClientConnection != null) {
      try
      {
        localManagedClientConnection.releaseConnection();
        managedConn = null;
        return;
      }
      catch (Throwable localThrowable)
      {
        managedConn = null;
        throw localThrowable;
      }
    }
  }
  
  public boolean streamAbort(InputStream paramInputStream)
  {
    paramInputStream = managedConn;
    if (paramInputStream != null) {
      paramInputStream.abortConnection();
    }
    return false;
  }
  
  public boolean streamClosed(InputStream paramInputStream)
  {
    try
    {
      ManagedClientConnection localManagedClientConnection = managedConn;
      if (localManagedClientConnection != null)
      {
        boolean bool = attemptReuse;
        if (bool)
        {
          bool = managedConn.isOpen();
          try
          {
            paramInputStream.close();
            paramInputStream = managedConn;
            paramInputStream.markReusable();
          }
          catch (SocketException paramInputStream)
          {
            if (bool) {
              break label54;
            }
          }
          break label65;
          label54:
          throw paramInputStream;
        }
        else
        {
          managedConn.unmarkReusable();
        }
      }
      label65:
      releaseManagedConnection();
      return false;
    }
    catch (Throwable paramInputStream)
    {
      releaseManagedConnection();
      throw paramInputStream;
    }
  }
  
  public void writeTo(OutputStream paramOutputStream)
  {
    super.writeTo(paramOutputStream);
    ensureConsumed();
  }
}
