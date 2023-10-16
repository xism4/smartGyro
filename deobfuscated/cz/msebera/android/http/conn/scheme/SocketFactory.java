package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.net.Socket;

@Deprecated
public abstract interface SocketFactory
{
  public abstract Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams);
  
  public abstract Socket createSocket();
  
  public abstract boolean isSecure(Socket paramSocket);
}
