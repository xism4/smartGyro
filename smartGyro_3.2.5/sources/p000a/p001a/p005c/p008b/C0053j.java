package p000a.p001a.p005c.p008b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import p000a.p001a.p005c.p006a.p007a.C0028c;
import p000a.p001a.p005c.p011d.C0064f;

/* renamed from: a.a.c.b.j */
class C0053j {

    /* renamed from: a.a.c.b.j$a */
    private interface C0054a<T> {
        /* renamed from: a */
        int mo187a(T t);

        /* renamed from: b */
        boolean mo189b(T t);
    }

    C0053j() {
    }

    /* renamed from: a */
    private C0028c.C0031c m199a(C0028c.C0030b bVar, int i) {
        return (C0028c.C0031c) m200a(bVar.mo158a(), i, new C0052i(this));
    }

    /* renamed from: a */
    private static <T> T m200a(T[] tArr, int i, C0054a<T> aVar) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int abs = (Math.abs(aVar.mo187a(t2) - i2) * 2) + (aVar.mo189b(t2) == z ? 0 : 1);
            if (t == null || i3 > abs) {
                t = t2;
                i3 = abs;
            }
        }
        return t;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0064f.C0066b mo192a(C0064f.C0066b[] bVarArr, int i) {
        return (C0064f.C0066b) m200a(bVarArr, i, new C0051h(this));
    }

    /* renamed from: a */
    public Typeface mo176a(Context context, C0028c.C0030b bVar, Resources resources, int i) {
        C0028c.C0031c a = m199a(bVar, i);
        if (a == null) {
            return null;
        }
        return C0046c.m160a(context, resources, a.mo160b(), a.mo159a(), i);
    }

    /* renamed from: a */
    public Typeface mo177a(Context context, Resources resources, int i, String str, int i2) {
        File a = C0055k.m208a(context);
        if (a == null) {
            return null;
        }
        try {
            if (!C0055k.m213a(a, resources, i)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(a.getPath());
            a.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a.delete();
        }
    }

    /* renamed from: a */
    public Typeface mo175a(Context context, CancellationSignal cancellationSignal, C0064f.C0066b[] bVarArr, int i) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (bVarArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(mo192a(bVarArr, i).mo234c());
            try {
                Typeface a = mo193a(context, inputStream);
                C0055k.m212a((Closeable) inputStream);
                return a;
            } catch (IOException unused) {
                C0055k.m212a((Closeable) inputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                C0055k.m212a((Closeable) inputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            inputStream = null;
            C0055k.m212a((Closeable) inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            C0055k.m212a((Closeable) inputStream2);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Typeface mo193a(Context context, InputStream inputStream) {
        File a = C0055k.m208a(context);
        if (a == null) {
            return null;
        }
        try {
            if (!C0055k.m214a(a, inputStream)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(a.getPath());
            a.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a.delete();
        }
    }
}
