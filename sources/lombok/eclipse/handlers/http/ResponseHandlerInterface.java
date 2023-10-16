package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpResponse;
import java.net.URI;

public interface ResponseHandlerInterface {
    boolean getUsePoolThread();

    boolean getUseSynchronousMode();

    void onPostProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse);

    void onPreProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse);

    void sendCancelMessage();

    void sendFailureMessage(int i, Header[] headerArr, byte[] bArr, Throwable th);

    void sendFinishMessage();

    void sendResponseMessage(HttpResponse httpResponse);

    void sendRetryMessage(int i);

    void sendStartMessage();

    void setRequestHeaders(Header[] headerArr);

    void setRequestURI(URI uri);
}
