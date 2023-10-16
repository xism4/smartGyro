package cz.msebera.android.http.entity;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.message.BasicHeader;

public abstract class AbstractHttpEntity
  implements HttpEntity
{
  protected boolean chunked;
  protected Header contentEncoding;
  protected Header contentType;
  
  protected AbstractHttpEntity() {}
  
  public void consumeContent() {}
  
  public Header getContentEncoding()
  {
    return contentEncoding;
  }
  
  public Header getContentType()
  {
    return contentType;
  }
  
  public boolean isChunked()
  {
    return chunked;
  }
  
  public void setChunked(boolean paramBoolean)
  {
    chunked = paramBoolean;
  }
  
  public void setContentEncoding(Header paramHeader)
  {
    contentEncoding = paramHeader;
  }
  
  public void setContentType(Header paramHeader)
  {
    contentType = paramHeader;
  }
  
  public void setContentType(String paramString)
  {
    if (paramString != null) {
      paramString = new BasicHeader("Content-Type", paramString);
    } else {
      paramString = null;
    }
    setContentType(paramString);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    if (contentType != null)
    {
      localStringBuilder.append("Content-Type: ");
      localStringBuilder.append(contentType.getValue());
      localStringBuilder.append(',');
    }
    if (contentEncoding != null)
    {
      localStringBuilder.append("Content-Encoding: ");
      localStringBuilder.append(contentEncoding.getValue());
      localStringBuilder.append(',');
    }
    long l = getContentLength();
    if (l >= 0L)
    {
      localStringBuilder.append("Content-Length: ");
      localStringBuilder.append(l);
      localStringBuilder.append(',');
    }
    localStringBuilder.append("Chunked: ");
    localStringBuilder.append(chunked);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
