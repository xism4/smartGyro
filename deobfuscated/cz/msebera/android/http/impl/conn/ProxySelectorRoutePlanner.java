package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.HttpRoutePlanner;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.tsccm.ConnRouteParams;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;

@Deprecated
public class ProxySelectorRoutePlanner
  implements HttpRoutePlanner
{
  protected final SchemeRegistry schemeRegistry;
  
  public ProxySelectorRoutePlanner(SchemeRegistry paramSchemeRegistry)
  {
    Args.notNull(paramSchemeRegistry, "Scheme registry");
    schemeRegistry = paramSchemeRegistry;
  }
  
  public HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    paramHttpContext = ConnRouteParams.getForcedRoute(paramHttpRequest.getParams());
    if (paramHttpContext != null) {
      return paramHttpContext;
    }
    Asserts.notNull(paramHttpHost, "Target host");
    paramHttpContext = ConnRouteParams.getLocalAddress(paramHttpRequest.getParams());
    paramHttpRequest = ConnRouteParams.getDefaultProxy(paramHttpRequest.getParams());
    Object localObject = schemeRegistry;
    try
    {
      localObject = ((SchemeRegistry)localObject).getScheme(paramHttpHost.getSchemeName());
      boolean bool = ((Scheme)localObject).isLayered();
      if (paramHttpRequest == null) {
        return new HttpRoute(paramHttpHost, paramHttpContext, bool);
      }
      return new HttpRoute(paramHttpHost, paramHttpContext, paramHttpRequest, bool);
    }
    catch (IllegalStateException paramHttpHost)
    {
      throw new HttpException(paramHttpHost.getMessage());
    }
  }
}
