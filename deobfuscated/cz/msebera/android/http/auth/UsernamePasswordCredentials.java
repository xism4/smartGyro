package cz.msebera.android.http.auth;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public class UsernamePasswordCredentials
  implements Credentials, Serializable
{
  private final String password;
  private final BasicUserPrincipal principal;
  
  public UsernamePasswordCredentials(String paramString)
  {
    Args.notNull(paramString, "Username:password string");
    int i = paramString.indexOf(':');
    if (i >= 0)
    {
      principal = new BasicUserPrincipal(paramString.substring(0, i));
      paramString = paramString.substring(i + 1);
    }
    else
    {
      principal = new BasicUserPrincipal(paramString);
      paramString = null;
    }
    password = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof UsernamePasswordCredentials))
    {
      paramObject = (UsernamePasswordCredentials)paramObject;
      if (LangUtils.equals(principal, principal)) {
        return true;
      }
    }
    return false;
  }
  
  public String getPassword()
  {
    return password;
  }
  
  public Principal getUserPrincipal()
  {
    return principal;
  }
  
  public int hashCode()
  {
    return principal.hashCode();
  }
  
  public String toString()
  {
    return principal.toString();
  }
}
