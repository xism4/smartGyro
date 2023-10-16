package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.org.v4.util.R.styleable;
import com.org.v4.view.ActionMode;
import com.org.v4.view.ActionMode.Callback;

public abstract class ActionBar
{
  public ActionBar() {}
  
  public abstract boolean collapseActionView();
  
  public abstract void dispatchMenuVisibilityChanged(boolean paramBoolean);
  
  public abstract Context getThemedContext();
  
  public boolean invalidateOptionsMenu()
  {
    return false;
  }
  
  public abstract boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent);
  
  public abstract void setDefaultDisplayHomeAsUpEnabled(boolean paramBoolean);
  
  public abstract void setShowHideAnimationEnabled(boolean paramBoolean);
  
  public abstract void setWindowTitle(CharSequence paramCharSequence);
  
  public abstract ActionMode startActionMode(ActionMode.Callback paramCallback);
  
  public class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    public int gravity = 0;
    
    public LayoutParams(int paramInt)
    {
      super(paramInt);
      gravity = 8388627;
    }
    
    public LayoutParams(AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      this$1 = this$1.obtainStyledAttributes(paramAttributeSet, R.styleable.ActionBarLayout);
      gravity = this$1.getInt(R.styleable.ActionBarLayout_android_layout_gravity, 0);
      this$1.recycle();
    }
    
    public LayoutParams()
    {
      super();
      gravity = gravity;
    }
    
    public LayoutParams()
    {
      super();
    }
  }
  
  public abstract interface OnMenuVisibilityListener
  {
    public abstract void onMenuVisibilityChanged(boolean paramBoolean);
  }
  
  @Deprecated
  public abstract class Tab
  {
    public Tab() {}
    
    public abstract CharSequence getContentDescription();
    
    public abstract View getCustomView();
    
    public abstract Drawable getIcon();
    
    public abstract CharSequence getText();
    
    public abstract void select();
  }
}
