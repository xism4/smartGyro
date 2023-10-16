package cz.msebera.android.http.conn.routing;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.mime.LangUtils;
import java.net.InetAddress;

public final class RouteTracker
  implements RouteInfo, Cloneable
{
  private boolean connected;
  private RouteInfo.LayerType layered;
  private final InetAddress localAddress;
  private HttpHost[] proxyChain;
  private boolean secure;
  private final HttpHost targetHost;
  private RouteInfo.TunnelType tunnelled;
  
  public RouteTracker(HttpHost paramHttpHost, InetAddress paramInetAddress)
  {
    Args.notNull(paramHttpHost, "Target host");
    targetHost = paramHttpHost;
    localAddress = paramInetAddress;
    tunnelled = RouteInfo.TunnelType.PLAIN;
    layered = RouteInfo.LayerType.PLAIN;
  }
  
  public RouteTracker(HttpRoute paramHttpRoute)
  {
    this(paramHttpRoute.getTargetHost(), paramHttpRoute.getLocalAddress());
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public final void connectProxy(HttpHost paramHttpHost, boolean paramBoolean)
  {
    Args.notNull(paramHttpHost, "Proxy host");
    Asserts.check(connected ^ true, "Already connected");
    connected = true;
    proxyChain = new HttpHost[] { paramHttpHost };
    secure = paramBoolean;
  }
  
  public final void connectProxy(boolean paramBoolean)
  {
    Asserts.check(connected ^ true, "Already connected");
    connected = true;
    secure = paramBoolean;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof RouteTracker)) {
      return false;
    }
    paramObject = (RouteTracker)paramObject;
    return (connected == connected) && (secure == secure) && (tunnelled == tunnelled) && (layered == layered) && (LangUtils.equals(targetHost, targetHost)) && (LangUtils.equals(localAddress, localAddress)) && (LangUtils.equals(proxyChain, proxyChain));
  }
  
  public final int getHopCount()
  {
    if (connected)
    {
      HttpHost[] arrayOfHttpHost = proxyChain;
      if (arrayOfHttpHost == null) {
        return 1;
      }
      return 1 + arrayOfHttpHost.length;
    }
    return 0;
  }
  
  public final HttpHost getHopTarget(int paramInt)
  {
    Args.notNegative(paramInt, "Hop index");
    int i = getHopCount();
    boolean bool;
    if (paramInt < i) {
      bool = true;
    } else {
      bool = false;
    }
    Args.check(bool, "Hop index exceeds tracked route length");
    if (paramInt < i - 1) {
      return proxyChain[paramInt];
    }
    return targetHost;
  }
  
  public final InetAddress getLocalAddress()
  {
    return localAddress;
  }
  
  public final HttpHost getProxyHost()
  {
    HttpHost[] arrayOfHttpHost = proxyChain;
    if (arrayOfHttpHost == null) {
      return null;
    }
    return arrayOfHttpHost[0];
  }
  
  public final HttpHost getTargetHost()
  {
    return targetHost;
  }
  
  public final int hashCode()
  {
    int i = LangUtils.hashCode(LangUtils.hashCode(17, targetHost), localAddress);
    HttpHost[] arrayOfHttpHost = proxyChain;
    int k = i;
    if (arrayOfHttpHost != null)
    {
      int m = arrayOfHttpHost.length;
      int j = 0;
      for (;;)
      {
        k = i;
        if (j >= m) {
          break;
        }
        i = LangUtils.hashCode(i, arrayOfHttpHost[j]);
        j += 1;
      }
    }
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(k, connected), secure), tunnelled), layered);
  }
  
  public final boolean isConnected()
  {
    return connected;
  }
  
  public final boolean isLayered()
  {
    return layered == RouteInfo.LayerType.LAYERED;
  }
  
  public final boolean isSecure()
  {
    return secure;
  }
  
  public final boolean isTunnelled()
  {
    return tunnelled == RouteInfo.TunnelType.TUNNELLED;
  }
  
  public final void layerProtocol(boolean paramBoolean)
  {
    Asserts.check(connected, "No layered protocol unless connected");
    layered = RouteInfo.LayerType.LAYERED;
    secure = paramBoolean;
  }
  
  public void reset()
  {
    connected = false;
    proxyChain = null;
    tunnelled = RouteInfo.TunnelType.PLAIN;
    layered = RouteInfo.LayerType.PLAIN;
    secure = false;
  }
  
  public final HttpRoute toRoute()
  {
    if (!connected) {
      return null;
    }
    return new HttpRoute(targetHost, localAddress, proxyChain, secure, tunnelled, layered);
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(getHopCount() * 30 + 50);
    localStringBuilder.append("RouteTracker[");
    Object localObject = localAddress;
    if (localObject != null)
    {
      localStringBuilder.append(localObject);
      localStringBuilder.append("->");
    }
    localStringBuilder.append('{');
    if (connected) {
      localStringBuilder.append('c');
    }
    if (tunnelled == RouteInfo.TunnelType.TUNNELLED) {
      localStringBuilder.append('t');
    }
    if (layered == RouteInfo.LayerType.LAYERED) {
      localStringBuilder.append('l');
    }
    if (secure) {
      localStringBuilder.append('s');
    }
    localStringBuilder.append("}->");
    localObject = proxyChain;
    if (localObject != null)
    {
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(localObject[i]);
        localStringBuilder.append("->");
        i += 1;
      }
    }
    localStringBuilder.append(targetHost);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public final void tunnelTarget(boolean paramBoolean)
  {
    Asserts.check(connected, "No tunnel unless connected");
    Asserts.notNull(proxyChain, "No tunnel without proxy");
    tunnelled = RouteInfo.TunnelType.TUNNELLED;
    secure = paramBoolean;
  }
}
