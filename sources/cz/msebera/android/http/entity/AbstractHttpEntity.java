package cz.msebera.android.http.entity;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.message.BasicHeader;

public abstract class AbstractHttpEntity implements HttpEntity {
    protected boolean chunked;
    protected Header contentEncoding;
    protected Header contentType;

    protected AbstractHttpEntity() {
    }

    public void consumeContent() {
    }

    public Header getContentEncoding() {
        return this.contentEncoding;
    }

    public Header getContentType() {
        return this.contentType;
    }

    public boolean isChunked() {
        return this.chunked;
    }

    public void setChunked(boolean z) {
        this.chunked = z;
    }

    public void setContentEncoding(Header header) {
        this.contentEncoding = header;
    }

    public void setContentType(Header header) {
        this.contentType = header;
    }

    public void setContentType(String str) {
        setContentType((Header) str != null ? new BasicHeader("Content-Type", str) : null);
    }

    public String toString() {
        StringBuilder $r1 = new StringBuilder();
        $r1.append('[');
        if (this.contentType != null) {
            $r1.append("Content-Type: ");
            $r1.append(this.contentType.getValue());
            $r1.append(',');
        }
        if (this.contentEncoding != null) {
            $r1.append("Content-Encoding: ");
            $r1.append(this.contentEncoding.getValue());
            $r1.append(',');
        }
        long $l0 = getContentLength();
        if ($l0 >= 0) {
            $r1.append("Content-Length: ");
            $r1.append($l0);
            $r1.append(',');
        }
        $r1.append("Chunked: ");
        $r1.append(this.chunked);
        $r1.append(']');
        return $r1.toString();
    }
}
