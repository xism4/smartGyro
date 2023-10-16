package cz.msebera.android.http.auth;

import cz.msebera.android.http.mime.Args;

public final class AuthOption {
    private final AuthScheme authScheme;
    private final Credentials creds;

    public AuthOption(AuthScheme authScheme2, Credentials credentials) {
        Args.notNull(authScheme2, "Auth scheme");
        Args.notNull(credentials, "User credentials");
        this.authScheme = authScheme2;
        this.creds = credentials;
    }

    public AuthScheme getAuthScheme() {
        return this.authScheme;
    }

    public Credentials getCredentials() {
        return this.creds;
    }

    public String toString() {
        return this.authScheme.toString();
    }
}
