package p036c.p037a.p038a.p039a.p060i.p067f;

import java.io.InputStream;
import p036c.p037a.p038a.p039a.p068j.C0805a;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.f.l */
public class C0800l extends InputStream {

    /* renamed from: a */
    private final C0810f f2338a;

    /* renamed from: b */
    private boolean f2339b = false;

    public C0800l(C0810f fVar) {
        C0870a.m3042a(fVar, "Session input buffer");
        this.f2338a = fVar;
    }

    public int available() {
        C0810f fVar = this.f2338a;
        if (fVar instanceof C0805a) {
            return ((C0805a) fVar).length();
        }
        return 0;
    }

    public void close() {
        this.f2339b = true;
    }

    public int read() {
        if (this.f2339b) {
            return -1;
        }
        return this.f2338a.read();
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.f2339b) {
            return -1;
        }
        return this.f2338a.read(bArr, i, i2);
    }
}
