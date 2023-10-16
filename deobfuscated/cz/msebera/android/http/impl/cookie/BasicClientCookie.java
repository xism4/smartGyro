package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.ClientCookie;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BasicClientCookie
  implements SetCookie, ClientCookie, Cloneable, Serializable
{
  private Map<String, String> attribs;
  private String cookieComment;
  private String cookieDomain;
  private Date cookieExpiryDate;
  private String cookiePath;
  private int cookieVersion;
  private boolean isSecure;
  private final String name;
  private String value;
  
  public BasicClientCookie(String paramString1, String paramString2)
  {
    Args.notNull(paramString1, "Name");
    name = paramString1;
    attribs = new HashMap();
    value = paramString2;
  }
  
  public Object clone()
  {
    BasicClientCookie localBasicClientCookie = (BasicClientCookie)super.clone();
    attribs = new HashMap(attribs);
    return localBasicClientCookie;
  }
  
  public boolean containsAttribute(String paramString)
  {
    return attribs.containsKey(paramString);
  }
  
  public String getAttribute(String paramString)
  {
    return (String)attribs.get(paramString);
  }
  
  public String getDomain()
  {
    return cookieDomain;
  }
  
  public Date getExpiryDate()
  {
    return cookieExpiryDate;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getPath()
  {
    return cookiePath;
  }
  
  public int[] getPorts()
  {
    return null;
  }
  
  public String getValue()
  {
    return value;
  }
  
  public int getVersion()
  {
    return cookieVersion;
  }
  
  public boolean isExpired(Date paramDate)
  {
    Args.notNull(paramDate, "Date");
    Date localDate = cookieExpiryDate;
    return (localDate != null) && (localDate.getTime() <= paramDate.getTime());
  }
  
  public boolean isSecure()
  {
    return isSecure;
  }
  
  public void setAttribute(String paramString1, String paramString2)
  {
    attribs.put(paramString1, paramString2);
  }
  
  public void setComment(String paramString)
  {
    cookieComment = paramString;
  }
  
  public void setDomain(String paramString)
  {
    if (paramString != null) {
      paramString = paramString.toLowerCase(Locale.ROOT);
    } else {
      paramString = null;
    }
    cookieDomain = paramString;
  }
  
  public void setExpiryDate(Date paramDate)
  {
    cookieExpiryDate = paramDate;
  }
  
  public void setPath(String paramString)
  {
    cookiePath = paramString;
  }
  
  public void setSecure(boolean paramBoolean)
  {
    isSecure = paramBoolean;
  }
  
  public void setVersion(int paramInt)
  {
    cookieVersion = paramInt;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[version: ");
    localStringBuilder.append(Integer.toString(cookieVersion));
    localStringBuilder.append("]");
    localStringBuilder.append("[name: ");
    localStringBuilder.append(name);
    localStringBuilder.append("]");
    localStringBuilder.append("[value: ");
    localStringBuilder.append(value);
    localStringBuilder.append("]");
    localStringBuilder.append("[domain: ");
    localStringBuilder.append(cookieDomain);
    localStringBuilder.append("]");
    localStringBuilder.append("[path: ");
    localStringBuilder.append(cookiePath);
    localStringBuilder.append("]");
    localStringBuilder.append("[expiry: ");
    localStringBuilder.append(cookieExpiryDate);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
