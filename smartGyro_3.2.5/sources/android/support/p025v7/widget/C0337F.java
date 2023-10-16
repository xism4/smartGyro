package android.support.p025v7.widget;

import android.support.p025v7.widget.C0328C;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* renamed from: android.support.v7.widget.F */
class C0337F implements PopupWindow.OnDismissListener {

    /* renamed from: a */
    final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f1215a;

    /* renamed from: b */
    final /* synthetic */ C0328C.C0330b f1216b;

    C0337F(C0328C.C0330b bVar, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        this.f1216b = bVar;
        this.f1215a = onGlobalLayoutListener;
    }

    public void onDismiss() {
        ViewTreeObserver viewTreeObserver = C0328C.this.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f1215a);
        }
    }
}
