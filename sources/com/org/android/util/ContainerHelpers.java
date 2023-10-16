package com.org.android.util;

class ContainerHelpers {
    static final Object[] b = new Object[0];
    static final int[] c = new int[0];
    static final long[] t = new long[0];

    static int a(int[] iArr, int i, int i2) {
        int $i2 = i - 1;
        int $i3 = 0;
        while ($i3 <= $i2) {
            int $i4 = ($i3 + $i2) >>> 1;
            int $i1 = iArr[$i4];
            if ($i1 < i2) {
                $i3 = $i4 + 1;
            } else if ($i1 <= i2) {
                return $i4;
            } else {
                $i2 = $i4 - 1;
            }
        }
        return ~$i3;
    }

    static int binarySearch(long[] jArr, int i, long j) {
        int $i3 = i - 1;
        int $i4 = 0;
        while ($i4 <= $i3) {
            int $i5 = ($i4 + $i3) >>> 1;
            long $l1 = jArr[$i5];
            if ($l1 < j) {
                $i4 = $i5 + 1;
            } else if ($l1 <= j) {
                return $i5;
            } else {
                $i3 = $i5 - 1;
            }
        }
        return ~$i4;
    }

    public static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int idealByteArraySize(int i) {
        for (int $i1 = 4; $i1 < 32; $i1++) {
            int $i2 = (1 << $i1) - 12;
            if (i <= $i2) {
                return $i2;
            }
        }
        return i;
    }

    public static int idealIntArraySize(int i) {
        return idealByteArraySize(i * 4) / 4;
    }

    public static int idealLongArraySize(int i) {
        return idealByteArraySize(i * 8) / 8;
    }
}
