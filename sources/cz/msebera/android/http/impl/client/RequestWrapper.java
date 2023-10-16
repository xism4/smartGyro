package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.message.AbstractHttpMessage;
import cz.msebera.android.http.message.BasicRequestLine;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpProtocolParams;
import java.net.URI;
import java.net.URISyntaxException;

@Deprecated
public class RequestWrapper extends AbstractHttpMessage implements HttpUriRequest {
    private int execCount;
    private String method;
    private final HttpRequest original;
    private URI uri;
    private ProtocolVersion version;

    public RequestWrapper(HttpRequest httpRequest) {
        ProtocolVersion $r7;
        Args.notNull(httpRequest, "HTTP request");
        this.original = httpRequest;
        setParams(httpRequest.getParams());
        setHeaders(httpRequest.getAllHeaders());
        if (httpRequest instanceof HttpUriRequest) {
            HttpUriRequest $r4 = (HttpUriRequest) httpRequest;
            this.uri = $r4.getURI();
            this.method = $r4.getMethod();
            $r7 = null;
        } else {
            RequestLine $r8 = httpRequest.getRequestLine();
            try {
                this.uri = new URI($r8.getUri());
                this.method = $r8.getMethod();
                $r7 = httpRequest.getProtocolVersion();
            } catch (URISyntaxException $r9) {
                throw new ProtocolException("Invalid request URI: " + $r8.getUri(), $r9);
            }
        }
        this.version = $r7;
        this.execCount = 0;
    }

    public void abort() {
        throw new UnsupportedOperationException();
    }

    public int getExecCount() {
        return this.execCount;
    }

    public String getMethod() {
        return this.method;
    }

    public HttpRequest getOriginal() {
        return this.original;
    }

    public ProtocolVersion getProtocolVersion() {
        if (this.version == null) {
            this.version = HttpProtocolParams.getVersion(getParams());
        }
        return this.version;
    }

    public RequestLine getRequestLine() {
        ProtocolVersion $r1 = getProtocolVersion();
        URI $r2 = this.uri;
        String $r3 = $r2 != null ? $r2.toASCIIString() : null;
        if ($r3 == null || $r3.isEmpty()) {
            $r3 = "/";
        }
        return new BasicRequestLine(getMethod(), $r3, $r1);
    }

    public URI getURI() {
        return this.uri;
    }

    public void incrementExecCount() {
        this.execCount++;
    }

    public boolean isAborted() {
        return false;
    }

    public boolean isRepeatable() {
        return true;
    }

    public void resetHeaders() {
        this.headergroup.clear();
        setHeaders(this.original.getAllHeaders());
    }

    public void setURI(URI uri2) {
        this.uri = uri2;
    }
}
