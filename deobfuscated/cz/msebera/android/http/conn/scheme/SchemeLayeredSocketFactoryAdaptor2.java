package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
class SchemeLayeredSocketFactoryAdaptor2
  implements SchemeLayeredSocketFactory
{
  private final LayeredSchemeSocketFactory factory;
  
  SchemeLayeredSocketFactoryAdaptor2(LayeredSchemeSocketFactory paramLayeredSchemeSocketFactory)
  {
    factory = paramLayeredSchemeSocketFactory;
  }
  
  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
  {
    return factory.connectSocket(paramSocket, paramInetSocketAddress1, paramInetSocketAddress2, paramHttpParams);
  }
  
  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpParams paramHttpParams)
  {
    return factory.createLayeredSocket(paramSocket, paramString, paramInt, true);
  }
  
  public Socket createSocket(HttpParams paramHttpParams)
  {
    return factory.createSocket(paramHttpParams);
  }
  
  public boolean isSecure(Socket paramSocket)
  {
    return factory.isSecure(paramSocket);
  }
}
