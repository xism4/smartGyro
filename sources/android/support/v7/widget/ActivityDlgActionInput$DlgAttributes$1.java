package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;

class ActivityDlgActionInput$DlgAttributes$1 implements AdapterView.OnItemClickListener {
    final /* synthetic */ SearchView this$0;

    ActivityDlgActionInput$DlgAttributes$1(SearchView searchView) {
        this.this$0 = searchView;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.this$0.onItemClicked(i, 0, (String) null);
    }
}
