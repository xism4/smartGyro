package cz.msebera.android.http.impl.client;

import c.a.a.a.i.b.k;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.client.cache.RequestAddCookies;
import cz.msebera.android.http.client.cache.RequestAuthCache;
import cz.msebera.android.http.client.cache.RequestClientConnControl;
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
import java.nio.charset.Charset;

@Deprecated
public class DefaultHttpClient
  extends AbstractHttpClient
{
  public DefaultHttpClient()
  {
    super(null, null);
  }
  
  public DefaultHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    super(paramClientConnectionManager, paramHttpParams);
  }
  
  public static void setDefaultHttpParams(HttpParams paramHttpParams)
  {
    HttpProtocolParams.setVersion(paramHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(paramHttpParams, HTTP.DEF_CONTENT_CHARSET.name());
    HttpConnectionParams.setTcpNoDelay(paramHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(paramHttpParams, 8192);
    HttpProtocolParams.setUserAgent(paramHttpParams, VersionInfo.getUserAgent("Apache-HttpClient", "cz.msebera.android.httpclient.client", k.class));
  }
  
  protected HttpParams createHttpParams()
  {
    SyncBasicHttpParams localSyncBasicHttpParams = new SyncBasicHttpParams();
    setDefaultHttpParams(localSyncBasicHttpParams);
    return localSyncBasicHttpParams;
  }
  
  protected BasicHttpProcessor createHttpProcessor()
  {
    BasicHttpProcessor localBasicHttpProcessor = new BasicHttpProcessor();
    localBasicHttpProcessor.addInterceptor(new cz.msebera.android.http.client.cache.RequestDefaultHeaders());
    localBasicHttpProcessor.addInterceptor(new RequestContent());
    localBasicHttpProcessor.addInterceptor(new cz.msebera.android.http.execchain.RequestDefaultHeaders());
    localBasicHttpProcessor.addInterceptor(new RequestAddCookies());
    localBasicHttpProcessor.addInterceptor(new RequestAcceptEncoding());
    localBasicHttpProcessor.addInterceptor(new RequestExpectContinue());
    localBasicHttpProcessor.addInterceptor(new RequestClientConnControl());
    localBasicHttpProcessor.addInterceptor(new ResponseProcessCookies());
    localBasicHttpProcessor.addInterceptor(new RequestAuthCache());
    localBasicHttpProcessor.addInterceptor(new RequestTargetAuthentication());
    localBasicHttpProcessor.addInterceptor(new RequestProxyAuthentication());
    return localBasicHttpProcessor;
  }
}
