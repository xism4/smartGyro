package cz.msebera.android.http.impl.entity;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.entity.ContentLengthStrategy;
import cz.msebera.android.http.impl.io.ChunkedOutputStream;
import cz.msebera.android.http.impl.io.ContentLengthOutputStream;
import cz.msebera.android.http.impl.io.IdentityOutputStream;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.OutputStream;

@Deprecated
public class EntitySerializer
{
  private final ContentLengthStrategy lenStrategy;
  
  public EntitySerializer(ContentLengthStrategy paramContentLengthStrategy)
  {
    Args.notNull(paramContentLengthStrategy, "Content length strategy");
    lenStrategy = ((ContentLengthStrategy)paramContentLengthStrategy);
  }
  
  protected OutputStream doSerialize(SessionOutputBuffer paramSessionOutputBuffer, HttpMessage paramHttpMessage)
  {
    long l = lenStrategy.determineLength(paramHttpMessage);
    if (l == -2L) {
      return new ChunkedOutputStream(paramSessionOutputBuffer);
    }
    if (l == -1L) {
      return new IdentityOutputStream(paramSessionOutputBuffer);
    }
    return new ContentLengthOutputStream(paramSessionOutputBuffer, l);
  }
  
  public void serialize(SessionOutputBuffer paramSessionOutputBuffer, HttpMessage paramHttpMessage, HttpEntity paramHttpEntity)
  {
    Args.notNull(paramSessionOutputBuffer, "Session output buffer");
    Args.notNull(paramHttpMessage, "HTTP message");
    Args.notNull(paramHttpEntity, "HTTP entity");
    paramSessionOutputBuffer = doSerialize(paramSessionOutputBuffer, paramHttpMessage);
    paramHttpEntity.writeTo(paramSessionOutputBuffer);
    paramSessionOutputBuffer.close();
  }
}
