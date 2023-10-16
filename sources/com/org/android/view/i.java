package com.org.android.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class i {
    private static Method b;
    private static boolean c;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;

    private static boolean a(ActionBar actionBar, KeyEvent keyEvent) {
        if (!c) {
            try {
                b = actionBar.getClass().getMethod("onMenuKeyEvent", new Class[]{KeyEvent.class});
            } catch (NoSuchMethodException e) {
            }
            c = true;
        }
        Method $r7 = b;
        if ($r7 == null) {
            return false;
        }
        try {
            return ((Boolean) $r7.invoke(actionBar, new Object[]{keyEvent})).booleanValue();
        } catch (IllegalAccessException e2) {
            return false;
        } catch (InvocationTargetException e3) {
            return false;
        }
    }

    private static boolean a(Activity activity, KeyEvent keyEvent) {
        activity.onUserInteraction();
        Window $r2 = activity.getWindow();
        if ($r2.hasFeature(8)) {
            ActionBar $r3 = activity.getActionBar();
            if (keyEvent.getKeyCode() == 82 && $r3 != null && a($r3, keyEvent)) {
                return true;
            }
        }
        if ($r2.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View $r4 = $r2.getDecorView();
        if (ViewCompat.b($r4, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(activity, $r4 != null ? $r4.getKeyDispatcherState() : null, activity);
    }

    private static boolean a(Dialog dialog, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener $r2 = getMinimumWidth(dialog);
        if ($r2 != null && $r2.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
            return true;
        }
        Window $r3 = dialog.getWindow();
        if ($r3.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View $r4 = $r3.getDecorView();
        if (ViewCompat.b($r4, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(dialog, $r4 != null ? $r4.getKeyDispatcherState() : null, dialog);
    }

    public static boolean a(View view, KeyEvent keyEvent) {
        return ViewCompat.a(view, keyEvent);
    }

    public static boolean a(x xVar, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (xVar == null) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 28 ? xVar.a(keyEvent) : callback instanceof Activity ? a((Activity) callback, keyEvent) : callback instanceof Dialog ? a((Dialog) callback, keyEvent) : (view != null && ViewCompat.b(view, keyEvent)) || xVar.a(keyEvent);
    }

    private static DialogInterface.OnKeyListener getMinimumWidth(Dialog dialog) {
        if (!sMinWidthFieldFetched) {
            try {
                sMinWidthField = Dialog.class.getDeclaredField("mOnKeyListener");
                sMinWidthField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            sMinWidthFieldFetched = true;
        }
        Field $r4 = sMinWidthField;
        if ($r4 == null) {
            return null;
        }
        try {
            return (DialogInterface.OnKeyListener) $r4.get(dialog);
        } catch (IllegalAccessException e2) {
            return null;
        }
    }
}
