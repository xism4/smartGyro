package cz.msebera.android.http.client;

public class HttpResponseException extends ClientProtocolException {
    private final int statusCode;

    public HttpResponseException(int i, String str) {
        super(str);
        this.statusCode = i;
    }
}
