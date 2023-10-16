package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.Serializable;

public class BasicHeader
  implements Header, Cloneable, Serializable
{
  private final String name;
  private final String value;
  
  public BasicHeader(String paramString1, String paramString2)
  {
    Args.notNull(paramString1, "Name");
    name = ((String)paramString1);
    value = paramString2;
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public HeaderElement[] getElements()
  {
    String str = value;
    if (str != null) {
      return BasicHeaderValueParser.parseElements(str, null);
    }
    return new HeaderElement[0];
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getValue()
  {
    return value;
  }
  
  public String toString()
  {
    return BasicLineFormatter.INSTANCE.formatHeader(null, this).toString();
  }
}
