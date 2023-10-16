package android.support.v7.widget;

import android.view.KeyEvent;
import android.view.View;

class TimePickerDialog$KeyboardListener implements View.OnKeyListener {
    final /* synthetic */ SearchView this$0;

    TimePickerDialog$KeyboardListener(SearchView searchView) {
        this.this$0 = searchView;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        SearchView $r3 = this.this$0;
        if ($r3.mSearchable == null) {
            return false;
        }
        if ($r3.mSearchSrcTextView.isPopupShowing() && this.this$0.mSearchSrcTextView.getListSelection() != -1) {
            return this.this$0.onSuggestionsKey(view, i, keyEvent);
        }
        if (this.this$0.mSearchSrcTextView.isEmpty() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
            return false;
        }
        view.cancelLongPress();
        SearchView $r32 = this.this$0;
        $r32.launchQuerySearch(0, (String) null, $r32.mSearchSrcTextView.getText().toString());
        return true;
    }
}
