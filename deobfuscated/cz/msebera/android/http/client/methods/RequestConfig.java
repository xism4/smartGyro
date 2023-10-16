package cz.msebera.android.http.client.methods;

import cz.msebera.android.http.HttpHost;
import java.net.InetAddress;
import java.util.Collection;

public class RequestConfig
  implements Cloneable
{
  public static final RequestConfig DEFAULT = new Builder().build();
  private final int N;
  private final boolean authenticationEnabled;
  private final boolean c;
  private final InetAddress circularRedirectsAllowed;
  private final int connectTimeout;
  private final boolean connectionRequestTimeout;
  private final int cookieSpec;
  private final boolean expectContinueEnabled;
  private final Collection<String> localAddress;
  private final String maxRedirects;
  private final Collection<String> proxyPreferredAuthSchemes;
  private final boolean redirectsEnabled;
  private final HttpHost relativeRedirectsAllowed;
  private final boolean socketTimeout;
  private final int staleConnectionCheckEnabled;
  private final boolean targetPreferredAuthSchemes;
  
  RequestConfig(boolean paramBoolean1, HttpHost paramHttpHost, InetAddress paramInetAddress, boolean paramBoolean2, String paramString, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, int paramInt1, boolean paramBoolean6, Collection paramCollection1, Collection paramCollection2, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean7)
  {
    redirectsEnabled = paramBoolean1;
    relativeRedirectsAllowed = paramHttpHost;
    circularRedirectsAllowed = paramInetAddress;
    socketTimeout = paramBoolean2;
    maxRedirects = paramString;
    authenticationEnabled = paramBoolean3;
    targetPreferredAuthSchemes = paramBoolean4;
    connectionRequestTimeout = paramBoolean5;
    connectTimeout = paramInt1;
    expectContinueEnabled = paramBoolean6;
    proxyPreferredAuthSchemes = paramCollection1;
    localAddress = paramCollection2;
    staleConnectionCheckEnabled = paramInt2;
    cookieSpec = paramInt3;
    N = paramInt4;
    c = paramBoolean7;
  }
  
  public static Builder custom()
  {
    return new Builder();
  }
  
  protected RequestConfig clone()
  {
    return (RequestConfig)super.clone();
  }
  
  public boolean getConnectionRequestTimeout()
  {
    return connectionRequestTimeout;
  }
  
  public String getMaxRedirects()
  {
    return maxRedirects;
  }
  
  public Collection getProxyPreferredAuthSchemes()
  {
    return proxyPreferredAuthSchemes;
  }
  
  public Collection getTargetPreferredAuthSchemes()
  {
    return localAddress;
  }
  
  public boolean isCircularRedirectsAllowed()
  {
    return targetPreferredAuthSchemes;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    localStringBuilder.append("expectContinueEnabled=");
    localStringBuilder.append(redirectsEnabled);
    localStringBuilder.append(", proxy=");
    localStringBuilder.append(relativeRedirectsAllowed);
    localStringBuilder.append(", localAddress=");
    localStringBuilder.append(circularRedirectsAllowed);
    localStringBuilder.append(", cookieSpec=");
    localStringBuilder.append(maxRedirects);
    localStringBuilder.append(", redirectsEnabled=");
    localStringBuilder.append(authenticationEnabled);
    localStringBuilder.append(", relativeRedirectsAllowed=");
    localStringBuilder.append(targetPreferredAuthSchemes);
    localStringBuilder.append(", maxRedirects=");
    localStringBuilder.append(connectTimeout);
    localStringBuilder.append(", circularRedirectsAllowed=");
    localStringBuilder.append(connectionRequestTimeout);
    localStringBuilder.append(", authenticationEnabled=");
    localStringBuilder.append(expectContinueEnabled);
    localStringBuilder.append(", targetPreferredAuthSchemes=");
    localStringBuilder.append(proxyPreferredAuthSchemes);
    localStringBuilder.append(", proxyPreferredAuthSchemes=");
    localStringBuilder.append(localAddress);
    localStringBuilder.append(", connectionRequestTimeout=");
    localStringBuilder.append(staleConnectionCheckEnabled);
    localStringBuilder.append(", connectTimeout=");
    localStringBuilder.append(cookieSpec);
    localStringBuilder.append(", socketTimeout=");
    localStringBuilder.append(N);
    localStringBuilder.append(", decompressionEnabled=");
    localStringBuilder.append(c);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public class Builder
  {
    private boolean authenticationEnabled = true;
    private boolean circularRedirectsAllowed;
    private int connectTimeout = -1;
    private int connectionRequestTimeout = -1;
    private String cookieSpec;
    private boolean expectContinueEnabled;
    private InetAddress localAddress;
    private int maxRedirects = 50;
    private HttpHost proxy;
    private Collection<String> proxyPreferredAuthSchemes;
    private boolean redirectsEnabled = true;
    private boolean relativeRedirectsAllowed = true;
    private int socketTimeout = -1;
    private boolean staleConnectionCheckEnabled = false;
    private Collection<String> targetPreferredAuthSchemes;
    private boolean textAlignment = true;
    
    Builder() {}
    
    public RequestConfig build()
    {
      return new RequestConfig(expectContinueEnabled, proxy, localAddress, staleConnectionCheckEnabled, cookieSpec, redirectsEnabled, relativeRedirectsAllowed, circularRedirectsAllowed, maxRedirects, authenticationEnabled, targetPreferredAuthSchemes, proxyPreferredAuthSchemes, connectionRequestTimeout, connectTimeout, socketTimeout, textAlignment);
    }
    
    public Builder setAuthenticationEnabled(boolean paramBoolean)
    {
      authenticationEnabled = paramBoolean;
      return this;
    }
    
    public Builder setCircularRedirectsAllowed(boolean paramBoolean)
    {
      circularRedirectsAllowed = paramBoolean;
      return this;
    }
    
    public Builder setConnectTimeout(int paramInt)
    {
      connectTimeout = paramInt;
      return this;
    }
    
    public Builder setConnectionRequestTimeout(int paramInt)
    {
      connectionRequestTimeout = paramInt;
      return this;
    }
    
    public Builder setCookieSpec(String paramString)
    {
      cookieSpec = paramString;
      return this;
    }
    
    public Builder setExpectContinueEnabled(boolean paramBoolean)
    {
      expectContinueEnabled = paramBoolean;
      return this;
    }
    
    public Builder setLocalAddress(InetAddress paramInetAddress)
    {
      localAddress = paramInetAddress;
      return this;
    }
    
    public Builder setMaxRedirects(int paramInt)
    {
      maxRedirects = paramInt;
      return this;
    }
    
    public Builder setProxy(HttpHost paramHttpHost)
    {
      proxy = paramHttpHost;
      return this;
    }
    
    public Builder setProxyPreferredAuthSchemes(Collection paramCollection)
    {
      proxyPreferredAuthSchemes = paramCollection;
      return this;
    }
    
    public Builder setRedirectsEnabled(boolean paramBoolean)
    {
      redirectsEnabled = paramBoolean;
      return this;
    }
    
    public Builder setRelativeRedirectsAllowed(boolean paramBoolean)
    {
      relativeRedirectsAllowed = paramBoolean;
      return this;
    }
    
    public Builder setSocketTimeout(int paramInt)
    {
      socketTimeout = paramInt;
      return this;
    }
    
    public Builder setStaleConnectionCheckEnabled(boolean paramBoolean)
    {
      staleConnectionCheckEnabled = paramBoolean;
      return this;
    }
    
    public Builder setTargetPreferredAuthSchemes(Collection paramCollection)
    {
      targetPreferredAuthSchemes = paramCollection;
      return this;
    }
  }
}
