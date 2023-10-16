package lombok.eclipse.handlers.http;

import android.content.Context;
import b.c.a.a.q;
import c.a.a.a.g.f;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.client.auth.HttpDelete;
import cz.msebera.android.http.client.auth.HttpEntityEnclosingRequestBase;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.scheme.PlainSocketFactory;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.scheme.SocketFactory;
import cz.msebera.android.http.conn.ssl.SSLSocketFactory;
import cz.msebera.android.http.conn.tsccm.ConnManagerParams;
import cz.msebera.android.http.conn.tsccm.ConnPerRouteBean;
import cz.msebera.android.http.entity.HttpEntityWrapper;
import cz.msebera.android.http.execchain.BasicHttpContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.SyncBasicHttpContext;
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

public class AsyncHttpClient {
    public static LogInterface log = new LogHandler();
    /* access modifiers changed from: private */
    public final Map<String, String> clientHeaderMap;
    private int connectTimeout;
    private final DefaultHttpClient httpClient;
    private final HttpContext httpContext;
    private boolean isUrlEncodingEnabled;
    private int maxConnections;
    private final Map<Context, List<q>> requestMap;
    private int responseTimeout;
    private ExecutorService threadPool;

    class InflatingEntity extends HttpEntityWrapper {
        GZIPInputStream gzippedStream;
        PushbackInputStream pushbackStream;
        InputStream wrappedStream;

        public InflatingEntity(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public void consumeContent() {
            AsyncHttpClient.silentCloseInputStream(this.wrappedStream);
            AsyncHttpClient.silentCloseInputStream(this.pushbackStream);
            AsyncHttpClient.silentCloseInputStream(this.gzippedStream);
            super.consumeContent();
        }

        public InputStream getContent() {
            this.wrappedStream = this.wrappedEntity.getContent();
            this.pushbackStream = new PushbackInputStream(this.wrappedStream, 2);
            if (!AsyncHttpClient.isInputStreamGZIPCompressed(this.pushbackStream)) {
                return this.pushbackStream;
            }
            this.gzippedStream = new GZIPInputStream(this.pushbackStream);
            return this.gzippedStream;
        }

        public long getContentLength() {
            HttpEntity $r1 = this.wrappedEntity;
            if ($r1 == null) {
                return 0;
            }
            return $r1.getContentLength();
        }
    }

    public AsyncHttpClient() {
        this(false, 80, 443);
    }

    public AsyncHttpClient(SchemeRegistry schemeRegistry) {
        this.maxConnections = 10;
        this.connectTimeout = 10000;
        this.responseTimeout = 10000;
        boolean $z0 = true;
        this.isUrlEncodingEnabled = true;
        BasicHttpParams $r2 = new BasicHttpParams();
        ConnManagerParams.setTimeout($r2, (long) this.connectTimeout);
        ConnManagerParams.setMaxConnectionsPerRoute($r2, new ConnPerRouteBean(this.maxConnections));
        ConnManagerParams.setMaxTotalConnections($r2, 10);
        HttpConnectionParams.setSoTimeout($r2, this.responseTimeout);
        HttpConnectionParams.setConnectionTimeout($r2, this.connectTimeout);
        HttpConnectionParams.setTcpNoDelay($r2, true);
        HttpConnectionParams.setSocketBufferSize($r2, 8192);
        HttpProtocolParams.setVersion($r2, HttpVersion.HTTP_1_1);
        ClientConnectionManager $r5 = createConnectionManager(schemeRegistry, $r2);
        Utils.asserts($r5 == null ? false : $z0, "Custom implementation of #createConnectionManager(SchemeRegistry, BasicHttpParams) returned null");
        this.threadPool = getDefaultThreadPool();
        this.requestMap = Collections.synchronizedMap(new WeakHashMap());
        this.clientHeaderMap = new HashMap();
        this.httpContext = new SyncBasicHttpContext(new BasicHttpContext());
        this.httpClient = new DefaultHttpClient($r5, $r2);
        this.httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(HttpRequest httpRequest, HttpContext httpContext) {
                if (!httpRequest.containsHeader("Accept-Encoding")) {
                    httpRequest.addHeader("Accept-Encoding", "gzip");
                }
                for (String $r9 : AsyncHttpClient.this.clientHeaderMap.keySet()) {
                    if (httpRequest.containsHeader($r9)) {
                        Header $r10 = httpRequest.getFirstHeader($r9);
                        AsyncHttpClient.log.d("AsyncHttpClient", String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", new Object[]{$r9, AsyncHttpClient.this.clientHeaderMap.get($r9), $r10.getName(), $r10.getValue()}));
                        httpRequest.removeHeader($r10);
                    }
                    httpRequest.addHeader($r9, (String) AsyncHttpClient.this.clientHeaderMap.get($r9));
                }
            }
        });
        this.httpClient.addResponseInterceptor(new ImmutableHttpProcessor(this));
        this.httpClient.addRequestInterceptor(new RequestDefaultHeaders(this), 0);
        this.httpClient.setHttpRequestRetryHandler(new RetryHandler(5, 1500));
    }

