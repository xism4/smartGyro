package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.execchain.HttpContext;

public abstract interface ConnectionKeepAliveStrategy
{
  public abstract long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}
