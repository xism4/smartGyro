package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import java.util.ArrayList;
import java.util.List;

public class NetscapeDraftSpec
  extends CookieSpecBase
{
  public NetscapeDraftSpec()
  {
    this(null);
  }
  
  NetscapeDraftSpec(cz.msebera.android.http.cookie.Object... paramVarArgs)
  {
    super(paramVarArgs);
  }
  
  public NetscapeDraftSpec(String[] paramArrayOfString)
  {
    super(new cz.msebera.android.http.cookie.Object[] { localBasicPathHandler, localNetscapeDomainHandler, localBasicSecureHandler, localBasicMaxAgeHandler, new BasicExpiresHandler(paramArrayOfString) });
  }
  
  public List formatCookies(List paramList)
  {
    Args.notEmpty(paramList, "List of cookies");
    cz.msebera.android.http.mime.CharArrayBuffer localCharArrayBuffer = new cz.msebera.android.http.mime.CharArrayBuffer(paramList.size() * 20);
    localCharArrayBuffer.append("Cookie");
    localCharArrayBuffer.append(": ");
    int i = 0;
    while (i < paramList.size())
    {
      Object localObject = (Cookie)paramList.get(i);
      if (i > 0) {
        localCharArrayBuffer.append("; ");
      }
      localCharArrayBuffer.append(((Cookie)localObject).getName());
      localObject = ((Cookie)localObject).getValue();
      if (localObject != null)
      {
        localCharArrayBuffer.append("=");
        localCharArrayBuffer.append((String)localObject);
      }
      i += 1;
    }
    paramList = new ArrayList(1);
    paramList.add(new BufferedHeader(localCharArrayBuffer));
    return paramList;
  }
  
  public int getVersion()
  {
    return 0;
  }
  
  public Header getVersionHeader()
  {
    return null;
  }
  
  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramHeader, "Header");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    if (paramHeader.getName().equalsIgnoreCase("Set-Cookie"))
    {
      NetscapeDraftHeaderParser localNetscapeDraftHeaderParser = NetscapeDraftHeaderParser.DEFAULT;
      Object localObject;
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
          break label139;
        }
        paramHeader = new cz.msebera.android.http.mime.CharArrayBuffer(((String)localObject).length());
        paramHeader.append((String)localObject);
        localObject = new cz.msebera.android.http.message.CharArrayBuffer(0, paramHeader.length());
      }
      return parse(new HeaderElement[] { localNetscapeDraftHeaderParser.parseHeader(paramHeader, (cz.msebera.android.http.message.CharArrayBuffer)localObject) }, paramCookieOrigin);
      label139:
      throw new MalformedCookieException("Header value is null");
    }
    paramCookieOrigin = new StringBuilder();
    paramCookieOrigin.append("Unrecognized cookie header '");
    paramCookieOrigin.append(paramHeader.toString());
    paramCookieOrigin.append("'");
    throw new MalformedCookieException(paramCookieOrigin.toString());
  }
  
  public String toString()
  {
    return "netscape";
  }
}
