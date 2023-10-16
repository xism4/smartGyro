package cz.msebera.android.http.impl.client;

import c.a.a.a.i.b.k;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponseInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.client.cache.RequestAddCookies;
import cz.msebera.android.http.client.cache.RequestAuthCache;
import cz.msebera.android.http.client.cache.RequestClientConnControl;
import cz.msebera.android.http.client.cache.RequestDefaultHeaders;
import cz.msebera.android.http.client.cache.RequestProxyAuthentication;
import cz.msebera.android.http.client.cache.RequestTargetAuthentication;
import cz.msebera.android.http.client.cache.ResponseProcessCookies;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.execchain.BasicHttpProcessor;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.execchain.RequestAcceptEncoding;
import cz.msebera.android.http.execchain.RequestContent;
import cz.msebera.android.http.execchain.RequestExpectContinue;
import cz.msebera.android.http.mime.VersionInfo;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import cz.msebera.android.http.util.HttpProtocolParams;
import cz.msebera.android.http.util.SyncBasicHttpParams;

@Deprecated
public class DefaultHttpClient extends AbstractHttpClient {
    public DefaultHttpClient() {
        super((ClientConnectionManager) null, (HttpParams) null);
    }

    public DefaultHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        super(clientConnectionManager, httpParams);
    }

    public static void setDefaultHttpParams(HttpParams httpParams) {
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, HTTP.DEF_CONTENT_CHARSET.name());
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
        HttpProtocolParams.setUserAgent(httpParams, VersionInfo.getUserAgent("Apache-HttpClient", "cz.msebera.android.httpclient.client", k.class));
    }

    /* access modifiers changed from: protected */
    public HttpParams createHttpParams() {
        SyncBasicHttpParams $r1 = new SyncBasicHttpParams();
        setDefaultHttpParams($r1);
        return $r1;
    }

    /* access modifiers changed from: protected */
    public BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor $r1 = new BasicHttpProcessor();
        $r1.addInterceptor((HttpRequestInterceptor) new RequestDefaultHeaders());
        $r1.addInterceptor((HttpRequestInterceptor) new RequestContent());
        $r1.addInterceptor((HttpRequestInterceptor) new cz.msebera.android.http.execchain.RequestDefaultHeaders());
        $r1.addInterceptor((HttpRequestInterceptor) new RequestAddCookies());
        $r1.addInterceptor((HttpRequestInterceptor) new RequestAcceptEncoding());
        $r1.addInterceptor((HttpRequestInterceptor) new RequestExpectContinue());
        $r1.addInterceptor((HttpRequestInterceptor) new RequestClientConnControl());
        $r1.addInterceptor((HttpResponseInterceptor) new ResponseProcessCookies());
        $r1.addInterceptor((HttpRequestInterceptor) new RequestAuthCache());
        $r1.addInterceptor((HttpRequestInterceptor) new RequestTargetAuthentication());
        $r1.addInterceptor((HttpRequestInterceptor) new RequestProxyAuthentication());
        return $r1;
    }
}
