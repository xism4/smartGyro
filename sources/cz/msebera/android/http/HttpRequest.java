package cz.msebera.android.http;

public interface HttpRequest extends HttpMessage {
    RequestLine getRequestLine();
}
