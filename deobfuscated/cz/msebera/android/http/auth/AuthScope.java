package cz.msebera.android.http.auth;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.util.Locale;

public class AuthScope
{
  public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
  public static final String ANY_HOST;
  public static final String ANY_REALM;
  public static final String ANY_SCHEME;
  private final String host;
  private final HttpHost origin;
  private final int port;
  private final String realm;
  private final String scheme;
  
  public AuthScope(HttpHost paramHttpHost, String paramString1, String paramString2)
  {
    Args.notNull(paramHttpHost, "Host");
    host = paramHttpHost.getHostName().toLowerCase(Locale.ROOT);
    int i;
    if (paramHttpHost.getPort() < 0) {
      i = -1;
    } else {
      i = paramHttpHost.getPort();
    }
    port = i;
    String str = paramString1;
    if (paramString1 == null) {
      str = ANY_REALM;
    }
    realm = str;
    if (paramString2 == null) {
      paramString1 = ANY_SCHEME;
    } else {
      paramString1 = paramString2.toUpperCase(Locale.ROOT);
    }
    scheme = paramString1;
    origin = paramHttpHost;
  }
  
  public AuthScope(String paramString, int paramInt)
  {
    this(paramString, paramInt, ANY_REALM, ANY_SCHEME);
  }
  
  public AuthScope(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    if (paramString1 == null) {
      paramString1 = ANY_HOST;
    } else {
      paramString1 = paramString1.toLowerCase(Locale.ROOT);
    }
    host = paramString1;
    int i = paramInt;
    if (paramInt < 0) {
      i = -1;
    }
    port = i;
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = ANY_REALM;
    }
    realm = paramString1;
    if (paramString3 == null) {
      paramString1 = ANY_SCHEME;
    } else {
      paramString1 = paramString3.toUpperCase(Locale.ROOT);
    }
    scheme = paramString1;
    origin = null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof AuthScope)) {
      return super.equals(paramObject);
    }
    paramObject = (AuthScope)paramObject;
    return (LangUtils.equals(host, host)) && (port == port) && (LangUtils.equals(realm, realm)) && (LangUtils.equals(scheme, scheme));
  }
  
  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, host), port), realm), scheme);
  }
  
  public int match(AuthScope paramAuthScope)
  {
    int j;
    if (LangUtils.equals(scheme, scheme))
    {
      j = 1;
    }
    else
    {
      str1 = scheme;
      str2 = ANY_SCHEME;
      if ((str1 != str2) && (scheme != str2)) {
        return -1;
      }
      j = 0;
    }
    int i;
    if (LangUtils.equals(realm, realm))
    {
      i = j + 2;
    }
    else
    {
      str1 = realm;
      str2 = ANY_REALM;
      i = j;
      if (str1 != str2)
      {
        i = j;
        if (realm != str2) {
          return -1;
        }
      }
    }
    int k = port;
    int m = port;
    if (k == m)
    {
      j = i + 4;
    }
    else
    {
      j = i;
      if (k != -1)
      {
        j = i;
        if (m != -1) {
          return -1;
        }
      }
    }
    if (LangUtils.equals(host, host)) {
      return j + 8;
    }
    String str1 = host;
    String str2 = ANY_HOST;
    if ((str1 != str2) && (host != str2)) {
      return -1;
    }
    return j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = scheme;
    if (str != null)
    {
      localStringBuilder.append(str.toUpperCase(Locale.ROOT));
      localStringBuilder.append(' ');
    }
    if (realm != null)
    {
      localStringBuilder.append('\'');
      localStringBuilder.append(realm);
      localStringBuilder.append('\'');
    }
    else
    {
      localStringBuilder.append("<any realm>");
    }
    if (host != null)
    {
      localStringBuilder.append('@');
      localStringBuilder.append(host);
      if (port >= 0)
      {
        localStringBuilder.append(':');
        localStringBuilder.append(port);
      }
    }
    return localStringBuilder.toString();
  }
}
