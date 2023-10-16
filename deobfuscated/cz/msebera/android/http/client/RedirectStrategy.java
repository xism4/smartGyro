package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.execchain.HttpContext;

public abstract interface RedirectStrategy
{
  public abstract HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext);
  
  public abstract boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}
