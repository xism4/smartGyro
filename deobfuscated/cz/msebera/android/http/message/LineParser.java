package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.StatusLine;

public abstract interface LineParser
{
  public abstract boolean hasProtocolVersion(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1);
  
  public abstract Header parseHeader(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer);
  
  public abstract StatusLine parseStatusLine(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1);
}
