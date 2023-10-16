package cz.msebera.android.http.impl;

import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.impl.io.SocketInputBuffer;
import cz.msebera.android.http.impl.io.SocketOutputBuffer;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

@Deprecated
public class SocketHttpClientConnection extends AbstractHttpClientConnection implements HttpInetConnection {
    private volatile boolean open;
    private volatile Socket socket = null;

    private static void formatAddress(StringBuilder sb, SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress $r2 = (InetSocketAddress) socketAddress;
            sb.append($r2.getAddress() != null ? $r2.getAddress().getHostAddress() : $r2.getAddress());
            sb.append(':');
            sb.append($r2.getPort());
            return;
        }
        sb.append(socketAddress);
    }

    /* access modifiers changed from: protected */
    public void assertNotOpen() {
        Asserts.check(!this.open, "Connection is already open");
    }

    /* access modifiers changed from: protected */
    public void assertOpen() {
        Asserts.check(this.open, "Connection is not open");
    }

    /* access modifiers changed from: protected */
    public void bind(Socket socket2, HttpParams httpParams) {
        Args.notNull(socket2, "Socket");
        Args.notNull(httpParams, "HTTP parameters");
        this.socket = socket2;
        int $i0 = httpParams.getIntParameter("http.socket.buffer-size", -1);
        init(createSessionInputBuffer(socket2, $i0, httpParams), createSessionOutputBuffer(socket2, $i0, httpParams), httpParams);
        this.open = true;
    }

    public void close() {
        if (this.open) {
            this.open = false;
            Socket $r6 = this.socket;
            try {
                doFlush();
                try {
                    $r6.shutdownOutput();
                } catch (IOException e) {
                } catch (UnsupportedOperationException e2) {
                }
                try {
                    $r6.shutdownInput();
                } catch (IOException e3) {
                } catch (UnsupportedOperationException e4) {
                }
            } finally {
                $r6.close();
            }
        }
    }

    /* access modifiers changed from: protected */
    public SessionInputBuffer createSessionInputBuffer(Socket socket2, int i, HttpParams httpParams) {
        return new SocketInputBuffer(socket2, i, httpParams);
    }

    /* access modifiers changed from: protected */
    public SessionOutputBuffer createSessionOutputBuffer(Socket socket2, int i, HttpParams httpParams) {
        return new SocketOutputBuffer(socket2, i, httpParams);
    }

    public InetAddress getRemoteAddress() {
        if (this.socket != null) {
            return this.socket.getInetAddress();
        }
        return null;
    }

    public int getRemotePort() {
        if (this.socket != null) {
            return this.socket.getPort();
        }
        return -1;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setSocketTimeout(int i) {
        assertOpen();
        if (this.socket != null) {
            try {
                this.socket.setSoTimeout(i);
            } catch (SocketException e) {
            }
        }
    }

    public void shutdown() {
        this.open = false;
        Socket $r1 = this.socket;
        if ($r1 != null) {
            $r1.close();
        }
    }

    public String toString() {
        if (this.socket == null) {
            return super.toString();
        }
        StringBuilder $r2 = new StringBuilder();
        SocketAddress $r3 = this.socket.getRemoteSocketAddress();
        SocketAddress $r4 = this.socket.getLocalSocketAddress();
        if (!($r3 == null || $r4 == null)) {
            formatAddress($r2, $r4);
            $r2.append("<->");
            formatAddress($r2, $r3);
        }
        return $r2.toString();
    }
}
