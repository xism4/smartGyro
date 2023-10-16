package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.conn.ConnectTimeoutException;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Deprecated
public class PlainSocketFactory
  implements SocketFactory, SchemeSocketFactory
{
  private final HostNameResolver nameResolver = null;
  
  public PlainSocketFactory() {}
  
  public static PlainSocketFactory getSocketFactory()
  {
    return new PlainSocketFactory();
  }
  
  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
  {
    if ((paramInetAddress == null) && (paramInt2 <= 0))
    {
      paramInetAddress = null;
    }
    else
    {
      if (paramInt2 <= 0) {
        paramInt2 = 0;
      }
      paramInetAddress = new InetSocketAddress(paramInetAddress, paramInt2);
    }
    HostNameResolver localHostNameResolver = nameResolver;
    if (localHostNameResolver != null) {
      paramString = localHostNameResolver.resolve(paramString);
    } else {
      paramString = InetAddress.getByName(paramString);
    }
    return connectSocket(paramSocket, new InetSocketAddress(paramString, paramInt1), paramInetAddress, paramHttpParams);
  }
  
  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
  {
    Args.notNull(paramInetSocketAddress1, "Remote address");
    Args.notNull(paramHttpParams, "HTTP parameters");
    Socket localSocket = paramSocket;
    if (paramSocket == null) {
      localSocket = createSocket();
    }
    if (paramInetSocketAddress2 != null)
    {
      localSocket.setReuseAddress(HttpConnectionParams.getSoReuseaddr(paramHttpParams));
      localSocket.bind(paramInetSocketAddress2);
    }
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    int j = HttpConnectionParams.getSoTimeout(paramHttpParams);
    try
    {
      localSocket.setSoTimeout(j);
      localSocket.connect(paramInetSocketAddress1, i);
      return localSocket;
    }
    catch (SocketTimeoutException paramSocket)
    {
      for (;;) {}
    }
    paramSocket = new StringBuilder();
    paramSocket.append("Connect to ");
    paramSocket.append(paramInetSocketAddress1);
    paramSocket.append(" timed out");
    throw new ConnectTimeoutException(paramSocket.toString());
  }
  
  public Socket createSocket()
  {
    return new Socket();
  }
  
  public Socket createSocket(HttpParams paramHttpParams)
  {
    return new Socket();
  }
  
  public final boolean isSecure(Socket paramSocket)
  {
    return false;
  }
}
