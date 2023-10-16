package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthProtocolState;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.execchain.HttpContext;

public class HttpAuthenticator {
    public HttpClientAndroidLog log;

    /* renamed from: cz.msebera.android.http.impl.auth.HttpAuthenticator$1  reason: invalid class name */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState = new int[AuthProtocolState.values().length];

        static {
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.CHALLENGED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.HANDSHAKE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.UNCHALLENGED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public HttpAuthenticator(HttpClientAndroidLog $r2) {
        this.log = $r2 == null ? new HttpClientAndroidLog(getClass()) : $r2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (r14 != 5) goto L_0x00f5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x013f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleAuthChallenge(cz.msebera.android.http.HttpHost r23, cz.msebera.android.http.HttpResponse r24, cz.msebera.android.http.client.AuthenticationStrategy r25, cz.msebera.android.http.auth.AuthState r26, cz.msebera.android.http.execchain.HttpContext r27) {
        /*
            r22 = this;
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            boolean r5 = r4.isDebugEnabled()     // Catch:{ MalformedChallengeException -> 0x0141 }
            if (r5 == 0) goto L_0x0028
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()     // Catch:{ MalformedChallengeException -> 0x0141 }
            r0 = r23
            java.lang.String r7 = r0.toHostString()     // Catch:{ MalformedChallengeException -> 0x0141 }
            r6.append(r7)     // Catch:{ MalformedChallengeException -> 0x0141 }
            java.lang.String r8 = " requested authentication"
            r6.append(r8)     // Catch:{ MalformedChallengeException -> 0x0141 }
            java.lang.String r7 = r6.toString()     // Catch:{ MalformedChallengeException -> 0x0141 }
            r4.debug(r7)     // Catch:{ MalformedChallengeException -> 0x0141 }
        L_0x0028:
            r0 = r25
            r1 = r23
            r2 = r24
            r3 = r27
            java.util.Map r9 = r0.getChallenges(r1, r2, r3)     // Catch:{ MalformedChallengeException -> 0x0141 }
            boolean r5 = r9.isEmpty()     // Catch:{ MalformedChallengeException -> 0x0141 }
            if (r5 == 0) goto L_0x0045
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            java.lang.String r8 = "Response contains no authentication challenges"
            r4.debug(r8)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r10 = 0
            return r10
        L_0x0045:
            r0 = r26
            cz.msebera.android.http.auth.AuthScheme r11 = r0.getAuthScheme()     // Catch:{ MalformedChallengeException -> 0x0141 }
            int[] r12 = cz.msebera.android.http.impl.auth.HttpAuthenticator.AnonymousClass1.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState
            r0 = r26
            cz.msebera.android.http.auth.AuthProtocolState r13 = r0.getState()     // Catch:{ MalformedChallengeException -> 0x0141 }
            int r14 = r13.ordinal()     // Catch:{ MalformedChallengeException -> 0x0141 }
            r14 = r12[r14]
            r10 = 1
            if (r14 == r10) goto L_0x0075
            r10 = 2
            if (r14 == r10) goto L_0x0075
            r10 = 3
            if (r14 == r10) goto L_0x006d
            r10 = 4
            if (r14 == r10) goto L_0x006b
            r10 = 5
            if (r14 == r10) goto L_0x0098
            goto L_0x00f5
        L_0x006b:
            r10 = 0
            return r10
        L_0x006d:
            r0 = r26
            r0.reset()     // Catch:{ MalformedChallengeException -> 0x0141 }
            goto L_0x00f5
        L_0x0075:
            if (r11 != 0) goto L_0x0098
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            java.lang.String r8 = "Auth scheme is null"
            r4.debug(r8)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r15 = 0
            r0 = r25
            r1 = r23
            r2 = r27
            r0.authFailed(r1, r15, r2)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r0 = r26
            r0.reset()     // Catch:{ MalformedChallengeException -> 0x0141 }
            cz.msebera.android.http.auth.AuthProtocolState r13 = cz.msebera.android.http.auth.AuthProtocolState.FAILURE
            r0 = r26
            r0.setState(r13)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r10 = 0
            return r10
        L_0x0098:
            if (r11 == 0) goto L_0x00f5
            java.lang.String r7 = r11.getSchemeName()     // Catch:{ MalformedChallengeException -> 0x0141 }
            java.util.Locale r16 = java.util.Locale.ROOT
            r0 = r16
            java.lang.String r7 = r7.toLowerCase(r0)     // Catch:{ MalformedChallengeException -> 0x0141 }
            java.lang.Object r17 = r9.get(r7)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r19 = r17
            cz.msebera.android.http.Header r19 = (cz.msebera.android.http.Header) r19
            r18 = r19
            if (r18 == 0) goto L_0x006d
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            java.lang.String r8 = "Authorization challenge processed"
            r4.debug(r8)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r0 = r18
            r11.processChallenge(r0)     // Catch:{ MalformedChallengeException -> 0x0141 }
            boolean r5 = r11.isComplete()     // Catch:{ MalformedChallengeException -> 0x0141 }
            if (r5 == 0) goto L_0x00ec
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            java.lang.String r8 = "Authentication failed"
            r4.debug(r8)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r0 = r26
            cz.msebera.android.http.auth.AuthScheme r11 = r0.getAuthScheme()     // Catch:{ MalformedChallengeException -> 0x0141 }
            r0 = r25
            r1 = r23
            r2 = r27
            r0.authFailed(r1, r11, r2)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r0 = r26
            r0.reset()     // Catch:{ MalformedChallengeException -> 0x0141 }
            cz.msebera.android.http.auth.AuthProtocolState r13 = cz.msebera.android.http.auth.AuthProtocolState.FAILURE
            r0 = r26
            r0.setState(r13)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r10 = 0
            return r10
        L_0x00ec:
            cz.msebera.android.http.auth.AuthProtocolState r13 = cz.msebera.android.http.auth.AuthProtocolState.HANDSHAKE
            r0 = r26
            r0.setState(r13)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r10 = 1
            return r10
        L_0x00f5:
            r0 = r25
            r1 = r23
            r2 = r24
            r3 = r27
            java.util.Queue r20 = r0.select(r9, r1, r2, r3)     // Catch:{ MalformedChallengeException -> 0x0141 }
            if (r20 == 0) goto L_0x013f
            r0 = r20
            boolean r5 = r0.isEmpty()     // Catch:{ MalformedChallengeException -> 0x0141 }
            if (r5 != 0) goto L_0x016f
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            boolean r5 = r4.isDebugEnabled()     // Catch:{ MalformedChallengeException -> 0x0141 }
            if (r5 == 0) goto L_0x012f
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()     // Catch:{ MalformedChallengeException -> 0x0141 }
            java.lang.String r8 = "Selected authentication options: "
            r6.append(r8)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r0 = r20
            r6.append(r0)     // Catch:{ MalformedChallengeException -> 0x0141 }
            java.lang.String r7 = r6.toString()     // Catch:{ MalformedChallengeException -> 0x0141 }
            r4.debug(r7)     // Catch:{ MalformedChallengeException -> 0x0141 }
        L_0x012f:
            cz.msebera.android.http.auth.AuthProtocolState r13 = cz.msebera.android.http.auth.AuthProtocolState.CHALLENGED
            r0 = r26
            r0.setState(r13)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r0 = r26
            r1 = r20
            r0.update((java.util.Queue) r1)     // Catch:{ MalformedChallengeException -> 0x0141 }
            r10 = 1
            return r10
        L_0x013f:
            r10 = 0
            return r10
        L_0x0141:
            r21 = move-exception
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            boolean r5 = r4.isWarnEnabled()
            if (r5 == 0) goto L_0x016a
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r4 = r0.log
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Malformed challenge: "
            r6.append(r8)
            r0 = r21
            java.lang.String r7 = r0.getMessage()
            r6.append(r7)
            java.lang.String r7 = r6.toString()
            r4.warn(r7)
        L_0x016a:
            r0 = r26
            r0.reset()
        L_0x016f:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.auth.HttpAuthenticator.handleAuthChallenge(cz.msebera.android.http.HttpHost, cz.msebera.android.http.HttpResponse, cz.msebera.android.http.client.AuthenticationStrategy, cz.msebera.android.http.auth.AuthState, cz.msebera.android.http.execchain.HttpContext):boolean");
    }

    public boolean isAuthenticationRequested(HttpHost httpHost, HttpResponse httpResponse, AuthenticationStrategy authenticationStrategy, AuthState authState, HttpContext httpContext) {
        if (authenticationStrategy.isAuthenticationRequested(httpHost, httpResponse, httpContext)) {
            this.log.debug("Authentication required");
            if (authState.getState() != AuthProtocolState.SUCCESS) {
                return true;
            }
            authenticationStrategy.authFailed(httpHost, authState.getAuthScheme(), httpContext);
            return true;
        }
        int $i0 = AnonymousClass1.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[authState.getState().ordinal()];
        if ($i0 == 1 || $i0 == 2) {
            this.log.debug("Authentication succeeded");
            authState.setState(AuthProtocolState.SUCCESS);
            authenticationStrategy.authSucceeded(httpHost, authState.getAuthScheme(), httpContext);
            return false;
        } else if ($i0 == 3) {
            return false;
        } else {
            authState.setState(AuthProtocolState.UNCHALLENGED);
            return false;
        }
    }
}
