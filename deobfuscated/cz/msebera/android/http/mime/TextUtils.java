package cz.msebera.android.http.mime;

public final class TextUtils
{
  public static boolean containsBlanks(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      return false;
    }
    int i = 0;
    while (i < paramCharSequence.length())
    {
      if (Character.isWhitespace(paramCharSequence.charAt(i))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isBlank(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      return true;
    }
    int i = 0;
    while (i < paramCharSequence.length())
    {
      if (!Character.isWhitespace(paramCharSequence.charAt(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static boolean isEmpty(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      return true;
    }
    return paramCharSequence.length() == 0;
  }
}
