package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.ConnectionReuseStrategy;
import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponseInterceptor;
import cz.msebera.android.http.auth.AuthSchemeRegistry;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.client.BackoffManager;
import cz.msebera.android.http.client.ClientProtocolException;
import cz.msebera.android.http.client.ConnectionBackoffStrategy;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.client.HttpRequestRetryHandler;
import cz.msebera.android.http.client.RedirectHandler;
import cz.msebera.android.http.client.RedirectStrategy;
import cz.msebera.android.http.client.RequestDirector;
import cz.msebera.android.http.client.UserTokenHandler;
import cz.msebera.android.http.client.auth.CloseableHttpResponse;
import cz.msebera.android.http.client.protocol.HttpClientParamConfig;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ClientConnectionManagerFactory;
import cz.msebera.android.http.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.HttpRoutePlanner;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.execchain.BasicHttpContext;
import cz.msebera.android.http.execchain.BasicHttpProcessor;
import cz.msebera.android.http.execchain.DefaultedHttpContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpProcessor;
import cz.msebera.android.http.execchain.HttpRequestExecutor;
import cz.msebera.android.http.execchain.ImmutableHttpProcessor;
import cz.msebera.android.http.impl.DefaultConnectionReuseStrategy;
import cz.msebera.android.http.impl.auth.BasicSchemeFactory;
import cz.msebera.android.http.impl.auth.DigestSchemeFactory;
import cz.msebera.android.http.impl.auth.NTLMSchemeFactory;
import cz.msebera.android.http.impl.conn.BasicClientConnectionManager;
import cz.msebera.android.http.impl.conn.ProxySelectorRoutePlanner;
import cz.msebera.android.http.impl.conn.SchemeRegistryFactory;
import cz.msebera.android.http.impl.cookie.BestMatchSpecFactory;
import cz.msebera.android.http.impl.cookie.BrowserCompatSpecFactory;
import cz.msebera.android.http.impl.cookie.IgnoreSpecFactory;
import cz.msebera.android.http.impl.cookie.NetscapeDraftSpecFactory;
import cz.msebera.android.http.impl.cookie.RFC2109SpecFactory;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;

