package cz.msebera.android.http.conn.routing;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.execchain.HttpContext;

public abstract interface HttpRoutePlanner
{
  public abstract HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext);
}
