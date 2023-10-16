package cz.msebera.android.http.conn.routing;

import c.a.a.a.o;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class HttpRoute
  implements RouteInfo, Cloneable
{
  private final RouteInfo.LayerType layered;
  private final InetAddress localAddress;
  private final List<o> proxyChain;
  private final boolean secure;
  private final HttpHost targetHost;
  private final RouteInfo.TunnelType tunnelled;
  
  public HttpRoute(HttpHost paramHttpHost)
  {
    this(paramHttpHost, null, Collections.emptyList(), false, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
  }
  
  public HttpRoute(HttpHost paramHttpHost1, InetAddress paramInetAddress, HttpHost paramHttpHost2, boolean paramBoolean)
  {
    this(paramHttpHost1, paramInetAddress, localList, paramBoolean, paramHttpHost2, localLayerType);
  }
  
  private HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, List paramList, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    Args.notNull(paramHttpHost, "Target host");
    targetHost = normalize(paramHttpHost);
    localAddress = paramInetAddress;
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramHttpHost = new ArrayList(paramList);
    } else {
      paramHttpHost = null;
    }
    proxyChain = paramHttpHost;
    if (paramTunnelType == RouteInfo.TunnelType.TUNNELLED)
    {
      boolean bool;
      if (proxyChain != null) {
        bool = true;
      } else {
        bool = false;
      }
      Args.check(bool, "Proxy required if tunnelled");
    }
    secure = paramBoolean;
    if (paramTunnelType == null) {
      paramTunnelType = RouteInfo.TunnelType.PLAIN;
    }
    tunnelled = paramTunnelType;
    if (paramLayerType == null) {
      paramLayerType = RouteInfo.LayerType.PLAIN;
    }
    layered = paramLayerType;
  }
  
  public HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, boolean paramBoolean)
  {
    this(paramHttpHost, paramInetAddress, Collections.emptyList(), paramBoolean, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
  }
  
  public HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, HttpHost[] paramArrayOfHttpHost, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    this(paramHttpHost, paramInetAddress, paramArrayOfHttpHost, paramBoolean, paramTunnelType, paramLayerType);
  }
  
  private static int getDefaultPort(String paramString)
  {
    if ("http".equalsIgnoreCase(paramString)) {
      return 80;
    }
    if ("https".equalsIgnoreCase(paramString)) {
      return 443;
    }
    return -1;
  }
  
  private static HttpHost normalize(HttpHost paramHttpHost)
  {
    if (paramHttpHost.getPort() >= 0) {
      return paramHttpHost;
    }
    InetAddress localInetAddress = paramHttpHost.getAddress();
    String str = paramHttpHost.getSchemeName();
    if (localInetAddress != null) {
      return new HttpHost(localInetAddress, getDefaultPort(str), str);
    }
    return new HttpHost(paramHttpHost.getHostName(), getDefaultPort(str), str);
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof HttpRoute))
    {
      paramObject = (HttpRoute)paramObject;
      return (secure == secure) && (tunnelled == tunnelled) && (layered == layered) && (LangUtils.equals(targetHost, targetHost)) && (LangUtils.equals(localAddress, localAddress)) && (LangUtils.equals(proxyChain, proxyChain));
    }
    return false;
  }
  
  public final int getHopCount()
  {
    List localList = proxyChain;
    if (localList != null) {
      return 1 + localList.size();
    }
    return 1;
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
      return (HttpHost)proxyChain.get(paramInt);
    }
    return targetHost;
  }
  
  public final InetAddress getLocalAddress()
  {
    return localAddress;
  }
  
  public final HttpHost getProxyHost()
  {
    List localList = proxyChain;
    if ((localList != null) && (!localList.isEmpty())) {
      return (HttpHost)proxyChain.get(0);
    }
    return null;
  }
  
  public final HttpHost getTargetHost()
  {
    return targetHost;
  }
  
  public final int hashCode()
  {
    int i = LangUtils.hashCode(LangUtils.hashCode(17, targetHost), localAddress);
    Object localObject = proxyChain;
    int j = i;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        j = i;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        i = LangUtils.hashCode(i, (HttpHost)((Iterator)localObject).next());
      }
    }
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(j, secure), tunnelled), layered);
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
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(getHopCount() * 30 + 50);
    Object localObject = localAddress;
    if (localObject != null)
    {
      localStringBuilder.append(localObject);
      localStringBuilder.append("->");
    }
    localStringBuilder.append('{');
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
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        localStringBuilder.append((HttpHost)((Iterator)localObject).next());
        localStringBuilder.append("->");
      }
    }
    localStringBuilder.append(targetHost);
    return localStringBuilder.toString();
  }
}
