package android.support.v7.widget;

import android.view.KeyEvent;
import android.widget.TextView;

class LockActivity$1 implements TextView.OnEditorActionListener {
    final /* synthetic */ SearchView this$0;

    LockActivity$1(SearchView searchView) {
        this.this$0 = searchView;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        this.this$0.onSubmitQuery();
        return true;
    }
}
