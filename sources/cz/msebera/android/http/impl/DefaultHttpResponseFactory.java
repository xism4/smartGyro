package cz.msebera.android.http.impl;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseFactory;
import cz.msebera.android.http.ReasonPhraseCatalog;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BasicHttpResponse;
import cz.msebera.android.http.mime.Args;
import java.util.Locale;

public class DefaultHttpResponseFactory implements HttpResponseFactory {
    public static final DefaultHttpResponseFactory INSTANCE = new DefaultHttpResponseFactory();
    protected final ReasonPhraseCatalog reasonCatalog;

    public DefaultHttpResponseFactory() {
        this(EnglishReasonPhraseCatalog.INSTANCE);
    }

    public DefaultHttpResponseFactory(ReasonPhraseCatalog $r1) {
        Args.notNull($r1, "Reason phrase catalog");
        this.reasonCatalog = $r1;
    }

    /* access modifiers changed from: protected */
    public Locale determineLocale(HttpContext httpContext) {
        return Locale.getDefault();
    }

    public HttpResponse newHttpResponse(StatusLine statusLine, HttpContext httpContext) {
        Args.notNull(statusLine, "Status line");
        return new BasicHttpResponse(statusLine, this.reasonCatalog, determineLocale(httpContext));
    }
}
