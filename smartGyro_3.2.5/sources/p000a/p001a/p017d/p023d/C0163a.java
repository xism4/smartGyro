package p000a.p001a.p017d.p023d;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0137b;
import p000a.p001a.p017d.p018a.C0139d;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: a.a.d.d.a */
public class C0163a {

    /* renamed from: a */
    private Context f374a;

    private C0163a(Context context) {
        this.f374a = context;
    }

    /* renamed from: a */
    public static C0163a m577a(Context context) {
        return new C0163a(context);
    }

    /* renamed from: a */
    public boolean mo639a() {
        return this.f374a.getApplicationInfo().targetSdkVersion < 14;
    }

    /* renamed from: b */
    public int mo640b() {
        return this.f374a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    /* renamed from: c */
    public int mo641c() {
        Configuration configuration = this.f374a.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i > 600) {
            return 5;
        }
        if (i > 960 && i2 > 720) {
            return 5;
        }
        if (i > 720 && i2 > 960) {
            return 5;
        }
        if (i >= 500) {
            return 4;
        }
        if (i > 640 && i2 > 480) {
            return 4;
        }
        if (i <= 480 || i2 <= 640) {
            return i >= 360 ? 3 : 2;
        }
        return 4;
    }

    /* renamed from: d */
    public int mo642d() {
        return this.f374a.getResources().getDimensionPixelSize(C0139d.abc_action_bar_stacked_tab_max_width);
    }

    /* renamed from: e */
    public int mo643e() {
        TypedArray obtainStyledAttributes = this.f374a.obtainStyledAttributes((AttributeSet) null, C0145j.ActionBar, C0136a.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(C0145j.ActionBar_height, 0);
        Resources resources = this.f374a.getResources();
        if (!mo644f()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(C0139d.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }

    /* renamed from: f */
    public boolean mo644f() {
        return this.f374a.getResources().getBoolean(C0137b.abc_action_bar_embed_tabs);
    }

    /* renamed from: g */
    public boolean mo645g() {
        if (Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        return !ViewConfiguration.get(this.f374a).hasPermanentMenuKey();
    }
}
