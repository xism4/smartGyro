package cz.msebera.android.http.auth;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.execchain.HttpContext;

public abstract interface ContextAwareAuthScheme
  extends AuthScheme
{
  public abstract Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext);
}
