package cz.msebera.android.http.execchain;

public interface HttpContext {
    Object getAttribute(String str);

    void setAttribute(String str, Object obj);
}
