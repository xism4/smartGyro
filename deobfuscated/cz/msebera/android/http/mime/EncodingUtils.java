package cz.msebera.android.http.mime;

import cz.msebera.android.http.Consts;
import java.io.UnsupportedEncodingException;

public final class EncodingUtils
{
  public static byte[] getAsciiBytes(String paramString)
  {
    Args.notNull(paramString, "Input");
    return paramString.getBytes(Consts.ASCII);
  }
  
  public static byte[] getBytes(String paramString1, String paramString2)
  {
    Args.notNull(paramString1, "Input");
    Args.notEmpty(paramString2, "Charset");
    try
    {
      paramString2 = paramString1.getBytes(paramString2);
      return paramString2;
    }
    catch (UnsupportedEncodingException paramString2)
    {
      for (;;) {}
    }
    return paramString1.getBytes();
  }
}
