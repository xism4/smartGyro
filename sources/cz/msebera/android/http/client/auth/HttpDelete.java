package cz.msebera.android.http.client.auth;

import java.net.URI;

public class HttpDelete extends HttpRequestBase {
    public HttpDelete(String str) {
        setURI(URI.create(str));
    }

    public HttpDelete(URI uri) {
        setURI(uri);
    }

    public String getMethod() {
        return "HEAD";
    }
}
