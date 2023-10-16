package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.EofSensor;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.net.Socket;

@Deprecated
public class SocketInputBuffer extends AbstractSessionInputBuffer implements EofSensor {
    private boolean eof = false;
    private final Socket socket;

    public SocketInputBuffer(Socket socket2, int $i0, HttpParams httpParams) {
        Args.notNull(socket2, "Socket");
        this.socket = socket2;
        $i0 = $i0 < 0 ? socket2.getReceiveBufferSize() : $i0;
        init(socket2.getInputStream(), $i0 < 1024 ? 1024 : $i0, httpParams);
    }

    /* access modifiers changed from: protected */
    public int fillBuffer() {
        int $i0 = super.fillBuffer();
        this.eof = $i0 == -1;
        return $i0;
    }

    /* JADX INFO: finally extract failed */
    public boolean isDataAvailable(int i) {
        boolean $z0 = hasBufferedData();
        if ($z0) {
            return $z0;
        }
        int $i1 = this.socket.getSoTimeout();
        try {
            this.socket.setSoTimeout(i);
            fillBuffer();
            boolean $z02 = hasBufferedData();
            this.socket.setSoTimeout($i1);
            return $z02;
        } catch (Throwable $r2) {
            this.socket.setSoTimeout($i1);
            throw $r2;
        }
    }

    public boolean isEof() {
        return this.eof;
    }
}
