package cz.msebera.android.http.client.cache;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;

@Deprecated
public class RequestTargetAuthentication extends RequestAuthenticationBase {
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        if (!httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") && !httpRequest.containsHeader("Authorization")) {
            AuthState $r6 = (AuthState) httpContext.getAttribute("http.auth.target-scope");
            if ($r6 == null) {
                this.log.debug("Target auth state not set in the context");
                return;
            }
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r7 = this.log;
                $r7.debug("Target auth state: " + $r6.getState());
            }
            process($r6, httpRequest, httpContext);
        }
    }
}
