package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public abstract class CookieSpecBase
  extends AbstractCookieSpec
{
  public CookieSpecBase() {}
  
  protected CookieSpecBase(cz.msebera.android.http.cookie.Object... paramVarArgs)
  {
    super(paramVarArgs);
  }
  
  protected static String getDefaultDomain(CookieOrigin paramCookieOrigin)
  {
    return paramCookieOrigin.getHost();
  }
  
  protected static String getDefaultPath(CookieOrigin paramCookieOrigin)
  {
    String str = paramCookieOrigin.getPath();
    int j = str.lastIndexOf('/');
    int i = j;
    paramCookieOrigin = str;
    if (j >= 0)
    {
      if (j == 0) {
        i = 1;
      }
      paramCookieOrigin = str.substring(0, i);
    }
    return paramCookieOrigin;
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    Iterator localIterator = getAttribHandlers().iterator();
    while (localIterator.hasNext()) {
      if (!((CookieAttributeHandler)localIterator.next()).match(paramCookie, paramCookieOrigin)) {
        return false;
      }
    }
    return true;
  }
  
  protected List parse(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfHeaderElement.length);
    int k = paramArrayOfHeaderElement.length;
    int i = 0;
    while (i < k)
    {
      Object localObject2 = paramArrayOfHeaderElement[i];
      Object localObject1 = ((HeaderElement)localObject2).getName();
      String str = ((HeaderElement)localObject2).getValue();
      if ((localObject1 != null) && (!((String)localObject1).isEmpty()))
      {
        localObject1 = new BasicClientCookie((String)localObject1, str);
        ((BasicClientCookie)localObject1).setPath(getDefaultPath(paramCookieOrigin));
        ((BasicClientCookie)localObject1).setDomain(getDefaultDomain(paramCookieOrigin));
        localObject2 = ((HeaderElement)localObject2).getParameters();
        int j = localObject2.length - 1;
        while (j >= 0)
        {
          str = localObject2[j];
          Object localObject3 = str.getName().toLowerCase(Locale.ROOT);
          ((BasicClientCookie)localObject1).setAttribute((String)localObject3, str.getValue());
          localObject3 = findAttribHandler((String)localObject3);
          if (localObject3 != null) {
            ((CookieAttributeHandler)localObject3).parse((SetCookie)localObject1, str.getValue());
          }
          j -= 1;
        }
        localArrayList.add(localObject1);
        i += 1;
      }
      else
      {
        throw new MalformedCookieException("Cookie name may not be empty");
      }
    }
    return localArrayList;
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    Iterator localIterator = getAttribHandlers().iterator();
    while (localIterator.hasNext()) {
      ((CookieAttributeHandler)localIterator.next()).validate(paramCookie, paramCookieOrigin);
    }
  }
}
