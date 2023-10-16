package cz.msebera.android.http;

public abstract interface HttpEntityEnclosingRequest
  extends HttpRequest
{
  public abstract boolean expectContinue();
  
  public abstract HttpEntity getEntity();
}
