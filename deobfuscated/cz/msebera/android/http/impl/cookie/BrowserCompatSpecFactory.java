package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.CookieSpecFactory;
import cz.msebera.android.http.cookie.CookieSpecProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.util.Collection;

@Deprecated
public class BrowserCompatSpecFactory
  implements CookieSpecFactory, CookieSpecProvider
{
  private final CookieSpec cookieSpec;
  private final MathArrays.OrderDirection securityLevel;
  
  public BrowserCompatSpecFactory()
  {
    this(null, MathArrays.OrderDirection.INCREASING);
  }
  
  public BrowserCompatSpecFactory(String[] paramArrayOfString, MathArrays.OrderDirection paramOrderDirection)
  {
    securityLevel = paramOrderDirection;
    cookieSpec = new BrowserCompatSpec(paramArrayOfString, paramOrderDirection);
  }
  
  public CookieSpec create(HttpContext paramHttpContext)
  {
    return cookieSpec;
  }
  
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    Object localObject = null;
    if (paramHttpParams != null)
    {
      Collection localCollection = (Collection)paramHttpParams.getParameter("http.protocol.cookie-datepatterns");
      paramHttpParams = localObject;
      if (localCollection != null) {
        paramHttpParams = (String[])localCollection.toArray(new String[localCollection.size()]);
      }
      return new BrowserCompatSpec(paramHttpParams, securityLevel);
    }
    return new BrowserCompatSpec(null, securityLevel);
  }
}
