package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.v4.util.R.string;

public final class MenuItemImpl
  implements SupportMenuItem
{
  private boolean c = false;
  private CharSequence currentName;
  private int k = 4096;
  private int l = 4096;
  private com.org.android.view.ActionProvider mActionProvider;
  private View mActionView;
  private ColorStateList mButtonTintList = null;
  private PorterDuff.Mode mButtonTintMode = null;
  private final int mCategoryOrder;
  private MenuItem.OnMenuItemClickListener mClickListener;
  private CharSequence mContentDesc;
  private int mFlags = 16;
  private final int mGroup;
  private boolean mHasButtonTint = false;
  private boolean mHasButtonTintMode = false;
  private Drawable mIconDrawable;
  private int mIconResId = 0;
  private final int mId;
  private Intent mIntent;
  private boolean mIsActionViewExpanded = false;
  private Runnable mItemCallback;
  MenuBuilder mMenu;
  private ContextMenu.ContextMenuInfo mMenuInfo;
  private MenuItem.OnActionExpandListener mOnActionExpandListener;
  private final int mOrdering;
  private char mShortcutAlphabeticChar;
  private char mShortcutNumericChar;
  private int mShowAsAction = 0;
  private SubMenuBuilder mSubMenu;
  private CharSequence mTitle;
  private CharSequence mTitleCondensed;
  
  MenuItemImpl(MenuBuilder paramMenuBuilder, int paramInt1, int paramInt2, int paramInt3, int paramInt4, CharSequence paramCharSequence, int paramInt5)
  {
    mMenu = paramMenuBuilder;
    mId = paramInt2;
    mGroup = paramInt1;
    mCategoryOrder = paramInt3;
    mOrdering = paramInt4;
    mTitle = paramCharSequence;
    mShowAsAction = paramInt5;
  }
  
  private static void add(StringBuilder paramStringBuilder, int paramInt1, int paramInt2, String paramString)
  {
    if ((paramInt1 & paramInt2) == paramInt2) {
      paramStringBuilder.append(paramString);
    }
  }
  
  private Drawable getDrawable(Drawable paramDrawable)
  {
    Drawable localDrawable = paramDrawable;
    if (paramDrawable != null)
    {
      localDrawable = paramDrawable;
      if (c) {
        if (!mHasButtonTint)
        {
          localDrawable = paramDrawable;
          if (!mHasButtonTintMode) {}
        }
        else
        {
          localDrawable = DrawableCompat.wrap(paramDrawable).mutate();
          if (mHasButtonTint) {
            DrawableCompat.setTintList(localDrawable, mButtonTintList);
          }
          if (mHasButtonTintMode) {
            DrawableCompat.setTintMode(localDrawable, mButtonTintMode);
          }
          c = false;
        }
      }
    }
    return localDrawable;
  }
  
  String a()
  {
    char c1 = getShortcut();
    if (c1 == 0) {
      return "";
    }
    android.content.res.Resources localResources = mMenu.getContext().getResources();
    StringBuilder localStringBuilder = new StringBuilder();
    if (ViewConfiguration.get(mMenu.getContext()).hasPermanentMenuKey()) {
      localStringBuilder.append(localResources.getString(R.string.abc_prepend_shortcut_label));
    }
    int i;
    if (mMenu.isQwertyMode()) {
      i = l;
    } else {
      i = k;
    }
    add(localStringBuilder, i, 65536, localResources.getString(R.string.abc_menu_meta_shortcut_label));
    add(localStringBuilder, i, 4096, localResources.getString(R.string.abc_menu_ctrl_shortcut_label));
    add(localStringBuilder, i, 2, localResources.getString(R.string.abc_menu_alt_shortcut_label));
    add(localStringBuilder, i, 1, localResources.getString(R.string.abc_menu_shift_shortcut_label));
    add(localStringBuilder, i, 4, localResources.getString(R.string.abc_menu_sym_shortcut_label));
    add(localStringBuilder, i, 8, localResources.getString(R.string.abc_menu_function_shortcut_label));
    if (c1 != '\b')
    {
      if (c1 != '\n')
      {
        if (c1 != ' ')
        {
          localStringBuilder.append(c1);
          break label229;
        }
        i = R.string.abc_menu_space_shortcut_label;
      }
      else
      {
        i = R.string.abc_menu_enter_shortcut_label;
      }
    }
    else {
      i = R.string.abc_menu_delete_shortcut_label;
    }
    localStringBuilder.append(localResources.getString(i));
    label229:
    return localStringBuilder.toString();
  }
  
  public void actionFormatChanged()
  {
    mMenu.onItemActionRequestChanged(this);
  }
  
  public boolean collapseActionView()
  {
    if ((mShowAsAction & 0x8) == 0) {
      return false;
    }
    if (mActionView == null) {
      return true;
    }
    MenuItem.OnActionExpandListener localOnActionExpandListener = mOnActionExpandListener;
    if ((localOnActionExpandListener != null) && (!localOnActionExpandListener.onMenuItemActionCollapse(this))) {
      return false;
    }
    return mMenu.collapseItemActionView(this);
  }
  
  public boolean expandActionView()
  {
    if (!hasCollapsibleActionView()) {
      return false;
    }
    MenuItem.OnActionExpandListener localOnActionExpandListener = mOnActionExpandListener;
    if ((localOnActionExpandListener != null) && (!localOnActionExpandListener.onMenuItemActionExpand(this))) {
      return false;
    }
    return mMenu.expandItemActionView(this);
  }
  
  public android.view.ActionProvider getActionProvider()
  {
    throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
  }
  
  public View getActionView()
  {
    Object localObject = mActionView;
    if (localObject != null) {
      return localObject;
    }
    localObject = mActionProvider;
    if (localObject != null)
    {
      mActionView = ((com.org.android.view.ActionProvider)localObject).onCreateActionView(this);
      return mActionView;
    }
    return null;
  }
  
  public int getAlphabeticModifiers()
  {
    return l;
  }
  
  public char getAlphabeticShortcut()
  {
    return mShortcutAlphabeticChar;
  }
  
  public CharSequence getContentDescription()
  {
    return mContentDesc;
  }
  
  public int getGroupId()
  {
    return mGroup;
  }
  
  public Drawable getIcon()
  {
    Drawable localDrawable = mIconDrawable;
    if (localDrawable != null) {
      return getDrawable(localDrawable);
    }
    if (mIconResId != 0)
    {
      localDrawable = com.org.v4.text.view.Resources.getDrawable(mMenu.getContext(), mIconResId);
      mIconResId = 0;
      mIconDrawable = localDrawable;
      return getDrawable(localDrawable);
    }
    return null;
  }
  
  public ColorStateList getIconTintList()
  {
    return mButtonTintList;
  }
  
  public PorterDuff.Mode getIconTintMode()
  {
    return mButtonTintMode;
  }
  
  public Intent getIntent()
  {
    return mIntent;
  }
  
  public int getItemId()
  {
    return mId;
  }
  
  public ContextMenu.ContextMenuInfo getMenuInfo()
  {
    return mMenuInfo;
  }
  
  public int getNumericModifiers()
  {
    return k;
  }
  
  public char getNumericShortcut()
  {
    return mShortcutNumericChar;
  }
  
  public int getOrder()
  {
    return mCategoryOrder;
  }
  
  public int getOrdering()
  {
    return mOrdering;
  }
  
  char getShortcut()
  {
    if (mMenu.isQwertyMode()) {
      return mShortcutAlphabeticChar;
    }
    return mShortcutNumericChar;
  }
  
  public SubMenu getSubMenu()
  {
    return mSubMenu;
  }
  
  public com.org.android.view.ActionProvider getSupportActionProvider()
  {
    return mActionProvider;
  }
  
  public CharSequence getTitle()
  {
    return mTitle;
  }
  
  public CharSequence getTitleCondensed()
  {
    CharSequence localCharSequence = mTitleCondensed;
    if (localCharSequence == null) {
      localCharSequence = mTitle;
    }
    if ((Build.VERSION.SDK_INT < 18) && (localCharSequence != null) && (!(localCharSequence instanceof String))) {
      return localCharSequence.toString();
    }
    return localCharSequence;
  }
  
  CharSequence getTitleForItemView(MenuView.ItemView paramItemView)
  {
    if ((paramItemView != null) && (paramItemView.prefersCondensedTitle())) {
      return getTitleCondensed();
    }
    return getTitle();
  }
  
  public CharSequence getTooltipText()
  {
    return currentName;
  }
  
  public boolean hasCollapsibleActionView()
  {
    if ((mShowAsAction & 0x8) != 0)
    {
      if (mActionView == null)
      {
        com.org.android.view.ActionProvider localActionProvider = mActionProvider;
        if (localActionProvider != null) {
          mActionView = localActionProvider.onCreateActionView(this);
        }
      }
      if (mActionView != null) {
        return true;
      }
    }
    return false;
  }
  
  public boolean hasSubMenu()
  {
    return mSubMenu != null;
  }
  
  public boolean invoke()
  {
    Object localObject = mClickListener;
    if ((localObject != null) && (((MenuItem.OnMenuItemClickListener)localObject).onMenuItemClick(this))) {
      return true;
    }
    localObject = mMenu;
    if (((MenuBuilder)localObject).dispatchMenuItemSelected((MenuBuilder)localObject, this)) {
      return true;
    }
    localObject = mItemCallback;
    if (localObject != null)
    {
      ((Runnable)localObject).run();
      return true;
    }
    if (mIntent != null)
    {
      localObject = mMenu;
      try
      {
        localObject = ((MenuBuilder)localObject).getContext();
        Intent localIntent = mIntent;
        ((Context)localObject).startActivity(localIntent);
        return true;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", localActivityNotFoundException);
      }
    }
    com.org.android.view.ActionProvider localActionProvider = mActionProvider;
    return (localActionProvider != null) && (localActionProvider.onPerformDefaultAction());
  }
  
  public boolean isActionButton()
  {
    return (mFlags & 0x20) == 32;
  }
  
  public boolean isActionViewExpanded()
  {
    return mIsActionViewExpanded;
  }
  
  public boolean isCheckable()
  {
    return (mFlags & 0x1) == 1;
  }
  
  public boolean isChecked()
  {
    return (mFlags & 0x2) == 2;
  }
  
  public boolean isEnabled()
  {
    return (mFlags & 0x10) != 0;
  }
  
  public boolean isExclusiveCheckable()
  {
    return (mFlags & 0x4) != 0;
  }
  
  public boolean isVisible()
  {
    com.org.android.view.ActionProvider localActionProvider = mActionProvider;
    if ((localActionProvider != null) && (localActionProvider.overridesItemVisibility())) {
      return ((mFlags & 0x8) == 0) && (mActionProvider.isVisible());
    }
    return (mFlags & 0x8) == 0;
  }
  
  public boolean requestsActionButton()
  {
    return (mShowAsAction & 0x1) == 1;
  }
  
  public boolean requiresActionButton()
  {
    return (mShowAsAction & 0x2) == 2;
  }
  
  public MenuItem setActionProvider(android.view.ActionProvider paramActionProvider)
  {
    throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
  }
  
  public SupportMenuItem setActionView(int paramInt)
  {
    Context localContext = mMenu.getContext();
    setActionView(LayoutInflater.from(localContext).inflate(paramInt, new LinearLayout(localContext), false));
    return this;
  }
  
  public SupportMenuItem setActionView(View paramView)
  {
    mActionView = paramView;
    mActionProvider = null;
    if ((paramView != null) && (paramView.getId() == -1))
    {
      int i = mId;
      if (i > 0) {
        paramView.setId(i);
      }
    }
    mMenu.onItemActionRequestChanged(this);
    return this;
  }
  
  public void setActionViewExpanded(boolean paramBoolean)
  {
    mIsActionViewExpanded = paramBoolean;
    mMenu.onItemsChanged(false);
  }
  
  public MenuItem setAlphabeticShortcut(char paramChar)
  {
    if (mShortcutAlphabeticChar == paramChar) {
      return this;
    }
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar);
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setAlphabeticShortcut(char paramChar, int paramInt)
  {
    if ((mShortcutAlphabeticChar == paramChar) && (l == paramInt)) {
      return this;
    }
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar);
    l = KeyEvent.normalizeMetaState(paramInt);
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setCheckable(boolean paramBoolean)
  {
    int i = mFlags;
    mFlags = (paramBoolean | i & 0xFFFFFFFE);
    if (i != mFlags) {
      mMenu.onItemsChanged(false);
    }
    return this;
  }
  
  public MenuItem setChecked(boolean paramBoolean)
  {
    if ((mFlags & 0x4) != 0)
    {
      mMenu.setExclusiveItemChecked(this);
      return this;
    }
    setCheckedInt(paramBoolean);
    return this;
  }
  
  void setCheckedInt(boolean paramBoolean)
  {
    int j = mFlags;
    int i;
    if (paramBoolean) {
      i = 2;
    } else {
      i = 0;
    }
    mFlags = (i | j & 0xFFFFFFFD);
    if (j != mFlags) {
      mMenu.onItemsChanged(false);
    }
  }
  
  public SupportMenuItem setContentDescription(CharSequence paramCharSequence)
  {
    mContentDesc = paramCharSequence;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setEnabled(boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = mFlags | 0x10;
    } else {
      i = mFlags & 0xFFFFFFEF;
    }
    mFlags = i;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public void setExclusiveCheckable(boolean paramBoolean)
  {
    int j = mFlags;
    int i;
    if (paramBoolean) {
      i = 4;
    } else {
      i = 0;
    }
    mFlags = (i | j & 0xFFFFFFFB);
  }
  
  public MenuItem setIcon(int paramInt)
  {
    mIconDrawable = null;
    mIconResId = paramInt;
    c = true;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setIcon(Drawable paramDrawable)
  {
    mIconResId = 0;
    mIconDrawable = paramDrawable;
    c = true;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setIconTintList(ColorStateList paramColorStateList)
  {
    mButtonTintList = paramColorStateList;
    mHasButtonTint = true;
    c = true;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setIconTintMode(PorterDuff.Mode paramMode)
  {
    mButtonTintMode = paramMode;
    mHasButtonTintMode = true;
    c = true;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setIntent(Intent paramIntent)
  {
    mIntent = paramIntent;
    return this;
  }
  
  public void setIsActionButton(boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = mFlags | 0x20;
    } else {
      i = mFlags & 0xFFFFFFDF;
    }
    mFlags = i;
  }
  
  void setMenuInfo(ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    mMenuInfo = paramContextMenuInfo;
  }
  
  public MenuItem setNumericShortcut(char paramChar)
  {
    if (mShortcutNumericChar == paramChar) {
      return this;
    }
    mShortcutNumericChar = paramChar;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setNumericShortcut(char paramChar, int paramInt)
  {
    if ((mShortcutNumericChar == paramChar) && (k == paramInt)) {
      return this;
    }
    mShortcutNumericChar = paramChar;
    k = KeyEvent.normalizeMetaState(paramInt);
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener paramOnActionExpandListener)
  {
    mOnActionExpandListener = paramOnActionExpandListener;
    return this;
  }
  
  public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    mClickListener = paramOnMenuItemClickListener;
    return this;
  }
  
  public MenuItem setShortcut(char paramChar1, char paramChar2)
  {
    mShortcutNumericChar = paramChar1;
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar2);
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setShortcut(char paramChar1, char paramChar2, int paramInt1, int paramInt2)
  {
    mShortcutNumericChar = paramChar1;
    k = KeyEvent.normalizeMetaState(paramInt1);
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar2);
    l = KeyEvent.normalizeMetaState(paramInt2);
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public void setShowAsAction(int paramInt)
  {
    int i = paramInt & 0x3;
    if ((i != 0) && (i != 1) && (i != 2)) {
      throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }
    mShowAsAction = paramInt;
    mMenu.onItemActionRequestChanged(this);
  }
  
  public SupportMenuItem setShowAsActionFlags(int paramInt)
  {
    setShowAsAction(paramInt);
    return this;
  }
  
  public void setSubMenu(SubMenuBuilder paramSubMenuBuilder)
  {
    mSubMenu = paramSubMenuBuilder;
    paramSubMenuBuilder.setHeaderTitle(getTitle());
  }
  
  public SupportMenuItem setSupportActionProvider(com.org.android.view.ActionProvider paramActionProvider)
  {
    com.org.android.view.ActionProvider localActionProvider = mActionProvider;
    if (localActionProvider != null) {
      localActionProvider.reset();
    }
    mActionView = null;
    mActionProvider = paramActionProvider;
    mMenu.onItemsChanged(true);
    paramActionProvider = mActionProvider;
    if (paramActionProvider != null) {
      paramActionProvider.setVisibilityListener(new MenuItemImpl.1(this));
    }
    return this;
  }
  
  public MenuItem setTitle(int paramInt)
  {
    setTitle(mMenu.getContext().getString(paramInt));
    return this;
  }
  
  public MenuItem setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    mMenu.onItemsChanged(false);
    SubMenuBuilder localSubMenuBuilder = mSubMenu;
    if (localSubMenuBuilder != null) {
      localSubMenuBuilder.setHeaderTitle(paramCharSequence);
    }
    return this;
  }
  
  public MenuItem setTitleCondensed(CharSequence paramCharSequence)
  {
    mTitleCondensed = paramCharSequence;
    if (paramCharSequence == null) {}
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public SupportMenuItem setTooltipText(CharSequence paramCharSequence)
  {
    currentName = paramCharSequence;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setVisible(boolean paramBoolean)
  {
    if (setVisibleInt(paramBoolean)) {
      mMenu.onItemVisibleChanged(this);
    }
    return this;
  }
  
  boolean setVisibleInt(boolean paramBoolean)
  {
    int j = mFlags;
    int i;
    if (paramBoolean) {
      i = 0;
    } else {
      i = 8;
    }
    mFlags = (i | j & 0xFFFFFFF7);
    return j != mFlags;
  }
  
  public boolean shouldShowIcon()
  {
    return mMenu.getOptionalIconsVisible();
  }
  
  boolean shouldShowShortcut()
  {
    return (mMenu.isShortcutsVisible()) && (getShortcut() != 0);
  }
  
  public boolean showsTextAsAction()
  {
    return (mShowAsAction & 0x4) == 4;
  }
  
  public String toString()
  {
    CharSequence localCharSequence = mTitle;
    if (localCharSequence != null) {
      return localCharSequence.toString();
    }
    return null;
  }
}
