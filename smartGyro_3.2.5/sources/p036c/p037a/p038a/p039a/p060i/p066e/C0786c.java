package p036c.p037a.p038a.p039a.p060i.p066e;

import p036c.p037a.p038a.p039a.C0486B;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0880q;
import p036c.p037a.p038a.p039a.p058g.C0660d;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.e.c */
public class C0786c implements C0660d {

    /* renamed from: a */
    public static final C0786c f2274a = new C0786c();

    /* renamed from: b */
    private final int f2275b;

    public C0786c() {
        this(-1);
    }

    public C0786c(int i) {
        this.f2275b = i;
    }

    /* renamed from: a */
    public long mo2795a(C0880q qVar) {
        long j;
        C0870a.m3042a(qVar, "HTTP message");
        C0576e firstHeader = qVar.getFirstHeader("Transfer-Encoding");
        if (firstHeader != null) {
            try {
                C0639f[] elements = firstHeader.getElements();
                int length = elements.length;
                return (!"identity".equalsIgnoreCase(firstHeader.getValue()) && length > 0 && "chunked".equalsIgnoreCase(elements[length + -1].getName())) ? -2 : -1;
            } catch (C0486B e) {
                throw new C0487C("Invalid Transfer-Encoding header value: " + firstHeader, e);
            }
        } else if (qVar.getFirstHeader("Content-Length") == null) {
            return (long) this.f2275b;
        } else {
            C0576e[] headers = qVar.getHeaders("Content-Length");
            int length2 = headers.length - 1;
            while (true) {
                if (length2 < 0) {
                    j = -1;
                    break;
                }
                try {
                    j = Long.parseLong(headers[length2].getValue());
                    break;
                } catch (NumberFormatException unused) {
                    length2--;
                }
            }
            if (j >= 0) {
                return j;
            }
            return -1;
        }
    }
}
