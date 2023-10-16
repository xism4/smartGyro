package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.AuthenticationException;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.auth.InvalidCredentialsException;
import cz.msebera.android.http.auth.MalformedChallengeException;
import cz.msebera.android.http.auth.NTCredentials;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;

public class NTLMScheme extends AuthSchemeBase {
    private String challenge;
    private final NTLMEngine engine;
    private State state;

    enum State {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        MSG_TYPE1_GENERATED,
        MSG_TYPE2_RECEVIED,
        MSG_TYPE3_GENERATED,
        FAILED
    }

    public NTLMScheme() {
        this(new NTLMEngineImpl());
    }

    public NTLMScheme(NTLMEngine nTLMEngine) {
        Args.notNull(nTLMEngine, "NTLM engine");
        this.engine = nTLMEngine;
        this.state = State.UNINITIATED;
        this.challenge = null;
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        try {
            NTCredentials $r4 = (NTCredentials) credentials;
            State $r5 = this.state;
            if ($r5 == State.FAILED) {
                throw new AuthenticationException("NTLM authentication failed");
            } else if ($r5 == State.CHALLENGE_RECEIVED) {
                $r4.getDomain();
                throw new NullPointerException("Null throw statement replaced by Soot");
            } else if ($r5 == State.MSG_TYPE2_RECEVIED) {
                $r4.getUserName();
                throw new NullPointerException("Null throw statement replaced by Soot");
            } else {
                throw new AuthenticationException("Unexpected state: " + this.state);
            }
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
    }

    public String getRealm() {
        return null;
    }

    public String getSchemeName() {
        return "ntlm";
    }

    public boolean isComplete() {
        State $r1 = this.state;
        return $r1 == State.MSG_TYPE3_GENERATED || $r1 == State.FAILED;
    }

    public boolean isConnectionBased() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) {
        State $r3;
        this.challenge = charArrayBuffer.substringTrimmed(i, i2);
        if (this.challenge.isEmpty()) {
            $r3 = this.state == State.UNINITIATED ? State.CHALLENGE_RECEIVED : State.FAILED;
        } else if (this.state.compareTo(State.MSG_TYPE1_GENERATED) < 0) {
            this.state = State.FAILED;
            throw new MalformedChallengeException("Out of sequence NTLM response message");
        } else if (this.state == State.MSG_TYPE1_GENERATED) {
            $r3 = State.MSG_TYPE2_RECEVIED;
        } else {
            return;
        }
        this.state = $r3;
    }
}
