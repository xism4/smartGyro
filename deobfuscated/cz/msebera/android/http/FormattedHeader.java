package cz.msebera.android.http;

import cz.msebera.android.http.mime.CharArrayBuffer;

public abstract interface FormattedHeader
  extends Header
{
  public abstract CharArrayBuffer getBuffer();
  
  public abstract int getValuePos();
}
