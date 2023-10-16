package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.HttpRequest;
import java.net.URI;

public interface HttpUriRequest extends HttpRequest {
    void abort();

    String getMethod();

    URI getURI();

    boolean isAborted();
}
