package cz.msebera.android.http.impl.auth;

public class UnsupportedDigestAlgorithmException extends RuntimeException {
    public UnsupportedDigestAlgorithmException(String str) {
        super(str);
    }
}
