package cz.msebera.android.http.impl.conn.tsccm;

import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.impl.conn.AbstractPoolEntry;
import cz.msebera.android.http.impl.conn.AbstractPooledConnAdapter;

@Deprecated
public class BasicPooledConnAdapter extends AbstractPooledConnAdapter {
    protected BasicPooledConnAdapter(ThreadSafeClientConnManager threadSafeClientConnManager, AbstractPoolEntry abstractPoolEntry) {
        super(threadSafeClientConnManager, abstractPoolEntry);
        markReusable();
    }

    /* access modifiers changed from: protected */
    public void detach() {
        super.detach();
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager getManager() {
        return super.getManager();
    }

    /* access modifiers changed from: protected */
    public AbstractPoolEntry getPoolEntry() {
        return super.getPoolEntry();
    }
}
