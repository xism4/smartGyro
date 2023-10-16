package android.support.v7.widget;

import android.os.Build.VERSION;
import android.view.View;

public class MenuItemImpl
{
  public static void add(View paramView, CharSequence paramCharSequence)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      paramView.setTooltipText(paramCharSequence);
      return;
    }
    ClassWriter.a(paramView, paramCharSequence);
  }
}
