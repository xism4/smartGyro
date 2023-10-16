package cz.msebera.android.http.cookie;

import c.a.a.a.d.a;
import c.a.a.a.f.j;
import c.a.a.a.f.k;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class CookieSpecRegistry
  implements a<k>
{
  private final ConcurrentHashMap<String, j> registeredSpecs = new ConcurrentHashMap();
  
  public CookieSpecRegistry() {}
  
  public CookieSpec getCookieSpec(String paramString, HttpParams paramHttpParams)
  {
    Args.notNull(paramString, "Name");
    CookieSpecFactory localCookieSpecFactory = (CookieSpecFactory)registeredSpecs.get(paramString.toLowerCase(Locale.ENGLISH));
    if (localCookieSpecFactory != null) {
      return localCookieSpecFactory.newInstance(paramHttpParams);
    }
    paramHttpParams = new StringBuilder();
    paramHttpParams.append("Unsupported cookie spec: ");
    paramHttpParams.append(paramString);
    throw new IllegalStateException(paramHttpParams.toString());
  }
  
  public CookieSpecProvider lookup(String paramString)
  {
    return new CookieSpecRegistry.1(this, paramString);
  }
  
  public void register(String paramString, CookieSpecFactory paramCookieSpecFactory)
  {
    Args.notNull(paramString, "Name");
    Args.notNull(paramCookieSpecFactory, "Cookie spec factory");
    registeredSpecs.put(paramString.toLowerCase(Locale.ENGLISH), paramCookieSpecFactory);
  }
}
