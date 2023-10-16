package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.client.auth.CloseableHttpResponse;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.impl.client.AbstractHttpClient;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AsyncHttpRequest implements Runnable {
    private boolean cancelIsNotified;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private int executionCount;
    private final AtomicBoolean isCancelled = new AtomicBoolean();
    private volatile boolean isFinished;
    private boolean isRequestPreProcessed;
    private final HttpUriRequest request;
    private final ResponseHandlerInterface responseHandler;

    public AsyncHttpRequest(AbstractHttpClient $r1, HttpContext $r2, HttpUriRequest $r3, ResponseHandlerInterface $r4) {
        Utils.notNull($r1, "client");
        this.client = $r1;
        Utils.notNull($r2, "context");
        this.context = $r2;
        Utils.notNull($r3, "request");
        this.request = $r3;
        Utils.notNull($r4, "responseHandler");
        this.responseHandler = $r4;
    }

    private void makeRequest() {
        if (!isCancelled()) {
            if (this.request.getURI().getScheme() != null) {
                ResponseHandlerInterface $r5 = this.responseHandler;
                if ($r5 instanceof RangeFileAsyncHttpResponseHandler) {
                    ((RangeFileAsyncHttpResponseHandler) $r5).updateRequestHeaders(this.request);
                }
                CloseableHttpResponse $r8 = this.client.execute(this.request, this.context);
                if (!isCancelled()) {
                    ResponseHandlerInterface $r52 = this.responseHandler;
                    $r52.onPreProcessResponse($r52, $r8);
                    if (!isCancelled()) {
                        this.responseHandler.sendResponseMessage($r8);
                        if (!isCancelled()) {
                            ResponseHandlerInterface $r53 = this.responseHandler;
                            $r53.onPostProcessResponse($r53, $r8);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new MalformedURLException("No valid URI scheme was provided");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x000a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void makeRequestWithRetries() {
        /*
            r21 = this;
            r0 = r21
            cz.msebera.android.http.impl.client.AbstractHttpClient r2 = r0.client
            cz.msebera.android.http.client.HttpRequestRetryHandler r3 = r2.getHttpRequestRetryHandler()
            r4 = 0
            r5 = 1
        L_0x000a:
            if (r5 == 0) goto L_0x00e2
            r0 = r21
            r0.makeRequest()     // Catch:{ UnknownHostException -> 0x0063, NullPointerException -> 0x0033, IOException -> 0x0016 }
            return
        L_0x0012:
            r6 = move-exception
            goto L_0x00b1
        L_0x0016:
            r4 = move-exception
            r0 = r21
            boolean r5 = r0.isCancelled()     // Catch:{ Exception -> 0x0012 }
            if (r5 == 0) goto L_0x0020
            return
        L_0x0020:
            r0 = r21
            int r7 = r0.executionCount
            int r7 = r7 + 1
            r0 = r21
            r0.executionCount = r7
            r0 = r21
            cz.msebera.android.http.execchain.HttpContext r8 = r0.context
            boolean r5 = r3.retryRequest(r4, r7, r8)     // Catch:{ Exception -> 0x0012 }
            goto L_0x009d
        L_0x0033:
            r9 = move-exception
            java.io.IOException r10 = new java.io.IOException
            r4 = r10
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r12 = r11
            r11.<init>()     // Catch:{ Exception -> 0x0012 }
            java.lang.String r13 = "NPE in HttpClient: "
            r12.append(r13)     // Catch:{ Exception -> 0x0012 }
            java.lang.String r14 = r9.getMessage()     // Catch:{ Exception -> 0x0012 }
            r12.append(r14)     // Catch:{ Exception -> 0x0012 }
            java.lang.String r14 = r12.toString()     // Catch:{ Exception -> 0x0012 }
            r10.<init>(r14)     // Catch:{ Exception -> 0x0012 }
            r0 = r21
            int r7 = r0.executionCount
            int r7 = r7 + 1
            r0 = r21
            r0.executionCount = r7
            r0 = r21
            cz.msebera.android.http.execchain.HttpContext r8 = r0.context
            boolean r5 = r3.retryRequest(r4, r7, r8)     // Catch:{ Exception -> 0x0012 }
            goto L_0x009d
        L_0x0063:
            r15 = move-exception
            java.io.IOException r10 = new java.io.IOException
            r4 = r10
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r12 = r11
            r11.<init>()     // Catch:{ Exception -> 0x0012 }
            java.lang.String r13 = "UnknownHostException exception: "
            r12.append(r13)     // Catch:{ Exception -> 0x0012 }
            java.lang.String r14 = r15.getMessage()     // Catch:{ Exception -> 0x0012 }
            r12.append(r14)     // Catch:{ Exception -> 0x0012 }
            java.lang.String r14 = r12.toString()     // Catch:{ Exception -> 0x0012 }
            r10.<init>(r14)     // Catch:{ Exception -> 0x0012 }
            r0 = r21
            int r7 = r0.executionCount
            if (r7 <= 0) goto L_0x009c
            r0 = r21
            int r7 = r0.executionCount
            int r7 = r7 + 1
            r0 = r21
            r0.executionCount = r7
            r0 = r21
            cz.msebera.android.http.execchain.HttpContext r8 = r0.context
            boolean r5 = r3.retryRequest(r15, r7, r8)     // Catch:{ Exception -> 0x0012 }
            if (r5 == 0) goto L_0x009c
            r5 = 1
            goto L_0x009d
        L_0x009c:
            r5 = 0
        L_0x009d:
            if (r5 == 0) goto L_0x000a
            r0 = r21
            lombok.eclipse.handlers.http.ResponseHandlerInterface r0 = r0.responseHandler
            r16 = r0
            r0 = r21
            int r7 = r0.executionCount
            r0 = r16
            r0.sendRetryMessage(r7)     // Catch:{ Exception -> 0x0012 }
            goto L_0x000a
        L_0x00b1:
            lombok.eclipse.handlers.http.LogInterface r17 = lombok.eclipse.handlers.http.AsyncHttpClient.log
            java.lang.String r13 = "AsyncHttpRequest"
            java.lang.String r18 = "Unhandled exception origin cause"
            r0 = r17
            r1 = r18
            r0.e(r13, r1, r6)
            java.io.IOException r10 = new java.io.IOException
            r4 = r10
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r12 = r11
            r11.<init>()
            java.lang.String r13 = "Unhandled exception: "
            r12.append(r13)
            r20 = r6
            java.lang.Exception r20 = (java.lang.Exception) r20
            r19 = r20
            r0 = r19
            java.lang.String r14 = r0.getMessage()
            r12.append(r14)
            java.lang.String r14 = r12.toString()
            r10.<init>(r14)
        L_0x00e2:
            goto L_0x00e3
        L_0x00e3:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.eclipse.handlers.http.AsyncHttpRequest.makeRequestWithRetries():void");
    }

    private synchronized void sendCancelNotification() {
        if (!this.isFinished && this.isCancelled.get() && !this.cancelIsNotified) {
            this.cancelIsNotified = true;
            this.responseHandler.sendCancelMessage();
        }
    }

    public boolean cancel(boolean z) {
        this.isCancelled.set(true);
        this.request.abort();
        return isCancelled();
    }

    public boolean isCancelled() {
        boolean $z0 = this.isCancelled.get();
        if ($z0) {
            sendCancelNotification();
        }
        return $z0;
    }

    public boolean isDone() {
        return isCancelled() || this.isFinished;
    }

    public void onPostProcessRequest(AsyncHttpRequest asyncHttpRequest) {
    }

    public void onPreProcessRequest(AsyncHttpRequest asyncHttpRequest) {
    }

    public void run() {
        if (!isCancelled()) {
            if (!this.isRequestPreProcessed) {
                this.isRequestPreProcessed = true;
                onPostProcessRequest(this);
            }
            if (!isCancelled()) {
                this.responseHandler.sendStartMessage();
                if (!isCancelled()) {
                    try {
                        makeRequestWithRetries();
                    } catch (IOException $r2) {
                        if (!isCancelled()) {
                            this.responseHandler.sendFailureMessage(0, (Header[]) null, (byte[]) null, $r2);
                        } else {
                            AsyncHttpClient.log.e("AsyncHttpRequest", "makeRequestWithRetries returned error", $r2);
                        }
                    }
                    if (!isCancelled()) {
                        this.responseHandler.sendFinishMessage();
                        if (!isCancelled()) {
                            onPreProcessRequest(this);
                            this.isFinished = true;
                        }
                    }
                }
            }
        }
    }
}
