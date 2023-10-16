package p036c.p037a.p038a.p039a.p074p;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/* renamed from: c.a.a.a.p.e */
public class C0874e {
    /* renamed from: a */
    public static Charset m3077a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Charset.forName(str);
        } catch (UnsupportedCharsetException unused) {
            return null;
        }
    }
}
