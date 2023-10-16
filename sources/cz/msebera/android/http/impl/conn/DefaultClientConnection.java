package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.g;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseFactory;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ManagedHttpClientConnection;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.impl.SocketHttpClientConnection;
import cz.msebera.android.http.io.HttpMessageParser;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.message.LineParser;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import cz.msebera.android.http.util.HttpProtocolParams;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public class DefaultClientConnection extends SocketHttpClientConnection implements OperatedClientConnection, ManagedHttpClientConnection, HttpContext {
    private final Map<String, Object> attributes = new HashMap();
    private boolean connSecure;
    public HttpClientAndroidLog headerLog = new HttpClientAndroidLog("cz.msebera.android.httpclient.headers");
    public HttpClientAndroidLog log = new HttpClientAndroidLog(g.class);
    private volatile boolean shutdown;
    private volatile Socket socket;
    private HttpHost targetHost;
    public HttpClientAndroidLog wireLog = new HttpClientAndroidLog("cz.msebera.android.httpclient.wire");

    public void close() {
        try {
            super.close();
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r1 = this.log;
                $r1.debug("Connection " + this + " closed");
            }
        } catch (IOException $r4) {
            this.log.debug("I/O error closing connection", $r4);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [cz.msebera.android.http.impl.conn.DefaultHttpResponseParser, cz.msebera.android.http.io.HttpMessageParser] */
    /* access modifiers changed from: protected */
    public HttpMessageParser createResponseParser(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        return new DefaultHttpResponseParser(sessionInputBuffer, (LineParser) null, httpResponseFactory, httpParams);
    }

    /* access modifiers changed from: protected */
    public SessionInputBuffer createSessionInputBuffer(Socket socket2, int $i0, HttpParams httpParams) {
        if ($i0 <= 0) {
            $i0 = 8192;
        }
        SessionInputBuffer $r5 = super.createSessionInputBuffer(socket2, $i0, httpParams);
        return this.wireLog.isDebugEnabled() ? new LoggingSessionInputBuffer($r5, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(httpParams)) : $r5;
    }

    /* access modifiers changed from: protected */
    public SessionOutputBuffer createSessionOutputBuffer(Socket socket2, int $i0, HttpParams httpParams) {
        if ($i0 <= 0) {
            $i0 = 8192;
        }
        SessionOutputBuffer $r5 = super.createSessionOutputBuffer(socket2, $i0, httpParams);
        return this.wireLog.isDebugEnabled() ? new LoggingSessionOutputBuffer($r5, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(httpParams)) : $r5;
    }

    public Object getAttribute(String str) {
        return this.attributes.get(str);
    }

    public SSLSession getSSLSession() {
        if (this.socket instanceof SSLSocket) {
            return ((SSLSocket) this.socket).getSession();
        }
        return null;
    }

    public final Socket getSocket() {
        return this.socket;
    }

    public final boolean isSecure() {
        return this.connSecure;
    }

    public void openCompleted(boolean z, HttpParams httpParams) {
        Args.notNull(httpParams, "Parameters");
        assertNotOpen();
        this.connSecure = z;
        bind(this.socket, httpParams);
    }

    public void opening(Socket socket2, HttpHost httpHost) {
        assertNotOpen();
        this.socket = socket2;
        this.targetHost = httpHost;
        if (this.shutdown) {
            socket2.close();
            throw new InterruptedIOException("Connection already shutdown");
        }
    }

    public HttpResponse receiveResponseHeader() {
        HttpResponse $r3 = super.receiveResponseHeader();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Receiving response: " + $r3.getStatusLine());
        }
        if (this.headerLog.isDebugEnabled()) {
            this.headerLog.debug("<< " + $r3.getStatusLine().toString());
            for (Header $r7 : $r3.getAllHeaders()) {
                this.headerLog.debug("<< " + $r7.toString());
            }
        }
        return $r3;
    }

    public void sendRequestHeader(HttpRequest httpRequest) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Sending request: " + httpRequest.getRequestLine());
        }
        super.sendRequestHeader(httpRequest);
        if (this.headerLog.isDebugEnabled()) {
            this.headerLog.debug(">> " + httpRequest.getRequestLine().toString());
            for (Header $r7 : httpRequest.getAllHeaders()) {
                this.headerLog.debug(">> " + $r7.toString());
            }
        }
    }

    public void setAttribute(String str, Object obj) {
        this.attributes.put(str, obj);
    }

    public void shutdown() {
        this.shutdown = true;
        try {
            super.shutdown();
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r1 = this.log;
                $r1.debug("Connection " + this + " shut down");
            }
            Socket $r4 = this.socket;
            if ($r4 != null) {
                $r4.close();
            }
        } catch (IOException $r5) {
            this.log.debug("I/O error shutting down connection", $r5);
        }
    }

    public void update(Socket socket2, HttpHost httpHost, boolean z, HttpParams httpParams) {
        assertOpen();
        Args.notNull(httpHost, "Target host");
        Args.notNull(httpParams, "Parameters");
        if (socket2 != null) {
            this.socket = socket2;
            bind(socket2, httpParams);
        }
        this.targetHost = httpHost;
        this.connSecure = z;
    }
}
