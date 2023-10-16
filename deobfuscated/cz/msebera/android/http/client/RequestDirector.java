package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.execchain.HttpContext;

@Deprecated
public abstract interface RequestDirector
{
  public abstract HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext);
}
