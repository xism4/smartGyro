package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.client.HttpRequestRetryHandler;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.client.cache.HttpClientContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.SSLException;

public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
    public static final DefaultHttpRequestRetryHandler INSTANCE = new DefaultHttpRequestRetryHandler();
    private final Set<Class<? extends IOException>> nonRetriableClasses;
    private final boolean requestSentRetryEnabled;
    private final int retryCount;

    public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }

    public DefaultHttpRequestRetryHandler(int i, boolean z) {
        this(i, z, Arrays.asList(new Class[]{InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class}));
    }

    protected DefaultHttpRequestRetryHandler(int i, boolean z, Collection collection) {
        this.retryCount = i;
        this.requestSentRetryEnabled = z;
        this.nonRetriableClasses = new HashSet();
        Iterator $r3 = collection.iterator();
        while ($r3.hasNext()) {
            this.nonRetriableClasses.add((Class) $r3.next());
        }
    }

    /* access modifiers changed from: protected */
    public boolean handleAsIdempotent(HttpRequest httpRequest) {
        return !(httpRequest instanceof HttpEntityEnclosingRequest);
    }

    /* access modifiers changed from: protected */
    public boolean requestIsAborted(HttpRequest $r1) {
        if ($r1 instanceof RequestWrapper) {
            $r1 = ((RequestWrapper) $r1).getOriginal();
        }
        return ($r1 instanceof HttpUriRequest) && ((HttpUriRequest) $r1).isAborted();
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        Args.notNull(iOException, "Exception parameter");
        Args.notNull(httpContext, "HTTP context");
        if (i > this.retryCount || this.nonRetriableClasses.contains(iOException.getClass())) {
            return false;
        }
        for (Class<? extends IOException> $r4 : this.nonRetriableClasses) {
            if ($r4.isInstance(iOException)) {
                return false;
            }
        }
        HttpClientContext $r7 = HttpClientContext.adapt(httpContext);
        HttpRequest $r8 = $r7.getRequest();
        if (requestIsAborted($r8)) {
            return false;
        }
        return handleAsIdempotent($r8) || !$r7.isRequestSent() || this.requestSentRetryEnabled;
    }
}
