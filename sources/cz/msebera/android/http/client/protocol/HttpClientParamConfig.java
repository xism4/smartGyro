package cz.msebera.android.http.client.protocol;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.util.Collection;

@Deprecated
public final class HttpClientParamConfig {
    public static RequestConfig getRequestConfig(HttpParams httpParams) {
        RequestConfig.Builder $r1 = RequestConfig.custom();
        $r1.setSocketTimeout(httpParams.getIntParameter("http.socket.timeout", 0));
        $r1.setStaleConnectionCheckEnabled(httpParams.getBooleanParameter("http.connection.stalecheck", true));
        $r1.setConnectTimeout(httpParams.getIntParameter("http.connection.timeout", 0));
        $r1.setExpectContinueEnabled(httpParams.getBooleanParameter("http.protocol.expect-continue", false));
        $r1.setProxy((HttpHost) httpParams.getParameter("http.route.default-proxy"));
        $r1.setLocalAddress((InetAddress) httpParams.getParameter("http.route.local-address"));
        $r1.setProxyPreferredAuthSchemes((Collection) httpParams.getParameter("http.auth.proxy-scheme-pref"));
        $r1.setTargetPreferredAuthSchemes((Collection) httpParams.getParameter("http.auth.target-scheme-pref"));
        $r1.setAuthenticationEnabled(httpParams.getBooleanParameter("http.protocol.handle-authentication", true));
        $r1.setCircularRedirectsAllowed(httpParams.getBooleanParameter("http.protocol.allow-circular-redirects", false));
        $r1.setConnectionRequestTimeout((int) httpParams.getLongParameter("http.conn-manager.timeout", 0));
        $r1.setCookieSpec((String) httpParams.getParameter("http.protocol.cookie-policy"));
        $r1.setMaxRedirects(httpParams.getIntParameter("http.protocol.max-redirects", 50));
        $r1.setRedirectsEnabled(httpParams.getBooleanParameter("http.protocol.handle-redirects", true));
        $r1.setRelativeRedirectsAllowed(!httpParams.getBooleanParameter("http.protocol.reject-relative-redirect", false));
        return $r1.build();
    }
}
