package cz.msebera.android.http.client.cache;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.auth.AuthOption;
import cz.msebera.android.http.auth.AuthProtocolState;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.auth.AuthenticationException;
import cz.msebera.android.http.auth.ContextAwareAuthScheme;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Asserts;
import java.util.Queue;

@Deprecated
abstract class RequestAuthenticationBase implements HttpRequestInterceptor {
    final HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    /* renamed from: cz.msebera.android.http.client.cache.RequestAuthenticationBase$1  reason: invalid class name */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState = new int[AuthProtocolState.values().length];

        static {
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.CHALLENGED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private Header authenticate(AuthScheme authScheme, Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        Asserts.notNull(authScheme, "Auth scheme");
        return authScheme instanceof ContextAwareAuthScheme ? ((ContextAwareAuthScheme) authScheme).authenticate(credentials, httpRequest, httpContext) : authScheme.authenticate(credentials, httpRequest);
    }

    private void ensureAuthScheme(AuthScheme authScheme) {
        Asserts.notNull(authScheme, "Auth scheme");
    }

    /* access modifiers changed from: package-private */
    public void process(AuthState authState, HttpRequest httpRequest, HttpContext httpContext) {
        AuthScheme $r4 = authState.getAuthScheme();
        Credentials $r5 = authState.getCredentials();
        int $i0 = AnonymousClass1.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[authState.getState().ordinal()];
        if ($i0 != 1) {
            if ($i0 == 2) {
                ensureAuthScheme($r4);
                if ($r4.isConnectionBased()) {
                    return;
                }
            } else if ($i0 == 3) {
                Queue $r8 = authState.getAuthOptions();
                if ($r8 != null) {
                    while (!$r8.isEmpty()) {
                        AuthOption $r10 = (AuthOption) $r8.remove();
                        AuthScheme $r42 = $r10.getAuthScheme();
                        Credentials $r52 = $r10.getCredentials();
                        authState.update($r42, $r52);
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog $r11 = this.log;
                            $r11.debug("Generating response to an authentication challenge using " + $r42.getSchemeName() + " scheme");
                        }
                        try {
                            httpRequest.addHeader(authenticate($r42, $r52, httpRequest, httpContext));
                            return;
                        } catch (AuthenticationException $r15) {
                            if (this.log.isWarnEnabled()) {
                                HttpClientAndroidLog $r112 = this.log;
                                $r112.warn($r42 + " authentication error: " + $r15.getMessage());
                            }
                        }
                    }
                    return;
                }
                ensureAuthScheme($r4);
            }
            if ($r4 != null) {
                try {
                    httpRequest.addHeader(authenticate($r4, $r5, httpRequest, httpContext));
                } catch (AuthenticationException $r16) {
                    if (this.log.isErrorEnabled()) {
                        HttpClientAndroidLog $r113 = this.log;
                        $r113.error($r4 + " authentication error: " + $r16.getMessage());
                    }
                }
            }
        }
    }
}
