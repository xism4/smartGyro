package cz.msebera.android.http.auth;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;

public abstract interface AuthScheme
{
  public abstract Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest);
  
  public abstract String getRealm();
  
  public abstract String getSchemeName();
  
  public abstract boolean isComplete();
  
  public abstract boolean isConnectionBased();
  
  public abstract void processChallenge(Header paramHeader);
}
