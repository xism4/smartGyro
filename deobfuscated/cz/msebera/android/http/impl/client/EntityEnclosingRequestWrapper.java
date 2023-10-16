package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.entity.HttpEntityWrapper;
import cz.msebera.android.http.message.AbstractHttpMessage;
import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public class EntityEnclosingRequestWrapper
  extends RequestWrapper
  implements HttpEntityEnclosingRequest
{
  private boolean consumed;
  private HttpEntity entity;
  
  public EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    super(paramHttpEntityEnclosingRequest);
    setEntity(paramHttpEntityEnclosingRequest.getEntity());
  }
  
  public boolean expectContinue()
  {
    Header localHeader = getFirstHeader("Expect");
    return (localHeader != null) && ("100-continue".equalsIgnoreCase(localHeader.getValue()));
  }
  
  public HttpEntity getEntity()
  {
    return entity;
  }
  
  public boolean isRepeatable()
  {
    HttpEntity localHttpEntity = entity;
    return (localHttpEntity == null) || (localHttpEntity.isRepeatable()) || (!consumed);
  }
  
  public void setEntity(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity != null) {
      paramHttpEntity = new EntityWrapper(paramHttpEntity);
    } else {
      paramHttpEntity = null;
    }
    entity = paramHttpEntity;
    consumed = false;
  }
  
  class EntityWrapper
    extends HttpEntityWrapper
  {
    EntityWrapper(HttpEntity paramHttpEntity)
    {
      super();
    }
    
    public void consumeContent()
    {
      EntityEnclosingRequestWrapper.access$setConsumed(EntityEnclosingRequestWrapper.this, true);
      super.consumeContent();
    }
    
    public InputStream getContent()
    {
      EntityEnclosingRequestWrapper.access$setConsumed(EntityEnclosingRequestWrapper.this, true);
      return super.getContent();
    }
    
    public void writeTo(OutputStream paramOutputStream)
    {
      EntityEnclosingRequestWrapper.access$setConsumed(EntityEnclosingRequestWrapper.this, true);
      super.writeTo(paramOutputStream);
    }
  }
}
