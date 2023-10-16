package cz.msebera.android.http.conn.tsccm;

import cz.msebera.android.http.conn.routing.HttpRoute;

@Deprecated
public abstract interface ConnPerRoute
{
  public abstract int getMaxForRoute(HttpRoute paramHttpRoute);
}
