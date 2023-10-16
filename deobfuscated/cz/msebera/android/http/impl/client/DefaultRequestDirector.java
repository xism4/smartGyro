package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.ConnectionReuseStrategy;
import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.NoHttpResponseException;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.auth.AuthProtocolState;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.auth.UsernamePasswordCredentials;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthenticationHandler;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.client.HttpRequestRetryHandler;
import cz.msebera.android.http.client.NonRepeatableRequestException;
import cz.msebera.android.http.client.RedirectException;
import cz.msebera.android.http.client.RedirectHandler;
import cz.msebera.android.http.client.RedirectStrategy;
import cz.msebera.android.http.client.RequestDirector;
import cz.msebera.android.http.client.UserTokenHandler;
import cz.msebera.android.http.client.auth.AbortableHttpRequest;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.client.protocol.HttpClientParams;
import cz.msebera.android.http.client.ssl.URIUtils;
import cz.msebera.android.http.conn.BasicManagedEntity;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.http.conn.ConnectionReleaseTrigger;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.routing.BasicRouteDirector;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.HttpRouteDirector;
import cz.msebera.android.http.conn.routing.HttpRoutePlanner;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.entity.BufferedHttpEntity;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpProcessor;
import cz.msebera.android.http.execchain.HttpRequestExecutor;
import cz.msebera.android.http.impl.auth.BasicScheme;
import cz.msebera.android.http.impl.conn.ConnectionShutdownException;
import cz.msebera.android.http.message.AbstractHttpMessage;
import cz.msebera.android.http.message.BasicHttpRequest;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.EntityUtils;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import cz.msebera.android.http.util.HttpProtocolParams;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@Deprecated
public class DefaultRequestDirector
  implements RequestDirector
{
  private final HttpAuthenticator authenticator;
  protected final ClientConnectionManager connManager;
  private int execCount;
  protected final HttpProcessor httpProcessor;
  protected final ConnectionKeepAliveStrategy keepAliveStrategy;
  public HttpClientAndroidLog log;
  protected ManagedClientConnection managedConn;
  private final int maxRedirects;
  protected final HttpParams params;
  @Deprecated
  protected final AuthenticationHandler proxyAuthHandler;
  protected final AuthState proxyAuthState;
  protected final AuthenticationStrategy proxyAuthStrategy;
  private int redirectCount;
  @Deprecated
  protected final RedirectHandler redirectHandler;
  protected final RedirectStrategy redirectStrategy;
  protected final HttpRequestExecutor requestExec;
  protected final HttpRequestRetryHandler retryHandler;
  protected final ConnectionReuseStrategy reuseStrategy;
  protected final HttpRoutePlanner routePlanner;
  @Deprecated
  protected final AuthenticationHandler targetAuthHandler;
  protected final AuthState targetAuthState;
  protected final AuthenticationStrategy targetAuthStrategy;
  protected final UserTokenHandler userTokenHandler;
  private HttpHost virtualHost;
  
  public DefaultRequestDirector(HttpClientAndroidLog paramHttpClientAndroidLog, HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationStrategy paramAuthenticationStrategy1, AuthenticationStrategy paramAuthenticationStrategy2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpClientAndroidLog, "Log");
    Args.notNull(paramHttpRequestExecutor, "Request executor");
    Args.notNull(paramClientConnectionManager, "Client connection manager");
    Args.notNull(paramConnectionReuseStrategy, "Connection reuse strategy");
    Args.notNull(paramConnectionKeepAliveStrategy, "Connection keep alive strategy");
    Args.notNull(paramHttpRoutePlanner, "Route planner");
    Args.notNull(paramHttpProcessor, "HTTP protocol processor");
    Args.notNull(paramHttpRequestRetryHandler, "HTTP request retry handler");
    Args.notNull(paramRedirectStrategy, "Redirect strategy");
    Args.notNull(paramAuthenticationStrategy1, "Target authentication strategy");
    Args.notNull(paramAuthenticationStrategy2, "Proxy authentication strategy");
    Args.notNull(paramUserTokenHandler, "User token handler");
    Args.notNull(paramHttpParams, "HTTP parameters");
    log = paramHttpClientAndroidLog;
    authenticator = new HttpAuthenticator(paramHttpClientAndroidLog);
    requestExec = paramHttpRequestExecutor;
    connManager = paramClientConnectionManager;
    reuseStrategy = paramConnectionReuseStrategy;
    keepAliveStrategy = paramConnectionKeepAliveStrategy;
    routePlanner = paramHttpRoutePlanner;
    httpProcessor = paramHttpProcessor;
    retryHandler = paramHttpRequestRetryHandler;
    redirectStrategy = paramRedirectStrategy;
    targetAuthStrategy = paramAuthenticationStrategy1;
    proxyAuthStrategy = paramAuthenticationStrategy2;
    userTokenHandler = paramUserTokenHandler;
    params = paramHttpParams;
    if ((paramRedirectStrategy instanceof DefaultRedirectStrategyAdaptor)) {
      redirectHandler = ((DefaultRedirectStrategyAdaptor)paramRedirectStrategy).getHandler();
    } else {
      redirectHandler = null;
    }
    if ((paramAuthenticationStrategy1 instanceof AuthenticationStrategyAdaptor)) {
      targetAuthHandler = ((AuthenticationStrategyAdaptor)paramAuthenticationStrategy1).getHandler();
    } else {
      targetAuthHandler = null;
    }
    if ((paramAuthenticationStrategy2 instanceof AuthenticationStrategyAdaptor)) {
      proxyAuthHandler = ((AuthenticationStrategyAdaptor)paramAuthenticationStrategy2).getHandler();
    } else {
      proxyAuthHandler = null;
    }
    managedConn = null;
    execCount = 0;
    redirectCount = 0;
    targetAuthState = new AuthState();
    proxyAuthState = new AuthState();
    maxRedirects = params.getIntParameter("http.protocol.max-redirects", 100);
  }
  
  private void abortConnection()
  {
    ManagedClientConnection localManagedClientConnection = managedConn;
    if (localManagedClientConnection != null)
    {
      managedConn = null;
      try
      {
        localManagedClientConnection.abortConnection();
      }
      catch (IOException localIOException2)
      {
        if (log.isDebugEnabled()) {
          log.debug(localIOException2.getMessage(), localIOException2);
        }
      }
      try
      {
        localManagedClientConnection.releaseConnection();
        return;
      }
      catch (IOException localIOException1)
      {
        log.debug("Error releasing connection", localIOException1);
      }
    }
  }
  
  private void tryConnect(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
  {
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    paramRoutedRequest = paramRoutedRequest.getRequest();
    int i = 0;
    for (;;)
    {
      paramHttpContext.setAttribute("http.request", paramRoutedRequest);
      int j = i + 1;
      ManagedClientConnection localManagedClientConnection = managedConn;
      Object localObject;
      try
      {
        boolean bool = localManagedClientConnection.isOpen();
        if (!bool)
        {
          localManagedClientConnection = managedConn;
          localObject = params;
          localManagedClientConnection.open(localHttpRoute, paramHttpContext, (HttpParams)localObject);
        }
        else
        {
          localManagedClientConnection = managedConn;
          localObject = params;
          localManagedClientConnection.setSocketTimeout(HttpConnectionParams.getSoTimeout((HttpParams)localObject));
        }
        establishRoute(localHttpRoute, paramHttpContext);
        return;
      }
      catch (IOException localIOException1)
      {
        localObject = managedConn;
      }
      try
      {
        ((HttpConnection)localObject).close();
        HttpClientAndroidLog localHttpClientAndroidLog;
        if (retryHandler.retryRequest(localIOException1, j, paramHttpContext))
        {
          i = j;
          if (!log.isInfoEnabled()) {
            continue;
          }
          localObject = log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("I/O exception (");
          localStringBuilder.append(localIOException1.getClass().getName());
          localStringBuilder.append(") caught when connecting to ");
          localStringBuilder.append(localHttpRoute);
          localStringBuilder.append(": ");
          localStringBuilder.append(localIOException1.getMessage());
          ((HttpClientAndroidLog)localObject).info(localStringBuilder.toString());
          if (log.isDebugEnabled()) {
            log.debug(localIOException1.getMessage(), localIOException1);
          }
          localHttpClientAndroidLog = log;
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Retrying connect to ");
          ((StringBuilder)localObject).append(localHttpRoute);
          localHttpClientAndroidLog.info(((StringBuilder)localObject).toString());
          i = j;
          continue;
        }
        throw localHttpClientAndroidLog;
      }
      catch (IOException localIOException2)
      {
        for (;;) {}
      }
    }
  }
  
  private HttpResponse tryExecute(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
  {
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    paramRoutedRequest = null;
    for (;;)
    {
      execCount += 1;
      localRequestWrapper.incrementExecCount();
      if (!localRequestWrapper.isRepeatable())
      {
        log.debug("Cannot retry non-repeatable request");
        if (paramRoutedRequest != null) {
          throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", paramRoutedRequest);
        }
        throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
      }
      paramRoutedRequest = managedConn;
      try
      {
        boolean bool = paramRoutedRequest.isOpen();
        if (!bool)
        {
          bool = localHttpRoute.isTunnelled();
          if (!bool)
          {
            paramRoutedRequest = log;
            paramRoutedRequest.debug("Reopening the direct connection.");
            paramRoutedRequest = managedConn;
            localObject = params;
            paramRoutedRequest.open(localHttpRoute, paramHttpContext, (HttpParams)localObject);
          }
          else
          {
            paramRoutedRequest = log;
            paramRoutedRequest.debug("Proxied connection. Need to start over.");
            return null;
          }
        }
        paramRoutedRequest = log;
        bool = paramRoutedRequest.isDebugEnabled();
        if (bool)
        {
          paramRoutedRequest = log;
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Attempt ");
          int i = execCount;
          ((StringBuilder)localObject).append(i);
          ((StringBuilder)localObject).append(" to execute request");
          paramRoutedRequest.debug(((StringBuilder)localObject).toString());
        }
        paramRoutedRequest = requestExec;
        Object localObject = managedConn;
        paramRoutedRequest = paramRoutedRequest.execute(localRequestWrapper, (HttpClientConnection)localObject, paramHttpContext);
        return paramRoutedRequest;
      }
      catch (IOException localIOException)
      {
        log.debug("Closing the connection.");
        paramRoutedRequest = managedConn;
      }
      try
      {
        paramRoutedRequest.close();
        if (retryHandler.retryRequest(localIOException, localRequestWrapper.getExecCount(), paramHttpContext))
        {
          if (log.isInfoEnabled())
          {
            paramRoutedRequest = log;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("I/O exception (");
            localStringBuilder.append(localIOException.getClass().getName());
            localStringBuilder.append(") caught when processing request to ");
            localStringBuilder.append(localHttpRoute);
            localStringBuilder.append(": ");
            localStringBuilder.append(localIOException.getMessage());
            paramRoutedRequest.info(localStringBuilder.toString());
          }
          if (log.isDebugEnabled()) {
            log.debug(localIOException.getMessage(), localIOException);
          }
          paramRoutedRequest = localIOException;
          if (!log.isInfoEnabled()) {
            continue;
          }
          paramRoutedRequest = log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Retrying request to ");
          localStringBuilder.append(localHttpRoute);
          paramRoutedRequest.info(localStringBuilder.toString());
          paramRoutedRequest = localIOException;
          continue;
        }
        if ((localIOException instanceof NoHttpResponseException))
        {
          paramRoutedRequest = new StringBuilder();
          paramRoutedRequest.append(localHttpRoute.getTargetHost().toHostString());
          paramRoutedRequest.append(" failed to respond");
          paramRoutedRequest = new NoHttpResponseException(paramRoutedRequest.toString());
          paramRoutedRequest.setStackTrace(localIOException.getStackTrace());
          throw paramRoutedRequest;
        }
        throw localIOException;
      }
      catch (IOException paramRoutedRequest)
      {
        for (;;) {}
      }
    }
  }
  
  private RequestWrapper wrapRequest(HttpRequest paramHttpRequest)
  {
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest)) {
      return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest)paramHttpRequest);
    }
    return new RequestWrapper(paramHttpRequest);
  }
  
  protected HttpRequest createConnectRequest(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
  {
    paramHttpContext = paramHttpRoute.getTargetHost();
    paramHttpRoute = paramHttpContext.getHostName();
    int j = paramHttpContext.getPort();
    int i = j;
    if (j < 0) {
      i = connManager.getSchemeRegistry().getScheme(paramHttpContext.getSchemeName()).getDefaultPort();
    }
    paramHttpContext = new StringBuilder(paramHttpRoute.length() + 6);
    paramHttpContext.append(paramHttpRoute);
    paramHttpContext.append(':');
    paramHttpContext.append(Integer.toString(i));
    return new BasicHttpRequest("CONNECT", paramHttpContext.toString(), HttpProtocolParams.getVersion(params));
  }
  
  protected boolean createTunnelToProxy(HttpRoute paramHttpRoute, int paramInt, HttpContext paramHttpContext)
  {
    throw new HttpException("Proxy chains are not supported.");
  }
  
  protected boolean createTunnelToTarget(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
  {
    HttpHost localHttpHost1 = paramHttpRoute.getProxyHost();
    HttpHost localHttpHost2 = paramHttpRoute.getTargetHost();
    Object localObject;
    for (;;)
    {
      if (!managedConn.isOpen()) {
        managedConn.open(paramHttpRoute, paramHttpContext, params);
      }
      localObject = createConnectRequest(paramHttpRoute, paramHttpContext);
      ((HttpMessage)localObject).setParams(params);
      paramHttpContext.setAttribute("http.target_host", localHttpHost2);
      paramHttpContext.setAttribute("http.route", paramHttpRoute);
      paramHttpContext.setAttribute("http.proxy_host", localHttpHost1);
      paramHttpContext.setAttribute("http.connection", managedConn);
      paramHttpContext.setAttribute("http.request", localObject);
      requestExec.preProcess((HttpRequest)localObject, httpProcessor, paramHttpContext);
      localObject = requestExec.execute((HttpRequest)localObject, managedConn, paramHttpContext);
      ((HttpMessage)localObject).setParams(params);
      requestExec.postProcess((HttpResponse)localObject, httpProcessor, paramHttpContext);
      if (((HttpResponse)localObject).getStatusLine().getStatusCode() < 200) {
        break label381;
      }
      if (HttpClientParams.isAuthenticating(params))
      {
        if ((!authenticator.isAuthenticationRequested(localHttpHost1, (HttpResponse)localObject, proxyAuthStrategy, proxyAuthState, paramHttpContext)) || (!authenticator.authenticate(localHttpHost1, (HttpResponse)localObject, proxyAuthStrategy, proxyAuthState, paramHttpContext))) {
          break;
        }
        if (reuseStrategy.keepAlive((HttpResponse)localObject, paramHttpContext))
        {
          log.debug("Connection kept alive");
          EntityUtils.consume(((HttpResponse)localObject).getEntity());
        }
        else
        {
          managedConn.close();
        }
      }
    }
    if (((HttpResponse)localObject).getStatusLine().getStatusCode() > 299)
    {
      paramHttpRoute = ((HttpResponse)localObject).getEntity();
      if (paramHttpRoute != null) {
        ((HttpResponse)localObject).setEntity(new BufferedHttpEntity(paramHttpRoute));
      }
      managedConn.close();
      paramHttpRoute = new StringBuilder();
      paramHttpRoute.append("CONNECT refused by proxy: ");
      paramHttpRoute.append(((HttpResponse)localObject).getStatusLine());
      throw new TunnelRefusedException(paramHttpRoute.toString(), (HttpResponse)localObject);
    }
    managedConn.markReusable();
    return false;
    label381:
    paramHttpRoute = new StringBuilder();
    paramHttpRoute.append("Unexpected response to CONNECT request: ");
    paramHttpRoute.append(((HttpResponse)localObject).getStatusLine());
    paramHttpRoute = new HttpException(paramHttpRoute.toString());
    throw paramHttpRoute;
  }
  
  protected HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    HttpRoutePlanner localHttpRoutePlanner = routePlanner;
    if (paramHttpHost == null) {
      paramHttpHost = (HttpHost)paramHttpRequest.getParams().getParameter("http.default-host");
    }
    return localHttpRoutePlanner.determineRoute(paramHttpHost, paramHttpRequest, paramHttpContext);
  }
  
  protected void establishRoute(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
  {
    BasicRouteDirector localBasicRouteDirector = new BasicRouteDirector();
    HttpRoute localHttpRoute;
    int i;
    do
    {
      localHttpRoute = managedConn.getRoute();
      i = localBasicRouteDirector.nextStep(paramHttpRoute, localHttpRoute);
      switch (i)
      {
      default: 
        paramHttpRoute = new StringBuilder();
        paramHttpRoute.append("Unknown step indicator ");
        paramHttpRoute.append(i);
        paramHttpRoute.append(" from RouteDirector.");
        throw new IllegalStateException(paramHttpRoute.toString());
      case 5: 
        managedConn.layerProtocol(paramHttpContext, params);
        break;
      case 4: 
        createTunnelToProxy(paramHttpRoute, localHttpRoute.getHopCount() - 1, paramHttpContext);
        throw new NullPointerException("Null throw statement replaced by Soot");
      case 3: 
        boolean bool = createTunnelToTarget(paramHttpRoute, paramHttpContext);
        log.debug("Tunnel to target created.");
        managedConn.tunnelTarget(bool, params);
        break;
      case 1: 
      case 2: 
        managedConn.open(paramHttpRoute, paramHttpContext, params);
      }
    } while (i > 0);
    return;
    paramHttpContext = new StringBuilder();
    paramHttpContext.append("Unable to establish route: planned = ");
    paramHttpContext.append(paramHttpRoute);
    paramHttpContext.append("; current = ");
    paramHttpContext.append(localHttpRoute);
    paramHttpRoute = new HttpException(paramHttpContext.toString());
    throw paramHttpRoute;
  }
  
  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    paramHttpContext.setAttribute("http.auth.target-scope", targetAuthState);
    paramHttpContext.setAttribute("http.auth.proxy-scope", proxyAuthState);
    Object localObject2 = wrapRequest(paramHttpRequest);
    ((AbstractHttpMessage)localObject2).setParams(params);
    Object localObject3 = determineRoute(paramHttpHost, (HttpRequest)localObject2, paramHttpContext);
    virtualHost = ((HttpHost)((AbstractHttpMessage)localObject2).getParams().getParameter("http.virtual-host"));
    Object localObject1 = virtualHost;
    if ((localObject1 != null) && (((HttpHost)localObject1).getPort() == -1))
    {
      if (paramHttpHost != null) {
        localObject1 = paramHttpHost;
      } else {
        localObject1 = ((HttpRoute)localObject3).getTargetHost();
      }
      i = ((HttpHost)localObject1).getPort();
      if (i != -1) {
        virtualHost = new HttpHost(virtualHost.getHostName(), i, virtualHost.getSchemeName());
      }
    }
    localObject3 = new RoutedRequest((RequestWrapper)localObject2, (HttpRoute)localObject3);
    localObject2 = null;
    int i = 0;
    boolean bool1 = false;
    for (;;)
    {
      if (i == 0) {}
      for (;;)
      {
        boolean bool2;
        try
        {
          localObject2 = ((RoutedRequest)localObject3).getRequest();
          localObject5 = ((RoutedRequest)localObject3).getRoute();
          localObject7 = paramHttpContext.getAttribute("http.user-token");
          localObject4 = localObject7;
          localObject1 = managedConn;
          if (localObject1 == null)
          {
            localObject1 = connManager;
            localObject1 = ((ClientConnectionManager)localObject1).requestConnection((HttpRoute)localObject5, localObject7);
            if ((paramHttpRequest instanceof AbortableHttpRequest))
            {
              localObject6 = (AbortableHttpRequest)paramHttpRequest;
              ((AbortableHttpRequest)localObject6).setConnectionRequest((ClientConnectionRequest)localObject1);
            }
            localObject6 = params;
            l = HttpClientParams.getConnectionManagerTimeout((HttpParams)localObject6);
            localObject6 = TimeUnit.MILLISECONDS;
          }
        }
        catch (RuntimeException paramHttpHost)
        {
          Object localObject5;
          Object localObject7;
          Object localObject4;
          Object localObject6;
          long l;
          Object localObject8;
          boolean bool3;
          int j;
        }
        catch (IOException paramHttpHost) {}catch (HttpException paramHttpHost) {}catch (ConnectionShutdownException paramHttpHost) {}
        try
        {
          localObject1 = ((ClientConnectionRequest)localObject1).getConnection(l, (TimeUnit)localObject6);
          managedConn = ((ManagedClientConnection)localObject1);
          localObject1 = params;
          bool2 = HttpConnectionParams.isStaleCheckingEnabled((HttpParams)localObject1);
          if (!bool2) {
            continue;
          }
          localObject1 = managedConn;
          bool2 = ((HttpConnection)localObject1).isOpen();
          if (!bool2) {
            continue;
          }
          localObject1 = log;
          ((HttpClientAndroidLog)localObject1).debug("Stale connection check");
          localObject1 = managedConn;
          bool2 = ((HttpConnection)localObject1).isStale();
          if (!bool2) {
            continue;
          }
          localObject1 = log;
          ((HttpClientAndroidLog)localObject1).debug("Stale connection detected");
          localObject1 = managedConn;
          ((HttpConnection)localObject1).close();
        }
        catch (InterruptedException paramHttpHost) {}
      }
      Thread.currentThread().interrupt();
      paramHttpHost = new InterruptedIOException();
      throw paramHttpHost;
      if ((paramHttpRequest instanceof AbortableHttpRequest))
      {
        localObject1 = (AbortableHttpRequest)paramHttpRequest;
        localObject6 = managedConn;
        ((AbortableHttpRequest)localObject1).setReleaseTrigger((ConnectionReleaseTrigger)localObject6);
      }
      try
      {
        tryConnect((RoutedRequest)localObject3, paramHttpContext);
        localObject1 = ((RequestWrapper)localObject2).getURI().getUserInfo();
        if (localObject1 != null)
        {
          localObject6 = targetAuthState;
          localObject8 = new BasicScheme();
          ((AuthState)localObject6).update((AuthScheme)localObject8, new UsernamePasswordCredentials((String)localObject1));
        }
        localObject1 = virtualHost;
        if (localObject1 != null)
        {
          paramHttpHost = virtualHost;
        }
        else
        {
          localObject1 = ((RequestWrapper)localObject2).getURI();
          bool2 = ((URI)localObject1).isAbsolute();
          if (bool2) {
            paramHttpHost = URIUtils.extractHost((URI)localObject1);
          }
        }
        localObject1 = paramHttpHost;
        if (paramHttpHost == null) {
          localObject1 = ((HttpRoute)localObject5).getTargetHost();
        }
        ((RequestWrapper)localObject2).resetHeaders();
        rewriteRequestURI((RequestWrapper)localObject2, (HttpRoute)localObject5);
        paramHttpContext.setAttribute("http.target_host", localObject1);
        paramHttpContext.setAttribute("http.route", localObject5);
        paramHttpHost = managedConn;
        paramHttpContext.setAttribute("http.connection", paramHttpHost);
        paramHttpHost = requestExec;
        localObject5 = httpProcessor;
        paramHttpHost.preProcess((HttpRequest)localObject2, (HttpProcessor)localObject5, paramHttpContext);
        localObject2 = tryExecute((RoutedRequest)localObject3, paramHttpContext);
        localObject5 = localObject2;
        if (localObject2 == null)
        {
          localObject2 = localObject5;
          paramHttpHost = (HttpHost)localObject1;
        }
        else
        {
          paramHttpHost = params;
          ((HttpMessage)localObject2).setParams(paramHttpHost);
          paramHttpHost = requestExec;
          localObject6 = httpProcessor;
          paramHttpHost.postProcess((HttpResponse)localObject2, (HttpProcessor)localObject6, paramHttpContext);
          paramHttpHost = reuseStrategy;
          bool1 = paramHttpHost.keepAlive((HttpResponse)localObject2, paramHttpContext);
          bool2 = bool1;
          if (bool1)
          {
            paramHttpHost = keepAliveStrategy;
            l = paramHttpHost.getKeepAliveDuration((HttpResponse)localObject2, paramHttpContext);
            paramHttpHost = log;
            bool3 = paramHttpHost.isDebugEnabled();
            if (bool3)
            {
              if (l > 0L)
              {
                paramHttpHost = new StringBuilder();
                paramHttpHost.append("for ");
                paramHttpHost.append(l);
                paramHttpHost.append(" ");
                localObject6 = TimeUnit.MILLISECONDS;
                paramHttpHost.append(localObject6);
                paramHttpHost = paramHttpHost.toString();
              }
              else
              {
                paramHttpHost = "indefinitely";
              }
              localObject6 = log;
              localObject8 = new StringBuilder();
              ((StringBuilder)localObject8).append("Connection can be kept alive ");
              ((StringBuilder)localObject8).append(paramHttpHost);
              ((HttpClientAndroidLog)localObject6).debug(((StringBuilder)localObject8).toString());
            }
            paramHttpHost = managedConn;
            localObject6 = TimeUnit.MILLISECONDS;
            paramHttpHost.setIdleDuration(l, (TimeUnit)localObject6);
          }
          localObject6 = handleResponse((RoutedRequest)localObject3, (HttpResponse)localObject2, paramHttpContext);
          if (localObject6 == null)
          {
            j = 1;
            localObject6 = localObject3;
          }
          else
          {
            if (bool1)
            {
              EntityUtils.consume(((HttpResponse)localObject2).getEntity());
              paramHttpHost = managedConn;
              paramHttpHost.markReusable();
            }
            else
            {
              paramHttpHost = managedConn;
              paramHttpHost.close();
              paramHttpHost = proxyAuthState;
              paramHttpHost = paramHttpHost.getState();
              localObject2 = AuthProtocolState.CHALLENGED;
              j = paramHttpHost.compareTo((Enum)localObject2);
              if (j > 0)
              {
                paramHttpHost = proxyAuthState;
                paramHttpHost = paramHttpHost.getAuthScheme();
                if (paramHttpHost != null)
                {
                  paramHttpHost = proxyAuthState;
                  bool1 = paramHttpHost.getAuthScheme().isConnectionBased();
                  if (bool1)
                  {
                    paramHttpHost = log;
                    paramHttpHost.debug("Resetting proxy auth state");
                    paramHttpHost = proxyAuthState;
                    paramHttpHost.reset();
                  }
                }
              }
              paramHttpHost = targetAuthState;
              paramHttpHost = paramHttpHost.getState();
              localObject2 = AuthProtocolState.CHALLENGED;
              j = paramHttpHost.compareTo((Enum)localObject2);
              if (j > 0)
              {
                paramHttpHost = targetAuthState;
                paramHttpHost = paramHttpHost.getAuthScheme();
                if (paramHttpHost != null)
                {
                  paramHttpHost = targetAuthState;
                  bool1 = paramHttpHost.getAuthScheme().isConnectionBased();
                  if (bool1)
                  {
                    paramHttpHost = log;
                    paramHttpHost.debug("Resetting target auth state");
                    paramHttpHost = targetAuthState;
                    paramHttpHost.reset();
                  }
                }
              }
            }
            bool1 = ((RoutedRequest)localObject6).getRoute().equals(((RoutedRequest)localObject3).getRoute());
            if (!bool1) {
              releaseConnection();
            }
            j = i;
          }
          localObject8 = managedConn;
          localObject3 = localObject6;
          localObject2 = localObject5;
          i = j;
          bool1 = bool2;
          paramHttpHost = (HttpHost)localObject1;
          if (localObject8 != null)
          {
            if (localObject7 == null)
            {
              paramHttpHost = userTokenHandler;
              paramHttpHost = paramHttpHost.getUserToken(paramHttpContext);
              localObject4 = paramHttpHost;
              paramHttpContext.setAttribute("http.user-token", paramHttpHost);
            }
            localObject3 = localObject6;
            localObject2 = localObject5;
            i = j;
            bool1 = bool2;
            paramHttpHost = (HttpHost)localObject1;
            if (localObject4 != null)
            {
              paramHttpHost = managedConn;
              paramHttpHost.setState(localObject4);
              localObject3 = localObject6;
              localObject2 = localObject5;
              i = j;
              bool1 = bool2;
              paramHttpHost = (HttpHost)localObject1;
            }
          }
        }
      }
      catch (TunnelRefusedException paramHttpHost)
      {
        paramHttpRequest = log;
        bool2 = paramHttpRequest.isDebugEnabled();
        if (bool2)
        {
          paramHttpRequest = log;
          paramHttpRequest.debug(paramHttpHost.getMessage());
        }
        localObject2 = paramHttpHost.getResponse();
      }
    }
    if (localObject2 != null)
    {
      paramHttpHost = ((HttpResponse)localObject2).getEntity();
      if (paramHttpHost != null)
      {
        bool2 = ((HttpResponse)localObject2).getEntity().isStreaming();
        if (bool2)
        {
          paramHttpHost = ((HttpResponse)localObject2).getEntity();
          paramHttpRequest = managedConn;
          ((HttpResponse)localObject2).setEntity(new BasicManagedEntity(paramHttpHost, paramHttpRequest, bool1));
          return localObject2;
        }
      }
    }
    if (bool1)
    {
      paramHttpHost = managedConn;
      paramHttpHost.markReusable();
    }
    releaseConnection();
    return localObject2;
    abortConnection();
    throw paramHttpHost;
    abortConnection();
    throw paramHttpHost;
    abortConnection();
    throw paramHttpHost;
    paramHttpRequest = new InterruptedIOException("Connection has been shut down");
    paramHttpRequest.initCause(paramHttpHost);
    throw paramHttpRequest;
  }
  
  protected RoutedRequest handleResponse(RoutedRequest paramRoutedRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    HttpParams localHttpParams = localRequestWrapper.getParams();
    Object localObject2;
    Object localObject1;
    if (HttpClientParams.isAuthenticating(localHttpParams))
    {
      localObject2 = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = localHttpRoute.getTargetHost();
      }
      localObject2 = localObject1;
      if (((HttpHost)localObject1).getPort() < 0)
      {
        localObject2 = connManager.getSchemeRegistry().getScheme((HttpHost)localObject1);
        localObject2 = new HttpHost(((HttpHost)localObject1).getHostName(), ((Scheme)localObject2).getDefaultPort(), ((HttpHost)localObject1).getSchemeName());
      }
      boolean bool1 = authenticator.isAuthenticationRequested((HttpHost)localObject2, paramHttpResponse, targetAuthStrategy, targetAuthState, paramHttpContext);
      HttpHost localHttpHost = localHttpRoute.getProxyHost();
      localObject1 = localHttpHost;
      if (localHttpHost == null) {
        localObject1 = localHttpRoute.getTargetHost();
      }
      boolean bool2 = authenticator.isAuthenticationRequested((HttpHost)localObject1, paramHttpResponse, proxyAuthStrategy, proxyAuthState, paramHttpContext);
      if ((bool1) && (authenticator.authenticate((HttpHost)localObject2, paramHttpResponse, targetAuthStrategy, targetAuthState, paramHttpContext))) {
        return paramRoutedRequest;
      }
      if ((bool2) && (authenticator.authenticate((HttpHost)localObject1, paramHttpResponse, proxyAuthStrategy, proxyAuthState, paramHttpContext))) {
        return paramRoutedRequest;
      }
    }
    if ((HttpClientParams.isRedirecting(localHttpParams)) && (redirectStrategy.isRedirected(localRequestWrapper, paramHttpResponse, paramHttpContext)))
    {
      int i = redirectCount;
      if (i < maxRedirects)
      {
        redirectCount = (i + 1);
        virtualHost = null;
        paramHttpResponse = redirectStrategy.getRedirect(localRequestWrapper, paramHttpResponse, paramHttpContext);
        paramHttpResponse.setHeaders(localRequestWrapper.getOriginal().getAllHeaders());
        paramRoutedRequest = paramHttpResponse.getURI();
        localObject1 = URIUtils.extractHost(paramRoutedRequest);
        if (localObject1 != null)
        {
          if (!localHttpRoute.getTargetHost().equals(localObject1))
          {
            log.debug("Resetting target auth state");
            targetAuthState.reset();
            localObject2 = proxyAuthState.getAuthScheme();
            if ((localObject2 != null) && (((AuthScheme)localObject2).isConnectionBased()))
            {
              log.debug("Resetting proxy auth state");
              proxyAuthState.reset();
            }
          }
          paramHttpResponse = wrapRequest(paramHttpResponse);
          paramHttpResponse.setParams(localHttpParams);
          paramHttpContext = determineRoute((HttpHost)localObject1, paramHttpResponse, paramHttpContext);
          paramHttpResponse = new RoutedRequest(paramHttpResponse, paramHttpContext);
          if (log.isDebugEnabled())
          {
            localObject1 = log;
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Redirecting to '");
            ((StringBuilder)localObject2).append(paramRoutedRequest);
            ((StringBuilder)localObject2).append("' via ");
            ((StringBuilder)localObject2).append(paramHttpContext);
            ((HttpClientAndroidLog)localObject1).debug(((StringBuilder)localObject2).toString());
            return paramHttpResponse;
          }
        }
        else
        {
          paramHttpResponse = new StringBuilder();
          paramHttpResponse.append("Redirect URI does not specify a valid host name: ");
          paramHttpResponse.append(paramRoutedRequest);
          throw new ProtocolException(paramHttpResponse.toString());
        }
      }
      else
      {
        paramRoutedRequest = new StringBuilder();
        paramRoutedRequest.append("Maximum redirects (");
        paramRoutedRequest.append(maxRedirects);
        paramRoutedRequest.append(") exceeded");
        throw new RedirectException(paramRoutedRequest.toString());
      }
    }
    else
    {
      return null;
    }
    return paramHttpResponse;
  }
  
  protected void releaseConnection()
  {
    ManagedClientConnection localManagedClientConnection = managedConn;
    try
    {
      localManagedClientConnection.releaseConnection();
    }
    catch (IOException localIOException)
    {
      log.debug("IOException releasing connection", localIOException);
    }
    managedConn = null;
  }
  
  protected void rewriteRequestURI(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
  {
    try
    {
      localObject = paramRequestWrapper.getURI();
      HttpHost localHttpHost = paramHttpRoute.getProxyHost();
      boolean bool;
      if (localHttpHost != null)
      {
        bool = paramHttpRoute.isTunnelled();
        if (!bool)
        {
          bool = ((URI)localObject).isAbsolute();
          if (!bool)
          {
            paramHttpRoute = URIUtils.rewriteURI((URI)localObject, paramHttpRoute.getTargetHost(), true);
            break label77;
          }
        }
      }
      do
      {
        paramHttpRoute = URIUtils.rewriteURI((URI)localObject);
        break;
        bool = ((URI)localObject).isAbsolute();
      } while (!bool);
      paramHttpRoute = URIUtils.rewriteURI((URI)localObject, null, true);
      label77:
      paramRequestWrapper.setURI(paramHttpRoute);
      return;
    }
    catch (URISyntaxException paramHttpRoute)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid URI: ");
      ((StringBuilder)localObject).append(paramRequestWrapper.getRequestLine().getUri());
      paramRequestWrapper = new ProtocolException(((StringBuilder)localObject).toString(), paramHttpRoute);
      throw paramRequestWrapper;
    }
  }
}
