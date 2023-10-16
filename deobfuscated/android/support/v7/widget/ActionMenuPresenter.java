package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ActionMenuItemView.b;
import android.support.v7.view.menu.ListPopupWindow;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.b;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.l.a;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.org.android.view.ActionProvider;
import com.org.android.view.ActionProvider.SubUiVisibilityListener;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.layout;
import com.org.v4.view.ActionBarPolicy;
import java.util.ArrayList;

class ActionMenuPresenter
  extends b
  implements ActionProvider.SubUiVisibilityListener
{
  XYPlot a;
  int e;
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  private int mActionItemWidthLimit;
  private boolean mExpandedActionViewsExclusive;
  private int mMaxItems;
  private boolean mMaxItemsSet;
  private int mMinCellSize;
  OverflowMenuButton mOverflowButton;
  i mOverflowPopup;
  private Drawable mPendingOverflowIcon;
  private boolean mPendingOverflowIconSet;
  private ActionMenuPopupCallback mPopupCallback;
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
  OpenOverflowRunnable mPostedOpenRunnable;
  private boolean mReserveOverflow;
  private boolean mReserveOverflowSet;
  private View mScrapActionButtonView;
  private boolean mStrictWidthLimit;
  private int mWidthLimit;
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext)
  {
    super(paramContext, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
  }
  
  private View findViewForItem(MenuItem paramMenuItem)
  {
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    if (localViewGroup == null) {
      return null;
    }
    int j = localViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = localViewGroup.getChildAt(i);
      if (((localView instanceof MenuView.ItemView)) && (((MenuView.ItemView)localView).getItemData() == paramMenuItem)) {
        return localView;
      }
      i += 1;
    }
    return null;
  }
  
  public void a(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    dismissPopupMenus();
    super.a(paramMenuBuilder, paramBoolean);
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    boolean bool1 = paramSubMenuBuilder.hasVisibleItems();
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    for (Object localObject = paramSubMenuBuilder; ((SubMenuBuilder)localObject).getParentMenu() != mMenu; localObject = (SubMenuBuilder)((SubMenuBuilder)localObject).getParentMenu()) {}
    localObject = findViewForItem(((SubMenuBuilder)localObject).getItem());
    if (localObject == null) {
      return false;
    }
    e = paramSubMenuBuilder.getItem().getItemId();
    int j = paramSubMenuBuilder.size();
    int i = 0;
    for (;;)
    {
      bool1 = bool2;
      if (i >= j) {
        break;
      }
      MenuItem localMenuItem = paramSubMenuBuilder.getItem(i);
      if ((localMenuItem.isVisible()) && (localMenuItem.getIcon() != null))
      {
        bool1 = true;
        break;
      }
      i += 1;
    }
    a = new XYPlot(this, mContext, paramSubMenuBuilder, (View)localObject);
    a.a(bool1);
    a.setTitle();
    super.a(paramSubMenuBuilder);
    return true;
  }
  
  public void bindItemView(android.support.v7.view.menu.MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView)
  {
    paramItemView.initialize(paramMenuItemImpl, 0);
    paramMenuItemImpl = (ActionMenuView)mMenuView;
    paramItemView = (ActionMenuItemView)paramItemView;
    paramItemView.setItemInvoker(paramMenuItemImpl);
    if (mPopupCallback == null) {
      mPopupCallback = new ActionMenuPopupCallback();
    }
    paramItemView.setPopupCallback(mPopupCallback);
  }
  
  public boolean dismissPopupMenus()
  {
    return hideOverflowMenu() | hideSubMenus();
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramViewGroup.getChildAt(paramInt) == mOverflowButton) {
      return false;
    }
    return super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems()
  {
    Object localObject1 = mMenu;
    Object localObject2;
    int i3;
    if (localObject1 != null)
    {
      localObject2 = ((MenuBuilder)localObject1).getVisibleItems();
      localObject1 = localObject2;
      i3 = ((ArrayList)localObject2).size();
    }
    else
    {
      localObject1 = null;
      i3 = 0;
    }
    int i = mMaxItems;
    int i2 = mActionItemWidthLimit;
    int i6 = View.MeasureSpec.makeMeasureSpec(0, 0);
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    int j = 0;
    int n = 0;
    int k = 0;
    int m = 0;
    int i1;
    while (j < i3)
    {
      localObject2 = (android.support.v7.view.menu.MenuItemImpl)((ArrayList)localObject1).get(j);
      if (((android.support.v7.view.menu.MenuItemImpl)localObject2).requiresActionButton()) {
        k += 1;
      } else if (((android.support.v7.view.menu.MenuItemImpl)localObject2).requestsActionButton()) {
        m += 1;
      } else {
        n = 1;
      }
      i1 = i;
      if (mExpandedActionViewsExclusive)
      {
        i1 = i;
        if (((android.support.v7.view.menu.MenuItemImpl)localObject2).isActionViewExpanded()) {
          i1 = 0;
        }
      }
      j += 1;
      i = i1;
    }
    j = i;
    if (mReserveOverflow) {
      if (n == 0)
      {
        j = i;
        if (m + k <= i) {}
      }
      else
      {
        j = i - 1;
      }
    }
    i = j - k;
    SparseBooleanArray localSparseBooleanArray = mActionButtonGroups;
    localSparseBooleanArray.clear();
    int i4;
    if (mStrictWidthLimit)
    {
      j = mMinCellSize;
      k = i2 / j;
      i4 = j + i2 % j / k;
    }
    else
    {
      i4 = 0;
      k = 0;
    }
    int i5 = 0;
    j = 0;
    for (;;)
    {
      localObject2 = this;
      if (i5 >= i3) {
        break;
      }
      android.support.v7.view.menu.MenuItemImpl localMenuItemImpl = (android.support.v7.view.menu.MenuItemImpl)((ArrayList)localObject1).get(i5);
      View localView;
      if (localMenuItemImpl.requiresActionButton())
      {
        localView = ((ActionMenuPresenter)localObject2).getItemView(localMenuItemImpl, mScrapActionButtonView, localViewGroup);
        if (mScrapActionButtonView == null) {
          mScrapActionButtonView = localView;
        }
        if (mStrictWidthLimit) {
          k -= ActionMenuView.measureChildForCells(localView, i4, k, i6, 0);
        } else {
          localView.measure(i6, i6);
        }
        n = localView.getMeasuredWidth();
        m = n;
        n = i2 - n;
        if (j == 0) {
          j = m;
        }
        m = localMenuItemImpl.getGroupId();
        if (m != 0) {
          localSparseBooleanArray.put(m, true);
        }
        localMenuItemImpl.setIsActionButton(true);
        i1 = j;
        m = n;
      }
      for (;;)
      {
        j = i1;
        break label773;
        if (!localMenuItemImpl.requestsActionButton()) {
          break;
        }
        int i7 = localMenuItemImpl.getGroupId();
        boolean bool = localSparseBooleanArray.get(i7);
        int i8;
        if (((i > 0) || (bool)) && (i2 > 0) && ((!mStrictWidthLimit) || (k > 0))) {
          i8 = 1;
        } else {
          i8 = 0;
        }
        m = i2;
        n = k;
        int i9 = i8;
        i1 = j;
        if (i8 != 0)
        {
          localView = ((ActionMenuPresenter)localObject2).getItemView(localMenuItemImpl, mScrapActionButtonView, localViewGroup);
          if (mScrapActionButtonView == null) {
            mScrapActionButtonView = localView;
          }
          if (mStrictWidthLimit)
          {
            n = ActionMenuView.measureChildForCells(localView, i4, k, i6, 0);
            m = k - n;
            k = m;
            if (n == 0)
            {
              i8 = 0;
              k = m;
            }
          }
          else
          {
            localView.measure(i6, i6);
          }
          n = localView.getMeasuredWidth();
          m = i2 - n;
          i1 = j;
          if (j == 0) {
            i1 = n;
          }
          if (mStrictWidthLimit ? m >= 0 : m + i1 > 0) {
            j = 1;
          } else {
            j = 0;
          }
          i9 = i8 & j;
          n = k;
        }
        if ((i9 != 0) && (i7 != 0))
        {
          localSparseBooleanArray.put(i7, true);
          j = i;
        }
        else
        {
          j = i;
          if (bool)
          {
            localSparseBooleanArray.put(i7, false);
            k = 0;
            for (;;)
            {
              j = i;
              if (k >= i5) {
                break;
              }
              localObject2 = (android.support.v7.view.menu.MenuItemImpl)((ArrayList)localObject1).get(k);
              j = i;
              if (((android.support.v7.view.menu.MenuItemImpl)localObject2).getGroupId() == i7)
              {
                j = i;
                if (((android.support.v7.view.menu.MenuItemImpl)localObject2).isActionButton()) {
                  j = i + 1;
                }
                ((android.support.v7.view.menu.MenuItemImpl)localObject2).setIsActionButton(false);
              }
              k += 1;
              i = j;
            }
          }
        }
        i = j;
        if (i9 != 0) {
          i = j - 1;
        }
        localMenuItemImpl.setIsActionButton(i9);
        k = n;
      }
      localMenuItemImpl.setIsActionButton(false);
      m = i2;
      label773:
      i5 += 1;
      i2 = m;
    }
    return true;
  }
  
  public View getItemView(android.support.v7.view.menu.MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup)
  {
    View localView2 = paramMenuItemImpl.getActionView();
    View localView1 = localView2;
    if ((localView2 == null) || (paramMenuItemImpl.hasCollapsibleActionView())) {
      localView1 = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup);
    }
    int i;
    if (paramMenuItemImpl.isActionViewExpanded()) {
      i = 8;
    } else {
      i = 0;
    }
    localView1.setVisibility(i);
    paramMenuItemImpl = (ActionMenuView)paramViewGroup;
    paramView = localView1.getLayoutParams();
    if (!paramMenuItemImpl.checkLayoutParams(paramView)) {
      localView1.setLayoutParams(paramMenuItemImpl.generateLayoutParams(paramView));
    }
    return localView1;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    MenuView localMenuView = mMenuView;
    paramViewGroup = super.getMenuView(paramViewGroup);
    if (localMenuView != paramViewGroup) {
      ((ActionMenuView)paramViewGroup).setPresenter(this);
    }
    return paramViewGroup;
  }
  
  public Drawable getOverflowIcon()
  {
    OverflowMenuButton localOverflowMenuButton = mOverflowButton;
    if (localOverflowMenuButton != null) {
      return localOverflowMenuButton.getDrawable();
    }
    if (mPendingOverflowIconSet) {
      return mPendingOverflowIcon;
    }
    return null;
  }
  
  public boolean hideOverflowMenu()
  {
    Object localObject = mPostedOpenRunnable;
    if (localObject != null)
    {
      MenuView localMenuView = mMenuView;
      if (localMenuView != null)
      {
        ((View)localMenuView).removeCallbacks((Runnable)localObject);
        mPostedOpenRunnable = null;
        return true;
      }
    }
    localObject = mOverflowPopup;
    if (localObject != null)
    {
      ((h)localObject).dismiss();
      return true;
    }
    return false;
  }
  
  public boolean hideSubMenus()
  {
    XYPlot localXYPlot = a;
    if (localXYPlot != null)
    {
      localXYPlot.dismiss();
      return true;
    }
    return false;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    super.initForMenu(paramContext, paramMenuBuilder);
    paramMenuBuilder = paramContext.getResources();
    paramContext = ActionBarPolicy.get(paramContext);
    if (!mReserveOverflowSet) {
      mReserveOverflow = paramContext.showsOverflowMenuButton();
    }
    if (!mWidthLimitSet) {
      mWidthLimit = paramContext.getEmbeddedMenuWidthLimit();
    }
    if (!mMaxItemsSet) {
      mMaxItems = paramContext.init();
    }
    int i = mWidthLimit;
    if (mReserveOverflow)
    {
      if (mOverflowButton == null)
      {
        mOverflowButton = new OverflowMenuButton(mSystemContext);
        if (mPendingOverflowIconSet)
        {
          mOverflowButton.setImageDrawable(mPendingOverflowIcon);
          mPendingOverflowIcon = null;
          mPendingOverflowIconSet = false;
        }
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        mOverflowButton.measure(j, j);
      }
      i -= mOverflowButton.getMeasuredWidth();
    }
    else
    {
      mOverflowButton = null;
    }
    mActionItemWidthLimit = i;
    mMinCellSize = ((int)(getDisplayMetricsdensity * 56.0F));
    mScrapActionButtonView = null;
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (mPostedOpenRunnable != null) || (isOverflowMenuShowing());
  }
  
  public boolean isOverflowMenuShowing()
  {
    i localI = mOverflowPopup;
    return (localI != null) && (localI.isShowing());
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!mMaxItemsSet) {
      mMaxItems = ActionBarPolicy.get(mContext).init();
    }
    paramConfiguration = mMenu;
    if (paramConfiguration != null) {
      paramConfiguration.onItemsChanged(true);
    }
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setMenuView(ActionMenuView paramActionMenuView)
  {
    mMenuView = paramActionMenuView;
    paramActionMenuView.initialize(mMenu);
  }
  
  public void setOverflowIcon(Drawable paramDrawable)
  {
    OverflowMenuButton localOverflowMenuButton = mOverflowButton;
    if (localOverflowMenuButton != null)
    {
      localOverflowMenuButton.setImageDrawable(paramDrawable);
      return;
    }
    mPendingOverflowIconSet = true;
    mPendingOverflowIcon = paramDrawable;
  }
  
  public void setReserveOverflow(boolean paramBoolean)
  {
    mReserveOverflow = paramBoolean;
    mReserveOverflowSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, android.support.v7.view.menu.MenuItemImpl paramMenuItemImpl)
  {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu()
  {
    if ((mReserveOverflow) && (!isOverflowMenuShowing()))
    {
      MenuBuilder localMenuBuilder = mMenu;
      if ((localMenuBuilder != null) && (mMenuView != null) && (mPostedOpenRunnable == null) && (!localMenuBuilder.getNonActionItems().isEmpty()))
      {
        mPostedOpenRunnable = new OpenOverflowRunnable(new i(this, mContext, mMenu, mOverflowButton, true));
        ((View)mMenuView).post(mPostedOpenRunnable);
        super.a(null);
        return true;
      }
    }
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    super.updateMenuView(paramBoolean);
    ((View)mMenuView).requestLayout();
    Object localObject1 = mMenu;
    int j = 0;
    int k;
    Object localObject2;
    if (localObject1 != null)
    {
      localObject1 = ((MenuBuilder)localObject1).getActionItems();
      k = ((ArrayList)localObject1).size();
      i = 0;
      while (i < k)
      {
        localObject2 = ((android.support.v7.view.menu.MenuItemImpl)((ArrayList)localObject1).get(i)).getSupportActionProvider();
        if (localObject2 != null) {
          ((ActionProvider)localObject2).setSubUiVisibilityListener(this);
        }
        i += 1;
      }
    }
    localObject1 = mMenu;
    if (localObject1 != null) {
      localObject1 = ((MenuBuilder)localObject1).getNonActionItems();
    } else {
      localObject1 = null;
    }
    int i = j;
    boolean bool;
    if (mReserveOverflow)
    {
      i = j;
      if (localObject1 != null)
      {
        k = ((ArrayList)localObject1).size();
        if (k == 1)
        {
          bool = ((android.support.v7.view.menu.MenuItemImpl)((ArrayList)localObject1).get(0)).isActionViewExpanded() ^ true;
        }
        else
        {
          bool = j;
          if (k > 0) {
            bool = true;
          }
        }
      }
    }
    if (bool)
    {
      if (mOverflowButton == null) {
        mOverflowButton = new OverflowMenuButton(mSystemContext);
      }
      localObject1 = (ViewGroup)mOverflowButton.getParent();
      if (localObject1 != mMenuView)
      {
        if (localObject1 != null) {
          ((ViewGroup)localObject1).removeView(mOverflowButton);
        }
        localObject1 = (ActionMenuView)mMenuView;
        ((ViewGroup)localObject1).addView(mOverflowButton, ((ActionMenuView)localObject1).generateOverflowButtonLayoutParams());
      }
    }
    else
    {
      localObject1 = mOverflowButton;
      if (localObject1 != null)
      {
        localObject1 = ((View)localObject1).getParent();
        localObject2 = mMenuView;
        if (localObject1 == localObject2) {
          ((ViewGroup)localObject2).removeView(mOverflowButton);
        }
      }
    }
    ((ActionMenuView)mMenuView).setOverflowReserved(mReserveOverflow);
  }
  
  class ActionMenuPopupCallback
    extends ActionMenuItemView.b
  {
    ActionMenuPopupCallback() {}
    
    public ListPopupWindow getPopup()
    {
      XYPlot localXYPlot = a;
      if (localXYPlot != null) {
        return localXYPlot.a();
      }
      return null;
    }
  }
  
  class OpenOverflowRunnable
    implements Runnable
  {
    private i mPopup;
    
    public OpenOverflowRunnable(i paramI)
    {
      mPopup = paramI;
    }
    
    public void run()
    {
      if (ActionMenuPresenter.access$setMOverflowPopup(ActionMenuPresenter.this) != null) {
        ActionMenuPresenter.access$getMMenu(ActionMenuPresenter.this).changeMenuMode();
      }
      View localView = (View)ActionMenuPresenter.access$getMMenuView(ActionMenuPresenter.this);
      if ((localView != null) && (localView.getWindowToken() != null) && (mPopup.c())) {
        mOverflowPopup = mPopup;
      }
      mPostedOpenRunnable = null;
    }
  }
  
  class OverflowMenuButton
    extends AppCompatImageView
    implements ActionMenuView.a
  {
    private final float[] mTempPts = new float[2];
    
    public OverflowMenuButton(Context paramContext)
    {
      super(null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
      MenuItemImpl.add(this, getContentDescription());
      setOnTouchListener(new ActionMenuPresenter.OverflowMenuButton.1(this, this, ActionMenuPresenter.this));
    }
    
    public boolean needsDividerAfter()
    {
      return false;
    }
    
    public boolean needsDividerBefore()
    {
      return false;
    }
    
    public boolean performClick()
    {
      if (super.performClick()) {
        return true;
      }
      playSoundEffect(0);
      showOverflowMenu();
      return true;
    }
    
    protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
      Drawable localDrawable1 = getDrawable();
      Drawable localDrawable2 = getBackground();
      if ((localDrawable1 != null) && (localDrawable2 != null))
      {
        int i = getWidth();
        paramInt2 = getHeight();
        paramInt1 = Math.max(i, paramInt2) / 2;
        int j = getPaddingLeft();
        int k = getPaddingRight();
        paramInt3 = getPaddingTop();
        paramInt4 = getPaddingBottom();
        i = (i + (j - k)) / 2;
        paramInt2 = (paramInt2 + (paramInt3 - paramInt4)) / 2;
        DrawableCompat.setHotspotBounds(localDrawable2, i - paramInt1, paramInt2 - paramInt1, i + paramInt1, paramInt2 + paramInt1);
      }
      return bool;
    }
  }
  
  class PopupPresenterCallback
    implements l.a
  {
    PopupPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      if ((paramMenuBuilder instanceof SubMenuBuilder)) {
        paramMenuBuilder.getRootMenu().close(false);
      }
      l.a localA = getCallback();
      if (localA != null) {
        localA.onCloseMenu(paramMenuBuilder, paramBoolean);
      }
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      if (paramMenuBuilder == null) {
        return false;
      }
      e = ((SubMenuBuilder)paramMenuBuilder).getItem().getItemId();
      l.a localA = getCallback();
      if (localA != null) {
        return localA.onOpenSubMenu(paramMenuBuilder);
      }
      return false;
    }
  }
}
