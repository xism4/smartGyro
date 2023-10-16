package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class i
  implements ActionMode.Callback
{
  private final TextView a;
  private Class b;
  private Method c;
  private boolean d;
  private boolean f;
  private final ActionMode.Callback mWrapped;
  
  i(ActionMode.Callback paramCallback, TextView paramTextView)
  {
    mWrapped = paramCallback;
    a = paramTextView;
    f = false;
  }
  
  private void a(Menu paramMenu)
  {
    Object localObject2 = a.getContext();
    PackageManager localPackageManager = ((Context)localObject2).getPackageManager();
    if (!f) {
      f = true;
    }
    try
    {
      localObject1 = Class.forName("com.android.internal.view.menu.MenuBuilder");
      b = ((Class)localObject1);
      localObject1 = b;
      localObject3 = Integer.TYPE;
      localObject1 = ((Class)localObject1).getDeclaredMethod("removeItemAt", new Class[] { localObject3 });
      c = ((Method)localObject1);
      d = true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        try
        {
          Object localObject3;
          boolean bool = ((Class)localObject1).isInstance(paramMenu);
          if (bool)
          {
            localObject1 = c;
          }
          else
          {
            localObject1 = paramMenu.getClass();
            localObject3 = Integer.TYPE;
            localObject1 = ((Class)localObject1).getDeclaredMethod("removeItemAt", new Class[] { localObject3 });
          }
          int i = paramMenu.size();
          i -= 1;
          if (i >= 0)
          {
            localObject3 = paramMenu.getItem(i);
            Intent localIntent = ((MenuItem)localObject3).getIntent();
            if (localIntent != null)
            {
              bool = "android.intent.action.PROCESS_TEXT".equals(((MenuItem)localObject3).getIntent().getAction());
              if (bool) {
                ((Method)localObject1).invoke(paramMenu, new Object[] { Integer.valueOf(i) });
              }
            }
            i -= 1;
            continue;
          }
          Object localObject1 = get((Context)localObject2, localPackageManager);
          i = 0;
          if (i < ((List)localObject1).size())
          {
            localObject2 = (ResolveInfo)((List)localObject1).get(i);
            paramMenu.add(0, 0, i + 100, ((ResolveInfo)localObject2).loadLabel(localPackageManager)).setIntent(onClick((ResolveInfo)localObject2, a)).setShowAsAction(1);
            i += 1;
            continue;
          }
          return;
        }
        catch (NoSuchMethodException paramMenu)
        {
          return;
        }
        catch (IllegalAccessException paramMenu)
        {
          return;
        }
        catch (InvocationTargetException paramMenu) {}
        localClassNotFoundException = localClassNotFoundException;
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
    b = null;
    c = null;
    d = false;
    if (d) {
      localObject1 = b;
    }
  }
  
  private boolean a(TextView paramTextView)
  {
    return ((paramTextView instanceof Editable)) && (paramTextView.onCheckIsTextEditor()) && (paramTextView.isEnabled());
  }
  
  private boolean add(ResolveInfo paramResolveInfo, Context paramContext)
  {
    if (paramContext.getPackageName().equals(activityInfo.packageName)) {
      return true;
    }
    if (!activityInfo.exported) {
      return false;
    }
    paramResolveInfo = activityInfo.permission;
    if (paramResolveInfo != null) {
      return paramContext.checkSelfPermission(paramResolveInfo) == 0;
    }
    return true;
  }
  
  private List get(Context paramContext, PackageManager paramPackageManager)
  {
    ArrayList localArrayList = new ArrayList();
    if (!(paramContext instanceof Activity)) {
      return localArrayList;
    }
    paramPackageManager = paramPackageManager.queryIntentActivities(getIntent(), 0).iterator();
    while (paramPackageManager.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramPackageManager.next();
      if (add(localResolveInfo, paramContext)) {
        localArrayList.add(localResolveInfo);
      }
    }
    return localArrayList;
  }
  
  private Intent getIntent()
  {
    return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
  }
  
  private Intent onClick(ResolveInfo paramResolveInfo, TextView paramTextView)
  {
    return getIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", a(paramTextView) ^ true).setClassName(activityInfo.packageName, activityInfo.name);
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return mWrapped.onCreateActionMode(paramActionMode, paramMenu);
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    mWrapped.onDestroyActionMode(paramActionMode);
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    a(paramMenu);
    return mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
  }
}
