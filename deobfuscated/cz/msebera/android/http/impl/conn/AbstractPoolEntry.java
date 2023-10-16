package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.RouteTracker;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpParams;
import java.io.InterruptedIOException;

@Deprecated
public abstract class AbstractPoolEntry
{
  protected final ClientConnectionOperator connOperator;
  protected final OperatedClientConnection connection;
  protected volatile HttpRoute route;
  protected volatile Object state;
  protected volatile RouteTracker tracker;
  
  protected AbstractPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute)
  {
    Args.notNull(paramClientConnectionOperator, "Connection operator");
    connOperator = paramClientConnectionOperator;
    connection = paramClientConnectionOperator.createConnection();
    route = paramHttpRoute;
    tracker = null;
  }
  
  public Object getState()
  {
    return state;
  }
  
  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    Asserts.notNull(tracker, "Route tracker");
    Asserts.check(tracker.isConnected(), "Connection not open");
    Asserts.check(tracker.isTunnelled(), "Protocol layering without a tunnel not supported");
    Asserts.check(tracker.isLayered() ^ true, "Multiple protocol layering not supported");
    HttpHost localHttpHost = tracker.getTargetHost();
    connOperator.updateSecureConnection(connection, localHttpHost, paramHttpContext, paramHttpParams);
    tracker.layerProtocol(connection.isSecure());
  }
  
  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpRoute, "Route");
    Args.notNull(paramHttpParams, "HTTP parameters");
    if (tracker != null) {
      Asserts.check(tracker.isConnected() ^ true, "Connection already open");
    }
    tracker = new RouteTracker(paramHttpRoute);
    HttpHost localHttpHost2 = paramHttpRoute.getProxyHost();
    ClientConnectionOperator localClientConnectionOperator = connOperator;
    OperatedClientConnection localOperatedClientConnection = connection;
    HttpHost localHttpHost1;
    if (localHttpHost2 != null) {
      localHttpHost1 = localHttpHost2;
    } else {
      localHttpHost1 = paramHttpRoute.getTargetHost();
    }
    localClientConnectionOperator.openConnection(localOperatedClientConnection, localHttpHost1, paramHttpRoute.getLocalAddress(), paramHttpContext, paramHttpParams);
    paramHttpRoute = tracker;
    if (paramHttpRoute != null)
    {
      if (localHttpHost2 == null)
      {
        paramHttpRoute.connectProxy(connection.isSecure());
        return;
      }
      paramHttpRoute.connectProxy(localHttpHost2, connection.isSecure());
      return;
    }
    throw new InterruptedIOException("Request aborted");
  }
  
  public void setState(Object paramObject)
  {
    state = paramObject;
  }
  
  protected void shutdownEntry()
  {
    tracker = null;
    state = null;
  }
  
  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    Asserts.notNull(tracker, "Route tracker");
    Asserts.check(tracker.isConnected(), "Connection not open");
    Asserts.check(tracker.isTunnelled() ^ true, "Connection is already tunnelled");
    connection.update(null, tracker.getTargetHost(), paramBoolean, paramHttpParams);
    tracker.tunnelTarget(paramBoolean);
  }
}
