package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.OutputStream;

public class ContentLengthOutputStream
  extends OutputStream
{
  private boolean closed = false;
  private final long contentLength;
  private final SessionOutputBuffer out;
  private long total = 0L;
  
  public ContentLengthOutputStream(SessionOutputBuffer paramSessionOutputBuffer, long paramLong)
  {
    Args.notNull(paramSessionOutputBuffer, "Session output buffer");
    out = ((SessionOutputBuffer)paramSessionOutputBuffer);
    Args.notNegative(paramLong, "Content length");
    contentLength = paramLong;
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
      if (total < contentLength)
      {
        out.write(paramInt);
        total += 1L;
      }
    }
    else {
      throw new IOException("Attempted write to closed stream.");
    }
  }
  
  public void write(byte[] paramArrayOfByte)
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!closed)
    {
      long l1 = total;
      long l2 = contentLength;
      if (l1 < l2)
      {
        l1 = l2 - l1;
        int i = paramInt2;
        if (paramInt2 > l1) {
          i = (int)l1;
        }
        out.write(paramArrayOfByte, paramInt1, i);
        total += i;
      }
    }
    else
    {
      throw new IOException("Attempted write to closed stream.");
    }
  }
}
