package cz.msebera.android.http.client.cache;

import c.a.a.a.b.e.c;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.auth.AuthProtocolState;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;

public class RequestAuthCache implements HttpRequestInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(c.class);

    private void doPreemptiveAuth(HttpHost httpHost, AuthScheme authScheme, AuthState authState, CredentialsProvider credentialsProvider) {
        String $r5 = authScheme.getSchemeName();
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog $r6 = this.log;
            $r6.debug("Re-using cached '" + $r5 + "' auth scheme for " + httpHost);
        }
        Credentials $r10 = credentialsProvider.getCredentials(new AuthScope(httpHost, AuthScope.ANY_REALM, $r5));
        if ($r10 != null) {
            authState.setState("BASIC".equalsIgnoreCase(authScheme.getSchemeName()) ? AuthProtocolState.CHALLENGED : AuthProtocolState.SUCCESS);
            authState.update(authScheme, $r10);
            return;
        }
        this.log.debug("No credentials for preemptive authentication");
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        AuthScheme $r16;
        AuthScheme $r162;
        HttpClientAndroidLog $r5;
        String $r6;
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        HttpClientContext $r3 = HttpClientContext.adapt(httpContext);
        AuthCache $r4 = $r3.getAuthCache();
        if ($r4 == null) {
            $r5 = this.log;
            $r6 = "Auth cache not set in the context";
        } else {
            CredentialsProvider $r7 = $r3.getCredentialsProvider();
            if ($r7 == null) {
                $r5 = this.log;
                $r6 = "Credentials provider not set in the context";
            } else {
                RouteInfo $r8 = $r3.getHttpRoute();
                if ($r8 == null) {
                    $r5 = this.log;
                    $r6 = "Route info not set in the context";
                } else {
                    HttpHost $r9 = $r3.getTargetHost();
                    HttpHost $r10 = $r9;
                    if ($r9 == null) {
                        $r5 = this.log;
                        $r6 = "Target host not set in the context";
                    } else {
                        if ($r9.getPort() < 0) {
                            $r10 = new HttpHost($r9.getHostName(), $r8.getTargetHost().getPort(), $r9.getSchemeName());
                        }
                        AuthState $r13 = $r3.getTargetAuthState();
                        if (!($r13 == null || $r13.getState() != AuthProtocolState.UNCHALLENGED || ($r162 = $r4.get($r10)) == null)) {
                            doPreemptiveAuth($r10, $r162, $r13, $r7);
                        }
                        HttpHost $r92 = $r8.getProxyHost();
                        AuthState $r132 = $r3.getProxyAuthState();
                        if ($r92 != null && $r132 != null && $r132.getState() == AuthProtocolState.UNCHALLENGED && ($r16 = $r4.get($r92)) != null) {
                            doPreemptiveAuth($r92, $r16, $r132, $r7);
                            return;
                        }
                        return;
                    }
                }
            }
        }
        $r5.debug($r6);
    }
}
