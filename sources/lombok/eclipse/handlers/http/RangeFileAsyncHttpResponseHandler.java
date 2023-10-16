package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.client.HttpResponseException;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import java.io.FileOutputStream;
import java.io.InputStream;

public abstract class RangeFileAsyncHttpResponseHandler extends FileAsyncHttpResponseHandler {
    private boolean append;
    private long current;

    /* access modifiers changed from: protected */
    public byte[] getResponseData(HttpEntity httpEntity) {
        if (httpEntity == null) {
            return null;
        }
        InputStream $r2 = httpEntity.getContent();
        long $l2 = httpEntity.getContentLength() + this.current;
        FileOutputStream $r3 = new FileOutputStream(getTargetFile(), this.append);
        if ($r2 == null) {
            return null;
        }
        try {
            byte[] $r5 = new byte[4096];
            while (this.current < $l2) {
                int $i3 = $r2.read($r5);
                if ($i3 != -1) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    this.current += (long) $i3;
                    $r3.write($r5, 0, $i3);
                    sendProgressMessage(this.current, $l2);
                } else {
                    break;
                }
            }
            return null;
        } finally {
            $r2.close();
            $r3.flush();
            $r3.close();
        }
    }

    public void sendResponseMessage(HttpResponse httpResponse) {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine $r3 = httpResponse.getStatusLine();
            if ($r3.getStatusCode() == 416) {
                if (!Thread.currentThread().isInterrupted()) {
                    sendSuccessMessage($r3.getStatusCode(), httpResponse.getAllHeaders(), (byte[]) null);
                }
            } else if ($r3.getStatusCode() >= 300) {
                if (!Thread.currentThread().isInterrupted()) {
                    sendFailureMessage($r3.getStatusCode(), httpResponse.getAllHeaders(), (byte[]) null, new HttpResponseException($r3.getStatusCode(), $r3.getReasonPhrase()));
                }
            } else if (!Thread.currentThread().isInterrupted()) {
                Header $r7 = httpResponse.getFirstHeader("Content-Range");
                if ($r7 == null) {
                    this.append = false;
                    this.current = 0;
                } else {
                    LogInterface $r8 = AsyncHttpClient.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Content-Range: ");
                    sb.append($r7.getValue());
                    $r8.v("RangeFileAsyncHttpRH", sb.toString());
                }
                sendSuccessMessage($r3.getStatusCode(), httpResponse.getAllHeaders(), getResponseData(httpResponse.getEntity()));
            }
        }
    }

    public void updateRequestHeaders(HttpUriRequest httpUriRequest) {
        if (this.file.exists() && this.file.canWrite()) {
            this.current = this.file.length();
        }
        if (this.current > 0) {
            this.append = true;
            httpUriRequest.setHeader("Range", "bytes=" + this.current + "-");
        }
    }
}
