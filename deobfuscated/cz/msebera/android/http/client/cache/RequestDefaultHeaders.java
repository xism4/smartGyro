package cz.msebera.android.http.client.cache;

import c.a.a.a.e;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.util.Collection;
import java.util.Iterator;

public class RequestDefaultHeaders
  implements HttpRequestInterceptor
{
  private final Collection<? extends e> defaultHeaders;
  
  public RequestDefaultHeaders()
  {
    this(null);
  }
  
  public RequestDefaultHeaders(Collection paramCollection)
  {
    defaultHeaders = paramCollection;
  }
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
      return;
    }
    Collection localCollection = (Collection)paramHttpRequest.getParams().getParameter("http.default-headers");
    paramHttpContext = localCollection;
    if (localCollection == null) {
      paramHttpContext = defaultHeaders;
    }
    if (paramHttpContext != null)
    {
      paramHttpContext = paramHttpContext.iterator();
      while (paramHttpContext.hasNext()) {
        paramHttpRequest.addHeader((Header)paramHttpContext.next());
      }
    }
  }
}
