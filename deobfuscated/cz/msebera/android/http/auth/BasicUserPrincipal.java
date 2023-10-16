package cz.msebera.android.http.auth;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public final class BasicUserPrincipal
  implements Principal, Serializable
{
  private final String username;
  
  public BasicUserPrincipal(String paramString)
  {
    Args.notNull(paramString, "User name");
    username = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof BasicUserPrincipal))
    {
      paramObject = (BasicUserPrincipal)paramObject;
      if (LangUtils.equals(username, username)) {
        return true;
      }
    }
    return false;
  }
  
  public String getName()
  {
    return username;
  }
  
  public int hashCode()
  {
    return LangUtils.hashCode(17, username);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[principal: ");
    localStringBuilder.append(username);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
