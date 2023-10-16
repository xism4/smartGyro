package cz.msebera.android.http.cookie;

import cz.msebera.android.http.util.HttpParams;

@Deprecated
public abstract interface CookieSpecFactory
{
  public abstract CookieSpec newInstance(HttpParams paramHttpParams);
}
