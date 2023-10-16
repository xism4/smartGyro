package android.support.p025v7.widget;

/* renamed from: android.support.v7.widget.Y */
class C0382Y {

    /* renamed from: a */
    private int f1470a = 0;

    /* renamed from: b */
    private int f1471b = 0;

    /* renamed from: c */
    private int f1472c = Integer.MIN_VALUE;

    /* renamed from: d */
    private int f1473d = Integer.MIN_VALUE;

    /* renamed from: e */
    private int f1474e = 0;

    /* renamed from: f */
    private int f1475f = 0;

    /* renamed from: g */
    private boolean f1476g = false;

    /* renamed from: h */
    private boolean f1477h = false;

    C0382Y() {
    }

    /* renamed from: a */
    public int mo2089a() {
        return this.f1476g ? this.f1470a : this.f1471b;
    }

    /* renamed from: a */
    public void mo2090a(int i, int i2) {
        this.f1477h = false;
        if (i != Integer.MIN_VALUE) {
            this.f1474e = i;
            this.f1470a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1475f = i2;
            this.f1471b = i2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        if (r2 != Integer.MIN_VALUE) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        if (r2 != Integer.MIN_VALUE) goto L_0x0031;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo2091a(boolean r2) {
        /*
            r1 = this;
            boolean r0 = r1.f1476g
            if (r2 != r0) goto L_0x0005
            return
        L_0x0005:
            r1.f1476g = r2
            boolean r0 = r1.f1477h
            if (r0 == 0) goto L_0x002b
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 == 0) goto L_0x001d
            int r2 = r1.f1473d
            if (r2 == r0) goto L_0x0014
            goto L_0x0016
        L_0x0014:
            int r2 = r1.f1474e
        L_0x0016:
            r1.f1470a = r2
            int r2 = r1.f1472c
            if (r2 == r0) goto L_0x002f
            goto L_0x0031
        L_0x001d:
            int r2 = r1.f1472c
            if (r2 == r0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            int r2 = r1.f1474e
        L_0x0024:
            r1.f1470a = r2
            int r2 = r1.f1473d
            if (r2 == r0) goto L_0x002f
            goto L_0x0031
        L_0x002b:
            int r2 = r1.f1474e
            r1.f1470a = r2
        L_0x002f:
            int r2 = r1.f1475f
        L_0x0031:
            r1.f1471b = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0382Y.mo2091a(boolean):void");
    }

    /* renamed from: b */
    public int mo2092b() {
        return this.f1470a;
    }

    /* renamed from: b */
    public void mo2093b(int i, int i2) {
        this.f1472c = i;
        this.f1473d = i2;
        this.f1477h = true;
        if (this.f1476g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f1470a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f1471b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f1470a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1471b = i2;
        }
    }

    /* renamed from: c */
    public int mo2094c() {
        return this.f1471b;
    }

    /* renamed from: d */
    public int mo2095d() {
        return this.f1476g ? this.f1471b : this.f1470a;
    }
}
