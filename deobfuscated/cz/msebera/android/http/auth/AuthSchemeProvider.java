package cz.msebera.android.http.auth;

import cz.msebera.android.http.execchain.HttpContext;

public abstract interface AuthSchemeProvider
{
  public abstract AuthScheme create(HttpContext paramHttpContext);
}
