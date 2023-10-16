package cz.msebera.android.http.execchain;

import c.a.a.a.s;
import c.a.a.a.v;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseInterceptor;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class BasicHttpProcessor implements HttpProcessor, HttpResponseInterceptor, HttpClientConnectionManager, Cloneable {
    protected final List<s> requestInterceptors = new ArrayList();
    protected final List<v> responseInterceptors = new ArrayList();

    public final void addInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        addRequestInterceptor(httpRequestInterceptor);
    }

    public final void addInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i) {
        addRequestInterceptor(httpRequestInterceptor, i);
    }

    public final void addInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        addResponseInterceptor(httpResponseInterceptor);
    }

    public void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            this.requestInterceptors.add(httpRequestInterceptor);
        }
    }

    public void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i) {
        if (httpRequestInterceptor != null) {
            this.requestInterceptors.add(i, httpRequestInterceptor);
        }
    }

    public void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            this.responseInterceptors.add(httpResponseInterceptor);
        }
    }

    public Object clone() {
        BasicHttpProcessor $r2 = (BasicHttpProcessor) super.clone();
        copyInterceptors($r2);
        return $r2;
    }

    /* access modifiers changed from: protected */
    public void copyInterceptors(BasicHttpProcessor basicHttpProcessor) {
        basicHttpProcessor.requestInterceptors.clear();
        basicHttpProcessor.requestInterceptors.addAll(this.requestInterceptors);
        basicHttpProcessor.responseInterceptors.clear();
        basicHttpProcessor.responseInterceptors.addAll(this.responseInterceptors);
    }

    public HttpRequestInterceptor getRequestInterceptor(int i) {
        if (i < 0 || i >= this.requestInterceptors.size()) {
            return null;
        }
        return this.requestInterceptors.get(i);
    }

    public int getRequestInterceptorCount() {
        return this.requestInterceptors.size();
    }

    public HttpResponseInterceptor getResponseInterceptor(int i) {
        if (i < 0 || i >= this.responseInterceptors.size()) {
            return null;
        }
        return this.responseInterceptors.get(i);
    }

    public int getResponseInterceptorCount() {
        return this.responseInterceptors.size();
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        for (HttpRequestInterceptor $r6 : this.requestInterceptors) {
            $r6.process(httpRequest, httpContext);
        }
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        for (HttpResponseInterceptor $r6 : this.responseInterceptors) {
            $r6.process(httpResponse, httpContext);
        }
    }
}
