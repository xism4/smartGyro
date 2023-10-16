package android.support.v4.widget;

import android.os.Build;
import android.view.View;
import android.widget.ListView;

public final class ListViewCompat {
    public static void scrollListBy(ListView listView, int i) {
        View $r1;
        if (Build.VERSION.SDK_INT >= 19) {
            listView.scrollListBy(i);
            return;
        }
        int $i1 = listView.getFirstVisiblePosition();
        if ($i1 != -1 && ($r1 = listView.getChildAt(0)) != null) {
            listView.setSelectionFromTop($i1, $r1.getTop() - i);
        }
    }
}
