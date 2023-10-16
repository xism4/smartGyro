package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.conn.DnsResolver;
import java.net.InetAddress;

public class SystemDefaultDnsResolver
  implements DnsResolver
{
  public static final SystemDefaultDnsResolver INSTANCE = new SystemDefaultDnsResolver();
  
  public SystemDefaultDnsResolver() {}
  
  public InetAddress[] resolve(String paramString)
  {
    return InetAddress.getAllByName(paramString);
  }
}