package com.org.android.ui.asm;

final class ByteVector {
    public static int[] get(int[] $r0, int i, int i2) {
        if (i + 1 > $r0.length) {
            int[] $r1 = new int[read(i)];
            System.arraycopy($r0, 0, $r1, 0, i);
            $r0 = $r1;
        }
        $r0[i] = i2;
        return $r0;
    }

    public static int read(int i) {
        if (i <= 4) {
            return 8;
        }
        return i * 2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object[] read(java.lang.Object[] r8, int r9, java.lang.Object r10) {
        /*
            int r0 = r9 + 1
            int r1 = r8.length
            if (r0 <= r1) goto L_0x001f
            java.lang.Class r2 = r8.getClass()
            java.lang.Class r2 = r2.getComponentType()
            int r0 = read(r9)
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r2, r0)
            r5 = r3
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r4 = r5
            r6 = 0
            r7 = 0
            java.lang.System.arraycopy(r8, r6, r4, r7, r9)
            r8 = r4
        L_0x001f:
            r8[r9] = r10
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.ui.asm.ByteVector.read(java.lang.Object[], int, java.lang.Object):java.lang.Object[]");
    }
}
