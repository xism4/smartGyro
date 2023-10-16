package cz.msebera.android.http.impl.entity;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.entity.ContentLengthStrategy;
import cz.msebera.android.http.mime.Args;

public class StrictContentLengthStrategy
  implements ContentLengthStrategy
{
  public static final StrictContentLengthStrategy INSTANCE = new StrictContentLengthStrategy();
  private final int implicitLen;
  
  public StrictContentLengthStrategy()
  {
    this(-1);
  }
  
  public StrictContentLengthStrategy(int paramInt)
  {
    implicitLen = paramInt;
  }
  
  public long determineLength(HttpMessage paramHttpMessage)
  {
    Args.notNull(paramHttpMessage, "HTTP message");
    Object localObject = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    if (localObject != null)
    {
      localObject = ((Header)localObject).getValue();
      if ("chunked".equalsIgnoreCase((String)localObject))
      {
        if (!paramHttpMessage.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0)) {
          return -2L;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Chunked transfer encoding not allowed for ");
        ((StringBuilder)localObject).append(paramHttpMessage.getProtocolVersion());
        throw new ProtocolException(((StringBuilder)localObject).toString());
      }
      if ("identity".equalsIgnoreCase((String)localObject)) {
        return -1L;
      }
      paramHttpMessage = new StringBuilder();
      paramHttpMessage.append("Unsupported transfer encoding: ");
      paramHttpMessage.append((String)localObject);
      throw new ProtocolException(paramHttpMessage.toString());
    }
    paramHttpMessage = paramHttpMessage.getFirstHeader("Content-Length");
    if (paramHttpMessage != null) {
      paramHttpMessage = paramHttpMessage.getValue();
    }
    try
    {
      long l = Long.parseLong(paramHttpMessage);
      if (l >= 0L) {
        return l;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Negative content length: ");
      ((StringBuilder)localObject).append(paramHttpMessage);
      localObject = new ProtocolException(((StringBuilder)localObject).toString());
      throw ((Throwable)localObject);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Invalid content length: ");
    ((StringBuilder)localObject).append(paramHttpMessage);
    throw new ProtocolException(((StringBuilder)localObject).toString());
    return implicitLen;
  }
}
