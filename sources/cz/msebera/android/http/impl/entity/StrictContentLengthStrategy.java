package cz.msebera.android.http.impl.entity;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.entity.ContentLengthStrategy;
import cz.msebera.android.http.mime.Args;

public class StrictContentLengthStrategy implements ContentLengthStrategy {
    public static final StrictContentLengthStrategy INSTANCE = new StrictContentLengthStrategy();
    private final int implicitLen;

    public StrictContentLengthStrategy() {
        this(-1);
    }

    public StrictContentLengthStrategy(int i) {
        this.implicitLen = i;
    }

    public long determineLength(HttpMessage httpMessage) {
        Args.notNull(httpMessage, "HTTP message");
        Header $r3 = httpMessage.getFirstHeader("Transfer-Encoding");
        if ($r3 != null) {
            String $r4 = $r3.getValue();
            if ("chunked".equalsIgnoreCase($r4)) {
                if (!httpMessage.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0)) {
                    return -2;
                }
                throw new ProtocolException("Chunked transfer encoding not allowed for " + httpMessage.getProtocolVersion());
            } else if ("identity".equalsIgnoreCase($r4)) {
                return -1;
            } else {
                throw new ProtocolException("Unsupported transfer encoding: " + $r4);
            }
        } else {
            Header $r32 = httpMessage.getFirstHeader("Content-Length");
            if ($r32 == null) {
                return (long) this.implicitLen;
            }
            String $r42 = $r32.getValue();
            try {
                long $l1 = Long.parseLong($r42);
                if ($l1 >= 0) {
                    return $l1;
                }
                throw new ProtocolException("Negative content length: " + $r42);
            } catch (NumberFormatException e) {
                throw new ProtocolException("Invalid content length: " + $r42);
            }
        }
    }
}
