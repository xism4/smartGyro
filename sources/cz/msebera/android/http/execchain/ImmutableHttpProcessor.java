package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseInterceptor;

public final class ImmutableHttpProcessor implements HttpProcessor {
    private final HttpRequestInterceptor[] requestInterceptors;
    private final HttpResponseInterceptor[] responseInterceptors;

    public ImmutableHttpProcessor(HttpRequestInterceptor[] httpRequestInterceptorArr, HttpResponseInterceptor[] httpResponseInterceptorArr) {
        if (httpRequestInterceptorArr != null) {
            int $i0 = httpRequestInterceptorArr.length;
            this.requestInterceptors = new HttpRequestInterceptor[$i0];
            System.arraycopy(httpRequestInterceptorArr, 0, this.requestInterceptors, 0, $i0);
        } else {
            this.requestInterceptors = new HttpRequestInterceptor[0];
        }
        if (httpResponseInterceptorArr != null) {
            int $i02 = httpResponseInterceptorArr.length;
            this.responseInterceptors = new HttpResponseInterceptor[$i02];
            System.arraycopy(httpResponseInterceptorArr, 0, this.responseInterceptors, 0, $i02);
            return;
        }
        this.responseInterceptors = new HttpResponseInterceptor[0];
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        for (HttpRequestInterceptor $r4 : this.requestInterceptors) {
            $r4.process(httpRequest, httpContext);
        }
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        for (HttpResponseInterceptor $r4 : this.responseInterceptors) {
            $r4.process(httpResponse, httpContext);
        }
    }
}
