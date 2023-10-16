package cz.msebera.android.http.entity;

import cz.msebera.android.http.impl.io.EmptyInputStream;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import java.io.InputStream;
import java.io.OutputStream;

public class BasicHttpEntity
  extends AbstractHttpEntity
{
  private InputStream content;
  private long length = -1L;
  
  public BasicHttpEntity() {}
  
  public InputStream getContent()
  {
    boolean bool;
    if (content != null) {
      bool = true;
    } else {
      bool = false;
    }
    Asserts.check(bool, "Content has not been provided");
    return content;
  }
  
  public long getContentLength()
  {
    return length;
  }
  
  public boolean isRepeatable()
  {
    return false;
  }
  
  public boolean isStreaming()
  {
    InputStream localInputStream = content;
    return (localInputStream != null) && (localInputStream != EmptyInputStream.INSTANCE);
  }
  
  public void setContent(InputStream paramInputStream)
  {
    content = paramInputStream;
  }
  
  public void setContentLength(long paramLong)
  {
    length = paramLong;
  }
  
  public void writeTo(OutputStream paramOutputStream)
  {
    Args.notNull(paramOutputStream, "Output stream");
    InputStream localInputStream = getContent();
    try
    {
      byte[] arrayOfByte = new byte['?'];
      for (;;)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      localInputStream.close();
      return;
    }
    catch (Throwable paramOutputStream)
    {
      localInputStream.close();
      throw paramOutputStream;
    }
  }
}
