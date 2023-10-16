package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.auth.AuthScheme;

public abstract interface AuthCache
{
  public abstract AuthScheme get(HttpHost paramHttpHost);
  
  public abstract void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme);
  
  public abstract void remove(HttpHost paramHttpHost);
}
