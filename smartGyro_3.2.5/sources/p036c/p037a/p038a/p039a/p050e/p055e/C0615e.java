package p036c.p037a.p038a.p039a.p050e.p055e;

import javax.security.auth.x500.X500Principal;

/* renamed from: c.a.a.a.e.e.e */
public final class C0615e {

    /* renamed from: a */
    private final String f1913a;

    /* renamed from: b */
    private final int f1914b = this.f1913a.length();

    /* renamed from: c */
    private int f1915c;

    /* renamed from: d */
    private int f1916d;

    /* renamed from: e */
    private int f1917e;

    /* renamed from: f */
    private int f1918f;

    /* renamed from: g */
    private char[] f1919g;

    public C0615e(X500Principal x500Principal) {
        this.f1913a = x500Principal.getName("RFC2253");
    }

    /* renamed from: a */
    private int m2352a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.f1914b) {
            char c = this.f1919g[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else if (c < 'A' || c > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f1913a);
            } else {
                i2 = c - '7';
            }
            char c2 = this.f1919g[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f1913a);
            } else {
                i3 = c2 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException("Malformed DN: " + this.f1913a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0097, code lost:
        r1 = r8.f1919g;
        r2 = r8.f1916d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a3, code lost:
        return new java.lang.String(r1, r2, r8.f1918f - r2);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m2353a() {
        /*
            r8 = this;
            int r0 = r8.f1915c
            r8.f1916d = r0
            r8.f1917e = r0
        L_0x0006:
            int r0 = r8.f1915c
            int r1 = r8.f1914b
            if (r0 < r1) goto L_0x0019
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f1919g
            int r2 = r8.f1916d
            int r3 = r8.f1917e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L_0x0019:
            char[] r1 = r8.f1919g
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L_0x005c
            if (r2 == r5) goto L_0x004f
            r5 = 92
            if (r2 == r5) goto L_0x0040
            if (r2 == r4) goto L_0x004f
            if (r2 == r3) goto L_0x004f
            int r2 = r8.f1917e
            int r3 = r2 + 1
            r8.f1917e = r3
            char r3 = r1[r0]
            r1[r2] = r3
        L_0x003b:
            int r0 = r0 + 1
            r8.f1915c = r0
            goto L_0x0006
        L_0x0040:
            int r0 = r8.f1917e
            int r2 = r0 + 1
            r8.f1917e = r2
            char r2 = r8.m2354b()
            r1[r0] = r2
            int r0 = r8.f1915c
            goto L_0x003b
        L_0x004f:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f1919g
            int r2 = r8.f1916d
            int r3 = r8.f1917e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L_0x005c:
            int r2 = r8.f1917e
            r8.f1918f = r2
            int r0 = r0 + 1
            r8.f1915c = r0
            int r0 = r2 + 1
            r8.f1917e = r0
            r1[r2] = r6
        L_0x006a:
            int r0 = r8.f1915c
            int r1 = r8.f1914b
            if (r0 >= r1) goto L_0x0083
            char[] r1 = r8.f1919g
            char r2 = r1[r0]
            if (r2 != r6) goto L_0x0083
            int r2 = r8.f1917e
            int r7 = r2 + 1
            r8.f1917e = r7
            r1[r2] = r6
            int r0 = r0 + 1
            r8.f1915c = r0
            goto L_0x006a
        L_0x0083:
            int r0 = r8.f1915c
            int r1 = r8.f1914b
            if (r0 == r1) goto L_0x0097
            char[] r1 = r8.f1919g
            char r2 = r1[r0]
            if (r2 == r3) goto L_0x0097
            char r2 = r1[r0]
            if (r2 == r4) goto L_0x0097
            char r0 = r1[r0]
            if (r0 != r5) goto L_0x0006
        L_0x0097:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f1919g
            int r2 = r8.f1916d
            int r3 = r8.f1918f
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p050e.p055e.C0615e.m2353a():java.lang.String");
    }

    /* renamed from: b */
    private char m2354b() {
        this.f1915c++;
        int i = this.f1915c;
        if (i != this.f1914b) {
            char c = this.f1919g[i];
            if (!(c == ' ' || c == '%' || c == '\\' || c == '_' || c == '\"' || c == '#')) {
                switch (c) {
                    case '*':
                    case '+':
                    case ',':
                        break;
                    default:
                        switch (c) {
                            case ';':
                            case '<':
                            case '=':
                            case '>':
                                break;
                            default:
                                return m2355c();
                        }
                }
            }
            return this.f1919g[this.f1915c];
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f1913a);
    }

    /* renamed from: c */
    private char m2355c() {
        int i;
        int i2;
        int a = m2352a(this.f1915c);
        this.f1915c++;
        if (a < 128) {
            return (char) a;
        }
        if (a < 192 || a > 247) {
            return '?';
        }
        if (a <= 223) {
            i2 = a & 31;
            i = 1;
        } else if (a <= 239) {
            i = 2;
            i2 = a & 15;
        } else {
            i = 3;
            i2 = a & 7;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.f1915c++;
            int i4 = this.f1915c;
            if (i4 == this.f1914b || this.f1919g[i4] != '\\') {
                return '?';
            }
            this.f1915c = i4 + 1;
            int a2 = m2352a(this.f1915c);
            this.f1915c++;
            if ((a2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (a2 & 63);
        }
        return (char) i2;
    }

    /* renamed from: d */
    private String m2356d() {
        int i = this.f1915c;
        if (i + 4 < this.f1914b) {
            this.f1916d = i;
            while (true) {
                this.f1915c = i + 1;
                int i2 = this.f1915c;
                if (i2 == this.f1914b) {
                    break;
                }
                char[] cArr = this.f1919g;
                if (cArr[i2] == '+' || cArr[i2] == ',' || cArr[i2] == ';') {
                    break;
                } else if (cArr[i2] == ' ') {
                    this.f1917e = i2;
                    do {
                        this.f1915c = i2 + 1;
                        i2 = this.f1915c;
                        if (i2 >= this.f1914b) {
                            break;
                        }
                    } while (this.f1919g[i2] != ' ');
                } else {
                    if (cArr[i2] >= 'A' && cArr[i2] <= 'F') {
                        cArr[i2] = (char) (cArr[i2] + ' ');
                    }
                    i = this.f1915c;
                }
            }
            this.f1917e = this.f1915c;
            int i3 = this.f1917e;
            int i4 = this.f1916d;
            int i5 = i3 - i4;
            if (i5 < 5 || (i5 & 1) == 0) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f1913a);
            }
            byte[] bArr = new byte[(i5 / 2)];
            int i6 = i4 + 1;
            for (int i7 = 0; i7 < bArr.length; i7++) {
                bArr[i7] = (byte) m2352a(i6);
                i6 += 2;
            }
            return new String(this.f1919g, this.f1916d, i5);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f1913a);
    }

    /* renamed from: e */
    private String m2357e() {
        char[] cArr;
        while (true) {
            int i = this.f1915c;
            if (i >= this.f1914b || this.f1919g[i] != ' ') {
                int i2 = this.f1915c;
            } else {
                this.f1915c = i + 1;
            }
        }
        int i22 = this.f1915c;
        if (i22 == this.f1914b) {
            return null;
        }
        this.f1916d = i22;
        do {
            this.f1915c = i22 + 1;
            i22 = this.f1915c;
            if (i22 >= this.f1914b) {
                break;
            }
            cArr = this.f1919g;
            if (cArr[i22] == '=') {
                break;
            }
        } while (cArr[i22] == ' ');
        int i3 = this.f1915c;
        if (i3 < this.f1914b) {
            this.f1917e = i3;
            if (this.f1919g[i3] == ' ') {
                while (true) {
                    int i4 = this.f1915c;
                    if (i4 >= this.f1914b) {
                        break;
                    }
                    char[] cArr2 = this.f1919g;
                    if (cArr2[i4] == '=' || cArr2[i4] != ' ') {
                        break;
                    }
                    this.f1915c = i4 + 1;
                }
                char[] cArr3 = this.f1919g;
                int i5 = this.f1915c;
                if (cArr3[i5] != '=' || i5 == this.f1914b) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.f1913a);
                }
            }
            int i6 = this.f1915c;
            do {
                this.f1915c = i6 + 1;
                i6 = this.f1915c;
                if (i6 >= this.f1914b || this.f1919g[i6] != ' ') {
                    int i7 = this.f1917e;
                    int i8 = this.f1916d;
                }
                this.f1915c = i6 + 1;
                i6 = this.f1915c;
                break;
            } while (this.f1919g[i6] != ' ');
            int i72 = this.f1917e;
            int i82 = this.f1916d;
            if (i72 - i82 > 4) {
                char[] cArr4 = this.f1919g;
                if (cArr4[i82 + 3] == '.' && (cArr4[i82] == 'O' || cArr4[i82] == 'o')) {
                    char[] cArr5 = this.f1919g;
                    int i9 = this.f1916d;
                    if (cArr5[i9 + 1] == 'I' || cArr5[i9 + 1] == 'i') {
                        char[] cArr6 = this.f1919g;
                        int i10 = this.f1916d;
                        if (cArr6[i10 + 2] == 'D' || cArr6[i10 + 2] == 'd') {
                            this.f1916d += 4;
                        }
                    }
                }
            }
            char[] cArr7 = this.f1919g;
            int i11 = this.f1916d;
            return new String(cArr7, i11, this.f1917e - i11);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f1913a);
    }

    /* renamed from: f */
    private String m2358f() {
        this.f1915c++;
        this.f1916d = this.f1915c;
        int i = this.f1916d;
        while (true) {
            this.f1917e = i;
            int i2 = this.f1915c;
            if (i2 != this.f1914b) {
                char[] cArr = this.f1919g;
                if (cArr[i2] == '\"') {
                    do {
                        this.f1915c = i2 + 1;
                        i2 = this.f1915c;
                        if (i2 >= this.f1914b || this.f1919g[i2] != ' ') {
                            char[] cArr2 = this.f1919g;
                            int i3 = this.f1916d;
                        }
                        this.f1915c = i2 + 1;
                        i2 = this.f1915c;
                        break;
                    } while (this.f1919g[i2] != ' ');
                    char[] cArr22 = this.f1919g;
                    int i32 = this.f1916d;
                    return new String(cArr22, i32, this.f1917e - i32);
                }
                if (cArr[i2] == '\\') {
                    cArr[this.f1917e] = m2354b();
                } else {
                    cArr[this.f1917e] = cArr[i2];
                }
                this.f1915c++;
                i = this.f1917e + 1;
            } else {
                throw new IllegalStateException("Unexpected end of DN: " + this.f1913a);
            }
        }
    }

    /* renamed from: a */
    public String mo2691a(String str) {
        this.f1915c = 0;
        this.f1916d = 0;
        this.f1917e = 0;
        this.f1918f = 0;
        this.f1919g = this.f1913a.toCharArray();
        String e = m2357e();
        if (e == null) {
            return null;
        }
        do {
            int i = this.f1915c;
            if (i == this.f1914b) {
                return null;
            }
            char c = this.f1919g[i];
            String a = c != '\"' ? c != '#' ? (c == '+' || c == ',' || c == ';') ? "" : m2353a() : m2356d() : m2358f();
            if (str.equalsIgnoreCase(e)) {
                return a;
            }
            int i2 = this.f1915c;
            if (i2 >= this.f1914b) {
                return null;
            }
            char[] cArr = this.f1919g;
            if (cArr[i2] == ',' || cArr[i2] == ';' || cArr[i2] == '+') {
                this.f1915c++;
                e = m2357e();
            } else {
                throw new IllegalStateException("Malformed DN: " + this.f1913a);
            }
        } while (e != null);
        throw new IllegalStateException("Malformed DN: " + this.f1913a);
    }
}
