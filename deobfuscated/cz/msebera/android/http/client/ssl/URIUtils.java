package cz.msebera.android.http.client.ssl;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;

public class URIUtils
{
  public static HttpHost extractHost(URI paramURI)
  {
    if (paramURI == null) {
      return null;
    }
    if (paramURI.isAbsolute())
    {
      int j = paramURI.getPort();
      Object localObject2 = paramURI.getHost();
      Object localObject1 = localObject2;
      int i = j;
      int k;
      int n;
      int m;
      if (localObject2 == null)
      {
        String str = paramURI.getAuthority();
        localObject2 = str;
        i = j;
        localObject1 = localObject2;
        if (str != null)
        {
          k = str.indexOf('@');
          if (k >= 0)
          {
            i = str.length();
            k += 1;
            if (i > k) {
              localObject2 = str.substring(k);
            } else {
              localObject2 = null;
            }
          }
          i = j;
          localObject1 = localObject2;
          if (localObject2 != null)
          {
            n = ((String)localObject2).indexOf(':');
            i = j;
            localObject1 = localObject2;
            if (n >= 0)
            {
              m = n + 1;
              i = m;
              k = 0;
              while ((i < ((String)localObject2).length()) && (Character.isDigit(((String)localObject2).charAt(i))))
              {
                k += 1;
                i += 1;
              }
              i = j;
              if (k <= 0) {}
            }
          }
        }
      }
      try
      {
        i = Integer.parseInt(((String)localObject2).substring(m, k + m));
        localObject1 = ((String)localObject2).substring(0, n);
        paramURI = paramURI.getScheme();
        if (TextUtils.isBlank((CharSequence)localObject1)) {}
      }
      catch (NumberFormatException localNumberFormatException)
      {
        for (;;)
        {
          try
          {
            paramURI = new HttpHost((String)localObject1, i, paramURI);
            return paramURI;
          }
          catch (IllegalArgumentException paramURI) {}
          localNumberFormatException = localNumberFormatException;
          i = j;
        }
      }
    }
    return null;
  }
  
  private static URI normalizeSyntax(URI paramURI)
  {
    if (!paramURI.isOpaque())
    {
      if (paramURI.getAuthority() == null) {
        return paramURI;
      }
      Args.check(paramURI.isAbsolute(), "Base URI must be absolute");
      Object localObject1;
      if (paramURI.getPath() == null) {
        localObject1 = "";
      } else {
        localObject1 = paramURI.getPath();
      }
      Object localObject2 = ((String)localObject1).split("/");
      Object localObject3 = new Stack();
      int j = localObject2.length;
      int i = 0;
      Object localObject4;
      while (i < j)
      {
        localObject4 = localObject2[i];
        if ((!((String)localObject4).isEmpty()) && (!".".equals(localObject4))) {
          if ("..".equals(localObject4))
          {
            if (!((Stack)localObject3).isEmpty()) {
              ((Stack)localObject3).pop();
            }
          }
          else {
            ((Stack)localObject3).push(localObject4);
          }
        }
        i += 1;
      }
      localObject2 = new StringBuilder();
      localObject3 = ((Stack)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (String)((Iterator)localObject3).next();
        ((StringBuilder)localObject2).append('/');
        ((StringBuilder)localObject2).append((String)localObject4);
      }
      if (((String)localObject1).lastIndexOf('/') == ((String)localObject1).length() - 1) {
        ((StringBuilder)localObject2).append('/');
      }
      try
      {
        localObject1 = paramURI.getScheme();
        localObject3 = Locale.ROOT;
        localObject1 = ((String)localObject1).toLowerCase((Locale)localObject3);
        localObject3 = paramURI.getAuthority();
        localObject4 = Locale.ROOT;
        localObject3 = ((String)localObject3).toLowerCase((Locale)localObject4);
        localObject1 = new URI((String)localObject1, (String)localObject3, ((StringBuilder)localObject2).toString(), null, null);
        localObject2 = paramURI.getQuery();
        if (localObject2 == null)
        {
          localObject2 = paramURI.getFragment();
          if (localObject2 == null) {
            return localObject1;
          }
        }
        localObject1 = new StringBuilder(((URI)localObject1).toASCIIString());
        localObject2 = paramURI.getQuery();
        if (localObject2 != null)
        {
          ((StringBuilder)localObject1).append('?');
          ((StringBuilder)localObject1).append(paramURI.getRawQuery());
        }
        localObject2 = paramURI.getFragment();
        if (localObject2 != null)
        {
          ((StringBuilder)localObject1).append('#');
          ((StringBuilder)localObject1).append(paramURI.getRawFragment());
        }
        paramURI = URI.create(((StringBuilder)localObject1).toString());
        return paramURI;
      }
      catch (URISyntaxException paramURI)
      {
        throw new IllegalArgumentException(paramURI);
      }
    }
    return paramURI;
  }
  
  public static URI resolve(URI paramURI1, URI paramURI2)
  {
    Args.notNull(paramURI1, "Base URI");
    Args.notNull(paramURI2, "Reference URI");
    String str = paramURI2.toString();
    if (str.startsWith("?")) {
      return resolveReferenceStartingWithQueryString(paramURI1, paramURI2);
    }
    boolean bool = str.isEmpty();
    if (bool) {
      paramURI2 = URI.create("#");
    }
    paramURI2 = paramURI1.resolve(paramURI2);
    paramURI1 = paramURI2;
    if (bool)
    {
      paramURI1 = paramURI2.toString();
      paramURI1 = URI.create(paramURI1.substring(0, paramURI1.indexOf('#')));
    }
    return normalizeSyntax(paramURI1);
  }
  
  private static URI resolveReferenceStartingWithQueryString(URI paramURI1, URI paramURI2)
  {
    Object localObject = paramURI1.toString();
    paramURI1 = (URI)localObject;
    if (((String)localObject).indexOf('?') > -1) {
      paramURI1 = ((String)localObject).substring(0, ((String)localObject).indexOf('?'));
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramURI1);
    ((StringBuilder)localObject).append(paramURI2.toString());
    return URI.create(((StringBuilder)localObject).toString());
  }
  
  public static URI rewriteURI(URI paramURI)
  {
    Args.notNull(paramURI, "URI");
    if (paramURI.isOpaque()) {
      return paramURI;
    }
    paramURI = new URIBuilder(paramURI);
    if (paramURI.getUserInfo() != null) {
      paramURI.setUserInfo(null);
    }
    if (TextUtils.isEmpty(paramURI.getPath())) {
      paramURI.setPath("/");
    }
    if (paramURI.getHost() != null) {
      paramURI.setHost(paramURI.getHost().toLowerCase(Locale.ROOT));
    }
    paramURI.setFragment(null);
    return paramURI.build();
  }
  
  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost, boolean paramBoolean)
  {
    Args.notNull(paramURI, "URI");
    if (paramURI.isOpaque()) {
      return paramURI;
    }
    paramURI = new URIBuilder(paramURI);
    int i;
    if (paramHttpHost != null)
    {
      paramURI.setScheme(paramHttpHost.getSchemeName());
      paramURI.setHost(paramHttpHost.getHostName());
      i = paramHttpHost.getPort();
    }
    else
    {
      paramURI.setScheme(null);
      paramURI.setHost(null);
      i = -1;
    }
    paramURI.setPort(i);
    if (paramBoolean) {
      paramURI.setFragment(null);
    }
    if (TextUtils.isEmpty(paramURI.getPath())) {
      paramURI.setPath("/");
    }
    return paramURI.build();
  }
}
