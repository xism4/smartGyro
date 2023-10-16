package lombok.eclipse.handlers.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.client.HttpResponseException;
import java.lang.ref.WeakReference;
import java.net.URI;

public abstract class AsyncHttpResponseHandler implements ResponseHandlerInterface {
    private WeakReference<Object> TAG;
    private Handler handler;
    private Looper looper;
    private Header[] requestHeaders;
    private URI requestURI;
    private String responseCharset;
    private boolean usePoolThread;
    private boolean useSynchronousMode;

    class ResponderHandler extends Handler {
        private final AsyncHttpResponseHandler mResponder;

        ResponderHandler(AsyncHttpResponseHandler asyncHttpResponseHandler, Looper looper) {
            super(looper);
            this.mResponder = asyncHttpResponseHandler;
        }

        public void handleMessage(Message message) {
            this.mResponder.handleMessage(message);
        }
    }

    public AsyncHttpResponseHandler() {
        this((Looper) null);
    }

    public AsyncHttpResponseHandler(Looper $r2) {
        this.responseCharset = "UTF-8";
        this.requestURI = null;
        this.requestHeaders = null;
        this.looper = null;
        this.TAG = new WeakReference((Object) null);
        this.looper = $r2 == null ? Looper.myLooper() : $r2;
        setUseSynchronousMode(false);
        setUsePoolThread(false);
    }

    public AsyncHttpResponseHandler(boolean z) {
        this.responseCharset = "UTF-8";
        this.requestURI = null;
        this.requestHeaders = null;
        this.looper = null;
        this.TAG = new WeakReference((Object) null);
        setUsePoolThread(z);
        if (!getUsePoolThread()) {
            this.looper = Looper.myLooper();
            setUseSynchronousMode(false);
        }
    }

    public abstract void close(int i, Header[] headerArr, byte[] bArr, Throwable th);

