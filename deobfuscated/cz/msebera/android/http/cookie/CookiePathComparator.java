package cz.msebera.android.http.cookie;

import c.a.a.a.f.c;
import java.io.Serializable;
import java.util.Comparator;

public class CookiePathComparator
  implements Serializable, Comparator<c>
{
  public static final CookiePathComparator INSTANCE = new CookiePathComparator();
  
  public CookiePathComparator() {}
  
  private String normalizePath(Cookie paramCookie)
  {
    Object localObject = paramCookie.getPath();
    paramCookie = (Cookie)localObject;
    if (localObject == null) {
      paramCookie = "/";
    }
    if (!paramCookie.endsWith("/"))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramCookie);
      ((StringBuilder)localObject).append('/');
      return ((StringBuilder)localObject).toString();
    }
    return paramCookie;
  }
  
  public int compare(Cookie paramCookie1, Cookie paramCookie2)
  {
    paramCookie1 = normalizePath(paramCookie1);
    paramCookie2 = normalizePath(paramCookie2);
    if (paramCookie1.equals(paramCookie2)) {
      return 0;
    }
    if (paramCookie1.startsWith(paramCookie2)) {
      return -1;
    }
    if (paramCookie2.startsWith(paramCookie1)) {
      return 1;
    }
    return 0;
  }
}
