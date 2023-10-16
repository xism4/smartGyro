package cz.msebera.android.http.cookie;

import cz.msebera.android.http.execchain.HttpContext;

public abstract interface CookieSpecProvider
{
  public abstract CookieSpec create(HttpContext paramHttpContext);
}
