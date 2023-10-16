package cz.msebera.android.http.auth;

import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public class NTCredentials
  implements Credentials, Serializable
{
  private final String password;
  private final NTUserPrincipal principal;
  private final String workstation;
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof NTCredentials))
    {
      paramObject = (NTCredentials)paramObject;
      if ((LangUtils.equals(principal, principal)) && (LangUtils.equals(workstation, workstation))) {
        return true;
      }
    }
    return false;
  }
  
  public String getDomain()
  {
    principal.getDomain();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public String getPassword()
  {
    return password;
  }
  
  public String getUserName()
  {
    principal.getUsername();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public Principal getUserPrincipal()
  {
    return principal;
  }
  
  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, principal), workstation);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[principal: ");
    localStringBuilder.append(principal);
    localStringBuilder.append("][workstation: ");
    localStringBuilder.append(workstation);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
