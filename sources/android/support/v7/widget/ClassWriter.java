package android.support.v7.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import com.org.android.view.Type;
import com.org.android.view.ViewCompat;

class ClassWriter implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static ClassWriter a;
    private static ClassWriter b;
    private final Runnable c = new AgendaListView$2(this);
    private final Runnable e = new MonthListView$1(this);
    private final View h;
    private int i;
    private int j;
    private final CharSequence k;
    private final int l;
    private a v;
    private boolean w;

    private ClassWriter(View view, CharSequence charSequence) {
        this.h = view;
        this.k = charSequence;
        this.l = Type.getSize(ViewConfiguration.get(this.h.getContext()));
        b();
        this.h.setOnLongClickListener(this);
        this.h.setOnHoverListener(this);
    }

    private static void a(ClassWriter classWriter) {
        ClassWriter $r0 = b;
        if ($r0 != null) {
            $r0.c();
        }
        b = classWriter;
        ClassWriter $r1 = b;
        if ($r1 != null) {
            $r1.init();
        }
    }

    public static void a(View view, CharSequence charSequence) {
        ClassWriter $r2 = b;
        if ($r2 != null && $r2.h == view) {
            a((ClassWriter) null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            ClassWriter $r22 = a;
            if ($r22 != null && $r22.h == view) {
                $r22.a();
            }
            view.setOnLongClickListener((View.OnLongClickListener) null);
            view.setLongClickable(false);
            view.setOnHoverListener((View.OnHoverListener) null);
            return;
        }
        new ClassWriter(view, charSequence);
    }

    private boolean a(MotionEvent motionEvent) {
        int $i0 = (int) motionEvent.getX();
        int $i1 = (int) motionEvent.getY();
        if (Math.abs($i0 - this.i) <= this.l && Math.abs($i1 - this.j) <= this.l) {
            return false;
        }
        this.i = $i0;
        this.j = $i1;
        return true;
    }

    private void b() {
        this.i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
    }

    private void c() {
        this.h.removeCallbacks(this.e);
    }

    private void init() {
        this.h.postDelayed(this.e, (long) ViewConfiguration.getLongPressTimeout());
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (a == this) {
            a = null;
            a $r2 = this.v;
            if ($r2 != null) {
                $r2.a();
                this.v = null;
                b();
                this.h.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (b == this) {
            a((ClassWriter) null);
        }
        this.h.removeCallbacks(this.c);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        long j2;
        if (ViewCompat.isAttachedToWindow(this.h)) {
            a((ClassWriter) null);
            ClassWriter $r4 = a;
            if ($r4 != null) {
                $r4.a();
            }
            a = this;
            this.w = z;
            this.v = new a(this.h.getContext());
            this.v.a(this.h, this.i, this.j, this.w, this.k);
            this.h.addOnAttachStateChangeListener(this);
            if (this.w) {
                j2 = 2500;
            } else {
                j2 = ((ViewCompat.a(this.h) & 1) == 1 ? 3000 : 15000) - ((long) ViewConfiguration.getLongPressTimeout());
            }
            View $r3 = this.h;
            Runnable $r6 = this.c;
            Runnable runnable = $r6;
            $r3.removeCallbacks($r6);
            View $r32 = this.h;
            Runnable $r62 = this.c;
            Runnable runnable2 = $r62;
            $r32.postDelayed($r62, j2);
        }
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.v != null && this.w) {
            return false;
        }
        AccessibilityManager $r6 = (AccessibilityManager) this.h.getContext().getSystemService("accessibility");
        if ($r6.isEnabled() && $r6.isTouchExplorationEnabled()) {
            return false;
        }
        int $i0 = motionEvent.getAction();
        if ($i0 != 7) {
            if ($i0 != 10) {
                return false;
            }
            b();
            a();
            return false;
        } else if (!this.h.isEnabled() || this.v != null || !a(motionEvent)) {
            return false;
        } else {
            a(this);
            return false;
        }
    }

    public boolean onLongClick(View view) {
        this.i = view.getWidth() / 2;
        this.j = view.getHeight() / 2;
        a(true);
        return true;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        a();
    }
}
