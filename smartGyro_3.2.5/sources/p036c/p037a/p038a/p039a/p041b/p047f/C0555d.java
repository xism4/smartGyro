package p036c.p037a.p038a.p039a.p041b.p047f;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.C0889z;

/* renamed from: c.a.a.a.b.f.d */
public class C0555d {

    /* renamed from: a */
    private String f1842a;

    /* renamed from: b */
    private String f1843b;

    /* renamed from: c */
    private String f1844c;

    /* renamed from: d */
    private String f1845d;

    /* renamed from: e */
    private String f1846e;

    /* renamed from: f */
    private String f1847f;

    /* renamed from: g */
    private int f1848g;

    /* renamed from: h */
    private String f1849h;

    /* renamed from: i */
    private String f1850i;

    /* renamed from: j */
    private String f1851j;

    /* renamed from: k */
    private List<C0889z> f1852k;

    /* renamed from: l */
    private String f1853l;

    /* renamed from: m */
    private Charset f1854m;

    /* renamed from: n */
    private String f1855n;

    /* renamed from: o */
    private String f1856o;

    public C0555d(URI uri) {
        m2231a(uri);
    }

    /* renamed from: a */
    private List<C0889z> m2230a(String str, Charset charset) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return C0557f.m2267d(str, charset);
    }

    /* renamed from: a */
    private void m2231a(URI uri) {
        this.f1842a = uri.getScheme();
        this.f1843b = uri.getRawSchemeSpecificPart();
        this.f1844c = uri.getRawAuthority();
        this.f1847f = uri.getHost();
        this.f1848g = uri.getPort();
        this.f1846e = uri.getRawUserInfo();
        this.f1845d = uri.getUserInfo();
        this.f1850i = uri.getRawPath();
        this.f1849h = uri.getPath();
        this.f1851j = uri.getRawQuery();
        String rawQuery = uri.getRawQuery();
        Charset charset = this.f1854m;
        if (charset == null) {
            charset = C0570c.f1865a;
        }
        this.f1852k = m2230a(rawQuery, charset);
        this.f1856o = uri.getRawFragment();
        this.f1855n = uri.getFragment();
    }

    /* renamed from: b */
    private String m2232b(List<C0889z> list) {
        Charset charset = this.f1854m;
        if (charset == null) {
            charset = C0570c.f1865a;
        }
        return C0557f.m2259a((Iterable<? extends C0889z>) list, charset);
    }

    /* renamed from: f */
    private String m2233f(String str) {
        Charset charset = this.f1854m;
        if (charset == null) {
            charset = C0570c.f1865a;
        }
        return C0557f.m2260a(str, charset);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c0  */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m2234g() {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r3.f1842a
            if (r1 == 0) goto L_0x0011
            r0.append(r1)
            r1 = 58
            r0.append(r1)
        L_0x0011:
            java.lang.String r1 = r3.f1843b
            if (r1 == 0) goto L_0x001a
        L_0x0015:
            r0.append(r1)
            goto L_0x00b1
        L_0x001a:
            java.lang.String r1 = r3.f1844c
            java.lang.String r2 = "//"
            if (r1 == 0) goto L_0x0029
            r0.append(r2)
            java.lang.String r1 = r3.f1844c
            r0.append(r1)
            goto L_0x006e
        L_0x0029:
            java.lang.String r1 = r3.f1847f
            if (r1 == 0) goto L_0x006e
            r0.append(r2)
            java.lang.String r1 = r3.f1846e
            java.lang.String r2 = "@"
            if (r1 == 0) goto L_0x003d
        L_0x0036:
            r0.append(r1)
            r0.append(r2)
            goto L_0x0046
        L_0x003d:
            java.lang.String r1 = r3.f1845d
            if (r1 == 0) goto L_0x0046
            java.lang.String r1 = r3.m2236h(r1)
            goto L_0x0036
        L_0x0046:
            java.lang.String r1 = r3.f1847f
            boolean r1 = p036c.p037a.p038a.p039a.p050e.p056f.C0625a.m2379b(r1)
            if (r1 == 0) goto L_0x005b
            java.lang.String r1 = "["
            r0.append(r1)
            java.lang.String r1 = r3.f1847f
            r0.append(r1)
            java.lang.String r1 = "]"
            goto L_0x005d
        L_0x005b:
            java.lang.String r1 = r3.f1847f
        L_0x005d:
            r0.append(r1)
            int r1 = r3.f1848g
            if (r1 < 0) goto L_0x006e
            java.lang.String r1 = ":"
            r0.append(r1)
            int r1 = r3.f1848g
            r0.append(r1)
        L_0x006e:
            java.lang.String r1 = r3.f1850i
            if (r1 == 0) goto L_0x007a
            java.lang.String r1 = m2237i(r1)
        L_0x0076:
            r0.append(r1)
            goto L_0x0087
        L_0x007a:
            java.lang.String r1 = r3.f1849h
            if (r1 == 0) goto L_0x0087
            java.lang.String r1 = m2237i(r1)
            java.lang.String r1 = r3.m2233f(r1)
            goto L_0x0076
        L_0x0087:
            java.lang.String r1 = r3.f1851j
            java.lang.String r2 = "?"
            if (r1 == 0) goto L_0x0093
            r0.append(r2)
            java.lang.String r1 = r3.f1851j
            goto L_0x0015
        L_0x0093:
            java.util.List<c.a.a.a.z> r1 = r3.f1852k
            if (r1 == 0) goto L_0x00a2
            r0.append(r2)
            java.util.List<c.a.a.a.z> r1 = r3.f1852k
            java.lang.String r1 = r3.m2232b((java.util.List<p036c.p037a.p038a.p039a.C0889z>) r1)
            goto L_0x0015
        L_0x00a2:
            java.lang.String r1 = r3.f1853l
            if (r1 == 0) goto L_0x00b1
            r0.append(r2)
            java.lang.String r1 = r3.f1853l
            java.lang.String r1 = r3.m2235g(r1)
            goto L_0x0015
        L_0x00b1:
            java.lang.String r1 = r3.f1856o
            java.lang.String r2 = "#"
            if (r1 == 0) goto L_0x00c0
            r0.append(r2)
            java.lang.String r1 = r3.f1856o
        L_0x00bc:
            r0.append(r1)
            goto L_0x00ce
        L_0x00c0:
            java.lang.String r1 = r3.f1855n
            if (r1 == 0) goto L_0x00ce
            r0.append(r2)
            java.lang.String r1 = r3.f1855n
            java.lang.String r1 = r3.m2235g(r1)
            goto L_0x00bc
        L_0x00ce:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p041b.p047f.C0555d.m2234g():java.lang.String");
    }

    /* renamed from: g */
    private String m2235g(String str) {
        Charset charset = this.f1854m;
        if (charset == null) {
            charset = C0570c.f1865a;
        }
        return C0557f.m2265b(str, charset);
    }

    /* renamed from: h */
    private String m2236h(String str) {
        Charset charset = this.f1854m;
        if (charset == null) {
            charset = C0570c.f1865a;
        }
        return C0557f.m2266c(str, charset);
    }

    /* renamed from: i */
    private static String m2237i(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        while (i < str.length() && str.charAt(i) == '/') {
            i++;
        }
        return i > 1 ? str.substring(i - 1) : str;
    }

    /* renamed from: a */
    public C0555d mo2578a(int i) {
        if (i < 0) {
            i = -1;
        }
        this.f1848g = i;
        this.f1843b = null;
        this.f1844c = null;
        return this;
    }

    /* renamed from: a */
    public C0555d mo2579a(String str) {
        this.f1855n = str;
        this.f1856o = null;
        return this;
    }

    /* renamed from: a */
    public C0555d mo2580a(Charset charset) {
        this.f1854m = charset;
        return this;
    }

    /* renamed from: a */
    public C0555d mo2581a(List<C0889z> list) {
        if (this.f1852k == null) {
            this.f1852k = new ArrayList();
        }
        this.f1852k.addAll(list);
        this.f1851j = null;
        this.f1843b = null;
        this.f1853l = null;
        return this;
    }

    /* renamed from: a */
    public URI mo2582a() {
        return new URI(m2234g());
    }

    /* renamed from: b */
    public C0555d mo2583b() {
        this.f1852k = null;
        this.f1851j = null;
        this.f1843b = null;
        return this;
    }

    /* renamed from: b */
    public C0555d mo2584b(String str) {
        this.f1847f = str;
        this.f1843b = null;
        this.f1844c = null;
        return this;
    }

    /* renamed from: c */
    public C0555d mo2585c(String str) {
        this.f1849h = str;
        this.f1843b = null;
        this.f1850i = null;
        return this;
    }

    /* renamed from: c */
    public String mo2586c() {
        return this.f1847f;
    }

    /* renamed from: d */
    public C0555d mo2587d(String str) {
        this.f1842a = str;
        return this;
    }

    /* renamed from: d */
    public String mo2588d() {
        return this.f1849h;
    }

    /* renamed from: e */
    public C0555d mo2589e(String str) {
        this.f1845d = str;
        this.f1843b = null;
        this.f1844c = null;
        this.f1846e = null;
        return this;
    }

    /* renamed from: e */
    public List<C0889z> mo2590e() {
        List<C0889z> list = this.f1852k;
        return list != null ? new ArrayList(list) : new ArrayList();
    }

    /* renamed from: f */
    public String mo2591f() {
        return this.f1845d;
    }

    public String toString() {
        return m2234g();
    }
}
