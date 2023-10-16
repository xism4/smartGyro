package p036c.p037a.p038a.p039a.p074p;

/* renamed from: c.a.a.a.p.h */
public final class C0877h {
    /* renamed from: a */
    public static int m3082a(int i, int i2) {
        return (i * 37) + i2;
    }

    /* renamed from: a */
    public static int m3083a(int i, Object obj) {
        return m3082a(i, obj != null ? obj.hashCode() : 0);
    }

    /* renamed from: a */
    public static int m3084a(int i, boolean z) {
        return m3082a(i, z ? 1 : 0);
    }

    /* renamed from: a */
    public static boolean m3085a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    /* renamed from: a */
    public static boolean m3086a(Object[] objArr, Object[] objArr2) {
        if (objArr == null) {
            return objArr2 == null;
        }
        if (objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        for (int i = 0; i < objArr.length; i++) {
            if (!m3085a(objArr[i], objArr2[i])) {
                return false;
            }
        }
        return true;
    }
}
