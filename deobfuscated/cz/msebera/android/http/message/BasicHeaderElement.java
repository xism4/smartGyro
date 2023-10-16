package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;

public class BasicHeaderElement
  implements HeaderElement, Cloneable
{
  private final String name;
  private final NameValuePair[] parameters;
  private final String value;
  
  public BasicHeaderElement(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }
  
  public BasicHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    Args.notNull(paramString1, "Name");
    name = ((String)paramString1);
    value = paramString2;
    if (paramArrayOfNameValuePair != null)
    {
      parameters = paramArrayOfNameValuePair;
      return;
    }
    parameters = new NameValuePair[0];
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
    if ((paramObject instanceof HeaderElement))
    {
      paramObject = (BasicHeaderElement)paramObject;
      return (name.equals(name)) && (LangUtils.equals(value, value)) && (LangUtils.equals(parameters, parameters));
    }
    return false;
  }
  
  public String getName()
  {
    return name;
  }
  
  public NameValuePair getParameter(int paramInt)
  {
    return parameters[paramInt];
  }
  
  public NameValuePair getParameterByName(String paramString)
  {
    Args.notNull(paramString, "Name");
    NameValuePair[] arrayOfNameValuePair = parameters;
    int j = arrayOfNameValuePair.length;
    int i = 0;
    while (i < j)
    {
      NameValuePair localNameValuePair = arrayOfNameValuePair[i];
      if (localNameValuePair.getName().equalsIgnoreCase(paramString)) {
        return localNameValuePair;
      }
      i += 1;
    }
    return null;
  }
  
  public int getParameterCount()
  {
    return parameters.length;
  }
  
  public NameValuePair[] getParameters()
  {
    return (NameValuePair[])parameters.clone();
  }
  
  public String getValue()
  {
    return value;
  }
  
  public int hashCode()
  {
    int j = LangUtils.hashCode(LangUtils.hashCode(17, name), value);
    NameValuePair[] arrayOfNameValuePair = parameters;
    int k = arrayOfNameValuePair.length;
    int i = 0;
    while (i < k)
    {
      j = LangUtils.hashCode(j, arrayOfNameValuePair[i]);
      i += 1;
    }
    return j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(name);
    if (value != null)
    {
      localStringBuilder.append("=");
      localStringBuilder.append(value);
    }
    NameValuePair[] arrayOfNameValuePair = parameters;
    int j = arrayOfNameValuePair.length;
    int i = 0;
    while (i < j)
    {
      NameValuePair localNameValuePair = arrayOfNameValuePair[i];
      localStringBuilder.append("; ");
      localStringBuilder.append(localNameValuePair);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}
