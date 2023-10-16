package cz.msebera.android.http.conn.routing;

public abstract interface HttpRouteDirector
{
  public abstract int nextStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2);
}
