package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Deprecated
public class HttpInetSocketAddress
  extends InetSocketAddress
{
  private final HttpHost httphost;
  
  public HttpInetSocketAddress(HttpHost paramHttpHost, InetAddress paramInetAddress, int paramInt)
  {
    super(paramInetAddress, paramInt);
    Args.notNull(paramHttpHost, "HTTP host");
    httphost = paramHttpHost;
  }
  
  public HttpHost getHttpHost()
  {
    return httphost;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(httphost.getHostName());
    localStringBuilder.append(":");
    localStringBuilder.append(getPort());
    return localStringBuilder.toString();
  }
}
