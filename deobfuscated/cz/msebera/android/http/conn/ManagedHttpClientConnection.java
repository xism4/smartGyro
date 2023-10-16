package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpInetConnection;
import javax.net.ssl.SSLSession;

public abstract interface ManagedHttpClientConnection
  extends HttpClientConnection, HttpInetConnection
{
  public abstract SSLSession getSSLSession();
}
