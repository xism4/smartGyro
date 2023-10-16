package p036c.p037a.p038a.p039a.p058g;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import p036c.p037a.p038a.p039a.p072n.C0854d;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.g.g */
public class C0663g extends C0657a implements Cloneable {

    /* renamed from: d */
    protected final byte[] f1976d;

    public C0663g(String str, C0661e eVar) {
        C0870a.m3042a(str, "Source string");
        Charset a = eVar != null ? eVar.mo2796a() : null;
        this.f1976d = str.getBytes(a == null ? C0854d.f2421a : a);
        if (eVar != null) {
            mo2785a(eVar.toString());
        }
    }

    public Object clone() {
        return super.clone();
    }

    public InputStream getContent() {
        return new ByteArrayInputStream(this.f1976d);
    }

    public long getContentLength() {
        return (long) this.f1976d.length;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        C0870a.m3042a(outputStream, "Output stream");
        outputStream.write(this.f1976d);
        outputStream.flush();
    }
}
