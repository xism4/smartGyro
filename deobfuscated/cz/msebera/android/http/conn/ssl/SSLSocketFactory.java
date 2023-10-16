package cz.msebera.android.http.conn.ssl;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.HttpInetSocketAddress;
import cz.msebera.android.http.conn.scheme.HostNameResolver;
import cz.msebera.android.http.conn.scheme.LayeredSocketFactory;
import cz.msebera.android.http.conn.scheme.SchemeLayeredSocketFactory;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

@Deprecated
public class SSLSocketFactory
  implements cz.msebera.android.http.conn.util.LayeredSchemeSocketFactory, SchemeLayeredSocketFactory, cz.msebera.android.http.conn.scheme.LayeredSchemeSocketFactory, LayeredSocketFactory
{
  public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
  public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
  public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
  private volatile X509HostnameVerifier hostnameVerifier;
  private final HostNameResolver nameResolver;
  private final javax.net.ssl.SSLSocketFactory socketfactory;
  private final String[] supportedCipherSuites;
  private final String[] supportedProtocols;
  
  public SSLSocketFactory(KeyStore paramKeyStore)
  {
    this(localSSLContextBuilder.build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }
  
  public SSLSocketFactory(SSLContext paramSSLContext, X509HostnameVerifier paramX509HostnameVerifier)
  {
    this(((SSLContext)paramSSLContext).getSocketFactory(), null, null, paramX509HostnameVerifier);
  }
  
  public SSLSocketFactory(javax.net.ssl.SSLSocketFactory paramSSLSocketFactory, String[] paramArrayOfString1, String[] paramArrayOfString2, X509HostnameVerifier paramX509HostnameVerifier)
  {
    Args.notNull(paramSSLSocketFactory, "SSL socket factory");
    socketfactory = ((javax.net.ssl.SSLSocketFactory)paramSSLSocketFactory);
    supportedProtocols = paramArrayOfString1;
    supportedCipherSuites = paramArrayOfString2;
    if (paramX509HostnameVerifier == null) {
      paramX509HostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    }
    hostnameVerifier = paramX509HostnameVerifier;
    nameResolver = null;
  }
  
  public static SSLSocketFactory getSocketFactory()
  {
    return new SSLSocketFactory(SSLContexts.createDefault(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }
  
  private void internalPrepareSocket(SSLSocket paramSSLSocket)
  {
    String[] arrayOfString = supportedProtocols;
    if (arrayOfString != null) {
      paramSSLSocket.setEnabledProtocols(arrayOfString);
    }
    arrayOfString = supportedCipherSuites;
    if (arrayOfString != null) {
      paramSSLSocket.setEnabledCipherSuites(arrayOfString);
    }
    prepareSocket(paramSSLSocket);
  }
  
  private void verifyHostname(SSLSocket paramSSLSocket, String paramString)
  {
    X509HostnameVerifier localX509HostnameVerifier = hostnameVerifier;
    try
    {
      localX509HostnameVerifier.verify(paramString, paramSSLSocket);
      return;
    }
    catch (IOException paramString) {}
    try
    {
      paramSSLSocket.close();
      throw paramString;
    }
    catch (Exception paramSSLSocket)
    {
      for (;;) {}
    }
  }
  
  public Socket connectSocket(int paramInt, Socket paramSocket, HttpHost paramHttpHost, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpHost, "HTTP host");
    Args.notNull(paramInetSocketAddress1, "Remote address");
    if (paramSocket == null) {
      paramSocket = createSocket(paramHttpContext);
    }
    if (paramInetSocketAddress2 != null) {
      paramSocket.bind(paramInetSocketAddress2);
    }
    try
    {
      paramSocket.connect(paramInetSocketAddress1, paramInt);
      if ((paramSocket instanceof SSLSocket))
      {
        paramInetSocketAddress1 = (SSLSocket)paramSocket;
        paramInetSocketAddress1.startHandshake();
        verifyHostname(paramInetSocketAddress1, paramHttpHost.getHostName());
        return paramSocket;
      }
      return createLayeredSocket(paramSocket, paramHttpHost.getHostName(), paramInetSocketAddress1.getPort(), paramHttpContext);
    }
    catch (IOException paramHttpHost) {}
    try
    {
      paramSocket.close();
      throw paramHttpHost;
    }
    catch (IOException paramSocket)
    {
      for (;;) {}
    }
  }
  
  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
  {
    Object localObject = nameResolver;
    if (localObject != null) {
      localObject = ((HostNameResolver)localObject).resolve(paramString);
    } else {
      localObject = InetAddress.getByName(paramString);
    }
    InetSocketAddress localInetSocketAddress = null;
    if ((paramInetAddress != null) || (paramInt2 > 0))
    {
      if (paramInt2 <= 0) {
        paramInt2 = 0;
      }
      localInetSocketAddress = new InetSocketAddress(paramInetAddress, paramInt2);
    }
    return connectSocket(paramSocket, new HttpInetSocketAddress(new HttpHost(paramString, paramInt1), (InetAddress)localObject, paramInt1), localInetSocketAddress, paramHttpParams);
  }
  
  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
  {
    Args.notNull(paramInetSocketAddress1, "Remote address");
    Args.notNull(paramHttpParams, "HTTP parameters");
    HttpHost localHttpHost;
    if ((paramInetSocketAddress1 instanceof HttpInetSocketAddress)) {
      localHttpHost = ((HttpInetSocketAddress)paramInetSocketAddress1).getHttpHost();
    } else {
      localHttpHost = new HttpHost(paramInetSocketAddress1.getHostName(), paramInetSocketAddress1.getPort(), "https");
    }
    int i = HttpConnectionParams.getSoTimeout(paramHttpParams);
    int j = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    paramSocket.setSoTimeout(i);
    return connectSocket(j, paramSocket, localHttpHost, paramInetSocketAddress1, paramInetSocketAddress2, null);
  }
  
  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpContext paramHttpContext)
  {
    paramSocket = (SSLSocket)socketfactory.createSocket(paramSocket, paramString, paramInt, true);
    internalPrepareSocket(paramSocket);
    paramSocket.startHandshake();
    verifyHostname(paramSocket, paramString);
    return paramSocket;
  }
  
  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpParams paramHttpParams)
  {
    return createLayeredSocket(paramSocket, paramString, paramInt, null);
  }
  
  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
  {
    return createLayeredSocket(paramSocket, paramString, paramInt, null);
  }
  
  public Socket createSocket()
  {
    return createSocket(null);
  }
  
  public Socket createSocket(HttpContext paramHttpContext)
  {
    paramHttpContext = (SSLSocket)socketfactory.createSocket();
    internalPrepareSocket(paramHttpContext);
    return paramHttpContext;
  }
  
  public Socket createSocket(HttpParams paramHttpParams)
  {
    return createSocket(null);
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
  {
    return createLayeredSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
  
  public boolean isSecure(Socket paramSocket)
  {
    Args.notNull(paramSocket, "Socket");
    Asserts.check(paramSocket instanceof SSLSocket, "Socket not created by this factory");
    Asserts.check(paramSocket.isClosed() ^ true, "Socket is closed");
    return true;
  }
  
  protected void prepareSocket(SSLSocket paramSSLSocket) {}
  
  public void setHostnameVerifier(X509HostnameVerifier paramX509HostnameVerifier)
  {
    Args.notNull(paramX509HostnameVerifier, "Hostname verifier");
    hostnameVerifier = paramX509HostnameVerifier;
  }
}
