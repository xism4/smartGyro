package android.support.v7.widget;

import android.support.v7.widget.SearchView;

class EventInfoFragment$2 implements Runnable {
    final /* synthetic */ SearchView.SearchAutoComplete this$0;

    EventInfoFragment$2(SearchView.SearchAutoComplete searchAutoComplete) {
        this.this$0 = searchAutoComplete;
    }

    public void run() {
        this.this$0.reset();
    }
}
