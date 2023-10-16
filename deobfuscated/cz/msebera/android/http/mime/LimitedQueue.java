package cz.msebera.android.http.mime;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class LimitedQueue
{
  public static Charset lookup(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      paramString = Charset.forName(paramString);
      return paramString;
    }
    catch (UnsupportedCharsetException paramString) {}
    return null;
  }
}
