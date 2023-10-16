package cz.msebera.android.http.impl.io;

import java.io.InputStream;

public final class EmptyInputStream
  extends InputStream
{
  public static final EmptyInputStream INSTANCE = new EmptyInputStream();
  
  private EmptyInputStream() {}
  
  public int available()
  {
    return 0;
  }
  
  public void close() {}
  
  public void mark(int paramInt) {}
  
  public boolean markSupported()
  {
    return true;
  }
  
  public int read()
  {
    return -1;
  }
  
  public int read(byte[] paramArrayOfByte)
  {
    return -1;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return -1;
  }
  
  public void reset() {}
  
  public long skip(long paramLong)
  {
    return 0L;
  }
}
