package cz.msebera.android.http.conn.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;

@Deprecated
public class SSLContexts
{
  public static SSLContext createDefault()
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      localSSLContext.init(null, null, null);
      return localSSLContext;
    }
    catch (KeyManagementException localKeyManagementException)
    {
      throw new SSLInitializationException(localKeyManagementException.getMessage(), localKeyManagementException);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new SSLInitializationException(localNoSuchAlgorithmException.getMessage(), localNoSuchAlgorithmException);
    }
  }
  
  public static SSLContextBuilder custom()
  {
    return new SSLContextBuilder();
  }
}
