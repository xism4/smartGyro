package cz.msebera.android.http.cookie;

public interface ClientCookie extends Cookie {
    boolean containsAttribute(String str);

    String getAttribute(String str);
}
