package cz.msebera.android.http.client;

import cz.msebera.android.http.conn.routing.HttpRoute;

public abstract interface BackoffManager
{
  public abstract void backOff(HttpRoute paramHttpRoute);
  
  public abstract void probe(HttpRoute paramHttpRoute);
}
