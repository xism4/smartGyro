package cz.msebera.android.http.execchain;

import c.a.a.a.j;
import c.a.a.a.o;
import c.a.a.a.r;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.mime.Args;

public class HttpCoreContext implements HttpContext {
    private final HttpContext context;

    public HttpCoreContext() {
        this.context = new BasicHttpContext();
    }

    public HttpCoreContext(HttpContext httpContext) {
        this.context = httpContext;
    }

    public static HttpCoreContext adapt(HttpContext httpContext) {
        Args.notNull(httpContext, "HTTP context");
        return httpContext instanceof HttpCoreContext ? (HttpCoreContext) httpContext : new HttpCoreContext(httpContext);
    }

    public Object getAttribute(String str) {
        return this.context.getAttribute(str);
    }

    public Object getAttribute(String str, Class cls) {
        Args.notNull(cls, "Attribute class");
        Object $r3 = getAttribute(str);
        if ($r3 == null) {
            return null;
        }
        return cls.cast($r3);
    }

    public HttpConnection getConnection() {
        return (HttpConnection) getAttribute("http.connection", j.class);
    }

    public HttpRequest getRequest() {
        return (HttpRequest) getAttribute("http.request", r.class);
    }

    public HttpHost getTargetHost() {
        return (HttpHost) getAttribute("http.target_host", o.class);
    }

    public boolean isRequestSent() {
        Boolean $r2 = (Boolean) getAttribute("http.request_sent", Boolean.class);
        return $r2 != null && $r2.booleanValue();
    }

    public void setAttribute(String str, Object obj) {
        this.context.setAttribute(str, obj);
    }
}
