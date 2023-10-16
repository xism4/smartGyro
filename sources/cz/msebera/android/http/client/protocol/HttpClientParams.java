package cz.msebera.android.http.client.protocol;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public class HttpClientParams {
    public static long getConnectionManagerTimeout(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Long $r2 = (Long) httpParams.getParameter("http.conn-manager.timeout");
        return $r2 != null ? $r2.longValue() : (long) HttpConnectionParams.getConnectionTimeout(httpParams);
    }

    public static boolean isAuthenticating(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.protocol.handle-authentication", true);
    }

    public static boolean isRedirecting(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.protocol.handle-redirects", true);
    }
}
