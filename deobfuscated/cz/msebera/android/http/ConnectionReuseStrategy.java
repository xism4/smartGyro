package cz.msebera.android.http;

import cz.msebera.android.http.execchain.HttpContext;

public abstract interface ConnectionReuseStrategy
{
  public abstract boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}
