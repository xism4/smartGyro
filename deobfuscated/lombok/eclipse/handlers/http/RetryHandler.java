package lombok.eclipse.handlers.http;

import android.os.SystemClock;
import c.a.a.a.A;
import cz.msebera.android.http.client.HttpRequestRetryHandler;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.execchain.HttpContext;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;

class RetryHandler
  implements HttpRequestRetryHandler
{
  private static final HashSet<Class<?>> exceptionBlacklist;
  private static final HashSet<Class<?>> exceptionWhitelist = new HashSet();
  private final int maxRetries;
  private final int retrySleepTimeMS;
  
  static
  {
    exceptionBlacklist = new HashSet();
    exceptionWhitelist.add(A.class);
    exceptionWhitelist.add(UnknownHostException.class);
    exceptionWhitelist.add(SocketException.class);
    exceptionBlacklist.add(InterruptedIOException.class);
    exceptionBlacklist.add(SSLException.class);
  }
  
  public RetryHandler(int paramInt1, int paramInt2)
  {
    maxRetries = paramInt1;
    retrySleepTimeMS = paramInt2;
  }
  
  static void addClassToWhitelist(Class paramClass)
  {
    exceptionWhitelist.add(paramClass);
  }
  
  protected boolean isInList(HashSet paramHashSet, Throwable paramThrowable)
  {
    paramHashSet = paramHashSet.iterator();
    while (paramHashSet.hasNext()) {
      if (((Class)paramHashSet.next()).isInstance(paramThrowable)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
  {
    Boolean localBoolean = (Boolean)paramHttpContext.getAttribute("http.request_sent");
    boolean bool = true;
    if (((localBoolean == null) || (localBoolean.booleanValue())) || (paramInt > maxRetries)) {}
    while ((!isInList(exceptionWhitelist, paramIOException)) && (isInList(exceptionBlacklist, paramIOException)))
    {
      bool = false;
      break;
    }
    if ((bool) && ((HttpUriRequest)paramHttpContext.getAttribute("http.request") == null)) {
      return false;
    }
    if (bool)
    {
      SystemClock.sleep(retrySleepTimeMS);
      return bool;
    }
    paramIOException.printStackTrace();
    return bool;
  }
}
