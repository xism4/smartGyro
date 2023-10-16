package android.support.v4.widget;

import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper {
    private final ListView mTarget;

    public ListViewAutoScrollHelper(ListView listView) {
        super(listView);
        this.mTarget = listView;
    }

    public boolean canTargetScrollHorizontally(int i) {
        return false;
    }

    public boolean canTargetScrollVertically(int $i1) {
        ListView $r1 = this.mTarget;
        int $i2 = $r1.getCount();
        if ($i2 == 0) {
            return false;
        }
        int $i3 = $r1.getChildCount();
        int $i4 = $r1.getFirstVisiblePosition();
        int $i0 = $i4 + $i3;
        if ($i1 > 0) {
            return $i0 < $i2 || $r1.getChildAt($i3 - 1).getBottom() > $r1.getHeight();
        }
        if ($i1 < 0) {
            return $i4 > 0 || $r1.getChildAt(0).getTop() < 0;
        }
        return false;
    }

    public void scrollTargetBy(int i, int i2) {
        ListViewCompat.scrollListBy(this.mTarget, i2);
    }
}
