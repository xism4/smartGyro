package cz.msebera.android.http.client;

import cz.msebera.android.http.execchain.HttpContext;
import java.io.IOException;

public abstract interface HttpRequestRetryHandler
{
  public abstract boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext);
}
