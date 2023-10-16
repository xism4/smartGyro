package cz.msebera.android.http.execchain;

import cz.msebera.android.http.Consts;
import java.nio.charset.Charset;

public final class HTTP {
    public static final Charset DEF_CONTENT_CHARSET = Consts.ISO_8859_1;
    public static final Charset DEF_PROTOCOL_CHARSET = Consts.ASCII;

    public static boolean isWhitespace(char c) {
        return c == ' ' || c == 9 || c == 13 || c == 10;
    }
}
