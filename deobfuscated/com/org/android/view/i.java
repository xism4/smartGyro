package com.org.android.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class i
{
  private static Method b;
  private static boolean c;
  private static Field sMinWidthField;
  private static boolean sMinWidthFieldFetched;
  
  private static boolean a(ActionBar paramActionBar, KeyEvent paramKeyEvent)
  {
    if (!c) {}
    try
    {
      localObject = paramActionBar.getClass();
      localObject = ((Class)localObject).getMethod("onMenuKeyEvent", new Class[] { KeyEvent.class });
      b = (Method)localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Object localObject;
      for (;;) {}
    }
    c = true;
    localObject = b;
    if (localObject != null) {}
    try
    {
      paramActionBar = ((Method)localObject).invoke(paramActionBar, new Object[] { paramKeyEvent });
      paramActionBar = (Boolean)paramActionBar;
      boolean bool = paramActionBar.booleanValue();
      return bool;
    }
    catch (IllegalAccessException paramActionBar)
    {
      return false;
    }
    catch (InvocationTargetException paramActionBar) {}
    return false;
    return false;
  }
  
  private static boolean a(Activity paramActivity, KeyEvent paramKeyEvent)
  {
    paramActivity.onUserInteraction();
    Object localObject = paramActivity.getWindow();
    if (((Window)localObject).hasFeature(8))
    {
      ActionBar localActionBar = paramActivity.getActionBar();
      if ((paramKeyEvent.getKeyCode() == 82) && (localActionBar != null) && (a(localActionBar, paramKeyEvent))) {
        return true;
      }
    }
    if (((Window)localObject).superDispatchKeyEvent(paramKeyEvent)) {
      return true;
    }
    localObject = ((Window)localObject).getDecorView();
    if (ViewCompat.b((View)localObject, paramKeyEvent)) {
      return true;
    }
    if (localObject != null) {
      localObject = ((View)localObject).getKeyDispatcherState();
    } else {
      localObject = null;
    }
    return paramKeyEvent.dispatch(paramActivity, (KeyEvent.DispatcherState)localObject, paramActivity);
  }
  
  private static boolean a(Dialog paramDialog, KeyEvent paramKeyEvent)
  {
    Object localObject = getMinimumWidth(paramDialog);
    if ((localObject != null) && (((DialogInterface.OnKeyListener)localObject).onKey(paramDialog, paramKeyEvent.getKeyCode(), paramKeyEvent))) {
      return true;
    }
    localObject = paramDialog.getWindow();
    if (((Window)localObject).superDispatchKeyEvent(paramKeyEvent)) {
      return true;
    }
    localObject = ((Window)localObject).getDecorView();
    if (ViewCompat.b((View)localObject, paramKeyEvent)) {
      return true;
    }
    if (localObject != null) {
      localObject = ((View)localObject).getKeyDispatcherState();
    } else {
      localObject = null;
    }
    return paramKeyEvent.dispatch(paramDialog, (KeyEvent.DispatcherState)localObject, paramDialog);
  }
  
  public static boolean a(View paramView, KeyEvent paramKeyEvent)
  {
    return ViewCompat.a(paramView, paramKeyEvent);
  }
  
  public static boolean a(x paramX, View paramView, Window.Callback paramCallback, KeyEvent paramKeyEvent)
  {
    if (paramX == null) {
      return false;
    }
    if (Build.VERSION.SDK_INT >= 28) {
      return paramX.a(paramKeyEvent);
    }
    if ((paramCallback instanceof Activity)) {
      return a((Activity)paramCallback, paramKeyEvent);
    }
    if ((paramCallback instanceof Dialog)) {
      return a((Dialog)paramCallback, paramKeyEvent);
    }
    return ((paramView != null) && (ViewCompat.b(paramView, paramKeyEvent))) || (paramX.a(paramKeyEvent));
  }
  
  private static DialogInterface.OnKeyListener getMinimumWidth(Dialog paramDialog)
  {
    if (!sMinWidthFieldFetched) {}
    try
    {
      localField = Dialog.class.getDeclaredField("mOnKeyListener");
      sMinWidthField = localField;
      localField = sMinWidthField;
      localField.setAccessible(true);
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      Field localField;
      for (;;) {}
    }
    sMinWidthFieldFetched = true;
    localField = sMinWidthField;
    if (localField != null) {}
    try
    {
      paramDialog = localField.get(paramDialog);
      return (DialogInterface.OnKeyListener)paramDialog;
    }
    catch (IllegalAccessException paramDialog)
    {
      for (;;) {}
    }
    return null;
  }
}
