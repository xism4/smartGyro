package cz.msebera.android.http.auth;

import c.a.a.a.a.d;
import c.a.a.a.a.e;
import c.a.a.a.d.a;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class AuthSchemeRegistry
  implements a<e>
{
  private final ConcurrentHashMap<String, d> registeredSchemes = new ConcurrentHashMap();
  
  public AuthSchemeRegistry() {}
  
  public AuthScheme getAuthScheme(String paramString, HttpParams paramHttpParams)
  {
    Args.notNull(paramString, "Name");
    AuthSchemeFactory localAuthSchemeFactory = (AuthSchemeFactory)registeredSchemes.get(paramString.toLowerCase(Locale.ENGLISH));
    if (localAuthSchemeFactory != null) {
      return localAuthSchemeFactory.newInstance(paramHttpParams);
    }
    paramHttpParams = new StringBuilder();
    paramHttpParams.append("Unsupported authentication scheme: ");
    paramHttpParams.append(paramString);
    throw new IllegalStateException(paramHttpParams.toString());
  }
  
  public AuthSchemeProvider lookup(String paramString)
  {
    return new AuthSchemeRegistry.1(this, paramString);
  }
  
  public void register(String paramString, AuthSchemeFactory paramAuthSchemeFactory)
  {
    Args.notNull(paramString, "Name");
    Args.notNull(paramAuthSchemeFactory, "Authentication scheme factory");
    registeredSchemes.put(paramString.toLowerCase(Locale.ENGLISH), paramAuthSchemeFactory);
  }
}
