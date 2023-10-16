package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.CookieSpecFactory;
import cz.msebera.android.http.cookie.CookieSpecProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.util.Collection;

@Deprecated
public class RFC2109SpecFactory
  implements CookieSpecFactory, CookieSpecProvider
{
  private final CookieSpec cookieSpec;
  
  public RFC2109SpecFactory()
  {
    this(null);
  }
  
  public RFC2109SpecFactory(String[] paramArrayOfString)
  {
    cookieSpec = new NetscapeDraftSpec(paramArrayOfString);
  }
  
  public CookieSpec create(HttpContext paramHttpContext)
  {
    return cookieSpec;
  }
  
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    if (paramHttpParams != null)
    {
      Object localObject = null;
      Collection localCollection = (Collection)paramHttpParams.getParameter("http.protocol.cookie-datepatterns");
      paramHttpParams = localObject;
      if (localCollection != null) {
        paramHttpParams = (String[])localCollection.toArray(new String[localCollection.size()]);
      }
      return new NetscapeDraftSpec(paramHttpParams);
    }
    return new NetscapeDraftSpec();
  }
}
