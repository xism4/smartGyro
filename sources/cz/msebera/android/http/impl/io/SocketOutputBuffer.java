package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.net.Socket;

@Deprecated
public class SocketOutputBuffer extends AbstractSessionOutputBuffer {
    public SocketOutputBuffer(Socket socket, int $i0, HttpParams httpParams) {
        Args.notNull(socket, "Socket");
        $i0 = $i0 < 0 ? socket.getSendBufferSize() : $i0;
        init(socket.getOutputStream(), $i0 < 1024 ? 1024 : $i0, httpParams);
    }
}
