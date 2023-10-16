package cz.msebera.android.http;

import java.io.InputStream;
import java.io.OutputStream;

public abstract interface HttpEntity
{
  public abstract void consumeContent();
  
  public abstract InputStream getContent();
  
  public abstract Header getContentEncoding();
  
  public abstract long getContentLength();
  
  public abstract Header getContentType();
  
  public abstract boolean isChunked();
  
  public abstract boolean isRepeatable();
  
  public abstract boolean isStreaming();
  
  public abstract void writeTo(OutputStream paramOutputStream);
}
