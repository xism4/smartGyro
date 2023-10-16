package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.g;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseFactory;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ManagedHttpClientConnection;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.impl.AbstractHttpClientConnection;
import cz.msebera.android.http.impl.SocketHttpClientConnection;
import cz.msebera.android.http.io.HttpMessageParser;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import cz.msebera.android.http.util.HttpProtocolParams;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public class DefaultClientConnection
  extends SocketHttpClientConnection
  implements OperatedClientConnection, ManagedHttpClientConnection, HttpContext
{
  private final Map<String, Object> attributes = new HashMap();
  private boolean connSecure;
  public HttpClientAndroidLog headerLog = new HttpClientAndroidLog("cz.msebera.android.httpclient.headers");
  public HttpClientAndroidLog log = new HttpClientAndroidLog(g.class);
  private volatile boolean shutdown;
  private volatile Socket socket;
  private HttpHost targetHost;
  public HttpClientAndroidLog wireLog = new HttpClientAndroidLog("cz.msebera.android.httpclient.wire");
  
  public DefaultClientConnection() {}
  
  public void close()
  {
    try
    {
      super.close();
      HttpClientAndroidLog localHttpClientAndroidLog = log;
      boolean bool = localHttpClientAndroidLog.isDebugEnabled();
      if (bool)
      {
        localHttpClientAndroidLog = log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Connection ");
        localStringBuilder.append(this);
        localStringBuilder.append(" closed");
        localHttpClientAndroidLog.debug(localStringBuilder.toString());
        return;
      }
    }
    catch (IOException localIOException)
    {
      log.debug("I/O error closing connection", localIOException);
    }
  }
  
  protected HttpMessageParser createResponseParser(SessionInputBuffer paramSessionInputBuffer, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    return new DefaultHttpResponseParser(paramSessionInputBuffer, null, paramHttpResponseFactory, paramHttpParams);
  }
  
  protected SessionInputBuffer createSessionInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
  {
    if (paramInt <= 0) {
      paramInt = 8192;
    }
    paramSocket = super.createSessionInputBuffer(paramSocket, paramInt, paramHttpParams);
    if (wireLog.isDebugEnabled()) {
      return new LoggingSessionInputBuffer(paramSocket, new Wire(wireLog), HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    }
    return paramSocket;
  }
  
  protected SessionOutputBuffer createSessionOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
  {
    if (paramInt <= 0) {
      paramInt = 8192;
    }
    paramSocket = super.createSessionOutputBuffer(paramSocket, paramInt, paramHttpParams);
    if (wireLog.isDebugEnabled()) {
      return new LoggingSessionOutputBuffer(paramSocket, new Wire(wireLog), HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    }
    return paramSocket;
  }
  
  public Object getAttribute(String paramString)
  {
    return attributes.get(paramString);
  }
  
  public SSLSession getSSLSession()
  {
    if ((socket instanceof SSLSocket)) {
      return ((SSLSocket)socket).getSession();
    }
    return null;
  }
  
  public final Socket getSocket()
  {
    return socket;
  }
  
  public final boolean isSecure()
  {
    return connSecure;
  }
  
  public void openCompleted(boolean paramBoolean, HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "Parameters");
    assertNotOpen();
    connSecure = paramBoolean;
    bind(socket, paramHttpParams);
  }
  
  public void opening(Socket paramSocket, HttpHost paramHttpHost)
  {
    assertNotOpen();
    socket = paramSocket;
    targetHost = paramHttpHost;
    if (!shutdown) {
      return;
    }
    paramSocket.close();
    throw new InterruptedIOException("Connection already shutdown");
  }
  
  public HttpResponse receiveResponseHeader()
  {
    HttpResponse localHttpResponse = super.receiveResponseHeader();
    Object localObject;
    StringBuilder localStringBuilder1;
    if (log.isDebugEnabled())
    {
      localObject = log;
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Receiving response: ");
      localStringBuilder1.append(localHttpResponse.getStatusLine());
      ((HttpClientAndroidLog)localObject).debug(localStringBuilder1.toString());
    }
    if (headerLog.isDebugEnabled())
    {
      localObject = headerLog;
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("<< ");
      localStringBuilder1.append(localHttpResponse.getStatusLine().toString());
      ((HttpClientAndroidLog)localObject).debug(localStringBuilder1.toString());
      localObject = localHttpResponse.getAllHeaders();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder1 = localObject[i];
        HttpClientAndroidLog localHttpClientAndroidLog = headerLog;
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("<< ");
        localStringBuilder2.append(localStringBuilder1.toString());
        localHttpClientAndroidLog.debug(localStringBuilder2.toString());
        i += 1;
      }
    }
    return localHttpResponse;
  }
  
  public void sendRequestHeader(HttpRequest paramHttpRequest)
  {
    HttpClientAndroidLog localHttpClientAndroidLog;
    Object localObject;
    if (log.isDebugEnabled())
    {
      localHttpClientAndroidLog = log;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Sending request: ");
      ((StringBuilder)localObject).append(paramHttpRequest.getRequestLine());
      localHttpClientAndroidLog.debug(((StringBuilder)localObject).toString());
    }
    super.sendRequestHeader(paramHttpRequest);
    if (headerLog.isDebugEnabled())
    {
      localHttpClientAndroidLog = headerLog;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(">> ");
      ((StringBuilder)localObject).append(paramHttpRequest.getRequestLine().toString());
      localHttpClientAndroidLog.debug(((StringBuilder)localObject).toString());
      paramHttpRequest = paramHttpRequest.getAllHeaders();
      int j = paramHttpRequest.length;
      int i = 0;
      while (i < j)
      {
        localHttpClientAndroidLog = paramHttpRequest[i];
        localObject = headerLog;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(">> ");
        localStringBuilder.append(localHttpClientAndroidLog.toString());
        ((HttpClientAndroidLog)localObject).debug(localStringBuilder.toString());
        i += 1;
      }
    }
  }
  
  public void setAttribute(String paramString, Object paramObject)
  {
    attributes.put(paramString, paramObject);
  }
  
  public void shutdown()
  {
    shutdown = true;
    try
    {
      super.shutdown();
      Object localObject = log;
      boolean bool = ((HttpClientAndroidLog)localObject).isDebugEnabled();
      if (bool)
      {
        localObject = log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Connection ");
        localStringBuilder.append(this);
        localStringBuilder.append(" shut down");
        ((HttpClientAndroidLog)localObject).debug(localStringBuilder.toString());
      }
      localObject = socket;
      if (localObject != null)
      {
        ((Socket)localObject).close();
        return;
      }
    }
    catch (IOException localIOException)
    {
      log.debug("I/O error shutting down connection", localIOException);
    }
  }
  
  public void update(Socket paramSocket, HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
  {
    assertOpen();
    Args.notNull(paramHttpHost, "Target host");
    Args.notNull(paramHttpParams, "Parameters");
    if (paramSocket != null)
    {
      socket = paramSocket;
      bind(paramSocket, paramHttpParams);
    }
    targetHost = paramHttpHost;
    connSecure = paramBoolean;
  }
}
