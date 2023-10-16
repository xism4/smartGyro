package cz.msebera.android.http.entity;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.EntityUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedHttpEntity
  extends HttpEntityWrapper
{
  private final byte[] buffer;
  
  public BufferedHttpEntity(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
    if ((paramHttpEntity.isRepeatable()) && (paramHttpEntity.getContentLength() >= 0L))
    {
      buffer = null;
      return;
    }
    buffer = EntityUtils.toByteArray(paramHttpEntity);
  }
  
  public InputStream getContent()
  {
    byte[] arrayOfByte = buffer;
    if (arrayOfByte != null) {
      return new ByteArrayInputStream(arrayOfByte);
    }
    return super.getContent();
  }
  
  public long getContentLength()
  {
    byte[] arrayOfByte = buffer;
    if (arrayOfByte != null) {
      return arrayOfByte.length;
    }
    return super.getContentLength();
  }
  
  public boolean isChunked()
  {
    return (buffer == null) && (super.isChunked());
  }
  
  public boolean isRepeatable()
  {
    return true;
  }
  
  public boolean isStreaming()
  {
    return (buffer == null) && (super.isStreaming());
  }
  
  public void writeTo(OutputStream paramOutputStream)
  {
    Args.notNull(paramOutputStream, "Output stream");
    byte[] arrayOfByte = buffer;
    if (arrayOfByte != null)
    {
      paramOutputStream.write(arrayOfByte);
      return;
    }
    super.writeTo(paramOutputStream);
  }
}
