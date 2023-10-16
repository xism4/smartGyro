package cz.msebera.android.http.client.auth;

import c.a.a.a.c.a;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.client.ssl.CloneUtils;
import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ConnectionReleaseTrigger;
import cz.msebera.android.http.message.AbstractHttpMessage;
import cz.msebera.android.http.message.HeaderGroup;
import cz.msebera.android.http.methods.Cancellable;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractExecutionAwareRequest extends AbstractHttpMessage implements HttpMessage, AbortableHttpRequest, Cloneable, HttpRequest {
    private final AtomicBoolean aborted = new AtomicBoolean(false);
    private final AtomicReference<a> cancellableRef = new AtomicReference((Object) null);

    protected AbstractExecutionAwareRequest() {
    }

    public void abort() {
        Cancellable $r4;
        if (this.aborted.compareAndSet(false, true) && ($r4 = this.cancellableRef.getAndSet((Object) null)) != null) {
            $r4.cancel();
        }
    }

    public Object clone() {
        AbstractExecutionAwareRequest $r2 = (AbstractExecutionAwareRequest) super.clone();
        $r2.headergroup = (HeaderGroup) CloneUtils.cloneObject(this.headergroup);
        $r2.params = (HttpParams) CloneUtils.cloneObject(this.params);
        return $r2;
    }

    public boolean isAborted() {
        return this.aborted.get();
    }

    public void setCancellable(Cancellable cancellable) {
        if (!this.aborted.get()) {
            this.cancellableRef.set(cancellable);
        }
    }

    public void setConnectionRequest(final ClientConnectionRequest clientConnectionRequest) {
        setCancellable(new Cancellable() {
            public boolean cancel() {
                clientConnectionRequest.abortRequest();
                return true;
            }
        });
    }

    public void setReleaseTrigger(final ConnectionReleaseTrigger connectionReleaseTrigger) {
        setCancellable(new Cancellable() {
            public boolean cancel() {
                try {
                    connectionReleaseTrigger.abortConnection();
                    return true;
                } catch (IOException e) {
                    return false;
                }
            }
        });
    }
}
