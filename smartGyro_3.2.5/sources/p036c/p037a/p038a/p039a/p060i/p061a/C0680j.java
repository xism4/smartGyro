package p036c.p037a.p038a.p039a.p060i.p061a;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.p074p.C0874e;

/* renamed from: c.a.a.a.i.a.j */
final class C0680j implements C0679i {

    /* renamed from: a */
    private static final Charset f2018a = C0874e.m3077a("UnicodeLittleUnmarked");

    /* renamed from: b */
    private static final Charset f2019b = C0570c.f1866b;

    /* renamed from: c */
    private static final SecureRandom f2020c;

    /* renamed from: d */
    private static final byte[] f2021d;

    /* renamed from: e */
    private static final C0682b f2022e = new C0682b();

    /* renamed from: c.a.a.a.i.a.j$a */
    static class C0681a {

        /* renamed from: a */
        private byte[] f2023a = null;

        /* renamed from: b */
        private int f2024b = 0;

        C0681a() {
        }
    }

    /* renamed from: c.a.a.a.i.a.j$b */
    static class C0682b extends C0681a {

        /* renamed from: c */
        private final byte[] f2025c = null;

        /* renamed from: d */
        private final byte[] f2026d = null;

        C0682b() {
        }
    }

    static {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception unused) {
            secureRandom = null;
        }
        f2020c = secureRandom;
        byte[] bytes = "NTLMSSP".getBytes(C0570c.f1866b);
        f2021d = new byte[(bytes.length + 1)];
        System.arraycopy(bytes, 0, f2021d, 0, bytes.length);
        f2021d[bytes.length] = 0;
    }

    C0680j() {
    }
}
