package cz.msebera.android.http.impl;

import c.a.a.a.j.c;
import c.a.a.a.j.d;
import c.a.a.a.r;
import c.a.a.a.t;
import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseFactory;
import cz.msebera.android.http.impl.entity.EntityDeserializer;
import cz.msebera.android.http.impl.entity.EntitySerializer;
import cz.msebera.android.http.impl.entity.LaxContentLengthStrategy;
import cz.msebera.android.http.impl.entity.StrictContentLengthStrategy;
import cz.msebera.android.http.impl.io.HttpRequestWriter;
import cz.msebera.android.http.io.EofSensor;
import cz.msebera.android.http.io.HttpMessageParser;
import cz.msebera.android.http.io.HttpMessageWriter;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.message.LineFormatter;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.net.SocketTimeoutException;

@Deprecated
public abstract class AbstractHttpClientConnection implements HttpClientConnection {
    private final EntityDeserializer entitydeserializer = createEntityDeserializer();
    private final EntitySerializer entityserializer = createEntitySerializer();
    private EofSensor eofSensor = null;
    private SessionInputBuffer inbuffer = null;
    private HttpConnectionMetricsImpl metrics = null;
    private SessionOutputBuffer outbuffer = null;
    private d<r> requestWriter = null;
    private c<t> responseParser = null;

    /* access modifiers changed from: protected */
    public abstract void assertOpen();

    /* access modifiers changed from: protected */
    public HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        return new HttpConnectionMetricsImpl(httpTransportMetrics, httpTransportMetrics2);
    }

    /* access modifiers changed from: protected */
    public EntityDeserializer createEntityDeserializer() {
        return new EntityDeserializer(new LaxContentLengthStrategy());
    }

    /* access modifiers changed from: protected */
    public EntitySerializer createEntitySerializer() {
        return new EntitySerializer(new StrictContentLengthStrategy());
    }

    /* access modifiers changed from: protected */
    public HttpResponseFactory createHttpResponseFactory() {
        return DefaultHttpResponseFactory.INSTANCE;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [cz.msebera.android.http.impl.io.HttpRequestWriter, cz.msebera.android.http.io.HttpMessageWriter] */
    /* access modifiers changed from: protected */
    public HttpMessageWriter createRequestWriter(SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        return new HttpRequestWriter(sessionOutputBuffer, (LineFormatter) null, httpParams);
    }

    /* access modifiers changed from: protected */
    public abstract HttpMessageParser createResponseParser(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams);

    /* access modifiers changed from: protected */
    public void doFlush() {
        this.outbuffer.flush();
    }

    public void flush() {
        assertOpen();
        doFlush();
    }

    /* access modifiers changed from: protected */
    public void init(SessionInputBuffer $r4, SessionOutputBuffer $r5, HttpParams httpParams) {
        Args.notNull($r4, "Input session buffer");
        this.inbuffer = $r4;
        Args.notNull($r5, "Output session buffer");
        this.outbuffer = $r5;
        if ($r4 instanceof EofSensor) {
            this.eofSensor = (EofSensor) $r4;
        }
        this.responseParser = createResponseParser($r4, createHttpResponseFactory(), httpParams);
        this.requestWriter = createRequestWriter($r5, httpParams);
        this.metrics = createConnectionMetrics($r4.getMetrics(), $r5.getMetrics());
    }

    /* access modifiers changed from: protected */
    public boolean isEof() {
        EofSensor $r1 = this.eofSensor;
        return $r1 != null && $r1.isEof();
    }

    public boolean isResponseAvailable(int i) {
        assertOpen();
        try {
            return this.inbuffer.isDataAvailable(i);
        } catch (SocketTimeoutException e) {
            return false;
        }
    }

    public boolean isStale() {
        if (!isOpen() || isEof()) {
            return true;
        }
        try {
            this.inbuffer.isDataAvailable(1);
            return isEof();
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return true;
        }
    }

    public void receiveResponseEntity(HttpResponse httpResponse) {
        Args.notNull(httpResponse, "HTTP response");
        assertOpen();
        httpResponse.setEntity(this.entitydeserializer.deserialize(this.inbuffer, httpResponse));
    }

    public HttpResponse receiveResponseHeader() {
        assertOpen();
        HttpResponse $r3 = (HttpResponse) this.responseParser.parse();
        if ($r3.getStatusLine().getStatusCode() >= 200) {
            this.metrics.incrementResponseCount();
        }
        return $r3;
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        Args.notNull(httpEntityEnclosingRequest, "HTTP request");
        assertOpen();
        if (httpEntityEnclosingRequest.getEntity() != null) {
            this.entityserializer.serialize(this.outbuffer, httpEntityEnclosingRequest, httpEntityEnclosingRequest.getEntity());
        }
    }

    public void sendRequestHeader(HttpRequest httpRequest) {
        Args.notNull(httpRequest, "HTTP request");
        assertOpen();
        this.requestWriter.write(httpRequest);
        this.metrics.incrementRequestCount();
    }
}
