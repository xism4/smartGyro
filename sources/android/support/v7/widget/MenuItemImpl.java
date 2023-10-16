package android.support.v7.widget;

import android.os.Build;
import android.view.View;

public class MenuItemImpl {
    public static void add(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        } else {
            ClassWriter.a(view, charSequence);
        }
    }
}
