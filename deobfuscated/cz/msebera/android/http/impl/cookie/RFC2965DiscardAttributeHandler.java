package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.cookie.SetCookie2;

public class RFC2965DiscardAttributeHandler
  implements cz.msebera.android.http.cookie.Object
{
  public RFC2965DiscardAttributeHandler() {}
  
  public String getAttributeName()
  {
    return "discard";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    return true;
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    if ((paramSetCookie instanceof SetCookie2)) {
      ((SetCookie2)paramSetCookie).setDiscard(true);
    }
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin) {}
}
