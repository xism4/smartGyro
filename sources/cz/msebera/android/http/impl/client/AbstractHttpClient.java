package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.ConnectionReuseStrategy;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponseInterceptor;
import cz.msebera.android.http.auth.AuthSchemeRegistry;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.client.BackoffManager;
import cz.msebera.android.http.client.ConnectionBackoffStrategy;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.client.HttpRequestRetryHandler;
import cz.msebera.android.http.client.RedirectHandler;
import cz.msebera.android.http.client.RedirectStrategy;
import cz.msebera.android.http.client.RequestDirector;
import cz.msebera.android.http.client.UserTokenHandler;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.http.conn.routing.HttpRoutePlanner;
import cz.msebera.android.http.cookie.CookieSpecRegistry;
import cz.msebera.android.http.execchain.BasicHttpContext;
import cz.msebera.android.http.execchain.BasicHttpProcessor;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpProcessor;
import cz.msebera.android.http.execchain.HttpRequestExecutor;
import cz.msebera.android.http.execchain.ImmutableHttpProcessor;
import cz.msebera.android.http.impl.DefaultConnectionReuseStrategy;
import cz.msebera.android.http.impl.auth.BasicSchemeFactory;
import cz.msebera.android.http.impl.auth.DigestSchemeFactory;
import cz.msebera.android.http.impl.auth.NTLMSchemeFactory;
import cz.msebera.android.http.impl.conn.ProxySelectorRoutePlanner;
import cz.msebera.android.http.impl.cookie.BestMatchSpecFactory;
import cz.msebera.android.http.impl.cookie.BrowserCompatSpecFactory;
import cz.msebera.android.http.impl.cookie.IgnoreSpecFactory;
import cz.msebera.android.http.impl.cookie.NetscapeDraftSpecFactory;
import cz.msebera.android.http.impl.cookie.RFC2109SpecFactory;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public abstract class AbstractHttpClient extends CloseableHttpClient {
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
    private CookieSpecRegistry supportedCookieSpecs;
    private UserTokenHandler targetAuthHandler;
    private HttpRoutePlanner targetAuthStrategy;
    private AuthenticationStrategy userTokenHandler;

    protected AbstractHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.defaultParams = httpParams;
        this.connManager = clientConnectionManager;
    }

    private synchronized HttpProcessor getProtocolProcessor() {
        if (this.protocolProcessor == null) {
            BasicHttpProcessor $r3 = getHttpProcessor();
            int $i0 = $r3.getRequestInterceptorCount();
            HttpRequestInterceptor[] $r1 = new HttpRequestInterceptor[$i0];
            for (int $i2 = 0; $i2 < $i0; $i2++) {
                $r1[$i2] = $r3.getRequestInterceptor($i2);
            }
            int $i02 = $r3.getResponseInterceptorCount();
            HttpResponseInterceptor[] $r5 = new HttpResponseInterceptor[$i02];
            for (int $i1 = 0; $i1 < $i02; $i1++) {
                $r5[$i1] = $r3.getResponseInterceptor($i1);
            }
            this.protocolProcessor = new ImmutableHttpProcessor($r1, $r5);
        }
        return this.protocolProcessor;
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        getHttpProcessor().addInterceptor(httpRequestInterceptor);
        this.protocolProcessor = null;
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i) {
        getHttpProcessor().addInterceptor(httpRequestInterceptor, i);
        this.protocolProcessor = null;
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        getHttpProcessor().addInterceptor(httpResponseInterceptor);
        this.protocolProcessor = null;
    }

    public void close() {
        getConnectionManager().shutdown();
    }

    /* access modifiers changed from: protected */
    public AuthSchemeRegistry createAuthSchemeRegistry() {
        AuthSchemeRegistry $r1 = new AuthSchemeRegistry();
        $r1.register("Basic", new DigestSchemeFactory());
        $r1.register("Digest", new BasicSchemeFactory());
        $r1.register("NTLM", new NTLMSchemeFactory());
        return $r1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: cz.msebera.android.http.conn.ClientConnectionManagerFactory} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cz.msebera.android.http.conn.ClientConnectionManager createClientConnectionManager() {
        /*
            r19 = this;
            cz.msebera.android.http.conn.scheme.SchemeRegistry r1 = cz.msebera.android.http.impl.conn.SchemeRegistryFactory.createDefault()
            r0 = r19
            cz.msebera.android.http.util.HttpParams r2 = r0.getParams()
            java.lang.String r4 = "http.connection-manager.factory-class-name"
            java.lang.Object r3 = r2.getParameter(r4)
            r6 = r3
            java.lang.String r6 = (java.lang.String) r6
            r5 = r6
            if (r5 == 0) goto L_0x0050
            java.lang.Class r7 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x0060, IllegalAccessException -> 0x002e, InstantiationException -> 0x0023 }
            java.lang.Object r3 = r7.newInstance()     // Catch:{ ClassNotFoundException -> 0x0060, IllegalAccessException -> 0x002e, InstantiationException -> 0x0023 }
            r9 = r3
            cz.msebera.android.http.conn.ClientConnectionManagerFactory r9 = (cz.msebera.android.http.conn.ClientConnectionManagerFactory) r9
            r8 = r9
            goto L_0x0051
        L_0x0023:
            r10 = move-exception
            java.lang.InstantiationError r11 = new java.lang.InstantiationError
            java.lang.String r5 = r10.getMessage()
            r11.<init>(r5)
            throw r11
        L_0x002e:
            r12 = move-exception
            java.lang.IllegalAccessError r13 = new java.lang.IllegalAccessError
            java.lang.String r5 = r12.getMessage()
            r13.<init>(r5)
            throw r13
        L_0x0039:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r4 = "Invalid class name: "
            r15.append(r4)
            r15.append(r5)
            java.lang.String r5 = r15.toString()
            r14.<init>(r5)
            throw r14
        L_0x0050:
            r8 = 0
        L_0x0051:
            if (r8 == 0) goto L_0x0058
            cz.msebera.android.http.conn.ClientConnectionManager r16 = r8.newInstance(r2, r1)
            return r16
        L_0x0058:
            cz.msebera.android.http.impl.conn.BasicClientConnectionManager r17 = new cz.msebera.android.http.impl.conn.BasicClientConnectionManager
            r0 = r17
            r0.<init>(r1)
            return r17
        L_0x0060:
            r18 = move-exception
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.client.AbstractHttpClient.createClientConnectionManager():cz.msebera.android.http.conn.ClientConnectionManager");
    }

    /* access modifiers changed from: protected */
    public RequestDirector createClientRequestDirector(HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectStrategy redirectStrategy2, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler2, HttpParams httpParams) {
        return new DefaultRequestDirector(this.log, httpRequestExecutor, clientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, httpRoutePlanner, httpProcessor, httpRequestRetryHandler, redirectStrategy2, authenticationStrategy, authenticationStrategy2, userTokenHandler2, httpParams);
    }

    /* access modifiers changed from: protected */
    public ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
        return new DefaultConnectionKeepAliveStrategy();
    }

    /* access modifiers changed from: protected */
    public ConnectionReuseStrategy createConnectionReuseStrategy() {
        return new DefaultConnectionReuseStrategy();
    }

    /* access modifiers changed from: protected */
    public CookieSpecRegistry createCookieSpecRegistry() {
        CookieSpecRegistry $r1 = new CookieSpecRegistry();
        $r1.register("default", new NetscapeDraftSpecFactory());
        $r1.register("best-match", new NetscapeDraftSpecFactory());
        $r1.register("compatibility", new BrowserCompatSpecFactory());
        $r1.register("netscape", new RFC2109SpecFactory());
        $r1.register("rfc2109", new cz.msebera.android.http.impl.cookie.CookieSpecRegistry());
        $r1.register("rfc2965", new BestMatchSpecFactory());
        $r1.register("ignoreCookies", new IgnoreSpecFactory());
        return $r1;
    }

    /* access modifiers changed from: protected */
    public CookieStore createCookieStore() {
        return new BasicCookieStore();
    }

    /* access modifiers changed from: protected */
    public CredentialsProvider createCredentialsProvider() {
        return new BasicCredentialsProvider();
    }

    /* access modifiers changed from: protected */
    public HttpContext createHttpContext() {
        BasicHttpContext $r1 = new BasicHttpContext();
        $r1.setAttribute("http.scheme-registry", getConnectionManager().getSchemeRegistry());
        $r1.setAttribute("http.authscheme-registry", getAuthSchemes());
        $r1.setAttribute("http.cookiespec-registry", getCookieSpecs());
        $r1.setAttribute("http.cookie-store", getCredentialsProvider());
        $r1.setAttribute("http.auth.credentials-provider", getCookieStore());
        return $r1;
    }

    /* access modifiers changed from: protected */
    public abstract HttpParams createHttpParams();

    /* access modifiers changed from: protected */
    public abstract BasicHttpProcessor createHttpProcessor();

    /* access modifiers changed from: protected */
    public HttpRequestRetryHandler createHttpRequestRetryHandler() {
        return new DefaultHttpRequestRetryHandler();
    }

    /* access modifiers changed from: protected */
    public HttpRoutePlanner createHttpRoutePlanner() {
        return new ProxySelectorRoutePlanner(getConnectionManager().getSchemeRegistry());
    }

    /* access modifiers changed from: protected */
    public AuthenticationStrategy createProxyAuthenticationStrategy() {
        return new ProxyAuthenticationStrategy();
    }

    /* access modifiers changed from: protected */
    public HttpRequestExecutor createRequestExecutor() {
        return new HttpRequestExecutor();
    }

    /* access modifiers changed from: protected */
    public AuthenticationStrategy createTargetAuthenticationStrategy() {
        return new TargetAuthenticationStrategy();
    }

    /* access modifiers changed from: protected */
    public UserTokenHandler createUserTokenHandler() {
        return new DefaultUserTokenHandler();
    }

    /* access modifiers changed from: protected */
    public HttpParams determineParams(HttpRequest httpRequest) {
        return new ClientParamsStack((HttpParams) null, getParams(), httpRequest.getParams(), (HttpParams) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v0, resolved type: cz.msebera.android.http.HttpHost} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r51v2, resolved type: cz.msebera.android.http.execchain.DefaultedHttpContext} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final cz.msebera.android.http.client.auth.CloseableHttpResponse doExecute(cz.msebera.android.http.HttpHost r49, cz.msebera.android.http.HttpRequest r50, cz.msebera.android.http.execchain.HttpContext r51) {
        /*
            r48 = this;
            java.lang.String r13 = "HTTP request"
            r0 = r50
            cz.msebera.android.http.mime.Args.notNull(r0, r13)
            monitor-enter(r48)
            r0 = r48
            cz.msebera.android.http.execchain.HttpContext r14 = r0.createHttpContext()     // Catch:{ Throwable -> 0x0169 }
            if (r51 != 0) goto L_0x0013
            r51 = r14
            goto L_0x001c
        L_0x0013:
            cz.msebera.android.http.execchain.DefaultedHttpContext r15 = new cz.msebera.android.http.execchain.DefaultedHttpContext     // Catch:{ Throwable -> 0x0169 }
            r0 = r51
            r15.<init>(r0, r14)     // Catch:{ Throwable -> 0x0169 }
            r51 = r15
        L_0x001c:
            r0 = r48
            r1 = r50
            cz.msebera.android.http.util.HttpParams r16 = r0.determineParams(r1)     // Catch:{ Throwable -> 0x0169 }
            r0 = r16
            cz.msebera.android.http.client.methods.RequestConfig r17 = cz.msebera.android.http.client.protocol.HttpClientParamConfig.getRequestConfig(r0)     // Catch:{ Throwable -> 0x0169 }
            java.lang.String r13 = "http.request-config"
            r0 = r51
            r1 = r17
            r0.setAttribute(r13, r1)     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.execchain.HttpRequestExecutor r18 = r0.getRequestExecutor()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.conn.ClientConnectionManager r19 = r0.getConnectionManager()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.ConnectionReuseStrategy r20 = r0.getConnectionReuseStrategy()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.conn.ConnectionKeepAliveStrategy r21 = r0.getConnectionKeepAliveStrategy()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.conn.routing.HttpRoutePlanner r22 = r0.getTargetAuthenticationStrategy()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.execchain.HttpProcessor r23 = r0.getProtocolProcessor()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.client.HttpRequestRetryHandler r24 = r0.getHttpRequestRetryHandler()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.client.RedirectStrategy r25 = r0.getRedirectStrategy()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.client.AuthenticationStrategy r26 = r0.getProxyAuthenticationStrategy()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.client.AuthenticationStrategy r27 = r0.getUserTokenHandler()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.client.UserTokenHandler r28 = r0.getTargetAuthenticationHandler()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            r7 = r24
            r8 = r25
            r9 = r26
            r10 = r27
            r11 = r28
            r12 = r16
            cz.msebera.android.http.client.RequestDirector r29 = r0.createClientRequestDirector(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.conn.routing.HttpRoutePlanner r22 = r0.getTargetAuthenticationStrategy()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.client.ConnectionBackoffStrategy r30 = r0.getConnectionBackoffStrategy()     // Catch:{ Throwable -> 0x0169 }
            r0 = r48
            cz.msebera.android.http.client.BackoffManager r31 = r0.getBackoffManager()     // Catch:{ Throwable -> 0x0169 }
            monitor-exit(r48)     // Catch:{ Throwable -> 0x0169 }
            if (r30 == 0) goto L_0x014b
            if (r31 == 0) goto L_0x014b
            if (r49 == 0) goto L_0x00af
            r32 = r49
        L_0x00ae:
            goto L_0x00c6
        L_0x00af:
            r0 = r48
            r1 = r50
            cz.msebera.android.http.util.HttpParams r16 = r0.determineParams(r1)     // Catch:{ HttpException -> 0x015e }
            java.lang.String r13 = "http.default-host"
            r0 = r16
            java.lang.Object r33 = r0.getParameter(r13)     // Catch:{ HttpException -> 0x015e }
            r34 = r33
            cz.msebera.android.http.HttpHost r34 = (cz.msebera.android.http.HttpHost) r34
            r32 = r34
            goto L_0x00ae
        L_0x00c6:
            r0 = r22
            r1 = r32
            r2 = r50
            r3 = r51
            cz.msebera.android.http.conn.routing.HttpRoute r35 = r0.determineRoute(r1, r2, r3)     // Catch:{ HttpException -> 0x015e }
            r0 = r29
            r1 = r49
            r2 = r50
            r3 = r51
            cz.msebera.android.http.HttpResponse r36 = r0.execute(r1, r2, r3)     // Catch:{ RuntimeException -> 0x0138, Exception -> 0x00fe }
            r0 = r36
            cz.msebera.android.http.client.auth.CloseableHttpResponse r37 = cz.msebera.android.http.impl.client.CloseableHttpResponseProxy.newProxy(r0)     // Catch:{ RuntimeException -> 0x0138, Exception -> 0x00fe }
            r0 = r30
            r1 = r37
            boolean r38 = r0.shouldBackoff((cz.msebera.android.http.HttpResponse) r1)     // Catch:{ HttpException -> 0x015e }
            if (r38 == 0) goto L_0x00f6
            r0 = r31
            r1 = r35
            r0.backOff(r1)     // Catch:{ HttpException -> 0x015e }
            return r37
        L_0x00f6:
            r0 = r31
            r1 = r35
            r0.probe(r1)     // Catch:{ HttpException -> 0x015e }
            return r37
        L_0x00fe:
            r39 = move-exception
            r0 = r30
            r1 = r39
            boolean r38 = r0.shouldBackoff((java.lang.Throwable) r1)     // Catch:{ HttpException -> 0x015e }
            if (r38 == 0) goto L_0x0110
            r0 = r31
            r1 = r35
            r0.backOff(r1)     // Catch:{ HttpException -> 0x015e }
        L_0x0110:
            r0 = r39
            boolean r0 = r0 instanceof cz.msebera.android.http.HttpException
            r38 = r0
            if (r38 != 0) goto L_0x0131
            r0 = r39
            boolean r0 = r0 instanceof java.io.IOException
            r38 = r0
            if (r38 == 0) goto L_0x0127
            r41 = r39
            java.io.IOException r41 = (java.io.IOException) r41
            r40 = r41
            throw r40
        L_0x0127:
            java.lang.reflect.UndeclaredThrowableException r42 = new java.lang.reflect.UndeclaredThrowableException
            r0 = r42
            r1 = r39
            r0.<init>(r1)     // Catch:{ HttpException -> 0x015e }
            throw r42
        L_0x0131:
            r44 = r39
            cz.msebera.android.http.HttpException r44 = (cz.msebera.android.http.HttpException) r44
            r43 = r44
            throw r43     // Catch:{ HttpException -> 0x015e }
        L_0x0138:
            r45 = move-exception
            r0 = r30
            r1 = r45
            boolean r38 = r0.shouldBackoff((java.lang.Throwable) r1)     // Catch:{ HttpException -> 0x015e }
            if (r38 == 0) goto L_0x014a
            r0 = r31
            r1 = r35
            r0.backOff(r1)     // Catch:{ HttpException -> 0x015e }
        L_0x014a:
            throw r45
        L_0x014b:
            r0 = r29
            r1 = r49
            r2 = r50
            r3 = r51
            cz.msebera.android.http.HttpResponse r36 = r0.execute(r1, r2, r3)     // Catch:{ HttpException -> 0x015e }
            r0 = r36
            cz.msebera.android.http.client.auth.CloseableHttpResponse r37 = cz.msebera.android.http.impl.client.CloseableHttpResponseProxy.newProxy(r0)     // Catch:{ HttpException -> 0x015e }
            return r37
        L_0x015e:
            r43 = move-exception
            cz.msebera.android.http.client.ClientProtocolException r46 = new cz.msebera.android.http.client.ClientProtocolException
            r0 = r46
            r1 = r43
            r0.<init>((java.lang.Throwable) r1)
            throw r46
        L_0x0169:
            r47 = move-exception
            monitor-exit(r48)     // Catch:{ Throwable -> 0x0169 }
            goto L_0x016c
        L_0x016c:
            throw r47
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.client.AbstractHttpClient.doExecute(cz.msebera.android.http.HttpHost, cz.msebera.android.http.HttpRequest, cz.msebera.android.http.execchain.HttpContext):cz.msebera.android.http.client.auth.CloseableHttpResponse");
    }

    public final synchronized AuthSchemeRegistry getAuthSchemes() {
        if (this.supportedAuthSchemes == null) {
            this.supportedAuthSchemes = createAuthSchemeRegistry();
        }
        return this.supportedAuthSchemes;
    }

    public final synchronized BackoffManager getBackoffManager() {
        return this.backoffManager;
    }

    public final synchronized ConnectionBackoffStrategy getConnectionBackoffStrategy() {
        return this.connectionBackoffStrategy;
    }

    public final synchronized ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        if (this.keepAliveStrategy == null) {
            this.keepAliveStrategy = createConnectionKeepAliveStrategy();
        }
        return this.keepAliveStrategy;
    }

    public final synchronized ClientConnectionManager getConnectionManager() {
        if (this.connManager == null) {
            this.connManager = createClientConnectionManager();
        }
        return this.connManager;
    }

    public final synchronized ConnectionReuseStrategy getConnectionReuseStrategy() {
        if (this.reuseStrategy == null) {
            this.reuseStrategy = createConnectionReuseStrategy();
        }
        return this.reuseStrategy;
    }

    public final synchronized CookieSpecRegistry getCookieSpecs() {
        if (this.supportedCookieSpecs == null) {
            this.supportedCookieSpecs = createCookieSpecRegistry();
        }
        return this.supportedCookieSpecs;
    }

    public final synchronized CredentialsProvider getCookieStore() {
        if (this.cookieStore == null) {
            this.cookieStore = createCredentialsProvider();
        }
        return this.cookieStore;
    }

    public final synchronized CookieStore getCredentialsProvider() {
        if (this.credsProvider == null) {
            this.credsProvider = createCookieStore();
        }
        return this.credsProvider;
    }

    /* access modifiers changed from: protected */
    public final synchronized BasicHttpProcessor getHttpProcessor() {
        if (this.mutableProcessor == null) {
            this.mutableProcessor = createHttpProcessor();
        }
        return this.mutableProcessor;
    }

    public final synchronized HttpRequestRetryHandler getHttpRequestRetryHandler() {
        if (this.retryHandler == null) {
            this.retryHandler = createHttpRequestRetryHandler();
        }
        return this.retryHandler;
    }

    public final synchronized HttpParams getParams() {
        if (this.defaultParams == null) {
            this.defaultParams = createHttpParams();
        }
        return this.defaultParams;
    }

    public final synchronized AuthenticationStrategy getProxyAuthenticationStrategy() {
        if (this.proxyAuthStrategy == null) {
            this.proxyAuthStrategy = createProxyAuthenticationStrategy();
        }
        return this.proxyAuthStrategy;
    }

    public final synchronized RedirectStrategy getRedirectStrategy() {
        if (this.redirectStrategy == null) {
            this.redirectStrategy = new DefaultRedirectStrategy();
        }
        return this.redirectStrategy;
    }

    public final synchronized HttpRequestExecutor getRequestExecutor() {
        if (this.requestExec == null) {
            this.requestExec = createRequestExecutor();
        }
        return this.requestExec;
    }

    public final synchronized UserTokenHandler getTargetAuthenticationHandler() {
        if (this.targetAuthHandler == null) {
            this.targetAuthHandler = createUserTokenHandler();
        }
        return this.targetAuthHandler;
    }

    public final synchronized HttpRoutePlanner getTargetAuthenticationStrategy() {
        if (this.targetAuthStrategy == null) {
            this.targetAuthStrategy = createHttpRoutePlanner();
        }
        return this.targetAuthStrategy;
    }

    public final synchronized AuthenticationStrategy getUserTokenHandler() {
        if (this.userTokenHandler == null) {
            this.userTokenHandler = createTargetAuthenticationStrategy();
        }
        return this.userTokenHandler;
    }

    public synchronized void setHttpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
        this.retryHandler = httpRequestRetryHandler;
    }

    public synchronized void setRedirectHandler(RedirectHandler redirectHandler) {
        this.redirectStrategy = new DefaultRedirectStrategyAdaptor(redirectHandler);
    }
}
