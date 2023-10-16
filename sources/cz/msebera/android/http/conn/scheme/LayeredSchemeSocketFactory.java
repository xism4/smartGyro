package cz.msebera.android.http.conn.scheme;

import java.net.Socket;

@Deprecated
public interface LayeredSchemeSocketFactory extends SchemeSocketFactory {
    Socket createLayeredSocket(Socket socket, String str, int i, boolean z);
}
