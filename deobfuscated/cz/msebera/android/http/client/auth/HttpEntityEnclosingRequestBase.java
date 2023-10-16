package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.client.ssl.CloneUtils;
import cz.msebera.android.http.message.AbstractHttpMessage;

public abstract class HttpEntityEnclosingRequestBase
  extends HttpRequestBase
  implements HttpEntityEnclosingRequest
{
  private HttpEntity entity;
  
  public HttpEntityEnclosingRequestBase() {}
  
  public Object clone()
  {
    HttpEntityEnclosingRequestBase localHttpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase)super.clone();
    HttpEntity localHttpEntity = entity;
    if (localHttpEntity != null) {
      entity = ((HttpEntity)CloneUtils.cloneObject(localHttpEntity));
    }
    return localHttpEntityEnclosingRequestBase;
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
  
  public void setEntity(HttpEntity paramHttpEntity)
  {
    entity = paramHttpEntity;
  }
}
