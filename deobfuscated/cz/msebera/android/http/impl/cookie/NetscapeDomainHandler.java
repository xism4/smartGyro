package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import java.util.Locale;
import java.util.StringTokenizer;

public class NetscapeDomainHandler
  extends BasicDomainHandler
{
  public NetscapeDomainHandler() {}
  
  private static boolean isSpecialDomain(String paramString)
  {
    paramString = paramString.toUpperCase(Locale.ROOT);
    return (paramString.endsWith(".COM")) || (paramString.endsWith(".EDU")) || (paramString.endsWith(".NET")) || (paramString.endsWith(".GOV")) || (paramString.endsWith(".MIL")) || (paramString.endsWith(".ORG")) || (paramString.endsWith(".INT"));
  }
  
  public String getAttributeName()
  {
    return "domain";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    paramCookieOrigin = paramCookieOrigin.getHost();
    paramCookie = paramCookie.getDomain();
    if (paramCookie == null) {
      return false;
    }
    return paramCookieOrigin.endsWith(paramCookie);
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (!TextUtils.isBlank(paramString))
    {
      paramSetCookie.setDomain(paramString);
      return;
    }
    throw new MalformedCookieException("Blank or null value for domain attribute");
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    paramCookieOrigin = paramCookieOrigin.getHost();
    paramCookie = paramCookie.getDomain();
    if ((!paramCookieOrigin.equals(paramCookie)) && (!BasicDomainHandler.domainMatch(paramCookie, paramCookieOrigin)))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Illegal domain attribute \"");
      localStringBuilder.append(paramCookie);
      localStringBuilder.append("\". Domain of origin: \"");
      localStringBuilder.append(paramCookieOrigin);
      localStringBuilder.append("\"");
      throw new CookieRestrictionViolationException(localStringBuilder.toString());
    }
    if (paramCookieOrigin.contains("."))
    {
      int i = new StringTokenizer(paramCookie, ".").countTokens();
      if (isSpecialDomain(paramCookie))
      {
        if (i >= 2) {
          return;
        }
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie);
        paramCookieOrigin.append("\" violates the Netscape cookie specification for ");
        paramCookieOrigin.append("special domains");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
      if (i >= 3) {
        return;
      }
      paramCookieOrigin = new StringBuilder();
      paramCookieOrigin.append("Domain attribute \"");
      paramCookieOrigin.append(paramCookie);
      paramCookieOrigin.append("\" violates the Netscape cookie specification");
      throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
    }
  }
}
