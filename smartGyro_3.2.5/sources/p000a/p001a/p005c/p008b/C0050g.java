package p000a.p001a.p005c.p008b;

import android.graphics.Typeface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: a.a.c.b.g */
public class C0050g extends C0049f {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Typeface mo178a(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.f131a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.f137g.invoke((Object) null, new Object[]{newInstance, "sans-serif", -1, -1});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public Method mo183d(Class cls) {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(cls, 1).getClass(), String.class, cls2, cls2});
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
