package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import java.util.concurrent.TimeUnit;

class PoolingClientConnectionManager$1 implements ClientConnectionRequest {
    final /* synthetic */ BasicClientConnectionManager this$0;
    final /* synthetic */ HttpRoute val$future;
    final /* synthetic */ Object val$route;

    PoolingClientConnectionManager$1(BasicClientConnectionManager basicClientConnectionManager, HttpRoute httpRoute, Object obj) {
        this.this$0 = basicClientConnectionManager;
        this.val$future = httpRoute;
        this.val$route = obj;
    }

    public void abortRequest() {
    }

    public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) {
        return this.this$0.getConnection(this.val$future, this.val$route);
    }
}
