package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookiePathComparator;
import cz.msebera.android.http.cookie.CookieRestrictionViolationException;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RFC2109Spec
  extends CookieSpecBase
{
  static final String[] DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy" };
  private final boolean oneHeader;
  
  public RFC2109Spec()
  {
    this(null, false);
  }
  
  protected RFC2109Spec(boolean paramBoolean, cz.msebera.android.http.cookie.Object... paramVarArgs)
  {
    super(paramVarArgs);
    oneHeader = paramBoolean;
  }
  
  public RFC2109Spec(String[] paramArrayOfString, boolean paramBoolean)
  {
    super(new cz.msebera.android.http.cookie.Object[] { localRFC2109VersionHandler, localBasicPathHandler, localRFC2965VersionAttributeHandler, localBasicCommentHandler, localBasicSecureHandler, localBasicMaxAgeHandler, new BasicExpiresHandler(paramArrayOfString) });
    oneHeader = paramBoolean;
  }
  
  private List doFormatManyHeaders(List paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Cookie localCookie = (Cookie)paramList.next();
      int i = localCookie.getVersion();
      CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(40);
      localCharArrayBuffer.append("Cookie: ");
      localCharArrayBuffer.append("$Version=");
      localCharArrayBuffer.append(Integer.toString(i));
      localCharArrayBuffer.append("; ");
      formatCookieAsVer(localCharArrayBuffer, localCookie, i);
      localArrayList.add(new BufferedHeader(localCharArrayBuffer));
    }
    return localArrayList;
  }
  
  private List doFormatOneHeader(List paramList)
  {
    Object localObject = paramList.iterator();
    int i = Integer.MAX_VALUE;
    Cookie localCookie;
    while (((Iterator)localObject).hasNext())
    {
      localCookie = (Cookie)((Iterator)localObject).next();
      if (localCookie.getVersion() < i) {
        i = localCookie.getVersion();
      }
    }
    localObject = new CharArrayBuffer(paramList.size() * 40);
    ((CharArrayBuffer)localObject).append("Cookie");
    ((CharArrayBuffer)localObject).append(": ");
    ((CharArrayBuffer)localObject).append("$Version=");
    ((CharArrayBuffer)localObject).append(Integer.toString(i));
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localCookie = (Cookie)paramList.next();
      ((CharArrayBuffer)localObject).append("; ");
      formatCookieAsVer((CharArrayBuffer)localObject, localCookie, i);
    }
    paramList = new ArrayList(1);
    paramList.add(new BufferedHeader((CharArrayBuffer)localObject));
    return paramList;
  }
  
  protected void formatCookieAsVer(CharArrayBuffer paramCharArrayBuffer, Cookie paramCookie, int paramInt)
  {
    formatParamAsVer(paramCharArrayBuffer, paramCookie.getName(), paramCookie.getValue(), paramInt);
    if ((paramCookie.getPath() != null) && ((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("path")))
    {
      paramCharArrayBuffer.append("; ");
      formatParamAsVer(paramCharArrayBuffer, "$Path", paramCookie.getPath(), paramInt);
    }
    if ((paramCookie.getDomain() != null) && ((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("domain")))
    {
      paramCharArrayBuffer.append("; ");
      formatParamAsVer(paramCharArrayBuffer, "$Domain", paramCookie.getDomain(), paramInt);
    }
  }
  
  public List formatCookies(List paramList)
  {
    Args.notEmpty((Collection)paramList, "List of cookies");
    Object localObject = paramList;
    if (((List)paramList).size() > 1)
    {
      localObject = new ArrayList((Collection)paramList);
      Collections.sort((List)localObject, CookiePathComparator.INSTANCE);
    }
    if (oneHeader) {
      return doFormatOneHeader((List)localObject);
    }
    return doFormatManyHeaders((List)localObject);
  }
  
  protected void formatParamAsVer(CharArrayBuffer paramCharArrayBuffer, String paramString1, String paramString2, int paramInt)
  {
    paramCharArrayBuffer.append(paramString1);
    paramCharArrayBuffer.append("=");
    if (paramString2 != null)
    {
      if (paramInt > 0)
      {
        paramCharArrayBuffer.append('"');
        paramCharArrayBuffer.append(paramString2);
        paramCharArrayBuffer.append('"');
        return;
      }
      paramCharArrayBuffer.append(paramString2);
    }
  }
  
  public int getVersion()
  {
    return 1;
  }
  
  public Header getVersionHeader()
  {
    return null;
  }
  
  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramHeader, "Header");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    if (paramHeader.getName().equalsIgnoreCase("Set-Cookie")) {
      return parse(paramHeader.getElements(), paramCookieOrigin);
    }
    paramCookieOrigin = new StringBuilder();
    paramCookieOrigin.append("Unrecognized cookie header '");
    paramCookieOrigin.append(paramHeader.toString());
    paramCookieOrigin.append("'");
    throw new MalformedCookieException(paramCookieOrigin.toString());
  }
  
  public String toString()
  {
    return "rfc2109";
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    String str = paramCookie.getName();
    if (str.indexOf(' ') == -1)
    {
      if (!str.startsWith("$"))
      {
        super.validate(paramCookie, paramCookieOrigin);
        return;
      }
      throw new CookieRestrictionViolationException("Cookie name may not start with $");
    }
    throw new CookieRestrictionViolationException("Cookie name may not contain blanks");
  }
}
