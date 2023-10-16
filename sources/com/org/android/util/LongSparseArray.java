package com.org.android.util;

public class LongSparseArray<E> implements Cloneable {
    private static final Object DELETED = new Object();
    private boolean mGarbage;
    private long[] mKeys;
    private int mSize;
    private Object[] mValues;

    public LongSparseArray() {
        this(10);
    }

    public LongSparseArray(int i) {
        this.mGarbage = false;
        if (i == 0) {
            this.mKeys = ContainerHelpers.t;
            this.mValues = ContainerHelpers.b;
        } else {
            int $i0 = ContainerHelpers.idealLongArraySize(i);
            this.mKeys = new long[$i0];
            this.mValues = new Object[$i0];
        }
        this.mSize = 0;
    }

    private void gc() {
        int $i0 = this.mSize;
        long[] $r1 = this.mKeys;
        Object[] $r2 = this.mValues;
        int $i2 = 0;
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            Object $r3 = $r2[$i1];
            if ($r3 != DELETED) {
                if ($i1 != $i2) {
                    $r1[$i2] = $r1[$i1];
                    $r2[$i2] = $r3;
                    $r2[$i1] = null;
                }
                $i2++;
            }
        }
        this.mGarbage = false;
        this.mSize = $i2;
    }

    public void append(long j, Object obj) {
        int $i2 = this.mSize;
        if ($i2 == 0 || j > this.mKeys[$i2 - 1]) {
            if (this.mGarbage && this.mSize >= this.mKeys.length) {
                gc();
            }
            int $i22 = this.mSize;
            if ($i22 >= this.mKeys.length) {
                int $i0 = ContainerHelpers.idealLongArraySize($i22 + 1);
                long[] $r2 = new long[$i0];
                Object[] $r3 = new Object[$i0];
                long[] $r4 = this.mKeys;
                System.arraycopy($r4, 0, $r2, 0, $r4.length);
                Object[] $r5 = this.mValues;
                System.arraycopy($r5, 0, $r3, 0, $r5.length);
                this.mKeys = $r2;
                this.mValues = $r3;
            }
            this.mKeys[$i22] = j;
            this.mValues[$i22] = obj;
            this.mSize = $i22 + 1;
            return;
        }
        put(j, obj);
    }

    public LongSparseArray clone() {
        try {
            LongSparseArray $r2 = (LongSparseArray) super.clone();
            $r2.mKeys = (long[]) this.mKeys.clone();
            $r2.mValues = (Object[]) this.mValues.clone();
            return $r2;
        } catch (CloneNotSupportedException $r5) {
            throw new AssertionError($r5);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r2 = r6.mValues;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void delete(long r7) {
        /*
            r6 = this;
            long[] r0 = r6.mKeys
            int r1 = r6.mSize
            int r1 = com.org.android.util.ContainerHelpers.binarySearch(r0, r1, r7)
            if (r1 < 0) goto L_0x0017
            java.lang.Object[] r2 = r6.mValues
            r3 = r2[r1]
            java.lang.Object r4 = DELETED
            if (r3 == r4) goto L_0x0017
            r2[r1] = r4
            r5 = 1
            r6.mGarbage = r5
        L_0x0017:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.util.LongSparseArray.delete(long):void");
    }

    public Object get(long j) {
        return get(j, (Object) null);
    }

    public Object get(long j, Object $r2) {
        int $i0 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, j);
        if ($i0 < 0) {
            return $r2;
        }
        Object[] $r1 = this.mValues;
        return $r1[$i0] == DELETED ? $r2 : $r1[$i0];
    }

    public long keyAt(int i) {
        if (this.mGarbage) {
            gc();
        }
        return this.mKeys[i];
    }

    public void put(long j, Object obj) {
        int $i1 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, j);
        if ($i1 >= 0) {
            this.mValues[$i1] = obj;
            return;
        }
        int $i12 = ~$i1;
        if ($i12 < this.mSize) {
            Object[] $r3 = this.mValues;
            if ($r3[$i12] == DELETED) {
                this.mKeys[$i12] = j;
                $r3[$i12] = obj;
                return;
            }
        }
        if (this.mGarbage && this.mSize >= this.mKeys.length) {
            gc();
            $i12 = ~ContainerHelpers.binarySearch(this.mKeys, this.mSize, j);
        }
        int $i2 = this.mSize;
        if ($i2 >= this.mKeys.length) {
            int $i22 = ContainerHelpers.idealLongArraySize($i2 + 1);
            long[] $r2 = new long[$i22];
            Object[] $r32 = new Object[$i22];
            long[] $r6 = this.mKeys;
            System.arraycopy($r6, 0, $r2, 0, $r6.length);
            Object[] $r7 = this.mValues;
            System.arraycopy($r7, 0, $r32, 0, $r7.length);
            this.mKeys = $r2;
            this.mValues = $r32;
        }
        int $i3 = this.mSize;
        if ($i3 - $i12 != 0) {
            long[] $r22 = this.mKeys;
            int $i23 = $i12 + 1;
            System.arraycopy($r22, $i12, $r22, $i23, $i3 - $i12);
            Object[] $r33 = this.mValues;
            System.arraycopy($r33, $i12, $r33, $i23, this.mSize - $i12);
        }
        this.mKeys[$i12] = j;
        this.mValues[$i12] = obj;
        this.mSize++;
    }

    public int size() {
        if (this.mGarbage) {
            gc();
        }
        return this.mSize;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder $r1 = new StringBuilder(this.mSize * 28);
        $r1.append('{');
        for (int $i0 = 0; $i0 < this.mSize; $i0++) {
            if ($i0 > 0) {
                $r1.append(", ");
            }
            $r1.append(keyAt($i0));
            $r1.append('=');
            Object $r2 = valueAt($i0);
            if ($r2 != this) {
                $r1.append($r2);
            } else {
                $r1.append("(this Map)");
            }
        }
        $r1.append('}');
        return $r1.toString();
    }

    public Object valueAt(int i) {
        if (this.mGarbage) {
            gc();
        }
        return this.mValues[i];
    }
}
