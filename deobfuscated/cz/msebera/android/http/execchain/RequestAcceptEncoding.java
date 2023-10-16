package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;

public class RequestAcceptEncoding
  implements HttpRequestInterceptor
{
  private final String originServer;
  
  public RequestAcceptEncoding()
  {
    this(null);
  }
  
  public RequestAcceptEncoding(String paramString)
  {
    originServer = paramString;
  }
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    if (!paramHttpRequest.containsHeader("User-Agent"))
    {
      paramHttpContext = null;
      Object localObject = paramHttpRequest.getParams();
      if (localObject != null) {
        paramHttpContext = (String)((HttpParams)localObject).getParameter("http.useragent");
      }
      localObject = paramHttpContext;
      if (paramHttpContext == null) {
        localObject = originServer;
      }
      if (localObject != null) {
        paramHttpRequest.addHeader("User-Agent", (String)localObject);
      }
    }
  }
}
