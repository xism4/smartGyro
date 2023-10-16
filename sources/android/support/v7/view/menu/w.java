package android.support.v7.view.menu;

import android.widget.PopupWindow;

class w implements PopupWindow.OnDismissListener {
    final /* synthetic */ h b;

    w(h hVar) {
        this.b = hVar;
    }

    public void onDismiss() {
        this.b.b();
    }
}
