package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;

/* renamed from: androidx.versionedparcelable.b */
public abstract class C0453b {
    /* renamed from: a */
    protected static <T extends C0455d> T m1931a(String str, C0453b bVar) {
        try {
            return (C0455d) Class.forName(str, true, C0453b.class.getClassLoader()).getDeclaredMethod("read", new Class[]{C0453b.class}).invoke((Object) null, new Object[]{bVar});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        }
    }

    /* renamed from: a */
    private static Class m1932a(Class<? extends C0455d> cls) {
        return Class.forName(String.format("%s.%sParcelizer", new Object[]{cls.getPackage().getName(), cls.getSimpleName()}), false, cls.getClassLoader());
    }

    /* renamed from: a */
    protected static <T extends C0455d> void m1933a(T t, C0453b bVar) {
        try {
            m1934b(t).getDeclaredMethod("write", new Class[]{t.getClass(), C0453b.class}).invoke((Object) null, new Object[]{t, bVar});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        }
    }

    /* renamed from: b */
    private static <T extends C0455d> Class m1934b(T t) {
        return m1932a((Class<? extends C0455d>) t.getClass());
    }

    /* renamed from: c */
    private void m1935c(C0455d dVar) {
        try {
            mo2323a(m1932a((Class<? extends C0455d>) dVar.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(dVar.getClass().getSimpleName() + " does not have a Parcelizer", e);
        }
    }

    /* renamed from: a */
    public int mo2317a(int i, int i2) {
        return !mo2326a(i2) ? i : mo2337e();
    }

    /* renamed from: a */
    public <T extends Parcelable> T mo2318a(T t, int i) {
        return !mo2326a(i) ? t : mo2338f();
    }

    /* renamed from: a */
    public String mo2319a(String str, int i) {
        return !mo2326a(i) ? str : mo2339g();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo2320a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo2321a(Parcelable parcelable);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2322a(C0455d dVar) {
        if (dVar == null) {
            mo2323a((String) null);
            return;
        }
        m1935c(dVar);
        C0453b b = mo2328b();
        m1933a(dVar, b);
        b.mo2320a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo2323a(String str);

    /* renamed from: a */
    public void mo2324a(boolean z, boolean z2) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo2325a(byte[] bArr);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo2326a(int i);

    /* renamed from: a */
    public byte[] mo2327a(byte[] bArr, int i) {
        return !mo2326a(i) ? bArr : mo2336d();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract C0453b mo2328b();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo2329b(int i);

    /* renamed from: b */
    public void mo2330b(int i, int i2) {
        mo2329b(i2);
        mo2334c(i);
    }

    /* renamed from: b */
    public void mo2331b(Parcelable parcelable, int i) {
        mo2329b(i);
        mo2321a(parcelable);
    }

    /* renamed from: b */
    public void mo2332b(String str, int i) {
        mo2329b(i);
        mo2323a(str);
    }

    /* renamed from: b */
    public void mo2333b(byte[] bArr, int i) {
        mo2329b(i);
        mo2325a(bArr);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract void mo2334c(int i);

    /* renamed from: c */
    public boolean mo2335c() {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract byte[] mo2336d();

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public abstract int mo2337e();

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public abstract <T extends Parcelable> T mo2338f();

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public abstract String mo2339g();

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public <T extends C0455d> T mo2340h() {
        String g = mo2339g();
        if (g == null) {
            return null;
        }
        return m1931a(g, mo2328b());
    }
}
