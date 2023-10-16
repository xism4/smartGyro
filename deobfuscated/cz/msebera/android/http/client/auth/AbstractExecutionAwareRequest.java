package cz.msebera.android.http.client.auth;

import c.a.a.a.c.a;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.client.ssl.CloneUtils;
import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ConnectionReleaseTrigger;
import cz.msebera.android.http.message.AbstractHttpMessage;
import cz.msebera.android.http.message.HeaderGroup;
import cz.msebera.android.http.methods.Cancellable;
import cz.msebera.android.http.util.HttpParams;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractExecutionAwareRequest
  extends AbstractHttpMessage
  implements HttpMessage, AbortableHttpRequest, Cloneable, HttpRequest
{
  private final AtomicBoolean aborted = new AtomicBoolean(false);
  private final AtomicReference<a> cancellableRef = new AtomicReference(null);
  
  protected AbstractExecutionAwareRequest() {}
  
  public void abort()
  {
    if (aborted.compareAndSet(false, true))
    {
      Cancellable localCancellable = (Cancellable)cancellableRef.getAndSet(null);
      if (localCancellable != null) {
        localCancellable.cancel();
      }
    }
  }
  
  public Object clone()
  {
    AbstractExecutionAwareRequest localAbstractExecutionAwareRequest = (AbstractExecutionAwareRequest)super.clone();
    headergroup = ((HeaderGroup)CloneUtils.cloneObject(headergroup));
    params = ((HttpParams)CloneUtils.cloneObject(params));
    return localAbstractExecutionAwareRequest;
  }
  
  public boolean isAborted()
  {
    return aborted.get();
  }
  
  public void setCancellable(Cancellable paramCancellable)
  {
    if (!aborted.get()) {
      cancellableRef.set(paramCancellable);
    }
  }
  
  public void setConnectionRequest(ClientConnectionRequest paramClientConnectionRequest)
  {
    setCancellable(new AbstractExecutionAwareRequest.1(this, paramClientConnectionRequest));
  }
  
  public void setReleaseTrigger(ConnectionReleaseTrigger paramConnectionReleaseTrigger)
  {
    setCancellable(new AbstractExecutionAwareRequest.2(this, paramConnectionReleaseTrigger));
  }
}
