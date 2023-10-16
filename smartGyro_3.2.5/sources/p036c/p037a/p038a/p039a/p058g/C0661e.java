package p036c.p037a.p038a.p039a.p058g;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p069k.C0818f;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;
import p036c.p037a.p038a.p039a.p074p.C0878i;

/* renamed from: c.a.a.a.g.e */
public final class C0661e implements Serializable {

    /* renamed from: a */
    public static final C0661e f1958a = m2423a("application/atom+xml", C0570c.f1867c);

    /* renamed from: b */
    public static final C0661e f1959b = m2423a("application/x-www-form-urlencoded", C0570c.f1867c);

    /* renamed from: c */
    public static final C0661e f1960c = m2423a("application/json", C0570c.f1865a);

    /* renamed from: d */
    public static final C0661e f1961d = m2423a("application/octet-stream", (Charset) null);

    /* renamed from: e */
    public static final C0661e f1962e = m2423a("application/svg+xml", C0570c.f1867c);

    /* renamed from: f */
    public static final C0661e f1963f = m2423a("application/xhtml+xml", C0570c.f1867c);

    /* renamed from: g */
    public static final C0661e f1964g = m2423a("application/xml", C0570c.f1867c);

    /* renamed from: h */
    public static final C0661e f1965h = m2423a("multipart/form-data", C0570c.f1867c);

    /* renamed from: i */
    public static final C0661e f1966i = m2423a("text/html", C0570c.f1867c);

    /* renamed from: j */
    public static final C0661e f1967j = m2423a("text/plain", C0570c.f1867c);

    /* renamed from: k */
    public static final C0661e f1968k = m2423a("text/xml", C0570c.f1867c);

    /* renamed from: l */
    public static final C0661e f1969l = m2423a("*/*", (Charset) null);

    /* renamed from: m */
    public static final C0661e f1970m = f1967j;

    /* renamed from: n */
    public static final C0661e f1971n = f1961d;

    /* renamed from: o */
    private final String f1972o;

    /* renamed from: p */
    private final Charset f1973p;

    /* renamed from: q */
    private final C0889z[] f1974q;

    C0661e(String str, Charset charset) {
        this.f1972o = str;
        this.f1973p = charset;
        this.f1974q = null;
    }

    C0661e(String str, Charset charset, C0889z[] zVarArr) {
        this.f1972o = str;
        this.f1973p = charset;
        this.f1974q = zVarArr;
    }

    /* renamed from: a */
    private static C0661e m2421a(C0639f fVar, boolean z) {
        return m2424a(fVar.getName(), fVar.getParameters(), z);
    }

    /* renamed from: a */
    public static C0661e m2422a(C0837l lVar) {
        C0576e contentType;
        if (!(lVar == null || (contentType = lVar.getContentType()) == null)) {
            C0639f[] elements = contentType.getElements();
            if (elements.length > 0) {
                return m2421a(elements[0], true);
            }
        }
        return null;
    }

    /* renamed from: a */
    public static C0661e m2423a(String str, Charset charset) {
        C0870a.m3047b(str, "MIME type");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        C0870a.m3044a(m2425a(lowerCase), "MIME type may not contain reserved characters");
        return new C0661e(lowerCase, charset);
    }

    /* renamed from: a */
    private static C0661e m2424a(String str, C0889z[] zVarArr, boolean z) {
        Charset charset;
        int length = zVarArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            C0889z zVar = zVarArr[i];
            if (zVar.getName().equalsIgnoreCase("charset")) {
                String value = zVar.getValue();
                if (!C0878i.m3088b(value)) {
                    try {
                        charset = Charset.forName(value);
                    } catch (UnsupportedCharsetException e) {
                        if (z) {
                            throw e;
                        }
                    }
                }
            } else {
                i++;
            }
        }
        charset = null;
        if (zVarArr == null || zVarArr.length <= 0) {
            zVarArr = null;
        }
        return new C0661e(str, charset, zVarArr);
    }

    /* renamed from: a */
    private static boolean m2425a(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"' || charAt == ',' || charAt == ';') {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public Charset mo2796a() {
        return this.f1973p;
    }

    /* renamed from: b */
    public String mo2797b() {
        return this.f1972o;
    }

    public String toString() {
        C0873d dVar = new C0873d(64);
        dVar.mo3298a(this.f1972o);
        if (this.f1974q != null) {
            dVar.mo3298a("; ");
            C0818f.f2360b.mo3146a(dVar, this.f1974q, false);
        } else if (this.f1973p != null) {
            dVar.mo3298a("; charset=");
            dVar.mo3298a(this.f1973p.name());
        }
        return dVar.toString();
    }
}
