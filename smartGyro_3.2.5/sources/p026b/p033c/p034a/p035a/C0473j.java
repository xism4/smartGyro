package p026b.p033c.p034a.p035a;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

/* renamed from: b.c.a.a.j */
public class C0473j implements C0474k {

    /* renamed from: a */
    boolean f1726a = true;

    /* renamed from: b */
    int f1727b = 2;

    @TargetApi(8)
    /* renamed from: c */
    private void m2066c(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }

    /* renamed from: a */
    public void mo2413a(int i, String str, String str2) {
        mo2414a(i, str, str2, (Throwable) null);
    }

    /* renamed from: a */
    public void mo2414a(int i, String str, String str2, Throwable th) {
        if (mo2417a() && mo2418a(i)) {
            if (i == 2) {
                Log.v(str, str2, th);
            } else if (i == 3) {
                Log.d(str, str2, th);
            } else if (i == 4) {
                Log.i(str, str2, th);
            } else if (i != 5) {
                if (i != 6) {
                    if (i == 8) {
                        if (Integer.valueOf(Build.VERSION.SDK).intValue() > 8) {
                            m2066c(str, str2, th);
                            return;
                        }
                    } else {
                        return;
                    }
                }
                Log.e(str, str2, th);
            } else {
                Log.w(str, str2, th);
            }
        }
    }

    /* renamed from: a */
    public void mo2415a(String str, String str2) {
        mo2413a(5, str, str2);
    }

    /* renamed from: a */
    public void mo2416a(String str, String str2, Throwable th) {
        mo2414a(5, str, str2, th);
    }

    /* renamed from: a */
    public boolean mo2417a() {
        return this.f1726a;
    }

    /* renamed from: a */
    public boolean mo2418a(int i) {
        return i >= this.f1727b;
    }

    /* renamed from: b */
    public void mo2419b(String str, String str2) {
        mo2413a(6, str, str2);
    }

    /* renamed from: b */
    public void mo2420b(String str, String str2, Throwable th) {
        mo2414a(6, str, str2, th);
    }

    /* renamed from: c */
    public void mo2421c(String str, String str2) {
        mo2413a(2, str, str2);
    }

    /* renamed from: d */
    public void mo2422d(String str, String str2) {
        mo2413a(2, str, str2);
    }
}
