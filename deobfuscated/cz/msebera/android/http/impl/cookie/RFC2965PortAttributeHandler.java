package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.cookie.SetCookie2;
import cz.msebera.android.http.mime.Args;
import java.util.StringTokenizer;

public class RFC2965PortAttributeHandler
  implements cz.msebera.android.http.cookie.Object
{
  public RFC2965PortAttributeHandler() {}
  
  private static int[] parsePortAttribute(String paramString)
  {
    paramString = new StringTokenizer(paramString, ",");
    Object localObject = new int[paramString.countTokens()];
    int i = 0;
    try
    {
      for (;;)
      {
        boolean bool = paramString.hasMoreTokens();
        if (!bool) {
          break label118;
        }
        int j = Integer.parseInt(paramString.nextToken().trim());
        localObject[i] = j;
        if (localObject[i] < 0) {
          break;
        }
        i += 1;
      }
      paramString = new MalformedCookieException("Invalid Port attribute.");
      throw paramString;
    }
    catch (NumberFormatException paramString)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid Port attribute: ");
      ((StringBuilder)localObject).append(paramString.getMessage());
      paramString = new MalformedCookieException(((StringBuilder)localObject).toString());
      throw paramString;
    }
    label118:
    return localObject;
  }
  
  private static boolean portMatch(int paramInt, int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramInt == paramArrayOfInt[i]) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public String getAttributeName()
  {
    return "port";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    int i = paramCookieOrigin.getPort();
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("port")))
    {
      if (paramCookie.getPorts() == null) {
        return false;
      }
      if (!portMatch(i, paramCookie.getPorts())) {
        return false;
      }
    }
    return true;
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if ((paramSetCookie instanceof SetCookie2))
    {
      paramSetCookie = (SetCookie2)paramSetCookie;
      if ((paramString != null) && (!paramString.trim().isEmpty())) {
        paramSetCookie.setPorts(parsePortAttribute(paramString));
      }
    }
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    int i = paramCookieOrigin.getPort();
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("port")))
    {
      if (portMatch(i, paramCookie.getPorts())) {
        return;
      }
      throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
    }
  }
}
