package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.conn.DnsResolver;
import java.net.InetAddress;

public class SystemDefaultDnsResolver implements DnsResolver {
    public static final SystemDefaultDnsResolver INSTANCE = new SystemDefaultDnsResolver();

    public InetAddress[] resolve(String str) {
        return InetAddress.getAllByName(str);
    }
}
