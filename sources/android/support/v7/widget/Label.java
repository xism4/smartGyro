package android.support.v7.widget;

import android.content.Context;
import android.os.Build;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ListPopupWindow;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class Label extends ListPopupWindow implements Object {
    private static Method b = PopupWindow.class.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});
    private Object d;

    static {
        try {
        } catch (NoSuchMethodException e) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public Label(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void a(MenuBuilder menuBuilder, MenuItem menuItem) {
        Object $r3 = this.d;
        if ($r3 != null) {
            $r3.a(menuBuilder, menuItem);
        }
    }

    public void a(Object object) {
        this.d = object;
    }

    public void a(boolean z) {
        Method $r4 = b;
        if ($r4 != null) {
            PopupWindow $r1 = this.mPopup;
            Object[] $r5 = new Object[1];
            try {
                $r5[0] = Boolean.valueOf(z);
                $r4.invoke($r1, $r5);
            } catch (Exception e) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    public void b(MenuBuilder menuBuilder, MenuItem menuItem) {
        Object $r3 = this.d;
        if ($r3 != null) {
            $r3.b(menuBuilder, menuItem);
        }
    }

    public void init(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mPopup.setEnterTransition((Transition) obj);
        }
    }

    /* access modifiers changed from: package-private */
    public ListViewCompat show(Context context, boolean z) {
        ListPopupWindow.DropDownListView $r2 = new ListPopupWindow.DropDownListView(context, z);
        $r2.setHoverListener(this);
        return $r2;
    }

    public void show(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mPopup.setExitTransition((Transition) obj);
        }
    }
}
