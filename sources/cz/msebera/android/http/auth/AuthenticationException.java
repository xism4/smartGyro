package cz.msebera.android.http.auth;

import cz.msebera.android.http.ProtocolException;

public class AuthenticationException extends ProtocolException {
    public AuthenticationException(String str) {
        super(str);
    }

    public AuthenticationException(String str, Throwable th) {
        super(str, th);
    }
}
