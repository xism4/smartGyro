package p036c.p037a.p038a.p039a.p050e;

import java.io.IOException;
import java.io.InputStream;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.e.k */
public class C0630k extends InputStream implements C0628i {

    /* renamed from: a */
    protected InputStream f1940a;

    /* renamed from: b */
    private boolean f1941b = false;

    /* renamed from: c */
    private final C0631l f1942c;

    public C0630k(InputStream inputStream, C0631l lVar) {
        C0870a.m3042a(inputStream, "Wrapped stream");
        this.f1940a = inputStream;
        this.f1942c = lVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2707a() {
        InputStream inputStream = this.f1940a;
        if (inputStream != null) {
            boolean z = true;
            try {
                if (this.f1942c != null) {
                    z = this.f1942c.streamAbort(inputStream);
                }
                if (z) {
                    this.f1940a.close();
                }
            } finally {
                this.f1940a = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2708a(int i) {
        InputStream inputStream = this.f1940a;
        if (inputStream != null && i < 0) {
            boolean z = true;
            try {
                if (this.f1942c != null) {
                    z = this.f1942c.eofDetected(inputStream);
                }
                if (z) {
                    this.f1940a.close();
                }
            } finally {
                this.f1940a = null;
            }
        }
    }

    public void abortConnection() {
        this.f1941b = true;
        mo2707a();
    }

    public int available() {
        if (!mo2711c()) {
            return 0;
        }
        try {
            return this.f1940a.available();
        } catch (IOException e) {
            mo2707a();
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo2710b() {
        InputStream inputStream = this.f1940a;
        if (inputStream != null) {
            boolean z = true;
            try {
                if (this.f1942c != null) {
                    z = this.f1942c.streamClosed(inputStream);
                }
                if (z) {
                    this.f1940a.close();
                }
            } finally {
                this.f1940a = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo2711c() {
        if (!this.f1941b) {
            return this.f1940a != null;
        }
        throw new IOException("Attempted read on closed stream.");
    }

    public void close() {
        this.f1941b = true;
        mo2710b();
    }

    public int read() {
        if (!mo2711c()) {
            return -1;
        }
        try {
            int read = this.f1940a.read();
            mo2708a(read);
            return read;
        } catch (IOException e) {
            mo2707a();
            throw e;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (!mo2711c()) {
            return -1;
        }
        try {
            int read = this.f1940a.read(bArr, i, i2);
            mo2708a(read);
            return read;
        } catch (IOException e) {
            mo2707a();
            throw e;
        }
    }
}
