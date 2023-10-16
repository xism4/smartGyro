package android.support.v7.view.menu;

import android.support.v7.widget.Label;
import android.support.v7.widget.ListPopupWindow;
import android.widget.ListView;

class e
{
  public final MenuBuilder c;
  public final int d;
  public final Label this$0;
  
  public e(Label paramLabel, MenuBuilder paramMenuBuilder, int paramInt)
  {
    this$0 = paramLabel;
    c = paramMenuBuilder;
    d = paramInt;
  }
  
  public ListView get()
  {
    return this$0.add();
  }
}
