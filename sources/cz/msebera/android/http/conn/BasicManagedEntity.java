package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.entity.HttpEntityWrapper;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.EntityUtils;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

@Deprecated
public class BasicManagedEntity extends HttpEntityWrapper implements ConnectionReleaseTrigger, EofSensorWatcher {
    protected final boolean attemptReuse;
    protected ManagedClientConnection managedConn;

    public BasicManagedEntity(HttpEntity httpEntity, ManagedClientConnection managedClientConnection, boolean z) {
        super(httpEntity);
        Args.notNull(managedClientConnection, "Connection");
        this.managedConn = managedClientConnection;
        this.attemptReuse = z;
    }

    private void ensureConsumed() {
        ManagedClientConnection $r1 = this.managedConn;
        if ($r1 != null) {
            try {
                if (this.attemptReuse) {
                    EntityUtils.consume(this.wrappedEntity);
                    this.managedConn.markReusable();
                } else {
                    $r1.unmarkReusable();
                }
            } finally {
                releaseManagedConnection();
            }
        }
    }

    public void abortConnection() {
        ManagedClientConnection $r1 = this.managedConn;
        if ($r1 != null) {
            try {
                $r1.abortConnection();
            } finally {
                this.managedConn = null;
            }
        }
    }

    public void consumeContent() {
        ensureConsumed();
    }

    /* JADX INFO: finally extract failed */
    public boolean eofDetected(InputStream inputStream) {
        try {
            if (this.managedConn != null) {
                if (this.attemptReuse) {
                    inputStream.close();
                    this.managedConn.markReusable();
                } else {
                    this.managedConn.unmarkReusable();
                }
            }
            releaseManagedConnection();
            return false;
        } catch (Throwable $r3) {
            releaseManagedConnection();
            throw $r3;
        }
    }

    public InputStream getContent() {
        return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
    }

    public boolean isRepeatable() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void releaseManagedConnection() {
        ManagedClientConnection $r1 = this.managedConn;
        if ($r1 != null) {
            try {
                $r1.releaseConnection();
            } finally {
                this.managedConn = null;
            }
        }
    }

    public boolean streamAbort(InputStream inputStream) {
        ManagedClientConnection $r2 = this.managedConn;
        if ($r2 == null) {
            return false;
        }
        $r2.abortConnection();
        return false;
    }

    public boolean streamClosed(InputStream inputStream) {
        boolean $z0;
        try {
            if (this.managedConn != null) {
                if (this.attemptReuse) {
                    $z0 = this.managedConn.isOpen();
                    inputStream.close();
                    this.managedConn.markReusable();
                } else {
                    this.managedConn.unmarkReusable();
                }
            }
        } catch (SocketException $r3) {
            if ($z0) {
                throw $r3;
            }
        } catch (Throwable $r4) {
            releaseManagedConnection();
            throw $r4;
        }
        releaseManagedConnection();
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        super.writeTo(outputStream);
        ensureConsumed();
    }
}
