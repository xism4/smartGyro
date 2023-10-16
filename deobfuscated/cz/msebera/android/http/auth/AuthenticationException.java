package cz.msebera.android.http.auth;

import cz.msebera.android.http.ProtocolException;

public class AuthenticationException
  extends ProtocolException
{
  public AuthenticationException(String paramString)
  {
    super(paramString);
  }
  
  public AuthenticationException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}
