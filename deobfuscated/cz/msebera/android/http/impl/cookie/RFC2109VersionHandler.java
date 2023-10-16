package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;

public class RFC2109VersionHandler
  extends AbstractCookieAttributeHandler
  implements Object
{
  public RFC2109VersionHandler() {}
  
  public String getAttributeName()
  {
    return "version";
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (paramString != null)
    {
      if (!paramString.trim().isEmpty()) {
        try
        {
          paramSetCookie.setVersion(Integer.parseInt(paramString));
          return;
        }
        catch (NumberFormatException paramSetCookie)
        {
          paramString = new StringBuilder();
          paramString.append("Invalid version: ");
          paramString.append(paramSetCookie.getMessage());
          throw new MalformedCookieException(paramString.toString());
        }
      }
      throw new MalformedCookieException("Blank value for version attribute");
    }
    throw new MalformedCookieException("Missing value for version attribute");
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    if (paramCookie.getVersion() >= 0) {
      return;
    }
    throw new CookieRestrictionViolationException("Cookie version may not be negative");
  }
}
