package cz.msebera.android.http.client.cache;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.HttpRoutedConnection;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;

@Deprecated
public class RequestProxyAuthentication extends RequestAuthenticationBase {
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        HttpClientAndroidLog $r5;
        String $r6;
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        if (!httpRequest.containsHeader("Proxy-Authorization")) {
            HttpRoutedConnection $r4 = (HttpRoutedConnection) httpContext.getAttribute("http.connection");
            if ($r4 == null) {
                $r5 = this.log;
                $r6 = "HTTP connection not set in the context";
            } else if (!$r4.getRoute().isTunnelled()) {
                AuthState $r8 = (AuthState) httpContext.getAttribute("http.auth.proxy-scope");
                if ($r8 == null) {
                    $r5 = this.log;
                    $r6 = "Proxy auth state not set in the context";
                } else {
                    if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog $r52 = this.log;
                        $r52.debug("Proxy auth state: " + $r8.getState());
                    }
                    process($r8, httpRequest, httpContext);
                    return;
                }
            } else {
                return;
            }
            $r5.debug($r6);
        }
    }
}
