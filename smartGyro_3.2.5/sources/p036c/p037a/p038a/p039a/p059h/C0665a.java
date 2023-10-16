package p036c.p037a.p038a.p039a.p059h;

/* renamed from: c.a.a.a.h.a */
public class C0665a {

    /* renamed from: c.a.a.a.h.a$a */
    static abstract class C0666a {

        /* renamed from: a */
        public byte[] f1977a;

        /* renamed from: b */
        public int f1978b;

        C0666a() {
        }
    }

    /* renamed from: c.a.a.a.h.a$b */
    static class C0667b extends C0666a {

        /* renamed from: c */
        private static final byte[] f1979c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

        /* renamed from: d */
        private static final byte[] f1980d = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

        /* renamed from: e */
        private final byte[] f1981e;

        /* renamed from: f */
        int f1982f;

        /* renamed from: g */
        private int f1983g;

        /* renamed from: h */
        public final boolean f1984h;

        /* renamed from: i */
        public final boolean f1985i;

        /* renamed from: j */
        public final boolean f1986j;

        /* renamed from: k */
        private final byte[] f1987k;

        public C0667b(int i, byte[] bArr) {
            this.f1977a = bArr;
            boolean z = true;
            this.f1984h = (i & 1) == 0;
            this.f1985i = (i & 2) == 0;
            this.f1986j = (i & 4) == 0 ? false : z;
            this.f1987k = (i & 8) == 0 ? f1979c : f1980d;
            this.f1981e = new byte[2];
            this.f1982f = 0;
            this.f1983g = this.f1985i ? 19 : -1;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0097  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00eb  */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x01bc  */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x00e9 A[SYNTHETIC] */
        /* renamed from: a */
        public boolean mo2802a(byte[] r18, int r19, int r20, boolean r21) {
            /*
                r17 = this;
                r0 = r17
                byte[] r1 = r0.f1987k
                byte[] r2 = r0.f1977a
                int r3 = r0.f1983g
                int r4 = r20 + r19
                int r5 = r0.f1982f
                r6 = -1
                r7 = 0
                r8 = 2
                r9 = 1
                if (r5 == 0) goto L_0x0053
                if (r5 == r9) goto L_0x0034
                if (r5 == r8) goto L_0x0017
                goto L_0x0053
            L_0x0017:
                int r5 = r19 + 1
                if (r5 > r4) goto L_0x0053
                byte[] r10 = r0.f1981e
                byte r11 = r10[r7]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 16
                byte r10 = r10[r9]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r10 = r10 << 8
                r10 = r10 | r11
                byte r11 = r18[r19]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r10 = r10 | r11
                r0.f1982f = r7
                r11 = r5
                r5 = r10
                goto L_0x0056
            L_0x0034:
                int r5 = r19 + 2
                if (r5 > r4) goto L_0x0053
                byte[] r5 = r0.f1981e
                byte r5 = r5[r7]
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << 16
                int r10 = r19 + 1
                byte r11 = r18[r19]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 8
                r5 = r5 | r11
                int r11 = r10 + 1
                byte r10 = r18[r10]
                r10 = r10 & 255(0xff, float:3.57E-43)
                r5 = r5 | r10
                r0.f1982f = r7
                goto L_0x0056
            L_0x0053:
                r11 = r19
                r5 = -1
            L_0x0056:
                r12 = 4
                r13 = 13
                r14 = 10
                if (r5 == r6) goto L_0x0092
                int r6 = r5 >> 18
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r7] = r6
                int r6 = r5 >> 12
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r9] = r6
                int r6 = r5 >> 6
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r8] = r6
                r5 = r5 & 63
                byte r5 = r1[r5]
                r6 = 3
                r2[r6] = r5
                int r3 = r3 + -1
                if (r3 != 0) goto L_0x0090
                boolean r3 = r0.f1986j
                if (r3 == 0) goto L_0x0088
                r3 = 5
                r2[r12] = r13
                goto L_0x0089
            L_0x0088:
                r3 = 4
            L_0x0089:
                int r5 = r3 + 1
                r2[r3] = r14
            L_0x008d:
                r3 = 19
                goto L_0x0093
            L_0x0090:
                r5 = 4
                goto L_0x0093
            L_0x0092:
                r5 = 0
            L_0x0093:
                int r6 = r11 + 3
                if (r6 > r4) goto L_0x00e9
                byte r15 = r18[r11]
                r15 = r15 & 255(0xff, float:3.57E-43)
                int r15 = r15 << 16
                int r16 = r11 + 1
                byte r10 = r18[r16]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r10 = r10 << 8
                r10 = r10 | r15
                int r11 = r11 + 2
                byte r11 = r18[r11]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r10 = r10 | r11
                int r11 = r10 >> 18
                r11 = r11 & 63
                byte r11 = r1[r11]
                r2[r5] = r11
                int r11 = r5 + 1
                int r15 = r10 >> 12
                r15 = r15 & 63
                byte r15 = r1[r15]
                r2[r11] = r15
                int r11 = r5 + 2
                int r15 = r10 >> 6
                r15 = r15 & 63
                byte r15 = r1[r15]
                r2[r11] = r15
                int r11 = r5 + 3
                r10 = r10 & 63
                byte r10 = r1[r10]
                r2[r11] = r10
                int r5 = r5 + 4
                int r3 = r3 + -1
                if (r3 != 0) goto L_0x00e7
                boolean r3 = r0.f1986j
                if (r3 == 0) goto L_0x00e0
                int r3 = r5 + 1
                r2[r5] = r13
                goto L_0x00e1
            L_0x00e0:
                r3 = r5
            L_0x00e1:
                int r5 = r3 + 1
                r2[r3] = r14
                r11 = r6
                goto L_0x008d
            L_0x00e7:
                r11 = r6
                goto L_0x0093
            L_0x00e9:
                if (r21 == 0) goto L_0x01bc
                int r6 = r0.f1982f
                int r10 = r11 - r6
                int r15 = r4 + -1
                if (r10 != r15) goto L_0x0139
                if (r6 <= 0) goto L_0x00fb
                byte[] r4 = r0.f1981e
                byte r4 = r4[r7]
                r7 = 1
                goto L_0x00fd
            L_0x00fb:
                byte r4 = r18[r11]
            L_0x00fd:
                r4 = r4 & 255(0xff, float:3.57E-43)
                int r4 = r4 << r12
                int r6 = r0.f1982f
                int r6 = r6 - r7
                r0.f1982f = r6
                int r6 = r5 + 1
                int r7 = r4 >> 6
                r7 = r7 & 63
                byte r7 = r1[r7]
                r2[r5] = r7
                int r5 = r6 + 1
                r4 = r4 & 63
                byte r1 = r1[r4]
                r2[r6] = r1
                boolean r1 = r0.f1984h
                if (r1 == 0) goto L_0x0125
                int r1 = r5 + 1
                r4 = 61
                r2[r5] = r4
                int r5 = r1 + 1
                r2[r1] = r4
            L_0x0125:
                boolean r1 = r0.f1985i
                if (r1 == 0) goto L_0x01e7
                boolean r1 = r0.f1986j
                if (r1 == 0) goto L_0x0132
                int r1 = r5 + 1
                r2[r5] = r13
                goto L_0x0133
            L_0x0132:
                r1 = r5
            L_0x0133:
                int r5 = r1 + 1
                r2[r1] = r14
                goto L_0x01e7
            L_0x0139:
                int r10 = r11 - r6
                int r4 = r4 - r8
                if (r10 != r4) goto L_0x01a2
                if (r6 <= r9) goto L_0x0146
                byte[] r4 = r0.f1981e
                byte r4 = r4[r7]
                r7 = 1
                goto L_0x014c
            L_0x0146:
                int r4 = r11 + 1
                byte r6 = r18[r11]
                r11 = r4
                r4 = r6
            L_0x014c:
                r4 = r4 & 255(0xff, float:3.57E-43)
                int r4 = r4 << r14
                int r6 = r0.f1982f
                if (r6 <= 0) goto L_0x015b
                byte[] r6 = r0.f1981e
                int r10 = r7 + 1
                byte r6 = r6[r7]
                r7 = r10
                goto L_0x015d
            L_0x015b:
                byte r6 = r18[r11]
            L_0x015d:
                r6 = r6 & 255(0xff, float:3.57E-43)
                int r6 = r6 << r8
                r4 = r4 | r6
                int r6 = r0.f1982f
                int r6 = r6 - r7
                r0.f1982f = r6
                int r6 = r5 + 1
                int r7 = r4 >> 12
                r7 = r7 & 63
                byte r7 = r1[r7]
                r2[r5] = r7
                int r5 = r6 + 1
                int r7 = r4 >> 6
                r7 = r7 & 63
                byte r7 = r1[r7]
                r2[r6] = r7
                int r6 = r5 + 1
                r4 = r4 & 63
                byte r1 = r1[r4]
                r2[r5] = r1
                boolean r1 = r0.f1984h
                if (r1 == 0) goto L_0x018d
                int r1 = r6 + 1
                r4 = 61
                r2[r6] = r4
                goto L_0x018e
            L_0x018d:
                r1 = r6
            L_0x018e:
                boolean r4 = r0.f1985i
                if (r4 == 0) goto L_0x01a0
                boolean r4 = r0.f1986j
                if (r4 == 0) goto L_0x019b
                int r4 = r1 + 1
                r2[r1] = r13
                r1 = r4
            L_0x019b:
                int r4 = r1 + 1
                r2[r1] = r14
                r1 = r4
            L_0x01a0:
                r5 = r1
                goto L_0x01e7
            L_0x01a2:
                boolean r1 = r0.f1985i
                if (r1 == 0) goto L_0x01e7
                if (r5 <= 0) goto L_0x01e7
                r1 = 19
                if (r3 == r1) goto L_0x01e7
                boolean r1 = r0.f1986j
                if (r1 == 0) goto L_0x01b5
                int r1 = r5 + 1
                r2[r5] = r13
                goto L_0x01b6
            L_0x01b5:
                r1 = r5
            L_0x01b6:
                int r4 = r1 + 1
                r2[r1] = r14
                r5 = r4
                goto L_0x01e7
            L_0x01bc:
                int r1 = r4 + -1
                if (r11 != r1) goto L_0x01cd
                byte[] r1 = r0.f1981e
                int r2 = r0.f1982f
                int r4 = r2 + 1
                r0.f1982f = r4
                byte r4 = r18[r11]
                r1[r2] = r4
                goto L_0x01e7
            L_0x01cd:
                int r4 = r4 - r8
                if (r11 != r4) goto L_0x01e7
                byte[] r1 = r0.f1981e
                int r2 = r0.f1982f
                int r4 = r2 + 1
                r0.f1982f = r4
                byte r4 = r18[r11]
                r1[r2] = r4
                int r2 = r0.f1982f
                int r4 = r2 + 1
                r0.f1982f = r4
                int r11 = r11 + r9
                byte r4 = r18[r11]
                r1[r2] = r4
            L_0x01e7:
                r0.f1978b = r5
                r0.f1983g = r3
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p059h.C0665a.C0667b.mo2802a(byte[], int, int, boolean):boolean");
        }
    }

    /* renamed from: a */
    public static byte[] m2428a(byte[] bArr, int i) {
        return m2429a(bArr, 0, bArr.length, i);
    }

    /* renamed from: a */
    public static byte[] m2429a(byte[] bArr, int i, int i2, int i3) {
        C0667b bVar = new C0667b(i3, (byte[]) null);
        int i4 = (i2 / 3) * 4;
        int i5 = 2;
        if (!bVar.f1984h) {
            int i6 = i2 % 3;
            if (i6 != 0) {
                if (i6 == 1) {
                    i4 += 2;
                } else if (i6 == 2) {
                    i4 += 3;
                }
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (bVar.f1985i && i2 > 0) {
            int i7 = ((i2 - 1) / 57) + 1;
            if (!bVar.f1986j) {
                i5 = 1;
            }
            i4 += i7 * i5;
        }
        bVar.f1977a = new byte[i4];
        bVar.mo2802a(bArr, i, i2, true);
        return bVar.f1977a;
    }
}
