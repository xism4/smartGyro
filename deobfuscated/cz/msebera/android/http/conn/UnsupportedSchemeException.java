package cz.msebera.android.http.conn;

import java.io.IOException;

public class UnsupportedSchemeException
  extends IOException
{
  public UnsupportedSchemeException(String paramString)
  {
    super(paramString);
  }
}