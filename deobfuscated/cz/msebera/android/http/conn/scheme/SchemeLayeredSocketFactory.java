package cz.msebera.android.http.conn.scheme;

import cz.msebera.android.http.util.HttpParams;
import java.net.Socket;

@Deprecated
public abstract interface SchemeLayeredSocketFactory
  extends SchemeSocketFactory
{
  public abstract Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpParams paramHttpParams);
}
