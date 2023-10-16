package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;

class CursorFilter extends Filter {
    CursorFilterClient mClient;

    interface CursorFilterClient {
        void changeCursor(Cursor cursor);

        CharSequence convertToString(Cursor cursor);

        Cursor getCursor();

        Cursor runQueryOnBackgroundThread(CharSequence charSequence);
    }

    CursorFilter(CursorFilterClient cursorFilterClient) {
        this.mClient = cursorFilterClient;
    }

    public CharSequence convertResultToString(Object obj) {
        return this.mClient.convertToString((Cursor) obj);
    }

    /* access modifiers changed from: protected */
    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor $r3 = this.mClient.runQueryOnBackgroundThread(charSequence);
        Cursor $r4 = $r3;
        Filter.FilterResults $r5 = new Filter.FilterResults();
        if ($r3 != null) {
            $r5.count = $r3.getCount();
        } else {
            $r5.count = 0;
            $r4 = null;
        }
        $r5.values = $r4;
        return $r5;
    }

    /* access modifiers changed from: protected */
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        Cursor $r1 = this.mClient.getCursor();
        Object $r5 = filterResults.values;
        if ($r5 != null && $r5 != $r1) {
            this.mClient.changeCursor((Cursor) $r5);
        }
    }
}