    public URI getRequestURI() {
        return this.requestURI;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        lombok.eclipse.handlers.http.AsyncHttpClient.silentCloseInputStream(r4);
        lombok.eclipse.handlers.http.AsyncHttpClient.endEntityViaReflection(r28);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0066, code lost:
        return r11.toByteArray();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getResponseData(cz.msebera.android.http.HttpEntity r28) {
        /*
            r27 = this;
            if (r28 == 0) goto L_0x008c
            r0 = r28
            java.io.InputStream r4 = r0.getContent()
            if (r4 == 0) goto L_0x008c
            r0 = r28
            long r5 = r0.getContentLength()
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 > 0) goto L_0x0080
            r8 = 0
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 > 0) goto L_0x0020
            r10 = 4096(0x1000, float:5.74E-42)
            goto L_0x0021
        L_0x0020:
            int r10 = (int) r5
        L_0x0021:
            cz.msebera.android.http.mime.ByteArrayBuffer r11 = new cz.msebera.android.http.mime.ByteArrayBuffer     // Catch:{ OutOfMemoryError -> 0x008f }
            r11.<init>(r10)     // Catch:{ OutOfMemoryError -> 0x008f }
            r13 = 4096(0x1000, float:5.74E-42)
            byte[] r12 = new byte[r13]     // Catch:{ Throwable -> 0x0067 }
            r14 = 0
        L_0x002c:
            int r10 = r4.read(r12)     // Catch:{ Throwable -> 0x0067 }
            r13 = -1
            if (r10 == r13) goto L_0x005a
            java.lang.Thread r16 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x0067 }
            r0 = r16
            boolean r17 = r0.isInterrupted()     // Catch:{ Throwable -> 0x0067 }
            if (r17 != 0) goto L_0x005a
            long r0 = (long) r10     // Catch:{ Throwable -> 0x0067 }
            r18 = r0
            long r14 = r14 + r0
            r13 = 0
            r11.append((byte[]) r12, (int) r13, (int) r10)     // Catch:{ Throwable -> 0x0067 }
            r8 = 0
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 > 0) goto L_0x0050
            r18 = 1
            goto L_0x0052
        L_0x0050:
            r18 = r5
        L_0x0052:
            r0 = r27
            r1 = r18
            r0.sendProgressMessage(r14, r1)     // Catch:{ Throwable -> 0x0067 }
            goto L_0x002c
        L_0x005a:
            lombok.eclipse.handlers.http.AsyncHttpClient.silentCloseInputStream(r4)     // Catch:{ OutOfMemoryError -> 0x0091 }
            r0 = r28
            lombok.eclipse.handlers.http.AsyncHttpClient.endEntityViaReflection(r0)     // Catch:{ OutOfMemoryError -> 0x0091 }
            byte[] r12 = r11.toByteArray()     // Catch:{ OutOfMemoryError -> 0x0091 }
            return r12
        L_0x0067:
            r20 = move-exception
            lombok.eclipse.handlers.http.AsyncHttpClient.silentCloseInputStream(r4)     // Catch:{ OutOfMemoryError -> 0x0091 }
            r0 = r28
            lombok.eclipse.handlers.http.AsyncHttpClient.endEntityViaReflection(r0)     // Catch:{ OutOfMemoryError -> 0x0091 }
            throw r20     // Catch:{ OutOfMemoryError -> 0x0091 }
        L_0x0071:
            java.lang.System.gc()
            java.io.IOException r21 = new java.io.IOException
            java.lang.String r22 = "File too large to fit into available memory"
            r0 = r21
            r1 = r22
            r0.<init>(r1)
            throw r21
        L_0x0080:
            java.lang.IllegalArgumentException r23 = new java.lang.IllegalArgumentException
            java.lang.String r22 = "HTTP entity too large to be buffered in memory"
            r0 = r23
            r1 = r22
            r0.<init>(r1)
            throw r23
        L_0x008c:
            r24 = 0
            return r24
        L_0x008f:
            r25 = move-exception
            goto L_0x0071
        L_0x0091:
            r26 = move-exception
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.eclipse.handlers.http.AsyncHttpResponseHandler.getResponseData(cz.msebera.android.http.HttpEntity):byte[]");
    }

    public boolean getUsePoolThread() {
        return this.usePoolThread;
    }

    public boolean getUseSynchronousMode() {
        return this.useSynchronousMode;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r14.e("AsyncHttpRH", r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0039, code lost:
        return;
     */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:44:0x0083=Splitter:B:44:0x0083, B:67:0x00d8=Splitter:B:67:0x00d8, B:82:0x0118=Splitter:B:82:0x0118, B:21:0x002e=Splitter:B:21:0x002e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r41) {
        /*
            r40 = this;
            r0 = r41
            int r7 = r0.what     // Catch:{ Throwable -> 0x011f }
            switch(r7) {
                case 0: goto L_0x00df;
                case 1: goto L_0x0094;
                case 2: goto L_0x008e;
                case 3: goto L_0x0088;
                case 4: goto L_0x003a;
                case 5: goto L_0x000f;
                case 6: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0008
        L_0x0008:
            return
        L_0x0009:
            r0 = r40
            r0.onCancel()     // Catch:{ Throwable -> 0x011f }
            return
        L_0x000f:
            r0 = r41
            java.lang.Object r8 = r0.obj     // Catch:{ Throwable -> 0x011f }
            r10 = r8
            java.lang.Object[] r10 = (java.lang.Object[]) r10     // Catch:{ Throwable -> 0x011f }
            r9 = r10
            if (r9 == 0) goto L_0x002e
            int r7 = r9.length     // Catch:{ Throwable -> 0x011f }
            r11 = 1
            if (r7 != r11) goto L_0x002e
            r11 = 0
            r8 = r9[r11]     // Catch:{ Throwable -> 0x011f }
            r13 = r8
            java.lang.Integer r13 = (java.lang.Integer) r13     // Catch:{ Throwable -> 0x011f }
            r12 = r13
            int r7 = r12.intValue()     // Catch:{ Throwable -> 0x011f }
            r0 = r40
            r0.onRetry(r7)     // Catch:{ Throwable -> 0x011f }
            return
        L_0x002e:
            lombok.eclipse.handlers.http.LogInterface r14 = lombok.eclipse.handlers.http.AsyncHttpClient.log     // Catch:{ Throwable -> 0x011f }
            java.lang.String r15 = "RETRY_MESSAGE didn't get enough params"
        L_0x0032:
            java.lang.String r16 = "AsyncHttpRH"
            r0 = r16
            r14.e(r0, r15)     // Catch:{ Throwable -> 0x011f }
            return
        L_0x003a:
            r0 = r41
            java.lang.Object r8 = r0.obj     // Catch:{ Throwable -> 0x011f }
            r17 = r8
            java.lang.Object[] r17 = (java.lang.Object[]) r17     // Catch:{ Throwable -> 0x011f }
            r9 = r17
            if (r9 == 0) goto L_0x0083
            int r7 = r9.length     // Catch:{ Throwable -> 0x011f }
            r11 = 2
            if (r7 < r11) goto L_0x0083
            r11 = 0
            r8 = r9[r11]     // Catch:{ Throwable -> 0x0072 }
            r19 = r8
            java.lang.Long r19 = (java.lang.Long) r19     // Catch:{ Throwable -> 0x0072 }
            r18 = r19
            r0 = r18
            long r20 = r0.longValue()     // Catch:{ Throwable -> 0x0072 }
            r11 = 1
            r8 = r9[r11]     // Catch:{ Throwable -> 0x0072 }
            r22 = r8
            java.lang.Long r22 = (java.lang.Long) r22     // Catch:{ Throwable -> 0x0072 }
            r18 = r22
            r0 = r18
            long r23 = r0.longValue()     // Catch:{ Throwable -> 0x0072 }
            r0 = r40
            r1 = r20
            r3 = r23
            r0.write(r1, r3)     // Catch:{ Throwable -> 0x0072 }
            return
        L_0x0072:
            r25 = move-exception
            lombok.eclipse.handlers.http.LogInterface r14 = lombok.eclipse.handlers.http.AsyncHttpClient.log     // Catch:{ Throwable -> 0x011f }
            java.lang.String r16 = "AsyncHttpRH"
            java.lang.String r26 = "custom onProgress contains an error"
            r0 = r16
            r1 = r26
            r2 = r25
            r14.e(r0, r1, r2)     // Catch:{ Throwable -> 0x011f }
            return
        L_0x0083:
            lombok.eclipse.handlers.http.LogInterface r14 = lombok.eclipse.handlers.http.AsyncHttpClient.log     // Catch:{ Throwable -> 0x011f }
            java.lang.String r15 = "PROGRESS_MESSAGE didn't got enough params"
            goto L_0x0032
        L_0x0088:
            r0 = r40
            r0.read()     // Catch:{ Throwable -> 0x011f }
            return
        L_0x008e:
            r0 = r40
            r0.write()     // Catch:{ Throwable -> 0x011f }
            return
        L_0x0094:
            r0 = r41
            java.lang.Object r8 = r0.obj     // Catch:{ Throwable -> 0x011f }
            r27 = r8
            java.lang.Object[] r27 = (java.lang.Object[]) r27     // Catch:{ Throwable -> 0x011f }
            r9 = r27
            if (r9 == 0) goto L_0x00d8
            int r7 = r9.length     // Catch:{ Throwable -> 0x011f }
            r11 = 4
            if (r7 < r11) goto L_0x00d8
            r11 = 0
            r8 = r9[r11]     // Catch:{ Throwable -> 0x011f }
            r28 = r8
            java.lang.Integer r28 = (java.lang.Integer) r28     // Catch:{ Throwable -> 0x011f }
            r12 = r28
            int r7 = r12.intValue()     // Catch:{ Throwable -> 0x011f }
            r11 = 1
            r8 = r9[r11]     // Catch:{ Throwable -> 0x011f }
            r30 = r8
            cz.msebera.android.http.Header[] r30 = (cz.msebera.android.http.Header[]) r30     // Catch:{ Throwable -> 0x011f }
            r29 = r30
            r11 = 2
            r8 = r9[r11]     // Catch:{ Throwable -> 0x011f }
            r32 = r8
            byte[] r32 = (byte[]) r32     // Catch:{ Throwable -> 0x011f }
            r31 = r32
            r11 = 3
            r8 = r9[r11]     // Catch:{ Throwable -> 0x011f }
            r33 = r8
            java.lang.Throwable r33 = (java.lang.Throwable) r33     // Catch:{ Throwable -> 0x011f }
            r25 = r33
            r0 = r40
            r1 = r29
            r2 = r31
            r3 = r25
            r0.close(r7, r1, r2, r3)     // Catch:{ Throwable -> 0x011f }
            return
        L_0x00d8:
            lombok.eclipse.handlers.http.LogInterface r14 = lombok.eclipse.handlers.http.AsyncHttpClient.log     // Catch:{ Throwable -> 0x011f }
            java.lang.String r15 = "FAILURE_MESSAGE didn't got enough params"
            goto L_0x0032
        L_0x00df:
            r0 = r41
            java.lang.Object r8 = r0.obj     // Catch:{ Throwable -> 0x011f }
            r34 = r8
            java.lang.Object[] r34 = (java.lang.Object[]) r34     // Catch:{ Throwable -> 0x011f }
            r9 = r34
            if (r9 == 0) goto L_0x0118
            int r7 = r9.length     // Catch:{ Throwable -> 0x011f }
            r11 = 3
            if (r7 < r11) goto L_0x0118
            r11 = 0
            r8 = r9[r11]     // Catch:{ Throwable -> 0x011f }
            r35 = r8
            java.lang.Integer r35 = (java.lang.Integer) r35     // Catch:{ Throwable -> 0x011f }
            r12 = r35
            int r7 = r12.intValue()     // Catch:{ Throwable -> 0x011f }
            r11 = 1
            r8 = r9[r11]     // Catch:{ Throwable -> 0x011f }
            r36 = r8
            cz.msebera.android.http.Header[] r36 = (cz.msebera.android.http.Header[]) r36     // Catch:{ Throwable -> 0x011f }
            r29 = r36
            r11 = 2
            r8 = r9[r11]     // Catch:{ Throwable -> 0x011f }
            r37 = r8
            byte[] r37 = (byte[]) r37     // Catch:{ Throwable -> 0x011f }
            r31 = r37
            r0 = r40
            r1 = r29
            r2 = r31
            r0.read(r7, r1, r2)     // Catch:{ Throwable -> 0x011f }
            return
        L_0x0118:
            lombok.eclipse.handlers.http.LogInterface r14 = lombok.eclipse.handlers.http.AsyncHttpClient.log     // Catch:{ Throwable -> 0x011f }
            java.lang.String r15 = "SUCCESS_MESSAGE didn't got enough params"
            goto L_0x0032
        L_0x011f:
            r38 = move-exception
            r0 = r40
            r1 = r38
            r0.onUserException(r1)
            goto L_0x0128
        L_0x0128:
            java.lang.NullPointerException r39 = new java.lang.NullPointerException
            java.lang.String r16 = "Null throw statement replaced by Soot"
            r0 = r39
            r1 = r16
            r0.<init>(r1)
            throw r39
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.eclipse.handlers.http.AsyncHttpResponseHandler.handleMessage(android.os.Message):void");
    }

    /* access modifiers changed from: protected */
    public Message obtainMessage(int i, Object obj) {
        return Message.obtain(this.handler, i, obj);
    }

    public void onCancel() {
        AsyncHttpClient.log.d("AsyncHttpRH", "Request got cancelled");
    }

    public void onPostProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse) {
    }

    public void onPreProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse) {
    }

