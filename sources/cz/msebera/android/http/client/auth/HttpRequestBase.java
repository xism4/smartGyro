package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.message.BasicRequestLine;
import cz.msebera.android.http.util.HttpProtocolParams;
import java.net.URI;

public abstract class HttpRequestBase extends AbstractExecutionAwareRequest implements HttpUriRequest, Configurable {
    private RequestConfig config;
    private URI uri;
    private ProtocolVersion version;

    public RequestConfig getConfig() {
        return this.config;
    }

    public abstract String getMethod();

    public ProtocolVersion getProtocolVersion() {
        ProtocolVersion $r1 = this.version;
        return $r1 != null ? $r1 : HttpProtocolParams.getVersion(getParams());
    }

    public RequestLine getRequestLine() {
        String $r1 = getMethod();
        ProtocolVersion $r2 = getProtocolVersion();
        URI $r3 = getURI();
        String $r4 = $r3 != null ? $r3.toASCIIString() : null;
        if ($r4 == null || $r4.isEmpty()) {
            $r4 = "/";
        }
        return new BasicRequestLine($r1, $r4, $r2);
    }

    public URI getURI() {
        return this.uri;
    }

    public void setConfig(RequestConfig requestConfig) {
        this.config = requestConfig;
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.version = protocolVersion;
    }

    public void setURI(URI uri2) {
        this.uri = uri2;
    }

    public String toString() {
        return getMethod() + " " + getURI() + " " + getProtocolVersion();
    }
}
