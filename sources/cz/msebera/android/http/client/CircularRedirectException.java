package cz.msebera.android.http.client;

public class CircularRedirectException extends RedirectException {
    public CircularRedirectException(String str) {
        super(str);
    }
}
