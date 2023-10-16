package p026b.p033c.p034a.p035a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0837l;

/* renamed from: b.c.a.a.h */
public abstract class C0471h extends C0468f {

    /* renamed from: i */
    protected final File f1722i;

    /* renamed from: j */
    protected final boolean f1723j;

    /* renamed from: k */
    protected final boolean f1724k;

    /* renamed from: l */
    protected File f1725l;

    public C0471h(File file, boolean z) {
        this(file, z, false);
    }

    public C0471h(File file, boolean z, boolean z2) {
        this(file, z, z2, false);
    }

    public C0471h(File file, boolean z, boolean z2, boolean z3) {
        super(z3);
        C0484u.m2111a(file != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        if (!file.isDirectory() && !file.getParentFile().isDirectory()) {
            C0484u.m2111a(file.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
        }
        if (file.isDirectory() && !file.mkdirs()) {
            C0465d.f1690a.mo2422d("FileAsyncHttpRH", "Cannot create directories for requested Directory location, might not be a problem");
        }
        this.f1722i = file;
        this.f1723j = z;
        this.f1724k = z2;
    }

    /* renamed from: a */
    public abstract void mo2407a(int i, C0576e[] eVarArr, File file);

    /* renamed from: a */
    public abstract void mo2408a(int i, C0576e[] eVarArr, Throwable th, File file);

    /* renamed from: a */
    public final void mo2378a(int i, C0576e[] eVarArr, byte[] bArr) {
        mo2407a(i, eVarArr, mo2410k());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public byte[] mo2389a(C0837l lVar) {
        if (lVar == null) {
            return null;
        }
        InputStream content = lVar.getContent();
        long contentLength = lVar.getContentLength();
        FileOutputStream fileOutputStream = new FileOutputStream(mo2410k(), this.f1723j);
        if (content == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            int i = 0;
            while (true) {
                int read = content.read(bArr);
                if (read != -1 && !Thread.currentThread().isInterrupted()) {
                    i += read;
                    fileOutputStream.write(bArr, 0, read);
                    mo2393b((long) i, contentLength);
                }
            }
            return null;
        } finally {
            C0465d.m2002a(content);
            fileOutputStream.flush();
            C0465d.m2003a((OutputStream) fileOutputStream);
        }
    }

    /* renamed from: b */
    public final void mo2392b(int i, C0576e[] eVarArr, byte[] bArr, Throwable th) {
        mo2408a(i, eVarArr, th, mo2410k());
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public File mo2409j() {
        C0484u.m2111a(this.f1722i != null, "Target file is null, fatal!");
        return this.f1722i;
    }

    /* renamed from: k */
    public File mo2410k() {
        if (this.f1725l == null) {
            this.f1725l = mo2409j().isDirectory() ? mo2411l() : mo2409j();
        }
        return this.f1725l;
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public File mo2411l() {
        StringBuilder sb;
        C0484u.m2111a(mo2409j().isDirectory(), "Target file is not a directory, cannot proceed");
        C0484u.m2111a(mo2401f() != null, "RequestURI is null, cannot proceed");
        String uri = mo2401f().toString();
        String substring = uri.substring(uri.lastIndexOf(47) + 1, uri.length());
        File file = new File(mo2409j(), substring);
        if (!file.exists() || !this.f1724k) {
            return file;
        }
        if (!substring.contains(".")) {
            sb = new StringBuilder();
            sb.append(substring);
            sb.append(" (%d)");
        } else {
            sb = new StringBuilder();
            sb.append(substring.substring(0, substring.lastIndexOf(46)));
            sb.append(" (%d)");
            sb.append(substring.substring(substring.lastIndexOf(46), substring.length()));
        }
        String sb2 = sb.toString();
        int i = 0;
        while (true) {
            File file2 = new File(mo2409j(), String.format(sb2, new Object[]{Integer.valueOf(i)}));
            if (!file2.exists()) {
                return file2;
            }
            i++;
        }
    }
}
