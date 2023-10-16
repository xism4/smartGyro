package cz.msebera.android.http.execchain;

import cz.msebera.android.http.mime.Args;

@Deprecated
public final class DefaultedHttpContext implements HttpContext {
    private final HttpContext defaults;
    private final HttpContext local;

    public DefaultedHttpContext(HttpContext $r2, HttpContext httpContext) {
        Args.notNull($r2, "HTTP context");
        this.local = $r2;
        this.defaults = httpContext;
    }

    public Object getAttribute(String str) {
        Object $r3 = this.local.getAttribute(str);
        return $r3 == null ? this.defaults.getAttribute(str) : $r3;
    }

    public void setAttribute(String str, Object obj) {
        this.local.setAttribute(str, obj);
    }

    public String toString() {
        return "[local: " + this.local + "defaults: " + this.defaults + "]";
    }
}
