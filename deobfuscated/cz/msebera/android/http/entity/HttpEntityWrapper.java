package cz.msebera.android.http.entity;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.mime.Args;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpEntityWrapper
  implements HttpEntity
{
  protected HttpEntity wrappedEntity;
  
  public HttpEntityWrapper(HttpEntity paramHttpEntity)
  {
    Args.notNull(paramHttpEntity, "Wrapped entity");
    wrappedEntity = ((HttpEntity)paramHttpEntity);
  }
  
  public void consumeContent()
  {
    wrappedEntity.consumeContent();
  }
  
  public InputStream getContent()
  {
    return wrappedEntity.getContent();
  }
  
  public Header getContentEncoding()
  {
    return wrappedEntity.getContentEncoding();
  }
  
  public long getContentLength()
  {
    return wrappedEntity.getContentLength();
  }
  
  public Header getContentType()
  {
    return wrappedEntity.getContentType();
  }
  
  public boolean isChunked()
  {
    return wrappedEntity.isChunked();
  }
  
  public boolean isRepeatable()
  {
    return wrappedEntity.isRepeatable();
  }
  
  public boolean isStreaming()
  {
    return wrappedEntity.isStreaming();
  }
  
  public void writeTo(OutputStream paramOutputStream)
  {
    wrappedEntity.writeTo(paramOutputStream);
  }
}
