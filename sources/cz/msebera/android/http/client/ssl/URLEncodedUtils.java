package cz.msebera.android.http.client.ssl;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.entity.ContentType;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.message.BasicNameValuePair;
import cz.msebera.android.http.message.TokenParser;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class URLEncodedUtils {
    private static final BitSet PATHSAFE = new BitSet(256);
    private static final BitSet PUNCT = new BitSet(256);
    private static final BitSet RESERVED = new BitSet(256);
    private static final BitSet UNRESERVED = new BitSet(256);
    private static final BitSet URIC = new BitSet(256);
    private static final BitSet URLENCODER = new BitSet(256);
    private static final BitSet USERINFO = new BitSet(256);

    static {
        for (int $i0 = 97; $i0 <= 122; $i0++) {
            UNRESERVED.set($i0);
        }
        for (int $i02 = 65; $i02 <= 90; $i02++) {
            UNRESERVED.set($i02);
        }
        for (int $i03 = 48; $i03 <= 57; $i03++) {
            UNRESERVED.set($i03);
        }
        UNRESERVED.set(95);
        UNRESERVED.set(45);
        UNRESERVED.set(46);
        UNRESERVED.set(42);
        URLENCODER.or(UNRESERVED);
        UNRESERVED.set(33);
        UNRESERVED.set(126);
        UNRESERVED.set(39);
        UNRESERVED.set(40);
        UNRESERVED.set(41);
        PUNCT.set(44);
        PUNCT.set(59);
        PUNCT.set(58);
        PUNCT.set(36);
        PUNCT.set(38);
        PUNCT.set(43);
        PUNCT.set(61);
        USERINFO.or(UNRESERVED);
        USERINFO.or(PUNCT);
        PATHSAFE.or(UNRESERVED);
        PATHSAFE.set(47);
        PATHSAFE.set(59);
        PATHSAFE.set(58);
        PATHSAFE.set(64);
        PATHSAFE.set(38);
        PATHSAFE.set(61);
        PATHSAFE.set(43);
        PATHSAFE.set(36);
        PATHSAFE.set(44);
        RESERVED.set(59);
        RESERVED.set(47);
        RESERVED.set(63);
        RESERVED.set(58);
        RESERVED.set(64);
        RESERVED.set(38);
        RESERVED.set(61);
        RESERVED.set(43);
        RESERVED.set(36);
        RESERVED.set(44);
        RESERVED.set(91);
        RESERVED.set(93);
        URIC.or(RESERVED);
        URIC.or(UNRESERVED);
    }

    private static String decodeFormFields(String str, Charset $r1) {
        if (str == null) {
            return null;
        }
        if ($r1 == null) {
            $r1 = Consts.UTF_8;
        }
        return urlDecode(str, $r1, true);
    }

    static String encPath(String str, Charset charset) {
        return urlEncode(str, charset, USERINFO, false);
    }

    static String encUric(String str, Charset charset) {
        return urlEncode(str, charset, URIC, false);
    }

    static String encUserInfo(String str, Charset charset) {
        return urlEncode(str, charset, PATHSAFE, false);
    }

    private static String encodeFormFields(String str, Charset $r2) {
        if (str == null) {
            return null;
        }
        if ($r2 == null) {
            $r2 = Consts.UTF_8;
        }
        return urlEncode(str, $r2, URLENCODER, true);
    }

    public static String format(Iterable iterable, char c, Charset charset) {
        StringBuilder $r1 = new StringBuilder();
        Iterator $r3 = iterable.iterator();
        while ($r3.hasNext()) {
            NameValuePair $r5 = (NameValuePair) $r3.next();
            String $r6 = encodeFormFields($r5.getName(), charset);
            String $r7 = encodeFormFields($r5.getValue(), charset);
            if ($r1.length() > 0) {
                $r1.append(c);
            }
            $r1.append($r6);
            if ($r7 != null) {
                $r1.append("=");
                $r1.append($r7);
            }
        }
        return $r1.toString();
    }

    public static String format(Iterable iterable, Charset charset) {
        return format(iterable, '&', charset);
    }

    /* JADX INFO: finally extract failed */
    public static List parse(HttpEntity httpEntity) {
        ContentType $r1 = ContentType.get(httpEntity);
        if ($r1 == null || !$r1.getMimeType().equalsIgnoreCase("application/x-www-form-urlencoded")) {
            return Collections.emptyList();
        }
        long $l1 = httpEntity.getContentLength();
        Args.check($l1 <= 2147483647L, "HTTP entity is too large");
        Charset $r3 = $r1.getCharset() != null ? $r1.getCharset() : HTTP.DEF_CONTENT_CHARSET;
        InputStream $r4 = httpEntity.getContent();
        if ($r4 == null) {
            return Collections.emptyList();
        }
        try {
            CharArrayBuffer $r6 = new CharArrayBuffer($l1 > 0 ? (int) $l1 : 1024);
            InputStreamReader inputStreamReader = new InputStreamReader($r4, $r3);
            char[] $r8 = new char[1024];
            while (true) {
                int $i2 = inputStreamReader.read($r8);
                if ($i2 == -1) {
                    break;
                }
                $r6.append($r8, 0, $i2);
            }
            $r4.close();
            if ($r6.length() == 0) {
                return Collections.emptyList();
            }
            return parse($r6, $r3, '&');
        } catch (Throwable $r9) {
            $r4.close();
            throw $r9;
        }
    }

    public static List parse(CharArrayBuffer charArrayBuffer, Charset charset, char... cArr) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        TokenParser $r4 = TokenParser.INSTANCE;
        BitSet $r2 = new BitSet();
        for (char $c2 : cArr) {
            $r2.set($c2);
        }
        cz.msebera.android.http.message.CharArrayBuffer $r5 = new cz.msebera.android.http.message.CharArrayBuffer(0, charArrayBuffer.length());
        ArrayList $r6 = new ArrayList();
        while (!$r5.atEnd()) {
            $r2.set(61);
            String $r7 = $r4.parseToken(charArrayBuffer, $r5, $r2);
            String $r8 = null;
            if (!$r5.atEnd()) {
                char $c22 = charArrayBuffer.charAt($r5.getPos());
                $r5.append($r5.getPos() + 1);
                if ($c22 == '=') {
                    $r2.clear(61);
                    $r8 = $r4.parseValue(charArrayBuffer, $r5, $r2);
                    if (!$r5.atEnd()) {
                        $r5.append($r5.getPos() + 1);
                    }
                }
            }
            if (!$r7.isEmpty()) {
                $r6.add(new BasicNameValuePair(decodeFormFields($r7, charset), decodeFormFields($r8, charset)));
            }
        }
        return $r6;
    }

    public static List parse(String str, Charset charset) {
        CharArrayBuffer $r1 = new CharArrayBuffer(str.length());
        $r1.append(str);
        return parse($r1, charset, '&', ';');
    }

    private static String urlDecode(String str, Charset charset, boolean z) {
        byte $b4;
        if (str == null) {
            return null;
        }
        ByteBuffer $r2 = ByteBuffer.allocate(str.length());
        CharBuffer $r3 = CharBuffer.wrap(str);
        while ($r3.hasRemaining()) {
            char $c1 = $r3.get();
            int $i0 = $c1;
            if ($c1 == '%' && $r3.remaining() >= 2) {
                char $c12 = $r3.get();
                char $c3 = $r3.get();
                int $i2 = Character.digit($c12, 16);
                int $i02 = Character.digit($c3, 16);
                if ($i2 == -1 || $i02 == -1) {
                    $r2.put((byte) 37);
                    $r2.put((byte) $c12);
                    $b4 = (byte) $c3;
                    $r2.put($b4);
                } else {
                    $i0 = ($i2 << 4) + $i02;
                }
            } else if (z && $c1 == '+') {
                $b4 = 32;
                $r2.put($b4);
            }
            $b4 = (byte) $i0;
            $r2.put($b4);
        }
        $r2.flip();
        return charset.decode($r2).toString();
    }

    private static String urlEncode(String str, Charset charset, BitSet bitSet, boolean z) {
        char $c2;
        if (str == null) {
            return null;
        }
        StringBuilder $r1 = new StringBuilder();
        ByteBuffer $r4 = charset.encode(str);
        while ($r4.hasRemaining()) {
            short $s1 = $r4.get() & 255;
            if (bitSet.get($s1)) {
                $c2 = (char) $s1;
            } else if (!z || $s1 != 32) {
                $r1.append("%");
                char $c4 = Character.toUpperCase(Character.forDigit(($s1 >> 4) & 15, 16));
                $c2 = Character.toUpperCase(Character.forDigit($s1 & 15, 16));
                $r1.append($c4);
            } else {
                $c2 = '+';
            }
            $r1.append($c2);
        }
        return $r1.toString();
    }
}
