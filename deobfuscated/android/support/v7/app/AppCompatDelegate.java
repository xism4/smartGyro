package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public abstract class AppCompatDelegate
{
  private static int sDefaultNightMode;
  
  AppCompatDelegate() {}
  
  public static AppCompatDelegate create(Dialog paramDialog, AppCompatCallback paramAppCompatCallback)
  {
    return new AppCompatDelegateImplV7(paramDialog.getContext(), paramDialog.getWindow(), paramAppCompatCallback);
  }
  
  public static int getDefaultNightMode()
  {
    return sDefaultNightMode;
  }
  
  public abstract boolean a();
  
  public abstract void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams);
  
  public abstract void c();
  
  public abstract View findViewById(int paramInt);
  
  public abstract void installViewFactory();
  
  public abstract void invalidateOptionsMenu();
  
  public abstract void onCreate(Bundle paramBundle);
  
  public abstract void onTitleChanged(CharSequence paramCharSequence);
  
  public abstract boolean requestWindowFeature(int paramInt);
  
  public abstract void setContentView(int paramInt);
  
  public abstract void setContentView(View paramView);
  
  public abstract void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams);
}
