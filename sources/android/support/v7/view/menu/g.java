package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.support.v7.app.ByteVector;
import android.support.v7.app.e;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.org.v4.util.R$layout;
import org.cocos2dx.package_1.GameControllerDelegate;

class g implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, l$a {
    ListMenuPresenter a;
    private MenuBuilder b;
    private e c;
    private l$a d;

    public g(MenuBuilder menuBuilder) {
        this.b = menuBuilder;
    }

    public void a() {
        e $r1 = this.c;
        if ($r1 != null) {
            $r1.dismiss();
        }
    }

    public void a(IBinder iBinder) {
        MenuBuilder $r2 = this.b;
        ByteVector $r3 = new ByteVector($r2.getContext());
        this.a = new ListMenuPresenter($r3.get(), R$layout.abc_list_menu_item_layout);
        this.a.a((l$a) this);
        this.b.addMenuPresenter(this.a);
        $r3.a(this.a.getAdapter(), (DialogInterface.OnClickListener) this);
        View $r8 = $r2.b();
        if ($r8 != null) {
            $r3.b($r8);
        } else {
            $r3.a($r2.getHeaderIcon());
            $r3.b($r2.getValue());
        }
        $r3.b((DialogInterface.OnKeyListener) this);
        this.c = $r3.a();
        this.c.setOnDismissListener(this);
        WindowManager.LayoutParams $r13 = this.c.getWindow().getAttributes();
        $r13.type = GameControllerDelegate.THUMBSTICK_RIGHT_Y;
        if (iBinder != null) {
            $r13.token = iBinder;
        }
        $r13.flags |= 131072;
        this.c.show();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.performItemAction((MenuItemImpl) this.a.getAdapter().getItem(i), 0);
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (z || menuBuilder == this.b) {
            a();
        }
        l$a $r3 = this.d;
        if ($r3 != null) {
            $r3.onCloseMenu(menuBuilder, z);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.a(this.b, true);
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window $r4;
        View $r5;
        KeyEvent.DispatcherState $r6;
        View $r52;
        KeyEvent.DispatcherState $r62;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window $r42 = this.c.getWindow();
                if (!($r42 == null || ($r52 = $r42.getDecorView()) == null || ($r62 = $r52.getKeyDispatcherState()) == null)) {
                    $r62.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && ($r4 = this.c.getWindow()) != null && ($r5 = $r4.getDecorView()) != null && ($r6 = $r5.getKeyDispatcherState()) != null && $r6.isTracking(keyEvent)) {
                this.b.close(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.b.performShortcut(i, keyEvent, 0);
    }

    public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        l$a $r1 = this.d;
        if ($r1 != null) {
            return $r1.onOpenSubMenu(menuBuilder);
        }
        return false;
    }
}
