package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.ChallengeState;
import cz.msebera.android.http.auth.ContextAwareAuthScheme;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.auth.MalformedChallengeException;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.Locale;

public abstract class AuthSchemeBase implements ContextAwareAuthScheme {
    private ChallengeState challengeState;

    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return authenticate(credentials, httpRequest);
    }

    public boolean isProxy() {
        ChallengeState $r2 = this.challengeState;
        return $r2 != null && $r2 == ChallengeState.PROXY;
    }

    /* access modifiers changed from: protected */
    public abstract void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2);

    public void processChallenge(Header header) {
        ChallengeState $r3;
        int $i0;
        CharArrayBuffer $r5;
        Args.notNull(header, "Header");
        String $r2 = header.getName();
        if ($r2.equalsIgnoreCase("WWW-Authenticate")) {
            $r3 = ChallengeState.TARGET;
        } else if ($r2.equalsIgnoreCase("Proxy-Authenticate")) {
            $r3 = ChallengeState.PROXY;
        } else {
            throw new MalformedChallengeException("Unexpected header name: " + $r2);
        }
        this.challengeState = $r3;
        if (header instanceof FormattedHeader) {
            FormattedHeader $r4 = (FormattedHeader) header;
            $r5 = $r4.getBuffer();
            $i0 = $r4.getValuePos();
        } else {
            String $r22 = header.getValue();
            if ($r22 != null) {
                $r5 = new CharArrayBuffer($r22.length());
                $r5.append($r22);
                $i0 = 0;
            } else {
                throw new MalformedChallengeException("Header value is null");
            }
        }
        while ($i0 < $r5.length() && HTTP.isWhitespace($r5.charAt($i0))) {
            $i0++;
        }
        int $i1 = $i0;
        while ($i1 < $r5.length() && !HTTP.isWhitespace($r5.charAt($i1))) {
            $i1++;
        }
        String $r23 = $r5.substring($i0, $i1);
        if ($r23.equalsIgnoreCase(getSchemeName())) {
            parseChallenge($r5, $i1, $r5.length());
            return;
        }
        throw new MalformedChallengeException("Invalid scheme identifier: " + $r23);
    }

    public String toString() {
        String $r2 = getSchemeName();
        return $r2 != null ? $r2.toUpperCase(Locale.ROOT) : super.toString();
    }
}
