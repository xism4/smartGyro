package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;

public class BasicHeaderValueFormatter
  implements HeaderValueFormatter
{
  @Deprecated
  public static final BasicHeaderValueFormatter DEFAULT = new BasicHeaderValueFormatter();
  public static final BasicHeaderValueFormatter INSTANCE = new BasicHeaderValueFormatter();
  
  public BasicHeaderValueFormatter() {}
  
  protected void doFormatValue(CharArrayBuffer paramCharArrayBuffer, String paramString, boolean paramBoolean)
  {
    int j = 0;
    boolean bool = paramBoolean;
    if (!paramBoolean)
    {
      i = 0;
      for (;;)
      {
        bool = paramBoolean;
        if (i >= paramString.length()) {
          break;
        }
        bool = paramBoolean;
        if (paramBoolean) {
          break;
        }
        paramBoolean = isSeparator(paramString.charAt(i));
        i += 1;
      }
    }
    int i = j;
    if (bool)
    {
      paramCharArrayBuffer.append('"');
      i = j;
    }
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (isUnsafe(c)) {
        paramCharArrayBuffer.append('\\');
      }
      paramCharArrayBuffer.append(c);
      i += 1;
    }
    if (bool) {
      paramCharArrayBuffer.append('"');
    }
  }
  
  protected int estimateHeaderElementLen(HeaderElement paramHeaderElement)
  {
    int k = 0;
    if (paramHeaderElement == null) {
      return 0;
    }
    int j = paramHeaderElement.getName().length();
    int i = j;
    String str = paramHeaderElement.getValue();
    if (str != null) {
      i = j + (str.length() + 3);
    }
    int m = paramHeaderElement.getParameterCount();
    j = i;
    if (m > 0) {
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += estimateNameValuePairLen(paramHeaderElement.getParameter(k)) + 2;
        k += 1;
      }
    }
    return j;
  }
  
  protected int estimateNameValuePairLen(NameValuePair paramNameValuePair)
  {
    if (paramNameValuePair == null) {
      return 0;
    }
    int j = paramNameValuePair.getName().length();
    paramNameValuePair = paramNameValuePair.getValue();
    int i = j;
    if (paramNameValuePair != null) {
      i = j + (paramNameValuePair.length() + 3);
    }
    return i;
  }
  
  protected int estimateParametersLen(NameValuePair[] paramArrayOfNameValuePair)
  {
    int i = 0;
    if (paramArrayOfNameValuePair != null)
    {
      if (paramArrayOfNameValuePair.length < 1) {
        return 0;
      }
      int j = (paramArrayOfNameValuePair.length - 1) * 2;
      int k = paramArrayOfNameValuePair.length;
      while (i < k)
      {
        j += estimateNameValuePairLen(paramArrayOfNameValuePair[i]);
        i += 1;
      }
      return j;
    }
    return 0;
  }
  
  public CharArrayBuffer formatHeaderElement(CharArrayBuffer paramCharArrayBuffer, HeaderElement paramHeaderElement, boolean paramBoolean)
  {
    Args.notNull(paramHeaderElement, "Header element");
    int i = estimateHeaderElementLen(paramHeaderElement);
    if (paramCharArrayBuffer == null) {
      paramCharArrayBuffer = new CharArrayBuffer(i);
    } else {
      paramCharArrayBuffer.ensureCapacity(i);
    }
    paramCharArrayBuffer.append(paramHeaderElement.getName());
    String str = paramHeaderElement.getValue();
    if (str != null)
    {
      paramCharArrayBuffer.append('=');
      doFormatValue(paramCharArrayBuffer, str, paramBoolean);
    }
    int j = paramHeaderElement.getParameterCount();
    if (j > 0)
    {
      i = 0;
      while (i < j)
      {
        paramCharArrayBuffer.append("; ");
        formatNameValuePair(paramCharArrayBuffer, paramHeaderElement.getParameter(i), paramBoolean);
        i += 1;
      }
    }
    return paramCharArrayBuffer;
  }
  
  public CharArrayBuffer formatNameValuePair(CharArrayBuffer paramCharArrayBuffer, NameValuePair paramNameValuePair, boolean paramBoolean)
  {
    Args.notNull(paramNameValuePair, "Name / value pair");
    int i = estimateNameValuePairLen(paramNameValuePair);
    if (paramCharArrayBuffer == null) {
      paramCharArrayBuffer = new CharArrayBuffer(i);
    } else {
      paramCharArrayBuffer.ensureCapacity(i);
    }
    paramCharArrayBuffer.append(paramNameValuePair.getName());
    paramNameValuePair = paramNameValuePair.getValue();
    if (paramNameValuePair != null)
    {
      paramCharArrayBuffer.append('=');
      doFormatValue(paramCharArrayBuffer, paramNameValuePair, paramBoolean);
    }
    return paramCharArrayBuffer;
  }
  
  public CharArrayBuffer formatParameters(CharArrayBuffer paramCharArrayBuffer, NameValuePair[] paramArrayOfNameValuePair, boolean paramBoolean)
  {
    Args.notNull(paramArrayOfNameValuePair, "Header parameter array");
    int i = estimateParametersLen(paramArrayOfNameValuePair);
    if (paramCharArrayBuffer == null) {
      paramCharArrayBuffer = new CharArrayBuffer(i);
    } else {
      paramCharArrayBuffer.ensureCapacity(i);
    }
    i = 0;
    while (i < paramArrayOfNameValuePair.length)
    {
      if (i > 0) {
        paramCharArrayBuffer.append("; ");
      }
      formatNameValuePair(paramCharArrayBuffer, paramArrayOfNameValuePair[i], paramBoolean);
      i += 1;
    }
    return paramCharArrayBuffer;
  }
  
  protected boolean isSeparator(char paramChar)
  {
    return " ;,:@()<>\\\"/[]?={}\t".indexOf(paramChar) >= 0;
  }
  
  protected boolean isUnsafe(char paramChar)
  {
    return "\"\\".indexOf(paramChar) >= 0;
  }
}
