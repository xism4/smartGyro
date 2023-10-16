package cz.msebera.android.http.client;

import cz.msebera.android.http.cookie.Cookie;
import java.util.Date;
import java.util.List;

public abstract interface CookieStore
{
  public abstract void addCookie(Cookie paramCookie);
  
  public abstract boolean clearExpired(Date paramDate);
  
  public abstract List getCookies();
}
