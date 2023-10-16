package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.message.BasicHeaderElement;
import cz.msebera.android.http.message.BasicNameValuePair;
import cz.msebera.android.http.message.TokenParser;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.ArrayList;
import java.util.BitSet;

public class NetscapeDraftHeaderParser {
    public static final NetscapeDraftHeaderParser DEFAULT = new NetscapeDraftHeaderParser();
    private static final BitSet TOKEN_DELIMS = TokenParser.INIT_BITSET(61, 59);
    private static final BitSet VALUE_DELIMS = TokenParser.INIT_BITSET(59);
    private final TokenParser tokenParser = TokenParser.INSTANCE;

    private NameValuePair parseNameValuePair(CharArrayBuffer charArrayBuffer, cz.msebera.android.http.message.CharArrayBuffer charArrayBuffer2) {
        String $r5 = this.tokenParser.parseToken(charArrayBuffer, charArrayBuffer2, TOKEN_DELIMS);
        if (charArrayBuffer2.atEnd()) {
            return new BasicNameValuePair($r5, (String) null);
        }
        char $c1 = charArrayBuffer.charAt(charArrayBuffer2.getPos());
        charArrayBuffer2.append(charArrayBuffer2.getPos() + 1);
        if ($c1 != '=') {
            return new BasicNameValuePair($r5, (String) null);
        }
        String $r7 = this.tokenParser.parseToken(charArrayBuffer, charArrayBuffer2, VALUE_DELIMS);
        if (!charArrayBuffer2.atEnd()) {
            charArrayBuffer2.append(charArrayBuffer2.getPos() + 1);
        }
        return new BasicNameValuePair($r5, $r7);
    }

    public HeaderElement parseHeader(CharArrayBuffer charArrayBuffer, cz.msebera.android.http.message.CharArrayBuffer charArrayBuffer2) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(charArrayBuffer2, "Parser cursor");
        NameValuePair $r3 = parseNameValuePair(charArrayBuffer, charArrayBuffer2);
        ArrayList $r4 = new ArrayList();
        while (!charArrayBuffer2.atEnd()) {
            $r4.add(parseNameValuePair(charArrayBuffer, charArrayBuffer2));
        }
        return new BasicHeaderElement($r3.getName(), $r3.getValue(), (NameValuePair[]) $r4.toArray(new NameValuePair[$r4.size()]));
    }
}
