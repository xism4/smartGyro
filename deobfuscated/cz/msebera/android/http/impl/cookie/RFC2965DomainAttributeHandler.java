package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.Locale;

public class RFC2965DomainAttributeHandler
  implements cz.msebera.android.http.cookie.Object
{
  public RFC2965DomainAttributeHandler() {}
  
  public boolean domainMatch(String paramString1, String paramString2)
  {
    return (paramString1.equals(paramString2)) || ((paramString2.startsWith(".")) && (paramString1.endsWith(paramString2)));
  }
  
  public String getAttributeName()
  {
    return "domain";
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    paramCookieOrigin = paramCookieOrigin.getHost().toLowerCase(Locale.ROOT);
    paramCookie = paramCookie.getDomain();
    if (!domainMatch(paramCookieOrigin, paramCookie)) {
      return false;
    }
    return paramCookieOrigin.substring(0, paramCookieOrigin.length() - paramCookie.length()).indexOf('.') == -1;
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (paramString != null)
    {
      if (!paramString.trim().isEmpty())
      {
        String str2 = paramString.toLowerCase(Locale.ROOT);
        String str1 = str2;
        if (!paramString.startsWith("."))
        {
          paramString = new StringBuilder();
          paramString.append('.');
          paramString.append(str2);
          str1 = paramString.toString();
        }
        paramSetCookie.setDomain(str1);
        return;
      }
      throw new MalformedCookieException("Blank value for domain attribute");
    }
    throw new MalformedCookieException("Missing value for domain attribute");
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    paramCookieOrigin = paramCookieOrigin.getHost().toLowerCase(Locale.ROOT);
    if (paramCookie.getDomain() != null)
    {
      Object localObject = paramCookie.getDomain().toLowerCase(Locale.ROOT);
      if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("domain")))
      {
        if (((String)localObject).startsWith("."))
        {
          int i = ((String)localObject).indexOf('.', 1);
          if (((i >= 0) && (i != ((String)localObject).length() - 1)) || (((String)localObject).equals(".local")))
          {
            if (domainMatch(paramCookieOrigin, (String)localObject))
            {
              if (paramCookieOrigin.substring(0, paramCookieOrigin.length() - ((String)localObject).length()).indexOf('.') == -1) {
                return;
              }
              paramCookieOrigin = new StringBuilder();
              paramCookieOrigin.append("Domain attribute \"");
              paramCookieOrigin.append(paramCookie.getDomain());
              paramCookieOrigin.append("\" violates RFC 2965: ");
              paramCookieOrigin.append("effective host minus domain may not contain any dots");
              throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
            }
            paramCookieOrigin = new StringBuilder();
            paramCookieOrigin.append("Domain attribute \"");
            paramCookieOrigin.append(paramCookie.getDomain());
            paramCookieOrigin.append("\" violates RFC 2965: effective host name does not ");
            paramCookieOrigin.append("domain-match domain attribute.");
            throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
          }
          paramCookieOrigin = new StringBuilder();
          paramCookieOrigin.append("Domain attribute \"");
          paramCookieOrigin.append(paramCookie.getDomain());
          paramCookieOrigin.append("\" violates RFC 2965: the value contains no embedded dots ");
          paramCookieOrigin.append("and the value is not .local");
          throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
        }
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie.getDomain());
        paramCookieOrigin.append("\" violates RFC 2109: domain must start with a dot");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
      if (paramCookie.getDomain().equals(paramCookieOrigin)) {
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Illegal domain attribute: \"");
      ((StringBuilder)localObject).append(paramCookie.getDomain());
      ((StringBuilder)localObject).append("\".");
      ((StringBuilder)localObject).append("Domain of origin: \"");
      ((StringBuilder)localObject).append(paramCookieOrigin);
      ((StringBuilder)localObject).append("\"");
      throw new CookieRestrictionViolationException(((StringBuilder)localObject).toString());
    }
    throw new CookieRestrictionViolationException("Invalid cookie state: domain not specified");
  }
}
