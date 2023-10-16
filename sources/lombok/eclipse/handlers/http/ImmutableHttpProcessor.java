package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseInterceptor;
import cz.msebera.android.http.execchain.HttpContext;
import lombok.eclipse.handlers.http.AsyncHttpClient;

class ImmutableHttpProcessor implements HttpResponseInterceptor {
    final /* synthetic */ AsyncHttpClient httpClient;

    ImmutableHttpProcessor(AsyncHttpClient asyncHttpClient) {
        this.httpClient = asyncHttpClient;
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        Header $r4;
        HttpEntity $r3 = httpResponse.getEntity();
        if ($r3 != null && ($r4 = $r3.getContentEncoding()) != null) {
            for (HeaderElement $r6 : $r4.getElements()) {
                if ($r6.getName().equalsIgnoreCase("gzip")) {
                    httpResponse.setEntity(new AsyncHttpClient.InflatingEntity($r3));
                    return;
                }
            }
        }
    }
}
