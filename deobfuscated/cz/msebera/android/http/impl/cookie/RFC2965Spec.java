package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class RFC2965Spec
  extends RFC2109Spec
{
  public RFC2965Spec()
  {
    this(null, false);
  }
  
  RFC2965Spec(boolean paramBoolean, cz.msebera.android.http.cookie.Object... paramVarArgs)
  {
    super(paramBoolean, paramVarArgs);
  }
  
  public RFC2965Spec(String[] paramArrayOfString, boolean paramBoolean)
  {
    super(paramBoolean, new cz.msebera.android.http.cookie.Object[] { localRFC2109DomainHandler, localBasicPathHandler, localRFC2965DomainAttributeHandler, localRFC2965PortAttributeHandler, localBasicCommentHandler, localBasicSecureHandler, localBasicMaxAgeHandler, new BasicExpiresHandler(paramArrayOfString), new RFC2965CommentUrlAttributeHandler(), new RFC2965DiscardAttributeHandler() });
  }
  
  private static CookieOrigin adjustEffectiveHost(CookieOrigin paramCookieOrigin)
  {
    String str = paramCookieOrigin.getHost();
    int k = 0;
    int i = 0;
    while (i < str.length())
    {
      int m = str.charAt(i);
      j = k;
      if (m == 46) {
        break label57;
      }
      if (m == 58)
      {
        j = k;
        break label57;
      }
      i += 1;
    }
    int j = 1;
    label57:
    if (j != 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(".local");
      return new CookieOrigin(localStringBuilder.toString(), paramCookieOrigin.getPort(), paramCookieOrigin.getPath(), paramCookieOrigin.isSecure());
    }
    return paramCookieOrigin;
  }
  
  private List createCookies(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfHeaderElement.length);
    int k = paramArrayOfHeaderElement.length;
    int i = 0;
    while (i < k)
    {
      Object localObject2 = paramArrayOfHeaderElement[i];
      Object localObject1 = ((HeaderElement)localObject2).getName();
      Object localObject3 = ((HeaderElement)localObject2).getValue();
      if ((localObject1 != null) && (!((String)localObject1).isEmpty()))
      {
        localObject1 = new BasicClientCookie2((String)localObject1, (String)localObject3);
        ((BasicClientCookie)localObject1).setPath(CookieSpecBase.getDefaultPath(paramCookieOrigin));
        ((BasicClientCookie)localObject1).setDomain(CookieSpecBase.getDefaultDomain(paramCookieOrigin));
        ((BasicClientCookie2)localObject1).setPorts(new int[] { paramCookieOrigin.getPort() });
        localObject2 = ((HeaderElement)localObject2).getParameters();
        localObject3 = new HashMap(localObject2.length);
        int j = localObject2.length - 1;
        Object localObject4;
        while (j >= 0)
        {
          localObject4 = localObject2[j];
          ((Map)localObject3).put(((NameValuePair)localObject4).getName().toLowerCase(Locale.ROOT), localObject4);
          j -= 1;
        }
        localObject2 = ((Map)localObject3).entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (NameValuePair)((Map.Entry)((Iterator)localObject2).next()).getValue();
          localObject4 = ((NameValuePair)localObject3).getName().toLowerCase(Locale.ROOT);
          ((BasicClientCookie)localObject1).setAttribute((String)localObject4, ((NameValuePair)localObject3).getValue());
          localObject4 = findAttribHandler((String)localObject4);
          if (localObject4 != null) {
            ((CookieAttributeHandler)localObject4).parse((SetCookie)localObject1, ((NameValuePair)localObject3).getValue());
          }
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
  
  protected void formatCookieAsVer(CharArrayBuffer paramCharArrayBuffer, Cookie paramCookie, int paramInt)
  {
    super.formatCookieAsVer(paramCharArrayBuffer, paramCookie, paramInt);
    if ((paramCookie instanceof ClientCookie))
    {
      String str = ((ClientCookie)paramCookie).getAttribute("port");
      if (str != null)
      {
        paramCharArrayBuffer.append("; $Port");
        paramCharArrayBuffer.append("=\"");
        if (!str.trim().isEmpty())
        {
          paramCookie = paramCookie.getPorts();
          if (paramCookie != null)
          {
            int i = paramCookie.length;
            paramInt = 0;
            while (paramInt < i)
            {
              if (paramInt > 0) {
                paramCharArrayBuffer.append(",");
              }
              paramCharArrayBuffer.append(Integer.toString(paramCookie[paramInt]));
              paramInt += 1;
            }
          }
        }
        paramCharArrayBuffer.append("\"");
      }
    }
  }
  
  public int getVersion()
  {
    return 1;
  }
  
  public Header getVersionHeader()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(40);
    localCharArrayBuffer.append("Cookie2");
    localCharArrayBuffer.append(": ");
    localCharArrayBuffer.append("$Version=");
    localCharArrayBuffer.append(Integer.toString(getVersion()));
    return new BufferedHeader(localCharArrayBuffer);
  }
  
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    return super.match(paramCookie, adjustEffectiveHost(paramCookieOrigin));
  }
  
  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramHeader, "Header");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    if (paramHeader.getName().equalsIgnoreCase("Set-Cookie2")) {
      return createCookies(paramHeader.getElements(), adjustEffectiveHost(paramCookieOrigin));
    }
    paramCookieOrigin = new StringBuilder();
    paramCookieOrigin.append("Unrecognized cookie header '");
    paramCookieOrigin.append(paramHeader.toString());
    paramCookieOrigin.append("'");
    throw new MalformedCookieException(paramCookieOrigin.toString());
  }
  
  protected List parse(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
  {
    return createCookies(paramArrayOfHeaderElement, adjustEffectiveHost(paramCookieOrigin));
  }
  
  public String toString()
  {
    return "rfc2965";
  }
  
  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    Args.notNull(paramCookie, "Cookie");
    Args.notNull(paramCookieOrigin, "Cookie origin");
    super.validate(paramCookie, adjustEffectiveHost(paramCookieOrigin));
  }
}
