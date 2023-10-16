package cz.msebera.android.http.auth;

import c.a.a.a.a.a;
import cz.msebera.android.http.mime.Args;
import java.util.Collection;
import java.util.Queue;

public class AuthState {
    private Queue<a> authOptions;
    private AuthScheme authScheme;
    private AuthScope authScope;
    private Credentials credentials;
    private AuthProtocolState state = AuthProtocolState.UNCHALLENGED;

    public Queue getAuthOptions() {
        return this.authOptions;
    }

    public AuthScheme getAuthScheme() {
        return this.authScheme;
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public AuthProtocolState getState() {
        return this.state;
    }

    public void reset() {
        this.state = AuthProtocolState.UNCHALLENGED;
        this.authOptions = null;
        this.authScheme = null;
        this.authScope = null;
        this.credentials = null;
    }

    public void setAuthScheme(AuthScheme authScheme2) {
        if (authScheme2 == null) {
            reset();
        } else {
            this.authScheme = authScheme2;
        }
    }

    public void setState(AuthProtocolState $r1) {
        if ($r1 == null) {
            $r1 = AuthProtocolState.UNCHALLENGED;
        }
        this.state = $r1;
    }

    public String toString() {
        StringBuilder $r1 = new StringBuilder();
        $r1.append("state:");
        $r1.append(this.state);
        $r1.append(";");
        if (this.authScheme != null) {
            $r1.append("auth scheme:");
            $r1.append(this.authScheme.getSchemeName());
            $r1.append(";");
        }
        if (this.credentials != null) {
            $r1.append("credentials present");
        }
        return $r1.toString();
    }

    public void update(AuthScheme authScheme2, Credentials credentials2) {
        Args.notNull(authScheme2, "Auth scheme");
        Args.notNull(credentials2, "Credentials");
        this.authScheme = authScheme2;
        this.credentials = credentials2;
        this.authOptions = null;
    }

    public void update(Credentials credentials2) {
        this.credentials = credentials2;
    }

    public void update(Queue queue) {
        Args.notEmpty((Collection) queue, "Queue of auth options");
        this.authOptions = queue;
        this.authScheme = null;
        this.credentials = null;
    }
}
