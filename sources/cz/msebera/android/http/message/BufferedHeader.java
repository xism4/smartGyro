package cz.msebera.android.http.message;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.Serializable;

public class BufferedHeader implements FormattedHeader, Cloneable, Serializable {
    private final CharArrayBuffer buffer;
    private final String name;
    private final int valuePos;

    public BufferedHeader(CharArrayBuffer charArrayBuffer) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        int $i0 = charArrayBuffer.indexOf(58);
        if ($i0 != -1) {
            String $r2 = charArrayBuffer.substringTrimmed(0, $i0);
            if ($r2.length() != 0) {
                this.buffer = charArrayBuffer;
                this.name = $r2;
                this.valuePos = $i0 + 1;
                return;
            }
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
        throw new ParseException("Invalid header: " + charArrayBuffer.toString());
    }

    public Object clone() {
        return super.clone();
    }

    public CharArrayBuffer getBuffer() {
        return this.buffer;
    }

    public HeaderElement[] getElements() {
        CharArrayBuffer $r1 = new CharArrayBuffer(0, this.buffer.length());
        $r1.append(this.valuePos);
        return BasicHeaderValueParser.INSTANCE.parseElements(this.buffer, $r1);
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        CharArrayBuffer $r1 = this.buffer;
        return $r1.substringTrimmed(this.valuePos, $r1.length());
    }

    public int getValuePos() {
        return this.valuePos;
    }

    public String toString() {
        return this.buffer.toString();
    }
}
