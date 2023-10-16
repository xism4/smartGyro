package p036c.p037a.p038a.p039a.p060i.p066e;

import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0880q;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p058g.C0660d;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.e.d */
public class C0787d implements C0660d {

    /* renamed from: a */
    public static final C0787d f2276a = new C0787d();

    /* renamed from: b */
    private final int f2277b;

    public C0787d() {
        this(-1);
    }

    public C0787d(int i) {
        this.f2277b = i;
    }

    /* renamed from: a */
    public long mo2795a(C0880q qVar) {
        C0870a.m3042a(qVar, "HTTP message");
        C0576e firstHeader = qVar.getFirstHeader("Transfer-Encoding");
        if (firstHeader != null) {
            String value = firstHeader.getValue();
            if ("chunked".equalsIgnoreCase(value)) {
                if (!qVar.getProtocolVersion().mo2445c(C0886w.f2445e)) {
                    return -2;
                }
                throw new C0487C("Chunked transfer encoding not allowed for " + qVar.getProtocolVersion());
            } else if ("identity".equalsIgnoreCase(value)) {
                return -1;
            } else {
                throw new C0487C("Unsupported transfer encoding: " + value);
            }
        } else {
            C0576e firstHeader2 = qVar.getFirstHeader("Content-Length");
            if (firstHeader2 == null) {
                return (long) this.f2277b;
            }
            String value2 = firstHeader2.getValue();
            try {
                long parseLong = Long.parseLong(value2);
                if (parseLong >= 0) {
                    return parseLong;
                }
                throw new C0487C("Negative content length: " + value2);
            } catch (NumberFormatException unused) {
                throw new C0487C("Invalid content length: " + value2);
            }
        }
    }
}
