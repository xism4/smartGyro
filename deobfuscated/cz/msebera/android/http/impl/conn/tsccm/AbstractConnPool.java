package cz.msebera.android.http.impl.conn.tsccm;

import c.a.a.a.i.c.a.b;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.impl.conn.BasicHttpClientConnectionManager;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
public abstract class AbstractConnPool
{
  protected BasicHttpClientConnectionManager idleConnHandler = new BasicHttpClientConnectionManager();
  protected Set<b> leasedConnections = new HashSet();
  public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
  protected final Lock poolLock = new ReentrantLock();
  
  protected AbstractConnPool() {}
}
