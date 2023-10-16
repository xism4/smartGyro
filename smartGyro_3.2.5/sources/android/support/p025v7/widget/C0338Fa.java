package android.support.p025v7.widget;

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
import org.cocos2dx.lib.GameControllerDelegate;
import p000a.p001a.p017d.p018a.C0139d;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0142g;
import p000a.p001a.p017d.p018a.C0144i;

/* renamed from: android.support.v7.widget.Fa */
class C0338Fa {

    /* renamed from: a */
    private final Context f1217a;

    /* renamed from: b */
    private final View f1218b;

    /* renamed from: c */
    private final TextView f1219c;

    /* renamed from: d */
    private final WindowManager.LayoutParams f1220d = new WindowManager.LayoutParams();

    /* renamed from: e */
    private final Rect f1221e = new Rect();

    /* renamed from: f */
    private final int[] f1222f = new int[2];

    /* renamed from: g */
    private final int[] f1223g = new int[2];

    C0338Fa(Context context) {
        this.f1217a = context;
        this.f1218b = LayoutInflater.from(this.f1217a).inflate(C0142g.abc_tooltip, (ViewGroup) null);
        this.f1219c = (TextView) this.f1218b.findViewById(C0141f.message);
        this.f1220d.setTitle(C0338Fa.class.getSimpleName());
        this.f1220d.packageName = this.f1217a.getPackageName();
        WindowManager.LayoutParams layoutParams = this.f1220d;
        layoutParams.type = GameControllerDelegate.THUMBSTICK_RIGHT_X;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = C0144i.Animation_AppCompat_Tooltip;
        layoutParams.flags = 24;
    }

    /* renamed from: a */
    private static View m1466a(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if ((layoutParams instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) layoutParams).type == 2) {
            return rootView;
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return ((Activity) context).getWindow().getDecorView();
            }
        }
        return rootView;
    }

    /* renamed from: a */
    private void m1467a(View view, int i, int i2, boolean z, WindowManager.LayoutParams layoutParams) {
        int i3;
        int i4;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.f1217a.getResources().getDimensionPixelOffset(C0139d.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.f1217a.getResources().getDimensionPixelOffset(C0139d.tooltip_precise_anchor_extra_offset);
            i4 = i2 + dimensionPixelOffset2;
            i3 = i2 - dimensionPixelOffset2;
        } else {
            i4 = view.getHeight();
            i3 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.f1217a.getResources().getDimensionPixelOffset(z ? C0139d.tooltip_y_offset_touch : C0139d.tooltip_y_offset_non_touch);
        View a = m1466a(view);
        if (a == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        a.getWindowVisibleDisplayFrame(this.f1221e);
        Rect rect = this.f1221e;
        if (rect.left < 0 && rect.top < 0) {
            Resources resources = this.f1217a.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.f1221e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        a.getLocationOnScreen(this.f1223g);
        view.getLocationOnScreen(this.f1222f);
        int[] iArr = this.f1222f;
        int i5 = iArr[0];
        int[] iArr2 = this.f1223g;
        iArr[0] = i5 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (iArr[0] + i) - (a.getWidth() / 2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.f1218b.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredHeight = this.f1218b.getMeasuredHeight();
        int[] iArr3 = this.f1222f;
        int i6 = ((iArr3[1] + i3) - dimensionPixelOffset3) - measuredHeight;
        int i7 = iArr3[1] + i4 + dimensionPixelOffset3;
        if (!z ? measuredHeight + i7 > this.f1221e.height() : i6 >= 0) {
            layoutParams.y = i6;
        } else {
            layoutParams.y = i7;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1712a() {
        if (mo1714b()) {
            ((WindowManager) this.f1217a.getSystemService("window")).removeView(this.f1218b);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1713a(View view, int i, int i2, boolean z, CharSequence charSequence) {
        if (mo1714b()) {
            mo1712a();
        }
        this.f1219c.setText(charSequence);
        m1467a(view, i, i2, z, this.f1220d);
        ((WindowManager) this.f1217a.getSystemService("window")).addView(this.f1218b, this.f1220d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo1714b() {
        return this.f1218b.getParent() != null;
    }
}
