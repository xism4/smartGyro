package cz.msebera.android.http.impl.client;

import c.a.a.a.f.c;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.cookie.BeanSerializer.1;
import cz.msebera.android.http.cookie.Cookie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class BasicCookieStore
  implements CookieStore, Serializable
{
  private final TreeSet<c> cookies = new TreeSet(new BeanSerializer.1());
  
  public BasicCookieStore() {}
  
  public void addCookie(Cookie paramCookie)
  {
    if (paramCookie != null) {
      try
      {
        cookies.remove(paramCookie);
        if (!paramCookie.isExpired(new Date())) {
          cookies.add(paramCookie);
        }
      }
      catch (Throwable paramCookie)
      {
        throw paramCookie;
      }
    }
  }
  
  public boolean clearExpired(Date paramDate)
  {
    boolean bool = false;
    if (paramDate == null) {
      return false;
    }
    try
    {
      Iterator localIterator = cookies.iterator();
      while (localIterator.hasNext()) {
        if (((Cookie)localIterator.next()).isExpired(paramDate))
        {
          localIterator.remove();
          bool = true;
        }
      }
      return bool;
    }
    catch (Throwable paramDate)
    {
      throw paramDate;
    }
  }
  
  public List getCookies()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(cookies);
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public String toString()
  {
    try
    {
      String str = cookies.toString();
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
