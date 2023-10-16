package cz.msebera.android.http;

import java.net.InetAddress;

public interface HttpInetConnection extends HttpConnection {
    InetAddress getRemoteAddress();

    int getRemotePort();
}
