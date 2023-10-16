package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.ConnectionReuseStrategy;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.NoHttpResponseException;
import cz.msebera.android.http.ProtocolException;
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
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.routing.BasicRouteDirector;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.HttpRoutePlanner;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.entity.BufferedHttpEntity;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpProcessor;
import cz.msebera.android.http.execchain.HttpRequestExecutor;
import cz.msebera.android.http.impl.auth.BasicScheme;
import cz.msebera.android.http.impl.conn.ConnectionShutdownException;
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
public class DefaultRequestDirector implements RequestDirector {
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

    public DefaultRequestDirector(HttpClientAndroidLog httpClientAndroidLog, HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor2, HttpRequestRetryHandler httpRequestRetryHandler, RedirectStrategy redirectStrategy2, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler2, HttpParams httpParams) {
        Args.notNull(httpClientAndroidLog, "Log");
        Args.notNull(httpRequestExecutor, "Request executor");
        Args.notNull(clientConnectionManager, "Client connection manager");
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        Args.notNull(connectionKeepAliveStrategy, "Connection keep alive strategy");
        Args.notNull(httpRoutePlanner, "Route planner");
        Args.notNull(httpProcessor2, "HTTP protocol processor");
        Args.notNull(httpRequestRetryHandler, "HTTP request retry handler");
        Args.notNull(redirectStrategy2, "Redirect strategy");
        Args.notNull(authenticationStrategy, "Target authentication strategy");
        Args.notNull(authenticationStrategy2, "Proxy authentication strategy");
        Args.notNull(userTokenHandler2, "User token handler");
        Args.notNull(httpParams, "HTTP parameters");
        this.log = httpClientAndroidLog;
        this.authenticator = new HttpAuthenticator(httpClientAndroidLog);
        this.requestExec = httpRequestExecutor;
        this.connManager = clientConnectionManager;
        this.reuseStrategy = connectionReuseStrategy;
        this.keepAliveStrategy = connectionKeepAliveStrategy;
        this.routePlanner = httpRoutePlanner;
        this.httpProcessor = httpProcessor2;
        this.retryHandler = httpRequestRetryHandler;
        this.redirectStrategy = redirectStrategy2;
        this.targetAuthStrategy = authenticationStrategy;
        this.proxyAuthStrategy = authenticationStrategy2;
        this.userTokenHandler = userTokenHandler2;
        this.params = httpParams;
        if (redirectStrategy2 instanceof DefaultRedirectStrategyAdaptor) {
            this.redirectHandler = ((DefaultRedirectStrategyAdaptor) redirectStrategy2).getHandler();
        } else {
            this.redirectHandler = null;
        }
        if (authenticationStrategy instanceof AuthenticationStrategyAdaptor) {
            this.targetAuthHandler = ((AuthenticationStrategyAdaptor) authenticationStrategy).getHandler();
        } else {
            this.targetAuthHandler = null;
        }
        if (authenticationStrategy2 instanceof AuthenticationStrategyAdaptor) {
            this.proxyAuthHandler = ((AuthenticationStrategyAdaptor) authenticationStrategy2).getHandler();
        } else {
            this.proxyAuthHandler = null;
        }
        this.managedConn = null;
        this.execCount = 0;
        this.redirectCount = 0;
        this.targetAuthState = new AuthState();
        this.proxyAuthState = new AuthState();
        this.maxRedirects = this.params.getIntParameter("http.protocol.max-redirects", 100);
    }

