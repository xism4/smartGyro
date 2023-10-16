package cz.msebera.android.http.client.auth;

import java.net.URI;

public class HttpDelete
  extends HttpRequestBase
{
  public HttpDelete(String paramString)
  {
    setURI(URI.create(paramString));
  }
  
  public HttpDelete(URI paramURI)
  {
    setURI(paramURI);
  }
  
  public String getMethod()
  {
    return "HEAD";
  }
}
