package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
class SchemeSocketFactoryAdaptor
  implements SchemeSocketFactory
{
  private final SocketFactory factory;
  
  SchemeSocketFactoryAdaptor(SocketFactory paramSocketFactory)
  {
    factory = paramSocketFactory;
  }
  
  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
  {
    String str = paramInetSocketAddress1.getHostName();
    int j = paramInetSocketAddress1.getPort();
    int i;
    if (paramInetSocketAddress2 != null)
    {
      paramInetSocketAddress1 = paramInetSocketAddress2.getAddress();
      i = paramInetSocketAddress2.getPort();
    }
    else
    {
      paramInetSocketAddress1 = null;
      i = 0;
    }
    return factory.connectSocket(paramSocket, str, j, paramInetSocketAddress1, i, paramHttpParams);
  }
  
  public Socket createSocket(HttpParams paramHttpParams)
  {
    return factory.createSocket();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    SocketFactory localSocketFactory;
    if ((paramObject instanceof SchemeSocketFactoryAdaptor))
    {
      localSocketFactory = factory;
      paramObject = factory;
    }
    for (;;)
    {
      return localSocketFactory.equals(paramObject);
      localSocketFactory = factory;
    }
  }
  
  public int hashCode()
  {
    return factory.hashCode();
  }
  
  public boolean isSecure(Socket paramSocket)
  {
    return factory.isSecure(paramSocket);
  }
}
