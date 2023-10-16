package android.support.v7.view.menu;

import android.widget.ListView;

public interface ListPopupWindow {
    ListView add();

    void dismiss();

    boolean isShowing();

    void show();
}
