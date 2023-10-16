package p000a.p001a.p005c.p014g;

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

/* renamed from: a.a.c.g.g */
public class C0112g {

    /* renamed from: a */
    private static boolean f264a = false;

    /* renamed from: b */
    private static Method f265b = null;

    /* renamed from: c */
    private static boolean f266c = false;

    /* renamed from: d */
    private static Field f267d;

    /* renamed from: a.a.c.g.g$a */
    public interface C0113a {
        /* renamed from: a */
        boolean mo463a(KeyEvent keyEvent);
    }

    /* renamed from: a */
    private static DialogInterface.OnKeyListener m396a(Dialog dialog) {
        if (!f266c) {
            try {
                f267d = Dialog.class.getDeclaredField("mOnKeyListener");
                f267d.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f266c = true;
        }
        Field field = f267d;
        if (field == null) {
            return null;
        }
        try {
            return (DialogInterface.OnKeyListener) field.get(dialog);
        } catch (IllegalAccessException unused2) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m397a(C0113a aVar, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (aVar == null) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 28 ? aVar.mo463a(keyEvent) : callback instanceof Activity ? m399a((Activity) callback, keyEvent) : callback instanceof Dialog ? m400a((Dialog) callback, keyEvent) : (view != null && C0127u.m446a(view, keyEvent)) || aVar.mo463a(keyEvent);
    }

    /* renamed from: a */
    private static boolean m398a(ActionBar actionBar, KeyEvent keyEvent) {
        if (!f264a) {
            try {
                f265b = actionBar.getClass().getMethod("onMenuKeyEvent", new Class[]{KeyEvent.class});
            } catch (NoSuchMethodException unused) {
            }
            f264a = true;
        }
        Method method = f265b;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(actionBar, new Object[]{keyEvent})).booleanValue();
            } catch (IllegalAccessException | InvocationTargetException unused2) {
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m399a(Activity activity, KeyEvent keyEvent) {
        activity.onUserInteraction();
        Window window = activity.getWindow();
        if (window.hasFeature(8)) {
            ActionBar actionBar = activity.getActionBar();
            if (keyEvent.getKeyCode() == 82 && actionBar != null && m398a(actionBar, keyEvent)) {
                return true;
            }
        }
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (C0127u.m446a(decorView, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
    }

    /* renamed from: a */
    private static boolean m400a(Dialog dialog, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener a = m396a(dialog);
        if (a != null && a.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
            return true;
        }
        Window window = dialog.getWindow();
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (C0127u.m446a(decorView, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(dialog, decorView != null ? decorView.getKeyDispatcherState() : null, dialog);
    }

    /* renamed from: a */
    public static boolean m401a(View view, KeyEvent keyEvent) {
        return C0127u.m448b(view, keyEvent);
    }
}
