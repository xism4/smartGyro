package cz.msebera.android.http.conn.scheme;

import java.net.InetAddress;

@Deprecated
public interface HostNameResolver {
    InetAddress resolve(String str);
}
