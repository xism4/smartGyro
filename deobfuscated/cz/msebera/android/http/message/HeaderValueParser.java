package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderElement;

public abstract interface HeaderValueParser
{
  public abstract HeaderElement[] parseElements(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1);
  
  public abstract HeaderElement parseHeaderElement(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1);
}
