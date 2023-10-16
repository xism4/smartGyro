package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.org.v4.util.R$dimen;
import com.org.v4.util.R$id;
import com.org.v4.util.R$layout;
import com.org.v4.util.R$style;
import org.cocos2dx.package_1.GameControllerDelegate;

class a {
    private final WindowManager.LayoutParams a = new WindowManager.LayoutParams();
    private final int[] b = new int[2];
    private final Context c;
    private final TextView g;
    private final View h;
    private final int[] n = new int[2];
    private final Rect r = new Rect();

    a(Context context) {
        this.c = context;
        this.h = LayoutInflater.from(this.c).inflate(R$layout.abc_tooltip, (ViewGroup) null);
        this.g = (TextView) this.h.findViewById(R$id.message);
        this.a.setTitle(Fa.class.getSimpleName());
        this.a.packageName = this.c.getPackageName();
        WindowManager.LayoutParams $r2 = this.a;
        $r2.type = GameControllerDelegate.THUMBSTICK_RIGHT_X;
        $r2.width = -2;
        $r2.height = -2;
        $r2.format = -3;
        $r2.windowAnimations = R$style.Animation_AppCompat_Tooltip;
        $r2.flags = 24;
    }

    private static View a(View view) {
        View $r1 = view.getRootView();
        ViewGroup.LayoutParams $r2 = $r1.getLayoutParams();
        if (($r2 instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) $r2).type == 2) {
            return $r1;
        }
        for (Context $r4 = view.getContext(); $r4 instanceof ContextWrapper; $r4 = ((ContextWrapper) $r4).getBaseContext()) {
            if ($r4 instanceof Activity) {
                return ((Activity) $r4).getWindow().getDecorView();
            }
        }
        return $r1;
    }

    private void show(View view, int $i0, int i, boolean z, WindowManager.LayoutParams layoutParams) {
        int $i3;
        int $i2;
        layoutParams.token = view.getApplicationWindowToken();
        int $i22 = this.c.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_threshold);
        if (view.getWidth() < $i22) {
            $i0 = view.getWidth() / 2;
        }
        if (view.getHeight() >= $i22) {
            int $i32 = this.c.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_extra_offset);
            $i2 = i + $i32;
            $i3 = i - $i32;
        } else {
            $i2 = view.getHeight();
            $i3 = 0;
        }
        layoutParams.gravity = 49;
        int $i1 = this.c.getResources().getDimensionPixelOffset(z ? R$dimen.tooltip_y_offset_touch : R$dimen.tooltip_y_offset_non_touch);
        View $r6 = a(view);
        if ($r6 == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        $r6.getWindowVisibleDisplayFrame(this.r);
        Rect $r7 = this.r;
        if ($r7.left < 0 && $r7.top < 0) {
            Resources $r5 = this.c.getResources();
            int $i4 = $r5.getIdentifier("status_bar_height", "dimen", "android");
            int $i42 = $i4 != 0 ? $r5.getDimensionPixelSize($i4) : 0;
            DisplayMetrics $r8 = $r5.getDisplayMetrics();
            this.r.set(0, $i42, $r8.widthPixels, $r8.heightPixels);
        }
        int[] $r9 = this.n;
        int[] iArr = $r9;
        $r6.getLocationOnScreen($r9);
        view.getLocationOnScreen(this.b);
        int[] $r92 = this.b;
        int $i43 = $r92[0];
        int[] $r10 = this.n;
        $r92[0] = $i43 - $r10[0];
        $r92[1] = $r92[1] - $r10[1];
        int $i02 = ($r92[0] + $i0) - ($r6.getWidth() / 2);
        int i2 = $i02;
        layoutParams.x = $i02;
        int $i03 = View.MeasureSpec.makeMeasureSpec(0, 0);
        View $r2 = this.h;
        View view2 = $r2;
        $r2.measure($i03, $i03);
        View $r22 = this.h;
        View view3 = $r22;
        int $i04 = $r22.getMeasuredHeight();
        int[] $r93 = this.b;
        int $i33 = (($r93[1] + $i3) - $i1) - $i04;
        int $i12 = $r93[1] + $i2 + $i1;
        if (!z ? $i04 + $i12 > this.r.height() : $i33 >= 0) {
            layoutParams.y = $i33;
        } else {
            layoutParams.y = $i12;
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (b()) {
            ((WindowManager) this.c.getSystemService("window")).removeView(this.h);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view, int i, int i2, boolean z, CharSequence charSequence) {
        if (b()) {
            a();
        }
        this.g.setText(charSequence);
        show(view, i, i2, z, this.a);
        ((WindowManager) this.c.getSystemService("window")).addView(this.h, this.a);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.h.getParent() != null;
    }
}
