package cz.msebera.android.http.impl.entity;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.entity.ContentLengthStrategy;
import cz.msebera.android.http.mime.Args;

public class LaxContentLengthStrategy implements ContentLengthStrategy {
    public static final LaxContentLengthStrategy INSTANCE = new LaxContentLengthStrategy();
    private final int implicitLen;

    public LaxContentLengthStrategy() {
        this(-1);
    }

    public LaxContentLengthStrategy(int i) {
        this.implicitLen = i;
    }

    public long determineLength(HttpMessage httpMessage) {
        long $l1;
        Args.notNull(httpMessage, "HTTP message");
        Header $r5 = httpMessage.getFirstHeader("Transfer-Encoding");
        if ($r5 != null) {
            try {
                HeaderElement[] $r6 = $r5.getElements();
                int $i0 = $r6.length;
                return (!"identity".equalsIgnoreCase($r5.getValue()) && $i0 > 0 && "chunked".equalsIgnoreCase($r6[$i0 + -1].getName())) ? -2 : -1;
            } catch (ParseException $r9) {
                throw new ProtocolException("Invalid Transfer-Encoding header value: " + $r5, $r9);
            }
        } else if (httpMessage.getFirstHeader("Content-Length") == null) {
            return (long) this.implicitLen;
        } else {
            Header[] $r11 = httpMessage.getHeaders("Content-Length");
            int $i02 = $r11.length - 1;
            while (true) {
                if ($i02 < 0) {
                    $l1 = -1;
                    break;
                }
                try {
                    $l1 = Long.parseLong($r11[$i02].getValue());
                    break;
                } catch (NumberFormatException e) {
                    $i02--;
                }
            }
            if ($l1 >= 0) {
                return $l1;
            }
            return -1;
        }
    }
}
