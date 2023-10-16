package cz.msebera.android.http.io;

import cz.msebera.android.http.mime.CharArrayBuffer;

public abstract interface SessionInputBuffer
{
  public abstract HttpTransportMetrics getMetrics();
  
  public abstract boolean isDataAvailable(int paramInt);
  
  public abstract int read();
  
  public abstract int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  public abstract int readLine(CharArrayBuffer paramCharArrayBuffer);
}
