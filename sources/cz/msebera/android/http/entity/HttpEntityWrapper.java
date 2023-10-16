package cz.msebera.android.http.entity;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.mime.Args;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpEntityWrapper implements HttpEntity {
    protected HttpEntity wrappedEntity;

    public HttpEntityWrapper(HttpEntity $r1) {
        Args.notNull($r1, "Wrapped entity");
        this.wrappedEntity = $r1;
    }

    public void consumeContent() {
        this.wrappedEntity.consumeContent();
    }

    public InputStream getContent() {
        return this.wrappedEntity.getContent();
    }

    public Header getContentEncoding() {
        return this.wrappedEntity.getContentEncoding();
    }

    public long getContentLength() {
        return this.wrappedEntity.getContentLength();
    }

    public Header getContentType() {
        return this.wrappedEntity.getContentType();
    }

    public boolean isChunked() {
        return this.wrappedEntity.isChunked();
    }

    public boolean isRepeatable() {
        return this.wrappedEntity.isRepeatable();
    }

    public boolean isStreaming() {
        return this.wrappedEntity.isStreaming();
    }

    public void writeTo(OutputStream outputStream) {
        this.wrappedEntity.writeTo(outputStream);
    }
}
