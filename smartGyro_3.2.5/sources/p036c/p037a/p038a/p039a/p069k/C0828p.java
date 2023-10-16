package p036c.p037a.p038a.p039a.p069k;

import java.util.NoSuchElementException;
import p036c.p037a.p038a.p039a.C0486B;
import p036c.p037a.p038a.p039a.C0492H;
import p036c.p037a.p038a.p039a.C0664h;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.k.p */
public class C0828p implements C0492H {

    /* renamed from: a */
    protected final C0664h f2393a;

    /* renamed from: b */
    protected String f2394b;

    /* renamed from: c */
    protected String f2395c;

    /* renamed from: d */
    protected int f2396d = mo3192a(-1);

    public C0828p(C0664h hVar) {
        C0870a.m3042a(hVar, "Header iterator");
        this.f2393a = hVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo3192a(int i) {
        int i2;
        String a;
        int i3 = -1;
        if (i >= 0) {
            i2 = mo3197c(i);
        } else if (!this.f2393a.hasNext()) {
            return -1;
        } else {
            this.f2394b = this.f2393a.nextHeader().getValue();
            i2 = 0;
        }
        int d = mo3199d(i2);
        if (d < 0) {
            a = null;
        } else {
            i3 = mo3195b(d);
            a = mo3193a(this.f2394b, d, i3);
        }
        this.f2395c = a;
        return i3;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo3193a(String str, int i, int i2) {
        return str.substring(i, i2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo3194a(char c) {
        return " ,;=()<>@:\\\"/[]?{}\t".indexOf(c) >= 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo3195b(int i) {
        C0870a.m3039a(i, "Search position");
        int length = this.f2394b.length();
        do {
            i++;
            if (i >= length || !mo3196b(this.f2394b.charAt(i))) {
                return i;
            }
            i++;
            break;
        } while (!mo3196b(this.f2394b.charAt(i)));
        return i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo3196b(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        return !Character.isISOControl(c) && !mo3194a(c);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo3197c(int i) {
        C0870a.m3039a(i, "Search position");
        int length = this.f2394b.length();
        boolean z = false;
        while (!z && i < length) {
            char charAt = this.f2394b.charAt(i);
            if (mo3198c(charAt)) {
                z = true;
            } else if (mo3200d(charAt)) {
                i++;
            } else if (mo3196b(charAt)) {
                throw new C0486B("Tokens without separator (pos " + i + "): " + this.f2394b);
            } else {
                throw new C0486B("Invalid character after token (pos " + i + "): " + this.f2394b);
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo3198c(char c) {
        return c == ',';
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public int mo3199d(int i) {
        C0870a.m3039a(i, "Search position");
        int i2 = i;
        boolean z = false;
        while (!z) {
            String str = this.f2394b;
            if (str == null) {
                break;
            }
            int length = str.length();
            while (!z && i2 < length) {
                char charAt = this.f2394b.charAt(i2);
                if (mo3198c(charAt) || mo3200d(charAt)) {
                    i2++;
                } else if (mo3196b(this.f2394b.charAt(i2))) {
                    z = true;
                } else {
                    throw new C0486B("Invalid character before token (pos " + i2 + "): " + this.f2394b);
                }
            }
            if (!z) {
                if (this.f2393a.hasNext()) {
                    this.f2394b = this.f2393a.nextHeader().getValue();
                    i2 = 0;
                } else {
                    this.f2394b = null;
                }
            }
        }
        if (z) {
            return i2;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public boolean mo3200d(char c) {
        return c == 9 || Character.isSpaceChar(c);
    }

    public boolean hasNext() {
        return this.f2395c != null;
    }

    public final Object next() {
        return nextToken();
    }

    public String nextToken() {
        String str = this.f2395c;
        if (str != null) {
            this.f2396d = mo3192a(this.f2396d);
            return str;
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    public final void remove() {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }
}
