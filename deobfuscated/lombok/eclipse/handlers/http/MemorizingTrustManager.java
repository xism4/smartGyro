package lombok.eclipse.handlers.http;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class MemorizingTrustManager
  implements X509TrustManager
{
  MemorizingTrustManager(MySSLSocketFactory paramMySSLSocketFactory) {}
  
  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public X509Certificate[] getAcceptedIssuers()
  {
    return null;
  }
}
