package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;

@Deprecated
public abstract interface ClientConnectionOperator
{
  public abstract OperatedClientConnection createConnection();
  
  public abstract void openConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, InetAddress paramInetAddress, HttpContext paramHttpContext, HttpParams paramHttpParams);
  
  public abstract void updateSecureConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, HttpContext paramHttpContext, HttpParams paramHttpParams);
}
