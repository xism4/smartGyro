package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.cookie.SetCookie2;
import cz.msebera.android.http.mime.Args;

public class RFC2109DomainHandler
  implements cz.msebera.android.http.cookie.Object
{
  public RFC2109DomainHandler() {}
  
  public String getAttributeName()
  {
    return "version";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    return true;
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (paramString != null) {}
    try
    {
      i = Integer.parseInt(paramString);
    }
    catch (NumberFormatException paramString)
    {
      int i;
      for (;;) {}
    }
    i = -1;
    if (i >= 0)
    {
      paramSetCookie.setVersion(i);
      return;
    }
    throw new MalformedCookieException("Invalid cookie version.");
    throw new MalformedCookieException("Missing value for version attribute");
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    if (((paramCookie instanceof SetCookie2)) && ((paramCookie instanceof ClientCookie)))
    {
      if (((ClientCookie)paramCookie).containsAttribute("version")) {
        return;
      }
      throw new CookieRestrictionViolationException("Violates RFC 2965. Version attribute is required.");
    }
  }
}
