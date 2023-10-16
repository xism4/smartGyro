package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.mime.Args;

public class RequestExpectContinue implements HttpRequestInterceptor {
    private final boolean activeByDefault;

    public RequestExpectContinue() {
        this(false);
    }

    public RequestExpectContinue(boolean z) {
        this.activeByDefault = z;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        if (!httpRequest.containsHeader("Expect") && (httpRequest instanceof HttpEntityEnclosingRequest)) {
            ProtocolVersion $r4 = httpRequest.getRequestLine().getProtocolVersion();
            HttpEntity $r6 = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
            if ($r6 != null && $r6.getContentLength() != 0 && !$r4.lessEquals(HttpVersion.HTTP_1_0) && httpRequest.getParams().getBooleanParameter("http.protocol.expect-continue", this.activeByDefault)) {
                httpRequest.addHeader("Expect", "100-continue");
            }
        }
    }
}
