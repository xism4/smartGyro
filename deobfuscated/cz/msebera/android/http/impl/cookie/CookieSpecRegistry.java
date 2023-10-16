package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.CookieSpecFactory;
import cz.msebera.android.http.cookie.CookieSpecProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.util.Collection;

@Deprecated
public class CookieSpecRegistry
  implements CookieSpecFactory, CookieSpecProvider
{
  private final CookieSpec cookieSpec;
  
  public CookieSpecRegistry()
  {
    this(null, false);
  }
  
  public CookieSpecRegistry(String[] paramArrayOfString, boolean paramBoolean)
  {
    cookieSpec = new RFC2109Spec(paramArrayOfString, paramBoolean);
  }
  
  public CookieSpec create(HttpContext paramHttpContext)
  {
    return cookieSpec;
  }
  
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    if (paramHttpParams != null)
    {
      String[] arrayOfString = null;
      Collection localCollection = (Collection)paramHttpParams.getParameter("http.protocol.cookie-datepatterns");
      if (localCollection != null) {
        arrayOfString = (String[])localCollection.toArray(new String[localCollection.size()]);
      }
      return new RFC2109Spec(arrayOfString, paramHttpParams.getBooleanParameter("http.protocol.single-cookie-header", false));
    }
    return new RFC2109Spec();
  }
}
