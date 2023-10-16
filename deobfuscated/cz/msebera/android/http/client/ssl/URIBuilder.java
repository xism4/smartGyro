package cz.msebera.android.http.client.ssl;

import c.a.a.a.z;
import cz.msebera.android.http.Consts;
import cz.msebera.android.http.conn.socket.InetAddressUtils;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class URIBuilder
{
  private Charset charset;
  private String encodedAuthority;
  private String encodedFragment;
  private String encodedPath;
  private String encodedQuery;
  private String encodedSchemeSpecificPart;
  private String encodedUserInfo;
  private String fragment;
  private String host;
  private String path;
  private int port;
  private String query;
  private List<z> queryParams;
  private String scheme;
  private String userInfo;
  
  public URIBuilder(URI paramURI)
  {
    digestURI(paramURI);
  }
  
  private String buildString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = scheme;
    if (str != null)
    {
      localStringBuilder.append(str);
      localStringBuilder.append(':');
    }
    str = encodedSchemeSpecificPart;
    if (str != null) {}
    for (;;)
    {
      localStringBuilder.append(str);
      break;
      if (encodedAuthority != null)
      {
        localStringBuilder.append("//");
        localStringBuilder.append(encodedAuthority);
      }
      else if (host != null)
      {
        localStringBuilder.append("//");
        str = encodedUserInfo;
        if (str != null) {}
        for (;;)
        {
          localStringBuilder.append(str);
          localStringBuilder.append("@");
          break;
          str = userInfo;
          if (str == null) {
            break;
          }
          str = encodePath(str);
        }
        if (InetAddressUtils.isIPv6Address(host))
        {
          localStringBuilder.append("[");
          localStringBuilder.append(host);
          str = "]";
        }
        else
        {
          str = host;
        }
        localStringBuilder.append(str);
        if (port >= 0)
        {
          localStringBuilder.append(":");
          localStringBuilder.append(port);
        }
      }
      str = encodedPath;
      if (str != null) {}
      for (str = normalizePath(str);; str = encodeUserInfo(normalizePath(str)))
      {
        localStringBuilder.append(str);
        break;
        str = path;
        if (str == null) {
          break;
        }
      }
      if (encodedQuery != null)
      {
        localStringBuilder.append("?");
        str = encodedQuery;
      }
      else if (queryParams != null)
      {
        localStringBuilder.append("?");
        str = encodeUrlForm(queryParams);
      }
      else
      {
        if (query == null) {
          break;
        }
        localStringBuilder.append("?");
        str = encodeUric(query);
      }
    }
    if (encodedFragment != null) {
      localStringBuilder.append("#");
    }
    for (str = encodedFragment;; str = encodeUric(fragment))
    {
      localStringBuilder.append(str);
      break;
      if (fragment == null) {
        break;
      }
      localStringBuilder.append("#");
    }
    return localStringBuilder.toString();
  }
  
  private void digestURI(URI paramURI)
  {
    scheme = paramURI.getScheme();
    encodedSchemeSpecificPart = paramURI.getRawSchemeSpecificPart();
    encodedAuthority = paramURI.getRawAuthority();
    host = paramURI.getHost();
    port = paramURI.getPort();
    encodedUserInfo = paramURI.getRawUserInfo();
    userInfo = paramURI.getUserInfo();
    encodedPath = paramURI.getRawPath();
    path = paramURI.getPath();
    encodedQuery = paramURI.getRawQuery();
    String str = paramURI.getRawQuery();
    Charset localCharset = charset;
    if (localCharset == null) {
      localCharset = Consts.UTF_8;
    }
    queryParams = parseQuery(str, localCharset);
    encodedFragment = paramURI.getRawFragment();
    fragment = paramURI.getFragment();
  }
  
  private String encodePath(String paramString)
  {
    Charset localCharset = charset;
    if (localCharset == null) {
      localCharset = Consts.UTF_8;
    }
    return URLEncodedUtils.encPath(paramString, localCharset);
  }
  
  private String encodeUric(String paramString)
  {
    Charset localCharset = charset;
    if (localCharset == null) {
      localCharset = Consts.UTF_8;
    }
    return URLEncodedUtils.encUric(paramString, localCharset);
  }
  
  private String encodeUrlForm(List paramList)
  {
    Charset localCharset = charset;
    if (localCharset == null) {
      localCharset = Consts.UTF_8;
    }
    return URLEncodedUtils.format(paramList, localCharset);
  }
  
  private String encodeUserInfo(String paramString)
  {
    Charset localCharset = charset;
    if (localCharset == null) {
      localCharset = Consts.UTF_8;
    }
    return URLEncodedUtils.encUserInfo(paramString, localCharset);
  }
  
  private static String normalizePath(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    int i = 0;
    while ((i < paramString.length()) && (paramString.charAt(i) == '/')) {
      i += 1;
    }
    String str = paramString;
    if (i > 1) {
      str = paramString.substring(i - 1);
    }
    return str;
  }
  
  private List parseQuery(String paramString, Charset paramCharset)
  {
    if ((paramString != null) && (!paramString.isEmpty())) {
      return URLEncodedUtils.parse(paramString, paramCharset);
    }
    return null;
  }
  
  public URIBuilder addParameters(List paramList)
  {
    if (queryParams == null) {
      queryParams = new ArrayList();
    }
    queryParams.addAll(paramList);
    encodedQuery = null;
    encodedSchemeSpecificPart = null;
    query = null;
    return this;
  }
  
  public URI build()
  {
    return new URI(buildString());
  }
  
  public URIBuilder clearParameters()
  {
    queryParams = null;
    encodedQuery = null;
    encodedSchemeSpecificPart = null;
    return this;
  }
  
  public String getHost()
  {
    return host;
  }
  
  public String getPath()
  {
    return path;
  }
  
  public List getQueryParams()
  {
    List localList = queryParams;
    if (localList != null) {
      return new ArrayList(localList);
    }
    return new ArrayList();
  }
  
  public String getUserInfo()
  {
    return userInfo;
  }
  
  public URIBuilder setCharset(Charset paramCharset)
  {
    charset = paramCharset;
    return this;
  }
  
  public URIBuilder setFragment(String paramString)
  {
    fragment = paramString;
    encodedFragment = null;
    return this;
  }
  
  public URIBuilder setHost(String paramString)
  {
    host = paramString;
    encodedSchemeSpecificPart = null;
    encodedAuthority = null;
    return this;
  }
  
  public URIBuilder setPath(String paramString)
  {
    path = paramString;
    encodedSchemeSpecificPart = null;
    encodedPath = null;
    return this;
  }
  
  public URIBuilder setPort(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = -1;
    }
    port = i;
    encodedSchemeSpecificPart = null;
    encodedAuthority = null;
    return this;
  }
  
  public URIBuilder setScheme(String paramString)
  {
    scheme = paramString;
    return this;
  }
  
  public URIBuilder setUserInfo(String paramString)
  {
    userInfo = paramString;
    encodedSchemeSpecificPart = null;
    encodedAuthority = null;
    encodedUserInfo = null;
    return this;
  }
  
  public String toString()
  {
    return buildString();
  }
}
