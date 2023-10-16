package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.l.a;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView
  extends LinearLayoutCompat
  implements MenuBuilder.ItemInvoker, MenuView
{
  private l.a mActionMenuPresenterCallback;
  private boolean mFormatItems;
  private int mFormatItemsWidth;
  private int mGeneratedItemPadding;
  private MenuBuilder mMenu;
  MenuBuilder.Callback mMenuBuilderCallback;
  e mMenuItemClickListener;
  private int mMinCellSize;
  private Context mPopupContext;
  private int mPopupTheme;
  private ActionMenuPresenter mPresenter;
  private boolean mReserveOverflow;
  
  public ActionMenuView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionMenuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setBaselineAligned(false);
    float f = getResourcesgetDisplayMetricsdensity;
    mMinCellSize = ((int)(56.0F * f));
    mGeneratedItemPadding = ((int)(f * 4.0F));
    mPopupContext = paramContext;
    mPopupTheme = 0;
  }
  
  static int measureChildForCells(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    c localC = (c)paramView.getLayoutParams();
    int j = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt3) - paramInt4, View.MeasureSpec.getMode(paramInt3));
    ActionMenuItemView localActionMenuItemView;
    if ((paramView instanceof ActionMenuItemView)) {
      localActionMenuItemView = (ActionMenuItemView)paramView;
    } else {
      localActionMenuItemView = null;
    }
    boolean bool = true;
    if ((localActionMenuItemView != null) && (localActionMenuItemView.hasText())) {
      paramInt3 = 1;
    } else {
      paramInt3 = 0;
    }
    paramInt4 = 2;
    if ((paramInt2 > 0) && ((paramInt3 == 0) || (paramInt2 >= 2)))
    {
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt2 * paramInt1, Integer.MIN_VALUE), j);
      int k = paramView.getMeasuredWidth();
      int i = k / paramInt1;
      paramInt2 = i;
      if (k % paramInt1 != 0) {
        paramInt2 = i + 1;
      }
      if ((paramInt3 != 0) && (paramInt2 < 2)) {
        paramInt2 = paramInt4;
      }
    }
    else
    {
      paramInt2 = 0;
    }
    if ((isOverflowButton) || (paramInt3 == 0)) {
      bool = false;
    }
    expandable = bool;
    cellsUsed = paramInt2;
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1 * paramInt2, 1073741824), j);
    return paramInt2;
  }
  
  private void onMeasureExactFormat(int paramInt1, int paramInt2)
  {
    int i9 = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int i7 = View.MeasureSpec.getSize(paramInt2);
    int i = getPaddingLeft();
    int j = getPaddingRight();
    int i8 = getPaddingTop() + getPaddingBottom();
    int i10 = ViewGroup.getChildMeasureSpec(paramInt2, i8, -2);
    int i11 = paramInt1 - (i + j);
    paramInt2 = mMinCellSize;
    paramInt1 = i11 / paramInt2;
    if (paramInt1 == 0)
    {
      setMeasuredDimension(i11, 0);
      return;
    }
    int i12 = paramInt2 + i11 % paramInt2 / paramInt1;
    int i13 = getChildCount();
    int i1 = 0;
    i = 0;
    int k = 0;
    int n = 0;
    int m = 0;
    j = 0;
    Object localObject;
    long l2;
    int i4;
    c localC;
    int i5;
    int i6;
    int i3;
    for (long l1 = 0L; i1 < i13; l1 = l2)
    {
      localObject = getChildAt(i1);
      if (((View)localObject).getVisibility() == 8)
      {
        i2 = k;
        l2 = l1;
      }
      else
      {
        boolean bool = localObject instanceof ActionMenuItemView;
        i4 = n + 1;
        if (bool)
        {
          paramInt2 = mGeneratedItemPadding;
          ((View)localObject).setPadding(paramInt2, 0, paramInt2, 0);
        }
        localC = (c)((View)localObject).getLayoutParams();
        expanded = false;
        extraPixels = 0;
        cellsUsed = 0;
        expandable = false;
        leftMargin = 0;
        rightMargin = 0;
        if ((bool) && (((ActionMenuItemView)localObject).hasText())) {
          bool = true;
        } else {
          bool = false;
        }
        preventEdgeOffset = bool;
        if (isOverflowButton) {
          paramInt2 = 1;
        } else {
          paramInt2 = paramInt1;
        }
        int i14 = measureChildForCells((View)localObject, i12, paramInt2, i10, i8);
        i5 = Math.max(m, i14);
        paramInt2 = j;
        if (expandable) {
          paramInt2 = j + 1;
        }
        if (isOverflowButton) {
          k = 1;
        }
        i6 = paramInt1 - i14;
        i3 = Math.max(i, ((View)localObject).getMeasuredHeight());
        i = i3;
        paramInt1 = i6;
        i2 = k;
        n = i4;
        m = i5;
        j = paramInt2;
        l2 = l1;
        if (i14 == 1)
        {
          l2 = l1 | 1 << i1;
          i = i3;
          paramInt1 = i6;
          i2 = k;
          n = i4;
          m = i5;
          j = paramInt2;
        }
      }
      i1 += 1;
      k = i2;
    }
    if ((k != 0) && (n == 2)) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    paramInt2 = 0;
    int i2 = paramInt1;
    while ((j > 0) && (i2 > 0))
    {
      i3 = Integer.MAX_VALUE;
      i4 = 0;
      i5 = 0;
      for (long l3 = 0L; i4 < i13; l3 = l2)
      {
        localObject = (c)getChildAt(i4).getLayoutParams();
        if (!expandable)
        {
          i6 = i3;
          paramInt1 = i5;
          l2 = l3;
        }
        else
        {
          i8 = cellsUsed;
          if (i8 < i3)
          {
            i6 = i8;
            l2 = 1L << i4;
            paramInt1 = 1;
          }
          else
          {
            i6 = i3;
            paramInt1 = i5;
            l2 = l3;
            if (i8 == i3)
            {
              l2 = l3 | 1L << i4;
              paramInt1 = i5 + 1;
              i6 = i3;
            }
          }
        }
        i4 += 1;
        i3 = i6;
        i5 = paramInt1;
      }
      l1 |= l3;
      if (i5 > i2) {
        break;
      }
      paramInt1 = 0;
      while (paramInt1 < i13)
      {
        localObject = getChildAt(paramInt1);
        localC = (c)((View)localObject).getLayoutParams();
        long l4 = 1 << paramInt1;
        if ((l3 & l4) == 0L)
        {
          l2 = l1;
          if (cellsUsed == i3 + 1) {
            l2 = l1 | l4;
          }
          l1 = l2;
        }
        else
        {
          if ((i1 != 0) && (preventEdgeOffset) && (i2 == 1))
          {
            paramInt2 = mGeneratedItemPadding;
            ((View)localObject).setPadding(paramInt2 + i12, 0, paramInt2, 0);
          }
          cellsUsed += 1;
          expanded = true;
          i2 -= 1;
        }
        paramInt1 += 1;
      }
      paramInt2 = 1;
    }
    if ((k == 0) && (n == 1)) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    }
    if ((i2 > 0) && (l1 != 0L) && ((i2 < n - 1) || (paramInt1 != 0) || (m > 1)))
    {
      float f2 = Long.bitCount(l1);
      if (paramInt1 == 0)
      {
        float f1;
        if ((l1 & 1L) != 0L)
        {
          f1 = f2;
          if (!getChildAt0getLayoutParamspreventEdgeOffset) {
            f1 = f2 - 0.5F;
          }
        }
        else
        {
          f1 = f2;
        }
        paramInt1 = i13 - 1;
        f2 = f1;
        if ((l1 & 1 << paramInt1) != 0L)
        {
          f2 = f1;
          if (!getChildAtgetLayoutParamspreventEdgeOffset) {
            f2 = f1 - 0.5F;
          }
        }
      }
      if (f2 > 0.0F) {
        paramInt1 = (int)(i2 * i12 / f2);
      } else {
        paramInt1 = 0;
      }
      j = 0;
      for (;;)
      {
        k = paramInt2;
        if (j >= i13) {
          break;
        }
        if ((l1 & 1 << j) == 0L)
        {
          k = paramInt2;
        }
        else
        {
          localObject = getChildAt(j);
          localC = (c)((View)localObject).getLayoutParams();
          if ((localObject instanceof ActionMenuItemView))
          {
            extraPixels = paramInt1;
            expanded = true;
            if ((j == 0) && (!preventEdgeOffset)) {
              leftMargin = (-paramInt1 / 2);
            }
          }
          else
          {
            if (!isOverflowButton) {
              break label1080;
            }
            extraPixels = paramInt1;
            expanded = true;
            rightMargin = (-paramInt1 / 2);
          }
          k = 1;
          break label1116;
          label1080:
          if (j != 0) {
            leftMargin = (paramInt1 / 2);
          }
          k = paramInt2;
          if (j != i13 - 1)
          {
            rightMargin = (paramInt1 / 2);
            k = paramInt2;
          }
        }
        label1116:
        j += 1;
        paramInt2 = k;
      }
    }
    k = paramInt2;
    paramInt1 = 0;
    if (k != 0) {
      while (paramInt1 < i13)
      {
        localObject = getChildAt(paramInt1);
        localC = (c)((View)localObject).getLayoutParams();
        if (expanded) {
          ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(cellsUsed * i12 + extraPixels, 1073741824), i10);
        }
        paramInt1 += 1;
      }
    }
    if (i9 != 1073741824) {
      paramInt1 = i;
    } else {
      paramInt1 = i7;
    }
    setMeasuredDimension(i11, paramInt1);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return (paramLayoutParams != null) && ((paramLayoutParams instanceof c));
  }
  
  public void dismissPopupMenus()
  {
    ActionMenuPresenter localActionMenuPresenter = mPresenter;
    if (localActionMenuPresenter != null) {
      localActionMenuPresenter.dismissPopupMenus();
    }
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    return false;
  }
  
  protected c generateDefaultLayoutParams()
  {
    c localC = new c(-2, -2);
    gravity = 16;
    return localC;
  }
  
  public c generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new c(getContext(), paramAttributeSet);
  }
  
  protected c generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    Object localObject;
    if (paramLayoutParams != null)
    {
      if ((paramLayoutParams instanceof c)) {
        paramLayoutParams = new c((c)paramLayoutParams);
      } else {
        paramLayoutParams = new c(paramLayoutParams);
      }
      localObject = paramLayoutParams;
      if (gravity <= 0)
      {
        gravity = 16;
        return paramLayoutParams;
      }
    }
    else
    {
      localObject = generateDefaultLayoutParams();
    }
    return localObject;
  }
  
  public c generateOverflowButtonLayoutParams()
  {
    c localC = generateDefaultLayoutParams();
    isOverflowButton = true;
    return localC;
  }
  
  public Menu getMenu()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a5 = a4\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  public Drawable getOverflowIcon()
  {
    getMenu();
    return mPresenter.getOverflowIcon();
  }
  
  public int getPopupTheme()
  {
    return mPopupTheme;
  }
  
  public int getWindowAnimations()
  {
    return 0;
  }
  
  protected boolean hasSupportDividerBeforeChildAt(int paramInt)
  {
    boolean bool2 = false;
    if (paramInt == 0) {
      return false;
    }
    View localView1 = getChildAt(paramInt - 1);
    View localView2 = getChildAt(paramInt);
    boolean bool1 = bool2;
    if (paramInt < getChildCount())
    {
      bool1 = bool2;
      if ((localView1 instanceof a)) {
        bool1 = false | ((a)localView1).needsDividerAfter();
      }
    }
    bool2 = bool1;
    if (paramInt > 0)
    {
      bool2 = bool1;
      if ((localView2 instanceof a)) {
        bool2 = bool1 | ((a)localView2).needsDividerBefore();
      }
    }
    return bool2;
  }
  
  public boolean hideOverflowMenu()
  {
    ActionMenuPresenter localActionMenuPresenter = mPresenter;
    return (localActionMenuPresenter != null) && (localActionMenuPresenter.hideOverflowMenu());
  }
  
  public void initialize(MenuBuilder paramMenuBuilder)
  {
    mMenu = paramMenuBuilder;
  }
  
  public boolean invokeItem(MenuItemImpl paramMenuItemImpl)
  {
    return mMenu.performItemAction(paramMenuItemImpl, 0);
  }
  
  public boolean isOverflowMenuShowPending()
  {
    ActionMenuPresenter localActionMenuPresenter = mPresenter;
    return (localActionMenuPresenter != null) && (localActionMenuPresenter.isOverflowMenuShowPending());
  }
  
  public boolean isOverflowMenuShowing()
  {
    ActionMenuPresenter localActionMenuPresenter = mPresenter;
    return (localActionMenuPresenter != null) && (localActionMenuPresenter.isOverflowMenuShowing());
  }
  
  public boolean isOverflowReserved()
  {
    return mReserveOverflow;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    paramConfiguration = mPresenter;
    if (paramConfiguration != null)
    {
      paramConfiguration.updateMenuView(false);
      if (mPresenter.isOverflowMenuShowing())
      {
        mPresenter.hideOverflowMenu();
        mPresenter.showOverflowMenu();
      }
    }
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    dismissPopupMenus();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!mFormatItems)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    int m = getChildCount();
    int k = (paramInt4 - paramInt2) / 2;
    int n = getDividerWidth();
    int i1 = paramInt3 - paramInt1;
    paramInt1 = getPaddingRight();
    paramInt2 = getPaddingLeft();
    paramBoolean = ViewUtils.isLayoutRtl(this);
    paramInt1 = i1 - paramInt1 - paramInt2;
    paramInt2 = 0;
    paramInt4 = 0;
    paramInt3 = 0;
    View localView;
    c localC;
    int j;
    while (paramInt2 < m)
    {
      localView = getChildAt(paramInt2);
      if (localView.getVisibility() != 8)
      {
        localC = (c)localView.getLayoutParams();
        if (isOverflowButton)
        {
          i = localView.getMeasuredWidth();
          paramInt4 = i;
          if (hasSupportDividerBeforeChildAt(paramInt2)) {
            paramInt4 = i + n;
          }
          int i2 = localView.getMeasuredHeight();
          if (paramBoolean)
          {
            i = getPaddingLeft() + leftMargin;
            j = i + paramInt4;
          }
          else
          {
            j = getWidth() - getPaddingRight() - rightMargin;
            i = j - paramInt4;
          }
          int i3 = k - i2 / 2;
          localView.layout(i, i3, j, i2 + i3);
          paramInt1 -= paramInt4;
          paramInt4 = 1;
        }
        else
        {
          paramInt1 -= localView.getMeasuredWidth() + leftMargin + rightMargin;
          hasSupportDividerBeforeChildAt(paramInt2);
          paramInt3 += 1;
        }
      }
      paramInt2 += 1;
    }
    if ((m == 1) && (paramInt4 == 0))
    {
      localView = getChildAt(0);
      paramInt1 = localView.getMeasuredWidth();
      paramInt2 = localView.getMeasuredHeight();
      paramInt3 = i1 / 2 - paramInt1 / 2;
      paramInt4 = k - paramInt2 / 2;
      localView.layout(paramInt3, paramInt4, paramInt1 + paramInt3, paramInt2 + paramInt4);
      return;
    }
    paramInt2 = paramInt3 - (paramInt4 ^ 0x1);
    if (paramInt2 > 0) {
      paramInt1 /= paramInt2;
    } else {
      paramInt1 = 0;
    }
    paramInt4 = 0;
    paramInt3 = 0;
    int i = Math.max(0, paramInt1);
    if (paramBoolean)
    {
      paramInt2 = getWidth() - getPaddingRight();
      paramInt1 = paramInt3;
      while (paramInt1 < m)
      {
        localView = getChildAt(paramInt1);
        localC = (c)localView.getLayoutParams();
        paramInt3 = paramInt2;
        if (localView.getVisibility() != 8) {
          if (isOverflowButton)
          {
            paramInt3 = paramInt2;
          }
          else
          {
            paramInt2 -= rightMargin;
            paramInt3 = localView.getMeasuredWidth();
            paramInt4 = localView.getMeasuredHeight();
            j = k - paramInt4 / 2;
            localView.layout(paramInt2 - paramInt3, j, paramInt2, paramInt4 + j);
            paramInt3 = paramInt2 - (paramInt3 + leftMargin + i);
          }
        }
        paramInt1 += 1;
        paramInt2 = paramInt3;
      }
    }
    paramInt2 = getPaddingLeft();
    paramInt1 = paramInt4;
    while (paramInt1 < m)
    {
      localView = getChildAt(paramInt1);
      localC = (c)localView.getLayoutParams();
      paramInt3 = paramInt2;
      if (localView.getVisibility() != 8) {
        if (isOverflowButton)
        {
          paramInt3 = paramInt2;
        }
        else
        {
          paramInt2 += leftMargin;
          paramInt3 = localView.getMeasuredWidth();
          paramInt4 = localView.getMeasuredHeight();
          j = k - paramInt4 / 2;
          localView.layout(paramInt2, j, paramInt2 + paramInt3, paramInt4 + j);
          paramInt3 = paramInt2 + (paramInt3 + rightMargin + i);
        }
      }
      paramInt1 += 1;
      paramInt2 = paramInt3;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    boolean bool2 = mFormatItems;
    boolean bool1;
    if (View.MeasureSpec.getMode(paramInt1) == 1073741824) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    mFormatItems = bool1;
    if (bool2 != mFormatItems) {
      mFormatItemsWidth = 0;
    }
    int i = View.MeasureSpec.getSize(paramInt1);
    Object localObject;
    if (mFormatItems)
    {
      localObject = mMenu;
      if ((localObject != null) && (i != mFormatItemsWidth))
      {
        mFormatItemsWidth = i;
        ((MenuBuilder)localObject).onItemsChanged(true);
      }
    }
    int j = getChildCount();
    if ((mFormatItems) && (j > 0))
    {
      onMeasureExactFormat(paramInt1, paramInt2);
      return;
    }
    i = 0;
    while (i < j)
    {
      localObject = (c)getChildAt(i).getLayoutParams();
      rightMargin = 0;
      leftMargin = 0;
      i += 1;
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public MenuBuilder peekMenu()
  {
    return mMenu;
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    mPresenter.setExpandedActionViewsExclusive(paramBoolean);
  }
  
  public void setMenuCallbacks(l.a paramA, MenuBuilder.Callback paramCallback)
  {
    mActionMenuPresenterCallback = paramA;
    mMenuBuilderCallback = paramCallback;
  }
  
  public void setOnMenuItemClickListener(e paramE)
  {
    mMenuItemClickListener = paramE;
  }
  
  public void setOverflowIcon(Drawable paramDrawable)
  {
    getMenu();
    mPresenter.setOverflowIcon(paramDrawable);
  }
  
  public void setOverflowReserved(boolean paramBoolean)
  {
    mReserveOverflow = paramBoolean;
  }
  
  public void setPopupTheme(int paramInt)
  {
    if (mPopupTheme != paramInt)
    {
      mPopupTheme = paramInt;
      if (paramInt == 0)
      {
        mPopupContext = getContext();
        return;
      }
      mPopupContext = new ContextThemeWrapper(getContext(), paramInt);
    }
  }
  
  public void setPresenter(ActionMenuPresenter paramActionMenuPresenter)
  {
    mPresenter = paramActionMenuPresenter;
    mPresenter.setMenuView(this);
  }
  
  public boolean showOverflowMenu()
  {
    ActionMenuPresenter localActionMenuPresenter = mPresenter;
    return (localActionMenuPresenter != null) && (localActionMenuPresenter.showOverflowMenu());
  }
  
  public static abstract interface a
  {
    public abstract boolean needsDividerAfter();
    
    public abstract boolean needsDividerBefore();
  }
  
  private static class b
    implements l.a
  {
    b() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {}
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      return false;
    }
  }
  
  public static class c
    extends LinearLayoutCompat.LayoutParams
  {
    @ViewDebug.ExportedProperty
    public int cellsUsed;
    @ViewDebug.ExportedProperty
    public boolean expandable;
    boolean expanded;
    @ViewDebug.ExportedProperty
    public int extraPixels;
    @ViewDebug.ExportedProperty
    public boolean isOverflowButton;
    @ViewDebug.ExportedProperty
    public boolean preventEdgeOffset;
    
    public c(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      isOverflowButton = false;
    }
    
    public c(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public c(c paramC)
    {
      super();
      isOverflowButton = isOverflowButton;
    }
    
    public c(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }
  
  private class d
    implements MenuBuilder.Callback
  {
    d() {}
    
    public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
    {
      paramMenuBuilder = mMenuItemClickListener;
      return (paramMenuBuilder != null) && (paramMenuBuilder.onMenuItemClick(paramMenuItem));
    }
    
    public void onMenuModeChange(MenuBuilder paramMenuBuilder)
    {
      MenuBuilder.Callback localCallback = mMenuBuilderCallback;
      if (localCallback != null) {
        localCallback.onMenuModeChange(paramMenuBuilder);
      }
    }
  }
  
  public static abstract interface e
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
}
