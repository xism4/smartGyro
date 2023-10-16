package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.m;
import c.a.a.a.j;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class BasicHttpClientConnectionManager
{
  private final Map<j, Object> connConfig = new HashMap();
  public HttpClientAndroidLog socketConfig = new HttpClientAndroidLog(m.class);
  
  public BasicHttpClientConnectionManager() {}
}
