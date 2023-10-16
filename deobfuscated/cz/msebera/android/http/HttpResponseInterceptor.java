package cz.msebera.android.http;

import cz.msebera.android.http.execchain.HttpContext;

public abstract interface HttpResponseInterceptor
{
  public abstract void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}
