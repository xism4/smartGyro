package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView.SelectionBoundsAdjuster;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.id;
import com.org.v4.util.R.layout;
import com.org.v4.util.R.styleable;

public class ListMenuItemView
  extends LinearLayout
  implements MenuView.ItemView, AbsListView.SelectionBoundsAdjuster
{
  private ImageView c;
  private LinearLayout d;
  private ImageView e;
  private Drawable h;
  private boolean i;
  private Drawable mBackground;
  private CheckBox mCheckBox;
  private boolean mForceShowIcon;
  private ImageView mIconView;
  private LayoutInflater mInflater;
  private MenuItemImpl mItemData;
  private int mMenuType;
  private boolean mPreserveIconSpacing;
  private RadioButton mRadioButton;
  private TextView mShortcutView;
  private int mTextAppearance;
  private Context mTextAppearanceContext;
  private TextView mTitleView;
  
  public ListMenuItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.listMenuViewStyle);
  }
  
  public ListMenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, R.styleable.MenuView, paramInt, 0);
    mBackground = paramAttributeSet.getDrawable(R.styleable.MenuView_android_itemBackground);
    mTextAppearance = paramAttributeSet.getResourceId(R.styleable.MenuView_android_itemTextAppearance, -1);
    mPreserveIconSpacing = paramAttributeSet.getBoolean(R.styleable.MenuView_preserveIconSpacing, false);
    mTextAppearanceContext = paramContext;
    h = paramAttributeSet.getDrawable(R.styleable.MenuView_subMenuArrow);
    paramContext = paramContext.getTheme();
    paramInt = R.attr.dropDownListViewStyle;
    paramContext = paramContext.obtainStyledAttributes(null, new int[] { 16843049 }, paramInt, 0);
    i = paramContext.hasValue(0);
    paramAttributeSet.recycle();
    paramContext.recycle();
  }
  
  private void a(View paramView)
  {
    a(paramView, -1);
  }
  
  private void a(View paramView, int paramInt)
  {
    LinearLayout localLinearLayout = d;
    if (localLinearLayout != null)
    {
      localLinearLayout.addView(paramView, paramInt);
      return;
    }
    addView(paramView, paramInt);
  }
  
  private LayoutInflater getInflater()
  {
    if (mInflater == null) {
      mInflater = LayoutInflater.from(getContext());
    }
    return mInflater;
  }
  
  private void insertCheckBox()
  {
    mCheckBox = ((CheckBox)getInflater().inflate(R.layout.abc_list_menu_item_checkbox, this, false));
    a(mCheckBox);
  }
  
  private void insertIconView()
  {
    mIconView = ((ImageView)getInflater().inflate(R.layout.abc_list_menu_item_icon, this, false));
    a(mIconView, 0);
  }
  
  private void insertRadioButton()
  {
    mRadioButton = ((RadioButton)getInflater().inflate(R.layout.abc_list_menu_item_radio, this, false));
    a(mRadioButton);
  }
  
  private void setSubMenuArrowVisible(boolean paramBoolean)
  {
    ImageView localImageView = e;
    if (localImageView != null)
    {
      int j;
      if (paramBoolean) {
        j = 0;
      } else {
        j = 8;
      }
      localImageView.setVisibility(j);
    }
  }
  
  public void adjustListItemSelectionBounds(Rect paramRect)
  {
    Object localObject = c;
    if ((localObject != null) && (((View)localObject).getVisibility() == 0))
    {
      localObject = (LinearLayout.LayoutParams)c.getLayoutParams();
      top += c.getHeight() + topMargin + bottomMargin;
    }
  }
  
  public MenuItemImpl getItemData()
  {
    return mItemData;
  }
  
  public void initialize(MenuItemImpl paramMenuItemImpl, int paramInt)
  {
    mItemData = paramMenuItemImpl;
    mMenuType = paramInt;
    if (paramMenuItemImpl.isVisible()) {
      paramInt = 0;
    } else {
      paramInt = 8;
    }
    setVisibility(paramInt);
    setTitle(paramMenuItemImpl.getTitleForItemView(this));
    setCheckable(paramMenuItemImpl.isCheckable());
    setShortcut(paramMenuItemImpl.shouldShowShortcut(), paramMenuItemImpl.getShortcut());
    setIcon(paramMenuItemImpl.getIcon());
    setEnabled(paramMenuItemImpl.isEnabled());
    setSubMenuArrowVisible(paramMenuItemImpl.hasSubMenu());
    setContentDescription(paramMenuItemImpl.getContentDescription());
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    ViewCompat.setBackground(this, mBackground);
    mTitleView = ((TextView)findViewById(R.id.title));
    int j = mTextAppearance;
    if (j != -1) {
      mTitleView.setTextAppearance(mTextAppearanceContext, j);
    }
    mShortcutView = ((TextView)findViewById(R.id.shortcut));
    e = ((ImageView)findViewById(R.id.submenuarrow));
    ImageView localImageView = e;
    if (localImageView != null) {
      localImageView.setImageDrawable(h);
    }
    c = ((ImageView)findViewById(R.id.group_divider));
    d = ((LinearLayout)findViewById(R.id.content));
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if ((mIconView != null) && (mPreserveIconSpacing))
    {
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      LinearLayout.LayoutParams localLayoutParams1 = (LinearLayout.LayoutParams)mIconView.getLayoutParams();
      if ((height > 0) && (width <= 0)) {
        width = height;
      }
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public boolean prefersCondensedTitle()
  {
    return false;
  }
  
  public void setCheckable(boolean paramBoolean)
  {
    if ((!paramBoolean) && (mRadioButton == null) && (mCheckBox == null)) {
      return;
    }
    Object localObject1;
    Object localObject2;
    if (mItemData.isExclusiveCheckable())
    {
      if (mRadioButton == null) {
        insertRadioButton();
      }
      localObject1 = mRadioButton;
      localObject2 = mCheckBox;
    }
    else
    {
      if (mCheckBox == null) {
        insertCheckBox();
      }
      localObject1 = mCheckBox;
      localObject2 = mRadioButton;
    }
    if (paramBoolean)
    {
      ((CompoundButton)localObject1).setChecked(mItemData.isChecked());
      if (((View)localObject1).getVisibility() != 0) {
        ((View)localObject1).setVisibility(0);
      }
      if ((localObject2 != null) && (((View)localObject2).getVisibility() != 8)) {
        ((View)localObject2).setVisibility(8);
      }
    }
    else
    {
      localObject1 = mCheckBox;
      if (localObject1 != null) {
        ((View)localObject1).setVisibility(8);
      }
      localObject1 = mRadioButton;
      if (localObject1 != null) {
        ((View)localObject1).setVisibility(8);
      }
    }
  }
  
  public void setChecked(boolean paramBoolean)
  {
    Object localObject;
    if (mItemData.isExclusiveCheckable())
    {
      if (mRadioButton == null) {
        insertRadioButton();
      }
      localObject = mRadioButton;
    }
    else
    {
      if (mCheckBox == null) {
        insertCheckBox();
      }
      localObject = mCheckBox;
    }
    ((CompoundButton)localObject).setChecked(paramBoolean);
  }
  
  public void setForceShowIcon(boolean paramBoolean)
  {
    mForceShowIcon = paramBoolean;
    mPreserveIconSpacing = paramBoolean;
  }
  
  public void setGroupDividerEnabled(boolean paramBoolean)
  {
    ImageView localImageView = c;
    if (localImageView != null)
    {
      int j;
      if ((!i) && (paramBoolean)) {
        j = 0;
      } else {
        j = 8;
      }
      localImageView.setVisibility(j);
    }
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    int j;
    if ((!mItemData.shouldShowIcon()) && (!mForceShowIcon)) {
      j = 0;
    } else {
      j = 1;
    }
    if ((j == 0) && (!mPreserveIconSpacing)) {
      return;
    }
    if ((mIconView == null) && (paramDrawable == null) && (!mPreserveIconSpacing)) {
      return;
    }
    if (mIconView == null) {
      insertIconView();
    }
    if ((paramDrawable == null) && (!mPreserveIconSpacing))
    {
      mIconView.setVisibility(8);
      return;
    }
    ImageView localImageView = mIconView;
    if (j == 0) {
      paramDrawable = null;
    }
    localImageView.setImageDrawable(paramDrawable);
    if (mIconView.getVisibility() != 0) {
      mIconView.setVisibility(0);
    }
  }
  
  public void setShortcut(boolean paramBoolean, char paramChar)
  {
    if ((paramBoolean) && (mItemData.shouldShowShortcut())) {
      paramChar = '\000';
    } else {
      paramChar = '\b';
    }
    if (paramChar == 0) {
      mShortcutView.setText(mItemData.a());
    }
    if (mShortcutView.getVisibility() != paramChar) {
      mShortcutView.setVisibility(paramChar);
    }
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    int j;
    if (paramCharSequence != null)
    {
      mTitleView.setText(paramCharSequence);
      if (mTitleView.getVisibility() == 0) {
        return;
      }
      paramCharSequence = mTitleView;
      j = 0;
    }
    else
    {
      int k = mTitleView.getVisibility();
      j = 8;
      if (k == 8) {
        return;
      }
      paramCharSequence = mTitleView;
    }
    paramCharSequence.setVisibility(j);
  }
}
