package cz.msebera.android.http.mime;

public class Asserts {
    public static void check(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void notNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalStateException(str + " is null");
        }
    }
}
