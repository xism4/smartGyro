package p036c.p037a.p038a.p039a.p058g;

import java.io.InputStream;
import java.io.OutputStream;
import p036c.p037a.p038a.p039a.p060i.p067f.C0797i;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

/* renamed from: c.a.a.a.g.b */
public class C0658b extends C0657a {

    /* renamed from: d */
    private InputStream f1955d;

    /* renamed from: e */
    private long f1956e = -1;

    /* renamed from: a */
    public void mo2792a(long j) {
        this.f1956e = j;
    }

    /* renamed from: a */
    public void mo2793a(InputStream inputStream) {
        this.f1955d = inputStream;
    }

    public InputStream getContent() {
        C0871b.m3050a(this.f1955d != null, "Content has not been provided");
        return this.f1955d;
    }

    public long getContentLength() {
        return this.f1956e;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        InputStream inputStream = this.f1955d;
        return (inputStream == null || inputStream == C0797i.f2336a) ? false : true;
    }

    public void writeTo(OutputStream outputStream) {
        C0870a.m3042a(outputStream, "Output stream");
        InputStream content = getContent();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = content.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        } finally {
            content.close();
        }
    }
}