    private void abortConnection() {
        ManagedClientConnection $r1 = this.managedConn;
        if ($r1 != null) {
            this.managedConn = null;
            try {
                $r1.abortConnection();
            } catch (IOException $r2) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug($r2.getMessage(), $r2);
                }
            }
            try {
                $r1.releaseConnection();
            } catch (IOException $r5) {
                this.log.debug("Error releasing connection", $r5);
            }
        }
    }

    private void tryConnect(RoutedRequest routedRequest, HttpContext httpContext) {
        HttpRoute $r4 = routedRequest.getRoute();
        RequestWrapper $r5 = routedRequest.getRequest();
        int $i0 = 0;
        while (true) {
            httpContext.setAttribute("http.request", $r5);
            $i0++;
            try {
                if (this.managedConn.isOpen()) {
                    this.managedConn.setSocketTimeout(HttpConnectionParams.getSoTimeout(this.params));
                    break;
                }
                this.managedConn.open($r4, httpContext, this.params);
                break;
            } catch (IOException $r8) {
                try {
                    this.managedConn.close();
                } catch (IOException e) {
                }
                if (!this.retryHandler.retryRequest($r8, $i0, httpContext)) {
                    throw $r8;
                } else if (this.log.isInfoEnabled()) {
                    HttpClientAndroidLog $r10 = this.log;
                    $r10.info("I/O exception (" + $r8.getClass().getName() + ") caught when connecting to " + $r4 + ": " + $r8.getMessage());
                    if (this.log.isDebugEnabled()) {
                        this.log.debug($r8.getMessage(), $r8);
                    }
                    HttpClientAndroidLog $r102 = this.log;
                    $r102.info("Retrying connect to " + $r4);
                }
            }
        }
        establishRoute($r4, httpContext);
    }

    private HttpResponse tryExecute(RoutedRequest routedRequest, HttpContext httpContext) {
        RequestWrapper $r4 = routedRequest.getRequest();
        HttpRoute $r5 = routedRequest.getRoute();
        IOException $r6 = null;
        while (true) {
            this.execCount++;
            $r4.incrementExecCount();
            if (!$r4.isRepeatable()) {
                this.log.debug("Cannot retry non-repeatable request");
                if ($r6 != null) {
                    throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", $r6);
                }
                throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
            }
            try {
                if (!this.managedConn.isOpen()) {
                    if (!$r5.isTunnelled()) {
                        this.log.debug("Reopening the direct connection.");
                        this.managedConn.open($r5, httpContext, this.params);
                    } else {
                        this.log.debug("Proxied connection. Need to start over.");
                        return null;
                    }
                }
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog $r7 = this.log;
                    StringBuilder $r11 = new StringBuilder();
                    $r11.append("Attempt ");
                    $r11.append(this.execCount);
                    $r11.append(" to execute request");
                    $r7.debug($r11.toString());
                }
                return this.requestExec.execute($r4, this.managedConn, httpContext);
            } catch (IOException e) {
                $r6 = e;
                this.log.debug("Closing the connection.");
                try {
                    this.managedConn.close();
                } catch (IOException e2) {
                }
                if (this.retryHandler.retryRequest($r6, $r4.getExecCount(), httpContext)) {
                    if (this.log.isInfoEnabled()) {
                        this.log.info("I/O exception (" + $r6.getClass().getName() + ") caught when processing request to " + $r5 + ": " + $r6.getMessage());
                    }
                    if (this.log.isDebugEnabled()) {
                        this.log.debug($r6.getMessage(), $r6);
                    }
                    if (this.log.isInfoEnabled()) {
                        this.log.info("Retrying request to " + $r5);
                    }
                } else if ($r6 instanceof NoHttpResponseException) {
                    NoHttpResponseException noHttpResponseException = new NoHttpResponseException($r5.getTargetHost().toHostString() + " failed to respond");
                    noHttpResponseException.setStackTrace($r6.getStackTrace());
                    throw noHttpResponseException;
                } else {
                    throw $r6;
                }
            }
        }
    }

    private RequestWrapper wrapRequest(HttpRequest httpRequest) {
        return httpRequest instanceof HttpEntityEnclosingRequest ? new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) httpRequest) : new RequestWrapper(httpRequest);
    }

    /* access modifiers changed from: protected */
    public HttpRequest createConnectRequest(HttpRoute httpRoute, HttpContext httpContext) {
        HttpHost $r3 = httpRoute.getTargetHost();
        String $r4 = $r3.getHostName();
        int $i0 = $r3.getPort();
        int $i1 = $i0;
        if ($i0 < 0) {
            $i1 = this.connManager.getSchemeRegistry().getScheme($r3.getSchemeName()).getDefaultPort();
        }
        StringBuilder $r9 = new StringBuilder($r4.length() + 6);
        $r9.append($r4);
        $r9.append(':');
        $r9.append(Integer.toString($i1));
        return new BasicHttpRequest("CONNECT", $r9.toString(), HttpProtocolParams.getVersion(this.params));
    }

    /* access modifiers changed from: protected */
    public boolean createTunnelToProxy(HttpRoute httpRoute, int i, HttpContext httpContext) {
        throw new HttpException("Proxy chains are not supported.");
    }

    /* access modifiers changed from: protected */
    public boolean createTunnelToTarget(HttpRoute httpRoute, HttpContext httpContext) {
        HttpResponse $r10;
        HttpHost $r3 = httpRoute.getProxyHost();
        HttpHost $r4 = httpRoute.getTargetHost();
        while (true) {
            if (!this.managedConn.isOpen()) {
                this.managedConn.open(httpRoute, httpContext, this.params);
            }
            HttpRequest $r7 = createConnectRequest(httpRoute, httpContext);
            $r7.setParams(this.params);
            httpContext.setAttribute("http.target_host", $r4);
            httpContext.setAttribute("http.route", httpRoute);
            httpContext.setAttribute("http.proxy_host", $r3);
            httpContext.setAttribute("http.connection", this.managedConn);
            httpContext.setAttribute("http.request", $r7);
            this.requestExec.preProcess($r7, this.httpProcessor, httpContext);
            $r10 = this.requestExec.execute($r7, this.managedConn, httpContext);
            $r10.setParams(this.params);
            this.requestExec.postProcess($r10, this.httpProcessor, httpContext);
            if ($r10.getStatusLine().getStatusCode() < 200) {
                throw new HttpException("Unexpected response to CONNECT request: " + $r10.getStatusLine());
            } else if (HttpClientParams.isAuthenticating(this.params)) {
                if (!this.authenticator.isAuthenticationRequested($r3, $r10, this.proxyAuthStrategy, this.proxyAuthState, httpContext)) {
                    break;
                }
                if (!this.authenticator.authenticate($r3, $r10, this.proxyAuthStrategy, this.proxyAuthState, httpContext)) {
                    break;
                }
                ConnectionReuseStrategy $r15 = this.reuseStrategy;
                ConnectionReuseStrategy connectionReuseStrategy = $r15;
                if ($r15.keepAlive($r10, httpContext)) {
                    this.log.debug("Connection kept alive");
                    EntityUtils.consume($r10.getEntity());
                } else {
                    this.managedConn.close();
                }
            }
        }
        if ($r10.getStatusLine().getStatusCode() > 299) {
            HttpEntity $r17 = $r10.getEntity();
            if ($r17 != null) {
                $r10.setEntity(new BufferedHttpEntity($r17));
            }
            this.managedConn.close();
            throw new TunnelRefusedException("CONNECT refused by proxy: " + $r10.getStatusLine(), $r10);
        }
        this.managedConn.markReusable();
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: cz.msebera.android.http.HttpHost} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cz.msebera.android.http.conn.routing.HttpRoute determineRoute(cz.msebera.android.http.HttpHost r7, cz.msebera.android.http.HttpRequest r8, cz.msebera.android.http.execchain.HttpContext r9) {
        /*
            r6 = this;
            cz.msebera.android.http.conn.routing.HttpRoutePlanner r0 = r6.routePlanner
            if (r7 == 0) goto L_0x0005
            goto L_0x0013
        L_0x0005:
            cz.msebera.android.http.util.HttpParams r1 = r8.getParams()
            java.lang.String r3 = "http.default-host"
            java.lang.Object r2 = r1.getParameter(r3)
            r4 = r2
            cz.msebera.android.http.HttpHost r4 = (cz.msebera.android.http.HttpHost) r4
            r7 = r4
        L_0x0013:
            cz.msebera.android.http.conn.routing.HttpRoute r5 = r0.determineRoute(r7, r8, r9)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.client.DefaultRequestDirector.determineRoute(cz.msebera.android.http.HttpHost, cz.msebera.android.http.HttpRequest, cz.msebera.android.http.execchain.HttpContext):cz.msebera.android.http.conn.routing.HttpRoute");
    }

    /* access modifiers changed from: protected */
    public void establishRoute(HttpRoute httpRoute, HttpContext httpContext) {
        int $i0;
        BasicRouteDirector $r3 = new BasicRouteDirector();
        do {
            HttpRoute $r5 = this.managedConn.getRoute();
            $i0 = $r3.nextStep(httpRoute, $r5);
            switch ($i0) {
                case -1:
                    throw new HttpException("Unable to establish route: planned = " + httpRoute + "; current = " + $r5);
                case 0:
                    break;
                case 1:
                case 2:
                    this.managedConn.open(httpRoute, httpContext, this.params);
                    continue;
                case 3:
                    boolean $z0 = createTunnelToTarget(httpRoute, httpContext);
                    this.log.debug("Tunnel to target created.");
                    this.managedConn.tunnelTarget($z0, this.params);
                    continue;
                case 4:
                    createTunnelToProxy(httpRoute, $r5.getHopCount() - 1, httpContext);
                    throw new NullPointerException("Null throw statement replaced by Soot");
                case 5:
                    this.managedConn.layerProtocol(httpContext, this.params);
                    continue;
                default:
                    throw new IllegalStateException("Unknown step indicator " + $i0 + " from RouteDirector.");
            }
        } while ($i0 > 0);
    }

    public HttpResponse execute(HttpHost $r2, HttpRequest httpRequest, HttpContext httpContext) {
        String $r12;
        httpContext.setAttribute("http.auth.target-scope", this.targetAuthState);
        httpContext.setAttribute("http.auth.proxy-scope", this.proxyAuthState);
        RequestWrapper $r6 = wrapRequest(httpRequest);
        $r6.setParams(this.params);
        HttpRoute $r8 = determineRoute($r2, $r6, httpContext);
        this.virtualHost = (HttpHost) $r6.getParams().getParameter("http.virtual-host");
        HttpHost $r10 = this.virtualHost;
        if ($r10 != null && $r10.getPort() == -1) {
            int $i0 = ($r2 != null ? $r2 : $r8.getTargetHost()).getPort();
            if ($i0 != -1) {
                this.virtualHost = new HttpHost(this.virtualHost.getHostName(), $i0, this.virtualHost.getSchemeName());
            }
        }
        RoutedRequest $r14 = new RoutedRequest($r6, $r8);
        HttpResponse $r15 = null;
        boolean z = false;
        boolean $z1 = false;
        while (!z) {
            try {
                RequestWrapper $r62 = $r14.getRequest();
                HttpRoute $r82 = $r14.getRoute();
                Object $r16 = httpContext.getAttribute("http.user-token");
                Object $r9 = $r16;
                try {
                    if (this.managedConn == null) {
                        ClientConnectionManager $r18 = this.connManager;
                        ClientConnectionManager clientConnectionManager = $r18;
                        ClientConnectionRequest $r19 = $r18.requestConnection($r82, $r16);
                        if (httpRequest instanceof AbortableHttpRequest) {
                            ((AbortableHttpRequest) httpRequest).setConnectionRequest($r19);
                        }
                        this.managedConn = $r19.getConnection(HttpClientParams.getConnectionManagerTimeout(this.params), TimeUnit.MILLISECONDS);
                        if (HttpConnectionParams.isStaleCheckingEnabled(this.params)) {
                            ManagedClientConnection $r17 = this.managedConn;
                            ManagedClientConnection managedClientConnection = $r17;
                            if ($r17.isOpen()) {
                                this.log.debug("Stale connection check");
                                ManagedClientConnection $r172 = this.managedConn;
                                ManagedClientConnection managedClientConnection2 = $r172;
                                if ($r172.isStale()) {
                                    this.log.debug("Stale connection detected");
                                    ManagedClientConnection $r173 = this.managedConn;
                                    ManagedClientConnection managedClientConnection3 = $r173;
                                    $r173.close();
                                }
                            }
                        }
                    }
                    if (httpRequest instanceof AbortableHttpRequest) {
                        ((AbortableHttpRequest) httpRequest).setReleaseTrigger(this.managedConn);
                    }
                    try {
                        tryConnect($r14, httpContext);
                        String $r122 = $r62.getURI().getUserInfo();
                        if ($r122 != null) {
                            AuthState $r5 = this.targetAuthState;
                            BasicScheme basicScheme = new BasicScheme();
                            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials($r122);
                            $r5.update(basicScheme, usernamePasswordCredentials);
                        }
                        if (this.virtualHost != null) {
                            $r2 = this.virtualHost;
                        } else {
                            URI $r25 = $r62.getURI();
                            if ($r25.isAbsolute()) {
                                $r2 = URIUtils.extractHost($r25);
                            }
                        }
                        if ($r2 == null) {
                            $r2 = $r82.getTargetHost();
                        }
                        $r62.resetHeaders();
                        rewriteRequestURI($r62, $r82);
                        httpContext.setAttribute("http.target_host", $r2);
                        httpContext.setAttribute("http.route", $r82);
                        httpContext.setAttribute("http.connection", this.managedConn);
                        this.requestExec.preProcess($r62, this.httpProcessor, httpContext);
                        HttpResponse $r30 = tryExecute($r14, httpContext);
                        $r15 = $r30;
                        if ($r30 != null) {
                            $r30.setParams(this.params);
                            this.requestExec.postProcess($r30, this.httpProcessor, httpContext);
                            ConnectionReuseStrategy $r31 = this.reuseStrategy;
                            ConnectionReuseStrategy connectionReuseStrategy = $r31;
                            boolean $z2 = $r31.keepAlive($r30, httpContext);
                            $z1 = $z2;
                            if ($z2) {
                                ConnectionKeepAliveStrategy $r32 = this.keepAliveStrategy;
                                ConnectionKeepAliveStrategy connectionKeepAliveStrategy = $r32;
                                long $l1 = $r32.getKeepAliveDuration($r30, httpContext);
                                HttpClientAndroidLog $r22 = this.log;
                                HttpClientAndroidLog httpClientAndroidLog = $r22;
                                if ($r22.isDebugEnabled()) {
                                    if ($l1 > 0) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("for ");
                                        sb.append($l1);
                                        sb.append(" ");
                                        sb.append(TimeUnit.MILLISECONDS);
                                        $r12 = sb.toString();
                                    } else {
                                        $r12 = "indefinitely";
                                    }
                                    HttpClientAndroidLog $r222 = this.log;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Connection can be kept alive ");
                                    sb2.append($r12);
                                    $r222.debug(sb2.toString());
                                }
                                this.managedConn.setIdleDuration($l1, TimeUnit.MILLISECONDS);
                            }
                            RoutedRequest $r34 = handleResponse($r14, $r30, httpContext);
                            if ($r34 == null) {
                                z = true;
                            } else {
                                if ($z2) {
                                    EntityUtils.consume($r30.getEntity());
                                    ManagedClientConnection $r174 = this.managedConn;
                                    ManagedClientConnection managedClientConnection4 = $r174;
                                    $r174.markReusable();
                                } else {
                                    ManagedClientConnection $r175 = this.managedConn;
                                    ManagedClientConnection managedClientConnection5 = $r175;
                                    $r175.close();
                                    if (this.proxyAuthState.getState().compareTo(AuthProtocolState.CHALLENGED) > 0) {
                                        if (this.proxyAuthState.getAuthScheme() != null) {
                                            if (this.proxyAuthState.getAuthScheme().isConnectionBased()) {
                                                this.log.debug("Resetting proxy auth state");
                                                this.proxyAuthState.reset();
                                            }
                                        }
                                    }
                                    if (this.targetAuthState.getState().compareTo(AuthProtocolState.CHALLENGED) > 0) {
                                        if (this.targetAuthState.getAuthScheme() != null) {
                                            if (this.targetAuthState.getAuthScheme().isConnectionBased()) {
                                                this.log.debug("Resetting target auth state");
                                                this.targetAuthState.reset();
                                            }
                                        }
                                    }
                                }
                                if (!$r34.getRoute().equals($r14.getRoute())) {
                                    releaseConnection();
                                }
                                $r14 = $r34;
                            }
                            if (this.managedConn != null) {
                                if ($r16 == null) {
                                    UserTokenHandler $r40 = this.userTokenHandler;
                                    UserTokenHandler userTokenHandler2 = $r40;
                                    Object $r162 = $r40.getUserToken(httpContext);
                                    $r9 = $r162;
                                    httpContext.setAttribute("http.user-token", $r162);
                                }
                                if ($r9 != null) {
                                    ManagedClientConnection $r176 = this.managedConn;
                                    ManagedClientConnection managedClientConnection6 = $r176;
                                    $r176.setState($r9);
                                }
                            }
                        }
                    } catch (TunnelRefusedException $r41) {
                        HttpClientAndroidLog $r223 = this.log;
                        HttpClientAndroidLog httpClientAndroidLog2 = $r223;
                        if ($r223.isDebugEnabled()) {
                            this.log.debug($r41.getMessage());
                        }
                        $r15 = $r41.getResponse();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    InterruptedIOException interruptedIOException = new InterruptedIOException();
                    throw interruptedIOException;
                } catch (RuntimeException $r42) {
                    abortConnection();
                    throw $r42;
                }
            } catch (ConnectionShutdownException $r45) {
                InterruptedIOException interruptedIOException2 = new InterruptedIOException("Connection has been shut down");
                interruptedIOException2.initCause($r45);
                throw interruptedIOException2;
            } catch (HttpException $r44) {
                abortConnection();
                throw $r44;
            } catch (IOException $r43) {
                abortConnection();
                throw $r43;
            }
        }
        if ($r15 != null) {
            if ($r15.getEntity() != null) {
                if ($r15.getEntity().isStreaming()) {
                    BasicManagedEntity basicManagedEntity = new BasicManagedEntity($r15.getEntity(), this.managedConn, $z1);
                    $r15.setEntity(basicManagedEntity);
                    return $r15;
                }
            }
        }
        if ($z1) {
            ManagedClientConnection $r177 = this.managedConn;
            ManagedClientConnection managedClientConnection7 = $r177;
            $r177.markReusable();
        }
        releaseConnection();
        return $r15;
    }

    /* access modifiers changed from: protected */
    public RoutedRequest handleResponse(RoutedRequest routedRequest, HttpResponse httpResponse, HttpContext httpContext) {
        HttpRoute $r5 = routedRequest.getRoute();
        RequestWrapper $r6 = routedRequest.getRequest();
        HttpParams $r7 = $r6.getParams();
        if (HttpClientParams.isAuthenticating($r7)) {
            HttpHost $r9 = (HttpHost) httpContext.getAttribute("http.target_host");
            if ($r9 == null) {
                $r9 = $r5.getTargetHost();
            }
            if ($r9.getPort() < 0) {
                Scheme $r12 = this.connManager.getSchemeRegistry().getScheme($r9);
                $r9 = new HttpHost($r9.getHostName(), $r12.getDefaultPort(), $r9.getSchemeName());
            }
            boolean $z1 = this.authenticator.isAuthenticationRequested($r9, httpResponse, this.targetAuthStrategy, this.targetAuthState, httpContext);
            HttpHost $r4 = $r5.getProxyHost();
            HttpHost $r13 = $r4;
            if ($r4 == null) {
                $r13 = $r5.getTargetHost();
            }
            HttpHost $r42 = $r13;
            boolean $z0 = this.authenticator.isAuthenticationRequested($r13, httpResponse, this.proxyAuthStrategy, this.proxyAuthState, httpContext);
            if ($z1) {
                if (this.authenticator.authenticate($r9, httpResponse, this.targetAuthStrategy, this.targetAuthState, httpContext)) {
                    return routedRequest;
                }
            }
            if ($z0) {
                if (this.authenticator.authenticate($r42, httpResponse, this.proxyAuthStrategy, this.proxyAuthState, httpContext)) {
                    return routedRequest;
                }
            }
        }
        if (!HttpClientParams.isRedirecting($r7)) {
            return null;
        }
        RedirectStrategy $r19 = this.redirectStrategy;
        RedirectStrategy redirectStrategy2 = $r19;
        if (!$r19.isRedirected($r6, httpResponse, httpContext)) {
            return null;
        }
        int $i0 = this.redirectCount;
        int $i1 = this.maxRedirects;
        int i = $i1;
        if ($i0 < $i1) {
            this.redirectCount = $i0 + 1;
            this.virtualHost = null;
            RedirectStrategy $r192 = this.redirectStrategy;
            RedirectStrategy redirectStrategy3 = $r192;
            HttpUriRequest $r20 = $r192.getRedirect($r6, httpResponse, httpContext);
            $r20.setHeaders($r6.getOriginal().getAllHeaders());
            URI $r23 = $r20.getURI();
            HttpHost $r92 = URIUtils.extractHost($r23);
            if ($r92 != null) {
                if (!$r5.getTargetHost().equals($r92)) {
                    this.log.debug("Resetting target auth state");
                    AuthState $r18 = this.targetAuthState;
                    AuthState authState = $r18;
                    $r18.reset();
                    AuthState $r182 = this.proxyAuthState;
                    AuthState authState2 = $r182;
                    AuthScheme $r25 = $r182.getAuthScheme();
                    if ($r25 != null && $r25.isConnectionBased()) {
                        this.log.debug("Resetting proxy auth state");
                        AuthState $r183 = this.proxyAuthState;
                        AuthState authState3 = $r183;
                        $r183.reset();
                    }
                }
                RequestWrapper $r62 = wrapRequest($r20);
                $r62.setParams($r7);
                HttpRoute $r52 = determineRoute($r92, $r62, httpContext);
                RoutedRequest routedRequest2 = new RoutedRequest($r62, $r52);
                HttpClientAndroidLog $r24 = this.log;
                HttpClientAndroidLog httpClientAndroidLog = $r24;
                if (!$r24.isDebugEnabled()) {
                    return routedRequest2;
                }
                HttpClientAndroidLog $r242 = this.log;
                $r242.debug("Redirecting to '" + $r23 + "' via " + $r52);
                return routedRequest2;
            }
            throw new ProtocolException("Redirect URI does not specify a valid host name: " + $r23);
        }
        throw new RedirectException("Maximum redirects (" + this.maxRedirects + ") exceeded");
    }

    /* access modifiers changed from: protected */
    public void releaseConnection() {
        try {
            this.managedConn.releaseConnection();
        } catch (IOException $r3) {
            this.log.debug("IOException releasing connection", $r3);
        }
        this.managedConn = null;
    }

    /* access modifiers changed from: protected */
    public void rewriteRequestURI(RequestWrapper requestWrapper, HttpRoute httpRoute) {
        URI $r3;
        try {
            URI $r32 = requestWrapper.getURI();
            if (httpRoute.getProxyHost() != null) {
                if (!httpRoute.isTunnelled()) {
                    if (!$r32.isAbsolute()) {
                        $r3 = URIUtils.rewriteURI($r32, httpRoute.getTargetHost(), true);
                        requestWrapper.setURI($r3);
                    }
                    $r3 = URIUtils.rewriteURI($r32);
                    requestWrapper.setURI($r3);
                }
            }
            if ($r32.isAbsolute()) {
                $r3 = URIUtils.rewriteURI($r32, (HttpHost) null, true);
                requestWrapper.setURI($r3);
            }
            $r3 = URIUtils.rewriteURI($r32);
            requestWrapper.setURI($r3);
        } catch (URISyntaxException $r5) {
            throw new ProtocolException("Invalid URI: " + requestWrapper.getRequestLine().getUri(), $r5);
        }
    }
}
