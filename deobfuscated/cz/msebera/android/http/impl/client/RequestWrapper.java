package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.message.AbstractHttpMessage;
import cz.msebera.android.http.message.BasicRequestLine;
import cz.msebera.android.http.message.HeaderGroup;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpProtocolParams;
import java.net.URI;
import java.net.URISyntaxException;

@Deprecated
public class RequestWrapper
  extends AbstractHttpMessage
  implements HttpUriRequest
{
  private int execCount;
  private String method;
  private final HttpRequest original;
  private URI uri;
  private ProtocolVersion version;
  
  public RequestWrapper(HttpRequest paramHttpRequest)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    original = paramHttpRequest;
    setParams(paramHttpRequest.getParams());
    setHeaders(paramHttpRequest.getAllHeaders());
    if ((paramHttpRequest instanceof HttpUriRequest))
    {
      paramHttpRequest = (HttpUriRequest)paramHttpRequest;
      uri = paramHttpRequest.getURI();
      method = paramHttpRequest.getMethod();
      paramHttpRequest = null;
    }
    for (;;)
    {
      version = paramHttpRequest;
      break label124;
      RequestLine localRequestLine = paramHttpRequest.getRequestLine();
      try
      {
        localObject = new URI(localRequestLine.getUri());
        uri = ((URI)localObject);
        method = localRequestLine.getMethod();
        paramHttpRequest = paramHttpRequest.getProtocolVersion();
      }
      catch (URISyntaxException paramHttpRequest)
      {
        label124:
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Invalid request URI: ");
        ((StringBuilder)localObject).append(localRequestLine.getUri());
        paramHttpRequest = new ProtocolException(((StringBuilder)localObject).toString(), paramHttpRequest);
        throw paramHttpRequest;
      }
    }
    execCount = 0;
  }
  
  public void abort()
  {
    throw new UnsupportedOperationException();
  }
  
  public int getExecCount()
  {
    return execCount;
  }
  
  public String getMethod()
  {
    return method;
  }
  
  public HttpRequest getOriginal()
  {
    return original;
  }
  
  public ProtocolVersion getProtocolVersion()
  {
    if (version == null) {
      version = HttpProtocolParams.getVersion(getParams());
    }
    return version;
  }
  
  public RequestLine getRequestLine()
  {
    ProtocolVersion localProtocolVersion = getProtocolVersion();
    Object localObject1 = uri;
    if (localObject1 != null) {
      localObject1 = ((URI)localObject1).toASCIIString();
    } else {
      localObject1 = null;
    }
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!((String)localObject1).isEmpty()) {}
    }
    else
    {
      localObject2 = "/";
    }
    return new BasicRequestLine(getMethod(), (String)localObject2, localProtocolVersion);
  }
  
  public URI getURI()
  {
    return uri;
  }
  
  public void incrementExecCount()
  {
    execCount += 1;
  }
  
  public boolean isAborted()
  {
    return false;
  }
  
  public boolean isRepeatable()
  {
    return true;
  }
  
  public void resetHeaders()
  {
    headergroup.clear();
    setHeaders(original.getAllHeaders());
  }
  
  public void setURI(URI paramURI)
  {
    uri = paramURI;
  }
}
