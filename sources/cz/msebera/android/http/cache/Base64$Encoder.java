package cz.msebera.android.http.cache;

class Base64$Encoder extends Base64$Coder {
    private static final byte[] ENCODE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] ENCODE_WEBSAFE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private final byte[] alphabet;
    private int count;
    public final boolean do_cr;
    public final boolean do_newline;
    public final boolean do_padding;
    private final byte[] tail;
    int tailLen;

    public Base64$Encoder(int i, byte[] bArr) {
        this.output = bArr;
        boolean $z0 = true;
        this.do_padding = (i & 1) == 0;
        this.do_newline = (i & 2) == 0;
        this.do_cr = (i & 4) == 0 ? false : $z0;
        this.alphabet = (i & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
        this.tail = new byte[2];
        this.tailLen = 0;
        this.count = this.do_newline ? (byte) 19 : -1;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0113 A[SYNTHETIC] */
    public boolean process(byte[] r16, int r17, int r18, boolean r19) {
        /*
            r15 = this;
            byte[] r1 = r15.alphabet
            byte[] r2 = r15.output
            int r3 = r15.count
            int r4 = r18 + r17
            int r5 = r15.tailLen
            r18 = 0
            if (r5 == 0) goto L_0x0067
            r6 = 1
            if (r5 == r6) goto L_0x003b
            r6 = 2
            if (r5 == r6) goto L_0x0015
            goto L_0x0067
        L_0x0015:
            int r5 = r17 + 1
            if (r5 > r4) goto L_0x0067
            byte[] r7 = r15.tail
            r6 = 0
            byte r8 = r7[r6]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r10 = r9 << 16
            r6 = 1
            byte r8 = r7[r6]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r11 = r9 << 8
            r10 = r11 | r10
            byte r8 = r16[r17]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            r6 = 0
            r15.tailLen = r6
            r17 = r10 | r9
            goto L_0x006b
        L_0x003b:
            int r5 = r17 + 2
            if (r5 > r4) goto L_0x0067
            byte[] r7 = r15.tail
            r6 = 0
            byte r8 = r7[r6]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r5 = r9 << 16
            int r10 = r17 + 1
            byte r8 = r16[r17]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r17 = r9 << 8
            r17 = r5 | r17
            int r5 = r10 + 1
            byte r8 = r16[r10]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            r0 = r17
            r0 = r0 | r9
            r17 = r0
            r6 = 0
            r15.tailLen = r6
            goto L_0x006b
        L_0x0067:
            r5 = r17
            r17 = -1
        L_0x006b:
            r6 = -1
            r0 = r17
            if (r0 == r6) goto L_0x00ae
            int r10 = r17 >> 18
            r10 = r10 & 63
            byte r8 = r1[r10]
            r6 = 0
            r2[r6] = r8
            int r10 = r17 >> 12
            r10 = r10 & 63
            byte r8 = r1[r10]
            r6 = 1
            r2[r6] = r8
            int r10 = r17 >> 6
            r10 = r10 & 63
            byte r8 = r1[r10]
            r6 = 2
            r2[r6] = r8
            r17 = r17 & 63
            byte r8 = r1[r17]
            r6 = 3
            r2[r6] = r8
            int r3 = r3 + -1
            if (r3 != 0) goto L_0x00ab
            boolean r12 = r15.do_cr
            if (r12 == 0) goto L_0x00a1
            r8 = 5
            r6 = 4
            r13 = 13
            r2[r6] = r13
            goto L_0x00a2
        L_0x00a1:
            r8 = 4
        L_0x00a2:
            int r17 = r8 + 1
            r6 = 10
            r2[r8] = r6
        L_0x00a8:
            r3 = 19
            goto L_0x00b0
        L_0x00ab:
            r17 = 4
            goto L_0x00b0
        L_0x00ae:
            r17 = 0
        L_0x00b0:
            int r10 = r5 + 3
            if (r10 > r4) goto L_0x0113
            byte r8 = r16[r5]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r11 = r9 << 16
            int r14 = r5 + 1
            byte r8 = r16[r14]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r14 = r9 << 8
            r11 = r14 | r11
            int r5 = r5 + 2
            byte r8 = r16[r5]
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            r5 = r11 | r9
            int r11 = r5 >> 18
            r11 = r11 & 63
            byte r8 = r1[r11]
            r2[r17] = r8
            int r11 = r17 + 1
            int r14 = r5 >> 12
            r14 = r14 & 63
            byte r8 = r1[r14]
            r2[r11] = r8
            int r11 = r17 + 2
            int r14 = r5 >> 6
            r14 = r14 & 63
            byte r8 = r1[r14]
            r2[r11] = r8
            int r11 = r17 + 3
            r5 = r5 & 63
            byte r8 = r1[r5]
            r2[r11] = r8
            int r17 = r17 + 4
            int r3 = r3 + -1
            if (r3 != 0) goto L_0x0111
            boolean r12 = r15.do_cr
            if (r12 == 0) goto L_0x0107
            int r3 = r17 + 1
            r6 = 13
            r2[r17] = r6
            goto L_0x0109
        L_0x0107:
            r3 = r17
        L_0x0109:
            int r17 = r3 + 1
            r6 = 10
            r2[r3] = r6
            r5 = r10
            goto L_0x00a8
        L_0x0111:
            r5 = r10
            goto L_0x00b0
        L_0x0113:
            if (r19 == 0) goto L_0x021a
            int r10 = r15.tailLen
            int r11 = r5 - r10
            int r14 = r4 + -1
            if (r11 != r14) goto L_0x017b
            if (r10 <= 0) goto L_0x0129
            byte[] r0 = r15.tail
            r16 = r0
            r6 = 0
            byte r8 = r16[r6]
            r18 = 1
            goto L_0x012b
        L_0x0129:
            byte r8 = r16[r5]
        L_0x012b:
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r4 = r9 << 4
            int r5 = r15.tailLen
            int r18 = r5 - r18
            r0 = r18
            r15.tailLen = r0
            int r18 = r17 + 1
            int r5 = r4 >> 6
            r5 = r5 & 63
            byte r8 = r1[r5]
            r2[r17] = r8
            int r17 = r18 + 1
            r4 = r4 & 63
            byte r8 = r1[r4]
            r2[r18] = r8
            boolean r0 = r15.do_padding
            r19 = r0
            if (r19 == 0) goto L_0x015d
            int r18 = r17 + 1
            r6 = 61
            r2[r17] = r6
            int r17 = r18 + 1
            r6 = 61
            r2[r18] = r6
        L_0x015d:
            boolean r0 = r15.do_newline
            r19 = r0
            if (r19 == 0) goto L_0x0252
            boolean r0 = r15.do_cr
            r19 = r0
            if (r19 == 0) goto L_0x0170
            int r18 = r17 + 1
            r6 = 13
            r2[r17] = r6
            goto L_0x0172
        L_0x0170:
            r18 = r17
        L_0x0172:
            int r17 = r18 + 1
            r6 = 10
            r2[r18] = r6
            goto L_0x0252
        L_0x017b:
            int r11 = r5 - r10
            r6 = 2
            int r4 = r4 - r6
            if (r11 != r4) goto L_0x01f8
            r6 = 1
            if (r10 <= r6) goto L_0x018c
            byte[] r7 = r15.tail
            r6 = 0
            byte r8 = r7[r6]
            r18 = 1
            goto L_0x0190
        L_0x018c:
            byte r8 = r16[r5]
            int r5 = r5 + 1
        L_0x0190:
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r4 = r9 << 10
            int r10 = r15.tailLen
            if (r10 <= 0) goto L_0x01a3
            byte[] r0 = r15.tail
            r16 = r0
            byte r8 = r16[r18]
            int r18 = r18 + 1
            goto L_0x01a5
        L_0x01a3:
            byte r8 = r16[r5]
        L_0x01a5:
            r6 = 255(0xff, float:3.57E-43)
            r9 = r8 & r6
            int r5 = r9 << 2
            r4 = r4 | r5
            int r5 = r15.tailLen
            int r18 = r5 - r18
            r0 = r18
            r15.tailLen = r0
            int r18 = r17 + 1
            int r5 = r4 >> 12
            r5 = r5 & 63
            byte r8 = r1[r5]
            r2[r17] = r8
            int r17 = r18 + 1
            int r5 = r4 >> 6
            r5 = r5 & 63
            byte r8 = r1[r5]
            r2[r18] = r8
            int r18 = r17 + 1
            r4 = r4 & 63
            byte r8 = r1[r4]
            r2[r17] = r8
            boolean r0 = r15.do_padding
            r19 = r0
            if (r19 == 0) goto L_0x01dd
            int r17 = r18 + 1
            r6 = 61
            r2[r18] = r6
            goto L_0x01df
        L_0x01dd:
            r17 = r18
        L_0x01df:
            boolean r0 = r15.do_newline
            r19 = r0
            if (r19 == 0) goto L_0x01f7
            boolean r0 = r15.do_cr
            r19 = r0
            if (r19 == 0) goto L_0x01f1
            r6 = 13
            r2[r17] = r6
            int r17 = r17 + 1
        L_0x01f1:
            r6 = 10
            r2[r17] = r6
            int r17 = r17 + 1
        L_0x01f7:
            goto L_0x0252
        L_0x01f8:
            boolean r0 = r15.do_newline
            r19 = r0
            if (r19 == 0) goto L_0x0252
            if (r17 <= 0) goto L_0x0252
            r6 = 19
            if (r3 == r6) goto L_0x0252
            boolean r0 = r15.do_cr
            r19 = r0
            if (r19 == 0) goto L_0x0211
            int r18 = r17 + 1
            r6 = 13
            r2[r17] = r6
            goto L_0x0213
        L_0x0211:
            r18 = r17
        L_0x0213:
            r6 = 10
            r2[r18] = r6
            int r17 = r18 + 1
            goto L_0x0252
        L_0x021a:
            int r18 = r4 + -1
            r0 = r18
            if (r5 != r0) goto L_0x022f
            byte[] r1 = r15.tail
            int r0 = r15.tailLen
            r18 = r0
            int r4 = r18 + 1
            r15.tailLen = r4
            byte r8 = r16[r5]
            r1[r18] = r8
            goto L_0x0252
        L_0x022f:
            r6 = 2
            int r18 = r4 - r6
            r0 = r18
            if (r5 != r0) goto L_0x0252
            byte[] r1 = r15.tail
            int r0 = r15.tailLen
            r18 = r0
            int r4 = r18 + 1
            r15.tailLen = r4
            byte r8 = r16[r5]
            r1[r18] = r8
            int r0 = r15.tailLen
            r18 = r0
            int r4 = r18 + 1
            r15.tailLen = r4
            int r4 = r5 + 1
            byte r8 = r16[r4]
            r1[r18] = r8
        L_0x0252:
            r0 = r17
            r15.op = r0
            r15.count = r3
            r6 = 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.cache.Base64$Encoder.process(byte[], int, int, boolean):boolean");
    }
}
