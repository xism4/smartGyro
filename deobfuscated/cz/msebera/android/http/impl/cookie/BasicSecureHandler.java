package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;

public class BasicSecureHandler
  extends AbstractCookieAttributeHandler
  implements Object
{
  public BasicSecureHandler() {}
  
  public String getAttributeName()
  {
    return "secure";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    return (!paramCookie.isSecure()) || (paramCookieOrigin.isSecure());
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    paramSetCookie.setSecure(true);
  }
}
