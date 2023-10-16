package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;

public class BasicPathHandler
  implements cz.msebera.android.http.cookie.Object
{
  public BasicPathHandler() {}
  
  static boolean pathMatch(String paramString1, String paramString2)
  {
    String str = paramString2;
    if (paramString2 == null) {
      str = "/";
    }
    paramString2 = str;
    if (str.length() > 1)
    {
      paramString2 = str;
      if (str.endsWith("/")) {
        paramString2 = str.substring(0, str.length() - 1);
      }
    }
    if (paramString1.startsWith(paramString2))
    {
      if (paramString2.equals("/")) {
        return true;
      }
      if (paramString1.length() == paramString2.length()) {
        return true;
      }
      if (paramString1.charAt(paramString2.length()) == '/') {
        return true;
      }
    }
    return false;
  }
  
  public String getAttributeName()
  {
    return "path";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    return pathMatch(paramCookieOrigin.getPath(), paramCookie.getPath());
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (TextUtils.isBlank(paramString)) {
      paramString = "/";
    }
    paramSetCookie.setPath(paramString);
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (match(paramCookie, paramCookieOrigin)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Illegal 'path' attribute \"");
    localStringBuilder.append(paramCookie.getPath());
    localStringBuilder.append("\". Path of origin: \"");
    localStringBuilder.append(paramCookieOrigin.getPath());
    localStringBuilder.append("\"");
    throw new CookieRestrictionViolationException(localStringBuilder.toString());
  }
}
