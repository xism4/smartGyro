package cz.msebera.android.http.entity;

import cz.msebera.android.http.impl.io.EmptyInputStream;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import java.io.InputStream;
import java.io.OutputStream;

public class BasicHttpEntity extends AbstractHttpEntity {
    private InputStream content;
    private long length = -1;

    public InputStream getContent() {
        Asserts.check(this.content != null, "Content has not been provided");
        return this.content;
    }

    public long getContentLength() {
        return this.length;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        InputStream $r2 = this.content;
        return ($r2 == null || $r2 == EmptyInputStream.INSTANCE) ? false : true;
    }

    public void setContent(InputStream inputStream) {
        this.content = inputStream;
    }

    public void setContentLength(long j) {
        this.length = j;
    }

    public void writeTo(OutputStream outputStream) {
        Args.notNull(outputStream, "Output stream");
        InputStream $r2 = getContent();
        try {
            byte[] $r3 = new byte[4096];
            while (true) {
                int $i0 = $r2.read($r3);
                if ($i0 != -1) {
                    outputStream.write($r3, 0, $i0);
                } else {
                    return;
                }
            }
        } finally {
            $r2.close();
        }
    }
}
