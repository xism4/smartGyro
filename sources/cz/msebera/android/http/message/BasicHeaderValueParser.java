package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.ArrayList;
import java.util.BitSet;

public class BasicHeaderValueParser implements HeaderValueParser {
    @Deprecated
    public static final BasicHeaderValueParser DEFAULT = new BasicHeaderValueParser();
    public static final BasicHeaderValueParser INSTANCE = new BasicHeaderValueParser();
    private static final BitSet TOKEN_DELIMS = TokenParser.INIT_BITSET(61, 59, 44);
    private static final BitSet VALUE_DELIMS = TokenParser.INIT_BITSET(59, 44);
    private final TokenParser tokenParser = TokenParser.INSTANCE;

    public static HeaderElement[] parseElements(String str, HeaderValueParser $r1) {
        Args.notNull(str, "Value");
        CharArrayBuffer $r2 = new CharArrayBuffer(str.length());
        $r2.append(str);
        CharArrayBuffer $r3 = new CharArrayBuffer(0, str.length());
        if ($r1 == null) {
            $r1 = INSTANCE;
        }
        return $r1.parseElements($r2, $r3);
    }

    /* access modifiers changed from: protected */
    public HeaderElement createHeaderElement(String str, String str2, NameValuePair[] nameValuePairArr) {
        return new BasicHeaderElement(str, str2, nameValuePairArr);
    }

    /* access modifiers changed from: protected */
    public NameValuePair createNameValuePair(String str, String str2) {
        return new BasicNameValuePair(str, str2);
    }

    public HeaderElement[] parseElements(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(charArrayBuffer2, "Parser cursor");
        ArrayList $r3 = new ArrayList();
        while (!charArrayBuffer2.atEnd()) {
            HeaderElement $r4 = parseHeaderElement(charArrayBuffer, charArrayBuffer2);
            if ($r4.getName().length() != 0 || $r4.getValue() != null) {
                $r3.add($r4);
            }
        }
        return (HeaderElement[]) $r3.toArray(new HeaderElement[$r3.size()]);
    }

    public HeaderElement parseHeaderElement(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(charArrayBuffer2, "Parser cursor");
        NameValuePair $r3 = parseNameValuePair(charArrayBuffer, charArrayBuffer2);
        return createHeaderElement($r3.getName(), $r3.getValue(), (charArrayBuffer2.atEnd() || charArrayBuffer.charAt(charArrayBuffer2.getPos() + -1) == ',') ? null : parseParameters(charArrayBuffer, charArrayBuffer2));
    }

    public NameValuePair parseNameValuePair(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(charArrayBuffer2, "Parser cursor");
        String $r5 = this.tokenParser.parseToken(charArrayBuffer, charArrayBuffer2, TOKEN_DELIMS);
        if (charArrayBuffer2.atEnd()) {
            return new BasicNameValuePair($r5, (String) null);
        }
        char $c1 = charArrayBuffer.charAt(charArrayBuffer2.getPos());
        charArrayBuffer2.append(charArrayBuffer2.getPos() + 1);
        if ($c1 != '=') {
            return createNameValuePair($r5, (String) null);
        }
        String $r8 = this.tokenParser.parseValue(charArrayBuffer, charArrayBuffer2, VALUE_DELIMS);
        if (!charArrayBuffer2.atEnd()) {
            charArrayBuffer2.append(charArrayBuffer2.getPos() + 1);
        }
        return createNameValuePair($r5, $r8);
    }

    public NameValuePair[] parseParameters(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(charArrayBuffer2, "Parser cursor");
        this.tokenParser.skipWhiteSpace(charArrayBuffer, charArrayBuffer2);
        ArrayList $r4 = new ArrayList();
        while (!charArrayBuffer2.atEnd()) {
            $r4.add(parseNameValuePair(charArrayBuffer, charArrayBuffer2));
            if (charArrayBuffer.charAt(charArrayBuffer2.getPos() - 1) == ',') {
                break;
            }
        }
        return (NameValuePair[]) $r4.toArray(new NameValuePair[$r4.size()]);
    }
}
