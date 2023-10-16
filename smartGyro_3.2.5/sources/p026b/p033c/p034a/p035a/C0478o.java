package p026b.p033c.p034a.p035a;

import java.io.FileOutputStream;
import java.io.InputStream;
import p036c.p037a.p038a.p039a.C0491G;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p041b.C0563l;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;

/* renamed from: b.c.a.a.o */
public abstract class C0478o extends C0471h {

    /* renamed from: m */
    private long f1731m;

    /* renamed from: n */
    private boolean f1732n;

    /* renamed from: a */
    public void mo2430a(C0532l lVar) {
        if (this.f1722i.exists() && this.f1722i.canWrite()) {
            this.f1731m = this.f1722i.length();
        }
        if (this.f1731m > 0) {
            this.f1732n = true;
            lVar.setHeader("Range", "bytes=" + this.f1731m + "-");
        }
    }

    /* renamed from: a */
    public void mo2383a(C0883t tVar) {
        if (!Thread.currentThread().isInterrupted()) {
            C0491G statusLine = tVar.getStatusLine();
            if (statusLine.getStatusCode() == 416) {
                if (!Thread.currentThread().isInterrupted()) {
                    mo2391b(statusLine.getStatusCode(), tVar.getAllHeaders(), (byte[]) null);
                }
            } else if (statusLine.getStatusCode() >= 300) {
                if (!Thread.currentThread().isInterrupted()) {
                    mo2379a(statusLine.getStatusCode(), tVar.getAllHeaders(), (byte[]) null, new C0563l(statusLine.getStatusCode(), statusLine.getReasonPhrase()));
                }
            } else if (!Thread.currentThread().isInterrupted()) {
                C0576e firstHeader = tVar.getFirstHeader("Content-Range");
                if (firstHeader == null) {
                    this.f1732n = false;
                    this.f1731m = 0;
                } else {
                    C0474k kVar = C0465d.f1690a;
                    kVar.mo2421c("RangeFileAsyncHttpRH", "Content-Range: " + firstHeader.getValue());
                }
                mo2391b(statusLine.getStatusCode(), tVar.getAllHeaders(), mo2389a(tVar.getEntity()));
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public byte[] mo2389a(C0837l lVar) {
        int read;
        if (lVar == null) {
            return null;
        }
        InputStream content = lVar.getContent();
        long contentLength = lVar.getContentLength() + this.f1731m;
        FileOutputStream fileOutputStream = new FileOutputStream(mo2410k(), this.f1732n);
        if (content == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (this.f1731m < contentLength && (read = content.read(bArr)) != -1 && !Thread.currentThread().isInterrupted()) {
                this.f1731m += (long) read;
                fileOutputStream.write(bArr, 0, read);
                mo2393b(this.f1731m, contentLength);
            }
            return null;
        } finally {
            content.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }
}
