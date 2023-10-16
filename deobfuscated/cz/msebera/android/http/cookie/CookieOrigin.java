package cz.msebera.android.http.cookie;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import java.util.Locale;

public final class CookieOrigin
{
  private final String host;
  private final String path;
  private final int port;
  private final boolean secure;
  
  public CookieOrigin(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    Args.notBlank(paramString1, "Host");
    Args.notNegative(paramInt, "Port");
    Args.notNull(paramString2, "Path");
    host = paramString1.toLowerCase(Locale.ROOT);
    port = paramInt;
    if (!TextUtils.isBlank(paramString2)) {
      path = paramString2;
    } else {
      path = "/";
    }
    secure = paramBoolean;
  }
  
  public String getHost()
  {
    return host;
  }
  
  public String getPath()
  {
    return path;
  }
  
  public int getPort()
  {
    return port;
  }
  
  public boolean isSecure()
  {
    return secure;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    if (secure) {
      localStringBuilder.append("(secure)");
    }
    localStringBuilder.append(host);
    localStringBuilder.append(':');
    localStringBuilder.append(Integer.toString(port));
    localStringBuilder.append(path);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
