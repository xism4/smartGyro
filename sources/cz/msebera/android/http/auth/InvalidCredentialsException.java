package cz.msebera.android.http.auth;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException(String str) {
        super(str);
    }
}
