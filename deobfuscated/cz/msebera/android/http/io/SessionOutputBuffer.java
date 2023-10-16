package cz.msebera.android.http.io;

import cz.msebera.android.http.mime.CharArrayBuffer;

public abstract interface SessionOutputBuffer
{
  public abstract void flush();
  
  public abstract HttpTransportMetrics getMetrics();
  
  public abstract void write(int paramInt);
  
  public abstract void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  public abstract void writeLine(CharArrayBuffer paramCharArrayBuffer);
  
  public abstract void writeLine(String paramString);
}
