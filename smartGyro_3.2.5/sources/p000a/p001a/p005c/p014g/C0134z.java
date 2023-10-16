package p000a.p001a.p005c.p014g;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* renamed from: a.a.c.g.z */
public final class C0134z {

    /* renamed from: a */
    private WeakReference<View> f294a;

    /* renamed from: b */
    Runnable f295b = null;

    /* renamed from: c */
    Runnable f296c = null;

    /* renamed from: d */
    int f297d = -1;

    /* renamed from: a.a.c.g.z$a */
    static class C0135a implements C0092A {

        /* renamed from: a */
        C0134z f298a;

        /* renamed from: b */
        boolean f299b;

        C0135a(C0134z zVar) {
            this.f298a = zVar;
        }

        /* renamed from: a */
        public void mo389a(View view) {
            Object tag = view.getTag(2113929216);
            C0092A a = tag instanceof C0092A ? (C0092A) tag : null;
            if (a != null) {
                a.mo389a(view);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: a.a.c.g.A} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void mo390b(android.view.View r4) {
            /*
                r3 = this;
                a.a.c.g.z r0 = r3.f298a
                int r0 = r0.f297d
                r1 = -1
                r2 = 0
                if (r0 <= r1) goto L_0x000f
                r4.setLayerType(r0, r2)
                a.a.c.g.z r0 = r3.f298a
                r0.f297d = r1
            L_0x000f:
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 16
                if (r0 >= r1) goto L_0x0019
                boolean r0 = r3.f299b
                if (r0 != 0) goto L_0x0039
            L_0x0019:
                a.a.c.g.z r0 = r3.f298a
                java.lang.Runnable r1 = r0.f296c
                if (r1 == 0) goto L_0x0024
                r0.f296c = r2
                r1.run()
            L_0x0024:
                r0 = 2113929216(0x7e000000, float:4.2535296E37)
                java.lang.Object r0 = r4.getTag(r0)
                boolean r1 = r0 instanceof p000a.p001a.p005c.p014g.C0092A
                if (r1 == 0) goto L_0x0031
                r2 = r0
                a.a.c.g.A r2 = (p000a.p001a.p005c.p014g.C0092A) r2
            L_0x0031:
                if (r2 == 0) goto L_0x0036
                r2.mo390b(r4)
            L_0x0036:
                r4 = 1
                r3.f299b = r4
            L_0x0039:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p014g.C0134z.C0135a.mo390b(android.view.View):void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: a.a.c.g.A} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* renamed from: c */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void mo391c(android.view.View r4) {
            /*
                r3 = this;
                r0 = 0
                r3.f299b = r0
                a.a.c.g.z r0 = r3.f298a
                int r0 = r0.f297d
                r1 = 0
                r2 = -1
                if (r0 <= r2) goto L_0x000f
                r0 = 2
                r4.setLayerType(r0, r1)
            L_0x000f:
                a.a.c.g.z r0 = r3.f298a
                java.lang.Runnable r2 = r0.f295b
                if (r2 == 0) goto L_0x001a
                r0.f295b = r1
                r2.run()
            L_0x001a:
                r0 = 2113929216(0x7e000000, float:4.2535296E37)
                java.lang.Object r0 = r4.getTag(r0)
                boolean r2 = r0 instanceof p000a.p001a.p005c.p014g.C0092A
                if (r2 == 0) goto L_0x0027
                r1 = r0
                a.a.c.g.A r1 = (p000a.p001a.p005c.p014g.C0092A) r1
            L_0x0027:
                if (r1 == 0) goto L_0x002c
                r1.mo391c(r4)
            L_0x002c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p014g.C0134z.C0135a.mo391c(android.view.View):void");
        }
    }

    C0134z(View view) {
        this.f294a = new WeakReference<>(view);
    }

    /* renamed from: a */
    private void m475a(View view, C0092A a) {
        if (a != null) {
            view.animate().setListener(new C0132x(this, a, view));
        } else {
            view.animate().setListener((Animator.AnimatorListener) null);
        }
    }

    /* renamed from: a */
    public C0134z mo503a(float f) {
        View view = (View) this.f294a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    /* renamed from: a */
    public C0134z mo504a(long j) {
        View view = (View) this.f294a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    /* renamed from: a */
    public C0134z mo505a(C0092A a) {
        View view = (View) this.f294a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT < 16) {
                view.setTag(2113929216, a);
                a = new C0135a(this);
            }
            m475a(view, a);
        }
        return this;
    }

    /* renamed from: a */
    public C0134z mo506a(C0094C c) {
        View view = (View) this.f294a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            C0133y yVar = null;
            if (c != null) {
                yVar = new C0133y(this, c, view);
            }
            view.animate().setUpdateListener(yVar);
        }
        return this;
    }

    /* renamed from: a */
    public C0134z mo507a(Interpolator interpolator) {
        View view = (View) this.f294a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    /* renamed from: a */
    public void mo508a() {
        View view = (View) this.f294a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    /* renamed from: b */
    public long mo509b() {
        View view = (View) this.f294a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0;
    }

    /* renamed from: b */
    public C0134z mo510b(float f) {
        View view = (View) this.f294a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }

    /* renamed from: b */
    public C0134z mo511b(long j) {
        View view = (View) this.f294a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    /* renamed from: c */
    public void mo512c() {
        View view = (View) this.f294a.get();
        if (view != null) {
            view.animate().start();
        }
    }
}
