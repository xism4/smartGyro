package p036c.p037a.p038a.p039a.p060i.p067f;

import java.io.IOException;
import java.io.InputStream;
import p036c.p037a.p038a.p039a.C0494a;
import p036c.p037a.p038a.p039a.p068j.C0805a;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.f.g */
public class C0795g extends InputStream {

    /* renamed from: a */
    private final long f2328a;

    /* renamed from: b */
    private long f2329b = 0;

    /* renamed from: c */
    private boolean f2330c = false;

    /* renamed from: d */
    private C0810f f2331d = null;

    public C0795g(C0810f fVar, long j) {
        C0870a.m3042a(fVar, "Session input buffer");
        this.f2331d = fVar;
        C0870a.m3040a(j, "Content length");
        this.f2328a = j;
    }

    public int available() {
        C0810f fVar = this.f2331d;
        if (fVar instanceof C0805a) {
            return Math.min(((C0805a) fVar).length(), (int) (this.f2328a - this.f2329b));
        }
        return 0;
    }

    public void close() {
        if (!this.f2330c) {
            try {
                if (this.f2329b < this.f2328a) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
            } finally {
                this.f2330c = true;
            }
        }
    }

    public int read() {
        if (this.f2330c) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f2329b >= this.f2328a) {
            return -1;
        } else {
            int read = this.f2331d.read();
            if (read != -1) {
                this.f2329b++;
            } else if (this.f2329b < this.f2328a) {
                throw new C0494a("Premature end of Content-Length delimited message body (expected: " + this.f2328a + "; received: " + this.f2329b);
            }
            return read;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (!this.f2330c) {
            long j = this.f2329b;
            long j2 = this.f2328a;
            if (j >= j2) {
                return -1;
            }
            if (((long) i2) + j > j2) {
                i2 = (int) (j2 - j);
            }
            int read = this.f2331d.read(bArr, i, i2);
            if (read != -1 || this.f2329b >= this.f2328a) {
                if (read > 0) {
                    this.f2329b += (long) read;
                }
                return read;
            }
            throw new C0494a("Premature end of Content-Length delimited message body (expected: " + this.f2328a + "; received: " + this.f2329b);
        }
        throw new IOException("Attempted read from closed stream.");
    }

    public long skip(long j) {
        int read;
        if (j <= 0) {
            return 0;
        }
        byte[] bArr = new byte[2048];
        long min = Math.min(j, this.f2328a - this.f2329b);
        long j2 = 0;
        while (min > 0 && (read = read(bArr, 0, (int) Math.min(2048, min))) != -1) {
            long j3 = (long) read;
            j2 += j3;
            min -= j3;
        }
        return j2;
    }
}
