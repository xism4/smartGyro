package android.support.v7.widget;

import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

class SpinnerCompat$DropDownAdapter implements ListAdapter, SpinnerAdapter {
    private SpinnerAdapter mAdapter;
    private ListAdapter mListAdapter;

    public SpinnerCompat$DropDownAdapter(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
        this.mAdapter = spinnerAdapter;
        if (spinnerAdapter instanceof ListAdapter) {
            this.mListAdapter = (ListAdapter) spinnerAdapter;
        }
        if (theme == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
            ThemedSpinnerAdapter $r4 = (ThemedSpinnerAdapter) spinnerAdapter;
            if ($r4.getDropDownViewTheme() != theme) {
                $r4.setDropDownViewTheme(theme);
            }
        } else if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
            ThemedSpinnerAdapter $r6 = (ThemedSpinnerAdapter) spinnerAdapter;
            if ($r6.getDropDownViewTheme() == null) {
                $r6.setDropDownViewTheme(theme);
            }
        }
    }

    public boolean areAllItemsEnabled() {
        ListAdapter $r1 = this.mListAdapter;
        if ($r1 != null) {
            return $r1.areAllItemsEnabled();
        }
        return true;
    }

    public int getCount() {
        SpinnerAdapter $r1 = this.mAdapter;
        if ($r1 == null) {
            return 0;
        }
        return $r1.getCount();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        SpinnerAdapter $r3 = this.mAdapter;
        if ($r3 == null) {
            return null;
        }
        return $r3.getDropDownView(i, view, viewGroup);
    }

    public Object getItem(int i) {
        SpinnerAdapter $r1 = this.mAdapter;
        if ($r1 == null) {
            return null;
        }
        return $r1.getItem(i);
    }

    public long getItemId(int i) {
        SpinnerAdapter $r1 = this.mAdapter;
        if ($r1 == null) {
            return -1;
        }
        return $r1.getItemId(i);
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return getDropDownView(i, view, viewGroup);
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean hasStableIds() {
        SpinnerAdapter $r1 = this.mAdapter;
        return $r1 != null && $r1.hasStableIds();
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }

    public boolean isEnabled(int i) {
        ListAdapter $r1 = this.mListAdapter;
        if ($r1 != null) {
            return $r1.isEnabled(i);
        }
        return true;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter $r2 = this.mAdapter;
        if ($r2 != null) {
            $r2.registerDataSetObserver(dataSetObserver);
        }
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter $r2 = this.mAdapter;
        if ($r2 != null) {
            $r2.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
