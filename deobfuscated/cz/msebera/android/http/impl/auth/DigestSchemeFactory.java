package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthSchemeFactory;
import cz.msebera.android.http.auth.AuthSchemeProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.nio.charset.Charset;

public class DigestSchemeFactory
  implements AuthSchemeFactory, AuthSchemeProvider
{
  private final Charset charset;
  
  public DigestSchemeFactory()
  {
    this(null);
  }
  
  public DigestSchemeFactory(Charset paramCharset)
  {
    charset = paramCharset;
  }
  
  public AuthScheme create(HttpContext paramHttpContext)
  {
    return new BasicScheme(charset);
  }
  
  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new BasicScheme();
  }
}
