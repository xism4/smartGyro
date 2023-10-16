package android.support.v7.widget;

import android.view.View;

class aa$b implements View.OnClickListener {
    final /* synthetic */ ScrollingTabContainerView adapter;

    aa$b(ScrollingTabContainerView scrollingTabContainerView) {
        this.adapter = scrollingTabContainerView;
    }

    public void onClick(View view) {
        ((aa$c) view).getTab().select();
        int $i0 = this.adapter.mTabLayout.getChildCount();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            View $r6 = this.adapter.mTabLayout.getChildAt($i1);
            $r6.setSelected($r6 == view);
        }
    }
}
