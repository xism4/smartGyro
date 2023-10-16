package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.execchain.HttpContext;
import java.net.URI;

@Deprecated
public abstract interface RedirectHandler
{
  public abstract URI getLocationURI(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
  
  public abstract boolean isRedirectRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}
