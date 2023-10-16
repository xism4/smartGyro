package cz.msebera.android.http;

public interface HttpResponse extends HttpMessage {
    HttpEntity getEntity();

    StatusLine getStatusLine();

    void setEntity(HttpEntity httpEntity);
}
