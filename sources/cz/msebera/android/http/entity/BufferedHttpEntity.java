package cz.msebera.android.http.entity;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.EntityUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedHttpEntity extends HttpEntityWrapper {
    private final byte[] buffer;

    public BufferedHttpEntity(HttpEntity httpEntity) {
        super(httpEntity);
        if (!httpEntity.isRepeatable() || httpEntity.getContentLength() < 0) {
            this.buffer = EntityUtils.toByteArray(httpEntity);
        } else {
            this.buffer = null;
        }
    }

    public InputStream getContent() {
        byte[] $r3 = this.buffer;
        return $r3 != null ? new ByteArrayInputStream($r3) : super.getContent();
    }

    public long getContentLength() {
        byte[] $r1 = this.buffer;
        return $r1 != null ? (long) $r1.length : super.getContentLength();
    }

    public boolean isChunked() {
        return this.buffer == null && super.isChunked();
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return this.buffer == null && super.isStreaming();
    }

    public void writeTo(OutputStream outputStream) {
        Args.notNull(outputStream, "Output stream");
        byte[] $r2 = this.buffer;
        if ($r2 != null) {
            outputStream.write($r2);
        } else {
            super.writeTo(outputStream);
        }
    }
}
