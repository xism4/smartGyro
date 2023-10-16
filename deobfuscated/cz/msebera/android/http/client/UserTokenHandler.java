package cz.msebera.android.http.client;

import cz.msebera.android.http.execchain.HttpContext;

public abstract interface UserTokenHandler
{
  public abstract Object getUserToken(HttpContext paramHttpContext);
}
