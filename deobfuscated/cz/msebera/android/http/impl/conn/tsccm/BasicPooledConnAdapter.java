package cz.msebera.android.http.impl.conn.tsccm;

import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.impl.conn.AbstractClientConnAdapter;
import cz.msebera.android.http.impl.conn.AbstractPoolEntry;
import cz.msebera.android.http.impl.conn.AbstractPooledConnAdapter;

@Deprecated
public class BasicPooledConnAdapter
  extends AbstractPooledConnAdapter
{
  protected BasicPooledConnAdapter(ThreadSafeClientConnManager paramThreadSafeClientConnManager, AbstractPoolEntry paramAbstractPoolEntry)
  {
    super(paramThreadSafeClientConnManager, paramAbstractPoolEntry);
    markReusable();
  }
  
  protected void detach()
  {
    super.detach();
  }
  
  protected ClientConnectionManager getManager()
  {
    return super.getManager();
  }
  
  protected AbstractPoolEntry getPoolEntry()
  {
    return super.getPoolEntry();
  }
}
