package cz.msebera.android.http.message;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;

public class BasicLineFormatter implements LineFormatter {
    @Deprecated
    public static final BasicLineFormatter DEFAULT = new BasicLineFormatter();
    public static final BasicLineFormatter INSTANCE = new BasicLineFormatter();

    public CharArrayBuffer appendProtocolVersion(CharArrayBuffer $r1, ProtocolVersion protocolVersion) {
        Args.notNull(protocolVersion, "Protocol version");
        int $i0 = estimateProtocolVersionLen(protocolVersion);
        if ($r1 == null) {
            $r1 = new CharArrayBuffer($i0);
        } else {
            $r1.ensureCapacity($i0);
        }
        $r1.append(protocolVersion.getProtocol());
        $r1.append('/');
        $r1.append(Integer.toString(protocolVersion.getMajor()));
        $r1.append('.');
        $r1.append(Integer.toString(protocolVersion.getMinor()));
        return $r1;
    }

    /* access modifiers changed from: protected */
    public void doFormatHeader(CharArrayBuffer charArrayBuffer, Header header) {
        String $r3 = header.getName();
        String $r4 = header.getValue();
        int $i0 = $r3.length() + 2;
        if ($r4 != null) {
            $i0 += $r4.length();
        }
        charArrayBuffer.ensureCapacity($i0);
        charArrayBuffer.append($r3);
        charArrayBuffer.append(": ");
        if ($r4 != null) {
            charArrayBuffer.append($r4);
        }
    }

    /* access modifiers changed from: protected */
    public void doFormatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        String $r3 = requestLine.getMethod();
        String $r4 = requestLine.getUri();
        charArrayBuffer.ensureCapacity($r3.length() + 1 + $r4.length() + 1 + estimateProtocolVersionLen(requestLine.getProtocolVersion()));
        charArrayBuffer.append($r3);
        charArrayBuffer.append(' ');
        charArrayBuffer.append($r4);
        charArrayBuffer.append(' ');
        appendProtocolVersion(charArrayBuffer, requestLine.getProtocolVersion());
    }

    /* access modifiers changed from: protected */
    public void doFormatStatusLine(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        int $i0 = estimateProtocolVersionLen(statusLine.getProtocolVersion()) + 1 + 3 + 1;
        String $r4 = statusLine.getReasonPhrase();
        if ($r4 != null) {
            $i0 += $r4.length();
        }
        charArrayBuffer.ensureCapacity($i0);
        appendProtocolVersion(charArrayBuffer, statusLine.getProtocolVersion());
        charArrayBuffer.append(' ');
        charArrayBuffer.append(Integer.toString(statusLine.getStatusCode()));
        charArrayBuffer.append(' ');
        if ($r4 != null) {
            charArrayBuffer.append($r4);
        }
    }

    /* access modifiers changed from: protected */
    public int estimateProtocolVersionLen(ProtocolVersion protocolVersion) {
        return protocolVersion.getProtocol().length() + 4;
    }

    public CharArrayBuffer formatHeader(CharArrayBuffer $r1, Header header) {
        Args.notNull(header, "Header");
        if (header instanceof FormattedHeader) {
            return ((FormattedHeader) header).getBuffer();
        }
        CharArrayBuffer $r12 = initBuffer($r1);
        doFormatHeader($r12, header);
        return $r12;
    }

    public CharArrayBuffer formatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        Args.notNull(requestLine, "Request line");
        CharArrayBuffer $r2 = initBuffer(charArrayBuffer);
        doFormatRequestLine($r2, requestLine);
        return $r2;
    }

    public CharArrayBuffer formatStatusLine(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        Args.notNull(statusLine, "Status line");
        CharArrayBuffer $r2 = initBuffer(charArrayBuffer);
        doFormatStatusLine($r2, statusLine);
        return $r2;
    }

    /* access modifiers changed from: protected */
    public CharArrayBuffer initBuffer(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer == null) {
            return new CharArrayBuffer(64);
        }
        charArrayBuffer.clear();
        return charArrayBuffer;
    }
}
