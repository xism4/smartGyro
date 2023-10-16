package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.cookie.SetCookie2;

public class RFC2965CommentUrlAttributeHandler
  implements cz.msebera.android.http.cookie.Object
{
  public RFC2965CommentUrlAttributeHandler() {}
  
  public String getAttributeName()
  {
    return "commenturl";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    return true;
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    if ((paramSetCookie instanceof SetCookie2)) {
      ((SetCookie2)paramSetCookie).setCommentURL(paramString);
    }
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin) {}
}
