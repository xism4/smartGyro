package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpResponse;

@Deprecated
public class TunnelRefusedException
  extends HttpException
{
  private final HttpResponse response;
  
  public TunnelRefusedException(String paramString, HttpResponse paramHttpResponse)
  {
    super(paramString);
    response = paramHttpResponse;
  }
  
  public HttpResponse getResponse()
  {
    return response;
  }
}
