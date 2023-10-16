package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.util.HttpParams;
import java.net.Socket;

@Deprecated
public abstract interface OperatedClientConnection
  extends HttpClientConnection, HttpInetConnection
{
  public abstract Socket getSocket();
  
  public abstract boolean isSecure();
  
  public abstract void openCompleted(boolean paramBoolean, HttpParams paramHttpParams);
  
  public abstract void opening(Socket paramSocket, HttpHost paramHttpHost);
  
  public abstract void update(Socket paramSocket, HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams);
}
