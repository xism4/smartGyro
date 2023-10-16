package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.conn.socket.InetAddressUtils;
import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import java.util.Locale;

public class BasicDomainHandler
  implements cz.msebera.android.http.cookie.Object
{
  public BasicDomainHandler() {}
  
  static boolean domainMatch(String paramString1, String paramString2)
  {
    if (!InetAddressUtils.isIPv4Address(paramString2))
    {
      if (InetAddressUtils.isIPv6Address(paramString2)) {
        return false;
      }
      String str = paramString1;
      if (paramString1.startsWith(".")) {
        str = paramString1.substring(1);
      }
      if (paramString2.endsWith(str))
      {
        int i = paramString2.length() - str.length();
        if (i == 0) {
          return true;
        }
        if ((i > 1) && (paramString2.charAt(i - 1) == '.')) {
          return true;
        }
      }
    }
    return false;
  }
  
  public String getAttributeName()
  {
    return "domain";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    String str2 = paramCookieOrigin.getHost();
    String str1 = paramCookie.getDomain();
    paramCookieOrigin = str1;
    if (str1 == null) {
      return false;
    }
    if (str1.startsWith(".")) {
      paramCookieOrigin = str1.substring(1);
    }
    paramCookieOrigin = paramCookieOrigin.toLowerCase(Locale.ROOT);
    if (str2.equals(paramCookieOrigin)) {
      return true;
    }
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("domain"))) {
      return domainMatch(paramCookieOrigin, str2);
    }
    return false;
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (!TextUtils.isBlank(paramString))
    {
      if (paramString.endsWith(".")) {
        return;
      }
      String str = paramString;
      if (paramString.startsWith(".")) {
        str = paramString.substring(1);
      }
      paramSetCookie.setDomain(str.toLowerCase(Locale.ROOT));
      return;
    }
    throw new MalformedCookieException("Blank or null value for domain attribute");
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    paramCookieOrigin = paramCookieOrigin.getHost();
    paramCookie = paramCookie.getDomain();
    if (paramCookie != null)
    {
      if (!paramCookieOrigin.equals(paramCookie))
      {
        if (domainMatch(paramCookie, paramCookieOrigin)) {
          return;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Illegal 'domain' attribute \"");
        localStringBuilder.append(paramCookie);
        localStringBuilder.append("\". Domain of origin: \"");
        localStringBuilder.append(paramCookieOrigin);
        localStringBuilder.append("\"");
        throw new CookieRestrictionViolationException(localStringBuilder.toString());
      }
    }
    else {
      throw new CookieRestrictionViolationException("Cookie 'domain' may not be null");
    }
  }
}
