package cz.msebera.android.http.conn.ssl;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

@Deprecated
public class SSLContextBuilder
{
  private Set<KeyManager> keymanagers = new LinkedHashSet();
  private String protocol;
  private SecureRandom secureRandom;
  private Set<TrustManager> trustmanagers = new LinkedHashSet();
  
  public SSLContextBuilder() {}
  
  public SSLContext build()
  {
    Object localObject1 = protocol;
    if (localObject1 == null) {
      localObject1 = "TLS";
    }
    SSLContext localSSLContext = SSLContext.getInstance((String)localObject1);
    boolean bool = keymanagers.isEmpty();
    Object localObject2 = null;
    if (!bool)
    {
      localObject1 = keymanagers;
      localObject1 = (KeyManager[])((Set)localObject1).toArray(new KeyManager[((Set)localObject1).size()]);
    }
    else
    {
      localObject1 = null;
    }
    if (!trustmanagers.isEmpty())
    {
      localObject2 = trustmanagers;
      localObject2 = (TrustManager[])((Set)localObject2).toArray(new TrustManager[((Set)localObject2).size()]);
    }
    localSSLContext.init((KeyManager[])localObject1, (TrustManager[])localObject2, secureRandom);
    return localSSLContext;
  }
  
  public SSLContextBuilder loadTrustMaterial(KeyStore paramKeyStore)
  {
    loadTrustMaterial(paramKeyStore, null);
    return this;
  }
  
  public SSLContextBuilder loadTrustMaterial(KeyStore paramKeyStore, TrustStrategy paramTrustStrategy)
  {
    TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    localTrustManagerFactory.init(paramKeyStore);
    paramKeyStore = localTrustManagerFactory.getTrustManagers();
    if (paramKeyStore != null)
    {
      int j = 0;
      if (paramTrustStrategy != null)
      {
        i = 0;
        while (i < paramKeyStore.length)
        {
          localTrustManagerFactory = paramKeyStore[i];
          if ((localTrustManagerFactory instanceof X509TrustManager)) {
            paramKeyStore[i] = new TrustManagerDelegate((X509TrustManager)localTrustManagerFactory, paramTrustStrategy);
          }
          i += 1;
        }
      }
      int k = paramKeyStore.length;
      int i = j;
      while (i < k)
      {
        paramTrustStrategy = paramKeyStore[i];
        trustmanagers.add(paramTrustStrategy);
        i += 1;
      }
    }
    return this;
  }
  
  class TrustManagerDelegate
    implements X509TrustManager
  {
    private final TrustStrategy trustStrategy;
    
    TrustManagerDelegate(TrustStrategy paramTrustStrategy)
    {
      trustStrategy = paramTrustStrategy;
    }
    
    public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    {
      SSLContextBuilder.this.checkClientTrusted(paramArrayOfX509Certificate, paramString);
    }
    
    public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    {
      if (!trustStrategy.isTrusted(paramArrayOfX509Certificate, paramString)) {
        SSLContextBuilder.this.checkServerTrusted(paramArrayOfX509Certificate, paramString);
      }
    }
    
    public X509Certificate[] getAcceptedIssuers()
    {
      return SSLContextBuilder.this.getAcceptedIssuers();
    }
  }
}
