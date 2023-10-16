package cz.msebera.android.http.impl.entity;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.entity.ContentLengthStrategy;
import cz.msebera.android.http.mime.Args;

public class LaxContentLengthStrategy
  implements ContentLengthStrategy
{
  public static final LaxContentLengthStrategy INSTANCE = new LaxContentLengthStrategy();
  private final int implicitLen;
  
  public LaxContentLengthStrategy()
  {
    this(-1);
  }
  
  public LaxContentLengthStrategy(int paramInt)
  {
    implicitLen = paramInt;
  }
  
  public long determineLength(HttpMessage paramHttpMessage)
  {
    Args.notNull(paramHttpMessage, "HTTP message");
    Header localHeader = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    int i;
    if (localHeader != null) {
      try
      {
        paramHttpMessage = localHeader.getElements();
        i = paramHttpMessage.length;
        if ("identity".equalsIgnoreCase(localHeader.getValue())) {
          return -1L;
        }
        if (i > 0)
        {
          if (!"chunked".equalsIgnoreCase(paramHttpMessage[(i - 1)].getName())) {
            break label204;
          }
          return -2L;
        }
        return -1L;
      }
      catch (ParseException paramHttpMessage)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Invalid Transfer-Encoding header value: ");
        localStringBuilder.append(localHeader);
        throw new ProtocolException(localStringBuilder.toString(), paramHttpMessage);
      }
    }
    if (paramHttpMessage.getFirstHeader("Content-Length") != null)
    {
      paramHttpMessage = paramHttpMessage.getHeaders("Content-Length");
      i = paramHttpMessage.length - 1;
      while (i >= 0)
      {
        localHeader = paramHttpMessage[i];
        try
        {
          l = Long.parseLong(localHeader.getValue());
        }
        catch (NumberFormatException localNumberFormatException)
        {
          long l;
          for (;;) {}
        }
        i -= 1;
      }
      l = -1L;
      if (l >= 0L) {
        return l;
      }
      return -1L;
    }
    return implicitLen;
    label204:
    return -1L;
  }
}
