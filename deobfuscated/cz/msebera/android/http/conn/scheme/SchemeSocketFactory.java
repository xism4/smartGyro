package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
public abstract interface SchemeSocketFactory
{
  public abstract Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams);
  
  public abstract Socket createSocket(HttpParams paramHttpParams);
  
  public abstract boolean isSecure(Socket paramSocket);
}
