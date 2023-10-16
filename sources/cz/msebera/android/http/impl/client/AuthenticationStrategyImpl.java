package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthOption;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthSchemeProvider;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.auth.MalformedChallengeException;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.client.cache.HttpClientContext;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.protocol.Lookup;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

abstract class AuthenticationStrategyImpl implements AuthenticationStrategy {
    private static final List<String> DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(new String[]{"Negotiate", "Kerberos", "NTLM", "Digest", "Basic"}));
    private final int challengeCode;
    private final String headerName;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    AuthenticationStrategyImpl(int i, String str) {
        this.challengeCode = i;
        this.headerName = str;
    }

    public void authFailed(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        Args.notNull(httpHost, "Host");
        Args.notNull(httpContext, "HTTP context");
        AuthCache $r5 = HttpClientContext.adapt(httpContext).getAuthCache();
        if ($r5 != null) {
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r6 = this.log;
                $r6.debug("Clearing cached auth scheme for " + httpHost);
            }
            $r5.remove(httpHost);
        }
    }

    public void authSucceeded(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        Args.notNull(httpHost, "Host");
        Args.notNull(authScheme, "Auth scheme");
        Args.notNull(httpContext, "HTTP context");
        HttpClientContext $r4 = HttpClientContext.adapt(httpContext);
        if (isCachable(authScheme)) {
            AuthCache $r5 = $r4.getAuthCache();
            AuthCache $r6 = $r5;
            if ($r5 == null) {
                $r6 = r10;
                AuthCache r10 = new BasicAuthCache();
                $r4.setAuthCache($r6);
            }
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r7 = this.log;
                StringBuilder $r8 = r11;
                StringBuilder r11 = new StringBuilder();
                $r8.append("Caching '");
                $r8.append(authScheme.getSchemeName());
                $r8.append("' auth scheme for ");
                $r8.append(httpHost);
                $r7.debug($r8.toString());
            }
            $r6.put(httpHost, authScheme);
        }
    }

    public Map getChallenges(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        int $i2;
        CharArrayBuffer $r9;
        Args.notNull(httpResponse, "HTTP response");
        Header[] $r6 = httpResponse.getHeaders(this.headerName);
        HashMap $r7 = new HashMap($r6.length);
        for (Header $r1 : $r6) {
            if ($r1 instanceof FormattedHeader) {
                FormattedHeader $r8 = (FormattedHeader) $r1;
                $r9 = $r8.getBuffer();
                $i2 = $r8.getValuePos();
            } else {
                String $r5 = $r1.getValue();
                if ($r5 != null) {
                    $r9 = new CharArrayBuffer($r5.length());
                    $r9.append($r5);
                    $i2 = 0;
                } else {
                    throw new MalformedChallengeException("Header value is null");
                }
            }
            while ($i2 < $r9.length() && HTTP.isWhitespace($r9.charAt($i2))) {
                $i2++;
            }
            int $i3 = $i2;
            while ($i3 < $r9.length() && !HTTP.isWhitespace($r9.charAt($i3))) {
                $i3++;
            }
            $r7.put($r9.substring($i2, $i3).toLowerCase(Locale.ROOT), $r1);
        }
        return $r7;
    }

    /* access modifiers changed from: package-private */
    public abstract Collection getPreferredAuthSchemes(RequestConfig requestConfig);

    public boolean isAuthenticationRequested(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpResponse, "HTTP response");
        return httpResponse.getStatusLine().getStatusCode() == this.challengeCode;
    }

    /* access modifiers changed from: protected */
    public boolean isCachable(AuthScheme authScheme) {
        if (authScheme == null || !authScheme.isComplete()) {
            return false;
        }
        String $r2 = authScheme.getSchemeName();
        return $r2.equalsIgnoreCase("Basic") || $r2.equalsIgnoreCase("Digest");
    }

    public Queue select(Map map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        HttpClientAndroidLog $r8;
        String $r9;
        Args.notNull(map, "Map of auth challenges");
        Args.notNull(httpHost, "Host");
        Args.notNull(httpResponse, "HTTP response");
        Args.notNull(httpContext, "HTTP context");
        HttpClientContext $r5 = HttpClientContext.adapt(httpContext);
        LinkedList $r6 = r25;
        LinkedList r25 = new LinkedList();
        Lookup $r7 = $r5.getAuthSchemeRegistry();
        if ($r7 == null) {
            $r8 = this.log;
            $r9 = "Auth scheme registry not set in the context";
        } else {
            CredentialsProvider $r10 = $r5.getCredentialsProvider();
            if ($r10 == null) {
                $r8 = this.log;
                $r9 = "Credentials provider not set in the context";
            } else {
                Object $r12 = getPreferredAuthSchemes($r5.getRequestConfig());
                Object $r13 = $r12;
                if ($r12 == null) {
                    $r13 = DEFAULT_SCHEME_PRIORITY;
                }
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog $r82 = this.log;
                    StringBuilder $r14 = r0;
                    StringBuilder sb = new StringBuilder();
                    $r14.append("Authentication schemes in the order of preference: ");
                    $r14.append($r13);
                    $r82.debug($r14.toString());
                }
                for (String $r92 : (Collection) $r13) {
                    Header $r18 = (Header) map.get($r92.toLowerCase(Locale.ROOT));
                    if ($r18 != null) {
                        AuthSchemeProvider $r19 = (AuthSchemeProvider) $r7.lookup($r92);
                        if ($r19 != null) {
                            AuthScheme $r20 = $r19.create(httpContext);
                            $r20.processChallenge($r18);
                            Credentials $r23 = $r10.getCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort(), $r20.getRealm(), $r20.getSchemeName()));
                            if ($r23 != null) {
                                $r6.add(new AuthOption($r20, $r23));
                            }
                        } else if (this.log.isWarnEnabled()) {
                            HttpClientAndroidLog $r83 = this.log;
                            StringBuilder $r142 = r0;
                            StringBuilder sb2 = new StringBuilder();
                            $r142.append("Authentication scheme ");
                            $r142.append($r92);
                            $r142.append(" not supported");
                            $r83.warn($r142.toString());
                        }
                    } else if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog $r84 = this.log;
                        StringBuilder $r143 = r0;
                        StringBuilder sb3 = new StringBuilder();
                        $r143.append("Challenge for ");
                        $r143.append($r92);
                        $r143.append(" authentication scheme not available");
                        $r84.debug($r143.toString());
                    }
                }
                return $r6;
            }
        }
        $r8.debug($r9);
        return $r6;
    }
}
