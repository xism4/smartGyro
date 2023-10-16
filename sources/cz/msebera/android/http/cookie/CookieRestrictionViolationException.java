package cz.msebera.android.http.cookie;

public class CookieRestrictionViolationException extends MalformedCookieException {
    public CookieRestrictionViolationException(String str) {
        super(str);
    }
}
