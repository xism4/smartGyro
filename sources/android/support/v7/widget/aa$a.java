package android.support.v7.widget;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

class aa$a extends BaseAdapter {
    final /* synthetic */ ScrollingTabContainerView this$0;

    aa$a(ScrollingTabContainerView scrollingTabContainerView) {
        this.this$0 = scrollingTabContainerView;
    }

    public int getCount() {
        return this.this$0.mTabLayout.getChildCount();
    }

    public Object getItem(int i) {
        return ((aa$c) this.this$0.mTabLayout.getChildAt(i)).getTab();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            return this.this$0.createTabView((ActionBar.Tab) getItem(i), true);
        }
        ((aa$c) view).bindTab((ActionBar.Tab) getItem(i));
        return view;
    }
}