@Deprecated
public abstract class AbstractHttpClient
  extends CloseableHttpClient
{
  private BackoffManager backoffManager;
  private ClientConnectionManager connManager;
  private ConnectionBackoffStrategy connectionBackoffStrategy;
  private CredentialsProvider cookieStore;
  private CookieStore credsProvider;
  private HttpParams defaultParams;
  private ConnectionKeepAliveStrategy keepAliveStrategy;
  public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
  private BasicHttpProcessor mutableProcessor;
  private ImmutableHttpProcessor protocolProcessor;
  private AuthenticationStrategy proxyAuthStrategy;
  private RedirectStrategy redirectStrategy;
  private HttpRequestExecutor requestExec;
  private HttpRequestRetryHandler retryHandler;
  private ConnectionReuseStrategy reuseStrategy;
  private AuthSchemeRegistry supportedAuthSchemes;
  private cz.msebera.android.http.cookie.CookieSpecRegistry supportedCookieSpecs;
  private UserTokenHandler targetAuthHandler;
  private HttpRoutePlanner targetAuthStrategy;
  private AuthenticationStrategy userTokenHandler;
  
  protected AbstractHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    defaultParams = paramHttpParams;
    connManager = paramClientConnectionManager;
  }
  
  private HttpProcessor getProtocolProcessor()
  {
    try
    {
      if (protocolProcessor == null)
      {
        localObject = getHttpProcessor();
        int k = ((BasicHttpProcessor)localObject).getRequestInterceptorCount();
        HttpRequestInterceptor[] arrayOfHttpRequestInterceptor = new HttpRequestInterceptor[k];
        int j = 0;
        int i = 0;
        while (i < k)
        {
          arrayOfHttpRequestInterceptor[i] = ((BasicHttpProcessor)localObject).getRequestInterceptor(i);
          i += 1;
        }
        k = ((BasicHttpProcessor)localObject).getResponseInterceptorCount();
        HttpResponseInterceptor[] arrayOfHttpResponseInterceptor = new HttpResponseInterceptor[k];
        i = j;
        while (i < k)
        {
          arrayOfHttpResponseInterceptor[i] = ((BasicHttpProcessor)localObject).getResponseInterceptor(i);
          i += 1;
        }
        protocolProcessor = new ImmutableHttpProcessor(arrayOfHttpRequestInterceptor, arrayOfHttpResponseInterceptor);
      }
      Object localObject = protocolProcessor;
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    try
    {
      getHttpProcessor().addInterceptor(paramHttpRequestInterceptor);
      protocolProcessor = null;
      return;
    }
    catch (Throwable paramHttpRequestInterceptor)
    {
      throw paramHttpRequestInterceptor;
    }
  }
  
  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    try
    {
      getHttpProcessor().addInterceptor(paramHttpRequestInterceptor, paramInt);
      protocolProcessor = null;
      return;
    }
    catch (Throwable paramHttpRequestInterceptor)
    {
      throw paramHttpRequestInterceptor;
    }
  }
  
  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    try
    {
      getHttpProcessor().addInterceptor(paramHttpResponseInterceptor);
      protocolProcessor = null;
      return;
    }
    catch (Throwable paramHttpResponseInterceptor)
    {
      throw paramHttpResponseInterceptor;
    }
  }
  
  public void close()
  {
    getConnectionManager().shutdown();
  }
  
  protected AuthSchemeRegistry createAuthSchemeRegistry()
  {
    AuthSchemeRegistry localAuthSchemeRegistry = new AuthSchemeRegistry();
    localAuthSchemeRegistry.register("Basic", new DigestSchemeFactory());
    localAuthSchemeRegistry.register("Digest", new BasicSchemeFactory());
    localAuthSchemeRegistry.register("NTLM", new NTLMSchemeFactory());
    return localAuthSchemeRegistry;
  }
  
  protected ClientConnectionManager createClientConnectionManager()
  {
    Object localObject3 = SchemeRegistryFactory.createDefault();
    HttpParams localHttpParams = getParams();
    Object localObject1 = (String)localHttpParams.getParameter("http.connection-manager.factory-class-name");
    if (localObject1 != null) {}
    try
    {
      try
      {
        Object localObject4 = Class.forName((String)localObject1).newInstance();
        localObject1 = (ClientConnectionManagerFactory)localObject4;
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new InstantiationError(localInstantiationException.getMessage());
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new IllegalAccessError(localIllegalAccessException.getMessage());
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append("Invalid class name: ");
    ((StringBuilder)localObject3).append(localIllegalAccessException);
    throw new IllegalStateException(((StringBuilder)localObject3).toString());
    localObject2 = null;
    if (localObject2 != null) {
      return localObject2.newInstance(localHttpParams, (SchemeRegistry)localObject3);
    }
    return new BasicClientConnectionManager((SchemeRegistry)localObject3);
  }
  
  protected RequestDirector createClientRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationStrategy paramAuthenticationStrategy1, AuthenticationStrategy paramAuthenticationStrategy2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    return new DefaultRequestDirector(log, paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectStrategy, paramAuthenticationStrategy1, paramAuthenticationStrategy2, paramUserTokenHandler, paramHttpParams);
  }
  
  protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy()
  {
    return new DefaultConnectionKeepAliveStrategy();
  }
  
  protected ConnectionReuseStrategy createConnectionReuseStrategy()
  {
    return new DefaultConnectionReuseStrategy();
  }
  
  protected cz.msebera.android.http.cookie.CookieSpecRegistry createCookieSpecRegistry()
  {
    cz.msebera.android.http.cookie.CookieSpecRegistry localCookieSpecRegistry = new cz.msebera.android.http.cookie.CookieSpecRegistry();
    localCookieSpecRegistry.register("default", new NetscapeDraftSpecFactory());
    localCookieSpecRegistry.register("best-match", new NetscapeDraftSpecFactory());
    localCookieSpecRegistry.register("compatibility", new BrowserCompatSpecFactory());
    localCookieSpecRegistry.register("netscape", new RFC2109SpecFactory());
    localCookieSpecRegistry.register("rfc2109", new cz.msebera.android.http.impl.cookie.CookieSpecRegistry());
    localCookieSpecRegistry.register("rfc2965", new BestMatchSpecFactory());
    localCookieSpecRegistry.register("ignoreCookies", new IgnoreSpecFactory());
    return localCookieSpecRegistry;
  }
  
  protected CookieStore createCookieStore()
  {
    return new BasicCookieStore();
  }
  
  protected CredentialsProvider createCredentialsProvider()
  {
    return new BasicCredentialsProvider();
  }
  
  protected HttpContext createHttpContext()
  {
    BasicHttpContext localBasicHttpContext = new BasicHttpContext();
    localBasicHttpContext.setAttribute("http.scheme-registry", getConnectionManager().getSchemeRegistry());
    localBasicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
    localBasicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
    localBasicHttpContext.setAttribute("http.cookie-store", getCredentialsProvider());
    localBasicHttpContext.setAttribute("http.auth.credentials-provider", getCookieStore());
    return localBasicHttpContext;
  }
  
  protected abstract HttpParams createHttpParams();
  
  protected abstract BasicHttpProcessor createHttpProcessor();
  
  protected HttpRequestRetryHandler createHttpRequestRetryHandler()
  {
    return new DefaultHttpRequestRetryHandler();
  }
  
  protected HttpRoutePlanner createHttpRoutePlanner()
  {
    return new ProxySelectorRoutePlanner(getConnectionManager().getSchemeRegistry());
  }
  
  protected AuthenticationStrategy createProxyAuthenticationStrategy()
  {
    return new ProxyAuthenticationStrategy();
  }
  
  protected HttpRequestExecutor createRequestExecutor()
  {
    return new HttpRequestExecutor();
  }
  
  protected AuthenticationStrategy createTargetAuthenticationStrategy()
  {
    return new TargetAuthenticationStrategy();
  }
  
  protected UserTokenHandler createUserTokenHandler()
  {
    return new DefaultUserTokenHandler();
  }
  
  protected HttpParams determineParams(HttpRequest paramHttpRequest)
  {
    return new ClientParamsStack(null, getParams(), paramHttpRequest.getParams(), null);
  }
  
  protected final CloseableHttpResponse doExecute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    try
    {
      Object localObject = createHttpContext();
      if (paramHttpContext == null) {
        paramHttpContext = (HttpContext)localObject;
      } else {
        paramHttpContext = new DefaultedHttpContext(paramHttpContext, (HttpContext)localObject);
      }
      localObject = determineParams(paramHttpRequest);
      paramHttpContext.setAttribute("http.request-config", HttpClientParamConfig.getRequestConfig((HttpParams)localObject));
      RequestDirector localRequestDirector = createClientRequestDirector(getRequestExecutor(), getConnectionManager(), getConnectionReuseStrategy(), getConnectionKeepAliveStrategy(), getTargetAuthenticationStrategy(), getProtocolProcessor(), getHttpRequestRetryHandler(), getRedirectStrategy(), getProxyAuthenticationStrategy(), getUserTokenHandler(), getTargetAuthenticationHandler(), (HttpParams)localObject);
      HttpRoutePlanner localHttpRoutePlanner = getTargetAuthenticationStrategy();
      ConnectionBackoffStrategy localConnectionBackoffStrategy = getConnectionBackoffStrategy();
      BackoffManager localBackoffManager = getBackoffManager();
      if ((localConnectionBackoffStrategy != null) && (localBackoffManager != null)) {
        if (paramHttpHost != null) {
          localObject = paramHttpHost;
        }
      }
      for (;;)
      {
        try
        {
          localObject = determineParams(paramHttpRequest).getParameter("http.default-host");
          localObject = (HttpHost)localObject;
        }
        catch (HttpException paramHttpHost)
        {
          boolean bool;
          throw new ClientProtocolException(paramHttpHost);
        }
      }
      localObject = localHttpRoutePlanner.determineRoute((HttpHost)localObject, paramHttpRequest, paramHttpContext);
      try
      {
        paramHttpHost = CloseableHttpResponseProxy.newProxy(localRequestDirector.execute(paramHttpHost, paramHttpRequest, paramHttpContext));
        bool = localConnectionBackoffStrategy.shouldBackoff(paramHttpHost);
        if (bool)
        {
          localBackoffManager.backOff((HttpRoute)localObject);
          return paramHttpHost;
        }
        localBackoffManager.probe((HttpRoute)localObject);
        return paramHttpHost;
      }
      catch (Exception paramHttpHost)
      {
        bool = localConnectionBackoffStrategy.shouldBackoff(paramHttpHost);
        if (bool) {
          localBackoffManager.backOff((HttpRoute)localObject);
        }
        if (!(paramHttpHost instanceof HttpException))
        {
          if ((paramHttpHost instanceof IOException)) {
            throw ((IOException)paramHttpHost);
          }
          paramHttpHost = new UndeclaredThrowableException(paramHttpHost);
          throw paramHttpHost;
        }
        paramHttpHost = (HttpException)paramHttpHost;
        throw paramHttpHost;
      }
      catch (RuntimeException paramHttpHost)
      {
        bool = localConnectionBackoffStrategy.shouldBackoff(paramHttpHost);
        if (bool) {
          localBackoffManager.backOff((HttpRoute)localObject);
        }
        throw paramHttpHost;
      }
      paramHttpHost = CloseableHttpResponseProxy.newProxy(localRequestDirector.execute(paramHttpHost, paramHttpRequest, paramHttpContext));
      return paramHttpHost;
    }
    catch (Throwable paramHttpHost)
    {
      throw paramHttpHost;
    }
  }
  
  public final AuthSchemeRegistry getAuthSchemes()
  {
    try
    {
      if (supportedAuthSchemes == null) {
        supportedAuthSchemes = createAuthSchemeRegistry();
      }
      AuthSchemeRegistry localAuthSchemeRegistry = supportedAuthSchemes;
      return localAuthSchemeRegistry;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final BackoffManager getBackoffManager()
  {
    try
    {
      BackoffManager localBackoffManager = backoffManager;
      return localBackoffManager;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final ConnectionBackoffStrategy getConnectionBackoffStrategy()
  {
    try
    {
      ConnectionBackoffStrategy localConnectionBackoffStrategy = connectionBackoffStrategy;
      return localConnectionBackoffStrategy;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy()
  {
    try
    {
      if (keepAliveStrategy == null) {
        keepAliveStrategy = createConnectionKeepAliveStrategy();
      }
      ConnectionKeepAliveStrategy localConnectionKeepAliveStrategy = keepAliveStrategy;
      return localConnectionKeepAliveStrategy;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final ClientConnectionManager getConnectionManager()
  {
    try
    {
      if (connManager == null) {
        connManager = createClientConnectionManager();
      }
      ClientConnectionManager localClientConnectionManager = connManager;
      return localClientConnectionManager;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final ConnectionReuseStrategy getConnectionReuseStrategy()
  {
    try
    {
      if (reuseStrategy == null) {
        reuseStrategy = createConnectionReuseStrategy();
      }
      ConnectionReuseStrategy localConnectionReuseStrategy = reuseStrategy;
      return localConnectionReuseStrategy;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final cz.msebera.android.http.cookie.CookieSpecRegistry getCookieSpecs()
  {
    try
    {
      if (supportedCookieSpecs == null) {
        supportedCookieSpecs = createCookieSpecRegistry();
      }
      cz.msebera.android.http.cookie.CookieSpecRegistry localCookieSpecRegistry = supportedCookieSpecs;
      return localCookieSpecRegistry;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final CredentialsProvider getCookieStore()
  {
    try
    {
      if (cookieStore == null) {
        cookieStore = createCredentialsProvider();
      }
      CredentialsProvider localCredentialsProvider = cookieStore;
      return localCredentialsProvider;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final CookieStore getCredentialsProvider()
  {
    try
    {
      if (credsProvider == null) {
        credsProvider = createCookieStore();
      }
      CookieStore localCookieStore = credsProvider;
      return localCookieStore;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected final BasicHttpProcessor getHttpProcessor()
  {
    try
    {
      if (mutableProcessor == null) {
        mutableProcessor = createHttpProcessor();
      }
      BasicHttpProcessor localBasicHttpProcessor = mutableProcessor;
      return localBasicHttpProcessor;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final HttpRequestRetryHandler getHttpRequestRetryHandler()
  {
    try
    {
      if (retryHandler == null) {
        retryHandler = createHttpRequestRetryHandler();
      }
      HttpRequestRetryHandler localHttpRequestRetryHandler = retryHandler;
      return localHttpRequestRetryHandler;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final HttpParams getParams()
  {
    try
    {
      if (defaultParams == null) {
        defaultParams = createHttpParams();
      }
      HttpParams localHttpParams = defaultParams;
      return localHttpParams;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final AuthenticationStrategy getProxyAuthenticationStrategy()
  {
    try
    {
      if (proxyAuthStrategy == null) {
        proxyAuthStrategy = createProxyAuthenticationStrategy();
      }
      AuthenticationStrategy localAuthenticationStrategy = proxyAuthStrategy;
      return localAuthenticationStrategy;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final RedirectStrategy getRedirectStrategy()
  {
    try
    {
      if (redirectStrategy == null) {
        redirectStrategy = new DefaultRedirectStrategy();
      }
      RedirectStrategy localRedirectStrategy = redirectStrategy;
      return localRedirectStrategy;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final HttpRequestExecutor getRequestExecutor()
  {
    try
    {
      if (requestExec == null) {
        requestExec = createRequestExecutor();
      }
      HttpRequestExecutor localHttpRequestExecutor = requestExec;
      return localHttpRequestExecutor;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final UserTokenHandler getTargetAuthenticationHandler()
  {
    try
    {
      if (targetAuthHandler == null) {
        targetAuthHandler = createUserTokenHandler();
      }
      UserTokenHandler localUserTokenHandler = targetAuthHandler;
      return localUserTokenHandler;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final HttpRoutePlanner getTargetAuthenticationStrategy()
  {
    try
    {
      if (targetAuthStrategy == null) {
        targetAuthStrategy = createHttpRoutePlanner();
      }
      HttpRoutePlanner localHttpRoutePlanner = targetAuthStrategy;
      return localHttpRoutePlanner;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final AuthenticationStrategy getUserTokenHandler()
  {
    try
    {
      if (userTokenHandler == null) {
        userTokenHandler = createTargetAuthenticationStrategy();
      }
      AuthenticationStrategy localAuthenticationStrategy = userTokenHandler;
      return localAuthenticationStrategy;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setHttpRequestRetryHandler(HttpRequestRetryHandler paramHttpRequestRetryHandler)
  {
    try
    {
      retryHandler = paramHttpRequestRetryHandler;
      return;
    }
    catch (Throwable paramHttpRequestRetryHandler)
    {
      throw paramHttpRequestRetryHandler;
    }
  }
  
  public void setRedirectHandler(RedirectHandler paramRedirectHandler)
  {
    try
    {
      redirectStrategy = new DefaultRedirectStrategyAdaptor(paramRedirectHandler);
      return;
    }
    catch (Throwable paramRedirectHandler)
    {
      throw paramRedirectHandler;
    }
  }
}
