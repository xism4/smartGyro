package lombok.eclipse.handlers.http;

import android.content.Context;
import b.c.a.a.q;
import c.a.a.a.g.f;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.client.auth.HttpDelete;
import cz.msebera.android.http.client.auth.HttpEntityEnclosingRequestBase;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.scheme.PlainSocketFactory;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.ssl.SSLSocketFactory;
import cz.msebera.android.http.conn.tsccm.ConnManagerParams;
import cz.msebera.android.http.conn.tsccm.ConnPerRouteBean;
import cz.msebera.android.http.entity.HttpEntityWrapper;
import cz.msebera.android.http.execchain.BasicHttpContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.SyncBasicHttpContext;
import cz.msebera.android.http.impl.client.AbstractHttpClient;
import cz.msebera.android.http.impl.client.DefaultHttpClient;
import cz.msebera.android.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import cz.msebera.android.http.util.BasicHttpParams;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import cz.msebera.android.http.util.HttpProtocolParams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

public class AsyncHttpClient
{
  public static LogInterface log = new LogHandler();
  private final Map<String, String> clientHeaderMap;
  private int connectTimeout = 10000;
  private final DefaultHttpClient httpClient;
  private final HttpContext httpContext;
  private boolean isUrlEncodingEnabled;
  private int maxConnections = 10;
  private final Map<Context, List<q>> requestMap;
  private int responseTimeout = 10000;
  private ExecutorService threadPool;
  
  public AsyncHttpClient()
  {
    this(false, 80, 443);
  }
  
