package cz.msebera.android.http.client;

public class HttpResponseException
  extends ClientProtocolException
{
  private final int statusCode;
  
  public HttpResponseException(int paramInt, String paramString)
  {
    super(paramString);
    statusCode = paramInt;
  }
}
