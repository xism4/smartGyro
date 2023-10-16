package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.Locale;

public class RFC2965VersionAttributeHandler
  implements cz.msebera.android.http.cookie.Object
{
  public RFC2965VersionAttributeHandler() {}
  
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
    return (paramCookieOrigin.equals(paramCookie)) || ((paramCookie.startsWith(".")) && (paramCookieOrigin.endsWith(paramCookie)));
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (paramString != null)
    {
      if (!paramString.trim().isEmpty())
      {
        paramSetCookie.setDomain(paramString);
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
    paramCookieOrigin = paramCookieOrigin.getHost();
    paramCookie = paramCookie.getDomain();
    if (paramCookie != null)
    {
      if (!paramCookie.equals(paramCookieOrigin))
      {
        if (paramCookie.indexOf('.') != -1)
        {
          if (paramCookie.startsWith("."))
          {
            int i = paramCookie.indexOf('.', 1);
            if ((i >= 0) && (i != paramCookie.length() - 1))
            {
              paramCookieOrigin = paramCookieOrigin.toLowerCase(Locale.ROOT);
              if (paramCookieOrigin.endsWith(paramCookie))
              {
                if (paramCookieOrigin.substring(0, paramCookieOrigin.length() - paramCookie.length()).indexOf('.') == -1) {
                  return;
                }
                paramCookieOrigin = new StringBuilder();
                paramCookieOrigin.append("Domain attribute \"");
                paramCookieOrigin.append(paramCookie);
                paramCookieOrigin.append("\" violates RFC 2109: host minus domain may not contain any dots");
                throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
              }
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("Illegal domain attribute \"");
              localStringBuilder.append(paramCookie);
              localStringBuilder.append("\". Domain of origin: \"");
              localStringBuilder.append(paramCookieOrigin);
              localStringBuilder.append("\"");
              throw new CookieRestrictionViolationException(localStringBuilder.toString());
            }
            paramCookieOrigin = new StringBuilder();
            paramCookieOrigin.append("Domain attribute \"");
            paramCookieOrigin.append(paramCookie);
            paramCookieOrigin.append("\" violates RFC 2109: domain must contain an embedded dot");
            throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
          }
          paramCookieOrigin = new StringBuilder();
          paramCookieOrigin.append("Domain attribute \"");
          paramCookieOrigin.append(paramCookie);
          paramCookieOrigin.append("\" violates RFC 2109: domain must start with a dot");
          throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Domain attribute \"");
        localStringBuilder.append(paramCookie);
        localStringBuilder.append("\" does not match the host \"");
        localStringBuilder.append(paramCookieOrigin);
        localStringBuilder.append("\"");
        throw new CookieRestrictionViolationException(localStringBuilder.toString());
      }
    }
    else {
      throw new CookieRestrictionViolationException("Cookie domain may not be null");
    }
  }
}
