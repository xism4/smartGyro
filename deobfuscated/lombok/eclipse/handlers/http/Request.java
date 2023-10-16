package lombok.eclipse.handlers.http;

import cz.msebera.android.http.client.auth.HttpEntityEnclosingRequestBase;
import cz.msebera.android.http.client.auth.HttpRequestBase;
import java.net.URI;

public final class Request
  extends HttpEntityEnclosingRequestBase
{
  public Request(String paramString)
  {
    setURI(URI.create(paramString));
  }
  
  public String getMethod()
  {
    return "GET";
  }
}
