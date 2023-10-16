package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.client.methods.RequestConfig;

public abstract interface Configurable
{
  public abstract RequestConfig getConfig();
}
