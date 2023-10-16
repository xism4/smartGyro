package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpResponse;

@Deprecated
public class TunnelRefusedException extends HttpException {
    private final HttpResponse response;

    public TunnelRefusedException(String str, HttpResponse httpResponse) {
        super(str);
        this.response = httpResponse;
    }

    public HttpResponse getResponse() {
        return this.response;
    }
}
