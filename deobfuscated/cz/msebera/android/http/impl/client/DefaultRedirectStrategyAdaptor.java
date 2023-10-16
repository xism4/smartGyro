package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.client.RedirectHandler;
import cz.msebera.android.http.client.RedirectStrategy;
import cz.msebera.android.http.client.auth.HttpDelete;
import cz.msebera.android.http.client.auth.HttpHead;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.execchain.HttpContext;

@Deprecated
class DefaultRedirectStrategyAdaptor
  implements RedirectStrategy
{
  private final RedirectHandler handler;
  
  public DefaultRedirectStrategyAdaptor(RedirectHandler paramRedirectHandler)
  {
    handler = paramRedirectHandler;
  }
  
  public RedirectHandler getHandler()
  {
    return handler;
  }
  
  public HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    paramHttpResponse = handler.getLocationURI(paramHttpResponse, paramHttpContext);
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("HEAD")) {
      return new HttpDelete(paramHttpResponse);
    }
    return new HttpHead(paramHttpResponse);
  }
  
  public boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    return handler.isRedirectRequested(paramHttpResponse, paramHttpContext);
  }
}
