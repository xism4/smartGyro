package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.mime.Args;

public class RequestContent implements HttpRequestInterceptor {
    private final boolean overwrite;

    public RequestContent() {
        this(false);
    }

    public RequestContent(boolean z) {
        this.overwrite = z;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            if (this.overwrite) {
                httpRequest.removeHeaders("Transfer-Encoding");
                httpRequest.removeHeaders("Content-Length");
            } else if (httpRequest.containsHeader("Transfer-Encoding")) {
                throw new ProtocolException("Transfer-encoding header already present");
            } else if (httpRequest.containsHeader("Content-Length")) {
                throw new ProtocolException("Content-Length header already present");
            }
            ProtocolVersion $r4 = httpRequest.getRequestLine().getProtocolVersion();
            HttpEntity $r6 = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
            if ($r6 == null) {
                httpRequest.addHeader("Content-Length", "0");
                return;
            }
            if (!$r6.isChunked() && $r6.getContentLength() >= 0) {
                httpRequest.addHeader("Content-Length", Long.toString($r6.getContentLength()));
            } else if (!$r4.lessEquals(HttpVersion.HTTP_1_0)) {
                httpRequest.addHeader("Transfer-Encoding", "chunked");
            } else {
                throw new ProtocolException("Chunked transfer encoding not allowed for " + $r4);
            }
            if ($r6.getContentType() != null && !httpRequest.containsHeader("Content-Type")) {
                httpRequest.addHeader($r6.getContentType());
            }
            if ($r6.getContentEncoding() != null && !httpRequest.containsHeader("Content-Encoding")) {
                httpRequest.addHeader($r6.getContentEncoding());
            }
        }
    }
}