  public AsyncHttpClient(SchemeRegistry paramSchemeRegistry)
  {
    boolean bool = true;
    isUrlEncodingEnabled = true;
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    ConnManagerParams.setTimeout(localBasicHttpParams, connectTimeout);
    ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(maxConnections));
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 10);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, responseTimeout);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, connectTimeout);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    paramSchemeRegistry = createConnectionManager(paramSchemeRegistry, localBasicHttpParams);
    if (paramSchemeRegistry == null) {
      bool = false;
    }
    Utils.asserts(bool, "Custom implementation of #createConnectionManager(SchemeRegistry, BasicHttpParams) returned null");
    threadPool = getDefaultThreadPool();
    requestMap = Collections.synchronizedMap(new WeakHashMap());
    clientHeaderMap = new HashMap();
    httpContext = new SyncBasicHttpContext(new BasicHttpContext());
    httpClient = new DefaultHttpClient(paramSchemeRegistry, localBasicHttpParams);
    httpClient.addRequestInterceptor(new AsyncHttpClient.1(this));
    httpClient.addResponseInterceptor(new ImmutableHttpProcessor(this));
    httpClient.addRequestInterceptor(new RequestDefaultHeaders(this), 0);
    httpClient.setHttpRequestRetryHandler(new RetryHandler(5, 1500));
  }
  
  public AsyncHttpClient(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    this(getDefaultSchemeRegistry(paramBoolean, paramInt1, paramInt2));
  }
  
  public static void allowRetryExceptionClass(Class paramClass)
  {
    if (paramClass != null) {
      RetryHandler.addClassToWhitelist(paramClass);
    }
  }
  
  public static void endEntityViaReflection(HttpEntity paramHttpEntity)
  {
    if ((paramHttpEntity instanceof HttpEntityWrapper))
    {
      Object localObject2 = null;
      try
      {
        Field[] arrayOfField = f.class.getDeclaredFields();
        int j = arrayOfField.length;
        int i = 0;
        Object localObject1;
        for (;;)
        {
          localObject1 = localObject2;
          if (i >= j) {
            break;
          }
          localObject1 = arrayOfField[i];
          boolean bool = ((Field)localObject1).getName().equals("wrappedEntity");
          if (bool) {
            break;
          }
          i += 1;
        }
        if (localObject1 != null)
        {
          ((Field)localObject1).setAccessible(true);
          paramHttpEntity = (HttpEntity)((Field)localObject1).get(paramHttpEntity);
          if (paramHttpEntity != null)
          {
            paramHttpEntity.consumeContent();
            return;
          }
        }
      }
      catch (Throwable paramHttpEntity)
      {
        log.e("AsyncHttpClient", "wrappedEntity consume", paramHttpEntity);
      }
    }
  }
  
  private static SchemeRegistry getDefaultSchemeRegistry(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramBoolean) {
      log.d("AsyncHttpClient", "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
    }
    int i = paramInt1;
    if (paramInt1 < 1)
    {
      i = 80;
      log.d("AsyncHttpClient", "Invalid HTTP port number specified, defaulting to 80");
    }
    paramInt1 = paramInt2;
    if (paramInt2 < 1)
    {
      paramInt1 = 443;
      log.d("AsyncHttpClient", "Invalid HTTPS port number specified, defaulting to 443");
    }
    SSLSocketFactory localSSLSocketFactory;
    if (paramBoolean) {
      localSSLSocketFactory = MySSLSocketFactory.getFixedSocketFactory();
    } else {
      localSSLSocketFactory = SSLSocketFactory.getSocketFactory();
    }
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), i));
    localSchemeRegistry.register(new Scheme("https", localSSLSocketFactory, paramInt1));
    return localSchemeRegistry;
  }
  
  public static String getUrlWithQueryString(boolean paramBoolean, String paramString, RequestParams paramRequestParams)
  {
    if (paramString == null) {
      return null;
    }
    Object localObject = paramString;
    String str;
    if (paramBoolean) {
      try
      {
        localObject = URLDecoder.decode(paramString, "UTF-8");
        localObject = new URL((String)localObject);
        localObject = new URI(((URL)localObject).getProtocol(), ((URL)localObject).getUserInfo(), ((URL)localObject).getHost(), ((URL)localObject).getPort(), ((URL)localObject).getPath(), ((URL)localObject).getQuery(), ((URL)localObject).getRef()).toASCIIString();
      }
      catch (Exception localException)
      {
        log.e("AsyncHttpClient", "getUrlWithQueryString encoding URL", localException);
        str = paramString;
      }
    }
    if (paramRequestParams == null) {
      return str;
    }
    paramRequestParams.getParamString();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public static boolean isInputStreamGZIPCompressed(PushbackInputStream paramPushbackInputStream)
  {
    if (paramPushbackInputStream == null) {
      return false;
    }
    byte[] arrayOfByte = new byte[2];
    int i = 0;
    while (i < 2) {
      try
      {
        int j = paramPushbackInputStream.read(arrayOfByte, i, 2 - i);
        if (j < 0)
        {
          paramPushbackInputStream.unread(arrayOfByte, 0, i);
          return false;
        }
        i += j;
      }
      catch (Throwable localThrowable)
      {
        paramPushbackInputStream.unread(arrayOfByte, 0, i);
        throw localThrowable;
      }
    }
    paramPushbackInputStream.unread(arrayOfByte, 0, i);
    return 35615 == (arrayOfByte[0] & 0xFF | arrayOfByte[1] << 8 & 0xFF00);
  }
  
  public static void silentCloseInputStream(InputStream paramInputStream)
  {
    if (paramInputStream != null) {
      try
      {
        paramInputStream.close();
        return;
      }
      catch (IOException paramInputStream)
      {
        log.w("AsyncHttpClient", "Cannot close input stream", paramInputStream);
      }
    }
  }
  
  public static void silentCloseOutputStream(OutputStream paramOutputStream)
  {
    if (paramOutputStream != null) {
      try
      {
        paramOutputStream.close();
        return;
      }
      catch (IOException paramOutputStream)
      {
        log.w("AsyncHttpClient", "Cannot close output stream", paramOutputStream);
      }
    }
  }
  
  protected ClientConnectionManager createConnectionManager(SchemeRegistry paramSchemeRegistry, BasicHttpParams paramBasicHttpParams)
  {
    return new ThreadSafeClientConnManager(paramBasicHttpParams, paramSchemeRegistry);
  }
  
  public RequestHandle delete(Context paramContext, String paramString, Header[] paramArrayOfHeader, RequestParams paramRequestParams, ResponseHandlerInterface paramResponseHandlerInterface)
  {
    paramString = new HttpDelete(getUrlWithQueryString(isUrlEncodingEnabled, paramString, paramRequestParams));
    if (paramArrayOfHeader != null) {
      paramString.setHeaders(paramArrayOfHeader);
    }
    return sendRequest(httpClient, httpContext, paramString, null, paramResponseHandlerInterface, paramContext);
  }
  
  public RequestHandle get(Context paramContext, String paramString, RequestParams paramRequestParams, ResponseHandlerInterface paramResponseHandlerInterface)
  {
    return sendRequest(httpClient, httpContext, new Request(getUrlWithQueryString(isUrlEncodingEnabled, paramString, paramRequestParams)), null, paramResponseHandlerInterface, paramContext);
  }
  
  public RequestHandle get(Context paramContext, String paramString, ResponseHandlerInterface paramResponseHandlerInterface)
  {
    return get(paramContext, paramString, null, paramResponseHandlerInterface);
  }
  
  public RequestHandle get(Context paramContext, String paramString, Header[] paramArrayOfHeader, RequestParams paramRequestParams, ResponseHandlerInterface paramResponseHandlerInterface)
  {
    paramString = new Request(getUrlWithQueryString(isUrlEncodingEnabled, paramString, paramRequestParams));
    if (paramArrayOfHeader != null) {
      paramString.setHeaders(paramArrayOfHeader);
    }
    return sendRequest(httpClient, httpContext, paramString, null, paramResponseHandlerInterface, paramContext);
  }
  
  protected ExecutorService getDefaultThreadPool()
  {
    return Executors.newCachedThreadPool();
  }
  
  protected AsyncHttpRequest newAsyncHttpRequest(DefaultHttpClient paramDefaultHttpClient, HttpContext paramHttpContext, HttpUriRequest paramHttpUriRequest, String paramString, ResponseHandlerInterface paramResponseHandlerInterface, Context paramContext)
  {
    return new AsyncHttpRequest(paramDefaultHttpClient, paramHttpContext, paramHttpUriRequest, paramResponseHandlerInterface);
  }
  
  protected RequestHandle sendRequest(DefaultHttpClient paramDefaultHttpClient, HttpContext paramHttpContext, HttpUriRequest paramHttpUriRequest, String paramString, ResponseHandlerInterface paramResponseHandlerInterface, Context paramContext)
  {
    if (paramHttpUriRequest != null) {
      if (paramResponseHandlerInterface != null)
      {
        if ((paramResponseHandlerInterface.getUseSynchronousMode()) && (!paramResponseHandlerInterface.getUsePoolThread())) {
          throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
        if (paramString != null) {
          if (((paramHttpUriRequest instanceof HttpEntityEnclosingRequestBase)) && (((HttpEntityEnclosingRequestBase)paramHttpUriRequest).getEntity() != null) && (paramHttpUriRequest.containsHeader("Content-Type"))) {
            log.w("AsyncHttpClient", "Passed contentType will be ignored because HttpEntity sets content type");
          } else {
            paramHttpUriRequest.setHeader("Content-Type", paramString);
          }
        }
        paramResponseHandlerInterface.setRequestHeaders(paramHttpUriRequest.getAllHeaders());
        paramResponseHandlerInterface.setRequestURI(paramHttpUriRequest.getURI());
        paramDefaultHttpClient = newAsyncHttpRequest(paramDefaultHttpClient, paramHttpContext, paramHttpUriRequest, paramString, paramResponseHandlerInterface, paramContext);
        threadPool.submit(paramDefaultHttpClient);
        paramHttpUriRequest = new RequestHandle(paramDefaultHttpClient);
        if (paramContext == null) {
          return paramHttpUriRequest;
        }
        paramString = requestMap;
        try
        {
          paramHttpContext = (List)requestMap.get(paramContext);
          paramDefaultHttpClient = paramHttpContext;
          if (paramHttpContext == null)
          {
            paramHttpContext = Collections.synchronizedList(new LinkedList());
            paramDefaultHttpClient = paramHttpContext;
            requestMap.put(paramContext, paramHttpContext);
          }
          paramDefaultHttpClient.add(paramHttpUriRequest);
          paramDefaultHttpClient = paramDefaultHttpClient.iterator();
          while (paramDefaultHttpClient.hasNext()) {
            if (((RequestHandle)paramDefaultHttpClient.next()).shouldBeGarbageCollected()) {
              paramDefaultHttpClient.remove();
            }
          }
          throw new IllegalArgumentException("ResponseHandler must not be null");
        }
        catch (Throwable paramDefaultHttpClient)
        {
          throw paramDefaultHttpClient;
        }
      }
    }
    paramDefaultHttpClient = new IllegalArgumentException("HttpUriRequest must not be null");
    throw paramDefaultHttpClient;
    return paramHttpUriRequest;
  }
  
  public void setConnectTimeout(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1000) {
      i = 10000;
    }
    connectTimeout = i;
    HttpParams localHttpParams = httpClient.getParams();
    ConnManagerParams.setTimeout(localHttpParams, connectTimeout);
    HttpConnectionParams.setConnectionTimeout(localHttpParams, connectTimeout);
  }
  
  public void setEnableRedirects(boolean paramBoolean)
  {
    setEnableRedirects(paramBoolean, paramBoolean, paramBoolean);
  }
  
  public void setEnableRedirects(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    httpClient.getParams().setBooleanParameter("http.protocol.reject-relative-redirect", paramBoolean2 ^ true);
    httpClient.getParams().setBooleanParameter("http.protocol.allow-circular-redirects", paramBoolean3);
    httpClient.setRedirectHandler(new MyRedirectHandler(paramBoolean1));
  }
  
  public void setResponseTimeout(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1000) {
      i = 10000;
    }
    responseTimeout = i;
    HttpConnectionParams.setSoTimeout(httpClient.getParams(), responseTimeout);
  }
  
  public void setTimeout(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1000) {
      i = 10000;
    }
    setConnectTimeout(i);
    setResponseTimeout(i);
  }
  
  class InflatingEntity
    extends HttpEntityWrapper
  {
    GZIPInputStream gzippedStream;
    PushbackInputStream pushbackStream;
    InputStream wrappedStream;
    
    public InflatingEntity()
    {
      super();
    }
    
    public void consumeContent()
    {
      AsyncHttpClient.silentCloseInputStream(wrappedStream);
      AsyncHttpClient.silentCloseInputStream(pushbackStream);
      AsyncHttpClient.silentCloseInputStream(gzippedStream);
      super.consumeContent();
    }
    
    public InputStream getContent()
    {
      wrappedStream = wrappedEntity.getContent();
      pushbackStream = new PushbackInputStream(wrappedStream, 2);
      if (AsyncHttpClient.isInputStreamGZIPCompressed(pushbackStream))
      {
        gzippedStream = new GZIPInputStream(pushbackStream);
        return gzippedStream;
      }
      return pushbackStream;
    }
    
    public long getContentLength()
    {
      HttpEntity localHttpEntity = wrappedEntity;
      if (localHttpEntity == null) {
        return 0L;
      }
      return localHttpEntity.getContentLength();
    }
  }
}
