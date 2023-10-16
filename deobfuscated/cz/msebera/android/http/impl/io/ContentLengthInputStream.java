package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.InputStream;

public class ContentLengthInputStream
  extends InputStream
{
  private boolean closed = false;
  private final long contentLength;
  private SessionInputBuffer in = null;
  private long pos = 0L;
  
  public ContentLengthInputStream(SessionInputBuffer paramSessionInputBuffer, long paramLong)
  {
    Args.notNull(paramSessionInputBuffer, "Session input buffer");
    in = ((SessionInputBuffer)paramSessionInputBuffer);
    Args.notNegative(paramLong, "Content length");
    contentLength = paramLong;
  }
  
  public int available()
  {
    SessionInputBuffer localSessionInputBuffer = in;
    if ((localSessionInputBuffer instanceof BufferInfo)) {
      return Math.min(((BufferInfo)localSessionInputBuffer).length(), (int)(contentLength - pos));
    }
    return 0;
  }
  
  public void close()
  {
    if (!closed) {
      try
      {
        long l1 = pos;
        long l2 = contentLength;
        if (l1 < l2)
        {
          byte[] arrayOfByte = new byte['?'];
          int i;
          do
          {
            i = read(arrayOfByte);
          } while (i >= 0);
        }
        closed = true;
        return;
      }
      catch (Throwable localThrowable)
      {
        closed = true;
        throw localThrowable;
      }
    }
  }
  
  public int read()
  {
    if (!closed)
    {
      if (pos >= contentLength) {
        return -1;
      }
      int i = in.read();
      if (i == -1)
      {
        if (pos >= contentLength) {
          return i;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Premature end of Content-Length delimited message body (expected: ");
        localStringBuilder.append(contentLength);
        localStringBuilder.append("; received: ");
        localStringBuilder.append(pos);
        throw new cz.msebera.android.http.IOException(localStringBuilder.toString());
      }
      pos += 1L;
      return i;
    }
    throw new java.io.IOException("Attempted read from closed stream.");
  }
  
  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!closed)
    {
      long l1 = pos;
      long l2 = contentLength;
      if (l1 >= l2) {
        return -1;
      }
      int i = paramInt2;
      if (paramInt2 + l1 > l2) {
        i = (int)(l2 - l1);
      }
      paramInt1 = in.read(paramArrayOfByte, paramInt1, i);
      if ((paramInt1 == -1) && (pos < contentLength))
      {
        paramArrayOfByte = new StringBuilder();
        paramArrayOfByte.append("Premature end of Content-Length delimited message body (expected: ");
        paramArrayOfByte.append(contentLength);
        paramArrayOfByte.append("; received: ");
        paramArrayOfByte.append(pos);
        throw new cz.msebera.android.http.IOException(paramArrayOfByte.toString());
      }
      if (paramInt1 > 0)
      {
        pos += paramInt1;
        return paramInt1;
      }
    }
    else
    {
      throw new java.io.IOException("Attempted read from closed stream.");
    }
    return paramInt1;
  }
  
  public long skip(long paramLong)
  {
    if (paramLong <= 0L) {
      return 0L;
    }
    byte[] arrayOfByte = new byte['?'];
    paramLong = Math.min(paramLong, contentLength - pos);
    long l1 = 0L;
    while (paramLong > 0L)
    {
      int i = read(arrayOfByte, 0, (int)Math.min(2048L, paramLong));
      if (i == -1) {
        return l1;
      }
      long l2 = i;
      l1 += l2;
      paramLong -= l2;
    }
    return l1;
  }
}
