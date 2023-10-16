package p036c.p037a.p038a.p039a.p069k;

import p036c.p037a.p038a.p039a.C0486B;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0491G;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p072n.C0854d;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.k */
public class C0823k implements C0834v {
    @Deprecated

    /* renamed from: a */
    public static final C0823k f2378a = new C0823k();

    /* renamed from: b */
    public static final C0823k f2379b = new C0823k();

    /* renamed from: c */
    protected final C0488D f2380c;

    public C0823k() {
        this((C0488D) null);
    }

    public C0823k(C0488D d) {
        this.f2380c = d == null ? C0886w.f2446f : d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0488D mo3171a(int i, int i2) {
        return this.f2380c.mo2441a(i, i2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0491G mo3172a(C0488D d, int i, String str) {
        return new C0827o(d, i, str);
    }

    /* renamed from: a */
    public C0491G mo3173a(C0873d dVar, C0835w wVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0870a.m3042a(wVar, "Parser cursor");
        int b = wVar.mo3220b();
        int c = wVar.mo3221c();
        try {
            C0488D c2 = mo3176c(dVar, wVar);
            mo3177d(dVar, wVar);
            int b2 = wVar.mo3220b();
            int a = dVar.mo3293a(32, b2, c);
            if (a < 0) {
                a = c;
            }
            String b3 = dVar.mo3304b(b2, a);
            int i = 0;
            while (i < b3.length()) {
                if (Character.isDigit(b3.charAt(i))) {
                    i++;
                } else {
                    throw new C0486B("Status line contains invalid status code: " + dVar.mo3294a(b, c));
                }
            }
            return mo3172a(c2, Integer.parseInt(b3), a < c ? dVar.mo3304b(a, c) : "");
        } catch (NumberFormatException unused) {
            throw new C0486B("Status line contains invalid status code: " + dVar.mo3294a(b, c));
        } catch (IndexOutOfBoundsException unused2) {
            throw new C0486B("Invalid status line: " + dVar.mo3294a(b, c));
        }
    }

    /* renamed from: a */
    public C0576e mo3174a(C0873d dVar) {
        return new C0829q(dVar);
    }

    /* renamed from: b */
    public boolean mo3175b(C0873d dVar, C0835w wVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0870a.m3042a(wVar, "Parser cursor");
        int b = wVar.mo3220b();
        String c = this.f2380c.mo2444c();
        int length = c.length();
        if (dVar.length() < length + 4) {
            return false;
        }
        if (b < 0) {
            b = (dVar.length() - 4) - length;
        } else if (b == 0) {
            while (b < dVar.length() && C0854d.m3012a(dVar.charAt(b))) {
                b++;
            }
        }
        int i = b + length;
        if (i + 4 > dVar.length()) {
            return false;
        }
        boolean z = true;
        int i2 = 0;
        while (z && i2 < length) {
            z = dVar.charAt(b + i2) == c.charAt(i2);
            i2++;
        }
        return z ? dVar.charAt(i) == '/' : z;
    }

    /* renamed from: c */
    public C0488D mo3176c(C0873d dVar, C0835w wVar) {
        C0870a.m3042a(dVar, "Char array buffer");
        C0870a.m3042a(wVar, "Parser cursor");
        String c = this.f2380c.mo2444c();
        int length = c.length();
        int b = wVar.mo3220b();
        int c2 = wVar.mo3221c();
        mo3177d(dVar, wVar);
        int b2 = wVar.mo3220b();
        int i = b2 + length;
        if (i + 4 <= c2) {
            boolean z = true;
            int i2 = 0;
            while (z && i2 < length) {
                z = dVar.charAt(b2 + i2) == c.charAt(i2);
                i2++;
            }
            if (z) {
                z = dVar.charAt(i) == '/';
            }
            if (z) {
                int i3 = b2 + length + 1;
                int a = dVar.mo3293a(46, i3, c2);
                if (a != -1) {
                    try {
                        int parseInt = Integer.parseInt(dVar.mo3304b(i3, a));
                        int i4 = a + 1;
                        int a2 = dVar.mo3293a(32, i4, c2);
                        if (a2 == -1) {
                            a2 = c2;
                        }
                        try {
                            int parseInt2 = Integer.parseInt(dVar.mo3304b(i4, a2));
                            wVar.mo3218a(a2);
                            return mo3171a(parseInt, parseInt2);
                        } catch (NumberFormatException unused) {
                            throw new C0486B("Invalid protocol minor version number: " + dVar.mo3294a(b, c2));
                        }
                    } catch (NumberFormatException unused2) {
                        throw new C0486B("Invalid protocol major version number: " + dVar.mo3294a(b, c2));
                    }
                } else {
                    throw new C0486B("Invalid protocol version number: " + dVar.mo3294a(b, c2));
                }
            } else {
                throw new C0486B("Not a valid protocol version: " + dVar.mo3294a(b, c2));
            }
        } else {
            throw new C0486B("Not a valid protocol version: " + dVar.mo3294a(b, c2));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo3177d(C0873d dVar, C0835w wVar) {
        int b = wVar.mo3220b();
        int c = wVar.mo3221c();
        while (b < c && C0854d.m3012a(dVar.charAt(b))) {
            b++;
        }
        wVar.mo3218a(b);
    }
}
