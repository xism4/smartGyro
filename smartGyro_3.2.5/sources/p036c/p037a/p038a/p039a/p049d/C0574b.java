package p036c.p037a.p038a.p039a.p049d;

/* renamed from: c.a.a.a.d.b */
public class C0574b implements Cloneable {

    /* renamed from: a */
    public static final C0574b f1868a = new C0575a().mo2610a();

    /* renamed from: b */
    private final int f1869b;

    /* renamed from: c */
    private final int f1870c;

    /* renamed from: c.a.a.a.d.b$a */
    public static class C0575a {

        /* renamed from: a */
        private int f1871a = -1;

        /* renamed from: b */
        private int f1872b = -1;

        C0575a() {
        }

        /* renamed from: a */
        public C0575a mo2609a(int i) {
            this.f1872b = i;
            return this;
        }

        /* renamed from: a */
        public C0574b mo2610a() {
            return new C0574b(this.f1871a, this.f1872b);
        }

        /* renamed from: b */
        public C0575a mo2611b(int i) {
            this.f1871a = i;
            return this;
        }
    }

    C0574b(int i, int i2) {
        this.f1869b = i;
        this.f1870c = i2;
    }

    /* renamed from: a */
    public static C0575a m2281a() {
        return new C0575a();
    }

    /* renamed from: b */
    public int mo2605b() {
        return this.f1870c;
    }

    /* renamed from: c */
    public int mo2606c() {
        return this.f1869b;
    }

    /* access modifiers changed from: protected */
    public C0574b clone() {
        return (C0574b) super.clone();
    }

    public String toString() {
        return "[maxLineLength=" + this.f1869b + ", maxHeaderCount=" + this.f1870c + "]";
    }
}
