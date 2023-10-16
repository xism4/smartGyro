package android.support.p024v4.app;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import p000a.p001a.p005c.p006a.C0025a;

/* renamed from: android.support.v4.app.b */
public class C0183b extends C0025a {

    /* renamed from: c */
    private static C0185b f452c;

    /* renamed from: android.support.v4.app.b$a */
    public interface C0184a {
        /* renamed from: a */
        void mo746a(int i, String[] strArr, int[] iArr);
    }

    /* renamed from: android.support.v4.app.b$b */
    public interface C0185b {
        /* renamed from: a */
        boolean mo747a(Activity activity, String[] strArr, int i);
    }

    /* renamed from: android.support.v4.app.b$c */
    public interface C0186c {
        /* renamed from: a */
        void mo748a(int i);
    }

    /* renamed from: a */
    public static void m657a(Activity activity, String[] strArr, int i) {
        C0185b bVar = f452c;
        if (bVar != null && bVar.mo747a(activity, strArr, i)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity instanceof C0186c) {
                ((C0186c) activity).mo748a(i);
            }
            activity.requestPermissions(strArr, i);
        } else if (activity instanceof C0184a) {
            new Handler(Looper.getMainLooper()).post(new C0182a(strArr, activity, i));
        }
    }
}
