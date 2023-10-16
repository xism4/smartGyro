package cz.msebera.android.http.mime;

public final class LangUtils {
    public static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        if (objArr == null) {
            return objArr2 == null;
        }
        if (objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        for (int $i0 = 0; $i0 < objArr.length; $i0++) {
            if (!equals(objArr[$i0], objArr2[$i0])) {
                return false;
            }
        }
        return true;
    }

    public static int hashCode(int i, int i2) {
        return (i * 37) + i2;
    }

    public static int hashCode(int i, Object obj) {
        return hashCode(i, obj != null ? obj.hashCode() : 0);
    }

    public static int hashCode(int i, boolean i1) {
        return hashCode(i, (int) i1);
    }
}
