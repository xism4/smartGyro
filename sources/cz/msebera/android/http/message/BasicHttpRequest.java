package cz.msebera.android.http.message;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.Args;

public class BasicHttpRequest extends AbstractHttpMessage implements HttpRequest {
    private final String method;
    private RequestLine requestline;
    private final String uri;

    public BasicHttpRequest(RequestLine $r2) {
        Args.notNull($r2, "Request line");
        this.requestline = $r2;
        this.method = $r2.getMethod();
        this.uri = $r2.getUri();
    }

    public BasicHttpRequest(String str, String str2, ProtocolVersion protocolVersion) {
        this(new BasicRequestLine(str, str2, protocolVersion));
    }

    public ProtocolVersion getProtocolVersion() {
        return getRequestLine().getProtocolVersion();
    }

    public RequestLine getRequestLine() {
        if (this.requestline == null) {
            this.requestline = new BasicRequestLine(this.method, this.uri, HttpVersion.HTTP_1_1);
        }
        return this.requestline;
    }

    public String toString() {
        return this.method + ' ' + this.uri + ' ' + this.headergroup;
    }
}
