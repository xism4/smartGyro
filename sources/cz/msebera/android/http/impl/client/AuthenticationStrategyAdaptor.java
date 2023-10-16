package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthOption;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.AuthenticationException;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.client.AuthenticationHandler;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

@Deprecated
class AuthenticationStrategyAdaptor implements AuthenticationStrategy {
    private final AuthenticationHandler handler;
    public HttpClientAndroidLog log;

    private boolean isCachable(AuthScheme authScheme) {
        if (authScheme == null || !authScheme.isComplete()) {
            return false;
        }
        String $r2 = authScheme.getSchemeName();
        return $r2.equalsIgnoreCase("Basic") || $r2.equalsIgnoreCase("Digest");
    }

    public void authFailed(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        AuthCache $r6 = (AuthCache) httpContext.getAttribute("http.auth.auth-cache");
        if ($r6 != null) {
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r7 = this.log;
                $r7.debug("Removing from cache '" + authScheme.getSchemeName() + "' auth scheme for " + httpHost);
            }
            $r6.remove(httpHost);
        }
    }

    public void authSucceeded(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        AuthCache $r5 = (AuthCache) httpContext.getAttribute("http.auth.auth-cache");
        if (isCachable(authScheme)) {
            if ($r5 == null) {
                $r5 = r9;
                AuthCache r9 = new BasicAuthCache();
                httpContext.setAttribute("http.auth.auth-cache", $r5);
            }
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r6 = this.log;
                StringBuilder $r7 = r10;
                StringBuilder r10 = new StringBuilder();
                $r7.append("Caching '");
                $r7.append(authScheme.getSchemeName());
                $r7.append("' auth scheme for ");
                $r7.append(httpHost);
                $r6.debug($r7.toString());
            }
            $r5.put(httpHost, authScheme);
        }
    }

    public Map getChallenges(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        return this.handler.getChallenges(httpResponse, httpContext);
    }

    public AuthenticationHandler getHandler() {
        return this.handler;
    }

    public boolean isAuthenticationRequested(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        return this.handler.isAuthenticationRequested(httpResponse, httpContext);
    }

    public Queue select(Map map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(map, "Map of auth challenges");
        Args.notNull(httpHost, "Host");
        Args.notNull(httpResponse, "HTTP response");
        Args.notNull(httpContext, "HTTP context");
        LinkedList $r5 = new LinkedList();
        CredentialsProvider $r7 = (CredentialsProvider) httpContext.getAttribute("http.auth.credentials-provider");
        if ($r7 == null) {
            this.log.debug("Credentials provider not set in the context");
            return $r5;
        }
        try {
            AuthScheme $r10 = this.handler.selectScheme(map, httpResponse, httpContext);
            $r10.processChallenge((Header) map.get($r10.getSchemeName().toLowerCase(Locale.ROOT)));
            Credentials $r17 = $r7.getCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort(), $r10.getRealm(), $r10.getSchemeName()));
            if ($r17 != null) {
                $r5.add(new AuthOption($r10, $r17));
                return $r5;
            }
        } catch (AuthenticationException $r19) {
            if (this.log.isWarnEnabled()) {
                this.log.warn($r19.getMessage(), $r19);
            }
        }
        return $r5;
    }
}
