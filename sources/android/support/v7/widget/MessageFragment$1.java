package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;

class MessageFragment$1 implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ SearchView this$0;

    MessageFragment$1(SearchView searchView) {
        this.this$0 = searchView;
    }

    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        this.this$0.onItemSelected(i);
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
