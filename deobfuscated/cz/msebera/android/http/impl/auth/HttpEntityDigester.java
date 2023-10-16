package cz.msebera.android.http.impl.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

class HttpEntityDigester
  extends OutputStream
{
  private boolean closed;
  private byte[] digest;
  private final MessageDigest digester;
  
  HttpEntityDigester(MessageDigest paramMessageDigest)
  {
    digester = paramMessageDigest;
    digester.reset();
  }
  
  public void close()
  {
    if (closed) {
      return;
    }
    closed = true;
    digest = digester.digest();
    super.close();
  }
  
  public byte[] getDigest()
  {
    return digest;
  }
  
  public void write(int paramInt)
  {
    if (!closed)
    {
      digester.update((byte)paramInt);
      return;
    }
    throw new IOException("Stream has been already closed");
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!closed)
    {
      digester.update(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    throw new IOException("Stream has been already closed");
  }
}
