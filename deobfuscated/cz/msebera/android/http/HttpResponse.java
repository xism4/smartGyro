package cz.msebera.android.http;

public abstract interface HttpResponse
  extends HttpMessage
{
  public abstract HttpEntity getEntity();
  
  public abstract StatusLine getStatusLine();
  
  public abstract void setEntity(HttpEntity paramHttpEntity);
}
