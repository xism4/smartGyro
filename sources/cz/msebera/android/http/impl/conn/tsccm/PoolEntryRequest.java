package cz.msebera.android.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;

@Deprecated
public interface PoolEntryRequest {
    void abortRequest();

    BasicPoolEntry getPoolEntry(long j, TimeUnit timeUnit);
}
