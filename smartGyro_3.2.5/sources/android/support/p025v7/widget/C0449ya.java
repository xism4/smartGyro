package android.support.p025v7.widget;

import android.support.p025v7.view.menu.C0280a;
import android.view.View;
import android.view.Window;

/* renamed from: android.support.v7.widget.ya */
class C0449ya implements View.OnClickListener {

    /* renamed from: a */
    final C0280a f1658a = new C0280a(this.f1659b.f1085a.getContext(), 0, 16908332, 0, 0, this.f1659b.f1093i);

    /* renamed from: b */
    final /* synthetic */ C0318Aa f1659b;

    C0449ya(C0318Aa aa) {
        this.f1659b = aa;
    }

    public void onClick(View view) {
        C0318Aa aa = this.f1659b;
        Window.Callback callback = aa.f1096l;
        if (callback != null && aa.f1097m) {
            callback.onMenuItemSelected(0, this.f1658a);
        }
    }
}
