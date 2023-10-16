package p036c.p037a.p038a.p039a;

import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: c.a.a.a.l */
public interface C0837l {
    @Deprecated
    void consumeContent();

    InputStream getContent();

    C0576e getContentEncoding();

    long getContentLength();

    C0576e getContentType();

    boolean isChunked();

    boolean isRepeatable();

    boolean isStreaming();

    void writeTo(OutputStream outputStream);
}
