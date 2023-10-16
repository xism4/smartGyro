package p036c.p037a.p038a.p039a.p074p;

import java.io.Serializable;
import java.nio.CharBuffer;
import p036c.p037a.p038a.p039a.p072n.C0854d;

/* renamed from: c.a.a.a.p.d */
public final class C0873d implements CharSequence, Serializable {

    /* renamed from: a */
    private char[] f2437a;

    /* renamed from: b */
    private int f2438b;

    public C0873d(int i) {
        C0870a.m3039a(i, "Buffer capacity");
        this.f2437a = new char[i];
    }

    /* renamed from: c */
    private void m3064c(int i) {
        char[] cArr = new char[Math.max(this.f2437a.length << 1, i)];
        System.arraycopy(this.f2437a, 0, cArr, 0, this.f2438b);
        this.f2437a = cArr;
    }

    /* renamed from: a */
    public int mo3293a(int i, int i2, int i3) {
        if (i2 < 0) {
            i2 = 0;
        }
        int i4 = this.f2438b;
        if (i3 > i4) {
            i3 = i4;
        }
        if (i2 > i3) {
            return -1;
        }
        while (i2 < i3) {
            if (this.f2437a[i2] == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    /* renamed from: a */
    public String mo3294a(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        } else if (i2 > this.f2438b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.f2438b);
        } else if (i <= i2) {
            return new String(this.f2437a, i, i2 - i);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
        }
    }

    /* renamed from: a */
    public void mo3295a(int i) {
        if (i > 0) {
            int length = this.f2437a.length;
            int i2 = this.f2438b;
            if (i > length - i2) {
                m3064c(i2 + i);
            }
        }
    }

    /* renamed from: a */
    public void mo3296a(C0872c cVar, int i, int i2) {
        if (cVar != null) {
            mo3299a(cVar.mo3285a(), i, i2);
        }
    }

    /* renamed from: a */
    public void mo3297a(C0873d dVar, int i, int i2) {
        if (dVar != null) {
            mo3300a(dVar.f2437a, i, i2);
        }
    }

    /* renamed from: a */
    public void mo3298a(String str) {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        int i = this.f2438b + length;
        if (i > this.f2437a.length) {
            m3064c(i);
        }
        str.getChars(0, length, this.f2437a, this.f2438b);
        this.f2438b = i;
    }

    /* renamed from: a */
    public void mo3299a(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
            } else if (i2 != 0) {
                int i4 = this.f2438b;
                int i5 = i2 + i4;
                if (i5 > this.f2437a.length) {
                    m3064c(i5);
                }
                while (i4 < i5) {
                    this.f2437a[i4] = (char) (bArr[i] & 255);
                    i++;
                    i4++;
                }
                this.f2438b = i5;
            }
        }
    }

    /* renamed from: a */
    public void mo3300a(char[] cArr, int i, int i2) {
        int i3;
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > cArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + cArr.length);
            } else if (i2 != 0) {
                int i4 = this.f2438b + i2;
                if (i4 > this.f2437a.length) {
                    m3064c(i4);
                }
                System.arraycopy(cArr, i, this.f2437a, this.f2438b, i2);
                this.f2438b = i4;
            }
        }
    }

    /* renamed from: a */
    public char[] mo3301a() {
        return this.f2437a;
    }

    public void append(char c) {
        int i = this.f2438b + 1;
        if (i > this.f2437a.length) {
            m3064c(i);
        }
        this.f2437a[this.f2438b] = c;
        this.f2438b = i;
    }

    /* renamed from: b */
    public int mo3303b(int i) {
        return mo3293a(i, 0, this.f2438b);
    }

    /* renamed from: b */
    public String mo3304b(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        } else if (i2 > this.f2438b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.f2438b);
        } else if (i <= i2) {
            while (i < i2 && C0854d.m3012a(this.f2437a[i])) {
                i++;
            }
            while (i2 > i && C0854d.m3012a(this.f2437a[i2 - 1])) {
                i2--;
            }
            return new String(this.f2437a, i, i2 - i);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
        }
    }

    /* renamed from: b */
    public boolean mo3305b() {
        return this.f2438b == 0;
    }

    public char charAt(int i) {
        return this.f2437a[i];
    }

    public void clear() {
        this.f2438b = 0;
    }

    public int length() {
        return this.f2438b;
    }

    public CharSequence subSequence(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        } else if (i2 > this.f2438b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.f2438b);
        } else if (i <= i2) {
            return CharBuffer.wrap(this.f2437a, i, i2);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
        }
    }

    public String toString() {
        return new String(this.f2437a, 0, this.f2438b);
    }
}
