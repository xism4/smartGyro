package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.widget.ActionMenuView.a;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.org.v4.util.R.styleable;

public class ActionMenuItemView
  extends AppCompatTextView
  implements MenuView.ItemView, View.OnClickListener, ActionMenuView.a
{
  b g;
  private boolean mAllowTextWithIcon;
  private boolean mExpandedFormat;
  private ListPopupWindow.ForwardingListener mForwardingListener;
  private Drawable mIcon;
  MenuItemImpl mItemData;
  MenuBuilder.ItemInvoker mItemInvoker;
  private int mMaxIconSize;
  private int mMinWidth;
  private int mSavedPaddingLeft;
  private CharSequence mTitle;
  
  public ActionMenuItemView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionMenuItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ActionMenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = paramContext.getResources();
    mAllowTextWithIcon = a();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ActionMenuItemView, paramInt, 0);
    mMinWidth = paramContext.getDimensionPixelSize(R.styleable.ActionMenuItemView_android_minWidth, 0);
    paramContext.recycle();
    mMaxIconSize = ((int)(getDisplayMetricsdensity * 32.0F + 0.5F));
    setOnClickListener(this);
    mSavedPaddingLeft = -1;
    setSaveEnabled(false);
  }
  
  private boolean a()
  {
    Configuration localConfiguration = getContext().getResources().getConfiguration();
    int i = screenWidthDp;
    int j = screenHeightDp;
    return (i >= 480) || ((i >= 640) && (j >= 480)) || (orientation == 2);
  }
  
  private void updateTextButtonVisibility()
  {
    boolean bool = TextUtils.isEmpty(mTitle);
    int j = 1;
    int i = j;
    if (mIcon != null)
    {
      if (mItemData.showsTextAsAction())
      {
        i = j;
        if (mAllowTextWithIcon) {
          break label52;
        }
        if (mExpandedFormat)
        {
          i = j;
          break label52;
        }
      }
      i = 0;
    }
    label52:
    i = (bool ^ true) & i;
    Object localObject2 = null;
    if (i != 0) {
      localObject1 = mTitle;
    } else {
      localObject1 = null;
    }
    setText((CharSequence)localObject1);
    CharSequence localCharSequence = mItemData.getContentDescription();
    Object localObject1 = localCharSequence;
    if (TextUtils.isEmpty(localCharSequence)) {
      if (i != 0) {
        localObject1 = null;
      } else {
        localObject1 = mItemData.getTitle();
      }
    }
    setContentDescription((CharSequence)localObject1);
    localObject1 = mItemData.getTooltipText();
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      if (i != 0) {
        localObject1 = localObject2;
      } else {
        localObject1 = mItemData.getTitle();
      }
      android.support.v7.widget.MenuItemImpl.add(this, (CharSequence)localObject1);
      return;
    }
    android.support.v7.widget.MenuItemImpl.add(this, (CharSequence)localObject1);
  }
  
  public MenuItemImpl getItemData()
  {
    return mItemData;
  }
  
  public boolean hasText()
  {
    return TextUtils.isEmpty(getText()) ^ true;
  }
  
  public void initialize(MenuItemImpl paramMenuItemImpl, int paramInt)
  {
    mItemData = paramMenuItemImpl;
    setIcon(paramMenuItemImpl.getIcon());
    setTitle(paramMenuItemImpl.getTitleForItemView(this));
    setId(paramMenuItemImpl.getItemId());
    if (paramMenuItemImpl.isVisible()) {
      paramInt = 0;
    } else {
      paramInt = 8;
    }
    setVisibility(paramInt);
    setEnabled(paramMenuItemImpl.isEnabled());
    if ((paramMenuItemImpl.hasSubMenu()) && (mForwardingListener == null)) {
      mForwardingListener = new a();
    }
  }
  
  public boolean needsDividerAfter()
  {
    return hasText();
  }
  
  public boolean needsDividerBefore()
  {
    return (hasText()) && (mItemData.getIcon() == null);
  }
  
  public void onClick(View paramView)
  {
    paramView = mItemInvoker;
    if (paramView != null) {
      paramView.invokeItem(mItemData);
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    mAllowTextWithIcon = a();
    updateTextButtonVisibility();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    boolean bool = hasText();
    if (bool)
    {
      i = mSavedPaddingLeft;
      if (i >= 0) {
        super.setPadding(i, getPaddingTop(), getPaddingRight(), getPaddingBottom());
      }
    }
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getMode(paramInt1);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int j = getMeasuredWidth();
    if (i == Integer.MIN_VALUE) {
      paramInt1 = Math.min(paramInt1, mMinWidth);
    } else {
      paramInt1 = mMinWidth;
    }
    if ((i != 1073741824) && (mMinWidth > 0) && (j < paramInt1)) {
      super.onMeasure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), paramInt2);
    }
    if ((!bool) && (mIcon != null)) {
      super.setPadding((getMeasuredWidth() - mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    super.onRestoreInstanceState(null);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (mItemData.hasSubMenu())
    {
      ListPopupWindow.ForwardingListener localForwardingListener = mForwardingListener;
      if ((localForwardingListener != null) && (localForwardingListener.onTouch(this, paramMotionEvent))) {
        return true;
      }
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean prefersCondensedTitle()
  {
    return true;
  }
  
  public void setCheckable(boolean paramBoolean) {}
  
  public void setChecked(boolean paramBoolean) {}
  
  public void setExpandedFormat(boolean paramBoolean)
  {
    if (mExpandedFormat != paramBoolean)
    {
      mExpandedFormat = paramBoolean;
      MenuItemImpl localMenuItemImpl = mItemData;
      if (localMenuItemImpl != null) {
        localMenuItemImpl.actionFormatChanged();
      }
    }
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    mIcon = paramDrawable;
    if (paramDrawable != null)
    {
      int m = paramDrawable.getIntrinsicWidth();
      int i = m;
      int n = paramDrawable.getIntrinsicHeight();
      int j = n;
      int k = mMaxIconSize;
      float f;
      if (m > k)
      {
        f = k / m;
        j = (int)(n * f);
        i = k;
      }
      n = mMaxIconSize;
      m = i;
      k = j;
      if (j > n)
      {
        f = n / j;
        m = (int)(i * f);
        k = n;
      }
      paramDrawable.setBounds(0, 0, m, k);
    }
    setCompoundDrawables(paramDrawable, null, null, null);
    updateTextButtonVisibility();
  }
  
  public void setItemInvoker(MenuBuilder.ItemInvoker paramItemInvoker)
  {
    mItemInvoker = paramItemInvoker;
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mSavedPaddingLeft = paramInt1;
    super.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setPopupCallback(b paramB)
  {
    g = paramB;
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    updateTextButtonVisibility();
  }
  
  private class a
    extends ListPopupWindow.ForwardingListener
  {
    public a()
    {
      super();
    }
    
    public ListPopupWindow getPopup()
    {
      ActionMenuItemView.b localB = g;
      if (localB != null) {
        return localB.getPopup();
      }
      return null;
    }
    
    protected boolean onForwardingStarted()
    {
      Object localObject = ActionMenuItemView.this;
      MenuBuilder.ItemInvoker localItemInvoker = mItemInvoker;
      if ((localItemInvoker != null) && (localItemInvoker.invokeItem(mItemData)))
      {
        localObject = getPopup();
        if ((localObject != null) && (((ListPopupWindow)localObject).isShowing())) {
          return true;
        }
      }
      return false;
    }
  }
  
  public static abstract class b
  {
    public b() {}
    
    public abstract ListPopupWindow getPopup();
  }
}
