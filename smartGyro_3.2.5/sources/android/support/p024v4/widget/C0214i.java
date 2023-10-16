package android.support.p024v4.widget;

import android.os.Build;
import android.view.View;
import android.widget.ListView;

/* renamed from: android.support.v4.widget.i */
public final class C0214i {
    /* renamed from: a */
    public static void m799a(ListView listView, int i) {
        View childAt;
        if (Build.VERSION.SDK_INT >= 19) {
            listView.scrollListBy(i);
            return;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition != -1 && (childAt = listView.getChildAt(0)) != null) {
            listView.setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i);
        }
    }
}
