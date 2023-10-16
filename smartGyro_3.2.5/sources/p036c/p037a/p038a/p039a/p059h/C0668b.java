package p036c.p037a.p038a.p039a.p059h;

import android.util.Log;

/* renamed from: c.a.a.a.h.b */
public class C0668b {

    /* renamed from: a */
    private String f1988a;

    /* renamed from: b */
    private boolean f1989b = false;

    /* renamed from: c */
    private boolean f1990c = false;

    /* renamed from: d */
    private boolean f1991d = false;

    /* renamed from: e */
    private boolean f1992e = false;

    /* renamed from: f */
    private boolean f1993f = false;

    public C0668b(Object obj) {
        this.f1988a = obj.toString();
    }

    /* renamed from: a */
    public void mo2803a(Object obj) {
        if (mo2805a()) {
            Log.d(this.f1988a, obj.toString());
        }
    }

    /* renamed from: a */
    public void mo2804a(Object obj, Throwable th) {
        if (mo2805a()) {
            Log.d(this.f1988a, obj.toString(), th);
        }
    }

    /* renamed from: a */
    public boolean mo2805a() {
        return this.f1989b;
    }

    /* renamed from: b */
    public void mo2806b(Object obj) {
        if (mo2808b()) {
            Log.e(this.f1988a, obj.toString());
        }
    }

    /* renamed from: b */
    public void mo2807b(Object obj, Throwable th) {
        if (mo2812d()) {
            Log.w(this.f1988a, obj.toString(), th);
        }
    }

    /* renamed from: b */
    public boolean mo2808b() {
        return this.f1990c;
    }

    /* renamed from: c */
    public void mo2809c(Object obj) {
        if (mo2810c()) {
            Log.i(this.f1988a, obj.toString());
        }
    }

    /* renamed from: c */
    public boolean mo2810c() {
        return this.f1993f;
    }

    /* renamed from: d */
    public void mo2811d(Object obj) {
        if (mo2812d()) {
            Log.w(this.f1988a, obj.toString());
        }
    }

    /* renamed from: d */
    public boolean mo2812d() {
        return this.f1992e;
    }
}
