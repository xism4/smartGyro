package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.util.Locale;

@Deprecated
public final class Scheme
{
  private final int defaultPort;
  private final boolean layered;
  private final String name;
  private final SchemeSocketFactory socketFactory;
  private String stringRep;
  
  public Scheme(String paramString, int paramInt, SchemeSocketFactory paramSchemeSocketFactory)
  {
    Args.notNull(paramString, "Scheme name");
    boolean bool;
    if ((paramInt > 0) && (paramInt <= 65535)) {
      bool = true;
    } else {
      bool = false;
    }
    Args.check(bool, "Port is invalid");
    Args.notNull(paramSchemeSocketFactory, "Socket factory");
    name = paramString.toLowerCase(Locale.ENGLISH);
    defaultPort = paramInt;
    if ((paramSchemeSocketFactory instanceof SchemeLayeredSocketFactory)) {}
    for (layered = true;; layered = false)
    {
      socketFactory = paramSchemeSocketFactory;
      return;
      if ((paramSchemeSocketFactory instanceof LayeredSchemeSocketFactory))
      {
        layered = true;
        socketFactory = new SchemeLayeredSocketFactoryAdaptor2((LayeredSchemeSocketFactory)paramSchemeSocketFactory);
        return;
      }
    }
  }
  
  public Scheme(String paramString, SocketFactory paramSocketFactory, int paramInt)
  {
    Args.notNull(paramString, "Scheme name");
    Args.notNull(paramSocketFactory, "Socket factory");
    boolean bool;
    if ((paramInt > 0) && (paramInt <= 65535)) {
      bool = true;
    } else {
      bool = false;
    }
    Args.check(bool, "Port is invalid");
    name = paramString.toLowerCase(Locale.ENGLISH);
    if ((paramSocketFactory instanceof LayeredSocketFactory))
    {
      socketFactory = new SchemeLayeredSocketFactoryAdaptor((LayeredSocketFactory)paramSocketFactory);
      layered = true;
    }
    else
    {
      socketFactory = new SchemeSocketFactoryAdaptor(paramSocketFactory);
      layered = false;
    }
    defaultPort = paramInt;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof Scheme))
    {
      paramObject = (Scheme)paramObject;
      return (name.equals(name)) && (defaultPort == defaultPort) && (layered == layered);
    }
    return false;
  }
  
  public final int getDefaultPort()
  {
    return defaultPort;
  }
  
  public final String getName()
  {
    return name;
  }
  
  public final SchemeSocketFactory getSchemeSocketFactory()
  {
    return socketFactory;
  }
  
  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, defaultPort), name), layered);
  }
  
  public final boolean isLayered()
  {
    return layered;
  }
  
  public final int resolvePort(int paramInt)
  {
    int i = paramInt;
    if (paramInt <= 0) {
      i = defaultPort;
    }
    return i;
  }
  
  public final String toString()
  {
    if (stringRep == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(name);
      localStringBuilder.append(':');
      localStringBuilder.append(Integer.toString(defaultPort));
      stringRep = localStringBuilder.toString();
    }
    return stringRep;
  }
}
