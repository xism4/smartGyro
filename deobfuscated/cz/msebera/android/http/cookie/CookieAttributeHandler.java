package cz.msebera.android.http.cookie;

public abstract interface CookieAttributeHandler
{
  public abstract boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin);
  
  public abstract void parse(SetCookie paramSetCookie, String paramString);
  
  public abstract void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin);
}
