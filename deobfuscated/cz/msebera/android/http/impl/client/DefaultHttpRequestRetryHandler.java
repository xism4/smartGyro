package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.client.HttpRequestRetryHandler;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.client.cache.HttpClientContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpCoreContext;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.SSLException;

public class DefaultHttpRequestRetryHandler
  implements HttpRequestRetryHandler
{
  public static final DefaultHttpRequestRetryHandler INSTANCE = new DefaultHttpRequestRetryHandler();
  private final Set<Class<? extends IOException>> nonRetriableClasses;
  private final boolean requestSentRetryEnabled;
  private final int retryCount;
  
  public DefaultHttpRequestRetryHandler()
  {
    this(3, false);
  }
  
  public DefaultHttpRequestRetryHandler(int paramInt, boolean paramBoolean)
  {
    this(paramInt, paramBoolean, Arrays.asList(new Class[] { InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class }));
  }
  
  protected DefaultHttpRequestRetryHandler(int paramInt, boolean paramBoolean, Collection paramCollection)
  {
    retryCount = paramInt;
    requestSentRetryEnabled = paramBoolean;
    nonRetriableClasses = new HashSet();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Class localClass = (Class)paramCollection.next();
      nonRetriableClasses.add(localClass);
    }
  }
  
  protected boolean handleAsIdempotent(HttpRequest paramHttpRequest)
  {
    return !(paramHttpRequest instanceof HttpEntityEnclosingRequest);
  }
  
  protected boolean requestIsAborted(HttpRequest paramHttpRequest)
  {
    HttpRequest localHttpRequest = paramHttpRequest;
    if ((paramHttpRequest instanceof RequestWrapper)) {
      localHttpRequest = ((RequestWrapper)paramHttpRequest).getOriginal();
    }
    return ((localHttpRequest instanceof HttpUriRequest)) && (((HttpUriRequest)localHttpRequest).isAborted());
  }
  
  public boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
  {
    Args.notNull(paramIOException, "Exception parameter");
    Args.notNull(paramHttpContext, "HTTP context");
    if (paramInt > retryCount) {
      return false;
    }
    if (nonRetriableClasses.contains(paramIOException.getClass())) {
      return false;
    }
    Iterator localIterator = nonRetriableClasses.iterator();
    while (localIterator.hasNext()) {
      if (((Class)localIterator.next()).isInstance(paramIOException)) {
        return false;
      }
    }
    paramIOException = HttpClientContext.adapt(paramHttpContext);
    paramHttpContext = paramIOException.getRequest();
    if (requestIsAborted(paramHttpContext)) {
      return false;
    }
    if (handleAsIdempotent(paramHttpContext)) {
      return true;
    }
    if (paramIOException.isRequestSent()) {
      return requestSentRetryEnabled;
    }
    return true;
  }
}
