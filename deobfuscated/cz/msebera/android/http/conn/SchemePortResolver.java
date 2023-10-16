package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpHost;

public abstract interface SchemePortResolver
{
  public abstract int resolve(HttpHost paramHttpHost);
}
