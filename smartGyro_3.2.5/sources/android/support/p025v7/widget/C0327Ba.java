package android.support.p025v7.widget;

import android.os.Build;
import android.view.View;

/* renamed from: android.support.v7.widget.Ba */
public class C0327Ba {
    /* renamed from: a */
    public static void m1444a(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        } else {
            C0336Ea.m1459a(view, charSequence);
        }
    }
}
