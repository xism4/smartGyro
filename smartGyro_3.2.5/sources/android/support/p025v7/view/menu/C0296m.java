package android.support.p025v7.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.support.p025v7.app.C0249k;
import android.support.p025v7.view.menu.C0310v;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import org.cocos2dx.lib.GameControllerDelegate;
import p000a.p001a.p017d.p018a.C0142g;

/* renamed from: android.support.v7.view.menu.m */
class C0296m implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, C0310v.C0311a {

    /* renamed from: a */
    private C0293l f1021a;

    /* renamed from: b */
    private C0249k f1022b;

    /* renamed from: c */
    C0290j f1023c;

    /* renamed from: d */
    private C0310v.C0311a f1024d;

    public C0296m(C0293l lVar) {
        this.f1021a = lVar;
    }

    /* renamed from: a */
    public void mo1329a() {
        C0249k kVar = this.f1022b;
        if (kVar != null) {
            kVar.dismiss();
        }
    }

    /* renamed from: a */
    public void mo1330a(IBinder iBinder) {
        C0293l lVar = this.f1021a;
        C0249k.C0250a aVar = new C0249k.C0250a(lVar.mo1305e());
        this.f1023c = new C0290j(aVar.mo1016b(), C0142g.abc_list_menu_item_layout);
        this.f1023c.mo1128a((C0310v.C0311a) this);
        this.f1021a.mo1275a((C0310v) this.f1023c);
        aVar.mo1011a(this.f1023c.mo1250b(), (DialogInterface.OnClickListener) this);
        View i = lVar.mo1313i();
        if (i != null) {
            aVar.mo1010a(i);
        } else {
            aVar.mo1009a(lVar.mo1309g());
            aVar.mo1017b(lVar.mo1311h());
        }
        aVar.mo1008a((DialogInterface.OnKeyListener) this);
        this.f1022b = aVar.mo1015a();
        this.f1022b.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f1022b.getWindow().getAttributes();
        attributes.type = GameControllerDelegate.THUMBSTICK_RIGHT_Y;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f1022b.show();
    }

    /* renamed from: a */
    public void mo1072a(C0293l lVar, boolean z) {
        if (z || lVar == this.f1021a) {
            mo1329a();
        }
        C0310v.C0311a aVar = this.f1024d;
        if (aVar != null) {
            aVar.mo1072a(lVar, z);
        }
    }

    /* renamed from: a */
    public boolean mo1073a(C0293l lVar) {
        C0310v.C0311a aVar = this.f1024d;
        if (aVar != null) {
            return aVar.mo1073a(lVar);
        }
        return false;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f1021a.mo1280a((MenuItem) (C0299p) this.f1023c.mo1250b().getItem(i), 0);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.f1023c.mo1127a(this.f1021a, true);
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f1022b.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f1022b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f1021a.mo1279a(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f1021a.performShortcut(i, keyEvent, 0);
    }
}
