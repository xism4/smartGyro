package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.execchain.HttpContext;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public abstract class AbstractClientConnAdapter implements ManagedClientConnection, HttpContext {
    private final ClientConnectionManager connManager;
    private volatile long duration = Long.MAX_VALUE;
    private volatile boolean markedReusable = false;
    private volatile boolean released = false;
    private volatile OperatedClientConnection wrappedConnection;

    protected AbstractClientConnAdapter(ClientConnectionManager clientConnectionManager, OperatedClientConnection operatedClientConnection) {
        this.connManager = clientConnectionManager;
        this.wrappedConnection = operatedClientConnection;
    }

    public synchronized void abortConnection() {
        if (!this.released) {
            this.released = true;
            unmarkReusable();
            try {
                shutdown();
            } catch (IOException e) {
            }
            this.connManager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: protected */
    public final void assertValid(OperatedClientConnection operatedClientConnection) {
        if (isReleased() || operatedClientConnection == null) {
            throw new ConnectionShutdownException();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void detach() {
        this.wrappedConnection = null;
        this.duration = Long.MAX_VALUE;
    }

    public void flush() {
        OperatedClientConnection $r1 = getWrappedConnection();
        assertValid($r1);
        $r1.flush();
    }

    public Object getAttribute(String str) {
        OperatedClientConnection $r2 = getWrappedConnection();
        assertValid($r2);
        if ($r2 instanceof HttpContext) {
            return ((HttpContext) $r2).getAttribute(str);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager getManager() {
        return this.connManager;
    }

    public InetAddress getRemoteAddress() {
        OperatedClientConnection $r1 = getWrappedConnection();
        assertValid($r1);
        return $r1.getRemoteAddress();
    }

    public int getRemotePort() {
        OperatedClientConnection $r1 = getWrappedConnection();
        assertValid($r1);
        return $r1.getRemotePort();
    }

    public SSLSession getSSLSession() {
        OperatedClientConnection $r1 = getWrappedConnection();
        assertValid($r1);
        if (!isOpen()) {
            return null;
        }
        Socket $r2 = $r1.getSocket();
        if ($r2 instanceof SSLSocket) {
            return ((SSLSocket) $r2).getSession();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public OperatedClientConnection getWrappedConnection() {
        return this.wrappedConnection;
    }

    public boolean isMarkedReusable() {
        return this.markedReusable;
    }

    public boolean isOpen() {
        OperatedClientConnection $r1 = getWrappedConnection();
        if ($r1 == null) {
            return false;
        }
        return $r1.isOpen();
    }

    /* access modifiers changed from: protected */
    public boolean isReleased() {
        return this.released;
    }

    public boolean isResponseAvailable(int i) {
        OperatedClientConnection $r1 = getWrappedConnection();
        assertValid($r1);
        return $r1.isResponseAvailable(i);
    }

    public boolean isStale() {
        OperatedClientConnection $r1;
        if (!isReleased() && ($r1 = getWrappedConnection()) != null) {
            return $r1.isStale();
        }
        return true;
    }

    public void markReusable() {
        this.markedReusable = true;
    }

    public void receiveResponseEntity(HttpResponse httpResponse) {
        OperatedClientConnection $r2 = getWrappedConnection();
        assertValid($r2);
        unmarkReusable();
        $r2.receiveResponseEntity(httpResponse);
    }

    public HttpResponse receiveResponseHeader() {
        OperatedClientConnection $r1 = getWrappedConnection();
        assertValid($r1);
        unmarkReusable();
        return $r1.receiveResponseHeader();
    }

    public synchronized void releaseConnection() {
        if (!this.released) {
            this.released = true;
            this.connManager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
        }
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        OperatedClientConnection $r2 = getWrappedConnection();
        assertValid($r2);
        unmarkReusable();
        $r2.sendRequestEntity(httpEntityEnclosingRequest);
    }

    public void sendRequestHeader(HttpRequest httpRequest) {
        OperatedClientConnection $r2 = getWrappedConnection();
        assertValid($r2);
        unmarkReusable();
        $r2.sendRequestHeader(httpRequest);
    }

    public void setAttribute(String str, Object obj) {
        OperatedClientConnection $r3 = getWrappedConnection();
        assertValid($r3);
        if ($r3 instanceof HttpContext) {
            ((HttpContext) $r3).setAttribute(str, obj);
        }
    }

    public void setIdleDuration(long j, TimeUnit timeUnit) {
        this.duration = j > 0 ? timeUnit.toMillis(j) : -1;
    }

    public void setSocketTimeout(int i) {
        OperatedClientConnection $r1 = getWrappedConnection();
        assertValid($r1);
        $r1.setSocketTimeout(i);
    }

    public void unmarkReusable() {
        this.markedReusable = false;
    }
}
