package android.support.v7.widget;

import android.content.res.Configuration;
import android.support.v7.app.ActionBar.Tab;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import com.org.v4.util.R.attr;
import com.org.v4.view.ActionBarPolicy;

public class ScrollingTabContainerView
  extends HorizontalScrollView
  implements AdapterView.OnItemSelectedListener
{
  private static final Interpolator sAlphaInterpolator = new DecelerateInterpolator();
  private boolean mAllowCollapse;
  private int mContentHeight;
  int mMaxTabWidth;
  private int mSelectedTabIndex;
  int mStackedTabMaxWidth;
  private aa.b mTabClickListener;
  LinearLayoutCompat mTabLayout;
  Runnable mTabSelector;
  private Spinner mTabSpinner;
  
  private Spinner createSpinner()
  {
    AppCompatSpinner localAppCompatSpinner = new AppCompatSpinner(getContext(), null, R.attr.actionDropDownStyle);
    localAppCompatSpinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
    localAppCompatSpinner.setOnItemSelectedListener(this);
    return localAppCompatSpinner;
  }
  
  private boolean isCollapsed()
  {
    Spinner localSpinner = mTabSpinner;
    return (localSpinner != null) && (localSpinner.getParent() == this);
  }
  
  private void performCollapse()
  {
    if (isCollapsed()) {
      return;
    }
    if (mTabSpinner == null) {
      mTabSpinner = createSpinner();
    }
    removeView(mTabLayout);
    addView(mTabSpinner, new ViewGroup.LayoutParams(-2, -1));
    if (mTabSpinner.getAdapter() == null) {
      mTabSpinner.setAdapter(new aa.a(this));
    }
    Runnable localRunnable = mTabSelector;
    if (localRunnable != null)
    {
      removeCallbacks(localRunnable);
      mTabSelector = null;
    }
    mTabSpinner.setSelection(mSelectedTabIndex);
  }
  
  private boolean performExpand()
  {
    if (!isCollapsed()) {
      return false;
    }
    removeView(mTabSpinner);
    addView(mTabLayout, new ViewGroup.LayoutParams(-2, -1));
    setTabSelected(mTabSpinner.getSelectedItemPosition());
    return false;
  }
  
  public void animateToTab(int paramInt)
  {
    View localView = mTabLayout.getChildAt(paramInt);
    Runnable localRunnable = mTabSelector;
    if (localRunnable != null) {
      removeCallbacks(localRunnable);
    }
    mTabSelector = new ScrollingTabContainerView.1(this, localView);
    post(mTabSelector);
  }
  
  aa.c createTabView(ActionBar.Tab paramTab, boolean paramBoolean)
  {
    paramTab = new aa.c(this, getContext(), paramTab, paramBoolean);
    if (paramBoolean)
    {
      paramTab.setBackgroundDrawable(null);
      paramTab.setLayoutParams(new AbsListView.LayoutParams(-1, mContentHeight));
      return paramTab;
    }
    paramTab.setFocusable(true);
    if (mTabClickListener == null) {
      mTabClickListener = new aa.b(this);
    }
    paramTab.setOnClickListener(mTabClickListener);
    return paramTab;
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Runnable localRunnable = mTabSelector;
    if (localRunnable != null) {
      post(localRunnable);
    }
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    paramConfiguration = ActionBarPolicy.get(getContext());
    setContentHeight(paramConfiguration.getTabContainerHeight());
    mStackedTabMaxWidth = paramConfiguration.getStackedTabMaxWidth();
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Runnable localRunnable = mTabSelector;
    if (localRunnable != null) {
      removeCallbacks(localRunnable);
    }
  }
  
  public void onItemSelected(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    ((aa.c)paramView).getTab().select();
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt2 = View.MeasureSpec.getMode(paramInt1);
    int i = 1;
    boolean bool;
    if (paramInt2 == 1073741824) {
      bool = true;
    } else {
      bool = false;
    }
    setFillViewport(bool);
    int j = mTabLayout.getChildCount();
    if ((j > 1) && ((paramInt2 == 1073741824) || (paramInt2 == Integer.MIN_VALUE)))
    {
      if (j > 2) {
        mMaxTabWidth = ((int)(View.MeasureSpec.getSize(paramInt1) * 0.4F));
      } else {
        mMaxTabWidth = (View.MeasureSpec.getSize(paramInt1) / 2);
      }
      paramInt2 = Math.min(mMaxTabWidth, mStackedTabMaxWidth);
    }
    else
    {
      paramInt2 = -1;
    }
    mMaxTabWidth = paramInt2;
    j = View.MeasureSpec.makeMeasureSpec(mContentHeight, 1073741824);
    if ((!bool) && (mAllowCollapse)) {
      paramInt2 = i;
    } else {
      paramInt2 = 0;
    }
    if (paramInt2 != 0)
    {
      mTabLayout.measure(0, j);
      if (mTabLayout.getMeasuredWidth() > View.MeasureSpec.getSize(paramInt1))
      {
        performCollapse();
        break label179;
      }
    }
    performExpand();
    label179:
    paramInt2 = getMeasuredWidth();
    super.onMeasure(paramInt1, j);
    paramInt1 = getMeasuredWidth();
    if ((bool) && (paramInt2 != paramInt1)) {
      setTabSelected(mSelectedTabIndex);
    }
  }
  
  public void onNothingSelected(AdapterView paramAdapterView) {}
  
  public void setAllowCollapse(boolean paramBoolean)
  {
    mAllowCollapse = paramBoolean;
  }
  
  public void setContentHeight(int paramInt)
  {
    mContentHeight = paramInt;
    requestLayout();
  }
  
  public void setTabSelected(int paramInt)
  {
    mSelectedTabIndex = paramInt;
    int j = mTabLayout.getChildCount();
    int i = 0;
    while (i < j)
    {
      localObject = mTabLayout.getChildAt(i);
      boolean bool;
      if (i == paramInt) {
        bool = true;
      } else {
        bool = false;
      }
      ((View)localObject).setSelected(bool);
      if (bool) {
        animateToTab(paramInt);
      }
      i += 1;
    }
    Object localObject = mTabSpinner;
    if ((localObject != null) && (paramInt >= 0)) {
      ((AbsSpinner)localObject).setSelection(paramInt);
    }
  }
}
