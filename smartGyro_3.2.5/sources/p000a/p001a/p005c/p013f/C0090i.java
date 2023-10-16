package p000a.p001a.p005c.p013f;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* renamed from: a.a.c.f.i */
public class C0090i<K, V> {

    /* renamed from: a */
    static Object[] f235a;

    /* renamed from: b */
    static int f236b;

    /* renamed from: c */
    static Object[] f237c;

    /* renamed from: d */
    static int f238d;

    /* renamed from: e */
    int[] f239e;

    /* renamed from: f */
    Object[] f240f;

    /* renamed from: g */
    int f241g;

    public C0090i() {
        this.f239e = C0079c.f204a;
        this.f240f = C0079c.f206c;
        this.f241g = 0;
    }

    public C0090i(int i) {
        if (i == 0) {
            this.f239e = C0079c.f204a;
            this.f240f = C0079c.f206c;
        } else {
            m316e(i);
        }
        this.f241g = 0;
    }

    /* renamed from: a */
    private static int m314a(int[] iArr, int i, int i2) {
        try {
            return C0079c.m271a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    /* renamed from: a */
    private static void m315a(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (C0078b.class) {
                if (f238d < 10) {
                    objArr[0] = f237c;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f237c = objArr;
                    f238d++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (C0078b.class) {
                if (f236b < 10) {
                    objArr[0] = f235a;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f235a = objArr;
                    f236b++;
                }
            }
        }
    }

    /* renamed from: e */
    private void m316e(int i) {
        if (i == 8) {
            synchronized (C0078b.class) {
                if (f237c != null) {
                    Object[] objArr = f237c;
                    this.f240f = objArr;
                    f237c = (Object[]) objArr[0];
                    this.f239e = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f238d--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (C0078b.class) {
                if (f235a != null) {
                    Object[] objArr2 = f235a;
                    this.f240f = objArr2;
                    f235a = (Object[]) objArr2[0];
                    this.f239e = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f236b--;
                    return;
                }
            }
        }
        this.f239e = new int[i];
        this.f240f = new Object[(i << 1)];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo360a() {
        int i = this.f241g;
        if (i == 0) {
            return -1;
        }
        int a = m314a(this.f239e, i, 0);
        if (a < 0 || this.f240f[a << 1] == null) {
            return a;
        }
        int i2 = a + 1;
        while (i2 < i && this.f239e[i2] == 0) {
            if (this.f240f[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        int i3 = a - 1;
        while (i3 >= 0 && this.f239e[i3] == 0) {
            if (this.f240f[i3 << 1] == null) {
                return i3;
            }
            i3--;
        }
        return i2 ^ -1;
    }

    /* renamed from: a */
    public int mo361a(Object obj) {
        return obj == null ? mo360a() : mo362a(obj, obj.hashCode());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo362a(Object obj, int i) {
        int i2 = this.f241g;
        if (i2 == 0) {
            return -1;
        }
        int a = m314a(this.f239e, i2, i);
        if (a < 0 || obj.equals(this.f240f[a << 1])) {
            return a;
        }
        int i3 = a + 1;
        while (i3 < i2 && this.f239e[i3] == i) {
            if (obj.equals(this.f240f[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        int i4 = a - 1;
        while (i4 >= 0 && this.f239e[i4] == i) {
            if (obj.equals(this.f240f[i4 << 1])) {
                return i4;
            }
            i4--;
        }
        return i3 ^ -1;
    }

    /* renamed from: a */
    public V mo363a(int i, V v) {
        int i2 = (i << 1) + 1;
        V[] vArr = this.f240f;
        V v2 = vArr[i2];
        vArr[i2] = v;
        return v2;
    }

    /* renamed from: a */
    public void mo364a(int i) {
        int i2 = this.f241g;
        int[] iArr = this.f239e;
        if (iArr.length < i) {
            Object[] objArr = this.f240f;
            m316e(i);
            if (this.f241g > 0) {
                System.arraycopy(iArr, 0, this.f239e, 0, i2);
                System.arraycopy(objArr, 0, this.f240f, 0, i2 << 1);
            }
            m315a(iArr, objArr, i2);
        }
        if (this.f241g != i2) {
            throw new ConcurrentModificationException();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo365b(Object obj) {
        int i = this.f241g * 2;
        Object[] objArr = this.f240f;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public K mo366b(int i) {
        return this.f240f[i << 1];
    }

    /* renamed from: c */
    public V mo367c(int i) {
        int i2;
        V[] vArr = this.f240f;
        int i3 = i << 1;
        V v = vArr[i3 + 1];
        int i4 = this.f241g;
        if (i4 <= 1) {
            m315a(this.f239e, (Object[]) vArr, i4);
            this.f239e = C0079c.f204a;
            this.f240f = C0079c.f206c;
            i2 = 0;
        } else {
            i2 = i4 - 1;
            int[] iArr = this.f239e;
            int i5 = 8;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i < i2) {
                    int[] iArr2 = this.f239e;
                    int i6 = i + 1;
                    int i7 = i2 - i;
                    System.arraycopy(iArr2, i6, iArr2, i, i7);
                    Object[] objArr = this.f240f;
                    System.arraycopy(objArr, i6 << 1, objArr, i3, i7 << 1);
                }
                Object[] objArr2 = this.f240f;
                int i8 = i2 << 1;
                objArr2[i8] = null;
                objArr2[i8 + 1] = null;
            } else {
                if (i4 > 8) {
                    i5 = i4 + (i4 >> 1);
                }
                int[] iArr3 = this.f239e;
                Object[] objArr3 = this.f240f;
                m316e(i5);
                if (i4 == this.f241g) {
                    if (i > 0) {
                        System.arraycopy(iArr3, 0, this.f239e, 0, i);
                        System.arraycopy(objArr3, 0, this.f240f, 0, i3);
                    }
                    if (i < i2) {
                        int i9 = i + 1;
                        int i10 = i2 - i;
                        System.arraycopy(iArr3, i9, this.f239e, i, i10);
                        System.arraycopy(objArr3, i9 << 1, this.f240f, i3, i10 << 1);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        }
        if (i4 == this.f241g) {
            this.f241g = i2;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    public void clear() {
        int i = this.f241g;
        if (i > 0) {
            int[] iArr = this.f239e;
            Object[] objArr = this.f240f;
            this.f239e = C0079c.f204a;
            this.f240f = C0079c.f206c;
            this.f241g = 0;
            m315a(iArr, objArr, i);
        }
        if (this.f241g > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return mo361a(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return mo365b(obj) >= 0;
    }

    /* renamed from: d */
    public V mo371d(int i) {
        return this.f240f[(i << 1) + 1];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0090i) {
            C0090i iVar = (C0090i) obj;
            if (size() != iVar.size()) {
                return false;
            }
            int i = 0;
            while (i < this.f241g) {
                try {
                    Object b = mo366b(i);
                    Object d = mo371d(i);
                    Object obj2 = iVar.get(b);
                    if (d == null) {
                        if (obj2 != null || !iVar.containsKey(b)) {
                            return false;
                        }
                    } else if (!d.equals(obj2)) {
                        return false;
                    }
                    i++;
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            int i2 = 0;
            while (i2 < this.f241g) {
                try {
                    Object b2 = mo366b(i2);
                    Object d2 = mo371d(i2);
                    Object obj3 = map.get(b2);
                    if (d2 == null) {
                        if (obj3 != null || !map.containsKey(b2)) {
                            return false;
                        }
                    } else if (!d2.equals(obj3)) {
                        return false;
                    }
                    i2++;
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public V get(Object obj) {
        int a = mo361a(obj);
        if (a >= 0) {
            return this.f240f[(a << 1) + 1];
        }
        return null;
    }

    public int hashCode() {
        int[] iArr = this.f239e;
        Object[] objArr = this.f240f;
        int i = this.f241g;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < i) {
            Object obj = objArr[i4];
            i3 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i2];
            i2++;
            i4 += 2;
        }
        return i3;
    }

    public boolean isEmpty() {
        return this.f241g <= 0;
    }

    public V put(K k, V v) {
        int i;
        int i2;
        int i3 = this.f241g;
        if (k == null) {
            i2 = mo360a();
            i = 0;
        } else {
            int hashCode = k.hashCode();
            i = hashCode;
            i2 = mo362a((Object) k, hashCode);
        }
        if (i2 >= 0) {
            int i4 = (i2 << 1) + 1;
            V[] vArr = this.f240f;
            V v2 = vArr[i4];
            vArr[i4] = v;
            return v2;
        }
        int i5 = i2 ^ -1;
        if (i3 >= this.f239e.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            int[] iArr = this.f239e;
            Object[] objArr = this.f240f;
            m316e(i6);
            if (i3 == this.f241g) {
                int[] iArr2 = this.f239e;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr, 0, this.f240f, 0, objArr.length);
                }
                m315a(iArr, objArr, i3);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i5 < i3) {
            int[] iArr3 = this.f239e;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr2 = this.f240f;
            System.arraycopy(objArr2, i5 << 1, objArr2, i7 << 1, (this.f241g - i5) << 1);
        }
        int i8 = this.f241g;
        if (i3 == i8) {
            int[] iArr4 = this.f239e;
            if (i5 < iArr4.length) {
                iArr4[i5] = i;
                Object[] objArr3 = this.f240f;
                int i9 = i5 << 1;
                objArr3[i9] = k;
                objArr3[i9 + 1] = v;
                this.f241g = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V remove(Object obj) {
        int a = mo361a(obj);
        if (a >= 0) {
            return mo367c(a);
        }
        return null;
    }

    public int size() {
        return this.f241g;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f241g * 28);
        sb.append('{');
        for (int i = 0; i < this.f241g; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object b = mo366b(i);
            if (b != this) {
                sb.append(b);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object d = mo371d(i);
            if (d != this) {
                sb.append(d);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
