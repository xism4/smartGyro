package cz.msebera.android.http;

import cz.msebera.android.http.execchain.HttpContext;

public abstract interface HttpRequestInterceptor
{
  public abstract void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext);
}
