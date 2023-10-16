package cz.msebera.android.http.cookie;

import cz.msebera.android.http.ProtocolException;

public class MalformedCookieException
  extends ProtocolException
{
  public MalformedCookieException(String paramString)
  {
    super(paramString);
  }
}
