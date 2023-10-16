package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie2;
import cz.msebera.android.http.mime.Args;
import java.util.Iterator;
import java.util.List;

public class BestMatchSpec
  implements CookieSpec
{
  private final RFC2109Spec obsoleteStrict;
  private final NetscapeDraftSpec rfc2109;
  private final RFC2965Spec strict;
  
  public BestMatchSpec(String[] paramArrayOfString, boolean paramBoolean)
  {
    strict = new RFC2965Spec(paramBoolean, new cz.msebera.android.http.cookie.Object[] { new RFC2109DomainHandler(), new BasicPathHandler(), new RFC2965DomainAttributeHandler(), new RFC2965PortAttributeHandler(), new BasicCommentHandler(), new BasicSecureHandler(), new BasicMaxAgeHandler(), new RFC2965CommentUrlAttributeHandler(), new RFC2965DiscardAttributeHandler() });
    obsoleteStrict = new RFC2109Spec(paramBoolean, new cz.msebera.android.http.cookie.Object[] { new RFC2109VersionHandler(), new BasicPathHandler(), new RFC2965VersionAttributeHandler(), new BasicCommentHandler(), new BasicSecureHandler(), new BasicMaxAgeHandler() });
    BasicDomainHandler localBasicDomainHandler = new BasicDomainHandler();
    BasicPathHandler localBasicPathHandler = new BasicPathHandler();
    BasicSecureHandler localBasicSecureHandler = new BasicSecureHandler();
    BasicMaxAgeHandler localBasicMaxAgeHandler = new BasicMaxAgeHandler();
    if (paramArrayOfString != null)
    {
      paramArrayOfString = (String[])paramArrayOfString.clone();
    }
    else
    {
      paramArrayOfString = new String[1];
      paramArrayOfString[0] = "EEE, dd-MMM-yy HH:mm:ss z";
    }
    rfc2109 = new NetscapeDraftSpec(new cz.msebera.android.http.cookie.Object[] { localBasicDomainHandler, localBasicPathHandler, localBasicSecureHandler, localBasicMaxAgeHandler, new BasicExpiresHandler(paramArrayOfString) });
  }
  
  public List formatCookies(List paramList)
  {
    Args.notNull(paramList, "List of cookies");
    Iterator localIterator = paramList.iterator();
    int i = Integer.MAX_VALUE;
    int j = 1;
    while (localIterator.hasNext())
    {
      Cookie localCookie = (Cookie)localIterator.next();
      int k = j;
      if (!(localCookie instanceof SetCookie2)) {
        k = 0;
      }
      j = k;
      if (localCookie.getVersion() < i)
      {
        i = localCookie.getVersion();
        j = k;
      }
    }
    if (i > 0)
    {
      if (j != 0) {
        return strict.formatCookies(paramList);
      }
      return obsoleteStrict.formatCookies(paramList);
    }
    return rfc2109.formatCookies(paramList);
  }
  
  public int getVersion()
  {
    return strict.getVersion();
  }
  
  public Header getVersionHeader()
  {
    return null;
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    if (paramCookie.getVersion() > 0)
    {
      if ((paramCookie instanceof SetCookie2)) {
        return strict.match(paramCookie, paramCookieOrigin);
      }
      return obsoleteStrict.match(paramCookie, paramCookieOrigin);
    }
    return rfc2109.match(paramCookie, paramCookieOrigin);
  }
  
  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramHeader, "Header");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    Object localObject = paramHeader.getElements();
    int m = localObject.length;
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < m)
    {
      localNetscapeDraftHeaderParser = localObject[i];
      if (localNetscapeDraftHeaderParser.getParameterByName("version") != null) {
        k = 1;
      }
      if (localNetscapeDraftHeaderParser.getParameterByName("expires") != null) {
        j = 1;
      }
      i += 1;
    }
    if ((j == 0) && (k != 0))
    {
      if ("Set-Cookie2".equals(paramHeader.getName())) {
        return strict.parse((HeaderElement[])localObject, paramCookieOrigin);
      }
      return obsoleteStrict.parse((HeaderElement[])localObject, paramCookieOrigin);
    }
    NetscapeDraftHeaderParser localNetscapeDraftHeaderParser = NetscapeDraftHeaderParser.DEFAULT;
    if ((paramHeader instanceof FormattedHeader))
    {
      FormattedHeader localFormattedHeader = (FormattedHeader)paramHeader;
      localObject = localFormattedHeader.getBuffer();
      paramHeader = (Header)localObject;
      localObject = new cz.msebera.android.http.message.CharArrayBuffer(localFormattedHeader.getValuePos(), ((cz.msebera.android.http.mime.CharArrayBuffer)localObject).length());
    }
    else
    {
      localObject = paramHeader.getValue();
      if (localObject == null) {
        break label259;
      }
      paramHeader = new cz.msebera.android.http.mime.CharArrayBuffer(((String)localObject).length());
      paramHeader.append((String)localObject);
      localObject = new cz.msebera.android.http.message.CharArrayBuffer(0, paramHeader.length());
    }
    paramHeader = localNetscapeDraftHeaderParser.parseHeader(paramHeader, (cz.msebera.android.http.message.CharArrayBuffer)localObject);
    return rfc2109.parse(new HeaderElement[] { paramHeader }, paramCookieOrigin);
    label259:
    paramHeader = new MalformedCookieException("Header value is null");
    throw paramHeader;
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    if (paramCookie.getVersion() > 0)
    {
      if ((paramCookie instanceof SetCookie2))
      {
        strict.validate(paramCookie, paramCookieOrigin);
        return;
      }
      obsoleteStrict.validate(paramCookie, paramCookieOrigin);
      return;
    }
    rfc2109.validate(paramCookie, paramCookieOrigin);
  }
}
