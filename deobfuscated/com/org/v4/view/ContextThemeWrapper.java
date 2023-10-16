package com.org.v4.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import com.org.v4.util.R.style;

public class ContextThemeWrapper
  extends ContextWrapper
{
  private Configuration instance;
  private LayoutInflater mInflater;
  private Resources.Theme mTheme;
  private int mThemeResource;
  private Resources result;
  
  public ContextThemeWrapper()
  {
    super(null);
  }
  
  public ContextThemeWrapper(Context paramContext, int paramInt)
  {
    super(paramContext);
    mThemeResource = paramInt;
  }
  
  public ContextThemeWrapper(Context paramContext, Resources.Theme paramTheme)
  {
    super(paramContext);
    mTheme = paramTheme;
  }
  
  private Resources get()
  {
    if (result == null)
    {
      Object localObject = instance;
      if (localObject == null) {}
      for (localObject = super.getResources();; localObject = createConfigurationContext((Configuration)localObject).getResources())
      {
        result = ((Resources)localObject);
        break;
        if (Build.VERSION.SDK_INT < 17) {
          break;
        }
      }
    }
    return result;
  }
  
  private void initializeTheme()
  {
    boolean bool;
    if (mTheme == null) {
      bool = true;
    } else {
      bool = false;
    }
    if (bool)
    {
      mTheme = getResources().newTheme();
      Resources.Theme localTheme = getBaseContext().getTheme();
      if (localTheme != null) {
        mTheme.setTo(localTheme);
      }
    }
    onApplyThemeResource(mTheme, mThemeResource, bool);
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
  }
  
  public AssetManager getAssets()
  {
    return getResources().getAssets();
  }
  
  public Resources getResources()
  {
    return get();
  }
  
  public Object getSystemService(String paramString)
  {
    if ("layout_inflater".equals(paramString))
    {
      if (mInflater == null) {
        mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
      }
      return mInflater;
    }
    return getBaseContext().getSystemService(paramString);
  }
  
  public Resources.Theme getTheme()
  {
    Resources.Theme localTheme = mTheme;
    if (localTheme != null) {
      return localTheme;
    }
    if (mThemeResource == 0) {
      mThemeResource = R.style.Theme_AppCompat_Light;
    }
    initializeTheme();
    return mTheme;
  }
  
  public int getThemeResId()
  {
    return mThemeResource;
  }
  
  protected void onApplyThemeResource(Resources.Theme paramTheme, int paramInt, boolean paramBoolean)
  {
    paramTheme.applyStyle(paramInt, true);
  }
  
  public void setTheme(int paramInt)
  {
    if (mThemeResource != paramInt)
    {
      mThemeResource = paramInt;
      initializeTheme();
    }
  }
}
