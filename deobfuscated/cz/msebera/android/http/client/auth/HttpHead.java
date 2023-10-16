package cz.msebera.android.http.client.auth;

import java.net.URI;

public class HttpHead
  extends HttpRequestBase
{
  public HttpHead(URI paramURI)
  {
    setURI(paramURI);
  }
  
  public String getMethod()
  {
    return "GET";
  }
}
