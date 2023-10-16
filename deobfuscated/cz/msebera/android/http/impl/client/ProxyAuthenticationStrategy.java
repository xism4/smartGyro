package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.client.methods.RequestConfig;
import java.util.Collection;

public class ProxyAuthenticationStrategy
  extends AuthenticationStrategyImpl
{
  public static final ProxyAuthenticationStrategy INSTANCE = new ProxyAuthenticationStrategy();
  
  public ProxyAuthenticationStrategy()
  {
    super(401, "WWW-Authenticate");
  }
  
  Collection getPreferredAuthSchemes(RequestConfig paramRequestConfig)
  {
    return paramRequestConfig.getProxyPreferredAuthSchemes();
  }
}
