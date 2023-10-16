package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthSchemeFactory;
import cz.msebera.android.http.auth.AuthSchemeProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;

public class NTLMSchemeFactory
  implements AuthSchemeFactory, AuthSchemeProvider
{
  public NTLMSchemeFactory() {}
  
  public AuthScheme create(HttpContext paramHttpContext)
  {
    return new NTLMScheme();
  }
  
  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new NTLMScheme();
  }
}
