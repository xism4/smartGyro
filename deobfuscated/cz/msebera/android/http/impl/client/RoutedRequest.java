package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.conn.routing.HttpRoute;

@Deprecated
public class RoutedRequest
{
  protected final RequestWrapper request;
  protected final HttpRoute route;
  
  public RoutedRequest(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
  {
    request = paramRequestWrapper;
    route = paramHttpRoute;
  }
  
  public final RequestWrapper getRequest()
  {
    return request;
  }
  
  public final HttpRoute getRoute()
  {
    return route;
  }
}
