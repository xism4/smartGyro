package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;

public class HttpRequestExecutor {
    private final int waitForContinue;

    public HttpRequestExecutor() {
        this(3000);
    }

    public HttpRequestExecutor(int i) {
        Args.positive(i, "Wait for continue time");
        this.waitForContinue = i;
    }

    private static void closeConnection(HttpClientConnection httpClientConnection) {
        try {
            httpClientConnection.close();
        } catch (IOException e) {
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
        r6 = r9.getStatusLine().getStatusCode();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canResponseHaveBody(cz.msebera.android.http.HttpRequest r8, cz.msebera.android.http.HttpResponse r9) {
        /*
            r7 = this;
            cz.msebera.android.http.RequestLine r0 = r8.getRequestLine()
            java.lang.String r1 = r0.getMethod()
            java.lang.String r2 = "HEAD"
            boolean r3 = r2.equalsIgnoreCase(r1)
            if (r3 == 0) goto L_0x0012
            r4 = 0
            return r4
        L_0x0012:
            cz.msebera.android.http.StatusLine r5 = r9.getStatusLine()
            int r6 = r5.getStatusCode()
            r4 = 200(0xc8, float:2.8E-43)
            if (r6 < r4) goto L_0x002c
            r4 = 204(0xcc, float:2.86E-43)
            if (r6 == r4) goto L_0x002c
            r4 = 304(0x130, float:4.26E-43)
            if (r6 == r4) goto L_0x002c
            r4 = 205(0xcd, float:2.87E-43)
            if (r6 == r4) goto L_0x002c
            r4 = 1
            return r4
        L_0x002c:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.execchain.HttpRequestExecutor.canResponseHaveBody(cz.msebera.android.http.HttpRequest, cz.msebera.android.http.HttpResponse):boolean");
    }

    /* access modifiers changed from: protected */
    public HttpResponse doReceiveResponse(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpClientConnection, "Client connection");
        Args.notNull(httpContext, "HTTP context");
        HttpResponse $r4 = null;
        int $i0 = 0;
        while (true) {
            if ($r4 != null && $i0 >= 200) {
                return $r4;
            }
            HttpResponse $r5 = httpClientConnection.receiveResponseHeader();
            $r4 = $r5;
            if (canResponseHaveBody(httpRequest, $r5)) {
                httpClientConnection.receiveResponseEntity($r5);
            }
            $i0 = $r5.getStatusLine().getStatusCode();
        }
    }

    /* access modifiers changed from: protected */
    public HttpResponse doSendRequest(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpClientConnection, "Client connection");
        Args.notNull(httpContext, "HTTP context");
        httpContext.setAttribute("http.connection", httpClientConnection);
        httpContext.setAttribute("http.request_sent", Boolean.FALSE);
        httpClientConnection.sendRequestHeader(httpRequest);
        HttpResponse $r5 = null;
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            boolean $z0 = true;
            ProtocolVersion $r7 = httpRequest.getRequestLine().getProtocolVersion();
            HttpEntityEnclosingRequest $r8 = (HttpEntityEnclosingRequest) httpRequest;
            if ($r8.expectContinue() && !$r7.lessEquals(HttpVersion.HTTP_1_0)) {
                httpClientConnection.flush();
                if (httpClientConnection.isResponseAvailable(this.waitForContinue)) {
                    HttpResponse $r10 = httpClientConnection.receiveResponseHeader();
                    if (canResponseHaveBody(httpRequest, $r10)) {
                        httpClientConnection.receiveResponseEntity($r10);
                    }
                    int $i0 = $r10.getStatusLine().getStatusCode();
                    if ($i0 >= 200) {
                        $z0 = false;
                        $r5 = $r10;
                    } else if ($i0 != 100) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unexpected response: ");
                        sb.append($r10.getStatusLine());
                        throw new ProtocolException(sb.toString());
                    }
                }
            }
            if ($z0) {
                httpClientConnection.sendRequestEntity($r8);
            }
        }
        httpClientConnection.flush();
        httpContext.setAttribute("http.request_sent", Boolean.TRUE);
        return $r5;
    }

    public HttpResponse execute(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpClientConnection, "Client connection");
        Args.notNull(httpContext, "HTTP context");
        try {
            HttpResponse $r4 = doSendRequest(httpRequest, httpClientConnection, httpContext);
            return $r4 == null ? doReceiveResponse(httpRequest, httpClientConnection, httpContext) : $r4;
        } catch (IOException $r7) {
            closeConnection(httpClientConnection);
            throw $r7;
        } catch (HttpException $r6) {
            closeConnection(httpClientConnection);
            throw $r6;
        } catch (RuntimeException $r5) {
            closeConnection(httpClientConnection);
            throw $r5;
        }
    }

    public void postProcess(HttpResponse httpResponse, HttpProcessor httpProcessor, HttpContext httpContext) {
        Args.notNull(httpResponse, "HTTP response");
        Args.notNull(httpProcessor, "HTTP processor");
        Args.notNull(httpContext, "HTTP context");
        httpContext.setAttribute("http.response", httpResponse);
        httpProcessor.process(httpResponse, httpContext);
    }

    public void preProcess(HttpRequest httpRequest, HttpProcessor httpProcessor, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpProcessor, "HTTP processor");
        Args.notNull(httpContext, "HTTP context");
        httpContext.setAttribute("http.request", httpRequest);
        httpProcessor.process(httpRequest, httpContext);
    }
}
