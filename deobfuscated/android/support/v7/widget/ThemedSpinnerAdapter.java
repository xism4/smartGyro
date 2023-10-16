package android.support.v7.widget;

import android.content.res.Resources.Theme;
import android.widget.SpinnerAdapter;

public abstract interface ThemedSpinnerAdapter
  extends SpinnerAdapter
{
  public abstract Resources.Theme getDropDownViewTheme();
  
  public abstract void setDropDownViewTheme(Resources.Theme paramTheme);
}
