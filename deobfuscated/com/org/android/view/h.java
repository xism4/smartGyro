package com.org.android.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.MenuItem;
import com.org.android.impl.cookie.SupportMenuItem;

public final class h
{
  public static void a(MenuItem paramMenuItem, char paramChar, int paramInt)
  {
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      ((SupportMenuItem)paramMenuItem).setAlphabeticShortcut(paramChar, paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setAlphabeticShortcut(paramChar, paramInt);
    }
  }
  
  public static void a(MenuItem paramMenuItem, ColorStateList paramColorStateList)
  {
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      ((SupportMenuItem)paramMenuItem).setIconTintList(paramColorStateList);
      return;
    }
    if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setIconTintList(paramColorStateList);
    }
  }
  
  public static void a(MenuItem paramMenuItem, PorterDuff.Mode paramMode)
  {
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      ((SupportMenuItem)paramMenuItem).setIconTintMode(paramMode);
      return;
    }
    if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setIconTintMode(paramMode);
    }
  }
  
  public static void a(MenuItem paramMenuItem, CharSequence paramCharSequence)
  {
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      ((SupportMenuItem)paramMenuItem).setContentDescription(paramCharSequence);
      return;
    }
    if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setContentDescription(paramCharSequence);
    }
  }
  
  public static MenuItem setActionProvider(MenuItem paramMenuItem, ActionProvider paramActionProvider)
  {
    if ((paramMenuItem instanceof SupportMenuItem)) {
      return ((SupportMenuItem)paramMenuItem).setSupportActionProvider(paramActionProvider);
    }
    Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
    return paramMenuItem;
  }
  
  public static void setShowAsAction(MenuItem paramMenuItem, char paramChar, int paramInt)
  {
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      ((SupportMenuItem)paramMenuItem).setNumericShortcut(paramChar, paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setNumericShortcut(paramChar, paramInt);
    }
  }
  
  public static void setShowAsAction(MenuItem paramMenuItem, CharSequence paramCharSequence)
  {
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      ((SupportMenuItem)paramMenuItem).setTooltipText(paramCharSequence);
      return;
    }
    if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setTooltipText(paramCharSequence);
    }
  }
}
