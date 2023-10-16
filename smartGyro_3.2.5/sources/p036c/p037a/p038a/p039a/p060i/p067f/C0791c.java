package p036c.p037a.p038a.p039a.p060i.p067f;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.p068j.C0805a;
import p036c.p037a.p038a.p039a.p068j.C0809e;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0872c;
import p036c.p037a.p038a.p039a.p074p.C0873d;

@Deprecated
/* renamed from: c.a.a.a.i.f.c */
public abstract class C0791c implements C0810f, C0805a {

    /* renamed from: a */
    private InputStream f2289a;

    /* renamed from: b */
    private byte[] f2290b;

    /* renamed from: c */
    private C0872c f2291c;

    /* renamed from: d */
    private Charset f2292d;

    /* renamed from: e */
    private boolean f2293e;

    /* renamed from: f */
    private int f2294f;

    /* renamed from: g */
    private int f2295g;

    /* renamed from: h */
    private C0799k f2296h;

    /* renamed from: i */
    private CodingErrorAction f2297i;

    /* renamed from: j */
    private CodingErrorAction f2298j;

    /* renamed from: k */
    private int f2299k;

    /* renamed from: l */
    private int f2300l;

    /* renamed from: m */
    private CharsetDecoder f2301m;

    /* renamed from: n */
    private CharBuffer f2302n;

    /* renamed from: a */
    private int m2860a(C0873d dVar, int i) {
        int i2 = this.f2299k;
        this.f2299k = i + 1;
        if (i > i2 && this.f2290b[i - 1] == 13) {
            i--;
        }
        int i3 = i - i2;
        if (!this.f2293e) {
            return m2861a(dVar, ByteBuffer.wrap(this.f2290b, i2, i3));
        }
        dVar.mo3299a(this.f2290b, i2, i3);
        return i3;
    }

