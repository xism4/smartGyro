package cz.msebera.android.http.mime;

import cz.msebera.android.http.Consts;
import java.io.UnsupportedEncodingException;

public final class EncodingUtils {
    public static byte[] getAsciiBytes(String str) {
        Args.notNull(str, "Input");
        return str.getBytes(Consts.ASCII);
    }

    public static byte[] getBytes(String str, String str2) {
        Args.notNull(str, "Input");
        Args.notEmpty((CharSequence) str2, "Charset");
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }
}
