package cz.msebera.android.http.client;

import java.io.IOException;

public class ClientProtocolException
  extends IOException
{
  public ClientProtocolException(String paramString)
  {
    super(paramString);
  }
  
  public ClientProtocolException(Throwable paramThrowable)
  {
    initCause(paramThrowable);
  }
}
