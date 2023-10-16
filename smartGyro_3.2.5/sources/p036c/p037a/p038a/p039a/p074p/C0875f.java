package p036c.p037a.p038a.p039a.p074p;

import java.io.UnsupportedEncodingException;
import p036c.p037a.p038a.p039a.C0570c;

/* renamed from: c.a.a.a.p.f */
public final class C0875f {
    /* renamed from: a */
    public static byte[] m3078a(String str) {
        C0870a.m3042a(str, "Input");
        return str.getBytes(C0570c.f1866b);
    }

    /* renamed from: a */
    public static byte[] m3079a(String str, String str2) {
        C0870a.m3042a(str, "Input");
        C0870a.m3048c(str2, "Charset");
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }
}
