package cz.msebera.android.http.conn.scheme;

import java.net.InetAddress;

@Deprecated
public abstract interface HostNameResolver
{
  public abstract InetAddress resolve(String paramString);
}
