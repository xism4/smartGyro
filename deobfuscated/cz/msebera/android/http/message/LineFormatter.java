package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.CharArrayBuffer;

public abstract interface LineFormatter
{
  public abstract CharArrayBuffer formatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader);
  
  public abstract CharArrayBuffer formatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine);
}
