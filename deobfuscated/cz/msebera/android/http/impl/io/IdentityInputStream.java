package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.InputStream;

public class IdentityInputStream
  extends InputStream
{
  private boolean closed = false;
  private final SessionInputBuffer in;
  
  public IdentityInputStream(SessionInputBuffer paramSessionInputBuffer)
  {
    Args.notNull(paramSessionInputBuffer, "Session input buffer");
    in = ((SessionInputBuffer)paramSessionInputBuffer);
  }
  
  public int available()
  {
    SessionInputBuffer localSessionInputBuffer = in;
    if ((localSessionInputBuffer instanceof BufferInfo)) {
      return ((BufferInfo)localSessionInputBuffer).length();
    }
    return 0;
  }
  
  public void close()
  {
    closed = true;
  }
  
  public int read()
  {
    if (closed) {
      return -1;
    }
    return in.read();
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (closed) {
      return -1;
    }
    return in.read(paramArrayOfByte, paramInt1, paramInt2);
  }
}
