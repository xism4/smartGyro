package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.message.BasicHeaderElement;
import cz.msebera.android.http.message.BasicHeaderValueFormatter;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BrowserCompatSpec
  extends CookieSpecBase
{
  private static final String[] DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z" };
  
  public BrowserCompatSpec(String[] paramArrayOfString, MathArrays.OrderDirection paramOrderDirection) {}
  
  private static boolean isQuoteEnclosed(String paramString)
  {
    return (paramString != null) && (paramString.startsWith("\"")) && (paramString.endsWith("\""));
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
      Cookie localCookie = (Cookie)paramList.get(i);
      if (i > 0) {
        localCharArrayBuffer.append("; ");
      }
      String str1 = localCookie.getName();
      String str2 = localCookie.getValue();
      if ((localCookie.getVersion() > 0) && (!isQuoteEnclosed(str2)))
      {
        BasicHeaderValueFormatter.INSTANCE.formatHeaderElement(localCharArrayBuffer, new BasicHeaderElement(str1, str2), false);
      }
      else
      {
        localCharArrayBuffer.append(str1);
        localCharArrayBuffer.append("=");
        if (str2 != null) {
          localCharArrayBuffer.append(str2);
        }
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
      Object localObject1 = paramHeader.getElements();
      int m = localObject1.length;
      int j = 0;
      int i = 0;
      int k = 0;
      while (j < m)
      {
        localObject2 = localObject1[j];
        if (((HeaderElement)localObject2).getParameterByName("version") != null) {
          k = 1;
        }
        if (((HeaderElement)localObject2).getParameterByName("expires") != null) {
          i = 1;
        }
        j += 1;
      }
      if ((i == 0) && (k != 0)) {
        return parse((HeaderElement[])localObject1, paramCookieOrigin);
      }
      Object localObject2 = NetscapeDraftHeaderParser.DEFAULT;
      if ((paramHeader instanceof FormattedHeader))
      {
        FormattedHeader localFormattedHeader = (FormattedHeader)paramHeader;
        localObject1 = localFormattedHeader.getBuffer();
        paramHeader = (Header)localObject1;
        localObject1 = new cz.msebera.android.http.message.CharArrayBuffer(localFormattedHeader.getValuePos(), ((cz.msebera.android.http.mime.CharArrayBuffer)localObject1).length());
      }
      else
      {
        localObject1 = paramHeader.getValue();
        if (localObject1 == null) {
          break label401;
        }
        paramHeader = new cz.msebera.android.http.mime.CharArrayBuffer(((String)localObject1).length());
        paramHeader.append((String)localObject1);
        localObject1 = new cz.msebera.android.http.message.CharArrayBuffer(0, paramHeader.length());
      }
      localObject1 = ((NetscapeDraftHeaderParser)localObject2).parseHeader(paramHeader, (cz.msebera.android.http.message.CharArrayBuffer)localObject1);
      paramHeader = ((HeaderElement)localObject1).getName();
      localObject2 = ((HeaderElement)localObject1).getValue();
      if ((paramHeader != null) && (!paramHeader.isEmpty()))
      {
        paramHeader = new BasicClientCookie(paramHeader, (String)localObject2);
        paramHeader.setPath(CookieSpecBase.getDefaultPath(paramCookieOrigin));
        paramHeader.setDomain(CookieSpecBase.getDefaultDomain(paramCookieOrigin));
        paramCookieOrigin = ((HeaderElement)localObject1).getParameters();
        j = paramCookieOrigin.length - 1;
        while (j >= 0)
        {
          localObject1 = paramCookieOrigin[j];
          localObject2 = ((NameValuePair)localObject1).getName().toLowerCase(Locale.ROOT);
          paramHeader.setAttribute((String)localObject2, ((NameValuePair)localObject1).getValue());
          localObject2 = findAttribHandler((String)localObject2);
          if (localObject2 != null) {
            ((CookieAttributeHandler)localObject2).parse(paramHeader, ((NameValuePair)localObject1).getValue());
          }
          j -= 1;
        }
        if (i != 0) {
          paramHeader.setVersion(0);
        }
        return Collections.singletonList(paramHeader);
      }
      throw new MalformedCookieException("Cookie name may not be empty");
      label401:
      throw new MalformedCookieException("Header value is null");
    }
    paramCookieOrigin = new StringBuilder();
    paramCookieOrigin.append("Unrecognized cookie header '");
    paramCookieOrigin.append(paramHeader.toString());
    paramCookieOrigin.append("'");
    paramHeader = new MalformedCookieException(paramCookieOrigin.toString());
    throw paramHeader;
  }
  
  public String toString()
  {
    return "compatibility";
  }
}
