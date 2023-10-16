package com.org.v4.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.bool;
import com.org.v4.util.R.dimen;
import com.org.v4.util.R.styleable;

public class ActionBarPolicy
{
  private Context mContext;
  
  private ActionBarPolicy(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static ActionBarPolicy get(Context paramContext)
  {
    return new ActionBarPolicy(paramContext);
  }
  
  public boolean enableHomeButtonByDefault()
  {
    return mContext.getApplicationInfo().targetSdkVersion < 14;
  }
  
  public int getEmbeddedMenuWidthLimit()
  {
    return mContext.getResources().getDisplayMetrics().widthPixels / 2;
  }
  
  public int getStackedTabMaxWidth()
  {
    return mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
  }
  
  public int getTabContainerHeight()
  {
    TypedArray localTypedArray = mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    int j = localTypedArray.getLayoutDimension(R.styleable.ActionBar_height, 0);
    int i = j;
    Resources localResources = mContext.getResources();
    if (!hasEmbeddedTabs()) {
      i = Math.min(j, localResources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
    }
    localTypedArray.recycle();
    return i;
  }
  
  public boolean hasEmbeddedTabs()
  {
    return mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
  }
  
  public int init()
  {
    Configuration localConfiguration = mContext.getResources().getConfiguration();
    int i = screenWidthDp;
    int j = screenHeightDp;
    if ((smallestScreenWidthDp <= 600) && (i <= 600) && ((i <= 960) || (j <= 720)) && ((i <= 720) || (j <= 960)))
    {
      if ((i < 500) && ((i <= 640) || (j <= 480)) && ((i <= 480) || (j <= 640)))
      {
        if (i >= 360) {
          return 3;
        }
        return 2;
      }
      return 4;
    }
    return 5;
  }
  
  public boolean showsOverflowMenuButton()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return true;
    }
    return ViewConfiguration.get(mContext).hasPermanentMenuKey() ^ true;
  }
}
