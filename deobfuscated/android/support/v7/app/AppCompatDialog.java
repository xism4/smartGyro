package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import com.org.android.view.i;
import com.org.android.view.x;
import com.org.v4.util.R.attr;
import com.org.v4.view.ActionMode;
import com.org.v4.view.ActionMode.Callback;

public class AppCompatDialog
  extends Dialog
  implements AppCompatCallback
{
  private final x b = new d(this);
  private AppCompatDelegate mDelegate;
  
  public AppCompatDialog(Context paramContext, int paramInt)
  {
    super(paramContext, getThemeResId(paramContext, paramInt));
    getDelegate().onCreate(null);
    getDelegate().a();
  }
  
  private static int getThemeResId(Context paramContext, int paramInt)
  {
    int i = paramInt;
    if (paramInt == 0)
    {
      TypedValue localTypedValue = new TypedValue();
      paramContext.getTheme().resolveAttribute(R.attr.dialogTheme, localTypedValue, true);
      i = resourceId;
    }
    return i;
  }
  
  boolean a(KeyEvent paramKeyEvent)
  {
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    getDelegate().addContentView(paramView, paramLayoutParams);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    View localView = getWindow().getDecorView();
    return i.a(b, localView, this, paramKeyEvent);
  }
  
  public View findViewById(int paramInt)
  {
    return getDelegate().findViewById(paramInt);
  }
  
  public AppCompatDelegate getDelegate()
  {
    if (mDelegate == null) {
      mDelegate = AppCompatDelegate.create(this, this);
    }
    return mDelegate;
  }
  
  public void invalidateOptionsMenu()
  {
    getDelegate().invalidateOptionsMenu();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    getDelegate().installViewFactory();
    super.onCreate(paramBundle);
    getDelegate().onCreate(paramBundle);
  }
  
  protected void onStop()
  {
    super.onStop();
    getDelegate().c();
  }
  
  public void onSupportActionModeFinished(ActionMode paramActionMode) {}
  
  public void onSupportActionModeStarted(ActionMode paramActionMode) {}
  
  public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback)
  {
    return null;
  }
  
  public void setContentView(int paramInt)
  {
    getDelegate().setContentView(paramInt);
  }
  
  public void setContentView(View paramView)
  {
    getDelegate().setContentView(paramView);
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    getDelegate().setContentView(paramView, paramLayoutParams);
  }
  
  public void setTitle(int paramInt)
  {
    super.setTitle(paramInt);
    getDelegate().onTitleChanged(getContext().getString(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    super.setTitle(paramCharSequence);
    getDelegate().onTitleChanged(paramCharSequence);
  }
  
  public boolean supportRequestWindowFeature(int paramInt)
  {
    return getDelegate().requestWindowFeature(paramInt);
  }
}
