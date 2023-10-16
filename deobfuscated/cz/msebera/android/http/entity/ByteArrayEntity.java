package cz.msebera.android.http.entity;

import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.mime.Args;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class ByteArrayEntity
  extends AbstractHttpEntity
  implements Cloneable
{
  protected final byte[] content;
  
  public ByteArrayEntity(String paramString, ContentType paramContentType)
  {
    Args.notNull(paramString, "Source string");
    Charset localCharset1;
    if (paramContentType != null) {
      localCharset1 = paramContentType.getCharset();
    } else {
      localCharset1 = null;
    }
    Charset localCharset2 = localCharset1;
    if (localCharset1 == null) {
      localCharset2 = HTTP.DEF_CONTENT_CHARSET;
    }
    content = paramString.getBytes(localCharset2);
    if (paramContentType != null) {
      setContentType(paramContentType.toString());
    }
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public InputStream getContent()
  {
    return new ByteArrayInputStream(content);
  }
  
  public long getContentLength()
  {
    return content.length;
  }
  
  public boolean isRepeatable()
  {
    return true;
  }
  
  public boolean isStreaming()
  {
    return false;
  }
  
  public void writeTo(OutputStream paramOutputStream)
  {
    Args.notNull(paramOutputStream, "Output stream");
    paramOutputStream.write(content);
    paramOutputStream.flush();
  }
}
