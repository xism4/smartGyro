package cz.msebera.android.http.util;

import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.mime.Args;
import java.nio.charset.Charset;

@Deprecated
public final class HttpProtocolParams
  implements CoreProtocolPNames
{
  public static String getHttpElementCharset(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    String str = (String)paramHttpParams.getParameter("http.protocol.element-charset");
    paramHttpParams = str;
    if (str == null) {
      paramHttpParams = HTTP.DEF_PROTOCOL_CHARSET.name();
    }
    return paramHttpParams;
  }
  
  public static ProtocolVersion getVersion(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams = paramHttpParams.getParameter("http.protocol.version");
    if (paramHttpParams == null) {
      return HttpVersion.HTTP_1_1;
    }
    return (ProtocolVersion)paramHttpParams;
  }
  
  public static void setContentCharset(HttpParams paramHttpParams, String paramString)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setParameter("http.protocol.content-charset", paramString);
  }
  
  public static void setUserAgent(HttpParams paramHttpParams, String paramString)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setParameter("http.useragent", paramString);
  }
  
  public static void setVersion(HttpParams paramHttpParams, ProtocolVersion paramProtocolVersion)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    paramHttpParams.setParameter("http.protocol.version", paramProtocolVersion);
  }
}
