package cz.msebera.android.http.auth;

import c.a.a.a.a.a;
import cz.msebera.android.http.mime.Args;
import java.util.Queue;

public class AuthState
{
  private Queue<a> authOptions;
  private AuthScheme authScheme;
  private AuthScope authScope;
  private Credentials credentials;
  private AuthProtocolState state = AuthProtocolState.UNCHALLENGED;
  
  public AuthState() {}
  
  public Queue getAuthOptions()
  {
    return authOptions;
  }
  
  public AuthScheme getAuthScheme()
  {
    return authScheme;
  }
  
  public Credentials getCredentials()
  {
    return credentials;
  }
  
  public AuthProtocolState getState()
  {
    return state;
  }
  
  public void reset()
  {
    state = AuthProtocolState.UNCHALLENGED;
    authOptions = null;
    authScheme = null;
    authScope = null;
    credentials = null;
  }
  
  public void setAuthScheme(AuthScheme paramAuthScheme)
  {
    if (paramAuthScheme == null)
    {
      reset();
      return;
    }
    authScheme = paramAuthScheme;
  }
  
  public void setState(AuthProtocolState paramAuthProtocolState)
  {
    if (paramAuthProtocolState == null) {
      paramAuthProtocolState = AuthProtocolState.UNCHALLENGED;
    }
    state = paramAuthProtocolState;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("state:");
    localStringBuilder.append(state);
    localStringBuilder.append(";");
    if (authScheme != null)
    {
      localStringBuilder.append("auth scheme:");
      localStringBuilder.append(authScheme.getSchemeName());
      localStringBuilder.append(";");
    }
    if (credentials != null) {
      localStringBuilder.append("credentials present");
    }
    return localStringBuilder.toString();
  }
  
  public void update(AuthScheme paramAuthScheme, Credentials paramCredentials)
  {
    Args.notNull(paramAuthScheme, "Auth scheme");
    Args.notNull(paramCredentials, "Credentials");
    authScheme = paramAuthScheme;
    credentials = paramCredentials;
    authOptions = null;
  }
  
  public void update(Credentials paramCredentials)
  {
    credentials = paramCredentials;
  }
  
  public void update(Queue paramQueue)
  {
    Args.notEmpty(paramQueue, "Queue of auth options");
    authOptions = paramQueue;
    authScheme = null;
    credentials = null;
  }
}
