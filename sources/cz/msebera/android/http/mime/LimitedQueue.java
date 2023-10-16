package cz.msebera.android.http.mime;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class LimitedQueue {
    public static Charset lookup(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Charset.forName(str);
        } catch (UnsupportedCharsetException e) {
            return null;
        }
    }
}
