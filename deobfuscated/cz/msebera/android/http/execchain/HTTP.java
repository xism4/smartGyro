package cz.msebera.android.http.execchain;

import cz.msebera.android.http.Consts;
import java.nio.charset.Charset;

public final class HTTP
{
  public static final Charset DEF_CONTENT_CHARSET = Consts.ISO_8859_1;
  public static final Charset DEF_PROTOCOL_CHARSET = Consts.ASCII;
  
  public static boolean isWhitespace(char paramChar)
  {
    return (paramChar == ' ') || (paramChar == '\t') || (paramChar == '\r') || (paramChar == '\n');
  }
}
