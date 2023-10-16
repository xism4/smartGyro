package p036c.p037a.p038a.p039a.p060i.p067f;

import java.io.IOException;
import java.io.InputStream;
import p036c.p037a.p038a.p039a.C0493I;
import p036c.p037a.p038a.p039a.C0494a;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0850n;
import p036c.p037a.p038a.p039a.C0887x;
import p036c.p037a.p038a.p039a.p049d.C0574b;
import p036c.p037a.p038a.p039a.p068j.C0805a;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p069k.C0834v;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.f.e */
public class C0793e extends InputStream {

    /* renamed from: a */
    private final C0810f f2314a;

    /* renamed from: b */
    private final C0873d f2315b;

    /* renamed from: c */
    private final C0574b f2316c;

    /* renamed from: d */
    private int f2317d;

    /* renamed from: e */
    private int f2318e;

    /* renamed from: f */
    private int f2319f;

    /* renamed from: g */
    private boolean f2320g;

    /* renamed from: h */
    private boolean f2321h;

    /* renamed from: i */
    private C0576e[] f2322i;

    public C0793e(C0810f fVar) {
        this(fVar, (C0574b) null);
    }

    public C0793e(C0810f fVar, C0574b bVar) {
        this.f2320g = false;
        this.f2321h = false;
        this.f2322i = new C0576e[0];
        C0870a.m3042a(fVar, "Session input buffer");
        this.f2314a = fVar;
        this.f2319f = 0;
        this.f2315b = new C0873d(16);
        this.f2316c = bVar == null ? C0574b.f1868a : bVar;
        this.f2317d = 1;
    }

    /* renamed from: a */
    private int m2877a() {
        int i = this.f2317d;
        if (i != 1) {
            if (i == 3) {
                this.f2315b.clear();
                if (this.f2314a.mo3012a(this.f2315b) == -1) {
                    throw new C0887x("CRLF expected at end of chunk");
                } else if (this.f2315b.mo3305b()) {
                    this.f2317d = 1;
                } else {
                    throw new C0887x("Unexpected content at the end of chunk");
                }
            } else {
                throw new IllegalStateException("Inconsistent codec state");
            }
        }
        this.f2315b.clear();
        if (this.f2314a.mo3012a(this.f2315b) != -1) {
            int b = this.f2315b.mo3303b(59);
            if (b < 0) {
                b = this.f2315b.length();
            }
            try {
                return Integer.parseInt(this.f2315b.mo3304b(0, b), 16);
            } catch (NumberFormatException unused) {
                throw new C0887x("Bad chunk header");
            }
        } else {
            throw new C0494a("Premature end of chunk coded message body: closing chunk expected");
        }
    }

    /* renamed from: b */
    private void m2878b() {
        if (this.f2317d != Integer.MAX_VALUE) {
            try {
                this.f2318e = m2877a();
                if (this.f2318e >= 0) {
                    this.f2317d = 2;
                    this.f2319f = 0;
                    if (this.f2318e == 0) {
                        this.f2320g = true;
                        m2879c();
                        return;
                    }
                    return;
                }
                throw new C0887x("Negative chunk size");
            } catch (C0887x e) {
                this.f2317d = Integer.MAX_VALUE;
                throw e;
            }
        } else {
            throw new C0887x("Corrupt data stream");
        }
    }

    /* renamed from: c */
    private void m2879c() {
        try {
            this.f2322i = C0789a.m2855a(this.f2314a, this.f2316c.mo2605b(), this.f2316c.mo2606c(), (C0834v) null);
        } catch (C0850n e) {
            C0887x xVar = new C0887x("Invalid footer: " + e.getMessage());
            xVar.initCause(e);
            throw xVar;
        }
    }

    public int available() {
        C0810f fVar = this.f2314a;
        if (fVar instanceof C0805a) {
            return Math.min(((C0805a) fVar).length(), this.f2318e - this.f2319f);
        }
        return 0;
    }

    public void close() {
        if (!this.f2321h) {
            try {
                if (!this.f2320g && this.f2317d != Integer.MAX_VALUE) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
            } finally {
                this.f2320g = true;
                this.f2321h = true;
            }
        }
    }

    public int read() {
        if (this.f2321h) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f2320g) {
            return -1;
        } else {
            if (this.f2317d != 2) {
                m2878b();
                if (this.f2320g) {
                    return -1;
                }
            }
            int read = this.f2314a.read();
            if (read != -1) {
                this.f2319f++;
                if (this.f2319f >= this.f2318e) {
                    this.f2317d = 3;
                }
            }
            return read;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.f2321h) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f2320g) {
            return -1;
        } else {
            if (this.f2317d != 2) {
                m2878b();
                if (this.f2320g) {
                    return -1;
                }
            }
            int read = this.f2314a.read(bArr, i, Math.min(i2, this.f2318e - this.f2319f));
            if (read != -1) {
                this.f2319f += read;
                if (this.f2319f >= this.f2318e) {
                    this.f2317d = 3;
                }
                return read;
            }
            this.f2320g = true;
            throw new C0493I("Truncated chunk ( expected size: " + this.f2318e + "; actual size: " + this.f2319f + ")");
        }
    }
}