    public AsyncHttpClient(boolean z, int i, int i2) {
        this(getDefaultSchemeRegistry(z, i, i2));
    }

    public static void allowRetryExceptionClass(Class cls) {
        if (cls != null) {
            RetryHandler.addClassToWhitelist(cls);
        }
    }

    public static void endEntityViaReflection(HttpEntity httpEntity) {
        if (httpEntity instanceof HttpEntityWrapper) {
            Field $r2 = null;
            try {
                Field[] $r4 = f.class.getDeclaredFields();
                int $i0 = $r4.length;
                int $i1 = 0;
                while (true) {
                    if ($i1 >= $i0) {
                        break;
                    }
                    Field $r0 = $r4[$i1];
                    if ($r0.getName().equals("wrappedEntity")) {
                        $r2 = $r0;
                        break;
                    }
                    $i1++;
                }
                if ($r2 != null) {
                    $r2.setAccessible(true);
                    HttpEntity $r1 = (HttpEntity) $r2.get(httpEntity);
                    if ($r1 != null) {
                        $r1.consumeContent();
                    }
                }
            } catch (Throwable $r7) {
                log.e("AsyncHttpClient", "wrappedEntity consume", $r7);
            }
        }
    }

    private static SchemeRegistry getDefaultSchemeRegistry(boolean z, int $i0, int $i1) {
        if (z) {
            log.d("AsyncHttpClient", "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        if ($i0 < 1) {
            $i0 = 80;
            log.d("AsyncHttpClient", "Invalid HTTP port number specified, defaulting to 80");
        }
        if ($i1 < 1) {
            $i1 = 443;
            log.d("AsyncHttpClient", "Invalid HTTPS port number specified, defaulting to 443");
        }
        SSLSocketFactory $r1 = z ? MySSLSocketFactory.getFixedSocketFactory() : SSLSocketFactory.getSocketFactory();
        SchemeRegistry $r2 = new SchemeRegistry();
        $r2.register(new Scheme("http", (SocketFactory) PlainSocketFactory.getSocketFactory(), $i0));
        $r2.register(new Scheme("https", (SocketFactory) $r1, $i1));
        return $r2;
    }

    public static String getUrlWithQueryString(boolean z, String $r1, RequestParams requestParams) {
        if ($r1 == null) {
            return null;
        }
        if (z) {
            try {
                URL $r3 = new URL(URLDecoder.decode($r1, "UTF-8"));
                $r1 = new URI($r3.getProtocol(), $r3.getUserInfo(), $r3.getHost(), $r3.getPort(), $r3.getPath(), $r3.getQuery(), $r3.getRef()).toASCIIString();
            } catch (Exception $r10) {
                log.e("AsyncHttpClient", "getUrlWithQueryString encoding URL", $r10);
            }
        }
        if (requestParams == null) {
            return $r1;
        }
        requestParams.getParamString();
        throw new NullPointerException("Null throw statement replaced by Soot");
    }

    /* JADX INFO: finally extract failed */
    public static boolean isInputStreamGZIPCompressed(PushbackInputStream pushbackInputStream) {
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] $r1 = new byte[2];
        int $i1 = 0;
        while ($i1 < 2) {
            try {
                int $i0 = pushbackInputStream.read($r1, $i1, 2 - $i1);
                if ($i0 < 0) {
                    pushbackInputStream.unread($r1, 0, $i1);
                    return false;
                }
                $i1 += $i0;
            } catch (Throwable $r2) {
                pushbackInputStream.unread($r1, 0, $i1);
                throw $r2;
            }
        }
        pushbackInputStream.unread($r1, 0, $i1);
        return 35615 == (($r1[0] & 255) | (($r1[1] << 8) & 65280));
    }

