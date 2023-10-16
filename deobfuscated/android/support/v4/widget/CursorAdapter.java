package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public abstract class CursorAdapter
  extends BaseAdapter
  implements Filterable, CursorFilter.CursorFilterClient
{
  protected boolean mAutoRequery;
  protected ChangeObserver mChangeObserver;
  protected Context mContext;
  protected Cursor mCursor;
  protected CursorFilter mCursorFilter;
  protected DataSetObserver mDataSetObserver;
  protected boolean mDataValid;
  protected int mRowIDColumn;
  
  public CursorAdapter(Context paramContext, Cursor paramCursor, boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = 1;
    } else {
      i = 2;
    }
    init(paramContext, paramCursor, i);
  }
  
  public abstract void bindView(View paramView, Context paramContext, Cursor paramCursor);
  
  public void changeCursor(Cursor paramCursor)
  {
    paramCursor = swapCursor(paramCursor);
    if (paramCursor != null) {
      paramCursor.close();
    }
  }
  
  public abstract CharSequence convertToString(Cursor paramCursor);
  
  public int getCount()
  {
    if (mDataValid)
    {
      Cursor localCursor = mCursor;
      if (localCursor != null) {
        return localCursor.getCount();
      }
    }
    return 0;
  }
  
  public Cursor getCursor()
  {
    return mCursor;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (mDataValid)
    {
      mCursor.moveToPosition(paramInt);
      View localView = paramView;
      if (paramView == null) {
        localView = newDropDownView(mContext, mCursor, paramViewGroup);
      }
      bindView(localView, mContext, mCursor);
      return localView;
    }
    return null;
  }
  
  public Filter getFilter()
  {
    if (mCursorFilter == null) {
      mCursorFilter = new CursorFilter(this);
    }
    return mCursorFilter;
  }
  
  public Object getItem(int paramInt)
  {
    if (mDataValid)
    {
      Cursor localCursor = mCursor;
      if (localCursor != null)
      {
        localCursor.moveToPosition(paramInt);
        return mCursor;
      }
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    if (mDataValid)
    {
      Cursor localCursor = mCursor;
      if ((localCursor != null) && (localCursor.moveToPosition(paramInt))) {
        return mCursor.getLong(mRowIDColumn);
      }
    }
    return 0L;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (mDataValid)
    {
      if (mCursor.moveToPosition(paramInt))
      {
        View localView = paramView;
        if (paramView == null) {
          localView = newView(mContext, mCursor, paramViewGroup);
        }
        bindView(localView, mContext, mCursor);
        return localView;
      }
      paramView = new StringBuilder();
      paramView.append("couldn't move cursor to position ");
      paramView.append(paramInt);
      throw new IllegalStateException(paramView.toString());
    }
    throw new IllegalStateException("this should only be called when the cursor is valid");
  }
  
  void init(Context paramContext, Cursor paramCursor, int paramInt)
  {
    boolean bool = false;
    if ((paramInt & 0x1) == 1)
    {
      paramInt |= 0x2;
      mAutoRequery = true;
    }
    else
    {
      mAutoRequery = false;
    }
    if (paramCursor != null) {
      bool = true;
    }
    mCursor = paramCursor;
    mDataValid = bool;
    mContext = paramContext;
    int i;
    if (bool) {
      i = paramCursor.getColumnIndexOrThrow("_id");
    } else {
      i = -1;
    }
    mRowIDColumn = i;
    if ((paramInt & 0x2) == 2)
    {
      mChangeObserver = new ChangeObserver();
      paramContext = new MyDataSetObserver();
    }
    else
    {
      paramContext = null;
      mChangeObserver = null;
    }
    mDataSetObserver = paramContext;
    if (bool)
    {
      paramContext = mChangeObserver;
      if (paramContext != null) {
        paramCursor.registerContentObserver(paramContext);
      }
      paramContext = mDataSetObserver;
      if (paramContext != null) {
        paramCursor.registerDataSetObserver(paramContext);
      }
    }
  }
  
  public abstract View newDropDownView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup);
  
  public abstract View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup);
  
  protected void onContentChanged()
  {
    if (mAutoRequery)
    {
      Cursor localCursor = mCursor;
      if ((localCursor != null) && (!localCursor.isClosed())) {
        mDataValid = mCursor.requery();
      }
    }
  }
  
  public Cursor swapCursor(Cursor paramCursor)
  {
    Cursor localCursor = mCursor;
    if (paramCursor == localCursor) {
      return null;
    }
    Object localObject;
    if (localCursor != null)
    {
      localObject = mChangeObserver;
      if (localObject != null) {
        localCursor.unregisterContentObserver((ContentObserver)localObject);
      }
      localObject = mDataSetObserver;
      if (localObject != null) {
        localCursor.unregisterDataSetObserver((DataSetObserver)localObject);
      }
    }
    mCursor = paramCursor;
    if (paramCursor != null)
    {
      localObject = mChangeObserver;
      if (localObject != null) {
        paramCursor.registerContentObserver((ContentObserver)localObject);
      }
      localObject = mDataSetObserver;
      if (localObject != null) {
        paramCursor.registerDataSetObserver((DataSetObserver)localObject);
      }
      mRowIDColumn = paramCursor.getColumnIndexOrThrow("_id");
      mDataValid = true;
      notifyDataSetChanged();
      return localCursor;
    }
    mRowIDColumn = -1;
    mDataValid = false;
    notifyDataSetInvalidated();
    return localCursor;
  }
  
  class ChangeObserver
    extends ContentObserver
  {
    ChangeObserver()
    {
      super();
    }
    
    public boolean deliverSelfNotifications()
    {
      return true;
    }
    
    public void onChange(boolean paramBoolean)
    {
      onContentChanged();
    }
  }
  
  class MyDataSetObserver
    extends DataSetObserver
  {
    MyDataSetObserver() {}
    
    public void onChanged()
    {
      CursorAdapter localCursorAdapter = CursorAdapter.this;
      mDataValid = true;
      localCursorAdapter.notifyDataSetChanged();
    }
    
    public void onInvalidated()
    {
      CursorAdapter localCursorAdapter = CursorAdapter.this;
      mDataValid = false;
      localCursorAdapter.notifyDataSetInvalidated();
    }
  }
}
