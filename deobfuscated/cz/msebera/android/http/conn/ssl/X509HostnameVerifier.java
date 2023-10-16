package cz.msebera.android.http.conn.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;

@Deprecated
public abstract interface X509HostnameVerifier
  extends HostnameVerifier
{
  public abstract void verify(String paramString, SSLSocket paramSSLSocket);
  
  public abstract void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2);
}
