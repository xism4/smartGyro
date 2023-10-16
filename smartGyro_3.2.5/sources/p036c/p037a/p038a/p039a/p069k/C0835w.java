package p036c.p037a.p038a.p039a.p069k;

/* renamed from: c.a.a.a.k.w */
public class C0835w {

    /* renamed from: a */
    private final int f2402a;

    /* renamed from: b */
    private final int f2403b;

    /* renamed from: c */
    private int f2404c;

    public C0835w(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        } else if (i <= i2) {
            this.f2402a = i;
            this.f2403b = i2;
            this.f2404c = i;
        } else {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
    }

    /* renamed from: a */
    public void mo3218a(int i) {
        if (i < this.f2402a) {
            throw new IndexOutOfBoundsException("pos: " + i + " < lowerBound: " + this.f2402a);
        } else if (i <= this.f2403b) {
            this.f2404c = i;
        } else {
            throw new IndexOutOfBoundsException("pos: " + i + " > upperBound: " + this.f2403b);
        }
    }

    /* renamed from: a */
    public boolean mo3219a() {
        return this.f2404c >= this.f2403b;
    }

    /* renamed from: b */
    public int mo3220b() {
        return this.f2404c;
    }

    /* renamed from: c */
    public int mo3221c() {
        return this.f2403b;
    }

    public String toString() {
        return '[' + Integer.toString(this.f2402a) + '>' + Integer.toString(this.f2404c) + '>' + Integer.toString(this.f2403b) + ']';
    }
}
