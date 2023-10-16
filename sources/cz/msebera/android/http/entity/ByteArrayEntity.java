package cz.msebera.android.http.entity;

import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.mime.Args;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class ByteArrayEntity extends AbstractHttpEntity implements Cloneable {
    protected final byte[] content;

    public ByteArrayEntity(String str, ContentType contentType) {
        Args.notNull(str, "Source string");
        Charset $r3 = contentType != null ? contentType.getCharset() : null;
        this.content = str.getBytes($r3 == null ? HTTP.DEF_CONTENT_CHARSET : $r3);
        if (contentType != null) {
            setContentType(contentType.toString());
        }
    }

    public Object clone() {
        return super.clone();
    }

    public InputStream getContent() {
        return new ByteArrayInputStream(this.content);
    }

    public long getContentLength() {
        return (long) this.content.length;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        Args.notNull(outputStream, "Output stream");
        outputStream.write(this.content);
        outputStream.flush();
    }
}
