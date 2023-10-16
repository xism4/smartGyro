package cz.msebera.android.http.client;

import cz.msebera.android.http.ProtocolException;

public class NonRepeatableRequestException
  extends ProtocolException
{
  public NonRepeatableRequestException(String paramString)
  {
    super(paramString);
  }
  
  public NonRepeatableRequestException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}
