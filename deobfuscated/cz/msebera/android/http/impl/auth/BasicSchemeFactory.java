package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthSchemeFactory;
import cz.msebera.android.http.auth.AuthSchemeProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.nio.charset.Charset;

public class BasicSchemeFactory
  implements AuthSchemeFactory, AuthSchemeProvider
{
  private final Charset charset;
  
  public BasicSchemeFactory()
  {
    this(null);
  }
  
  public BasicSchemeFactory(Charset paramCharset)
  {
    charset = paramCharset;
  }
  
  public AuthScheme create(HttpContext paramHttpContext)
  {
    return new DigestScheme(charset);
  }
  
  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new DigestScheme();
  }
}
