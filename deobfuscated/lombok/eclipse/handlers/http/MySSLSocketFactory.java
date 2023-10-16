package lombok.eclipse.handlers.http;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class MySSLSocketFactory
  extends cz.msebera.android.http.conn.ssl.SSLSocketFactory
{
  final SSLContext sslContext = SSLContext.getInstance("TLS");
  
  public MySSLSocketFactory(KeyStore paramKeyStore)
  {
    super(paramKeyStore);
    paramKeyStore = new MemorizingTrustManager(this);
    sslContext.init(null, (TrustManager[])new TrustManager[] { paramKeyStore }, null);
  }
  
  public static cz.msebera.android.http.conn.ssl.SSLSocketFactory getFixedSocketFactory()
  {
    try
    {
      MySSLSocketFactory localMySSLSocketFactory = new MySSLSocketFactory(getKeystore());
      localMySSLSocketFactory.setHostnameVerifier(cz.msebera.android.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      return localMySSLSocketFactory;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return cz.msebera.android.http.conn.ssl.SSLSocketFactory.getSocketFactory();
  }
  
  public static KeyStore getKeystore()
  {
    Object localObject = null;
    try
    {
      KeyStore localKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      try
      {
        localKeyStore.load(null, null);
        return localKeyStore;
      }
      catch (Throwable localThrowable1)
      {
        localObject = localKeyStore;
      }
      localThrowable2.printStackTrace();
    }
    catch (Throwable localThrowable2) {}
    return localObject;
  }
  
  public Socket createSocket()
  {
    return sslContext.getSocketFactory().createSocket();
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
  {
    return sslContext.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
}
