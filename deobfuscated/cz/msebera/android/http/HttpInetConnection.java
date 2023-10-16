package cz.msebera.android.http;

import java.net.InetAddress;

public abstract interface HttpInetConnection
  extends HttpConnection
{
  public abstract InetAddress getRemoteAddress();
  
  public abstract int getRemotePort();
}
