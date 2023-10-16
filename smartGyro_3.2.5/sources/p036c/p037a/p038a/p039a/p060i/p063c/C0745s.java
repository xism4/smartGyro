package p036c.p037a.p038a.p039a.p060i.p063c;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.c.s */
public class C0745s {

    /* renamed from: a */
    public C0668b f2231a;

    /* renamed from: b */
    private final String f2232b;

    public C0745s(C0668b bVar) {
        this(bVar, "");
    }

    public C0745s(C0668b bVar, String str) {
        this.f2231a = bVar;
        this.f2232b = str;
    }

    /* renamed from: a */
    private void m2731a(String str, InputStream inputStream) {
        String str2;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            }
            if (read == 13) {
                str2 = "[\\r]";
            } else if (read == 10) {
                sb.append("[\\n]\"");
                sb.insert(0, "\"");
                sb.insert(0, str);
                C0668b bVar = this.f2231a;
                bVar.mo2803a(this.f2232b + " " + sb.toString());
                sb.setLength(0);
            } else if (read < 32 || read > 127) {
                sb.append("[0x");
                sb.append(Integer.toHexString(read));
                str2 = "]";
            } else {
                sb.append((char) read);
            }
            sb.append(str2);
        }
        if (sb.length() > 0) {
            sb.append('\"');
            sb.insert(0, '\"');
            sb.insert(0, str);
            C0668b bVar2 = this.f2231a;
            bVar2.mo2803a(this.f2232b + " " + sb.toString());
        }
    }

    /* renamed from: a */
    public void mo3028a(int i) {
        mo3029a(new byte[]{(byte) i});
    }

    /* renamed from: a */
    public void mo3029a(byte[] bArr) {
        C0870a.m3042a(bArr, "Input");
        m2731a("<< ", new ByteArrayInputStream(bArr));
    }

    /* renamed from: a */
    public void mo3030a(byte[] bArr, int i, int i2) {
        C0870a.m3042a(bArr, "Input");
        m2731a("<< ", new ByteArrayInputStream(bArr, i, i2));
    }

    /* renamed from: a */
    public boolean mo3031a() {
        return this.f2231a.mo2805a();
    }

    /* renamed from: b */
    public void mo3032b(int i) {
        mo3033b(new byte[]{(byte) i});
    }

    /* renamed from: b */
    public void mo3033b(byte[] bArr) {
        C0870a.m3042a(bArr, "Output");
        m2731a(">> ", new ByteArrayInputStream(bArr));
    }

    /* renamed from: b */
    public void mo3034b(byte[] bArr, int i, int i2) {
        C0870a.m3042a(bArr, "Output");
        m2731a(">> ", new ByteArrayInputStream(bArr, i, i2));
    }
}
