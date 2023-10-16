package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ConnectionReleaseTrigger;

@Deprecated
public interface AbortableHttpRequest {
    void setConnectionRequest(ClientConnectionRequest clientConnectionRequest);

    void setReleaseTrigger(ConnectionReleaseTrigger connectionReleaseTrigger);
}
