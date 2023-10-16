package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.RedirectHandler;

@Deprecated
public class DefaultRedirectHandler
  implements RedirectHandler
{
  public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
  
  public DefaultRedirectHandler() {}
}
