package cz.msebera.android.http.message;

import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;

public class BasicNameValuePair
  implements NameValuePair, Cloneable, Serializable
{
  private final String name;
  private final String value;
  
  public BasicNameValuePair(String paramString1, String paramString2)
  {
    Args.notNull(paramString1, "Name");
    name = ((String)paramString1);
    value = paramString2;
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof NameValuePair))
    {
      paramObject = (BasicNameValuePair)paramObject;
      return (name.equals(name)) && (LangUtils.equals(value, value));
    }
    return false;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getValue()
  {
    return value;
  }
  
  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, name), value);
  }
  
  public String toString()
  {
    if (value == null) {
      return name;
    }
    StringBuilder localStringBuilder = new StringBuilder(name.length() + 1 + value.length());
    localStringBuilder.append(name);
    localStringBuilder.append("=");
    localStringBuilder.append(value);
    return localStringBuilder.toString();
  }
}
