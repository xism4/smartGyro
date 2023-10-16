package cz.msebera.android.http.execchain;

import c.a.a.a.j;
import c.a.a.a.o;
import c.a.a.a.r;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.mime.Args;

public class HttpCoreContext
  implements HttpContext
{
  private final HttpContext context;
  
  public HttpCoreContext()
  {
    context = new BasicHttpContext();
  }
  
  public HttpCoreContext(HttpContext paramHttpContext)
  {
    context = paramHttpContext;
  }
  
  public static HttpCoreContext adapt(HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpContext, "HTTP context");
    if ((paramHttpContext instanceof HttpCoreContext)) {
      return (HttpCoreContext)paramHttpContext;
    }
    return new HttpCoreContext(paramHttpContext);
  }
  
  public Object getAttribute(String paramString)
  {
    return context.getAttribute(paramString);
  }
  
  public Object getAttribute(String paramString, Class paramClass)
  {
    Args.notNull(paramClass, "Attribute class");
    paramString = getAttribute(paramString);
    if (paramString == null) {
      return null;
    }
    return paramClass.cast(paramString);
  }
  
  public HttpConnection getConnection()
  {
    return (HttpConnection)getAttribute("http.connection", j.class);
  }
  
  public HttpRequest getRequest()
  {
    return (HttpRequest)getAttribute("http.request", r.class);
  }
  
  public HttpHost getTargetHost()
  {
    return (HttpHost)getAttribute("http.target_host", o.class);
  }
  
  public boolean isRequestSent()
  {
    Boolean localBoolean = (Boolean)getAttribute("http.request_sent", Boolean.class);
    return (localBoolean != null) && (localBoolean.booleanValue());
  }
  
  public void setAttribute(String paramString, Object paramObject)
  {
    context.setAttribute(paramString, paramObject);
  }
}
