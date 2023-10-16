package cz.msebera.android.http.auth;

import cz.msebera.android.http.mime.Args;

public final class AuthOption
{
  private final AuthScheme authScheme;
  private final Credentials creds;
  
  public AuthOption(AuthScheme paramAuthScheme, Credentials paramCredentials)
  {
    Args.notNull(paramAuthScheme, "Auth scheme");
    Args.notNull(paramCredentials, "User credentials");
    authScheme = paramAuthScheme;
    creds = paramCredentials;
  }
  
  public AuthScheme getAuthScheme()
  {
    return authScheme;
  }
  
  public Credentials getCredentials()
  {
    return creds;
  }
  
  public String toString()
  {
    return authScheme.toString();
  }
}
