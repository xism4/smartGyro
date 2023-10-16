package android.support.v7.widget;

import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;

class ActivityChooserView$3 extends ListPopupWindow.ForwardingListener {
    final /* synthetic */ AppCompatSpinner.DropdownPopup mPopup;
    final /* synthetic */ AppCompatSpinner this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ActivityChooserView$3(AppCompatSpinner appCompatSpinner, View view, AppCompatSpinner.DropdownPopup dropdownPopup) {
        super(view);
        this.this$0 = appCompatSpinner;
        this.mPopup = dropdownPopup;
    }

    public android.support.v7.view.menu.ListPopupWindow getPopup() {
        return this.mPopup;
    }

    public boolean onForwardingStarted() {
        if (this.this$0.mPopup.isShowing()) {
            return true;
        }
        this.this$0.mPopup.show();
        return true;
    }
}