    public static void silentCloseInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException $r2) {
                log.w("AsyncHttpClient", "Cannot close input stream", $r2);
            }
        }
    }

    public static void silentCloseOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException $r2) {
                log.w("AsyncHttpClient", "Cannot close output stream", $r2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager createConnectionManager(SchemeRegistry schemeRegistry, BasicHttpParams basicHttpParams) {
        return new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
    }

    public RequestHandle delete(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpDelete $r6 = new HttpDelete(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams));
        if (headerArr != null) {
            $r6.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, $r6, (String) null, responseHandlerInterface, context);
    }

    public RequestHandle get(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, new Request(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams)), (String) null, responseHandlerInterface, context);
    }

    public RequestHandle get(Context context, String str, ResponseHandlerInterface responseHandlerInterface) {
        return get(context, str, (RequestParams) null, responseHandlerInterface);
    }

    public RequestHandle get(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        Request $r6 = new Request(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams));
        if (headerArr != null) {
            $r6.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, $r6, (String) null, responseHandlerInterface, context);
    }

    /* access modifiers changed from: protected */
    public ExecutorService getDefaultThreadPool() {
        return Executors.newCachedThreadPool();
    }

    /* access modifiers changed from: protected */
    public AsyncHttpRequest newAsyncHttpRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext2, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        return new AsyncHttpRequest(defaultHttpClient, httpContext2, httpUriRequest, responseHandlerInterface);
    }

    /* access modifiers changed from: protected */
    public RequestHandle sendRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext2, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        List $r19;
        if (httpUriRequest == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        } else if (responseHandlerInterface == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        } else if (!responseHandlerInterface.getUseSynchronousMode() || responseHandlerInterface.getUsePoolThread()) {
            if (str != null) {
                if (!(httpUriRequest instanceof HttpEntityEnclosingRequestBase) || ((HttpEntityEnclosingRequestBase) httpUriRequest).getEntity() == null || !httpUriRequest.containsHeader("Content-Type")) {
                    httpUriRequest.setHeader("Content-Type", str);
                } else {
                    log.w("AsyncHttpClient", "Passed contentType will be ignored because HttpEntity sets content type");
                }
            }
            responseHandlerInterface.setRequestHeaders(httpUriRequest.getAllHeaders());
            responseHandlerInterface.setRequestURI(httpUriRequest.getURI());
            AsyncHttpRequest $r13 = newAsyncHttpRequest(defaultHttpClient, httpContext2, httpUriRequest, str, responseHandlerInterface, context);
            this.threadPool.submit($r13);
            RequestHandle $r15 = new RequestHandle($r13);
            if (context != null) {
                synchronized (this.requestMap) {
                    Map<Context, List<q>> map = this.requestMap;
                    Map<Context, List<q>> map2 = map;
                    $r19 = map.get(context);
                    if ($r19 == null) {
                        List $r21 = Collections.synchronizedList(new LinkedList());
                        $r19 = $r21;
                        Map<Context, List<q>> map3 = this.requestMap;
                        Map<Context, List<q>> map4 = map3;
                        map3.put(context, $r21);
                    }
                }
                $r19.add($r15);
                Iterator $r22 = $r19.iterator();
                while ($r22.hasNext()) {
                    if (((RequestHandle) $r22.next()).shouldBeGarbageCollected()) {
                        $r22.remove();
                    }
                }
            }
            return $r15;
        } else {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
    }

    public void setConnectTimeout(int $i0) {
        if ($i0 < 1000) {
            $i0 = 10000;
        }
        this.connectTimeout = $i0;
        HttpParams $r1 = this.httpClient.getParams();
        ConnManagerParams.setTimeout($r1, (long) this.connectTimeout);
        HttpConnectionParams.setConnectionTimeout($r1, this.connectTimeout);
    }

    public void setEnableRedirects(boolean z) {
        setEnableRedirects(z, z, z);
    }

    public void setEnableRedirects(boolean z, boolean z2, boolean z3) {
        this.httpClient.getParams().setBooleanParameter("http.protocol.reject-relative-redirect", !z2);
        this.httpClient.getParams().setBooleanParameter("http.protocol.allow-circular-redirects", z3);
        this.httpClient.setRedirectHandler(new MyRedirectHandler(z));
    }

    public void setResponseTimeout(int $i0) {
        if ($i0 < 1000) {
            $i0 = 10000;
        }
        this.responseTimeout = $i0;
        HttpConnectionParams.setSoTimeout(this.httpClient.getParams(), this.responseTimeout);
    }

    public void setTimeout(int $i0) {
        if ($i0 < 1000) {
            $i0 = 10000;
        }
        setConnectTimeout($i0);
        setResponseTimeout($i0);
    }
}
