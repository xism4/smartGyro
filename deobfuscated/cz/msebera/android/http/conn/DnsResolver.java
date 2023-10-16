package cz.msebera.android.http.conn;

import java.net.InetAddress;

public abstract interface DnsResolver
{
  public abstract InetAddress[] resolve(String paramString);
}
