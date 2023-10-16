package cz.msebera.android.http.client.ssl;

import java.lang.reflect.InvocationTargetException;

public class CloneUtils {
    public static Object cloneObject(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Cloneable) {
            try {
                try {
                    return obj.getClass().getMethod("clone", (Class[]) null).invoke(obj, (Object[]) null);
                } catch (InvocationTargetException $r6) {
                    Throwable $r7 = $r6.getCause();
                    if ($r7 instanceof CloneNotSupportedException) {
                        throw ((CloneNotSupportedException) $r7);
                    }
                    throw new Error("Unexpected exception", $r7);
                } catch (IllegalAccessException $r3) {
                    throw new IllegalAccessError($r3.getMessage());
                }
            } catch (NoSuchMethodException $r10) {
                throw new NoSuchMethodError($r10.getMessage());
            }
        } else {
            throw new CloneNotSupportedException();
        }
    }
}