    /* renamed from: a */
    private int m2861a(C0873d dVar, ByteBuffer byteBuffer) {
        int i = 0;
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.f2301m == null) {
            this.f2301m = this.f2292d.newDecoder();
            this.f2301m.onMalformedInput(this.f2297i);
            this.f2301m.onUnmappableCharacter(this.f2298j);
        }
        if (this.f2302n == null) {
            this.f2302n = CharBuffer.allocate(1024);
        }
        this.f2301m.reset();
        while (byteBuffer.hasRemaining()) {
            i += m2862a(this.f2301m.decode(byteBuffer, this.f2302n, true), dVar, byteBuffer);
        }
        int a = i + m2862a(this.f2301m.flush(this.f2302n), dVar, byteBuffer);
        this.f2302n.clear();
        return a;
    }

    /* renamed from: a */
    private int m2862a(CoderResult coderResult, C0873d dVar, ByteBuffer byteBuffer) {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.f2302n.flip();
        int remaining = this.f2302n.remaining();
        while (this.f2302n.hasRemaining()) {
            dVar.append(this.f2302n.get());
        }
        this.f2302n.compact();
        return remaining;
    }

    /* renamed from: b */
    private int m2863b(C0873d dVar) {
        int f = this.f2291c.mo3291f();
        if (f > 0) {
            if (this.f2291c.mo3287b(f - 1) == 10) {
                f--;
            }
            if (f > 0 && this.f2291c.mo3287b(f - 1) == 13) {
                f--;
            }
        }
        if (this.f2293e) {
            dVar.mo3296a(this.f2291c, 0, f);
        } else {
            f = m2861a(dVar, ByteBuffer.wrap(this.f2291c.mo3285a(), 0, f));
        }
        this.f2291c.mo3288c();
        return f;
    }

    /* renamed from: e */
    private int m2864e() {
        for (int i = this.f2299k; i < this.f2300l; i++) {
            if (this.f2290b[i] == 10) {
                return i;
            }
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        if (r2 == -1) goto L_0x002d;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo3012a(p036c.p037a.p038a.p039a.p074p.C0873d r8) {
        /*
            r7 = this;
            java.lang.String r0 = "Char array buffer"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3042a(r8, (java.lang.String) r0)
            r0 = 1
            r1 = 0
            r2 = 0
        L_0x0008:
            r3 = -1
            if (r0 == 0) goto L_0x0063
            int r4 = r7.m2864e()
            if (r4 == r3) goto L_0x002f
            c.a.a.a.p.c r0 = r7.f2291c
            boolean r0 = r0.mo3289d()
            if (r0 == 0) goto L_0x001e
            int r8 = r7.m2860a((p036c.p037a.p038a.p039a.p074p.C0873d) r8, (int) r4)
            return r8
        L_0x001e:
            int r4 = r4 + 1
            int r0 = r7.f2299k
            int r3 = r4 - r0
            c.a.a.a.p.c r5 = r7.f2291c
            byte[] r6 = r7.f2290b
            r5.mo3283a((byte[]) r6, (int) r0, (int) r3)
            r7.f2299k = r4
        L_0x002d:
            r0 = 0
            goto L_0x004c
        L_0x002f:
            boolean r2 = r7.mo3064d()
            if (r2 == 0) goto L_0x0045
            int r2 = r7.f2300l
            int r4 = r7.f2299k
            int r2 = r2 - r4
            c.a.a.a.p.c r5 = r7.f2291c
            byte[] r6 = r7.f2290b
            r5.mo3283a((byte[]) r6, (int) r4, (int) r2)
            int r2 = r7.f2300l
            r7.f2299k = r2
        L_0x0045:
            int r2 = r7.mo3063c()
            if (r2 != r3) goto L_0x004c
            goto L_0x002d
        L_0x004c:
            int r3 = r7.f2294f
            if (r3 <= 0) goto L_0x0008
            c.a.a.a.p.c r3 = r7.f2291c
            int r3 = r3.mo3291f()
            int r4 = r7.f2294f
            if (r3 >= r4) goto L_0x005b
            goto L_0x0008
        L_0x005b:
            java.io.IOException r8 = new java.io.IOException
            java.lang.String r0 = "Maximum line length limit exceeded"
            r8.<init>(r0)
            throw r8
        L_0x0063:
            if (r2 != r3) goto L_0x006e
            c.a.a.a.p.c r0 = r7.f2291c
            boolean r0 = r0.mo3289d()
            if (r0 == 0) goto L_0x006e
            return r3
        L_0x006e:
            int r8 = r7.m2863b(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p067f.C0791c.mo3012a(c.a.a.a.p.d):int");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3061a(InputStream inputStream, int i, C0844g gVar) {
        C0870a.m3042a(inputStream, "Input stream");
        C0870a.m3039a(i, "Buffer size");
        C0870a.m3042a(gVar, "HTTP parameters");
        this.f2289a = inputStream;
        this.f2290b = new byte[i];
        this.f2299k = 0;
        this.f2300l = 0;
        this.f2291c = new C0872c(i);
        String str = (String) gVar.getParameter("http.protocol.element-charset");
        this.f2292d = str != null ? Charset.forName(str) : C0570c.f1866b;
        this.f2293e = this.f2292d.equals(C0570c.f1866b);
        this.f2301m = null;
        this.f2294f = gVar.getIntParameter("http.connection.max-line-length", -1);
        this.f2295g = gVar.getIntParameter("http.connection.min-chunk-limit", 512);
        this.f2296h = mo3062b();
        CodingErrorAction codingErrorAction = (CodingErrorAction) gVar.getParameter("http.malformed.input.action");
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.f2297i = codingErrorAction;
        CodingErrorAction codingErrorAction2 = (CodingErrorAction) gVar.getParameter("http.unmappable.input.action");
        if (codingErrorAction2 == null) {
            codingErrorAction2 = CodingErrorAction.REPORT;
        }
        this.f2298j = codingErrorAction2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0799k mo3062b() {
        return new C0799k();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo3063c() {
        int i = this.f2299k;
        if (i > 0) {
            int i2 = this.f2300l - i;
            if (i2 > 0) {
                byte[] bArr = this.f2290b;
                System.arraycopy(bArr, i, bArr, 0, i2);
            }
            this.f2299k = 0;
            this.f2300l = i2;
        }
        int i3 = this.f2300l;
        byte[] bArr2 = this.f2290b;
        int read = this.f2289a.read(bArr2, i3, bArr2.length - i3);
        if (read == -1) {
            return -1;
        }
        this.f2300l = i3 + read;
        this.f2296h.mo3105a((long) read);
        return read;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public boolean mo3064d() {
        return this.f2299k < this.f2300l;
    }

    public C0809e getMetrics() {
        return this.f2296h;
    }

    public int length() {
        return this.f2300l - this.f2299k;
    }

    public int read() {
        while (!mo3064d()) {
            if (mo3063c() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.f2290b;
        int i = this.f2299k;
        this.f2299k = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr, int i, int i2) {
        int min;
        if (bArr == null) {
            return 0;
        }
        if (mo3064d()) {
            min = Math.min(i2, this.f2300l - this.f2299k);
            System.arraycopy(this.f2290b, this.f2299k, bArr, i, min);
        } else if (i2 > this.f2295g) {
            int read = this.f2289a.read(bArr, i, i2);
            if (read > 0) {
                this.f2296h.mo3105a((long) read);
            }
            return read;
        } else {
            while (!mo3064d()) {
                if (mo3063c() == -1) {
                    return -1;
                }
            }
            min = Math.min(i2, this.f2300l - this.f2299k);
            System.arraycopy(this.f2290b, this.f2299k, bArr, i, min);
        }
        this.f2299k += min;
        return min;
    }
}
