package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.widget.PopupWindowCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.styleable;
import java.lang.reflect.Method;

public class ListPopupWindow
  implements android.support.v7.view.menu.ListPopupWindow
{
  private static Method b;
  private static Method sClipToWindowEnabledMethod;
  private static Method sGetMaxAvailableHeightMethod;
  private boolean DEBUG = true;
  private ListAdapter mAdapter;
  private Context mContext;
  private boolean mDropDownAlwaysVisible = false;
  private View mDropDownAnchorView;
  private int mDropDownGravity = 0;
  private int mDropDownHeight = -2;
  private int mDropDownHorizontalOffset;
  ListViewCompat mDropDownList;
  private Drawable mDropDownListHighlight;
  private int mDropDownVerticalOffset;
  private boolean mDropDownVerticalOffsetSet;
  private int mDropDownWidth = -2;
  private int mDropDownWindowLayoutType = 1002;
  private boolean mForceIgnoreOutsideTouch = false;
  final Handler mHandler;
  private final ListSelectorHider mHideSelector = new ListSelectorHider();
  private AdapterView.OnItemClickListener mItemClickListener;
  private AdapterView.OnItemSelectedListener mItemSelectedListener;
  int mListItemExpandMaximum = Integer.MAX_VALUE;
  private boolean mModal;
  private DataSetObserver mObserver;
  PopupWindow mPopup;
  private boolean mPostedShow;
  private int mPromptPosition = 0;
  private View mPromptView;
  private final PopupScrollListener mScrollListener = new PopupScrollListener();
  private Runnable mShowDropDownRunnable;
  private Rect mTempAdapter;
  private final Rect mTempRect = new Rect();
  private final PopupTouchInterceptor mTouchInterceptor = new PopupTouchInterceptor();
  final ResizePopupRunnable runnable = new ResizePopupRunnable();
  private boolean x;
  
  static
  {
    Object localObject = Boolean.TYPE;
    try
    {
      localObject = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[] { localObject });
      sClipToWindowEnabledMethod = (Method)localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException1)
    {
      Class localClass;
      for (;;) {}
    }
    Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
    localObject = Integer.TYPE;
    localClass = Boolean.TYPE;
    try
    {
      localObject = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[] { View.class, localObject, localClass });
      sGetMaxAvailableHeightMethod = (Method)localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException2)
    {
      for (;;) {}
    }
    Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
    try
    {
      localObject = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[] { Rect.class });
      b = (Method)localObject;
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException3)
    {
      for (;;) {}
    }
    Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    mContext = paramContext;
    mHandler = new Handler(paramContext.getMainLooper());
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ListPopupWindow, paramInt1, paramInt2);
    mDropDownHorizontalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
    mDropDownVerticalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
    if (mDropDownVerticalOffset != 0) {
      mDropDownVerticalOffsetSet = true;
    }
    localTypedArray.recycle();
    mPopup = new AppCompatPopupWindow(paramContext, paramAttributeSet, paramInt1, paramInt2);
    mPopup.setInputMethodMode(1);
  }
  
  private int buildDropDown()
  {
    Object localObject1 = mDropDownList;
    int k = Integer.MIN_VALUE;
    boolean bool = true;
    Object localObject2;
    int i;
    int j;
    if (localObject1 == null)
    {
      localObject1 = mContext;
      mShowDropDownRunnable = new ListPopupWindow.2(this);
      mDropDownList = show((Context)localObject1, mModal ^ true);
      localObject2 = mDropDownListHighlight;
      if (localObject2 != null) {
        mDropDownList.setSelector((Drawable)localObject2);
      }
      mDropDownList.setAdapter(mAdapter);
      mDropDownList.setOnItemClickListener(mItemClickListener);
      mDropDownList.setFocusable(true);
      mDropDownList.setFocusableInTouchMode(true);
      mDropDownList.setOnItemSelectedListener(new IcsListPopupWindow.1(this));
      mDropDownList.setOnScrollListener(mScrollListener);
      localObject2 = mItemSelectedListener;
      if (localObject2 != null) {
        mDropDownList.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)localObject2);
      }
      localObject2 = mDropDownList;
      View localView = mPromptView;
      if (localView != null)
      {
        localObject1 = new LinearLayout((Context)localObject1);
        ((LinearLayout)localObject1).setOrientation(1);
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0F);
        i = mPromptPosition;
        if (i != 0)
        {
          if (i != 1)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Invalid hint position ");
            ((StringBuilder)localObject2).append(mPromptPosition);
            Log.e("ListPopupWindow", ((StringBuilder)localObject2).toString());
          }
          else
          {
            ((ViewGroup)localObject1).addView((View)localObject2, localLayoutParams);
            ((ViewGroup)localObject1).addView(localView);
          }
        }
        else
        {
          ((ViewGroup)localObject1).addView(localView);
          ((ViewGroup)localObject1).addView((View)localObject2, localLayoutParams);
        }
        i = mDropDownWidth;
        if (i >= 0)
        {
          j = Integer.MIN_VALUE;
        }
        else
        {
          i = 0;
          j = 0;
        }
        localView.measure(View.MeasureSpec.makeMeasureSpec(i, j), 0);
        localObject2 = (LinearLayout.LayoutParams)localView.getLayoutParams();
        i = localView.getMeasuredHeight() + topMargin + bottomMargin;
      }
      else
      {
        i = 0;
        localObject1 = localObject2;
      }
      mPopup.setContentView((View)localObject1);
    }
    else
    {
      localObject1 = (ViewGroup)mPopup.getContentView();
      localObject1 = mPromptView;
      if (localObject1 != null)
      {
        localObject2 = (LinearLayout.LayoutParams)((View)localObject1).getLayoutParams();
        i = ((View)localObject1).getMeasuredHeight() + topMargin + bottomMargin;
      }
      else
      {
        i = 0;
      }
    }
    localObject1 = mPopup.getBackground();
    int n;
    if (localObject1 != null)
    {
      ((Drawable)localObject1).getPadding(mTempRect);
      localObject1 = mTempRect;
      n = top;
      m = bottom + n;
      j = m;
      if (!mDropDownVerticalOffsetSet)
      {
        mDropDownVerticalOffset = (-n);
        j = m;
      }
    }
    else
    {
      mTempRect.setEmpty();
      j = 0;
    }
    if (mPopup.getInputMethodMode() != 2) {
      bool = false;
    }
    int m = getMaxAvailableHeight(getAnchorView(), mDropDownVerticalOffset, bool);
    if ((!mDropDownAlwaysVisible) && (mDropDownHeight != -1))
    {
      n = mDropDownWidth;
      if (n != -2)
      {
        k = 1073741824;
        if (n == -1) {}
      }
      for (k = View.MeasureSpec.makeMeasureSpec(n, 1073741824);; k = View.MeasureSpec.makeMeasureSpec(n - (left + right), k))
      {
        break;
        n = mContext.getResources().getDisplayMetrics().widthPixels;
        localObject1 = mTempRect;
      }
      m = mDropDownList.measureHeightOfChildrenCompat(k, 0, -1, m - i, -1);
      k = i;
      if (m > 0) {
        k = i + (j + (mDropDownList.getPaddingTop() + mDropDownList.getPaddingBottom()));
      }
      return m + k;
    }
    return m + j;
  }
  
  private int getMaxAvailableHeight(View paramView, int paramInt, boolean paramBoolean)
  {
    Object localObject = sGetMaxAvailableHeightMethod;
    PopupWindow localPopupWindow;
    if (localObject != null) {
      localPopupWindow = mPopup;
    }
    try
    {
      localObject = ((Method)localObject).invoke(localPopupWindow, new Object[] { paramView, Integer.valueOf(paramInt), Boolean.valueOf(paramBoolean) });
      localObject = (Integer)localObject;
      int i = ((Integer)localObject).intValue();
      return i;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
    return mPopup.getMaxAvailableHeight(paramView, paramInt);
  }
  
  private void removePromptView()
  {
    Object localObject = mPromptView;
    if (localObject != null)
    {
      localObject = ((View)localObject).getParent();
      if ((localObject instanceof ViewGroup)) {
        ((ViewGroup)localObject).removeView(mPromptView);
      }
    }
  }
  
  private void setPopupClipToScreenEnabled(boolean paramBoolean)
  {
    Method localMethod = sClipToWindowEnabledMethod;
    if (localMethod != null)
    {
      PopupWindow localPopupWindow = mPopup;
      try
      {
        localMethod.invoke(localPopupWindow, new Object[] { Boolean.valueOf(paramBoolean) });
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
      return;
    }
  }
  
  public void a(int paramInt)
  {
    mDropDownGravity = paramInt;
  }
  
  public void a(View paramView)
  {
    mDropDownAnchorView = paramView;
  }
  
  public ListView add()
  {
    return mDropDownList;
  }
  
  public void clearListSelection()
  {
    ListViewCompat localListViewCompat = mDropDownList;
    if (localListViewCompat != null)
    {
      localListViewCompat.setListSelectionHidden(true);
      localListViewCompat.requestLayout();
    }
  }
  
  public void dismiss()
  {
    mPopup.dismiss();
    removePromptView();
    mPopup.setContentView(null);
    mDropDownList = null;
    mHandler.removeCallbacks(runnable);
  }
  
  public void dismiss(int paramInt)
  {
    mPopup.setAnimationStyle(paramInt);
  }
  
  public void dismiss(boolean paramBoolean)
  {
    mModal = paramBoolean;
    mPopup.setFocusable(paramBoolean);
  }
  
  public View getAnchorView()
  {
    return mDropDownAnchorView;
  }
  
  public Drawable getBackground()
  {
    return mPopup.getBackground();
  }
  
  public int getHorizontalOffset()
  {
    return mDropDownHorizontalOffset;
  }
  
  public int getVerticalOffset()
  {
    if (!mDropDownVerticalOffsetSet) {
      return 0;
    }
    return mDropDownVerticalOffset;
  }
  
  public int getWidth()
  {
    return mDropDownWidth;
  }
  
  public boolean isInputMethodNotNeeded()
  {
    return mPopup.getInputMethodMode() == 2;
  }
  
  public boolean isModal()
  {
    return mModal;
  }
  
  public boolean isShowing()
  {
    return mPopup.isShowing();
  }
  
  public void setAdapter(int paramInt)
  {
    mDropDownHorizontalOffset = paramInt;
  }
  
  public void setAdapter(Rect paramRect)
  {
    mTempAdapter = paramRect;
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    DataSetObserver localDataSetObserver = mObserver;
    if (localDataSetObserver == null)
    {
      mObserver = new PopupDataSetObserver();
    }
    else
    {
      ListAdapter localListAdapter = mAdapter;
      if (localListAdapter != null) {
        localListAdapter.unregisterDataSetObserver(localDataSetObserver);
      }
    }
    mAdapter = paramListAdapter;
    if (paramListAdapter != null) {
      paramListAdapter.registerDataSetObserver(mObserver);
    }
    paramListAdapter = mDropDownList;
    if (paramListAdapter != null) {
      paramListAdapter.setAdapter(mAdapter);
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    mPopup.setBackgroundDrawable(paramDrawable);
  }
  
  public void setContentWidth(int paramInt)
  {
    Object localObject = mPopup.getBackground();
    if (localObject != null)
    {
      ((Drawable)localObject).getPadding(mTempRect);
      localObject = mTempRect;
      mDropDownWidth = (left + right + paramInt);
      return;
    }
    setWidth(paramInt);
  }
  
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    mPopup.setOnDismissListener(paramOnDismissListener);
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    mItemClickListener = paramOnItemClickListener;
  }
  
  public void setPromptPosition(int paramInt)
  {
    mPromptPosition = paramInt;
  }
  
  public void setSelection(int paramInt)
  {
    ListViewCompat localListViewCompat = mDropDownList;
    if ((isShowing()) && (localListViewCompat != null))
    {
      localListViewCompat.setListSelectionHidden(false);
      localListViewCompat.setSelection(paramInt);
      if (localListViewCompat.getChoiceMode() != 0) {
        localListViewCompat.setItemChecked(paramInt, true);
      }
    }
  }
  
  public void setVerticalOffset(int paramInt)
  {
    mDropDownVerticalOffset = paramInt;
    mDropDownVerticalOffsetSet = true;
  }
  
  public void setWidth(int paramInt)
  {
    mDropDownWidth = paramInt;
  }
  
  ListViewCompat show(Context paramContext, boolean paramBoolean)
  {
    return new ListViewCompat(paramContext, paramBoolean);
  }
  
  public void show()
  {
    int j = buildDropDown();
    boolean bool2 = isInputMethodNotNeeded();
    PopupWindowCompat.setWindowLayoutType(mPopup, mDropDownWindowLayoutType);
    boolean bool3 = mPopup.isShowing();
    boolean bool1 = true;
    int i;
    Object localObject2;
    if (bool3)
    {
      if (!ViewCompat.isAttachedToWindow(getAnchorView())) {
        return;
      }
      k = mDropDownWidth;
      if (k == -1)
      {
        i = -1;
      }
      else
      {
        i = k;
        if (k == -2) {
          i = getAnchorView().getWidth();
        }
      }
      k = mDropDownHeight;
      if (k == -1)
      {
        if (!bool2) {
          j = -1;
        }
        if (bool2)
        {
          localObject1 = mPopup;
          if (mDropDownWidth == -1) {
            k = -1;
          } else {
            k = 0;
          }
          ((PopupWindow)localObject1).setWidth(k);
          mPopup.setHeight(0);
        }
        else
        {
          localObject1 = mPopup;
          if (mDropDownWidth == -1) {
            k = -1;
          } else {
            k = 0;
          }
          ((PopupWindow)localObject1).setWidth(k);
          mPopup.setHeight(-1);
        }
      }
      else if (k != -2)
      {
        j = k;
      }
      localObject1 = mPopup;
      if ((mForceIgnoreOutsideTouch) || (mDropDownAlwaysVisible)) {
        bool1 = false;
      }
      ((PopupWindow)localObject1).setOutsideTouchable(bool1);
      localObject1 = mPopup;
      localObject2 = getAnchorView();
      int m = mDropDownHorizontalOffset;
      int n = mDropDownVerticalOffset;
      k = i;
      if (i < 0) {
        k = -1;
      }
      i = j;
      if (j < 0) {
        i = -1;
      }
      ((PopupWindow)localObject1).update((View)localObject2, m, n, k, i);
      return;
    }
    int k = mDropDownWidth;
    if (k == -1)
    {
      i = -1;
    }
    else
    {
      i = k;
      if (k == -2) {
        i = getAnchorView().getWidth();
      }
    }
    k = mDropDownHeight;
    if (k == -1) {
      j = -1;
    } else if (k != -2) {
      j = k;
    }
    mPopup.setWidth(i);
    mPopup.setHeight(j);
    setPopupClipToScreenEnabled(true);
    Object localObject1 = mPopup;
    if ((!mForceIgnoreOutsideTouch) && (!mDropDownAlwaysVisible)) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ((PopupWindow)localObject1).setOutsideTouchable(bool1);
    mPopup.setTouchInterceptor(mTouchInterceptor);
    if (mPostedShow) {
      PopupWindowCompat.init(mPopup, x);
    }
    localObject1 = b;
    if (localObject1 != null)
    {
      localObject2 = mPopup;
      Rect localRect = mTempAdapter;
      try
      {
        ((Method)localObject1).invoke(localObject2, new Object[] { localRect });
      }
      catch (Exception localException)
      {
        Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", localException);
      }
    }
    PopupWindowCompat.update(mPopup, getAnchorView(), mDropDownHorizontalOffset, mDropDownVerticalOffset, mDropDownGravity);
    mDropDownList.setSelection(-1);
    if ((!mModal) || (mDropDownList.isInTouchMode())) {
      clearListSelection();
    }
    if (!mModal) {
      mHandler.post(mHideSelector);
    }
  }
  
  public void show(int paramInt)
  {
    mPopup.setInputMethodMode(paramInt);
  }
  
  public void show(boolean paramBoolean)
  {
    mPostedShow = true;
    x = paramBoolean;
  }
  
  class ListSelectorHider
    implements Runnable
  {
    ListSelectorHider() {}
    
    public void run()
    {
      clearListSelection();
    }
  }
  
  class PopupDataSetObserver
    extends DataSetObserver
  {
    PopupDataSetObserver() {}
    
    public void onChanged()
    {
      if (isShowing()) {
        show();
      }
    }
    
    public void onInvalidated()
    {
      dismiss();
    }
  }
  
  class PopupScrollListener
    implements AbsListView.OnScrollListener
  {
    PopupScrollListener() {}
    
    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      if ((paramInt == 1) && (!isInputMethodNotNeeded()) && (mPopup.getContentView() != null))
      {
        paramAbsListView = ListPopupWindow.this;
        mHandler.removeCallbacks(runnable);
        runnable.run();
      }
    }
  }
  
  class PopupTouchInterceptor
    implements View.OnTouchListener
  {
    PopupTouchInterceptor() {}
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      int i = paramMotionEvent.getAction();
      int j = (int)paramMotionEvent.getX();
      int k = (int)paramMotionEvent.getY();
      if (i == 0)
      {
        paramView = mPopup;
        if ((paramView != null) && (paramView.isShowing()) && (j >= 0) && (j < mPopup.getWidth()) && (k >= 0) && (k < mPopup.getHeight()))
        {
          paramView = ListPopupWindow.this;
          mHandler.postDelayed(runnable, 250L);
          break label126;
        }
      }
      if (i == 1)
      {
        paramView = ListPopupWindow.this;
        mHandler.removeCallbacks(runnable);
      }
      label126:
      return false;
    }
  }
  
  class ResizePopupRunnable
    implements Runnable
  {
    ResizePopupRunnable() {}
    
    public void run()
    {
      Object localObject = mDropDownList;
      if ((localObject != null) && (ViewCompat.isAttachedToWindow((View)localObject)) && (mDropDownList.getCount() > mDropDownList.getChildCount()))
      {
        int i = mDropDownList.getChildCount();
        localObject = ListPopupWindow.this;
        if (i <= mListItemExpandMaximum)
        {
          mPopup.setInputMethodMode(2);
          show();
        }
      }
    }
  }
}
