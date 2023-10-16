package p036c.p037a.p038a.p039a.p050e;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.p058g.C0662f;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0876g;

@Deprecated
/* renamed from: c.a.a.a.e.a */
public class C0577a extends C0662f implements C0628i, C0631l {

    /* renamed from: b */
    protected C0634o f1873b;

    /* renamed from: c */
    protected final boolean f1874c;

    public C0577a(C0837l lVar, C0634o oVar, boolean z) {
        super(lVar);
        C0870a.m3042a(oVar, "Connection");
        this.f1873b = oVar;
        this.f1874c = z;
    }

    /* renamed from: b */
    private void m2287b() {
        C0634o oVar = this.f1873b;
        if (oVar != null) {
            try {
                if (this.f1874c) {
                    C0876g.m3080a(this.f1975a);
                    this.f1873b.markReusable();
                } else {
                    oVar.unmarkReusable();
                }
            } finally {
                mo2615a();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2615a() {
        C0634o oVar = this.f1873b;
        if (oVar != null) {
            try {
                oVar.releaseConnection();
            } finally {
                this.f1873b = null;
            }
        }
    }

    public void abortConnection() {
        C0634o oVar = this.f1873b;
        if (oVar != null) {
            try {
                oVar.abortConnection();
            } finally {
                this.f1873b = null;
            }
        }
    }

    @Deprecated
    public void consumeContent() {
        m2287b();
    }

    /* JADX INFO: finally extract failed */
    public boolean eofDetected(InputStream inputStream) {
        try {
            if (this.f1873b != null) {
                if (this.f1874c) {
                    inputStream.close();
                    this.f1873b.markReusable();
                } else {
                    this.f1873b.unmarkReusable();
                }
            }
            mo2615a();
            return false;
        } catch (Throwable th) {
            mo2615a();
            throw th;
        }
    }

    public InputStream getContent() {
        return new C0630k(this.f1975a.getContent(), this);
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean streamAbort(InputStream inputStream) {
        C0634o oVar = this.f1873b;
        if (oVar == null) {
            return false;
        }
        oVar.abortConnection();
        return false;
    }

    public boolean streamClosed(InputStream inputStream) {
        boolean isOpen;
        try {
            if (this.f1873b != null) {
                if (this.f1874c) {
                    isOpen = this.f1873b.isOpen();
                    inputStream.close();
                    this.f1873b.markReusable();
                } else {
                    this.f1873b.unmarkReusable();
                }
            }
        } catch (SocketException e) {
            if (isOpen) {
                throw e;
            }
        } catch (Throwable th) {
            mo2615a();
            throw th;
        }
        mo2615a();
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        super.writeTo(outputStream);
        m2287b();
    }
}
