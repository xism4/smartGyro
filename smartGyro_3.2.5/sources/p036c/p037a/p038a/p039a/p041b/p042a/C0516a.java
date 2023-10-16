package p036c.p037a.p038a.p039a.p041b.p042a;

import java.net.InetAddress;
import java.util.Collection;
import p036c.p037a.p038a.p039a.C0867o;

/* renamed from: c.a.a.a.b.a.a */
public class C0516a implements Cloneable {

    /* renamed from: a */
    public static final C0516a f1778a = new C0517a().mo2522a();

    /* renamed from: b */
    private final boolean f1779b;

    /* renamed from: c */
    private final C0867o f1780c;

    /* renamed from: d */
    private final InetAddress f1781d;

    /* renamed from: e */
    private final boolean f1782e;

    /* renamed from: f */
    private final String f1783f;

    /* renamed from: g */
    private final boolean f1784g;

    /* renamed from: h */
    private final boolean f1785h;

    /* renamed from: i */
    private final boolean f1786i;

    /* renamed from: j */
    private final int f1787j;

    /* renamed from: k */
    private final boolean f1788k;

    /* renamed from: l */
    private final Collection<String> f1789l;

    /* renamed from: m */
    private final Collection<String> f1790m;

    /* renamed from: n */
    private final int f1791n;

    /* renamed from: o */
    private final int f1792o;

    /* renamed from: p */
    private final int f1793p;

    /* renamed from: q */
    private final boolean f1794q;

    /* renamed from: c.a.a.a.b.a.a$a */
    public static class C0517a {

        /* renamed from: a */
        private boolean f1795a;

        /* renamed from: b */
        private C0867o f1796b;

        /* renamed from: c */
        private InetAddress f1797c;

        /* renamed from: d */
        private boolean f1798d = false;

        /* renamed from: e */
        private String f1799e;

        /* renamed from: f */
        private boolean f1800f = true;

        /* renamed from: g */
        private boolean f1801g = true;

        /* renamed from: h */
        private boolean f1802h;

        /* renamed from: i */
        private int f1803i = 50;

        /* renamed from: j */
        private boolean f1804j = true;

        /* renamed from: k */
        private Collection<String> f1805k;

        /* renamed from: l */
        private Collection<String> f1806l;

        /* renamed from: m */
        private int f1807m = -1;

        /* renamed from: n */
        private int f1808n = -1;

        /* renamed from: o */
        private int f1809o = -1;

        /* renamed from: p */
        private boolean f1810p = true;

        C0517a() {
        }

        /* renamed from: a */
        public C0517a mo2516a(int i) {
            this.f1808n = i;
            return this;
        }

        /* renamed from: a */
        public C0517a mo2517a(C0867o oVar) {
            this.f1796b = oVar;
            return this;
        }

        /* renamed from: a */
        public C0517a mo2518a(String str) {
            this.f1799e = str;
            return this;
        }

        /* renamed from: a */
        public C0517a mo2519a(InetAddress inetAddress) {
            this.f1797c = inetAddress;
            return this;
        }

        /* renamed from: a */
        public C0517a mo2520a(Collection<String> collection) {
            this.f1806l = collection;
            return this;
        }

        /* renamed from: a */
        public C0517a mo2521a(boolean z) {
            this.f1804j = z;
            return this;
        }

        /* renamed from: a */
        public C0516a mo2522a() {
            return new C0516a(this.f1795a, this.f1796b, this.f1797c, this.f1798d, this.f1799e, this.f1800f, this.f1801g, this.f1802h, this.f1803i, this.f1804j, this.f1805k, this.f1806l, this.f1807m, this.f1808n, this.f1809o, this.f1810p);
        }

        /* renamed from: b */
        public C0517a mo2523b(int i) {
            this.f1807m = i;
            return this;
        }

        /* renamed from: b */
        public C0517a mo2524b(Collection<String> collection) {
            this.f1805k = collection;
            return this;
        }

        /* renamed from: b */
        public C0517a mo2525b(boolean z) {
            this.f1802h = z;
            return this;
        }

        /* renamed from: c */
        public C0517a mo2526c(int i) {
            this.f1803i = i;
            return this;
        }

        /* renamed from: c */
        public C0517a mo2527c(boolean z) {
            this.f1795a = z;
            return this;
        }

        /* renamed from: d */
        public C0517a mo2528d(int i) {
            this.f1809o = i;
            return this;
        }

        /* renamed from: d */
        public C0517a mo2529d(boolean z) {
            this.f1800f = z;
            return this;
        }

        /* renamed from: e */
        public C0517a mo2530e(boolean z) {
            this.f1801g = z;
            return this;
        }

        @Deprecated
        /* renamed from: f */
        public C0517a mo2531f(boolean z) {
            this.f1798d = z;
            return this;
        }
    }

    C0516a(boolean z, C0867o oVar, InetAddress inetAddress, boolean z2, String str, boolean z3, boolean z4, boolean z5, int i, boolean z6, Collection<String> collection, Collection<String> collection2, int i2, int i3, int i4, boolean z7) {
        this.f1779b = z;
        this.f1780c = oVar;
        this.f1781d = inetAddress;
        this.f1782e = z2;
        this.f1783f = str;
        this.f1784g = z3;
        this.f1785h = z4;
        this.f1786i = z5;
        this.f1787j = i;
        this.f1788k = z6;
        this.f1789l = collection;
        this.f1790m = collection2;
        this.f1791n = i2;
        this.f1792o = i3;
        this.f1793p = i4;
        this.f1794q = z7;
    }

    /* renamed from: a */
    public static C0517a m2148a() {
        return new C0517a();
    }

    /* renamed from: b */
    public String mo2509b() {
        return this.f1783f;
    }

    /* renamed from: c */
    public Collection<String> mo2510c() {
        return this.f1790m;
    }

    /* access modifiers changed from: protected */
    public C0516a clone() {
        return (C0516a) super.clone();
    }

    /* renamed from: d */
    public Collection<String> mo2512d() {
        return this.f1789l;
    }

    /* renamed from: e */
    public boolean mo2513e() {
        return this.f1786i;
    }

    /* renamed from: f */
    public boolean mo2514f() {
        return this.f1785h;
    }

    public String toString() {
        return "[" + "expectContinueEnabled=" + this.f1779b + ", proxy=" + this.f1780c + ", localAddress=" + this.f1781d + ", cookieSpec=" + this.f1783f + ", redirectsEnabled=" + this.f1784g + ", relativeRedirectsAllowed=" + this.f1785h + ", maxRedirects=" + this.f1787j + ", circularRedirectsAllowed=" + this.f1786i + ", authenticationEnabled=" + this.f1788k + ", targetPreferredAuthSchemes=" + this.f1789l + ", proxyPreferredAuthSchemes=" + this.f1790m + ", connectionRequestTimeout=" + this.f1791n + ", connectTimeout=" + this.f1792o + ", socketTimeout=" + this.f1793p + ", decompressionEnabled=" + this.f1794q + "]";
    }
}
