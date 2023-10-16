package lombok.eclipse.handlers.http;

import cz.msebera.android.http.client.auth.HttpEntityEnclosingRequestBase;
import java.net.URI;

public final class Request extends HttpEntityEnclosingRequestBase {
    public Request(String str) {
        setURI(URI.create(str));
    }

    public String getMethod() {
        return "GET";
    }
}
