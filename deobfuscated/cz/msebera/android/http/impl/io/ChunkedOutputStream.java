package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.SessionOutputBuffer;
import java.io.IOException;
import java.io.OutputStream;

public class ChunkedOutputStream
  extends OutputStream
{
  private final byte[] cache;
  private int cachePosition = 0;
  private boolean closed = false;
  private final SessionOutputBuffer out;
  private boolean wroteLastChunk = false;
  
  public ChunkedOutputStream(int paramInt, SessionOutputBuffer paramSessionOutputBuffer)
  {
    cache = new byte[paramInt];
    out = paramSessionOutputBuffer;
  }
  
  public ChunkedOutputStream(SessionOutputBuffer paramSessionOutputBuffer)
  {
    this(2048, paramSessionOutputBuffer);
  }
  
  public void close()
  {
    if (!closed)
    {
      closed = true;
      finish();
      out.flush();
    }
  }
  
  public void finish()
  {
    if (!wroteLastChunk)
    {
      flushCache();
      writeClosingChunk();
      wroteLastChunk = true;
    }
  }
  
  public void flush()
  {
    flushCache();
    out.flush();
  }
  
  protected void flushCache()
  {
    int i = cachePosition;
    if (i > 0)
    {
      out.writeLine(Integer.toHexString(i));
      out.write(cache, 0, cachePosition);
      out.writeLine("");
      cachePosition = 0;
    }
  }
  
  protected void flushCacheWithAppend(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    out.writeLine(Integer.toHexString(cachePosition + paramInt2));
    out.write(cache, 0, cachePosition);
    out.write(paramArrayOfByte, paramInt1, paramInt2);
    out.writeLine("");
    cachePosition = 0;
  }
  
  public void write(int paramInt)
  {
    if (!closed)
    {
      byte[] arrayOfByte = cache;
      int i = cachePosition;
      arrayOfByte[i] = ((byte)paramInt);
      cachePosition = (i + 1);
      if (cachePosition == arrayOfByte.length) {
        flushCache();
      }
    }
    else
    {
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
      byte[] arrayOfByte = cache;
      int i = arrayOfByte.length;
      int j = cachePosition;
      if (paramInt2 >= i - j)
      {
        flushCacheWithAppend(paramArrayOfByte, paramInt1, paramInt2);
        return;
      }
      System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, j, paramInt2);
      cachePosition += paramInt2;
      return;
    }
    throw new IOException("Attempted write to closed stream.");
  }
  
  protected void writeClosingChunk()
  {
    out.writeLine("0");
    out.writeLine("");
  }
}
