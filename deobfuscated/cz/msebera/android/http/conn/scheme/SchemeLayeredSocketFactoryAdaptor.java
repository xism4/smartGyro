package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.Socket;

@Deprecated
class SchemeLayeredSocketFactoryAdaptor
  extends SchemeSocketFactoryAdaptor
  implements SchemeLayeredSocketFactory
{
  private final LayeredSocketFactory factory;
  
  SchemeLayeredSocketFactoryAdaptor(LayeredSocketFactory paramLayeredSocketFactory)
  {
    super(paramLayeredSocketFactory);
    factory = paramLayeredSocketFactory;
  }
  
  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpParams paramHttpParams)
  {
    return factory.createSocket(paramSocket, paramString, paramInt, true);
  }
}
