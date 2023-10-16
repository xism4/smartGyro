package cz.msebera.android.http.auth;

import cz.msebera.android.http.util.HttpParams;

@Deprecated
public abstract interface AuthSchemeFactory
{
  public abstract AuthScheme newInstance(HttpParams paramHttpParams);
}
