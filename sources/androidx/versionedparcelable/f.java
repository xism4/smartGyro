package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;

public abstract class f {
    private void a(Context context) {
        try {
            a(get(context.getClass()).getName());
        } catch (ClassNotFoundException $r6) {
            throw new RuntimeException(context.getClass().getSimpleName() + " does not have a Parcelizer", $r6);
        }
    }

    protected static void a(Context context, f fVar) {
        try {
            Class $r3 = e(context);
            Class[] $r0 = new Class[2];
            $r0[0] = context.getClass();
            $r0[1] = b.class;
            $r3.getDeclaredMethod("write", $r0).invoke((Object) null, new Object[]{context, fVar});
        } catch (IllegalAccessException $r12) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", $r12);
        } catch (InvocationTargetException $r10) {
            if ($r10.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) $r10.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", $r10);
        } catch (NoSuchMethodException $r9) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", $r9);
        } catch (ClassNotFoundException $r7) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", $r7);
        }
    }

    private static Class e(Context context) {
        return get(context.getClass());
    }

    protected static Context get(String str, f fVar) {
        try {
            return (Context) Class.forName(str, true, b.class.getClassLoader()).getDeclaredMethod("read", new Class[]{b.class}).invoke((Object) null, new Object[]{fVar});
        } catch (IllegalAccessException $r14) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", $r14);
        } catch (InvocationTargetException $r12) {
            if ($r12.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) $r12.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", $r12);
        } catch (NoSuchMethodException $r11) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", $r11);
        } catch (ClassNotFoundException $r9) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", $r9);
        }
    }

    private static Class get(Class cls) {
        return Class.forName(String.format("%s.%sParcelizer", new Object[]{cls.getPackage().getName(), cls.getSimpleName()}), false, cls.getClassLoader());
    }

    public String a(String str, int i) {
        return !add(i) ? str : getValue();
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    public void a(int i, int i2) {
        e(i2);
        b(i);
    }

    public void a(Parcelable parcelable, int i) {
        e(i);
        clear(parcelable);
    }

    /* access modifiers changed from: protected */
    public abstract void a(String str);

    public void a(boolean z, boolean z2) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr);

    public byte[] a(byte[] bArr, int i) {
        return !add(i) ? bArr : b();
    }

    public int add(int i, int i2) {
        return !add(i2) ? i : size();
    }

    /* access modifiers changed from: protected */
    public abstract Parcelable add();

    public Parcelable add(Parcelable parcelable, int i) {
        return !add(i) ? parcelable : add();
    }

    /* access modifiers changed from: protected */
    public abstract boolean add(int i);

    /* access modifiers changed from: protected */
    public abstract void b(int i);

    public void b(String str, int i) {
        e(i);
        a(str);
    }

    /* access modifiers changed from: protected */
    public abstract byte[] b();

    /* access modifiers changed from: protected */
    public abstract f c();

    /* access modifiers changed from: protected */
    public abstract void clear(Parcelable parcelable);

    public void clear(byte[] bArr, int i) {
        e(i);
        a(bArr);
    }

    /* access modifiers changed from: protected */
    public void d(Context context) {
        if (context == null) {
            a((String) null);
            return;
        }
        a(context);
        f $r0 = c();
        a(context, $r0);
        $r0.a();
    }

    /* access modifiers changed from: protected */
    public abstract void e(int i);

    /* access modifiers changed from: protected */
    public Context f() {
        String $r1 = getValue();
        if ($r1 == null) {
            return null;
        }
        return get($r1, c());
    }

    /* access modifiers changed from: protected */
    public abstract String getValue();

    public boolean processBytes() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract int size();
}
