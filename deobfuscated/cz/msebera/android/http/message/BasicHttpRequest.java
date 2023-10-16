package cz.msebera.android.http.message;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.Args;

public class BasicHttpRequest
  extends AbstractHttpMessage
  implements HttpRequest
{
  private final String method;
  private RequestLine requestline;
  private final String uri;
  
  public BasicHttpRequest(RequestLine paramRequestLine)
  {
    Args.notNull(paramRequestLine, "Request line");
    requestline = ((RequestLine)paramRequestLine);
    method = paramRequestLine.getMethod();
    uri = paramRequestLine.getUri();
  }
  
  public BasicHttpRequest(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    this(new BasicRequestLine(paramString1, paramString2, paramProtocolVersion));
  }
  
  public ProtocolVersion getProtocolVersion()
  {
    return getRequestLine().getProtocolVersion();
  }
  
  public RequestLine getRequestLine()
  {
    if (requestline == null) {
      requestline = new BasicRequestLine(method, uri, HttpVersion.HTTP_1_1);
    }
    return requestline;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(method);
    localStringBuilder.append(' ');
    localStringBuilder.append(uri);
    localStringBuilder.append(' ');
    localStringBuilder.append(headergroup);
    return localStringBuilder.toString();
  }
}
