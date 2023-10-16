package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import com.org.android.impl.cookie.SupportMenuItem;

public class ActionMenuItem
  implements SupportMenuItem
{
  private CharSequence currentName;
  private int i = 4096;
  private int k = 4096;
  private ColorStateList mButtonTintList = null;
  private PorterDuff.Mode mButtonTintMode = null;
  private final int mCategoryOrder;
  private MenuItem.OnMenuItemClickListener mClickListener;
  private CharSequence mContentDesc;
  private Context mContext;
  private Drawable mDrawable;
  private int mFlags = 16;
  private final int mGroup;
  private boolean mHasButtonTint = false;
  private boolean mHasButtonTintMode = false;
  private int mIconResId = 0;
  private final int mId;
  private Intent mIntent;
  private final int mOrdering;
  private char mShortcutAlphabeticChar;
  private char mShortcutNumericChar;
  private CharSequence mTitle;
  private CharSequence mTitleCondensed;
  
  public ActionMenuItem(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, CharSequence paramCharSequence)
  {
    mContext = paramContext;
    mId = paramInt2;
    mGroup = paramInt1;
    mCategoryOrder = paramInt3;
    mOrdering = paramInt4;
    mTitle = paramCharSequence;
  }
  
  private void setIcon()
  {
    if ((mDrawable != null) && ((mHasButtonTint) || (mHasButtonTintMode)))
    {
      mDrawable = DrawableCompat.wrap(mDrawable);
      mDrawable = mDrawable.mutate();
      if (mHasButtonTint) {
        DrawableCompat.setTintList(mDrawable, mButtonTintList);
      }
      if (mHasButtonTintMode) {
        DrawableCompat.setTintMode(mDrawable, mButtonTintMode);
      }
    }
  }
  
  public boolean collapseActionView()
  {
    return false;
  }
  
  public boolean expandActionView()
  {
    return false;
  }
  
  public android.view.ActionProvider getActionProvider()
  {
    throw new UnsupportedOperationException();
  }
  
  public View getActionView()
  {
    return null;
  }
  
  public int getAlphabeticModifiers()
  {
    return i;
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
    return mDrawable;
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
    return null;
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
    return mOrdering;
  }
  
  public SubMenu getSubMenu()
  {
    return null;
  }
  
  public com.org.android.view.ActionProvider getSupportActionProvider()
  {
    return null;
  }
  
  public CharSequence getTitle()
  {
    return mTitle;
  }
  
  public CharSequence getTitleCondensed()
  {
    CharSequence localCharSequence = mTitleCondensed;
    if (localCharSequence != null) {
      return localCharSequence;
    }
    return mTitle;
  }
  
  public CharSequence getTooltipText()
  {
    return currentName;
  }
  
  public boolean hasSubMenu()
  {
    return false;
  }
  
  public boolean isActionViewExpanded()
  {
    return false;
  }
  
  public boolean isCheckable()
  {
    return (mFlags & 0x1) != 0;
  }
  
  public boolean isChecked()
  {
    return (mFlags & 0x2) != 0;
  }
  
  public boolean isEnabled()
  {
    return (mFlags & 0x10) != 0;
  }
  
  public boolean isVisible()
  {
    return (mFlags & 0x8) == 0;
  }
  
  public MenuItem setActionProvider(android.view.ActionProvider paramActionProvider)
  {
    throw new UnsupportedOperationException();
  }
  
  public SupportMenuItem setActionView(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public SupportMenuItem setActionView(View paramView)
  {
    throw new UnsupportedOperationException();
  }
  
  public MenuItem setAlphabeticShortcut(char paramChar)
  {
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar);
    return this;
  }
  
  public MenuItem setAlphabeticShortcut(char paramChar, int paramInt)
  {
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar);
    i = KeyEvent.normalizeMetaState(paramInt);
    return this;
  }
  
  public MenuItem setCheckable(boolean paramBoolean)
  {
    mFlags = (paramBoolean | mFlags & 0xFFFFFFFE);
    return this;
  }
  
  public MenuItem setChecked(boolean paramBoolean)
  {
    int m = mFlags;
    int j;
    if (paramBoolean) {
      j = 2;
    } else {
      j = 0;
    }
    mFlags = (j | m & 0xFFFFFFFD);
    return this;
  }
  
  public SupportMenuItem setContentDescription(CharSequence paramCharSequence)
  {
    mContentDesc = paramCharSequence;
    return this;
  }
  
  public MenuItem setEnabled(boolean paramBoolean)
  {
    int m = mFlags;
    int j;
    if (paramBoolean) {
      j = 16;
    } else {
      j = 0;
    }
    mFlags = (j | m & 0xFFFFFFEF);
    return this;
  }
  
  public MenuItem setIcon(int paramInt)
  {
    mIconResId = paramInt;
    mDrawable = com.org.android.ui.Resources.getDrawable(mContext, paramInt);
    setIcon();
    return this;
  }
  
  public MenuItem setIcon(Drawable paramDrawable)
  {
    mDrawable = paramDrawable;
    mIconResId = 0;
    setIcon();
    return this;
  }
  
  public MenuItem setIconTintList(ColorStateList paramColorStateList)
  {
    mButtonTintList = paramColorStateList;
    mHasButtonTint = true;
    setIcon();
    return this;
  }
  
  public MenuItem setIconTintMode(PorterDuff.Mode paramMode)
  {
    mButtonTintMode = paramMode;
    mHasButtonTintMode = true;
    setIcon();
    return this;
  }
  
  public MenuItem setIntent(Intent paramIntent)
  {
    mIntent = paramIntent;
    return this;
  }
  
  public MenuItem setNumericShortcut(char paramChar)
  {
    mShortcutNumericChar = paramChar;
    return this;
  }
  
  public MenuItem setNumericShortcut(char paramChar, int paramInt)
  {
    mShortcutNumericChar = paramChar;
    k = KeyEvent.normalizeMetaState(paramInt);
    return this;
  }
  
  public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener paramOnActionExpandListener)
  {
    throw new UnsupportedOperationException();
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
    return this;
  }
  
  public MenuItem setShortcut(char paramChar1, char paramChar2, int paramInt1, int paramInt2)
  {
    mShortcutNumericChar = paramChar1;
    k = KeyEvent.normalizeMetaState(paramInt1);
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar2);
    i = KeyEvent.normalizeMetaState(paramInt2);
    return this;
  }
  
  public void setShowAsAction(int paramInt) {}
  
  public SupportMenuItem setShowAsActionFlags(int paramInt)
  {
    setShowAsAction(paramInt);
    return this;
  }
  
  public SupportMenuItem setSupportActionProvider(com.org.android.view.ActionProvider paramActionProvider)
  {
    throw new UnsupportedOperationException();
  }
  
  public MenuItem setTitle(int paramInt)
  {
    mTitle = mContext.getResources().getString(paramInt);
    return this;
  }
  
  public MenuItem setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    return this;
  }
  
  public MenuItem setTitleCondensed(CharSequence paramCharSequence)
  {
    mTitleCondensed = paramCharSequence;
    return this;
  }
  
  public SupportMenuItem setTooltipText(CharSequence paramCharSequence)
  {
    currentName = paramCharSequence;
    return this;
  }
  
  public MenuItem setVisible(boolean paramBoolean)
  {
    int m = mFlags;
    int j = 8;
    if (paramBoolean) {
      j = 0;
    }
    mFlags = (m & 0x8 | j);
    return this;
  }
}
