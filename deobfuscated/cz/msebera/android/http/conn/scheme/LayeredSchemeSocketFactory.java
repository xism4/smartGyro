package cz.msebera.android.http.conn.scheme;

import java.net.Socket;

@Deprecated
public abstract interface LayeredSchemeSocketFactory
  extends SchemeSocketFactory
{
  public abstract Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean);
}
