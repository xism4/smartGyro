package p036c.p037a.p038a.p039a.p041b.p047f;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p058g.C0661e;
import p036c.p037a.p038a.p039a.p069k.C0825m;
import p036c.p037a.p038a.p039a.p069k.C0835w;
import p036c.p037a.p038a.p039a.p069k.C0836x;
import p036c.p037a.p038a.p039a.p072n.C0854d;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.b.f.f */
public class C0557f {

    /* renamed from: a */
    private static final BitSet f1857a = new BitSet(256);

    /* renamed from: b */
    private static final BitSet f1858b = new BitSet(256);

    /* renamed from: c */
    private static final BitSet f1859c = new BitSet(256);

    /* renamed from: d */
    private static final BitSet f1860d = new BitSet(256);

    /* renamed from: e */
    private static final BitSet f1861e = new BitSet(256);

    /* renamed from: f */
    private static final BitSet f1862f = new BitSet(256);

    /* renamed from: g */
    private static final BitSet f1863g = new BitSet(256);

    static {
        for (int i = 97; i <= 122; i++) {
            f1857a.set(i);
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            f1857a.set(i2);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            f1857a.set(i3);
        }
        f1857a.set(95);
        f1857a.set(45);
        f1857a.set(46);
        f1857a.set(42);
        f1863g.or(f1857a);
        f1857a.set(33);
        f1857a.set(126);
        f1857a.set(39);
        f1857a.set(40);
        f1857a.set(41);
        f1858b.set(44);
        f1858b.set(59);
        f1858b.set(58);
        f1858b.set(36);
        f1858b.set(38);
        f1858b.set(43);
        f1858b.set(61);
        f1859c.or(f1857a);
        f1859c.or(f1858b);
        f1860d.or(f1857a);
        f1860d.set(47);
        f1860d.set(59);
        f1860d.set(58);
        f1860d.set(64);
        f1860d.set(38);
        f1860d.set(61);
        f1860d.set(43);
        f1860d.set(36);
        f1860d.set(44);
        f1862f.set(59);
        f1862f.set(47);
        f1862f.set(63);
        f1862f.set(58);
        f1862f.set(64);
        f1862f.set(38);
        f1862f.set(61);
        f1862f.set(43);
        f1862f.set(36);
        f1862f.set(44);
        f1862f.set(91);
        f1862f.set(93);
        f1861e.or(f1862f);
        f1861e.or(f1857a);
    }

    /* renamed from: a */
    public static String m2258a(Iterable<? extends C0889z> iterable, char c, Charset charset) {
        StringBuilder sb = new StringBuilder();
        for (C0889z zVar : iterable) {
            String f = m2269f(zVar.getName(), charset);
            String f2 = m2269f(zVar.getValue(), charset);
            if (sb.length() > 0) {
                sb.append(c);
            }
            sb.append(f);
            if (f2 != null) {
                sb.append("=");
                sb.append(f2);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m2259a(Iterable<? extends C0889z> iterable, Charset charset) {
        return m2258a(iterable, '&', charset);
    }

    /* renamed from: a */
    static String m2260a(String str, Charset charset) {
        return m2261a(str, charset, f1860d, false);
    }

    /* renamed from: a */
    private static String m2261a(String str, Charset charset, BitSet bitSet, boolean z) {
        char upperCase;
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ByteBuffer encode = charset.encode(str);
        while (encode.hasRemaining()) {
            byte b = encode.get() & 255;
            if (bitSet.get(b)) {
                upperCase = (char) b;
            } else if (!z || b != 32) {
                sb.append("%");
                char upperCase2 = Character.toUpperCase(Character.forDigit((b >> 4) & 15, 16));
                upperCase = Character.toUpperCase(Character.forDigit(b & 15, 16));
                sb.append(upperCase2);
            } else {
                upperCase = '+';
            }
            sb.append(upperCase);
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static String m2262a(String str, Charset charset, boolean z) {
        byte b;
        if (str == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(str.length());
        CharBuffer wrap = CharBuffer.wrap(str);
        while (wrap.hasRemaining()) {
            int i = wrap.get();
            if (i == 37 && wrap.remaining() >= 2) {
                char c = wrap.get();
                char c2 = wrap.get();
                int digit = Character.digit(c, 16);
                int digit2 = Character.digit(c2, 16);
                if (digit == -1 || digit2 == -1) {
                    allocate.put((byte) 37);
                    allocate.put((byte) c);
                    b = (byte) c2;
                    allocate.put(b);
                } else {
                    i = (digit << 4) + digit2;
                }
            } else if (z && i == 43) {
                b = 32;
                allocate.put(b);
            }
            b = (byte) i;
            allocate.put(b);
        }
        allocate.flip();
        return charset.decode(allocate).toString();
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public static List<C0889z> m2263a(C0837l lVar) {
        C0661e a = C0661e.m2422a(lVar);
        if (a == null || !a.mo2797b().equalsIgnoreCase("application/x-www-form-urlencoded")) {
            return Collections.emptyList();
        }
        long contentLength = lVar.getContentLength();
        C0870a.m3044a(contentLength <= 2147483647L, "HTTP entity is too large");
        Charset a2 = a.mo2796a() != null ? a.mo2796a() : C0854d.f2421a;
        InputStream content = lVar.getContent();
        if (content == null) {
            return Collections.emptyList();
        }
        try {
            C0873d dVar = new C0873d(contentLength > 0 ? (int) contentLength : 1024);
            InputStreamReader inputStreamReader = new InputStreamReader(content, a2);
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    break;
                }
                dVar.mo3300a(cArr, 0, read);
            }
            content.close();
            if (dVar.length() == 0) {
                return Collections.emptyList();
            }
            return m2264a(dVar, a2, '&');
        } catch (Throwable th) {
            content.close();
            throw th;
        }
    }

    /* renamed from: a */
    public static List<C0889z> m2264a(C0873d dVar, Charset charset, char... cArr) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0836x xVar = C0836x.f2405a;
        BitSet bitSet = new BitSet();
        for (char c : cArr) {
            bitSet.set(c);
        }
        C0835w wVar = new C0835w(0, dVar.length());
        ArrayList arrayList = new ArrayList();
        while (!wVar.mo3219a()) {
            bitSet.set(61);
            String a = xVar.mo3223a(dVar, wVar, bitSet);
            String str = null;
            if (!wVar.mo3219a()) {
                char charAt = dVar.charAt(wVar.mo3220b());
                wVar.mo3218a(wVar.mo3220b() + 1);
                if (charAt == '=') {
                    bitSet.clear(61);
                    str = xVar.mo3227b(dVar, wVar, bitSet);
                    if (!wVar.mo3219a()) {
                        wVar.mo3218a(wVar.mo3220b() + 1);
                    }
                }
            }
            if (!a.isEmpty()) {
                arrayList.add(new C0825m(m2268e(a, charset), m2268e(str, charset)));
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    static String m2265b(String str, Charset charset) {
        return m2261a(str, charset, f1861e, false);
    }

    /* renamed from: c */
    static String m2266c(String str, Charset charset) {
        return m2261a(str, charset, f1859c, false);
    }

    /* renamed from: d */
    public static List<C0889z> m2267d(String str, Charset charset) {
        C0873d dVar = new C0873d(str.length());
        dVar.mo3298a(str);
        return m2264a(dVar, charset, '&', ';');
    }

    /* renamed from: e */
    private static String m2268e(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = C0570c.f1865a;
        }
        return m2262a(str, charset, true);
    }

    /* renamed from: f */
    private static String m2269f(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = C0570c.f1865a;
        }
        return m2261a(str, charset, f1863g, true);
    }
}
