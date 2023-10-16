package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.v4.graphics.drawable.DrawableWrapper;
import com.org.v4.util.R.attr;
import java.lang.reflect.Field;

class ListViewCompat
  extends ListView
{
  SnackBar b;
  private ViewPropertyAnimatorCompat mClickAnimation;
  private boolean mDrawsInPressedState;
  private boolean mHijackFocus;
  private Field mIsChildViewEnabled;
  private boolean mListSelectionHidden;
  private int mMotionPosition;
  private ListViewAutoScrollHelper mScrollHelper;
  private int mSelectionBottomPadding = 0;
  private int mSelectionLeftPadding = 0;
  private int mSelectionRightPadding = 0;
  private int mSelectionTopPadding = 0;
  private GateKeeperDrawable mSelector;
  private final Rect mSelectorRect = new Rect();
  
  ListViewCompat(Context paramContext, boolean paramBoolean)
  {
    super(paramContext, null, R.attr.dropDownListViewStyle);
    mHijackFocus = paramBoolean;
    setCacheColorHint(0);
    try
    {
      paramContext = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
      mIsChildViewEnabled = paramContext;
      paramContext = mIsChildViewEnabled;
      paramContext.setAccessible(true);
      return;
    }
    catch (NoSuchFieldException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void clearPressedItem()
  {
    mDrawsInPressedState = false;
    setPressed(false);
    drawableStateChanged();
    Object localObject = getChildAt(mMotionPosition - getFirstVisiblePosition());
    if (localObject != null) {
      ((View)localObject).setPressed(false);
    }
    localObject = mClickAnimation;
    if (localObject != null)
    {
      ((ViewPropertyAnimatorCompat)localObject).cancel();
      mClickAnimation = null;
    }
  }
  
  private void clickPressedItem(View paramView, int paramInt)
  {
    performItemClick(paramView, paramInt, getItemIdAtPosition(paramInt));
  }
  
  private void drawSelectorCompat(Canvas paramCanvas)
  {
    if (!mSelectorRect.isEmpty())
    {
      Drawable localDrawable = getSelector();
      if (localDrawable != null)
      {
        localDrawable.setBounds(mSelectorRect);
        localDrawable.draw(paramCanvas);
      }
    }
  }
  
  private void positionSelectorCompat(int paramInt, View paramView)
  {
    Object localObject = mSelectorRect;
    ((Rect)localObject).set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
    left -= mSelectionLeftPadding;
    top -= mSelectionTopPadding;
    right += mSelectionRightPadding;
    bottom += mSelectionBottomPadding;
    localObject = mIsChildViewEnabled;
    try
    {
      boolean bool1 = ((Field)localObject).getBoolean(this);
      boolean bool2 = paramView.isEnabled();
      if (bool2 != bool1)
      {
        paramView = mIsChildViewEnabled;
        if (!bool1) {
          bool1 = true;
        } else {
          bool1 = false;
        }
        paramView.set(this, Boolean.valueOf(bool1));
        if (paramInt != -1)
        {
          refreshDrawableState();
          return;
        }
      }
    }
    catch (IllegalAccessException paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  private void positionSelectorLikeFocusCompat(int paramInt, View paramView)
  {
    Drawable localDrawable = getSelector();
    boolean bool = true;
    int i;
    if ((localDrawable != null) && (paramInt != -1)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      localDrawable.setVisible(false, false);
    }
    positionSelectorCompat(paramInt, paramView);
    if (i != 0)
    {
      paramView = mSelectorRect;
      float f1 = paramView.exactCenterX();
      float f2 = paramView.exactCenterY();
      if (getVisibility() != 0) {
        bool = false;
      }
      localDrawable.setVisible(bool, false);
      DrawableCompat.setHotspot(localDrawable, f1, f2);
    }
  }
  
  private void positionSelectorLikeTouchCompat(int paramInt, View paramView, float paramFloat1, float paramFloat2)
  {
    positionSelectorLikeFocusCompat(paramInt, paramView);
    paramView = getSelector();
    if ((paramView != null) && (paramInt != -1)) {
      DrawableCompat.setHotspot(paramView, paramFloat1, paramFloat2);
    }
  }
  
  private void setPressedItem(View paramView, int paramInt, float paramFloat1, float paramFloat2)
  {
    mDrawsInPressedState = true;
    if (Build.VERSION.SDK_INT >= 21) {
      drawableHotspotChanged(paramFloat1, paramFloat2);
    }
    if (!isPressed()) {
      setPressed(true);
    }
    layoutChildren();
    int i = mMotionPosition;
    if (i != -1)
    {
      View localView = getChildAt(i - getFirstVisiblePosition());
      if ((localView != null) && (localView != paramView) && (localView.isPressed())) {
        localView.setPressed(false);
      }
    }
    mMotionPosition = paramInt;
    float f1 = paramView.getLeft();
    float f2 = paramView.getTop();
    if (Build.VERSION.SDK_INT >= 21) {
      paramView.drawableHotspotChanged(paramFloat1 - f1, paramFloat2 - f2);
    }
    if (!paramView.isPressed()) {
      paramView.setPressed(true);
    }
    positionSelectorLikeTouchCompat(paramInt, paramView, paramFloat1, paramFloat2);
    setSelectorEnabled(false);
    refreshDrawableState();
  }
  
  private void setSelectorEnabled(boolean paramBoolean)
  {
    GateKeeperDrawable localGateKeeperDrawable = mSelector;
    if (localGateKeeperDrawable != null) {
      localGateKeeperDrawable.setEnabled(paramBoolean);
    }
  }
  
  private boolean shouldShowSelectorCompat()
  {
    return mDrawsInPressedState;
  }
  
  private void updateSelectorStateCompat()
  {
    Drawable localDrawable = getSelector();
    if ((localDrawable != null) && (shouldShowSelectorCompat()) && (isPressed())) {
      localDrawable.setState(getDrawableState());
    }
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    drawSelectorCompat(paramCanvas);
    super.dispatchDraw(paramCanvas);
  }
  
  protected void drawableStateChanged()
  {
    if (b != null) {
      return;
    }
    super.drawableStateChanged();
    setSelectorEnabled(true);
    updateSelectorStateCompat();
  }
  
  public boolean hasFocus()
  {
    return (mHijackFocus) || (super.hasFocus());
  }
  
  public boolean hasWindowFocus()
  {
    return (mHijackFocus) || (super.hasWindowFocus());
  }
  
  public boolean isFocused()
  {
    return (mHijackFocus) || (super.isFocused());
  }
  
  public boolean isInTouchMode()
  {
    return ((mHijackFocus) && (mListSelectionHidden)) || (super.isInTouchMode());
  }
  
  public int measureHeightOfChildrenCompat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    paramInt3 = getListPaddingTop();
    int j = getListPaddingBottom();
    getListPaddingLeft();
    getListPaddingRight();
    int i = getDividerHeight();
    paramInt2 = i;
    Object localObject = getDivider();
    ListAdapter localListAdapter = getAdapter();
    if (localListAdapter == null) {
      return paramInt3 + j;
    }
    paramInt3 += j;
    if ((i <= 0) || (localObject == null)) {
      paramInt2 = 0;
    }
    int i1 = localListAdapter.getCount();
    localObject = null;
    j = 0;
    int n = 0;
    i = 0;
    while (j < i1)
    {
      int m = localListAdapter.getItemViewType(j);
      int k = n;
      if (m != n)
      {
        localObject = null;
        k = m;
      }
      View localView = localListAdapter.getView(j, (View)localObject, this);
      localObject = localView;
      ViewGroup.LayoutParams localLayoutParams2 = localView.getLayoutParams();
      ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
      if (localLayoutParams2 == null)
      {
        localLayoutParams2 = generateDefaultLayoutParams();
        localLayoutParams1 = localLayoutParams2;
        localView.setLayoutParams(localLayoutParams2);
      }
      m = height;
      if (m > 0) {
        m = View.MeasureSpec.makeMeasureSpec(m, 1073741824);
      } else {
        m = View.MeasureSpec.makeMeasureSpec(0, 0);
      }
      localView.measure(paramInt1, m);
      localView.forceLayout();
      n = paramInt3;
      m = paramInt2;
      if (j > 0)
      {
        n = paramInt3 + paramInt2;
        m = paramInt2;
      }
      paramInt3 = n + localView.getMeasuredHeight();
      if (paramInt3 >= paramInt4)
      {
        if ((paramInt5 < 0) || (j <= paramInt5) || (i <= 0) || (paramInt3 == paramInt4)) {
          break label326;
        }
        return i;
      }
      paramInt2 = i;
      if (paramInt5 >= 0)
      {
        paramInt2 = i;
        if (j >= paramInt5) {
          paramInt2 = paramInt3;
        }
      }
      j += 1;
      n = k;
      i = paramInt2;
      paramInt2 = m;
    }
    return paramInt3;
    label326:
    return paramInt4;
  }
  
  protected void onDetachedFromWindow()
  {
    b = null;
    super.onDetachedFromWindow();
  }
  
  public boolean onForwardedEvent(MotionEvent paramMotionEvent, int paramInt)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i != 1) {
      if (i != 2) {
        if (i == 3) {}
      }
    }
    boolean bool;
    for (;;)
    {
      paramInt = 0;
      bool = true;
      break;
      label45:
      do
      {
        paramInt = 0;
        bool = false;
        break;
        bool = true;
        break label45;
        bool = false;
        j = paramMotionEvent.findPointerIndex(paramInt);
      } while (j < 0);
      paramInt = (int)paramMotionEvent.getX(j);
      int j = (int)paramMotionEvent.getY(j);
      int k = pointToPosition(paramInt, j);
      if (k == -1)
      {
        paramInt = 1;
        break;
      }
      View localView = getChildAt(k - getFirstVisiblePosition());
      setPressedItem(localView, k, paramInt, j);
      if (i == 1) {
        clickPressedItem(localView, k);
      }
    }
    if ((!bool) || (paramInt != 0)) {
      clearPressedItem();
    }
    if (bool)
    {
      if (mScrollHelper == null) {
        mScrollHelper = new ListViewAutoScrollHelper(this);
      }
      mScrollHelper.setEnabled(true);
      mScrollHelper.onTouch(this, paramMotionEvent);
      return bool;
    }
    paramMotionEvent = mScrollHelper;
    if (paramMotionEvent != null) {
      paramMotionEvent.setEnabled(false);
    }
    return bool;
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    if (Build.VERSION.SDK_INT < 26) {
      return super.onHoverEvent(paramMotionEvent);
    }
    int i = paramMotionEvent.getActionMasked();
    if ((i == 10) && (b == null))
    {
      b = new SnackBar(this);
      b.clear();
    }
    boolean bool = super.onHoverEvent(paramMotionEvent);
    if ((i != 9) && (i != 7))
    {
      setSelection(-1);
      return bool;
    }
    i = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    if ((i != -1) && (i != getSelectedItemPosition()))
    {
      paramMotionEvent = getChildAt(i - getFirstVisiblePosition());
      if (paramMotionEvent.isEnabled()) {
        setSelectionFromTop(i, paramMotionEvent.getTop() - getTop());
      }
      updateSelectorStateCompat();
    }
    return bool;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0) {
      mMotionPosition = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    }
    SnackBar localSnackBar = b;
    if (localSnackBar != null) {
      localSnackBar.onTouchEvent();
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  void setListSelectionHidden(boolean paramBoolean)
  {
    mListSelectionHidden = paramBoolean;
  }
  
  public void setSelector(Drawable paramDrawable)
  {
    if (paramDrawable != null) {
      localObject = new GateKeeperDrawable(paramDrawable);
    } else {
      localObject = null;
    }
    mSelector = ((GateKeeperDrawable)localObject);
    super.setSelector(mSelector);
    Object localObject = new Rect();
    if (paramDrawable != null) {
      paramDrawable.getPadding((Rect)localObject);
    }
    mSelectionLeftPadding = left;
    mSelectionTopPadding = top;
    mSelectionRightPadding = right;
    mSelectionBottomPadding = bottom;
  }
  
  class GateKeeperDrawable
    extends DrawableWrapper
  {
    private boolean mEnabled = true;
    
    GateKeeperDrawable()
    {
      super();
    }
    
    public void draw(Canvas paramCanvas)
    {
      if (mEnabled) {
        super.draw(paramCanvas);
      }
    }
    
    void setEnabled(boolean paramBoolean)
    {
      mEnabled = paramBoolean;
    }
    
    public void setHotspot(float paramFloat1, float paramFloat2)
    {
      if (mEnabled) {
        super.setHotspot(paramFloat1, paramFloat2);
      }
    }
    
    public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (mEnabled) {
        super.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
      }
    }
    
    public boolean setState(int[] paramArrayOfInt)
    {
      if (mEnabled) {
        return super.setState(paramArrayOfInt);
      }
      return false;
    }
    
    public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
    {
      if (mEnabled) {
        return super.setVisible(paramBoolean1, paramBoolean2);
      }
      return false;
    }
  }
}
