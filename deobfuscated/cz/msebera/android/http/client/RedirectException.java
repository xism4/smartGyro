package cz.msebera.android.http.client;

import cz.msebera.android.http.ProtocolException;

public class RedirectException
  extends ProtocolException
{
  public RedirectException(String paramString)
  {
    super(paramString);
  }
}