    public void onRetry(int i) {
        AsyncHttpClient.log.d("AsyncHttpRH", String.format("Request retry no. %d", new Object[]{Integer.valueOf(i)}));
    }

    public void onUserException(Throwable th) {
        AsyncHttpClient.log.e("AsyncHttpRH", "User-space exception detected!", th);
        throw new RuntimeException(th);
    }

    public void read() {
    }

    public abstract void read(int i, Header[] headerArr, byte[] bArr);

    public final void sendCancelMessage() {
        sendMessage(obtainMessage(6, (Object) null));
    }

    public final void sendFailureMessage(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        sendMessage(obtainMessage(1, new Object[]{Integer.valueOf(i), headerArr, bArr, th}));
    }

    public final void sendFinishMessage() {
        sendMessage(obtainMessage(3, (Object) null));
    }

    /* access modifiers changed from: protected */
    public void sendMessage(Message message) {
        if (getUseSynchronousMode() || this.handler == null) {
            handleMessage(message);
        } else if (!Thread.currentThread().isInterrupted()) {
            Utils.asserts(this.handler != null, "handler should not be null!");
            this.handler.sendMessage(message);
        }
    }

    public final void sendProgressMessage(long j, long j2) {
        sendMessage(obtainMessage(4, new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
    }

    public void sendResponseMessage(HttpResponse httpResponse) {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine $r3 = httpResponse.getStatusLine();
            byte[] $r5 = getResponseData(httpResponse.getEntity());
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            if ($r3.getStatusCode() >= 300) {
                sendFailureMessage($r3.getStatusCode(), httpResponse.getAllHeaders(), $r5, new HttpResponseException($r3.getStatusCode(), $r3.getReasonPhrase()));
            } else {
                sendSuccessMessage($r3.getStatusCode(), httpResponse.getAllHeaders(), $r5);
            }
        }
    }

    public final void sendRetryMessage(int i) {
        sendMessage(obtainMessage(5, new Object[]{Integer.valueOf(i)}));
    }

    public final void sendStartMessage() {
        sendMessage(obtainMessage(2, (Object) null));
    }

    public final void sendSuccessMessage(int i, Header[] headerArr, byte[] bArr) {
        sendMessage(obtainMessage(0, new Object[]{Integer.valueOf(i), headerArr, bArr}));
    }

    public void setRequestHeaders(Header[] headerArr) {
        this.requestHeaders = headerArr;
    }

    public void setRequestURI(URI uri) {
        this.requestURI = uri;
    }

    public void setUsePoolThread(boolean z) {
        if (z) {
            this.looper = null;
            this.handler = null;
        }
        this.usePoolThread = z;
    }

    public void setUseSynchronousMode(boolean $z0) {
        ResponderHandler $r4;
        if (!$z0 && this.looper == null) {
            $z0 = true;
            AsyncHttpClient.log.w("AsyncHttpRH", "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
        }
        if ($z0 || this.handler != null) {
            if ($z0 && this.handler != null) {
                $r4 = null;
            }
            this.useSynchronousMode = $z0;
        }
        $r4 = new ResponderHandler(this, this.looper);
        this.handler = $r4;
        this.useSynchronousMode = $z0;
    }

    public void write() {
    }

    public void write(long j, long j2) {
        double $d0;
        LogInterface $r1 = AsyncHttpClient.log;
        Object[] $r2 = new Object[3];
        $r2[0] = Long.valueOf(j);
        $r2[1] = Long.valueOf(j2);
        if (j2 > 0) {
            double $d02 = (double) j;
            Double.isNaN($d02);
            double $d1 = (double) j2;
            Double.isNaN($d1);
            $d0 = (($d02 * 1.0d) / $d1) * 100.0d;
        } else {
            $d0 = -1.0d;
        }
        $r2[2] = Double.valueOf($d0);
        $r1.v("AsyncHttpRH", String.format("Progress %d from %d (%2.0f%%)", $r2));
    }
}
