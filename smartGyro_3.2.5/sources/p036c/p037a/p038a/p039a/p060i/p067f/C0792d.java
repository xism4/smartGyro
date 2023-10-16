package p036c.p037a.p038a.p039a.p060i.p067f;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.p068j.C0805a;
import p036c.p037a.p038a.p039a.p068j.C0809e;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0872c;
import p036c.p037a.p038a.p039a.p074p.C0873d;

@Deprecated
/* renamed from: c.a.a.a.i.f.d */
public abstract class C0792d implements C0811g, C0805a {

    /* renamed from: a */
    private static final byte[] f2303a = {13, 10};

    /* renamed from: b */
    private OutputStream f2304b;

    /* renamed from: c */
    private C0872c f2305c;

    /* renamed from: d */
    private Charset f2306d;

    /* renamed from: e */
    private boolean f2307e;

    /* renamed from: f */
    private int f2308f;

    /* renamed from: g */
    private C0799k f2309g;

    /* renamed from: h */
    private CodingErrorAction f2310h;

    /* renamed from: i */
    private CodingErrorAction f2311i;

    /* renamed from: j */
    private CharsetEncoder f2312j;

    /* renamed from: k */
    private ByteBuffer f2313k;

    /* renamed from: a */
    private void m2870a(CharBuffer charBuffer) {
        if (charBuffer.hasRemaining()) {
            if (this.f2312j == null) {
                this.f2312j = this.f2306d.newEncoder();
                this.f2312j.onMalformedInput(this.f2310h);
                this.f2312j.onUnmappableCharacter(this.f2311i);
            }
            if (this.f2313k == null) {
                this.f2313k = ByteBuffer.allocate(1024);
            }
            this.f2312j.reset();
            while (charBuffer.hasRemaining()) {
                m2871a(this.f2312j.encode(charBuffer, this.f2313k, true));
            }
            m2871a(this.f2312j.flush(this.f2313k));
            this.f2313k.clear();
        }
    }

    /* renamed from: a */
    private void m2871a(CoderResult coderResult) {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.f2313k.flip();
        while (this.f2313k.hasRemaining()) {
            write(this.f2313k.get());
        }
        this.f2313k.compact();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0799k mo3066a() {
        return new C0799k();
    }

    /* renamed from: a */
    public void mo3018a(C0873d dVar) {
        if (dVar != null) {
            int i = 0;
            if (this.f2307e) {
                int length = dVar.length();
                while (length > 0) {
                    int min = Math.min(this.f2305c.mo3286b() - this.f2305c.mo3291f(), length);
                    if (min > 0) {
                        this.f2305c.mo3282a(dVar, i, min);
                    }
                    if (this.f2305c.mo3290e()) {
                        mo3069b();
                    }
                    i += min;
                    length -= min;
                }
            } else {
                m2870a(CharBuffer.wrap(dVar.mo3301a(), 0, dVar.length()));
            }
            mo3068a(f2303a);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3067a(OutputStream outputStream, int i, C0844g gVar) {
        C0870a.m3042a(outputStream, "Input stream");
        C0870a.m3039a(i, "Buffer size");
        C0870a.m3042a(gVar, "HTTP parameters");
        this.f2304b = outputStream;
        this.f2305c = new C0872c(i);
        String str = (String) gVar.getParameter("http.protocol.element-charset");
        this.f2306d = str != null ? Charset.forName(str) : C0570c.f1866b;
        this.f2307e = this.f2306d.equals(C0570c.f1866b);
        this.f2312j = null;
        this.f2308f = gVar.getIntParameter("http.connection.min-chunk-limit", 512);
        this.f2309g = mo3066a();
        CodingErrorAction codingErrorAction = (CodingErrorAction) gVar.getParameter("http.malformed.input.action");
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.f2310h = codingErrorAction;
        CodingErrorAction codingErrorAction2 = (CodingErrorAction) gVar.getParameter("http.unmappable.input.action");
        if (codingErrorAction2 == null) {
            codingErrorAction2 = CodingErrorAction.REPORT;
        }
        this.f2311i = codingErrorAction2;
    }

    /* renamed from: a */
    public void mo3068a(byte[] bArr) {
        if (bArr != null) {
            write(bArr, 0, bArr.length);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo3069b() {
        int f = this.f2305c.mo3291f();
        if (f > 0) {
            this.f2304b.write(this.f2305c.mo3285a(), 0, f);
            this.f2305c.mo3288c();
            this.f2309g.mo3105a((long) f);
        }
    }

    public void flush() {
        mo3069b();
        this.f2304b.flush();
    }

    public C0809e getMetrics() {
        return this.f2309g;
    }

    public int length() {
        return this.f2305c.mo3291f();
    }

    public void write(int i) {
        if (this.f2305c.mo3290e()) {
            mo3069b();
        }
        this.f2305c.mo3281a(i);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i2 > this.f2308f || i2 > this.f2305c.mo3286b()) {
                mo3069b();
                this.f2304b.write(bArr, i, i2);
                this.f2309g.mo3105a((long) i2);
                return;
            }
            if (i2 > this.f2305c.mo3286b() - this.f2305c.mo3291f()) {
                mo3069b();
            }
            this.f2305c.mo3283a(bArr, i, i2);
        }
    }

    public void writeLine(String str) {
        if (str != null) {
            if (str.length() > 0) {
                if (this.f2307e) {
                    for (int i = 0; i < str.length(); i++) {
                        write(str.charAt(i));
                    }
                } else {
                    m2870a(CharBuffer.wrap(str));
                }
            }
            mo3068a(f2303a);
        }
    }
}
