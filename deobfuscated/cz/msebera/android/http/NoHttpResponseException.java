package cz.msebera.android.http;

import java.io.IOException;

public class NoHttpResponseException
  extends IOException
{
  public NoHttpResponseException(String paramString)
  {
    super(paramString);
  }
}
