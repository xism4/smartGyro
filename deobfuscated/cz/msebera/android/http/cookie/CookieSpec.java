package cz.msebera.android.http.cookie;

import cz.msebera.android.http.Header;
import java.util.List;

public abstract interface CookieSpec
{
  public abstract List formatCookies(List paramList);
  
  public abstract int getVersion();
  
  public abstract Header getVersionHeader();
  
  public abstract boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin);
  
  public abstract List parse(Header paramHeader, CookieOrigin paramCookieOrigin);
  
  public abstract void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin);
}
