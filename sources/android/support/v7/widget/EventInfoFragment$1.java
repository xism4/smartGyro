package android.support.v7.widget;

import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;

class EventInfoFragment$1 implements Runnable {
    final /* synthetic */ SearchView this$0;

    EventInfoFragment$1(SearchView searchView) {
        this.this$0 = searchView;
    }

    public void run() {
        CursorAdapter $r2 = this.this$0.mSuggestionsAdapter;
        if ($r2 != null && ($r2 instanceof SuggestionsAdapter)) {
            $r2.changeCursor((Cursor) null);
        }
    }
}
