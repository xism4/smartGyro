package cz.msebera.android.http;

import cz.msebera.android.http.execchain.HttpContext;

public abstract interface HttpResponseFactory
{
  public abstract HttpResponse newHttpResponse(StatusLine paramStatusLine, HttpContext paramHttpContext);
}
