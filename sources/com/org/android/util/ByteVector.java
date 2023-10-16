package com.org.android.util;

public class ByteVector<E> implements Cloneable {
    private static final Object a = new Object();
    private Object[] b;
    private int[] c;
    private int i;
    private boolean s;

    public ByteVector() {
        this(10);
    }

    public ByteVector(int i2) {
        this.s = false;
        if (i2 == 0) {
            this.c = ContainerHelpers.c;
            this.b = ContainerHelpers.b;
        } else {
            int $i0 = ContainerHelpers.idealIntArraySize(i2);
            this.c = new int[$i0];
            this.b = new Object[$i0];
        }
        this.i = 0;
    }

    private void d() {
        int $i0 = this.i;
        int[] $r1 = this.c;
        Object[] $r2 = this.b;
        int $i2 = 0;
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            Object $r3 = $r2[$i1];
            if ($r3 != a) {
                if ($i1 != $i2) {
                    $r1[$i2] = $r1[$i1];
                    $r2[$i2] = $r3;
                    $r2[$i1] = null;
                }
                $i2++;
            }
        }
        this.s = false;
        this.i = $i2;
    }

    public int a(int i2) {
        if (this.s) {
            d();
        }
        return this.c[i2];
    }

    public void a(int i2, Object obj) {
        int $i1 = ContainerHelpers.a(this.c, this.i, i2);
        if ($i1 >= 0) {
            this.b[$i1] = obj;
            return;
        }
        int $i12 = ~$i1;
        if ($i12 < this.i) {
            Object[] $r3 = this.b;
            if ($r3[$i12] == a) {
                this.c[$i12] = i2;
                $r3[$i12] = obj;
                return;
            }
        }
        if (this.s && this.i >= this.c.length) {
            d();
            $i12 = ~ContainerHelpers.a(this.c, this.i, i2);
        }
        int $i2 = this.i;
        if ($i2 >= this.c.length) {
            int $i22 = ContainerHelpers.idealIntArraySize($i2 + 1);
            int[] $r2 = new int[$i22];
            Object[] $r32 = new Object[$i22];
            int[] $r6 = this.c;
            System.arraycopy($r6, 0, $r2, 0, $r6.length);
            Object[] $r7 = this.b;
            System.arraycopy($r7, 0, $r32, 0, $r7.length);
            this.c = $r2;
            this.b = $r32;
        }
        int $i3 = this.i;
        if ($i3 - $i12 != 0) {
            int[] $r22 = this.c;
            int $i23 = $i12 + 1;
            System.arraycopy($r22, $i12, $r22, $i23, $i3 - $i12);
            Object[] $r33 = this.b;
            System.arraycopy($r33, $i12, $r33, $i23, this.i - $i12);
        }
        this.c[$i12] = i2;
        this.b[$i12] = obj;
        this.i++;
    }

    public Object add(int i2, Object $r1) {
        int $i0 = ContainerHelpers.a(this.c, this.i, i2);
        if ($i0 < 0) {
            return $r1;
        }
        Object[] $r4 = this.b;
        return $r4[$i0] == a ? $r1 : $r4[$i0];
    }

    public ByteVector clone() {
        try {
            ByteVector $r2 = (ByteVector) super.clone();
            $r2.c = (int[]) this.c.clone();
            $r2.b = (Object[]) this.b.clone();
            return $r2;
        } catch (CloneNotSupportedException $r5) {
            throw new AssertionError($r5);
        }
    }

    public void d(int i2, Object obj) {
        int $i2 = this.i;
        if ($i2 == 0 || i2 > this.c[$i2 - 1]) {
            if (this.s && this.i >= this.c.length) {
                d();
            }
            int $i22 = this.i;
            if ($i22 >= this.c.length) {
                int $i0 = ContainerHelpers.idealIntArraySize($i22 + 1);
                int[] $r2 = new int[$i0];
                Object[] $r3 = new Object[$i0];
                int[] $r4 = this.c;
                System.arraycopy($r4, 0, $r2, 0, $r4.length);
                Object[] $r5 = this.b;
                System.arraycopy($r5, 0, $r3, 0, $r5.length);
                this.c = $r2;
                this.b = $r3;
            }
            this.c[$i22] = i2;
            this.b[$i22] = obj;
            this.i = $i22 + 1;
            return;
        }
        a(i2, obj);
    }

    public Object get(int i2) {
        return add(i2, (Object) null);
    }

    public int read() {
        if (this.s) {
            d();
        }
        return this.i;
    }

    public Object read(int i2) {
        if (this.s) {
            d();
        }
        return this.b[i2];
    }

    public String toString() {
        if (read() <= 0) {
            return "{}";
        }
        StringBuilder $r1 = new StringBuilder(this.i * 28);
        $r1.append('{');
        for (int $i0 = 0; $i0 < this.i; $i0++) {
            if ($i0 > 0) {
                $r1.append(", ");
            }
            $r1.append(a($i0));
            $r1.append('=');
            Object $r2 = read($i0);
            if ($r2 != this) {
                $r1.append($r2);
            } else {
                $r1.append("(this Map)");
            }
        }
        $r1.append('}');
        return $r1.toString();
    }
}
