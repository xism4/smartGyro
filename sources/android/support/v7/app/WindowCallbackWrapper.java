package android.support.v7.app;

import android.os.Build;
import android.support.v7.app.AppCompatDelegateImplV7;
import android.support.v7.view.menu.MenuBuilder;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import com.org.v4.view.SupportActionModeWrapper;
import java.util.List;

class WindowCallbackWrapper extends com.org.v4.view.WindowCallbackWrapper {
    final /* synthetic */ AppCompatDelegateImplV7 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WindowCallbackWrapper(AppCompatDelegateImplV7 appCompatDelegateImplV7, Window.Callback callback) {
        super(callback);
        this.this$0 = appCompatDelegateImplV7;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.this$0.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent) || this.this$0.onKeyShortcut(keyEvent.getKeyCode(), keyEvent);
    }

    public void onContentChanged() {
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || (menu instanceof MenuBuilder)) {
            return super.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    public boolean onMenuOpened(int i, Menu menu) {
        super.onMenuOpened(i, menu);
        this.this$0.onMenuOpened(i);
        return true;
    }

    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
        this.this$0.onPanelClosed(i);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        MenuBuilder $r3 = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
        if (i == 0 && $r3 == null) {
            return false;
        }
        if ($r3 != null) {
            $r3.setOverrideVisibleItems(true);
        }
        boolean $z0 = super.onPreparePanel(i, view, menu);
        if ($r3 != null) {
            $r3.setOverrideVisibleItems(false);
        }
        return $z0;
    }

    public void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
        MenuBuilder $r5;
        AppCompatDelegateImplV7.PanelFeatureState $r3 = this.this$0.getPanelState(0, true);
        if ($r3 == null || ($r5 = $r3.menu) == null) {
            super.onProvideKeyboardShortcuts(list, menu, i);
        } else {
            super.onProvideKeyboardShortcuts(list, $r5, i);
        }
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        if (Build.VERSION.SDK_INT >= 23) {
            return null;
        }
        return this.this$0.isHandleNativeActionModesEnabled() ? startAsSupportActionMode(callback) : super.onWindowStartingActionMode(callback);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return (!this.this$0.isHandleNativeActionModesEnabled() || i != 0) ? super.onWindowStartingActionMode(callback, i) : startAsSupportActionMode(callback);
    }

    /* access modifiers changed from: package-private */
    public final ActionMode startAsSupportActionMode(ActionMode.Callback callback) {
        SupportActionModeWrapper.CallbackWrapper $r1 = new SupportActionModeWrapper.CallbackWrapper(this.this$0.mContext, callback);
        com.org.v4.view.ActionMode $r5 = this.this$0.startSupportActionMode($r1);
        if ($r5 != null) {
            return $r1.getActionModeWrapper($r5);
        }
        return null;
    }
}
