package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.widget.CursorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public abstract class CursorAdapter extends BaseAdapter implements Filterable, CursorFilter.CursorFilterClient {
    protected boolean mAutoRequery;
    protected ChangeObserver mChangeObserver;
    protected Context mContext;
    protected Cursor mCursor;
    protected CursorFilter mCursorFilter;
    protected DataSetObserver mDataSetObserver;
    protected boolean mDataValid;
    protected int mRowIDColumn;

    class ChangeObserver extends ContentObserver {
        ChangeObserver() {
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            CursorAdapter.this.onContentChanged();
        }
    }

    class MyDataSetObserver extends DataSetObserver {
        MyDataSetObserver() {
        }

        public void onChanged() {
            CursorAdapter $r1 = CursorAdapter.this;
            $r1.mDataValid = true;
            $r1.notifyDataSetChanged();
        }

        public void onInvalidated() {
            CursorAdapter $r1 = CursorAdapter.this;
            $r1.mDataValid = false;
            $r1.notifyDataSetInvalidated();
        }
    }

    public CursorAdapter(Context context, Cursor cursor, boolean z) {
        init(context, cursor, z ? (byte) 1 : 2);
    }

    public abstract void bindView(View view, Context context, Cursor cursor);

    public void changeCursor(Cursor cursor) {
        Cursor $r1 = swapCursor(cursor);
        if ($r1 != null) {
            $r1.close();
        }
    }

    public abstract CharSequence convertToString(Cursor cursor);

    public int getCount() {
        Cursor $r1;
        if (!this.mDataValid || ($r1 = this.mCursor) == null) {
            return 0;
        }
        return $r1.getCount();
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    public View getDropDownView(int i, View $r1, ViewGroup viewGroup) {
        if (!this.mDataValid) {
            return null;
        }
        this.mCursor.moveToPosition(i);
        if ($r1 == null) {
            $r1 = newDropDownView(this.mContext, this.mCursor, viewGroup);
        }
        bindView($r1, this.mContext, this.mCursor);
        return $r1;
    }

    public Filter getFilter() {
        if (this.mCursorFilter == null) {
            this.mCursorFilter = new CursorFilter(this);
        }
        return this.mCursorFilter;
    }

    public Object getItem(int i) {
        Cursor $r1;
        if (!this.mDataValid || ($r1 = this.mCursor) == null) {
            return null;
        }
        $r1.moveToPosition(i);
        return this.mCursor;
    }

    public long getItemId(int i) {
        Cursor $r1;
        if (!this.mDataValid || ($r1 = this.mCursor) == null || !$r1.moveToPosition(i)) {
            return 0;
        }
        return this.mCursor.getLong(this.mRowIDColumn);
    }

    public View getView(int i, View $r1, ViewGroup viewGroup) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.mCursor.moveToPosition(i)) {
            if ($r1 == null) {
                $r1 = newView(this.mContext, this.mCursor, viewGroup);
            }
            bindView($r1, this.mContext, this.mCursor);
            return $r1;
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
    }

    /* access modifiers changed from: package-private */
    public void init(Context context, Cursor cursor, int $i1) {
        MyDataSetObserver $r4;
        boolean $z0 = false;
        if (($i1 & 1) == 1) {
            $i1 |= 2;
            this.mAutoRequery = true;
        } else {
            this.mAutoRequery = false;
        }
        if (cursor != null) {
            $z0 = true;
        }
        this.mCursor = cursor;
        this.mDataValid = $z0;
        this.mContext = context;
        this.mRowIDColumn = $z0 ? cursor.getColumnIndexOrThrow("_id") : -1;
        if (($i1 & 2) == 2) {
            this.mChangeObserver = new ChangeObserver();
            $r4 = new MyDataSetObserver();
        } else {
            $r4 = null;
            this.mChangeObserver = null;
        }
        this.mDataSetObserver = $r4;
        if ($z0) {
            ChangeObserver $r3 = this.mChangeObserver;
            if ($r3 != null) {
                cursor.registerContentObserver($r3);
            }
            DataSetObserver $r5 = this.mDataSetObserver;
            if ($r5 != null) {
                cursor.registerDataSetObserver($r5);
            }
        }
    }

    public abstract View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup);

    public abstract View newView(Context context, Cursor cursor, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public void onContentChanged() {
        Cursor $r1;
        if (this.mAutoRequery && ($r1 = this.mCursor) != null && !$r1.isClosed()) {
            this.mDataValid = this.mCursor.requery();
        }
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor $r1 = this.mCursor;
        if (cursor == $r1) {
            return null;
        }
        if ($r1 != null) {
            ChangeObserver $r3 = this.mChangeObserver;
            if ($r3 != null) {
                $r1.unregisterContentObserver($r3);
            }
            DataSetObserver $r4 = this.mDataSetObserver;
            if ($r4 != null) {
                $r1.unregisterDataSetObserver($r4);
            }
        }
        this.mCursor = cursor;
        if (cursor != null) {
            ChangeObserver $r32 = this.mChangeObserver;
            if ($r32 != null) {
                cursor.registerContentObserver($r32);
            }
            DataSetObserver $r42 = this.mDataSetObserver;
            if ($r42 != null) {
                cursor.registerDataSetObserver($r42);
            }
            this.mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            notifyDataSetChanged();
            return $r1;
        }
        this.mRowIDColumn = -1;
        this.mDataValid = false;
        notifyDataSetInvalidated();
        return $r1;
    }
}
