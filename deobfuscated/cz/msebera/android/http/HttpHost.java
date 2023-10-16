package cz.msebera.android.http;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;

public final class HttpHost
  implements Cloneable, Serializable
{
  protected final InetAddress address;
  protected final String hostname;
  protected final String lcHostname;
  protected final int port;
  protected final String schemeName;
  
  public HttpHost(String paramString, int paramInt)
  {
    this(paramString, paramInt, null);
  }
  
  public HttpHost(String paramString1, int paramInt, String paramString2)
  {
    Args.containsNoBlanks(paramString1, "Host name");
    hostname = ((String)paramString1);
    lcHostname = paramString1.toLowerCase(Locale.ROOT);
    if (paramString2 != null) {
      paramString1 = paramString2.toLowerCase(Locale.ROOT);
    } else {
      paramString1 = "http";
    }
    schemeName = paramString1;
    port = paramInt;
    address = null;
  }
  
  public HttpHost(InetAddress paramInetAddress, int paramInt, String paramString)
  {
    this((InetAddress)paramInetAddress, paramInetAddress.getHostName(), paramInt, paramString);
  }
  
  public HttpHost(InetAddress paramInetAddress, String paramString1, int paramInt, String paramString2)
  {
    Args.notNull(paramInetAddress, "Inet address");
    address = ((InetAddress)paramInetAddress);
    Args.notNull(paramString1, "Hostname");
    hostname = ((String)paramString1);
    lcHostname = hostname.toLowerCase(Locale.ROOT);
    if (paramString2 != null) {
      paramInetAddress = paramString2.toLowerCase(Locale.ROOT);
    } else {
      paramInetAddress = "http";
    }
    schemeName = paramInetAddress;
    port = paramInt;
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof HttpHost))
    {
      paramObject = (HttpHost)paramObject;
      if ((lcHostname.equals(lcHostname)) && (port == port) && (schemeName.equals(schemeName)))
      {
        InetAddress localInetAddress = address;
        if (localInetAddress == null)
        {
          if (address == null) {
            return true;
          }
        }
        else if (localInetAddress.equals(address)) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  public InetAddress getAddress()
  {
    return address;
  }
  
  public String getHostName()
  {
    return hostname;
  }
  
  public int getPort()
  {
    return port;
  }
  
  public String getSchemeName()
  {
    return schemeName;
  }
  
  public int hashCode()
  {
    int j = LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, lcHostname), port), schemeName);
    InetAddress localInetAddress = address;
    int i = j;
    if (localInetAddress != null) {
      i = LangUtils.hashCode(j, localInetAddress);
    }
    return i;
  }
  
  public String toHostString()
  {
    if (port != -1)
    {
      StringBuilder localStringBuilder = new StringBuilder(hostname.length() + 6);
      localStringBuilder.append(hostname);
      localStringBuilder.append(":");
      localStringBuilder.append(Integer.toString(port));
      return localStringBuilder.toString();
    }
    return hostname;
  }
  
  public String toString()
  {
    return toURI();
  }
  
  public String toURI()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(schemeName);
    localStringBuilder.append("://");
    localStringBuilder.append(hostname);
    if (port != -1)
    {
      localStringBuilder.append(':');
      localStringBuilder.append(Integer.toString(port));
    }
    return localStringBuilder.toString();
  }
}
