package cz.msebera.android.http.conn;

import java.net.InetAddress;

public interface DnsResolver {
    InetAddress[] resolve(String str);
}
