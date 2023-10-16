package cz.msebera.android.http.message;

import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.BitSet;

public class TokenParser {
    public static final TokenParser INSTANCE = new TokenParser();

    public static BitSet INIT_BITSET(int... iArr) {
        BitSet $r1 = new BitSet();
        for (int $i1 : iArr) {
            $r1.set($i1);
        }
        return $r1;
    }

    public static boolean isWhitespace(char c) {
        return c == ' ' || c == 9 || c == 13 || c == 10;
    }

    public void copyContent(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2, BitSet bitSet, StringBuilder sb) {
        int $i0 = charArrayBuffer2.getPos();
        int $i2 = charArrayBuffer2.length();
        for (int $i1 = charArrayBuffer2.getPos(); $i1 < $i2; $i1++) {
            char $c3 = charArrayBuffer.charAt($i1);
            if ((bitSet != null && bitSet.get($c3)) || isWhitespace($c3)) {
                break;
            }
            $i0++;
            sb.append($c3);
        }
        charArrayBuffer2.append($i0);
    }

    public void copyQuotedContent(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2, StringBuilder sb) {
        if (!charArrayBuffer2.atEnd()) {
            int $i0 = charArrayBuffer2.getPos();
            int $i1 = charArrayBuffer2.getPos();
            int $i2 = charArrayBuffer2.length();
            if (charArrayBuffer.charAt($i0) == '\"') {
                int $i12 = $i1 + 1;
                int $i02 = $i0 + 1;
                boolean $z0 = false;
                while (true) {
                    if ($i12 >= $i2) {
                        break;
                    }
                    char $c3 = charArrayBuffer.charAt($i12);
                    if ($z0) {
                        if (!($c3 == '\"' || $c3 == '\\')) {
                            sb.append('\\');
                        }
                        sb.append($c3);
                        $z0 = false;
                    } else if ($c3 == '\"') {
                        $i02++;
                        break;
                    } else if ($c3 == '\\') {
                        $z0 = true;
                    } else if (!($c3 == 13 || $c3 == 10)) {
                        sb.append($c3);
                    }
                    $i12++;
                    $i02++;
                }
                charArrayBuffer2.append($i02);
            }
        }
    }

    public String parseToken(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2, BitSet bitSet) {
        StringBuilder $r3 = new StringBuilder();
        loop0:
        while (true) {
            boolean $z0 = false;
            while (!charArrayBuffer2.atEnd()) {
                char $c1 = charArrayBuffer.charAt(charArrayBuffer2.getPos());
                if (bitSet != null && bitSet.get($c1)) {
                    break loop0;
                } else if (isWhitespace($c1)) {
                    skipWhiteSpace(charArrayBuffer, charArrayBuffer2);
                    $z0 = true;
                } else {
                    if ($z0 && $r3.length() > 0) {
                        $r3.append(' ');
                    }
                    copyContent(charArrayBuffer, charArrayBuffer2, bitSet, $r3);
                }
            }
            break loop0;
        }
        return $r3.toString();
    }

    public String parseValue(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2, BitSet bitSet) {
        StringBuilder $r3 = new StringBuilder();
        loop0:
        while (true) {
            boolean $z0 = false;
            while (!charArrayBuffer2.atEnd()) {
                char $c1 = charArrayBuffer.charAt(charArrayBuffer2.getPos());
                if (bitSet != null && bitSet.get($c1)) {
                    break loop0;
                } else if (isWhitespace($c1)) {
                    skipWhiteSpace(charArrayBuffer, charArrayBuffer2);
                    $z0 = true;
                } else if ($c1 == '\"') {
                    if ($z0 && $r3.length() > 0) {
                        $r3.append(' ');
                    }
                    copyQuotedContent(charArrayBuffer, charArrayBuffer2, $r3);
                } else {
                    if ($z0 && $r3.length() > 0) {
                        $r3.append(' ');
                    }
                    parseValue(charArrayBuffer, charArrayBuffer2, bitSet, $r3);
                }
            }
            break loop0;
        }
        return $r3.toString();
    }

    public void parseValue(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2, BitSet bitSet, StringBuilder sb) {
        int $i0 = charArrayBuffer2.getPos();
        int $i2 = charArrayBuffer2.length();
        for (int $i1 = charArrayBuffer2.getPos(); $i1 < $i2; $i1++) {
            char $c3 = charArrayBuffer.charAt($i1);
            if ((bitSet != null && bitSet.get($c3)) || isWhitespace($c3) || $c3 == '\"') {
                break;
            }
            $i0++;
            sb.append($c3);
        }
        charArrayBuffer2.append($i0);
    }

    public void skipWhiteSpace(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        int $i0 = charArrayBuffer2.getPos();
        int $i1 = charArrayBuffer2.getPos();
        int $i2 = charArrayBuffer2.length();
        while ($i1 < $i2 && isWhitespace(charArrayBuffer.charAt($i1))) {
            $i0++;
            $i1++;
        }
        charArrayBuffer2.append($i0);
    }
}
