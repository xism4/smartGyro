package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.client.RedirectHandler;
import cz.msebera.android.http.client.RedirectStrategy;
import cz.msebera.android.http.client.auth.HttpDelete;
import cz.msebera.android.http.client.auth.HttpHead;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.execchain.HttpContext;
import java.net.URI;

@Deprecated
class DefaultRedirectStrategyAdaptor implements RedirectStrategy {
    private final RedirectHandler handler;

    public DefaultRedirectStrategyAdaptor(RedirectHandler redirectHandler) {
        this.handler = redirectHandler;
    }

    public RedirectHandler getHandler() {
        return this.handler;
    }

    public HttpUriRequest getRedirect(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        URI $r5 = this.handler.getLocationURI(httpResponse, httpContext);
        return httpRequest.getRequestLine().getMethod().equalsIgnoreCase("HEAD") ? new HttpDelete($r5) : new HttpHead($r5);
    }

    public boolean isRedirected(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        return this.handler.isRedirectRequested(httpResponse, httpContext);
    }
}
