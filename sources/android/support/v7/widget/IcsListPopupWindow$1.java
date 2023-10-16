package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;

class IcsListPopupWindow$1 implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ ListPopupWindow this$0;

    IcsListPopupWindow$1(ListPopupWindow listPopupWindow) {
        this.this$0 = listPopupWindow;
    }

    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        ListViewCompat $r4;
        if (i != -1 && ($r4 = this.this$0.mDropDownList) != null) {
            $r4.setListSelectionHidden(false);
        }
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
