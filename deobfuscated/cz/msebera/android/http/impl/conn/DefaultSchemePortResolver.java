package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.SchemePortResolver;
import cz.msebera.android.http.conn.UnsupportedSchemeException;
import cz.msebera.android.http.mime.Args;

public class DefaultSchemePortResolver
  implements SchemePortResolver
{
  public static final DefaultSchemePortResolver INSTANCE = new DefaultSchemePortResolver();
  
  public DefaultSchemePortResolver() {}
  
  public int resolve(HttpHost paramHttpHost)
  {
    Args.notNull(paramHttpHost, "HTTP host");
    int i = paramHttpHost.getPort();
    if (i > 0) {
      return i;
    }
    paramHttpHost = paramHttpHost.getSchemeName();
    if (paramHttpHost.equalsIgnoreCase("http")) {
      return 80;
    }
    if (paramHttpHost.equalsIgnoreCase("https")) {
      return 443;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramHttpHost);
    localStringBuilder.append(" protocol is not supported");
    throw new UnsupportedSchemeException(localStringBuilder.toString());
  }
}
