package cz.msebera.android.http;

public abstract interface HttpRequest
  extends HttpMessage
{
  public abstract RequestLine getRequestLine();
}
