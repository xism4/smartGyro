package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ConnectionReleaseTrigger;

@Deprecated
public abstract interface AbortableHttpRequest
{
  public abstract void setConnectionRequest(ClientConnectionRequest paramClientConnectionRequest);
  
  public abstract void setReleaseTrigger(ConnectionReleaseTrigger paramConnectionReleaseTrigger);
}
