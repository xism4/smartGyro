package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.client.methods.RequestConfig;
import java.util.Collection;

public class TargetAuthenticationStrategy
  extends AuthenticationStrategyImpl
{
  public static final TargetAuthenticationStrategy INSTANCE = new TargetAuthenticationStrategy();
  
  public TargetAuthenticationStrategy()
  {
    super(407, "Proxy-Authenticate");
  }
  
  Collection getPreferredAuthSchemes(RequestConfig paramRequestConfig)
  {
    return paramRequestConfig.getTargetPreferredAuthSchemes();
  }
}
