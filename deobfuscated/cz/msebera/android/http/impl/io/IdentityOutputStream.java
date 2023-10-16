package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.OutputStream;

public class IdentityOutputStream
  extends OutputStream
{
  private boolean closed = false;
  private final SessionOutputBuffer out;
  
  public IdentityOutputStream(SessionOutputBuffer paramSessionOutputBuffer)
  {
    Args.notNull(paramSessionOutputBuffer, "Session output buffer");
    out = ((SessionOutputBuffer)paramSessionOutputBuffer);
  }
  
  public void close()
  {
    if (!closed)
    {
      closed = true;
      out.flush();
    }
  }
  
  public void flush()
  {
    out.flush();
  }
  
  public void write(int paramInt)
  {
    if (!closed)
    {
      out.write(paramInt);
      return;
    }
    throw new IOException("Attempted write to closed stream.");
  }
  
  public void write(byte[] paramArrayOfByte)
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!closed)
    {
      out.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    throw new IOException("Attempted write to closed stream.");
  }
}
