package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.HttpResponse;
import java.io.Closeable;

public interface CloseableHttpResponse extends HttpResponse, Closeable {
}
