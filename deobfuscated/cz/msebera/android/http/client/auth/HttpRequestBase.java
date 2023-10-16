package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.message.AbstractHttpMessage;
import cz.msebera.android.http.message.BasicRequestLine;
import cz.msebera.android.http.util.HttpProtocolParams;
import java.net.URI;

public abstract class HttpRequestBase
  extends AbstractExecutionAwareRequest
  implements HttpUriRequest, Configurable
{
  private RequestConfig config;
  private URI uri;
  private ProtocolVersion version;
  
  public HttpRequestBase() {}
  
  public RequestConfig getConfig()
  {
    return config;
  }
  
  public abstract String getMethod();
  
  public ProtocolVersion getProtocolVersion()
  {
    ProtocolVersion localProtocolVersion = version;
    if (localProtocolVersion != null) {
      return localProtocolVersion;
    }
    return HttpProtocolParams.getVersion(getParams());
  }
  
  public RequestLine getRequestLine()
  {
    String str = getMethod();
    ProtocolVersion localProtocolVersion = getProtocolVersion();
    Object localObject1 = getURI();
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
    return new BasicRequestLine(str, (String)localObject2, localProtocolVersion);
  }
  
  public URI getURI()
  {
    return uri;
  }
  
  public void setConfig(RequestConfig paramRequestConfig)
  {
    config = paramRequestConfig;
  }
  
  public void setProtocolVersion(ProtocolVersion paramProtocolVersion)
  {
    version = paramProtocolVersion;
  }
  
  public void setURI(URI paramURI)
  {
    uri = paramURI;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getMethod());
    localStringBuilder.append(" ");
    localStringBuilder.append(getURI());
    localStringBuilder.append(" ");
    localStringBuilder.append(getProtocolVersion());
    return localStringBuilder.toString();
  }
}
