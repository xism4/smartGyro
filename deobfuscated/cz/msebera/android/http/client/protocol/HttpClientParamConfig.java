package cz.msebera.android.http.client.protocol;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.client.methods.RequestConfig.Builder;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.util.Collection;

@Deprecated
public final class HttpClientParamConfig
{
  public static RequestConfig getRequestConfig(HttpParams paramHttpParams)
  {
    RequestConfig.Builder localBuilder = RequestConfig.custom();
    localBuilder.setSocketTimeout(paramHttpParams.getIntParameter("http.socket.timeout", 0));
    localBuilder.setStaleConnectionCheckEnabled(paramHttpParams.getBooleanParameter("http.connection.stalecheck", true));
    localBuilder.setConnectTimeout(paramHttpParams.getIntParameter("http.connection.timeout", 0));
    localBuilder.setExpectContinueEnabled(paramHttpParams.getBooleanParameter("http.protocol.expect-continue", false));
    localBuilder.setProxy((HttpHost)paramHttpParams.getParameter("http.route.default-proxy"));
    localBuilder.setLocalAddress((InetAddress)paramHttpParams.getParameter("http.route.local-address"));
    localBuilder.setProxyPreferredAuthSchemes((Collection)paramHttpParams.getParameter("http.auth.proxy-scheme-pref"));
    localBuilder.setTargetPreferredAuthSchemes((Collection)paramHttpParams.getParameter("http.auth.target-scheme-pref"));
    localBuilder.setAuthenticationEnabled(paramHttpParams.getBooleanParameter("http.protocol.handle-authentication", true));
    localBuilder.setCircularRedirectsAllowed(paramHttpParams.getBooleanParameter("http.protocol.allow-circular-redirects", false));
    localBuilder.setConnectionRequestTimeout((int)paramHttpParams.getLongParameter("http.conn-manager.timeout", 0L));
    localBuilder.setCookieSpec((String)paramHttpParams.getParameter("http.protocol.cookie-policy"));
    localBuilder.setMaxRedirects(paramHttpParams.getIntParameter("http.protocol.max-redirects", 50));
    localBuilder.setRedirectsEnabled(paramHttpParams.getBooleanParameter("http.protocol.handle-redirects", true));
    localBuilder.setRelativeRedirectsAllowed(paramHttpParams.getBooleanParameter("http.protocol.reject-relative-redirect", false) ^ true);
    return localBuilder.build();
  }
}
