package com.org.android.util;

import a.a.c.f.b;
import java.util.ConcurrentModificationException;
import java.util.Map;

public class SimpleArrayMap<K, V> {
    static Object[] a;
    static Object[] e;
    static int i;
    static int j;
    Object[] b;
    int[] c;
    int n;

    public SimpleArrayMap() {
        this.c = ContainerHelpers.c;
        this.b = ContainerHelpers.b;
        this.n = 0;
    }

    public SimpleArrayMap(int i2) {
        if (i2 == 0) {
            this.c = ContainerHelpers.c;
            this.b = ContainerHelpers.b;
        } else {
            init(i2);
        }
        this.n = 0;
    }

    private static int a(int[] iArr, int i2, int i3) {
        try {
            return ContainerHelpers.a(iArr, i2, i3);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new ConcurrentModificationException();
        }
    }

    private static void a(int[] iArr, Object[] objArr, int $i0) {
        Class<b> cls = b.class;
        if (iArr.length == 8) {
            synchronized (cls) {
                if (i < 10) {
                    objArr[0] = e;
                    objArr[1] = iArr;
                    for (int $i02 = ($i0 << 1) - 1; $i02 >= 2; $i02--) {
                        objArr[$i02] = null;
                    }
                    e = objArr;
                    i++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (cls) {
                if (j < 10) {
                    objArr[0] = a;
                    objArr[1] = iArr;
                    for (int $i03 = ($i0 << 1) - 1; $i03 >= 2; $i03--) {
                        objArr[$i03] = null;
                    }
                    a = objArr;
                    j++;
                }
            }
        }
    }

    private void init(int $i0) {
        Class<b> cls = b.class;
        if ($i0 == 8) {
            synchronized (cls) {
                if (e != null) {
                    Object[] $r1 = e;
                    this.b = $r1;
                    e = (Object[]) $r1[0];
                    this.c = (int[]) $r1[1];
                    $r1[1] = null;
                    $r1[0] = null;
                    i--;
                    return;
                }
            }
        } else if ($i0 == 4) {
            synchronized (cls) {
                if (a != null) {
                    Object[] $r12 = a;
                    this.b = $r12;
                    a = (Object[]) $r12[0];
                    this.c = (int[]) $r12[1];
                    $r12[1] = null;
                    $r12[0] = null;
                    j--;
                    return;
                }
            }
        }
        this.c = new int[$i0];
        this.b = new Object[($i0 << 1)];
    }

    /* access modifiers changed from: package-private */
    public int a() {
        int $i2 = this.n;
        if ($i2 == 0) {
            return -1;
        }
        int $i1 = a(this.c, $i2, 0);
        if ($i1 < 0 || this.b[$i1 << 1] == null) {
            return $i1;
        }
        int $i3 = $i1 + 1;
        while ($i3 < $i2 && this.c[$i3] == 0) {
            if (this.b[$i3 << 1] == null) {
                return $i3;
            }
            $i3++;
        }
        int $i12 = $i1 - 1;
        while ($i12 >= 0 && this.c[$i12] == 0) {
            if (this.b[$i12 << 1] == null) {
                return $i12;
            }
            $i12--;
        }
        return ~$i3;
    }

    /* access modifiers changed from: package-private */
    public int a(Object obj, int i2) {
        int $i2 = this.n;
        if ($i2 == 0) {
            return -1;
        }
        int $i3 = a(this.c, $i2, i2);
        if ($i3 < 0 || obj.equals(this.b[$i3 << 1])) {
            return $i3;
        }
        int $i4 = $i3 + 1;
        while ($i4 < $i2 && this.c[$i4] == i2) {
            if (obj.equals(this.b[$i4 << 1])) {
                return $i4;
            }
            $i4++;
        }
        int $i22 = $i3 - 1;
        while ($i22 >= 0 && this.c[$i22] == i2) {
            if (obj.equals(this.b[$i22 << 1])) {
                return $i22;
            }
            $i22--;
        }
        return ~$i4;
    }

    public Object a(int $i2) {
        int $i4;
        Object[] $r1 = this.b;
        int $i3 = $i2 << 1;
        Object $r2 = $r1[$i3 + 1];
        int $i0 = this.n;
        if ($i0 <= 1) {
            a(this.c, $r1, $i0);
            this.c = ContainerHelpers.c;
            this.b = ContainerHelpers.b;
            $i4 = 0;
        } else {
            $i4 = $i0 - 1;
            int[] $r3 = this.c;
            int $i1 = 8;
            if ($r3.length <= 8 || $i0 >= $r3.length / 3) {
                if ($i2 < $i4) {
                    int[] $r32 = this.c;
                    int $i5 = $i2 + 1;
                    int $i12 = $i4 - $i2;
                    System.arraycopy($r32, $i5, $r32, $i2, $i12);
                    Object[] $r12 = this.b;
                    System.arraycopy($r12, $i5 << 1, $r12, $i3, $i12 << 1);
                }
                Object[] $r13 = this.b;
                int $i22 = $i4 << 1;
                $r13[$i22] = null;
                $r13[$i22 + 1] = null;
            } else {
                if ($i0 > 8) {
                    $i1 = $i0 + ($i0 >> 1);
                }
                int[] $r33 = this.c;
                Object[] $r14 = this.b;
                init($i1);
                if ($i0 == this.n) {
                    if ($i2 > 0) {
                        System.arraycopy($r33, 0, this.c, 0, $i2);
                        System.arraycopy($r14, 0, this.b, 0, $i3);
                    }
                    if ($i2 < $i4) {
                        int $i52 = $i2 + 1;
                        int $i13 = $i4 - $i2;
                        System.arraycopy($r33, $i52, this.c, $i2, $i13);
                        System.arraycopy($r14, $i52 << 1, this.b, $i3, $i13 << 1);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        }
        if ($i0 == this.n) {
            this.n = $i4;
            return $r2;
        }
        throw new ConcurrentModificationException();
    }

    public void clear() {
        int $i0 = this.n;
        if ($i0 > 0) {
            int[] $r1 = this.c;
            Object[] $r2 = this.b;
            this.c = ContainerHelpers.c;
            this.b = ContainerHelpers.b;
            this.n = 0;
            a($r1, $r2, $i0);
        }
        if (this.n > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear(int i2) {
        int $i0 = this.n;
        int[] $r1 = this.c;
        if ($r1.length < i2) {
            Object[] $r2 = this.b;
            init(i2);
            if (this.n > 0) {
                System.arraycopy($r1, 0, this.c, 0, $i0);
                System.arraycopy($r2, 0, this.b, 0, $i0 << 1);
            }
            a($r1, $r2, $i0);
        }
        if (this.n != $i0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return indexOfKey(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return indexOfValue(obj) >= 0;
    }

    public boolean equals(Object $r5) {
        if (this == $r5) {
            return true;
        }
        if ($r5 instanceof SimpleArrayMap) {
            SimpleArrayMap $r6 = (SimpleArrayMap) $r5;
            if (size() != $r6.size()) {
                return false;
            }
            int $i0 = 0;
            while ($i0 < this.n) {
                try {
                    try {
                        Object $r52 = keyAt($i0);
                        Object $r7 = valueAt($i0);
                        Object $r8 = $r6.get($r52);
                        if ($r7 == null) {
                            if ($r8 != null) {
                                return false;
                            }
                            if (!$r6.containsKey($r52)) {
                                return false;
                            }
                        } else if (!$r7.equals($r8)) {
                            return false;
                        }
                        $i0++;
                    } catch (ClassCastException e2) {
                        return false;
                    }
                } catch (NullPointerException e3) {
                    return false;
                }
            }
            return true;
        } else if (!($r5 instanceof Map)) {
            return false;
        } else {
            Map $r9 = (Map) $r5;
            if (size() != $r9.size()) {
                return false;
            }
            int $i02 = 0;
            while ($i02 < this.n) {
                try {
                    try {
                        Object $r53 = keyAt($i02);
                        Object $r72 = valueAt($i02);
                        Object $r82 = $r9.get($r53);
                        if ($r72 == null) {
                            if ($r82 != null) {
                                return false;
                            }
                            if (!$r9.containsKey($r53)) {
                                return false;
                            }
                        } else if (!$r72.equals($r82)) {
                            return false;
                        }
                        $i02++;
                    } catch (ClassCastException e4) {
                        return false;
                    }
                } catch (NullPointerException e5) {
                    return false;
                }
            }
            return true;
        }
    }

    public Object get(Object obj) {
        int $i0 = indexOfKey(obj);
        if ($i0 >= 0) {
            return this.b[($i0 << 1) + 1];
        }
        return null;
    }

    public int hashCode() {
        int[] $r1 = this.c;
        Object[] $r2 = this.b;
        int $i0 = this.n;
        int $i3 = 0;
        int $i4 = 0;
        int $i5 = 1;
        while ($i3 < $i0) {
            Object $r3 = $r2[$i5];
            $i4 += ($r3 == null ? 0 : $r3.hashCode()) ^ $r1[$i3];
            $i3++;
            $i5 += 2;
        }
        return $i4;
    }

    public int indexOfKey(Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }

    /* access modifiers changed from: package-private */
    public int indexOfValue(Object $r2) {
        int $i0 = this.n * 2;
        Object[] $r1 = this.b;
        if ($r2 == null) {
            for (int $i1 = 1; $i1 < $i0; $i1 += 2) {
                if ($r1[$i1] == null) {
                    return $i1 >> 1;
                }
            }
            return -1;
        }
        for (int $i12 = 1; $i12 < $i0; $i12 += 2) {
            if ($r2.equals($r1[$i12])) {
                return $i12 >> 1;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.n <= 0;
    }

    public Object keyAt(int i2) {
        return this.b[i2 << 1];
    }

    public Object put(Object $r2, Object obj) {
        int $i1;
        int $i2;
        int $i0 = this.n;
        if ($r2 == null) {
            $i2 = a();
            $i1 = 0;
        } else {
            $i1 = $r2.hashCode();
            $i2 = a($r2, $i1);
        }
        if ($i2 >= 0) {
            int $i02 = ($i2 << 1) + 1;
            Object[] $r3 = this.b;
            Object $r22 = $r3[$i02];
            $r3[$i02] = obj;
            return $r22;
        }
        int $i22 = ~$i2;
        if ($i0 >= this.c.length) {
            int $i3 = 4;
            if ($i0 >= 8) {
                $i3 = ($i0 >> 1) + $i0;
            } else if ($i0 >= 4) {
                $i3 = 8;
            }
            int[] $r4 = this.c;
            Object[] $r32 = this.b;
            init($i3);
            if ($i0 == this.n) {
                int[] $r5 = this.c;
                if ($r5.length > 0) {
                    System.arraycopy($r4, 0, $r5, 0, $r4.length);
                    System.arraycopy($r32, 0, this.b, 0, $r32.length);
                }
                a($r4, $r32, $i0);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if ($i22 < $i0) {
            int[] $r42 = this.c;
            int $i4 = $i22 + 1;
            System.arraycopy($r42, $i22, $r42, $i4, $i0 - $i22);
            Object[] $r33 = this.b;
            System.arraycopy($r33, $i22 << 1, $r33, $i4 << 1, (this.n - $i22) << 1);
        }
        int $i32 = this.n;
        if ($i0 == $i32) {
            int[] $r43 = this.c;
            if ($i22 < $r43.length) {
                $r43[$i22] = $i1;
                Object[] $r34 = this.b;
                int $i03 = $i22 << 1;
                $r34[$i03] = $r2;
                $r34[$i03 + 1] = obj;
                this.n = $i32 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public Object remove(Object obj) {
        int $i0 = indexOfKey(obj);
        if ($i0 >= 0) {
            return a($i0);
        }
        return null;
    }

    public Object setValueAt(int i2, Object obj) {
        int $i0 = (i2 << 1) + 1;
        Object[] $r2 = this.b;
        Object $r3 = $r2[$i0];
        $r2[$i0] = obj;
        return $r3;
    }

    public int size() {
        return this.n;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder $r1 = new StringBuilder(this.n * 28);
        $r1.append('{');
        for (int $i0 = 0; $i0 < this.n; $i0++) {
            if ($i0 > 0) {
                $r1.append(", ");
            }
            Object $r2 = keyAt($i0);
            if ($r2 != this) {
                $r1.append($r2);
            } else {
                $r1.append("(this Map)");
            }
            $r1.append('=');
            Object $r22 = valueAt($i0);
            if ($r22 != this) {
                $r1.append($r22);
            } else {
                $r1.append("(this Map)");
            }
        }
        $r1.append('}');
        return $r1.toString();
    }

    public Object valueAt(int i2) {
        return this.b[(i2 << 1) + 1];
    }
}
