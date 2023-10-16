package lombok.eclipse.handlers.http;

class Utils {
    public static void asserts(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }

    public static Object notNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new IllegalArgumentException(str + " should not be null!");
    }
}
