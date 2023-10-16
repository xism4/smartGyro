package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;

public final class NavUtils
{
  public static String getParentActivityName(Activity paramActivity)
  {
    try
    {
      paramActivity = getParentActivityName(paramActivity, paramActivity.getComponentName());
      return paramActivity;
    }
    catch (PackageManager.NameNotFoundException paramActivity)
    {
      throw new IllegalArgumentException(paramActivity);
    }
  }
  
  public static String getParentActivityName(Context paramContext, ComponentName paramComponentName)
  {
    paramComponentName = paramContext.getPackageManager().getActivityInfo(paramComponentName, 128);
    if (Build.VERSION.SDK_INT >= 16)
    {
      str = parentActivityName;
      if (str != null) {
        return str;
      }
    }
    if (metaData == null) {
      return null;
    }
    String str = metaData.getString("android.support.PARENT_ACTIVITY");
    if (str == null) {
      return null;
    }
    paramComponentName = str;
    if (str.charAt(0) == '.')
    {
      paramComponentName = new StringBuilder();
      paramComponentName.append(paramContext.getPackageName());
      paramComponentName.append(str);
      paramComponentName = paramComponentName.toString();
    }
    return paramComponentName;
  }
}
