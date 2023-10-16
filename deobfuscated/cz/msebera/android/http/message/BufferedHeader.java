package cz.msebera.android.http.message;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.mime.Args;
import java.io.Serializable;

public class BufferedHeader
  implements FormattedHeader, Cloneable, Serializable
{
  private final cz.msebera.android.http.mime.CharArrayBuffer buffer;
  private final String name;
  private final int valuePos;
  
  public BufferedHeader(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    int i = paramCharArrayBuffer.indexOf(58);
    if (i != -1)
    {
      localObject = paramCharArrayBuffer.substringTrimmed(0, i);
      if (((String)localObject).length() != 0)
      {
        buffer = paramCharArrayBuffer;
        name = ((String)localObject);
        valuePos = (i + 1);
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid header: ");
      ((StringBuilder)localObject).append(paramCharArrayBuffer.toString());
      throw new ParseException(((StringBuilder)localObject).toString());
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Invalid header: ");
    ((StringBuilder)localObject).append(paramCharArrayBuffer.toString());
    throw new ParseException(((StringBuilder)localObject).toString());
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public cz.msebera.android.http.mime.CharArrayBuffer getBuffer()
  {
    return buffer;
  }
  
  public HeaderElement[] getElements()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(0, buffer.length());
    localCharArrayBuffer.append(valuePos);
    return BasicHeaderValueParser.INSTANCE.parseElements(buffer, localCharArrayBuffer);
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getValue()
  {
    cz.msebera.android.http.mime.CharArrayBuffer localCharArrayBuffer = buffer;
    return localCharArrayBuffer.substringTrimmed(valuePos, localCharArrayBuffer.length());
  }
  
  public int getValuePos()
  {
    return valuePos;
  }
  
  public String toString()
  {
    return buffer.toString();
  }
}
