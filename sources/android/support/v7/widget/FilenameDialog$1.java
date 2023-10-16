package android.support.v7.widget;

import android.view.View;

class FilenameDialog$1 implements View.OnClickListener {
    final /* synthetic */ SearchView this$0;

    FilenameDialog$1(SearchView searchView) {
        this.this$0 = searchView;
    }

    public void onClick(View view) {
        SearchView $r2 = this.this$0;
        if (view == $r2.mSearchButton) {
            $r2.onSearchClicked();
        } else if (view == $r2.mCloseButton) {
            $r2.onCloseClicked();
        } else if (view == $r2.mGoButton) {
            $r2.onSubmitQuery();
        } else if (view == $r2.mVoiceButton) {
            $r2.onVoiceClicked();
        } else if (view == $r2.mSearchSrcTextView) {
            $r2.forceSuggestionQuery();
        }
    }
}
